package Controller;

import Model.Partida;
import View.TabuleiroView;
import Model.Cor;

public class AdicionarJogadoresController 
{
	private Partida partida;
	
	public AdicionarJogadoresController() 
	{
		partida = Partida.getInstancia();
	}
	
	public void tryAddJogador(String nomeJogador, String nomeCor) throws Exception
	{
		partida.adicionarJogador(nomeJogador, nomeCor);
	}
	
	public void tryIniciarPartida() throws Exception
	{
		// comeca partida
		partida.comecarPartida();
		
		// inicia prox view
		TabuleiroController tabController = new TabuleiroController();
		TabuleiroView tabView = new TabuleiroView(tabController);
		tabView.setVisible(true);
		
	}
}
