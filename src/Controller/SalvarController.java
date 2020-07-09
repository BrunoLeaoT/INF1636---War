package Controller;

import Model.Partida;

public class SalvarController {
	private Partida partida;
	
	public SalvarController() {
		partida = Partida.getInstancia();
	}
	
	public boolean salvarJogo() {
		return partida.salvarJogo();
	}
	
	public boolean carregarJogo() {
		return partida.carregarJogoSalvo();
	}
}
