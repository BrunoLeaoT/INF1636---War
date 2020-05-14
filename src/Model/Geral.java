package Model;

import java.util.ArrayList;
import java.util.Collections;

enum Cor{
	VERDE, PRETO, AZUL, VERMELHO, AMARELO,BRANCO;
}
public class Geral {
	ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	int jogadorDaVez = 0;
	Objetivo objetivo = new Objetivo();
	Cartas cartas = new Cartas();
	ArrayList<Territorio> territorios = new ArrayList<Territorio>();
	
	// ******** Funções Jogadores ********
	public void adicionarJogador(String nome,Cor cor) {
		if(jogadores.size() == 6)
			throw new IllegalArgumentException("quantidade de jogadores excedida");
		Jogador auxJogador = new Jogador(nome, cor, objetivo.randomizarObjetivo());
		jogadores.add(auxJogador);
	}
	
	//Antes de começar randomizar o os jogadores para determinar ordem
	public void randomizarJogadores() {
		Collections.shuffle(jogadores);
	}

	// ******** Funções Exercito e territorios ********
	public void distribuirExercitos() {
		cartas.distribuirCartas(jogadores);
		for (int i = 0; i < jogadores.size(); i++) {
			adicionarExercitosIniciais(jogadores.get(i));
		}
	}
	
	public void adicionarExercitosIniciais(Jogador jogador) {
		ArrayList<CartaObject> cartasJogador = jogador.getCartas();
		for (int i = 0; i < cartasJogador.size(); i++) {
			Territorio territorio = acharTerritorioPorNome(cartasJogador.get(i).getTerritorio());
			if(territorio == null)
				throw new IllegalArgumentException("Territorio não existe");
			territorio.SetDono(jogador, 1);
		}
		jogador.getCartas().clear();
	}
	
	private Territorio acharTerritorioPorNome(String territorio) {
		for (int i = 0; i < territorios.size(); i++) {
			if(territorios.get(i).Nome.equals(territorio))
				return territorios.get(i);
		}
		return null;
	}
	
	public void adicionarExercitos(int numDeTropas, Territorio territorio) {
		territorio.SetTropas(numDeTropas);
	}


}
