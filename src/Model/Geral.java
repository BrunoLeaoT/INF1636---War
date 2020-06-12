package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



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
	public void iniciarJogo() {
		distribuirExercitosIniciais();
		jogadores.randomizarJogadores();
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
			jogadoresList.get(i).getCartas().clear();
		}
		
	}
	
	// ******** Fun��es para ataque******** 
	

	public boolean ataque(int nmDadosAtaque,int nmDadosDefesa, String territorioAtacante, String territorioDefesa) {
		/* Se voltar true o ataque conquistou o territorio, precisa voltar para view e perguntar n�mero de tropas
		   a se movimentar. */
		Territorio atacante = territorios.getTerritorioPorNome(territorioAtacante);
		Territorio defensor = territorios.getTerritorioPorNome(territorioDefesa);
		verifica��esAtaque(atacante, defensor,nmDadosAtaque, nmDadosDefesa);
		
		int dadosGanhos = 0;
		int[] ataque = dado.JogaDados(nmDadosAtaque);
		int[] defesa = dado.JogaDados(nmDadosDefesa);
		ataque = dado.organizarDados(ataque);
		defesa = dado.organizarDados(defesa);
		for (int i = 0; i < defesa.length; i++) {
			if(ataque[i] > defesa[i] )
				dadosGanhos++;
		}
		//verDados(ataque, defesa);
		atacante.removeTropas(nmDadosDefesa - dadosGanhos);
		if(defensor.removeTropas(dadosGanhos)) {
			conquista(territorioDefesa, territorioAtacante);
			return true;	
		}
		return false;
	}
	
	public void verifica��esAtaque(Territorio territorioAtacante, Territorio territorioDefesa,int nmDadosAtaque,int nmDadosDefesa) {		
		if(territorioAtacante.GetTropas() <= 1) 
			throw new IllegalArgumentException("Atacante n�o pode atacar com 1 tropa s�");
		if(territorioDefesa.GetTropas() != nmDadosDefesa)
			if(territorioDefesa.GetTropas() < 4)
				throw new IllegalArgumentException("Defensor est� com n�mero de dados relacionado com numero de tropas incorreto");
		if((territorioAtacante.GetTropas() -1) != nmDadosAtaque)
			if(territorioAtacante.GetTropas() < 5)
				throw new IllegalArgumentException("Atacante est� com n�mero de dados relacionado com numero de tropas incorreto");
		if(territorioAtacante == null || territorioDefesa == null)
			throw new IllegalArgumentException("Territorio no combate n�o existente");
	}
	
	public boolean conquista(String nomeTerritorio, String nomeTerritorioAtk) {
		Jogador jogador = null;
		Territorio conquistado = territorios.getTerritorioPorNome(nomeTerritorio);
		Territorio conquistador = territorios.getTerritorioPorNome(nomeTerritorioAtk);
		jogador = jogadores.getJogadorPorNome(conquistador.GetDono().nome);
		
		if(jogador == null) {
			throw new IllegalArgumentException("Jogador n�o existente");
		}
		conquistado.SetDono(jogador, (conquistador.GetTropas() -1));
		if(conquistado.GetDono().equals(jogador)) {
			jogador.getCartas().add(cartas.getCartaConquista());
			return true;
		}
		return false;
	}
	
	public void verDados(int[] atacante,int[] defesa) {
		for (int i = 0; i < defesa.length; i++) {
			System.out.println("Dado " +i+" defesa: " + defesa[i]);
		}
		for (int i = 0; i < atacante.length; i++) {
			System.out.println("Dado" +i+" atacante: " + atacante[i]);
		}
	}
	
	/****** Valores pra view *******/
	public int getTropasParaAdicionarRodada(String nomeJogador) {
		int soma = 0;
		for (int i = 0; i < Territorio.territorios.size(); i++) {
			if(Territorio.territorios.get(i).GetDono().nome.compareTo(nomeJogador) == 0)
				soma++;
		}
		return soma/2;
	}
	public Map<String, String> getJogadoresToVIew(){
		Map<String,String> aux = new HashMap<String, String>();
		for (int i = 0; i < Territorio.territorios.size(); i++) {
			aux.put(Territorio.territorios.get(i).Nome, Territorio.territorios.get(i).GetDono().nome);
		}
		return aux;
	}
	
	public Map getMapcoords() {
		Map<String, ArrayList<Point>> aux = new HashMap<String, ArrayList<Point>>();
		for (int i = 0; i < Territorio.territorios.size(); i++) {
			ArrayList<Point> points = new ArrayList<Point>();
			points.add(Territorio.territorios.get(i).Ponto1);
			points.add(Territorio.territorios.get(i).Ponto2);
			points.add(Territorio.territorios.get(i).Ponto3);
			points.add(Territorio.territorios.get(i).Ponto4);
			aux.put(Territorio.territorios.get(i).Nome, points);
		}
		return aux;
	}
	
	public Map getTropasPorTerritorios() {
		Map<String, String> aux = new HashMap<String, String>();
		for (int i = 0; i < Territorio.territorios.size(); i++) {
			aux.put(Territorio.territorios.get(i).Nome,  Integer.toString(Territorio.territorios.get(i).GetTropas()));
		}
		return aux;
	}
}
