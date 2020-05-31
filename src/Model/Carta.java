package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

enum Forma{
	QUADRADO, CIRCULO, TRIANGULO;
}

class Carta {
	private int numeroDeTrocas = 4;
	private String nomeTerritorio;
	private Forma forma;
	
	// Deck de cartas.
	static private ArrayList<Carta> Deck; 
	
	public Carta() {
		// Inicializa deck.
		Deck = new ArrayList<Carta>();
		Deck.add(new Carta("África do Sul", Forma.TRIANGULO));
		Deck.add(new Carta("Alaska", Forma.TRIANGULO));
		Deck.add(new Carta("Alemanha", Forma.CIRCULO));
		Deck.add(new Carta("Aral", Forma.TRIANGULO));
		Deck.add(new Carta("Argélia", Forma.CIRCULO));
		Deck.add(new Carta("Argentina", Forma.QUADRADO));
		Deck.add(new Carta("Bolívia", Forma.TRIANGULO));
		Deck.add(new Carta("Egito", Forma.TRIANGULO));
	}
	public Carta(String nome,Forma forma) {
		nomeTerritorio = nome;
		this.forma = forma;

	}
	
	static public void ShuffleDeck()
	{
		Collections.shuffle(Deck);
	}
	
	
	public void distribuirCartas(ArrayList<Jogador> jogadores) {
		if(jogadores.size() <= 0)
			throw new IllegalArgumentException("Quantidade de jogadores deve ser maior que zero");
		for (int i = 0; i < Deck.size(); i++) {
			for(int j = 0; j < jogadores.size();j++) {
				if(Deck.size() == i)
					break;
				jogadores.get(j).getCartas().add(Deck.get(i));
				if((jogadores.size() -1) != j)
					i++;
			}
		}
	}
	
	public int podeTrocarCarta(ArrayList<Carta> cartas) {
		int numQuadrado = 0, numCirculo = 0, numTriangulo = 0;
		if(cartas.size() < 3)
			throw new IllegalArgumentException("Quantidade de cartas deve ser maior ou igual a 3");
		
		for (int i = 0; i < cartas.size(); i++) {
			if(cartas.get(i).forma.compareTo(Forma.QUADRADO) == 0)
				numQuadrado++;
			
			else if(cartas.get(i).forma.compareTo(Forma.TRIANGULO) == 0)
				numTriangulo++;
			
			else if(cartas.get(i).forma.compareTo(Forma.CIRCULO) == 0)
				numCirculo++;
			else
				throw new IllegalArgumentException("Esta forma não existe em cartas");
	
			if((numCirculo >= 3 || numQuadrado >= 3 || numTriangulo >= 3) || (numCirculo >=1 && numTriangulo >= 1 && numQuadrado >= 1)) {
				numeroDeTrocas++;
				return numeroDeTrocas;
			}
		}
		return 0;
	}
	
	public Carta getCartaConquista() {
		Random rand = new Random();
		return Deck.get(rand.nextInt(Deck.size()));
	}
	public String getTerritorio() {
		return nomeTerritorio;
	}
	
}
