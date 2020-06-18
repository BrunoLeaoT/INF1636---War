package Model;

import java.util.Random;

public abstract class Objetivo 
{
	
	static public Objetivo CreateNew()
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
				return null;
					
		}
	}
	
	public abstract boolean verificarVitoria(Jogador jogador);
	
}
