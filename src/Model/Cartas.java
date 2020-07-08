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
		Deck.add(new Carta("Arg�lia", CartaForma.Circulo));
		Deck.add(new Carta("Egito", CartaForma.Triangulo));
		Deck.add(new Carta("Nig�ria", CartaForma.Circulo));
		Deck.add(new Carta("Somalia", CartaForma.Quadrado));
		
		// Am do Norte.
		Deck.add(new Carta("Alasca", CartaForma.Triangulo));
		Deck.add(new Carta("Calgary", CartaForma.Circulo));
		Deck.add(new Carta("Calif�rnia", CartaForma.Quadrado));
		Deck.add(new Carta("Groelancdia", CartaForma.Circulo));
		Deck.add(new Carta("M�xico", CartaForma.Quadrado));
		Deck.add(new Carta("Nova York", CartaForma.Quadrado));
		Deck.add(new Carta("Qu�bec", CartaForma.Circulo));
		Deck.add(new Carta("Texas", CartaForma.Triangulo));
		Deck.add(new Carta("Vancouver", CartaForma.Triangulo));
		
		// Asia.
		Deck.add(new Carta("Ar�bia Saudita", CartaForma.Circulo));
		Deck.add(new Carta("Bangladesh", CartaForma.Circulo));
		Deck.add(new Carta("Cazaquist�o", CartaForma.Circulo));
		Deck.add(new Carta("China", CartaForma.Quadrado));
		Deck.add(new Carta("Cor�ia do Norte", CartaForma.Quadrado));
		Deck.add(new Carta("Coreia do Sul", CartaForma.Triangulo));
		Deck.add(new Carta("Est�nia", CartaForma.Circulo));
		Deck.add(new Carta("�ndia", CartaForma.Triangulo));
		Deck.add(new Carta("Ir�", CartaForma.Quadrado));
		Deck.add(new Carta("Iraque", CartaForma.Triangulo));
		Deck.add(new Carta("Jap�o", CartaForma.Circulo));
		Deck.add(new Carta("Jord�nia", CartaForma.Quadrado));
		Deck.add(new Carta("Let�nia", CartaForma.Quadrado));
		Deck.add(new Carta("Mong�lia", CartaForma.Triangulo));
		Deck.add(new Carta("Paquist�o", CartaForma.Circulo));
		Deck.add(new Carta("Russia", CartaForma.Triangulo));
		Deck.add(new Carta("Sib�ria", CartaForma.Quadrado));
		Deck.add(new Carta("Siria", CartaForma.Quadrado));
		Deck.add(new Carta("Tail�ndia", CartaForma.Triangulo));
		Deck.add(new Carta("Turquia", CartaForma.Triangulo));
		
		// Am do sul.
		Deck.add(new Carta("Argentina", CartaForma.Quadrado));
		Deck.add(new Carta("Brasil", CartaForma.Circulo));
		Deck.add(new Carta("Peru", CartaForma.Triangulo));
		Deck.add(new Carta("Venezuaela", CartaForma.Triangulo));
		
		// Europa.
		Deck.add(new Carta("Espanha", CartaForma.Circulo));
		Deck.add(new Carta("Fran�a", CartaForma.Triangulo));
		Deck.add(new Carta("It�lia", CartaForma.Quadrado));
		Deck.add(new Carta("Pol�nia", CartaForma.Triangulo));
		Deck.add(new Carta("Reino Unido", CartaForma.Circulo));
		Deck.add(new Carta("Rom�nia", CartaForma.Triangulo));
		Deck.add(new Carta("Su�cia", CartaForma.Quadrado));
		Deck.add(new Carta("Ucr�nia", CartaForma.Circulo));
		
		// Oceania.
		Deck.add(new Carta("Austr�lia", CartaForma.Triangulo));
		Deck.add(new Carta("Indon�sia", CartaForma.Triangulo));
		Deck.add(new Carta("Nova Zel�ndia", CartaForma.Quadrado));
		Deck.add(new Carta("Perth", CartaForma.Circulo));
		
		// Misc.
		Deck.add(new Carta("Coringa", CartaForma.Coringa));
		Deck.add(new Carta("Coringa", CartaForma.Coringa));

	}
}
