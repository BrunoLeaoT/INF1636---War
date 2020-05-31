package Model;

import java.util.ArrayList;
import java.util.Collections;


public class Geral {
	Jogador jogadores;
	Objetivo objetivo ;
	Carta cartas;
	Territorio territorios;
	private static Geral singleton;
	Dado dado = new Dado();
	private Geral() {
		jogadores = new Jogador();
		//objetivo = Objetivo.CreateNew();
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
		new Jogador(nome, cor, null);
	}
	

	// ******** Fun��es Exercito e territorios ********
	public void distribuirExercitosIniciais() {
		ArrayList<Jogador> jogadoresList = jogadores.getJogadores();
		cartas.distribuirCartas(jogadoresList);
		for(int i =0 ; i < jogadoresList.size(); i++) {
			ArrayList<Carta> cartasJogador = jogadoresList.get(i).getCartas();
			for(int index = 0; index < cartasJogador.size() ; index++) {
				territorios.adicionarTropaPorNome(cartasJogador.get(index).getTerritorio(), 1, jogadoresList.get(i));
			}
		}

	}
	
	// Se voltar true o ataque conquistou o territorio, precisa voltar para view e perguntar n�mero de tropas a se movimentar
	// Ai o pr�prio controller pode dar esse set direto
	public boolean ataque(int nmDadosAtaque,int nmDadosDefesa, Territorio territorioAtacante, Territorio territorioDefesa) {
		if(territorioAtacante.GetTropas() <= 1) 
			throw new IllegalArgumentException("Atacante n�o pode atacar com 1 tropa s�");
		if(territorioDefesa.GetTropas() != nmDadosDefesa)
			if(territorioDefesa.GetTropas() < 4)
				throw new IllegalArgumentException("Defensor est� com n�mero de dados relacionado com numero de tropas incorreto");
		if((territorioAtacante.GetTropas() -1) != nmDadosAtaque)
			if(territorioAtacante.GetTropas() < 5)
				throw new IllegalArgumentException("Atacante est� com n�mero de dados relacionado com numero de tropas incorreto");
		
		int dadosGanhos = 0;
		int[] atacante = dado.JogaDados(nmDadosAtaque);
		int[] defesa = dado.JogaDados(nmDadosDefesa);
		atacante = dado.organizarDados(atacante);
		defesa = dado.organizarDados(defesa);
		for (int i = 0; i < defesa.length; i++) {
			if(atacante[i] > defesa[i] )
				dadosGanhos++;
		}
		//verDados(atacante, defesa);
		territorioAtacante.removeTropas(nmDadosDefesa - dadosGanhos);
		if(territorioDefesa.removeTropas(dadosGanhos))
			return true;
		return false;
	}
	

	
	public void verDados(int[] atacante,int[] defesa) {
		for (int i = 0; i < defesa.length; i++) {
			System.out.println("Dado defesa" + i + ": " + defesa[i]);
		}
		for (int i = 0; i < atacante.length; i++) {
			System.out.println("Dado atacante" + i + ": " + atacante[i]);
		}
	}
	
}
