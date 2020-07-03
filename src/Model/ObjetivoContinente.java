package Model;

import java.util.ArrayList;
import java.util.Random;

public class ObjetivoContinente extends Objetivo 
{
	private Continente[] Continentes;
	private boolean PrecisaOutroQualquer; 
	
	public ObjetivoContinente(boolean precisaOutroQualquer, Continente... conts) 
	{
		PrecisaOutroQualquer = precisaOutroQualquer;
		Continentes = conts;
	}

	@Override
	public boolean verificarVitoria(Jogador jogador) 
	{
		// Determina continentes que o jogador possui.
		ArrayList<Continente> ContinentesEmPosse = new ArrayList<Continente>();
		for(Continente c : Continentes)
		{
			if(jogador.hasContinente(c))
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
