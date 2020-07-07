package Controller;

import Model.Partida;

public class Salvamento {
	private Partida partida;
	
	public Salvamento() {
		partida = Partida.getInstancia();
	}
	
	public boolean salvarJogo() {
		return partida.salvarJogo();
	}
	
	public boolean carregarJogo() {
		return partida.carregarJogoSalvo();
	}
}
