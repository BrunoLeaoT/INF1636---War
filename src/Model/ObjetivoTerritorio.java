package Model;

import java.util.Random;

public class ObjetivoTerritorio extends Objetivo
{
	private int nmTerritorios;
	private boolean PrecisaDuasTropas = false;
	
	public ObjetivoTerritorio(boolean precisaDuasTropas, int numeroDeTerritorios) 
	{
		Random rand = new Random();
		if(rand.nextBoolean())
		{
			nmTerritorios = 18;
			PrecisaDuasTropas = true;
		}
		else
			nmTerritorios = 24;
	}

	@Override
	public boolean verificarVitoria(Jogador jogador) {
		Territorios territorios = Territorios.getInstancia();
		int territoriosConquistados = 0;
		
		for (int i = 0; i < territorios.getSize(); i++) {
			if(territorios.selectTerritorioByIndex(i).getDono().getCor().compareTo(jogador.getCor()) == 0)
				if(nmTerritorios == 24)
					territoriosConquistados++;
				else
					if(territorios.selectTerritorioByIndex(i).getTropas() >= 2)
						territoriosConquistados++;
		}
		
		if(territoriosConquistados >= nmTerritorios)
			return true;
		return false;
			
	}
	
	public int getNumTerritorios()
	{
		return nmTerritorios;
	}
	
	public boolean getPrecisaDuasTropas()
	{
		return PrecisaDuasTropas;
	}

	@Override
	public String objetivoEmString() {
		String objetivo = "Territorio:"+nmTerritorios;
		if(PrecisaDuasTropas)
			objetivo += "-2";
		else
			objetivo += "-1";
		return objetivo ;
	}
	
	
}
