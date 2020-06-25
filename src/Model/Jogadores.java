package Model;

import java.util.ArrayList;
import java.util.Collections;

// Singleton simples (nao thread safe)
public class Jogadores 
{
	static private Jogadores singleton;
	private ArrayList<Jogador> ListaJogadores;
	private int qtdJogadores;
	
	static public Jogadores getInstancia()
	{
		if (singleton == null)
			singleton = new Jogadores();
		return singleton;
	}
	
	private Jogadores()
	{
		ListaJogadores = new ArrayList<Jogador>();
		qtdJogadores = 0;
	}
	
	public void addJogador(Jogador j) throws Exception
	{
		if(qtdJogadores > 6)
			throw new Exception("O jogo n�o pode ter mais de 6 jogadores");
		
		ListaJogadores.add(j);
		qtdJogadores++;
	}
	
	public Jogador selectJogadorByIndex(int index)
	{
		return ListaJogadores.get(index);
	}
	
	public int getQtdJogadores()
	{
		return qtdJogadores;
	}
	
	// Retorna lista de cores (de jogadores) existentes na partida 
	// Obs: lista � ordenada de acordo com a lista de jogadores 
	public ArrayList<Cor> getJogadoresCor()
	{
		ArrayList<Cor> cores = new ArrayList<Cor>();
		for(Jogador j : ListaJogadores)
			cores.add(j.getCor());
		return cores;
	}
	
	public void shuffleJogadores()
	{
		Collections.shuffle(ListaJogadores);
	}
	
}