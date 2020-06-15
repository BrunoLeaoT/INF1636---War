package Model;

import java.util.ArrayList;
import java.util.Random;

public class ObjetivoTerritorio extends Objetivo{
	int nmTerritorios;
	boolean PrecisaDuasTropas = false;
	
	public ObjetivoTerritorio() {
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
		ArrayList<Territorio> territorio = Territorio.territorios;
		int territoriosConquistados = 0;
		
		for (int i = 0; i < territorio.size(); i++) {
			if(territorio.get(i).GetDono().getCor().compareTo(jogador.getCor()) == 0)
				if(nmTerritorios == 24)
					territoriosConquistados++;
				else
					if(Territorio.GetTropas(territorio.get(i).Nome) >= 2)
						territoriosConquistados++;
		}
		
		if(territoriosConquistados >= nmTerritorios)
			return true;
		return false;
			
	}
	
	
}
