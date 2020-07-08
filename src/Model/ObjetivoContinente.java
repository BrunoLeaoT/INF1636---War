package Model;

import java.util.ArrayList;

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
			if(jogador.hasContinente(c)) {
				ContinentesEmPosse.add(c);
			}
			
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
	
	public String getContinentesTexto()
	{
		if(PrecisaOutroQualquer)
			return String.format("%s, %s e mais um continente a sua escolha", Continentes[0].getNome(), Continentes[1].getNome());
		else
			return String.format("%s e %s", Continentes[0].getNome(), Continentes[1].getNome());
	}

	@Override
	public String objetivoEmString() {
		String objetivo = "Continente:";
		if(PrecisaOutroQualquer)
			objetivo += "PrecisaOutroQualquer,";
		for (int i = 0; i < Continentes.length; i++) {
			objetivo += Continentes[i].getNome().replaceAll("\\s", "") + ",";
		}
		return objetivo.substring(0, objetivo.length() -1); // Remover ultima virgula
	}
	
}
