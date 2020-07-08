package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Cartas 
{
	static private Cartas singleton;
	public ArrayList<Carta> Deck;
	
	static public Cartas getInstancia()
	{
		if(singleton == null)
			singleton = new Cartas();
		return singleton;
	}
	
	public Cartas()
	{
		Deck = new ArrayList<Carta>();
		InicializaCartas();
	}
	
	public void shuffleDeck()
	{
		Collections.shuffle(Deck);
	}
	
	public void addCarta(Carta c)
	{
		Deck.add(c);
	}
	
	public Carta compraCarta()
	{
		return Deck.remove(0);
	}
	
	public Carta compraCartaByNome(String nome) // Pro treco de salvar
	{
		int index = 0;
		for (int i = 0; i < Deck.size(); i++) {
			if(Deck.get(i).getTerritorioNome().compareTo(nome) == 0)
				index = i;
		}
		return Deck.remove(index);
	}
	
	private void InicializaCartas()
	{
		// Africa.
		Deck.add(new Carta("africa do Sul", CartaForma.Triangulo));
		Deck.add(new Carta("Angola", CartaForma.Quadrado));
		Deck.add(new Carta("Argélia", CartaForma.Circulo));
		Deck.add(new Carta("Egito", CartaForma.Triangulo));
		Deck.add(new Carta("Nigéria", CartaForma.Circulo));
		Deck.add(new Carta("Somalia", CartaForma.Quadrado));
		
		// Am do Norte.
		Deck.add(new Carta("Alasca", CartaForma.Triangulo));
		Deck.add(new Carta("Calgary", CartaForma.Circulo));
		Deck.add(new Carta("Califórnia", CartaForma.Quadrado));
		Deck.add(new Carta("Groelancdia", CartaForma.Circulo));
		Deck.add(new Carta("México", CartaForma.Quadrado));
		Deck.add(new Carta("Nova York", CartaForma.Quadrado));
		Deck.add(new Carta("Québec", CartaForma.Circulo));
		Deck.add(new Carta("Texas", CartaForma.Triangulo));
		Deck.add(new Carta("Vancouver", CartaForma.Triangulo));
		
		// Asia.
		Deck.add(new Carta("Arábia Saudita", CartaForma.Circulo));
		Deck.add(new Carta("Bangladesh", CartaForma.Circulo));
		Deck.add(new Carta("Cazaquistão", CartaForma.Circulo));
		Deck.add(new Carta("China", CartaForma.Quadrado));
		Deck.add(new Carta("Coréia do Norte", CartaForma.Quadrado));
		Deck.add(new Carta("Coreia do Sul", CartaForma.Triangulo));
		Deck.add(new Carta("Estônia", CartaForma.Circulo));
		Deck.add(new Carta("Índia", CartaForma.Triangulo));
		Deck.add(new Carta("Irã", CartaForma.Quadrado));
		Deck.add(new Carta("Iraque", CartaForma.Triangulo));
		Deck.add(new Carta("Japão", CartaForma.Circulo));
		Deck.add(new Carta("Jordânia", CartaForma.Quadrado));
		Deck.add(new Carta("Letônia", CartaForma.Quadrado));
		Deck.add(new Carta("Mongólia", CartaForma.Triangulo));
		Deck.add(new Carta("Paquistão", CartaForma.Circulo));
		Deck.add(new Carta("Russia", CartaForma.Triangulo));
		Deck.add(new Carta("Sibéria", CartaForma.Quadrado));
		Deck.add(new Carta("Siria", CartaForma.Quadrado));
		Deck.add(new Carta("Tailândia", CartaForma.Triangulo));
		Deck.add(new Carta("Turquia", CartaForma.Triangulo));
		
		// Am do sul.
		Deck.add(new Carta("Argentina", CartaForma.Quadrado));
		Deck.add(new Carta("Brasil", CartaForma.Circulo));
		Deck.add(new Carta("Peru", CartaForma.Triangulo));
		Deck.add(new Carta("Venezuaela", CartaForma.Triangulo));
		
		// Europa.
		Deck.add(new Carta("Espanha", CartaForma.Circulo));
		Deck.add(new Carta("França", CartaForma.Triangulo));
		Deck.add(new Carta("Itália", CartaForma.Quadrado));
		Deck.add(new Carta("Polônia", CartaForma.Triangulo));
		Deck.add(new Carta("Reino Unido", CartaForma.Circulo));
		Deck.add(new Carta("Romênia", CartaForma.Triangulo));
		Deck.add(new Carta("Suécia", CartaForma.Quadrado));
		Deck.add(new Carta("Ucrânia", CartaForma.Circulo));
		
		// Oceania.
		Deck.add(new Carta("Austrália", CartaForma.Triangulo));
		Deck.add(new Carta("Indonésia", CartaForma.Triangulo));
		Deck.add(new Carta("Nova Zelândia", CartaForma.Quadrado));
		Deck.add(new Carta("Perth", CartaForma.Circulo));
		
		// Misc.
		Deck.add(new Carta("Coringa", CartaForma.Coringa));
		Deck.add(new Carta("Coringa", CartaForma.Coringa));

	}
}
