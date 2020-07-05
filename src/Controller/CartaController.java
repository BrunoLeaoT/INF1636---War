package Controller;

import Model.Partida;

public class CartaController 
{
	private Partida partida;
	
	public CartaController() 
	{
		partida = Partida.getInstancia();
	}
	
	public String[][] tryGetCartas()
	{
		return partida.getCartasJogadorDaVez();
	}
	
	public void tryFazerTroca() throws Exception
	{
		partida.fazTrocaJogadorDaVez();
	}
}
