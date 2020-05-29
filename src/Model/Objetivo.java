package Model;

import java.util.ArrayList;
import java.util.Random;

class Objetivo {
	private ArrayList<String> ListaDeObjetivos;
	private Random rand;
	
	public Objetivo() {
		adicionarObjetivos();
		rand =  new Random();
	}
	
	private void adicionarObjetivos() {
		ListaDeObjetivos = new ArrayList<String>();
		ListaDeObjetivos.add("Conquistar America do Norte, Africa");
		ListaDeObjetivos.add("Matar todos os exercitos verdes ou 24 territorios");
		ListaDeObjetivos.add("Matar todos os exercitos vermelhos ou 24 territorios");
		ListaDeObjetivos.add("Matar todos os exercitos amarelos ou 24 territorios");
		ListaDeObjetivos.add("Matar todos os exercitos pretos ou 24 territorios");
		ListaDeObjetivos.add("Conquistar Ásia e Oceania");
		ListaDeObjetivos.add("Conquistar Europa, America do Sul e mais um continente a sua escolha");
		ListaDeObjetivos.add("Conquiste 24 territorios e tenha duas peças em cada");
	}
	
	public String randomizarObjetivo() {
		int index = rand.nextInt(ListaDeObjetivos.size());
		return ListaDeObjetivos.remove(index);
	
	}
}
