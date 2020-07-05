package Controller;

import Model.Partida;
import View.CartaView;
import View.ObjetivoView;

public class TabuleiroController 
{
	private Partida partida;
	
	public TabuleiroController() 
	{
		partida = Partida.getInstancia();
	}
	
	public void tryShowObjetivo()
	{
		String textoObjetivo = partida.getTextoObjetivoJogadorDaVez();
		ObjetivoView ov = new ObjetivoView(textoObjetivo);
		ov.setVisible(true);
	}
	
	public void tryShowCartas()
	{
		CartaView cv = new CartaView(new CartaController());
		cv.setVisible(true);
	}
}
