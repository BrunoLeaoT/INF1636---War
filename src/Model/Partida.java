package Model;

import java.util.ArrayList;

public class Partida 
{
	static private Partida singleton;
	private int turno;
	private int numeroTrocas;
	
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
	}
	
	public Jogador getJogadorDaVez()
	{
		int indexVez = turno % Jogadores.getInstancia().getQtdJogadores();
		return Jogadores.getInstancia().selectJogadorByIndex(indexVez);
	}
	
	public Jogador passaTurno()
	{	
		turno++;
		return getJogadorDaVez();
	}
	
	public void adicionarJogador(String nome, Cor cor) throws Exception
	{
		Jogador j = new Jogador(nome, cor);
		Jogadores.getInstancia().addJogador(j);
	}
	
	// Distribui objetivos e territorios entre os jogadores, alem 
	public void comecarPartida() throws Exception
	{
		if(Jogadores.getInstancia().getQtdJogadores() == 0)
			throw new Exception("Impossivel comecar partida sem jogadores!");
		
		// Embaralha jogadores (primeiro jogador é aleatorio)
		Jogadores.getInstancia().shuffleJogadores();
		
		// Embaralha e destribui territorios
		Territorios.getInstancia().shuffleTerritorios();
		distribuirTerritorios();
		
		// Limpa, embaralha e distribui objetivos
		Objetivos.getInstancia().limpaObjetivosImpossiveis(Jogadores.getInstancia().getJogadoresCor());
		Objetivos.getInstancia().shuffleObjetivos();
		distribuirObjetivos();
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
	
	// Adiciona tropas a um territorio
	public void addTropasTerritorio(String nomeTerritorio, int tropas) throws Exception
	{
		Territorio t = Territorios.getInstancia().selectTerritorioByName(nomeTerritorio);
		t.addTropas(tropas);
	}
	
	// Remove tropas de um territorio
	public void rmTropasTerritorio(String nomeTerritorio, int tropas) throws Exception
	{
		Territorio t = Territorios.getInstancia().selectTerritorioByName(nomeTerritorio);
		t.rmTropas(tropas);
	}
	
	public boolean podemAtacar(String nomeTerritorioAtacante, String nomeTerritorioDefensor) throws Exception
	{
		Territorio atacante = Territorios.getInstancia().selectTerritorioByName(nomeTerritorioAtacante);
		Territorio defensor = Territorios.getInstancia().selectTerritorioByName(nomeTerritorioDefensor);
		
		return atacante.podeAtacar(defensor);
	}
	
	// Realiza logica de ataque entre dois territorios
	// Retorna o nome do territorio vencedor
	public boolean processaAtaque(String nomeTerritorioatacante, String nomeTerritorioDefensor) throws Exception
	{
		Territorio atacante = Territorios.getInstancia().selectTerritorioByName(nomeTerritorioatacante);
		Territorio defensor = Territorios.getInstancia().selectTerritorioByName(nomeTerritorioDefensor);
		
		// Estabelece quantidade de dados
		int qtdDadosDefensor = Math.min(defensor.getTropas(), 3);
		int qtdDadosAtacante = Math.min(atacante.getTropas() - 1, 3);
		if(qtdDadosAtacante < 1)
			throw new IllegalArgumentException("Voce nao pode atacar com somente uma tropa no territorio");
		
		// Cria e joga dados
		Dados dadosDefesa = new Dados(qtdDadosDefensor);
		Dados dadosAtaque = new Dados(qtdDadosAtacante);
		int[] resultadosAtaque = dadosDefesa.jogarEOrganizar();
		int[] resultadosDefesa = dadosAtaque.jogarEOrganizar();
		
		// Compara dados
		int vitoriasAtaque = 0;
		for(int i = 0; i < qtdDadosDefensor; i++)
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
		return j.atualizaTropasDisponiveis();
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
	
	
	// fazerr funcao de caminahr por turno.
}
