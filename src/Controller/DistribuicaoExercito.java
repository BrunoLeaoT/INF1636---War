package Controller;

import Model.Geral;

public class DistribuicaoExercito {
	Geral geral = Geral.getGeral();
	
	public int getTropasASeremAdicionadasNaRodada(String jogador) {
		return geral.getTropasParaAdicionarRodada(jogador);
	}
}
