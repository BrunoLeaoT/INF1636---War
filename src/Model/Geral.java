package Model;

import java.util.ArrayList;
import java.util.Collections;


public class Geral {
	Jogador jogadores = new Jogador();
	Objetivo objetivo = new Objetivo();
	Carta cartas = new Carta();
	Territorio territorios = new Territorio();

	// ******** Funções Jogadores ********
	public void adicionarJogador(String nome,Cor cor) {
		if(jogadores.getJogadores().size() == 6)
			throw new IllegalArgumentException("quantidade de jogadores excedida");
		new Jogador(nome, cor, objetivo.randomizarObjetivo());
	}
	

	// ******** Funções Exercito e territorios ********
	public void distribuirCartas() {
		cartas.distribuirCartas(jogadores.getJogadores());
		//ChamarEmTerritorioParaAdd1tropa
	}
	

}
