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
		Deck.add(new Carta("Africa do Sul", Forma.TRIANGULO));
		Deck.add(new Carta("Angola", Forma.QUADRADO));
		Deck.add(new Carta("Argelia", Forma.CIRCULO));
		Deck.add(new Carta("Egito", Forma.TRIANGULO));
		Deck.add(new Carta("Nigeria", Forma.CIRCULO));
		Deck.add(new Carta("Somalia", Forma.QUADRADO));
		Deck.add(new Carta("Alasca", Forma.TRIANGULO));
		Deck.add(new Carta("Calgary", Forma.CIRCULO));
		Deck.add(new Carta("California", Forma.QUADRADO));
		Deck.add(new Carta("Groelandia", Forma.CIRCULO));
		Deck.add(new Carta("Mexico", Forma.QUADRADO));
		Deck.add(new Carta("Nova York", Forma.QUADRADO));
		Deck.add(new Carta("Quebec", Forma.CIRCULO));
		Deck.add(new Carta("Texas", Forma.TRIANGULO));
		Deck.add(new Carta("Africa do Sul", Forma.TRIANGULO));
		Deck.add(new Carta("Vancouver", Forma.QUADRADO));
		Deck.add(new Carta("Arabia Saudita", Forma.CIRCULO));
		Deck.add(new Carta("Bangladesh", Forma.CIRCULO));
		Deck.add(new Carta("Cazaquistao", Forma.CIRCULO));
		Deck.add(new Carta("China", Forma.QUADRADO));
		Deck.add(new Carta("Coreia do Norte", Forma.QUADRADO));
		Deck.add(new Carta("Coreia do Sul", Forma.TRIANGULO));
		Deck.add(new Carta("Estonia", Forma.CIRCULO));
		Deck.add(new Carta("India", Forma.TRIANGULO));
		Deck.add(new Carta("Ira", Forma.QUADRADO));
		Deck.add(new Carta("Iraque", Forma.TRIANGULO));
		Deck.add(new Carta("Japao", Forma.CIRCULO));
		Deck.add(new Carta("Jordania", Forma.QUADRADO));
		Deck.add(new Carta("Letonia", Forma.QUADRADO));
		Deck.add(new Carta("Mongolia", Forma.TRIANGULO));
		Deck.add(new Carta("Paquistao", Forma.CIRCULO));
		Deck.add(new Carta("Russia", Forma.TRIANGULO));
		Deck.add(new Carta("Siberia", Forma.QUADRADO));
		Deck.add(new Carta("Siria", Forma.QUADRADO));
		Deck.add(new Carta("Tailandia", Forma.TRIANGULO));
		Deck.add(new Carta("Turquia", Forma.TRIANGULO));
		Deck.add(new Carta("Argentina", Forma.QUADRADO));
		Deck.add(new Carta("Brasil", Forma.CIRCULO));
		Deck.add(new Carta("Peru", Forma.TRIANGULO));
		Deck.add(new Carta("Venezuela", Forma.TRIANGULO));
		Deck.add(new Carta("Espanha", Forma.CIRCULO));
		Deck.add(new Carta("Franca", Forma.TRIANGULO));
		Deck.add(new Carta("Italia", Forma.QUADRADO));
		Deck.add(new Carta("Polonia", Forma.TRIANGULO));
		Deck.add(new Carta("Ucrania", Forma.CIRCULO));
		Deck.add(new Carta("Suecia", Forma.QUADRADO));
		Deck.add(new Carta("Reino Unido", Forma.CIRCULO));
		Deck.add(new Carta("Romenia", Forma.TRIANGULO));
		Deck.add(new Carta("Australia", Forma.TRIANGULO));
		Deck.add(new Carta("Indonesia", Forma.TRIANGULO));
		Deck.add(new Carta("Nova Zelandia", Forma.QUADRADO));
		Deck.add(new Carta("Perth", Forma.CIRCULO));

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
