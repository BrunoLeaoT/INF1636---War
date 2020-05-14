package Model;

import java.util.ArrayList;
import java.util.Collections;

enum Forma{
	QUADRADO, CIRCULO, TRIANGULO;
}

class CartaObject {
	
	private String nomeTerritorio;
	private Forma forma;
	
	// Deck de cartas.
	static private ArrayList<CartaObject> Deck; 
	
	public CartaObject(String nome,Forma forma) {
		nomeTerritorio = nome;
		this.forma = forma;
		
		// Inicializa deck.
		Deck = new ArrayList<CartaObject>();
		Deck.add(new CartaObject("África do Sul", Forma.TRIANGULO));
		Deck.add(new CartaObject("Alaska", Forma.TRIANGULO));
		Deck.add(new CartaObject("Alemanha", Forma.CIRCULO));
		Deck.add(new CartaObject("Aral", Forma.TRIANGULO));
		Deck.add(new CartaObject("Argélia", Forma.CIRCULO));
		Deck.add(new CartaObject("Argentina", Forma.QUADRADO));
		Deck.add(new CartaObject("Austrália", Forma.TRIANGULO));
		Deck.add(new CartaObject("Bolívia", Forma.TRIANGULO));
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
	
	public boolean podeTrocarCarta(ArrayList<CartaObject> cartas) {
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
