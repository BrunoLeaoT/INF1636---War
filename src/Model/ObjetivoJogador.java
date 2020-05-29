package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class ObjetivoJogador implements IObjetivo{
	Cor jogadorAlvo;
	static ObjetivoJogador obj = new ObjetivoJogador(null);
	
	public ObjetivoJogador(Cor corDoJogadorPraMatar) {
		// TODO Auto-generated constructor stub
		jogadorAlvo = corDoJogadorPraMatar;
	}
	
	@Override
	public IObjetivo randomizarObjetivo() {
		// TODO Auto-generated method stub
		ArrayList<Jogador> jogadores = Jogador.jogadores;
		Random rand = new Random();
		Jogador jogador = jogadores.get(rand.nextInt(jogadores.size()));
		
		return new ObjetivoJogador(jogador.getCor());
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
