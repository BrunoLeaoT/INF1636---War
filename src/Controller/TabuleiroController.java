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
	
	public void addObservadorPartidajogadorDaVez(Observador obs)
	{
		partida.addObservador(obs);
	}
	
	public void addObservadorTerritorio(Observador obs, String territorio)
	{
		try 
		{
			partida.addObservadorTerritorio(obs, territorio);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public String getCentroidTerritorio(String territorio) {
		try 
		{
			return partida.getCentroidTerritorio(territorio);
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	public void tryShowObjetivo()
	{
		String textoObjetivo = partida.getTextoObjetivoJogadorDaVez();
		System.out.println(textoObjetivo);
		ObjetivoView ov = new ObjetivoView(textoObjetivo);
		ov.setVisible(true);
	}
	
	public void tryShowCartas()
	{
		CartaView cv = new CartaView(new CartaController());
		cv.setVisible(true);
	}
	
	public String tryAcabarVez()
	{
		String vitorioso = partida.verificaVitoriaJogadorDaVez();
		try {
			partida.passaTurno();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vitorioso;
	}
	
	public void trySelecionarTerritorio(int x, int y)
	{
		Point p = new Point(x,y);
		partida.setTerritorioCorrente(p);
	}
	
	public void tryRemanejar(int x, int y) throws Exception 
	{
		Point p = new Point(x,y);
		partida.selecionadoRemanejaTropas(x, y);
	}
	
	public void tryInserirTropasEmSelecionado() throws Exception
	{
		partida.inserirTropasEmSelecionado();
	}
}
