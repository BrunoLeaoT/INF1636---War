package Model;

import java.util.ArrayList;

enum Cor{
	VERDE, PRETO, AZUL, VERMELHO, AMARELO,BRANCO;
}
public class Geral {
	ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	Objetivo objetivo = new Objetivo();
	Cartas cartas = new Cartas();
	public void adicionarJogador(String nome,Cor cor) {
		Jogador auxJogador = new Jogador(nome, cor, objetivo.randomizarObjetivo());
		jogadores.add(auxJogador);
	}

	public void distribuirExercitos() {
		cartas.distribuirCartas(jogadores);
		for (int i = 0; i < jogadores.size(); i++) {
			jogadores.get(i).adicionarExercitosIniciais();
		}
	}
	

}
