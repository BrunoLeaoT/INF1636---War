package Model;

public class Teste {
	static Geral geral = new Geral();
	
	public static boolean testeInclusãoJogador() {
		geral.adicionarJogador("Ivan", Cor.AZUL);
		if(geral.jogadores.size() > 0) {
			System.out.println("Teste Adicionar jogadores feito com sucesso");
			return true;
		}
		else {
			System.out.println("Teste adicionar jogadores insucesso");
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
	public static void main(String[] args) {
		testeDistribuirCartas();
	}
}
