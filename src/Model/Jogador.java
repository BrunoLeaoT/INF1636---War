package Model;

import java.util.ArrayList;
import java.util.Scanner;

class Jogador {
	private String nome;
	private Cor cor;
	String objetivo;
	private int numeroDeTrocas = 4;//Isso aqui fica numa classe principal, pelo visto � do jogo e n�o cada jogador
	private ArrayList<Carta> cartas;
	//territorios
	
	public Jogador(String nomeRecebido, Cor corRecebida, String objetivoRecebido) {
		if(nomeRecebido.equals(""))
			throw new IllegalArgumentException("nome n�o pode ser vazio");
		nome = nomeRecebido;
		cor = corRecebida;
		objetivo = objetivoRecebido;
		cartas = new ArrayList<Carta>();
	}
	
	private void adicionarExercitos(int numeroDeTropas, String territorio) {
		// Adicionar no territorio
		
	}
	
	public void trocarDeCartas(String territorio, ArrayList<Carta> cartasTrocadas) {
		if(cartasTrocadas.size() < 3)
			throw new IllegalArgumentException("Quantidade de cartas deve ser maior ou igual a 3");
		
		if(getCartas().get(0).podeTrocarCarta(cartasTrocadas)) {
			adicionarExercitos(numeroDeTrocas, territorio); // precisa trocar para o territorio escolhido
			numeroDeTrocas += 2;
			removerCartas(cartasTrocadas);
		}
		else {
			// Avisa algum coisa aqui?
			System.out.println("Cartas n�o podem efetuar troca");
		}
	}
	
	public void bonusExercitoContinente() {
		
	}

	public void adicionarExercitosIniciais() {
		for (int i = 0; i < cartas.size(); i++) {
			adicionarExercitos(1, cartas.get(i).getTerritorio());
		}
		cartas.clear();
	}
	
	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	public void removerCartas(ArrayList<Carta> cartasASeremRemovidas) {
		for (int i = 0; i < cartas.size(); i++) {
			for (int j = 0; j < cartasASeremRemovidas.size(); j++) {
				if(cartas.get(i).getTerritorio().equals(cartasASeremRemovidas.get(j).getTerritorio()))
					cartas.remove(i);	
			}
		}
	}
}
