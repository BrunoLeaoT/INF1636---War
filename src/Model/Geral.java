package Model;

import java.util.ArrayList;
import java.util.Collections;

enum Cor{
	VERDE, PRETO, AZUL, VERMELHO, AMARELO,BRANCO;
}
public class Geral {
	ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	Objetivo objetivo = new Objetivo();
	Cartas cartas = new Cartas();
	int jogadorDaVez = 0;
	public void adicionarJogador(String nome,Cor cor) {
		if(jogadores.size() == 6)
			throw new IllegalArgumentException("quantidade de jogadores excedida");
		Jogador auxJogador = new Jogador(nome, cor, objetivo.randomizarObjetivo());
		jogadores.add(auxJogador);
	}

	public void distribuirExercitos() {
		cartas.distribuirCartas(jogadores);
		for (int i = 0; i < jogadores.size(); i++) {
			jogadores.get(i).adicionarExercitosIniciais();
		}
	}
	
	//Antes de começar randomizar o os jogadores para determinar ordem
	public void randomizarJogadores() {
		Collections.shuffle(jogadores);
	}

}
