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
	
	public void addJogador(Jogador novoj) throws Exception
	{
		if(qtdJogadores == 6)
			throw new Exception("O jogo não pode ter mais de 6 jogadores");
		
		for(Jogador jexistente : ListaJogadores)
		{
			if(jexistente.getNome().equals(novoj.getNome()))
				throw new Exception("Já existe um jogador com esse nome");
			if(jexistente.getCor() == novoj.getCor())
				throw new Exception("Já existe um jogador com essa cor");
		}
		
		ListaJogadores.add(novoj);
		qtdJogadores++;
	}
	
	public Jogador selectJogadorByIndex(int index)
	{
		return ListaJogadores.get(index);
	}
	
	public Jogador selectJogadorByName(String nome)
	{
		for (int i = 0; i < ListaJogadores.size(); i++) {
			if(ListaJogadores.get(i).getNome().compareTo(nome) == 0)
				return ListaJogadores.get(i);
		}
		return null;
	}
	
	public int getQtdJogadores()
	{
		return ListaJogadores.size();
	}
	
	// Retorna lista de cores (de jogadores) existentes na partida 
	// Obs: lista é ordenada de acordo com a lista de jogadores 
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

	public void removeJogador(String nome) {
		ListaJogadores.remove(selectJogadorByName(nome));
	}
}
