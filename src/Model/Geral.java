package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Controller.DadosPraView;

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
	
	// ******** Funções Jogadores ********
	public void adicionarJogador(String nome,Cor cor) {
		if(jogadores.getJogadores().size() == 6)
			throw new IllegalArgumentException("quantidade de jogadores excedida");
		new Jogador(nome, cor, null);
	}
	
	public void iniciarJogo() {
		jogadores.randomizarJogadores();
		distribuirExercitosIniciais();
	}

	// ******** Funções Exercito e territorios ********
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
	
	// ******** Funções para ataque******** 
	
	public boolean podeAtacar(String atacante, String territorio, String territorioAtacante) {
		int index =territorios.getTerritorioPorNome(territorio);
		Territorio defensor = territorios.territorios.get(index);
		return defensor.temTerrAdjacente(atacante, territorioAtacante);
	}
	
	public boolean ataque(int nmDadosAtaque,int nmDadosDefesa, String territorioAtacante, String territorioDefesa, int valorDadoAtk) {
		/* Se voltar true o ataque conquistou o territorio, precisa voltar para view e perguntar número de tropas
		   a se movimentar. */
		verificaçõesAtaque(territorioAtacante, territorioDefesa,nmDadosAtaque, nmDadosDefesa);
		
		int dadosGanhos = 0;
		int[] ataque = dado.JogaDados(nmDadosAtaque);
		for (int i = 0; i < ataque.length; i++) {
			ataque[i] = valorDadoAtk;
		}
		int[] defesa = dado.JogaDados(nmDadosDefesa);
		ataque = dado.organizarDados(ataque);
		defesa = dado.organizarDados(defesa);
		for (int i = 0; i < defesa.length; i++) {
			if(ataque[i] > defesa[i] )
				dadosGanhos++;
		}
		//verDados(ataque, defesa);
		territorios.removeTropas(territorioAtacante, (nmDadosDefesa - dadosGanhos));
		if(territorios.removeTropas(territorioDefesa,dadosGanhos)) {
			conquista(territorioDefesa, territorioAtacante);
			notificar();
			return true;	
		}
		return false;
	}
	
	public void verificaçõesAtaque(String territorioAtacante, String territorioDefesa,int nmDadosAtaque,int nmDadosDefesa) {		
		if(Territorio.GetTropas(territorioAtacante) <= 1) 
			throw new IllegalArgumentException("Atacante não pode atacar com 1 tropa só");
		if(Territorio.GetTropas(territorioDefesa) != nmDadosDefesa)
			if(Territorio.GetTropas(territorioDefesa) < 4)
				throw new IllegalArgumentException("Defensor está com número de dados relacionado com numero de tropas incorreto");
		if((Territorio.GetTropas(territorioAtacante) -1) != nmDadosAtaque)
			if(Territorio.GetTropas(territorioAtacante) < 5)
				throw new IllegalArgumentException("Atacante está com número de dados relacionado com numero de tropas incorreto");
		if(territorioAtacante == null || territorioDefesa == null)
			throw new IllegalArgumentException("Territorio no combate não existente");
	}
	
	public boolean conquista(String nomeTerritorio, String nomeTerritorioAtk) {
		Jogador jogador = null;
		int conquistado = Territorio.getTerritorioPorNome(nomeTerritorio);
		int conquistador = Territorio.getTerritorioPorNome(nomeTerritorioAtk);
		jogador = jogadores.getJogadorPorNome(Territorio.territorios.get(conquistador).GetDono().nome);
		
		if(jogador == null) {
			throw new IllegalArgumentException("Jogador não existente");
		}
		Territorio.SetDono(nomeTerritorio,jogador, (Territorio.GetTropas(nomeTerritorioAtk) -1));
		Territorio.SetTropas(nomeTerritorio, 1);
		if(Territorio.territorios.get(conquistado).GetDono().equals(jogador)) {
			jogador.getCartas().add(cartas.getCartaConquista());
			notificar();
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
	public void notificar() {
		DadosPraView.getDados().notificar();
	}
	public int getTropasParaAdicionarRodada(String nomeJogador) {
		int soma = 0;
		for (int i = 0; i < Territorio.territorios.size(); i++) {
			if(Territorio.territorios.get(i).GetDono().nome.compareTo(nomeJogador) == 0)
				soma++;
		}
		soma += Continente.temAlgumContinente(nomeJogador);
		return soma/2;
	}
	public Map<String, String> getJogadoresToVIew(){
		Map<String,String> aux = new HashMap<String, String>();
		for (int i = 0; i < Territorio.territorios.size(); i++) {
			aux.put(Territorio.territorios.get(i).Nome, Territorio.territorios.get(i).GetDono().nome);
		}
		return aux;
	}
	
	public void addTropa(String territorio) {
		try {
			int tropasAntes = Territorio.GetTropas(territorio);
			Territorio.SetTropas(territorio, tropasAntes+1);
			notificar();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

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
			String nome = Territorio.territorios.get(i).Nome;
			aux.put(nome,  Integer.toString(Territorio.GetTropas(nome)));
		}
		return aux;
	}
	
	public ArrayList<String> getCartasJogador(String jogador) {
		ArrayList<Carta> cartas  = jogadores.getJogadorPorNome(jogador).getCartas();
		ArrayList<String> retCartas = new ArrayList<String>();
		for (int i = 0; i < cartas.size(); i++) {
			retCartas.add(cartas.get(i).getTerritorio() + "-" + cartas.get(i).getForma());
		}
		return retCartas;
	}
	
	public int getTropasTrocaDeCartas(String jogador) {
		Jogador aux =jogadores.getJogadorPorNome(jogador);
		int tropas = cartas.podeTrocarCarta(aux.getCartas());
		if(tropas > 0) // Isso deveria ser feito melhor, tipo remover só as cartas da troca, mas tempo
			aux.getCartas().clear();
		return tropas;
		
	}
}
