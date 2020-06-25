package Model;

public class Partida 
{
	static private Partida singleton;
	private int turno;
	
	static public Partida getInstancia()
	{
		if(singleton == null)
			singleton = new Partida();
		return singleton;
	}
	
	private Partida()
	{
		turno = 0;
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
			territorios.selectTerritorioByIndex(i).SetDono(jog, 1);
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
	public boolean processaAtaque(String nomeTerritorioatacante, String nomeTerritorioDefensor, int qtdDadosAtacante) throws Exception
	{
		Territorio atacante = Territorios.getInstancia().selectTerritorioByName(nomeTerritorioatacante);
		Territorio defensor = Territorios.getInstancia().selectTerritorioByName(nomeTerritorioDefensor);
		
		// Confere se territorio pode atacar com essa qtd de tropas
		if(atacante.getTropas() - 1 < qtdDadosAtacante)
			throw new IllegalArgumentException("Voce está tentando atacar com mais tropas do que é permitido");
		
		
		// processa e devolve vencedor
		return true;
	}
	
	
	// fazerr funcao de caminahr por turno.
}
