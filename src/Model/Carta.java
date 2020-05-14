package Model;

import java.util.ArrayList;
import java.util.Collections;

enum Forma{
	QUADRADO, CIRCULO, TRIANGULO;
}

class Carta {
	
	private String nomeTerritorio;
	private Forma forma;
	
	// Deck de cartas.
	static private ArrayList<Carta> Deck; 
	
	public Carta(String nome,Forma forma) {
		nomeTerritorio = nome;
		this.forma = forma;
		
		// Inicializa deck.
		Deck = new ArrayList<Carta>();
		Deck.add(new Carta("África do Sul", Forma.TRIANGULO));
		Deck.add(new Carta("Alaska", Forma.TRIANGULO));
		Deck.add(new Carta("Alemanha", Forma.CIRCULO));
		Deck.add(new Carta("Aral", Forma.TRIANGULO));
		Deck.add(new Carta("Argélia", Forma.CIRCULO));
		Deck.add(new Carta("Argentina", Forma.QUADRADO));
		Deck.add(new Carta("Austrália", Forma.TRIANGULO));
		Deck.add(new Carta("Bolívia", Forma.TRIANGULO));
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
				i++;
			}
		}
	}
	
	public boolean podeTrocarCarta(ArrayList<Carta> cartas) {
		int numQuadrado = 0, numCirculo = 0, numTriangulo = 0;
		
		for (int i = 0; i < cartas.size(); i++) {
			switch (cartas.get(i).forma) {
			case QUADRADO: {
				numQuadrado++;
			}
			case TRIANGULO: {
				numTriangulo++;
			}
			case CIRCULO: {
				numCirculo++;
			}
			default:
				System.out.println("Forma não existe");
			}
			
			if((numCirculo == 3 || numQuadrado == 3 || numTriangulo == 3) || (numCirculo >1 && numTriangulo > 1 && numQuadrado > 1))
				return true;
		}
		return false;
	}
	
	public String getTerritorio() {
		return nomeTerritorio;
	}
}
