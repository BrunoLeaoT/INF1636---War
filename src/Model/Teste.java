package Model;

import java.util.ArrayList;

public class Teste {
	static Geral geral = new Geral();
	// TESTE DEVEM SER CHAMADOS EM ORDEM QUE ESTÃO IMPLEMENTADOS
	public static boolean testeInclusãoJogador() {
		geral.adicionarJogador("Ivan", Cor.AZUL);
		if(geral.jogadores.size() > 0) {
			System.out.println("Teste Adicionar jogadores feito com sucesso");
			return true;
		}
		else {
			System.out.println("Teste adicionar jogadores INSUCESSO");
			return false;
		}
	}
	
	public static void testeDistribuirCartas() {
		if(testeInclusãoJogador()) {
			geral.distribuirExercitos();
			/* if(geral.jogadores[0].territorios.lenght > 0){
					System.out.println("Teste distribuir cartas feito com sucesso");
				else
					System.out.println("Teste distribuir cartas  jogadores insucesso");
			 * }
			 */
		}
	}
	public static void testeJogadorTemObjetivo() {
		if(geral.jogadores.get(0).objetivo.length() > 0) {
			System.out.println("Caso jogador tem Objetivo feito com sucesso");
			System.out.println(geral.jogadores.get(0).objetivo);
		}
		else {
			System.out.println("Caso jogador tem Objetivo feito com INSUCESSO");
		}
	}
	public static void testeRandomizarJogadores() {
		geral.adicionarJogador("Bruno", Cor.VERDE);
		geral.adicionarJogador("Stefano", Cor.AMARELO);
		ArrayList<Jogador> jogadoresAntes = geral.jogadores;
		geral.randomizarJogadores();
		if(jogadoresAntes.equals(geral.jogadores)) // Num sei real se isso daria insucesso algum momento
			System.out.println("Caso Randomizando ordem de jogada com sucesso");
		else
			System.out.println("Caso Randomizando ordem de jogada com INSUCESSO");
		
	}
	public static void main(String[] args) {
		testeDistribuirCartas();
		testeJogadorTemObjetivo();
		testeRandomizarJogadores();
	}
}
