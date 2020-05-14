package Model;

import java.util.ArrayList;
import java.util.Scanner;

class Jogador {
	private String nome;
	private Cor cor;
	String objetivo;
	private int numeroDeTrocas = 4;//Isso aqui fica numa classe principal, pelo visto é do jogo e não cada jogador
	private ArrayList<CartaObject> cartas;
	//territorios
	
	public Jogador(String nomeRecebido, Cor corRecebida, String objetivoRecebido) {
		nome = nomeRecebido;
		cor = corRecebida;
		objetivo = objetivoRecebido;
		cartas = new ArrayList<CartaObject>();
	}
	
	private void adicionarExercitos(int numeroDeTropas, String territorio) {
		// Adicionar no territorio
	}
	
	public void trocarDeCartas(String territorio, ArrayList<CartaObject> cartasTrocadas) {
		if(getCartas().get(0).podeTrocarCarta(cartasTrocadas)) {
			adicionarExercitos(numeroDeTrocas, territorio); // precisa trocar para o territorio escolhido
			numeroDeTrocas += 2;
			removerCartas(cartasTrocadas);
		}
		else {
			// Avisa algum coisa aqui?
			System.out.println("Cartas não podem efetuar troca");
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
	
	public ArrayList<CartaObject> getCartas() {
		return cartas;
	}

	public void removerCartas(ArrayList<CartaObject> cartasASeremRemovidas) {
		for (int i = 0; i < cartas.size(); i++) {
			for (int j = 0; j < cartasASeremRemovidas.size(); j++) {
				if(cartas.get(i).getTerritorio().equals(cartasASeremRemovidas.get(j).getTerritorio()))
					cartas.remove(i);	
			}
		}
	}
}
