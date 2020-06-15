package Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


import java.util.Set;

import Model.Geral;

public class Jogada {
	ArrayList<String> jogadores;
	int jogadorDaVez;
	public static Jogada jogada = null;
	private Jogada() {
		// TODO Auto-generated constructor stub
		jogadores = new ArrayList();
		jogadorDaVez = -1;
	}
	
	public static Jogada getJogada() {
		if(jogada == null)
			jogada = new Jogada();
		return jogada;
	}
	
	public void setJogadores(Map<String, String>  jogadores) {
		Object[] aux = jogadores.entrySet().toArray();
		for (int i = 0; i < aux.length; i++) {
			String nomeJogador = aux[i].toString().split("=")[0];
			this.jogadores.add(nomeJogador);
		}
		System.out.println(this.jogadores);
	}
	public String proximaRodada() {
		if((jogadorDaVez +1) == jogadores.size()) {
			jogadorDaVez = 0;
			return jogadores.get(0);
		}
		jogadorDaVez++;
		return jogadores.get(jogadorDaVez);
	}
	
	
	public boolean iniciarJogo() {
		try {
			Geral.getGeral().iniciarJogo();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
