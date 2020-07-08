package Controller;

import Model.Partida;
import View.AdicionarJogadoresView;
import View.TabuleiroView;

public class InicioController 
{
	private Partida partida;
	
	public InicioController() {
		partida = Partida.getInstancia();
	}
	
	public void tryConfigurarNovoJogo()
	{
		AdicionarJogadoresController addJogController = new AdicionarJogadoresController();
		AdicionarJogadoresView addJogView = new AdicionarJogadoresView(addJogController);
		addJogView.setVisible(true);
	}
	
	public void tryCarregarJogoSalvo()
	{
		TabuleiroController tabController = new TabuleiroController();
		TabuleiroView tabView = new TabuleiroView(tabController);
		tabView.setVisible(true);
	}
}
