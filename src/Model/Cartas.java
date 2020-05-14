package Model;

import java.util.ArrayList;

class Cartas {

	ArrayList<CartaObject> cartas = new ArrayList();
	public Cartas() {
		addCartas();
	}
	
	public void distribuirCartas(ArrayList<Jogador> jogadores) {
		if(jogadores.size() == 0)
			throw new IllegalArgumentException("quantidade jogadores deve ser maior que zero");
		for (int i = 0; i < cartas.size(); i++) {
			for(int j = 0; j < jogadores.size();j++) {
				if(cartas.size() == i)
					break;
				jogadores.get(j).getCartas().add(cartas.get(i));
				i++;
			}
		}
	}
	
	private void addCartas() {
		CartaObject aux;
		aux = new CartaObject("África do Sul", Forma.TRIANGULO);
		cartas.add(aux);
		aux = new CartaObject("Alaska", Forma.TRIANGULO);
		cartas.add(aux);
		aux = new CartaObject("Alemanha", Forma.CIRCULO);
		cartas.add(aux);
		aux = new CartaObject("Aral", Forma.TRIANGULO);
		cartas.add(aux);
		aux = new CartaObject("Argélia", Forma.CIRCULO);
		cartas.add(aux);
		aux = new CartaObject("Argentina", Forma.QUADRADO);
		cartas.add(aux);
		aux = new CartaObject("Austrália", Forma.TRIANGULO);
		cartas.add(aux);
		aux = new CartaObject("Bolívia", Forma.TRIANGULO);
		cartas.add(aux);
	}
}
