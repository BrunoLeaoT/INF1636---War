package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import View.SelecaoLabel;
import View.JogadorDaVezLabel;

public class Partida implements Observado
{
	static private Partida singleton;
	
	private int turno;
	private int numeroTrocas;
	private Jogador jogadorDaVez;
	private HashMap<String, String> currentTerritorioSelecionado;
		
	// boolean para contralar momentos da vez do jogador (primeiro ele insere tropas, depois ataca, depois remaneja)
	// nao devem ser referenciados diretamente, mas sim pelos metodos encerraFaseAtaque() e encerraFaseInserirTropas
	private boolean acabouFaseAtaque;
	private boolean acabouFaseInserirTropas;
	
	// obervadores
	private ArrayList<Observador> observadorList;
	
	static public Partida getInstancia()
	{
		if(singleton == null)
			singleton = new Partida();
		return singleton;
	}
	
	private Partida()
	{
		turno = 0;
		numeroTrocas = 0;


		
		currentTerritorioSelecionado = new HashMap<String, String>();
		observadorList = new ArrayList<Observador>();
	}
	
	// Funcoes interface
	@Override
	public void addObservador(Observador obs) 
	{
		observadorList.add(obs);
		
		// notifica observadores por default
		this.notificarObservadores();
	}

	@Override
	public void rmObservador(Observador obs) 
	{
		observadorList.remove(obs);
	}

	@Override
	public void notificarObservadores() 
	{
		for(Observador obs : observadorList)
		{
			if(obs instanceof SelecaoLabel)
				obs.atualizarObservacao(geraInfoTerritorioSelecionado());
			else if (obs instanceof JogadorDaVezLabel)
				obs.atualizarObservacao(geraInfoJogadorDaVez());
		}
	}
	
	private Territorio getTerritorioNaPosicao(int x, int y) throws Exception
	{
		return Territorios.getInstancia().selectTerritorioByCoordenada(x, y);
	}
	
	// gera uma string com as infos do jogador da vez
	private String geraInfoJogadorDaVez()
	{
		String nome = this.jogadorDaVez.getNome();
		String cor = this.jogadorDaVez.getCor().name();
		int tropasDisponiveis = this.jogadorDaVez.getTropasDisponiveis();
		return String.format("Jogador da vez: %s (%s), com %d tropas para serem distribuidas", nome, cor, tropasDisponiveis);
	}
	
	// gera uma string com as infos do territorio selecionado corrente.
	private String geraInfoTerritorioSelecionado()
	{
		if(currentTerritorioSelecionado.size() == 0)
			return "";
		else
		{
			String nomeTerr = currentTerritorioSelecionado.get("nome");
			String corDono = currentTerritorioSelecionado.get("dono");
			String tropas = currentTerritorioSelecionado.get("tropas");
			return String.format("Territorio selecionado: %s; de %s; com %s tropas", nomeTerr, corDono, tropas);
		}
	}
	
	public void updateJogadorDaVez()
	{
		System.out.println(turno + " + "  + Jogadores.getInstancia().getQtdJogadores());
		int indexVez = turno % Jogadores.getInstancia().getQtdJogadores();
		System.out.println(indexVez);
		jogadorDaVez = Jogadores.getInstancia().selectJogadorByIndex(indexVez);
		jogadorDaVez.atualizaTropasDisponiveis();
	}
	
	public String getTurnoETrocaEmString() {
		return turno +"-"+numeroTrocas;
	}
	
	public void setTurnoETroca(int turno, int troca) {
		this.turno = turno;
		numeroTrocas = troca;
	}
	
	public boolean passaTurno() throws Exception
	{	
		verificaJogadorInseriuTodasAsTropas();
		
		// verifica se jogador que encerra a vez ganhou o jogo
		//this.verificaVitoriaJogadorDaVez();
		
		// configura prox turno
		turno++;
		updateJogadorDaVez();
		resetFasesTurno();
		
		// notifica observers sobre novo turno
		this.notificarObservadores();
		
		return false;
	}
	
