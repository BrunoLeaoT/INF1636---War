package Controller;

import Model.Geral;

public class DistribuicaoExercito {
	Geral geral = Geral.getGeral();

	public int getTropasASeremAdicionadasNaRodada(String jogador) {
		return geral.getTropasParaAdicionarRodada(jogador);
	}
	
	public int trocaDeCartas(String jogador) {
		return Geral.getGeral().getTropasTrocaDeCartas(jogador);
	}
	
	public void adicionandoTropa(String territorio) {
		try {
			geral.addTropa(territorio);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
