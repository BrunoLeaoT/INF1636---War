package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Carta {
	private int numeroDeTrocas = 4;
	private String nomeTerritorio;
	private CartaForma forma;
	
	public String getTerritorio() {
		return nomeTerritorio;
	}
	
	public String getCartaForma() {
		return forma.name();
	}
	
	public Carta(String nome,CartaForma forma) {
		nomeTerritorio = nome;
		this.forma = forma;
	}
	
	public int podeTrocarCarta(ArrayList<Carta> cartas) {
		int numQuadrado = 0, numCirculo = 0, numTriangulo = 0;
		if(cartas.size() < 3)
			throw new IllegalArgumentException("Quantidade de cartas deve ser maior ou igual a 3");
		
		for (int i = 0; i < cartas.size(); i++) {
			if(cartas.get(i).forma.compareTo(CartaForma.Quadrado) == 0)
				numQuadrado++;
			
			else if(cartas.get(i).forma.compareTo(CartaForma.Triangulo) == 0)
				numTriangulo++;
			
			else if(cartas.get(i).forma.compareTo(CartaForma.Circulo) == 0)
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
}