	// reseta o turno para fase inicial, em que o jogador pode fazer ataques, insercoes de tropas ou remanejamento
	public void resetFasesTurno()
	{	
		// na primeira rodada ngm pode atacar
		if(turno / Jogadores.getInstancia().getQtdJogadores() == 0)
			acabouFaseAtaque = true;
		else
			acabouFaseAtaque = false;
		
		acabouFaseInserirTropas = false;
	}
	
	private void verificaJogadorInseriuTodasAsTropas() throws Exception
	{
		if(jogadorDaVez.getTropasDisponiveis() != 0)
			throw new Exception("Voce não pode realizar essa ação antes de inserir todas as tropas disponíveis");
	}
	
	public void adicionarJogador(String nome, String nomeCor) throws Exception
	{
		Cor cor = Cor.valueOf(nomeCor);
		this.adicionarJogador(nome,  cor);
	}
	
	public void adicionarJogador(String nome, Cor cor) throws Exception
	{
		Jogador j = new Jogador(nome, cor);
		Jogadores.getInstancia().addJogador(j);
	}
	
	// Distribui objetivos e territorios entre os jogadores, alem 
	public void comecarPartida() throws Exception
	{
		if(Jogadores.getInstancia().getQtdJogadores() < 3)
			throw new Exception(String.format("Impossivel comecar partida com %d jogadores!", Jogadores.getInstancia().getQtdJogadores()));
		
		// Embaralha jogadores (primeiro jogador é aleatorio)
		Jogadores.getInstancia().shuffleJogadores();
		
		// Embaralha e destribui territorios
		Territorios.getInstancia().shuffleTerritorios();
		distribuirTerritorios();
		
		// Limpa, embaralha e distribui objetivos
		Objetivos.getInstancia().limpaObjetivosImpossiveis(Jogadores.getInstancia().getJogadoresCor());
		Objetivos.getInstancia().shuffleObjetivos();
		distribuirObjetivos();

		 this.iniciarPartida();
	}
	
	// após carregar partida, inicia ela notificando os observadores e setando o jogador da vez
	public void iniciarPartida() 
	{
		// setta jogador da vez
		this.updateJogadorDaVez();
		
		// notifica observers sobre o inicio da partida
		this.notificarObservadores();
	}
	public void distribuirTerritorios()
	{
		Territorios territorios = Territorios.getInstancia();
		Jogadores jogadores = Jogadores.getInstancia();
		
		for(int i = 0; i < territorios.getSize(); i++)
		{
			Jogador jog = jogadores.selectJogadorByIndex(i % jogadores.getQtdJogadores());
			Territorio t = territorios.selectTerritorioByIndex(i);
			t.setDono(jog);
			t.addTropas(1);
		}
	}
	
	public void distribuirObjetivos()
	{
		Jogadores jogadores = Jogadores.getInstancia();
		Objetivos objetivos = Objetivos.getInstancia();
		for(int i = 0; i < jogadores.getQtdJogadores(); i++)
		{
			jogadores.selectJogadorByIndex(i).setObjetivo(objetivos.compraObjetivo());
		}
	}
	
	// Insere UMA tropa ao territorio na posicao
	public void inserirTropasEmSelecionado() throws Exception
	{
		if(currentTerritorioSelecionado.get("nome") == null)
			throw new Exception("Voce precisa ter um territorio selecionado para inserir tropas nele. Um território, caso esteja selecionado, aparece no topo da tela.");
		
		if(acabouFaseInserirTropas)
			throw new Exception("Voce saiu da fase de inserir tropas a partir do momento que atacou um territorio");
		
		Territorio t = Territorios.getInstancia().selectTerritorioByName(this.currentTerritorioSelecionado.get("nome"));
		
		if(t.getDono() != this.jogadorDaVez)
			throw new Exception("Voce não pode inserir tropas em um território que não é seu");
		
		removeTropasDisponiveisJogadorDaVez(1);
		t.addTropas(1);
		
		// notifica observers sobre insercao de tropas
		this.notificarObservadores();
	}
	
