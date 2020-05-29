package Model;

import java.util.ArrayList;
import java.util.Random;

public class ObjetivoTerritorio implements IObjetivo{
	int nmTerritorios;
	static ObjetivoTerritorio obj = new ObjetivoTerritorio(-1);
	
	public ObjetivoTerritorio(int nm) {
		nmTerritorios = nm;
	}
	
	@Override
	public IObjetivo randomizarObjetivo() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		if(rand.nextBoolean())
			return new ObjetivoTerritorio(18);
		else
			return new ObjetivoTerritorio(24);
		
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
					if(territorio.get(i).GetTropas() >= 2)
						territoriosConquistados++;
		}
		
		if(territoriosConquistados >= nmTerritorios)
			return true;
		return false;
			
	}
	
	
}
