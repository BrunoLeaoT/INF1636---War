package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Objetivos 
{
	static private Objetivos singleton;
	private ArrayList<Objetivo> ListaObjetivos;
	
	private Objetivos()
	{
		ListaObjetivos = new ArrayList<Objetivo>();
		inicializaObjetivos();
	}
	
	static public Objetivos getInstancia()
	{
		if(singleton == null)
				singleton = new Objetivos();
		return singleton;
	}
	
	// Remove primeiro objetivo da lista.
	public Objetivo compraObjetivo()
	{
		return ListaObjetivos.remove(0);
	}
	
	public void shuffleObjetivos()
	{
		Collections.shuffle(ListaObjetivos);
	}
	
	private void inicializaObjetivos()
	{
		// Objetivos de continente
		ListaObjetivos.add(new ObjetivoContinente(true, Continente.Europa, Continente.Oceania));
		ListaObjetivos.add(new ObjetivoContinente(false, Continente.Asia, Continente.AmericaDoSul));
		ListaObjetivos.add(new ObjetivoContinente(true, Continente.Europa, Continente.AmericaDoSul));
		ListaObjetivos.add(new ObjetivoContinente(true, Continente.Asia, Continente.Africa));
		ListaObjetivos.add(new ObjetivoContinente(true, Continente.AmericaDoNorte, Continente.Africa));
		ListaObjetivos.add(new ObjetivoContinente(true, Continente.AmericaDoNorte, Continente.Oceania));
		
		// Objetivos de jogadores
		ListaObjetivos.add(new ObjetivoJogador(Cor.Amarelo));
		ListaObjetivos.add(new ObjetivoJogador(Cor.Azul));
		ListaObjetivos.add(new ObjetivoJogador(Cor.Branco));
		ListaObjetivos.add(new ObjetivoJogador(Cor.Preto));
		ListaObjetivos.add(new ObjetivoJogador(Cor.Verde));
		ListaObjetivos.add(new ObjetivoJogador(Cor.Vermelho));
		
		// Objetivos de Territorios
		ListaObjetivos.add(new ObjetivoTerritorio(true, 18));
		ListaObjetivos.add(new ObjetivoTerritorio(false, 24));
	}
	
	// Remove os objetivos de eliminar cores não pertencentes a nenhum jogador
	// Candidata a refatoração
	public void limpaObjetivosImpossiveis(ArrayList<Cor> coresNaPartida)
	{
		// Monta lista de objetivos de tipo ObjetivoJogador
		ArrayList<ObjetivoJogador> objetivosJogador = new ArrayList<ObjetivoJogador>();	
		for(Objetivo o : ListaObjetivos)
			if(o instanceof ObjetivoJogador)
				objetivosJogador.add((ObjetivoJogador)o);
		
		for(ObjetivoJogador oj : objetivosJogador)
		{
			Cor corObjetivo = oj.getCorAlvo();
			if(!coresNaPartida.contains(corObjetivo))
				ListaObjetivos.remove(oj);
		}
	}
	
	
}
