package Controller;

import Model.Partida;

public class AtaqueController {
	private Partida partida;
	
	public AtaqueController() {
		partida = Partida.getInstancia();
	}
	
	public boolean realizarAtaque(String nomeTerritorioAtacante, String nomeTerritorioDefensor) throws Exception
	{
		if(partida.podemAtacar(nomeTerritorioAtacante, nomeTerritorioDefensor))
		{						
			if(partida.processaAtaque(nomeTerritorioAtacante, nomeTerritorioDefensor))
			{
				partida.daCartaJogadorDaVez();
				return true;
			}
			else
				return false;
		}
		else
			throw new Exception("Voce nao pode atacar esse territorio");
	}
}
