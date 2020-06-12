package Controller;

import Model.Geral;

public class DistribuicaoExercito {
	Geral geral = Geral.getGeral();
	
	public boolean iniciarJogo() {
		try {
			geral.iniciarJogo();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	public int getTropasASeremAdicionadasNaRodada(String jogador) {
		return geral.getTropasParaAdicionarRodada(jogador);
	}
}
