package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
enum Cor{
	VERDE, PRETO, AZUL, VERMELHO, AMARELO,BRANCO;
}
class Jogador {
	private String nome;
	private Cor cor;
	String objetivo;
	private ArrayList<Carta> cartas;
	static private ArrayList<Jogador> jogadores;
	
	public Jogador() {
		jogadores = new ArrayList<Jogador>();
	}
	public Jogador(String nomeRecebido, Cor corRecebida, String objetivoRecebido) {
		if(nomeRecebido.equals(""))
			throw new IllegalArgumentException("Nome não pode ser vazio");
		if(jaSelecionaramCor(corRecebida))
			throw new IllegalArgumentException("Cor já foi selecionada");
		nome = nomeRecebido;
		cor = corRecebida;
		objetivo = objetivoRecebido;
		cartas = new ArrayList<Carta>();
		jogadores.add(this);
	}
	
	//Antes de começar randomizar o os jogadores para determinar ordem
	public void randomizarJogadores() {
		Collections.shuffle(jogadores);
	}
	
	private boolean jaSelecionaramCor(Cor cor) {
		for(int i=0;i<jogadores.size();i++) {
			if(jogadores.get(i).cor.compareTo(cor) == 0)
				return true;
		}
		return false;
	}
	
	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}
	public ArrayList<Carta> getCartas() {
		return cartas;
	}

}
