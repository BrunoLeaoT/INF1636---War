package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Jogador {
		
	private String nome;
	private Cor cor;
	private Objetivo objetivo;
	private ArrayList<Carta> cartas;
	private ArrayList<Territorio> Territorios;
	
	public Jogador(String nomeRecebido, Cor corRecebida) {
		
		if(nomeRecebido.equals(""))
			throw new IllegalArgumentException("Nome n�o pode ser vazio");
		
		nome = nomeRecebido;
		cor = corRecebida;
		cartas = new ArrayList<Carta>();
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}
	
	public void setObjetivo(Objetivo o)
	{
		objetivo = o;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Cor getCor() {
		return cor;
	}
	
	public void addCarta(Carta c)
	{
		cartas.add(c);
	}
	
	public boolean podeTrocarCartas()
	{
		int cartasBola = 0;
		int cartasQuadrado = 0;
		int cartasTriangulo = 0;
		int cartasCoringa = 0;
		
		for(Carta c : this.cartas)
		{
			switch(c.getCartaForma())
			{
				case Circulo:
					cartasBola++;
				case Quadrado:
					cartasQuadrado++;
				case Triangulo:
					cartasTriangulo++;
				case Coringa:
					cartasCoringa++;
			}
		}
		
		// ISSO TA ERRADO!!
		// TODO!!
		if((cartasBola >= 3 || cartasQuadrado >= 3 || cartasTriangulo >= 3) || (cartasBola >=1 && cartasQuadrado >= 1 && cartasTriangulo >= 1))
			return true;
		else
			return false;
	}
	
	public ArrayList<Carta> getCartas() {
		return cartas;
	}
	
	public void addTerritorio(Territorio novo) {
		Territorios.add(novo);
	}
	
	// Territorios devem ter o mesmo hash; so pode haver uma instancia de terr no codigo.
	public void rmTerritorio(Territorio velho) {
		Territorios.remove(velho);
	}
	
	public int calculaTropasDisponiveis()
	{
		int tropas = 0;
		tropas += calculaTropasTerritorios();
		tropas += calculaTropasContinentes();
		return tropas;
	}
	
	private int calculaTropasTerritorios()
	{
		return Territorios.size()/2;
	}
	
	private int calculaTropasContinentes()
	{
		int bonusContinente = 0;
		
		for (Continente c : Continente.values())
		{
			if(hasContinente(c))
				bonusContinente += c.Bonus;
		}
		
		return bonusContinente;
	}
	
	public boolean hasContinente(Continente cont)
	{
		int count = 0;
		for(Territorio t : Territorios)
		{
			if(t.Continente == cont)
				count++;
		}
		
		if(count == cont.NumeroTerritorios)
			return true;
		else
			return false;
	}
	
	public void fazerTrocaCartas() throws Exception
	{
		throw new Exception("not implemented");
	}
}
