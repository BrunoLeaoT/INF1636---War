package Controller;

import Model.Geral;

public class Ataque {
	public Geral geral;
	public Ataque() {
		geral = Geral.getGeral();
	}
	public boolean ataqueAoOponente(String atacante,String territorioAtacante,String territorioDefesa,int valor) { 
		if(geral.podeAtacar(atacante, territorioDefesa, territorioAtacante)) {
			if(geral.ataque(3, 1, territorioAtacante, territorioDefesa,valor)) {
				return true;
			}
		}
		else
			throw new IllegalArgumentException("Voce não tem territorio com fronteira");
		return false;
	}
}