	// Remaneja TODAS as tropas do territorio selecionado para outro nas coordenadas x,y
	public void selecionadoRemanejaTropas(int xDestino, int yDestino) throws Exception
	{
		verificaJogadorInseriuTodasAsTropas();
		
		// jogador comecou a remanejar -> acabou fase de ataque
		acabouFaseAtaque = true;
		
		if(currentTerritorioSelecionado.get("nome") == null)
			throw new Exception("Voce precisa ter um territorio selecionado antes de enviar tropas para outro. Um território, caso esteja selecionado, aparece no topo da tela.");

		Territorio origem = Territorios.getInstancia().selectTerritorioByName(currentTerritorioSelecionado.get("nome"));
		Territorio destino = getTerritorioNaPosicao(xDestino, yDestino);
		
		if(origem.getDono() != jogadorDaVez)
			throw new Exception("Voce nao pode remanejar de um territorio inimigo");
		
		if(destino.getDono() != origem.getDono())
			throw new Exception("Voce nao pode remanejar tropas para um território inimigo");
		
		origem.remanejarTropas(destino, origem.getTropas() - 1);
	}
	
	// Confere se territorio selecionado pode atacar territorio desejado
	public boolean selecionadoPodeAtacar(int xDefensor, int yDefensor) throws Exception
	{
		verificaJogadorInseriuTodasAsTropas();
			
		// jogador comecou a atacar -> acabou fase de insercao de tropas
		acabouFaseInserirTropas = true;

		if(acabouFaseAtaque == true)
			throw new Exception("Você não pode atacar territórios na primeira rodada, ou após remanejar tropas");
		
		if(currentTerritorioSelecionado.get("nome") == null)
			throw new Exception("Você precisa ter um territorio selecionado antes de atacar outro. Um território, caso esteja selecionado, aparece no topo da tela.");

		Territorio atacante = Territorios.getInstancia().selectTerritorioByName(currentTerritorioSelecionado.get("nome"));
		Territorio defensor = getTerritorioNaPosicao(xDefensor, yDefensor);
		
		return atacante.podeAtacar(defensor);
	}
	
	// Realiza logica de ataque entre o territorio selecionado contra outro de entrada
	// Retorna o true se teritorio selecionao ganhou, false caso contrario
	public boolean processaAtaque(int xDefensor, int yDefensor) throws Exception
	{		
		Territorio atacante = Territorios.getInstancia().selectTerritorioByName(currentTerritorioSelecionado.get("nome"));
		Territorio defensor = Territorios.getInstancia().selectTerritorioByCoordenada(xDefensor, yDefensor);
		
		// Estabelece quantidade de dados
		int qtdDadosDefensor = Math.min(defensor.getTropas(), 3);
		int qtdDadosAtacante = Math.min(atacante.getTropas() - 1, 3);
		if(qtdDadosAtacante < 1)
			throw new IllegalArgumentException("Voce nao pode atacar com somente uma tropa no territorio");
		
		// Cria e joga dados
		Dados dadosDefesa = new Dados(qtdDadosDefensor);
		Dados dadosAtaque = new Dados(qtdDadosAtacante);
		int[] resultadosDefesa = dadosDefesa.jogarEOrganizar();
		int[] resultadosAtaque = dadosAtaque.jogarEOrganizar();
		
		// Compara dados
		int vitoriasAtaque = 0;

		for(int i = 0; i < qtdDadosDefensor && i < qtdDadosAtacante; i++)
		{
			if(resultadosAtaque[i] > resultadosDefesa[i])
				vitoriasAtaque++;
		}
		
		atacante.rmTropas(qtdDadosDefensor - vitoriasAtaque);
		defensor.rmTropas(vitoriasAtaque);
		
		if(atacante.getTropas() == 0)
			System.out.println("xabu");
		
		// Checa se atacante conquistou territorio
		if(defensor.getTropas() == 0)
		{
			// Atacante conquistou o territorio
			// Move todas as tropas pro novo territorio
			
			// ISSO AQUI TEM QUE SER UM "REMANEJAR"
			defensor.setDono(atacante.getDono());
			atacante.remanejarTropas(defensor, atacante.getTropas() - 1);
			return true;
		}
		else
			return false;
	}
	
	public void daCartaJogadorDaVez()
	{
		this.jogadorDaVez.addCarta(Cartas.getInstancia().compraCarta());
	}
	
