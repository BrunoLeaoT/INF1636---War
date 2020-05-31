package Model;

import java.util.ArrayList;
import java.util.Random;

public class ObjetivoJogador extends Objetivo{
	Cor jogadorAlvo;
	static ObjetivoJogador obj = new ObjetivoJogador(null);
	
	public ObjetivoJogador(Cor corDoJogadorPraMatar) {
		jogadorAlvo = corDoJogadorPraMatar;
	}
	
	public ObjetivoJogador() {
		ArrayList<Jogador> jogadores = Jogador.jogadores;
		Random rand = new Random();
		System.out.println(rand.nextInt( Jogador.jogadores.size()));
		Jogador jogador = jogadores.get(rand.nextInt(Jogador.jogadores.size()));
		
		jogadorAlvo = jogador.getCor();
	}

	@Override
	public boolean verificarVitoria(Jogador jogador) {
		ArrayList<Territorio> territorio = Territorio.territorios;
		for (int i = 0; i < territorio.size(); i++) {
			if(territorio.get(i).GetDono().getCor().compareTo(jogadorAlvo) == 0)
				return false;
		}
		return true;
	}

}
