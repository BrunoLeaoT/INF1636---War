package Controller;

import Model.Partida;

public class Salvamento {
	private Partida partida;
	
	public Salvamento() {
		partida = Partida.getInstancia();
	}
	
	public void salvarJogo() {
		
	}
	
	public boolean carregarJogo() {
		return partida.carregarJogoSalvo();
	}
}
