package Controller;

import Model.Partida;

public class TabuleiroController 
{
	private Partida partida;
	
	public TabuleiroController() 
	{
		partida = Partida.getInstancia();
	}
}
