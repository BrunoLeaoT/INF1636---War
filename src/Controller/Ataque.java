package Controller;

import Model.Geral;
import Model.Partida;

public class Ataque {
	private Geral geral;
	private Partida partida;
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
	
	public boolean realizarAtaque(String nomeTerritorioAtacante, String nomeTerritorioDefensor) throws Exception
	{
		if(partida.podemAtacar(nomeTerritorioAtacante, nomeTerritorioDefensor))
		{
			//pergunta pra view com quantos exercitos voce quer atacar
			int qtdTropasAtaque = 0;
			if(qtdTropasAtaque > 3)
				throw new Exception("Voce nao pode atacar com mais de 3 tropas");
			
			if(partida.processaAtaque(nomeTerritorioAtacante, nomeTerritorioDefensor, qtdTropasAtaque))
			{
				// Atacante venceu
				// remaneja tropas pra dentro do novo territorio
				return true;
			}
			else
				return false;
		}
		else
			throw new Exception("Voce nao pode atacar esse territorio");
	}
}
