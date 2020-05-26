package Model;

import java.util.ArrayList;
import java.util.Collections;


public class Geral {
	Jogador jogadores;
	Objetivo objetivo ;
	Carta cartas;
	Territorio territorios;
	private static Geral singleton;
	private Geral() {
		jogadores = new Jogador();
		objetivo = new Objetivo();
		cartas = new Carta();
		territorios = new Territorio();
	}
	
	public static Geral getGeral() {
		if(singleton == null)
			singleton = new Geral();
		return singleton;
	}
	
	// ******** Fun��es Jogadores ********
	public void adicionarJogador(String nome,Cor cor) {
		if(jogadores.getJogadores().size() == 6)
			throw new IllegalArgumentException("quantidade de jogadores excedida");
		new Jogador(nome, cor, objetivo.randomizarObjetivo());
	}
	

	// ******** Fun��es Exercito e territorios ********
	public void distribuirCartas() {
		cartas.distribuirCartas(jogadores.getJogadores());
		//ChamarEmTerritorioParaAdd1tropa
	}
	
	
}
