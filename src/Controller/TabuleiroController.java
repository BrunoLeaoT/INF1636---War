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
	public String getCentroidTerritorio(String territorio) {
		try {
			return partida.getCentroidTerritorio(territorio);
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	public void addObservadorTerritorio(Observador obs, String territorio)
	{
		// Deleguei pra partida pq não soube acessar os territorios daqui
		try {
			partida.addObservadorTerritorio(obs, territorio);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		partida.setTerritorioCorrente(p);
	}
}