	public void removeTropasDisponiveisJogadorDaVez(int qtd) throws Exception
	{
		this.jogadorDaVez.rmTropasDisponiveis(qtd);
	}
	
	public void fazTrocaJogadorDaVez() throws Exception
	{		
		// remove cartas do jogador
		ArrayList<Carta> cartasTrocadas = this.jogadorDaVez.tentaTrocarCartas(numeroTrocas);
		if(cartasTrocadas == null)
			throw new Exception("Jogador da vez não pode fazer troca de cartas");
		
		// volta cartas pro deck e embaralha
		for(Carta c : cartasTrocadas)
			Cartas.getInstancia().addCarta(c);
		Cartas.getInstancia().shuffleDeck();
	}
	
	// Candidato a Refactor!!
	// Poderiaos usar stringbuilder aqui, ja que strings sao imutaveis e o += é caro
	// podemos usar ToString() em cada objeto
	public String getTextoObjetivoJogadorDaVez()
	{
		Objetivo o = this.jogadorDaVez.getObjetivo();

		String texto;
		if(o instanceof ObjetivoTerritorio)
		{
			ObjetivoTerritorio ot = (ObjetivoTerritorio) o;
			texto = String.format("Seu objetivo é possuir %d territórios simultâneamente", ot.getNumTerritorios());
			if(ot.getPrecisaDuasTropas())
				texto += ", e possuir no mínimo dois exércitos em cada";
			return texto;
		}
		else if(o instanceof ObjetivoJogador)
		{
			ObjetivoJogador oj = (ObjetivoJogador) o;
			texto = "Seu objeito é eliminar o jogadores de cor " + oj.getCorAlvo().name();
			return texto;
		}
		else if(o instanceof ObjetivoContinente)
		{
			ObjetivoContinente oc = (ObjetivoContinente) o;
			texto = String.format("Seu objetivo é conquistar na integridade os continentes: %s", oc.getContinentesTexto());
			return texto;
		}
		// nunca vai acontecer:
		return null;
	}
	
	public String[][] getCartasJogadorDaVez()
	{
		String[][] cartasInfo = new String[this.jogadorDaVez.getQtdCartas()][2];
		
		for(int i = 0; i < this.jogadorDaVez.getQtdCartas(); i++)
		{
			Carta c = this.jogadorDaVez.getCarta(i);
			cartasInfo[i][0] = c.getTerritorioNome();
			cartasInfo[i][1] = c.getCartaForma().name();
		}
		
		return cartasInfo;
	}
	
	public String verificaVitoriaJogadorDaVez()
	{
		Jogador j = this.jogadorDaVez;
		if(j.verificarVitoria())
			return j.getNome();
		return "";
	}
	
	// Funções de salvamento
	public boolean carregarJogoSalvo() {
		return Salvar.getInstancia().carregarJogo();
	}
	
	public boolean salvarJogo() {
		return Salvar.getInstancia().salvarJogo();
	}
	
	// Retorna dados do territorio que possui o ponto, ou hashmapVazio se territorio que possui o ponto nao foi encontrado
	public void setTerritorioCorrente(Point ponto)
	{		
		Territorios territorios = Territorios.getInstancia();
		for(int i = 0; i < territorios.getSize(); i++)
		{
			Territorio t = territorios.selectTerritorioByIndex(i);
			//if(t.delimitacaoPossuiPonto(ponto))
			if(t.contemPonto(ponto))
			{
				currentTerritorioSelecionado.put("nome", t.getNome());
				currentTerritorioSelecionado.put("dono", t.getDono().getCor().name());
				currentTerritorioSelecionado.put("tropas", String.valueOf(t.getTropas()));
				this.notificarObservadores();
				return;
			}
		}
		
		// se nao encontra
		currentTerritorioSelecionado.clear();
		this.notificarObservadores();
		return;
	}
	

	public String getCentroidTerritorio(String territorio) throws Exception 
	{
		return Territorios.getInstancia().selectTerritorioByName(territorio).getCentroid();
	}

	public void addObservadorTerritorio(Observador obs, String territorio) throws Exception 
	{
		Territorios.getInstancia().selectTerritorioByName(territorio).addObservador(obs);
	}
}
