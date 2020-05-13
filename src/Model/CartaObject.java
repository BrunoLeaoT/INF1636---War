package Model;

import java.util.ArrayList;

enum Forma{
	QUADRADO, CIRCULO, TRIANGULO;
}
class CartaObject {
	private String nomeTerritorio;
	private Forma forma;
	
	public CartaObject(String nome,Forma forma) {
		// TODO Auto-generated constructor stub
		nomeTerritorio = nome;
		this.forma = forma;
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
