package Controller;

import Model.Geral;

public class Ataque {
	public Geral geral;
	public Ataque() {
		geral = Geral.getGeral();
	}
	public boolean ataqueAoOponente(String territorioAtacante,String territorioDefesa) {
		if(geral.ataque(3, 1, territorioAtacante, territorioDefesa)) {
			return true;
		}
		return false;
	}
	public boolean conquista(String nomeJogador, String nomeTerritorio, int qtdTropas) {
		return geral.conquista(nomeJogador, nomeTerritorio, qtdTropas);
	}
}
