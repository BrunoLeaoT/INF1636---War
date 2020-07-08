package Controller;

import Model.Partida;

public class AtaqueController 
{
	private Partida partida;
	
	public AtaqueController() 
	{
		partida = Partida.getInstancia();
	}
	
	public boolean tryRealizarAtaque(int xDefensor, int yDefensor) throws Exception
	{
		if(partida.selecionadoPodeAtacar(xDefensor,yDefensor))
		{						
			if(partida.processaAtaque(xDefensor, yDefensor))
			{
				partida.daCartaJogadorDaVez();
				return true;
			}
			else
				return false;
		}
		else
			throw new Exception("Voce nao pode atacar esse territorio.");
	}
}
