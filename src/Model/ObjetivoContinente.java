package Model;

import java.util.ArrayList;
import java.util.Random;

public class ObjetivoContinente extends Objetivo 
{
	private ArrayList<Continente> Continentes;
	private boolean PrecisaOutroQualquer; 
	
	public ObjetivoContinente() 
	{
		Continentes = new ArrayList<Continente>();
		PrecisaOutroQualquer = false;
		
		Random rand = new Random();
		switch(rand.nextInt(6)) 
		{
			case 0:
				Continentes.add(Continente.Europa);
				Continentes.add(Continente.Oceania);
				PrecisaOutroQualquer = true;
				break;
			case 1:
				Continentes.add(Continente.Asia);
				Continentes.add(Continente.AmericaDoSul);
				break;
			case 2:
				Continentes.add(Continente.Europa);
				Continentes.add(Continente.AmericaDoSul);
				PrecisaOutroQualquer = true;
				break;
			case 3:
				Continentes.add(Continente.Asia);
				Continentes.add(Continente.Africa);
				break;
			case 4:
				Continentes.add(Continente.AmericaDoNorte);
				Continentes.add(Continente.Africa);
				break;
			case 5:
				Continentes.add(Continente.AmericaDoNorte);
				Continentes.add(Continente.Oceania);
				break;
		}
	}

	@Override
	public boolean verificarVitoria(Jogador jogador) 
	{
		// Determina continentes que o jogador possui.
		ArrayList<Continente> ContinentesEmPosse = new ArrayList<Continente>();
		for(Continente c : Continentes)
		{
			if(jogador.HasContinente(c))
				ContinentesEmPosse.add(c);
		}
		
		// Checa se dentre os continentes que o jogador possui estão os que o objetivo precisa.
		for(Continente cNecessario : Continentes)
		{
			if(!ContinentesEmPosse.contains(cNecessario))
				return false;
		}
		
		// Checa se possui o Continente extra, caso objetivo precise.
		if(PrecisaOutroQualquer && ContinentesEmPosse.size() < 3)
			return false;
		
		// Se chegou até aqui, passou nos dois Checks.
		return true;
	}
	
}
