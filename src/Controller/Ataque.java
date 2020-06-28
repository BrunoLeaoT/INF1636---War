package Controller;

import Model.Geral;
import Model.Partida;

public class Ataque {
	private Geral geral;
	private Partida partida;
	public Ataque() {
		geral = Geral.getGeral();
	}
	
	public boolean realizarAtaque(String nomeTerritorioAtacante, String nomeTerritorioDefensor) throws Exception
	{
		if(partida.podemAtacar(nomeTerritorioAtacante, nomeTerritorioDefensor))
		{						
			if(partida.processaAtaque(nomeTerritorioAtacante, nomeTerritorioDefensor))
			{
				// da carta
				return true;
			}
			else
				return false;
		}
		else
			throw new Exception("Voce nao pode atacar esse territorio");
	}
}
