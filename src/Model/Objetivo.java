package Model;

import java.util.ArrayList;
import java.util.Random;

public abstract class Objetivo 
{
	static public Objetivo CreateNew() throws Exception
	{
		Random rand = new Random();
		switch(rand.nextInt(3))
		{
			case 0:
				return new ObjetivoContinente();
			case 1:
				return new ObjetivoTerritorio();
			case 2:
				return new ObjetivoJogador();
			default:
				// Nunca deve acontecer, mas o compiler pede um default.
				throw new Exception("Demita o programador.");
					
		}
	}
	
	public abstract boolean verificarVitoria(Jogador jogador);
	
}
