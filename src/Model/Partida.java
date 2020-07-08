package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import View.InfoLabel;

public class Partida implements Observado
{
	static private Partida singleton;
	
	private int turno;
	private int numeroTrocas;
	private HashMap<String, String> currentTerritorioSelecionado;
		
	// boolean para contralar momentos da vez do jogador (primeiro ele insere tropas, depois ataca, depois remaneja)
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
			if(obs instanceof InfoLabel)
				obs.atualizarObservacao(geraInfoTerritorioSelecionado());
		}
	}
	
	private Territorio getTerritorioNaPosicao(int x, int y) throws Exception
	{
		return Territorios.getInstancia().selectTerritorioByCoordenada(x, y);
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
	
	public Jogador getJogadorDaVez()
	{
		int indexVez = turno % Jogadores.getInstancia().getQtdJogadores();
		return Jogadores.getInstancia().selectJogadorByIndex(indexVez);
	}
	
	public String getInfoJogadorDaVez()
	{
		int indexVez = turno % Jogadores.getInstancia().getQtdJogadores();
		Jogador j = Jogadores.getInstancia().selectJogadorByIndex(indexVez);
		
		return "Vez de " + j.getNome() + ", da cor " + j.getCor().name();
	}
	
	public String getTurnoETrocaEmString() {
		return turno +"-"+numeroTrocas;
	}
	
	public void setTurnoETroca(int turno, int troca) {
		this.turno = turno;
		numeroTrocas = troca;
	}
	
	public Jogador passaTurno()
	{	
		turno++;
		resetFasesTurno();
		getJogadorDaVez().atualizaTropasDisponiveis();
		return getJogadorDaVez();
	}
	
	// reseta o turno para fase inicial, em que o jogador pode fazer ataques, insercoes de tropas ou remanejamento
	public void resetFasesTurno()
	{
		acabouFaseAtaque = false;
		acabouFaseInserirTropas = false;
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
		
		// calcula tropas disponiveis para cada jogador inicialmente
		Jogadores.getInstancia().atualizarTropasDisponiveisTodos();
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
		
		if(t.getDono() != this.getJogadorDaVez())
			throw new Exception("Voce não pode inserir tropas em um território que não é seu");
		
		t.addTropas(1);
		removeTropasDisponiveisJogadorDaVez(1);
	}
	
	// Remaneja TODAS as tropas do territorio selecionado para outro nas coordenadas x,y
	public void selecionadoRemanejaTropas(int xDestino, int yDestino) throws Exception
	{
		// jogador comecou a remanejar -> acabou fase de ataque
		acabouFaseAtaque = true;
		
		if(currentTerritorioSelecionado.get("nome") == null)
			throw new Exception("Voce precisa ter um territorio selecionado antes de enviar tropas para outro. Um território, caso esteja selecionado, aparece no topo da tela.");

		Territorio origem = Territorios.getInstancia().selectTerritorioByName(currentTerritorioSelecionado.get("nome"));
		Territorio destino = getTerritorioNaPosicao(xDestino, yDestino);
		
		origem.remanejarTropas(destino, origem.getTropas() - 1);
	}
	
	// Confere se territorio selecionado pode atacar territorio desejado
	public boolean selecionadoPodeAtacar(int xDefensor, int yDefensor) throws Exception
	{
		// jogador comecou a atacar -> acabou fase de insercao de tropas
		acabouFaseInserirTropas = true;

		if(acabouFaseAtaque == true)
			throw new Exception("Voce encerrou a fase de ataque no momento que comecou a remanejar tropas");
		
		if(currentTerritorioSelecionado.get("nome") == null)
			throw new Exception("Voce precisa ter um territorio selecionado antes de atacar outro. Um território, caso esteja selecionado, aparece no topo da tela.");

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
		Jogador j = getJogadorDaVez();
		j.addCarta(Cartas.getInstancia().compraCarta());
	}
	
	public int getTropasDisponiveisJogadorDaVez()
	{
		Jogador j = getJogadorDaVez();
		return j.getTropasDisponiveis();
	}
	
	public void removeTropasDisponiveisJogadorDaVez(int qtd) throws Exception
	{
		Jogador j = getJogadorDaVez();
		j.rmTropasDisponiveis(qtd);
	}
	
	public void fazTrocaJogadorDaVez() throws Exception
	{
		Jogador j = getJogadorDaVez();
		
		// remove cartas do jogador
		ArrayList<Carta> cartasTrocadas = j.tentaTrocarCartas(numeroTrocas);
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

		Jogador j = getJogadorDaVez();
		Objetivo o = j.getObjetivo();
		String texto;
		if(o instanceof ObjetivoTerritorio)
		{
			ObjetivoTerritorio ot = (ObjetivoTerritorio) o;
			System.out.println("a pronto 1");
			texto = String.format("Seu objetivo é possuir %d territórios simultâneamente", ot.getNumTerritorios());
			if(ot.getPrecisaDuasTropas())
				texto += ", e possuir no mínimo dois exércitos em cada";
			System.out.println(texto);
			return texto;
		}
		else if(o instanceof ObjetivoJogador)
		{
			ObjetivoJogador oj = (ObjetivoJogador) o;
			System.out.println("a pronto 2");
			texto = "Seu objeito é eliminar o jogadores de cor " + oj.getCorAlvo().name();
			System.out.println(texto);
			return texto;
		}
		else if(o instanceof ObjetivoContinente)
		{
			ObjetivoContinente oc = (ObjetivoContinente) o;
			System.out.println("a pronto 3");
			texto = String.format("Seu objetivo é conquistar na integridade os continentes: %s", oc.getContinentesTexto());
			System.out.println(texto);
			return texto;
		}
		// nunca vai acontecer:
		return null;
	}
	
	public String[][] getCartasJogadorDaVez()
	{
		Jogador j = getJogadorDaVez();
		String[][] cartasInfo = new String[j.getQtdCartas()][2];
		
		for(int i = 0; i < j.getQtdCartas(); i++)
		{
			Carta c = j.getCarta(i);
			cartasInfo[i][0] = c.getTerritorioNome();
			cartasInfo[i][1] = c.getCartaForma().name();
		}
		
		return cartasInfo;
	}
	
	public boolean verificaVitoriaJogadorDaVez()
	{
		Jogador j = getJogadorDaVez();
		return j.verificarVitoria();
	}
	
	// Funções de salvamento
	public boolean carregarJogoSalvo() {
		return Salvar.carregarJogo();
	}
	
	public boolean salvarJogo() {
		Salvar salvar = new Salvar();
		return salvar.salvarJogo();
	}
	
	// Retorna dados do territorio que possui o ponto, ou hashmapVazio se territorio que possui o ponto nao foi encontrado
	public void setTerritorioCorrente(Point ponto)
	{		
		Territorios territorios = Territorios.getInstancia();
		for(int i = 0; i < territorios.getSize(); i++)
		{
			Territorio t = territorios.selectTerritorioByIndex(i);
			if(t.delimitacaoPossuiPonto(ponto))
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
