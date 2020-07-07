package Controller;

import java.awt.Point;
import java.util.HashMap;

import Model.Observador;
import Model.Partida;
import View.CartaView;
import View.ObjetivoView;
import View.TabuleiroView;

public class TabuleiroController 
{
	private TabuleiroView tabView;
	private Partida partida;
	
	public TabuleiroController() 
	{
		partida = Partida.getInstancia();
	}
	
	public void addObservadorPartidaSelecaoLabel(Observador obs)
	{
		partida.addObservador(obs);
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
	
	public void tryAcabarVez()
	{
		partida.passaTurno();
	}
	
	public void trySelecionarTerritorio(int x, int y)
	{
		Point p = new Point(x,y);
		partida.selecionaTerritorio(p);
	}
}
