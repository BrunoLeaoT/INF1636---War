package Model;

import java.util.ArrayList;

public class Teste {
	static Geral geral =Geral.getGeral();
	// TESTE DEVEM SER CHAMADOS EM ORDEM QUE ESTÃO IMPLEMENTADOS
	public static boolean testeInclusãoJogador() {
		Geral.getGeral().adicionarJogador("Ivan", Cor.AZUL);
		if(geral.jogadores.getJogadores().size() > 0) {
			System.out.println("Caso Teste Adicionar jogadores feito com sucesso");
			return true;
		}
		else {
			System.err.println("Caso Teste adicionar jogadores INSUCESSO");

			return false;
		}
	}
	public static void testeRandomizarJogadores() {
		geral.adicionarJogador("Bruno", Cor.VERDE);
		geral.adicionarJogador("Stefano", Cor.AMARELO);
		ArrayList<Jogador> jogadoresAntes = geral.jogadores.getJogadores();
		geral.jogadores.randomizarJogadores();
		if(jogadoresAntes.equals(geral.jogadores.getJogadores())) // Num sei real se isso daria insucesso algum momento
			System.out.println("Caso Randomizando ordem de jogada com sucesso");
		else
			System.err.println("Caso Randomizando ordem de jogada com INSUCESSO");
		
	}
	public static void testeCorJaExiste() {
		try {
			geral.adicionarJogador("Bruno", Cor.VERDE);
			System.err.println("Caso teste adicionar mesma cor INSUCESSO");
		} catch (Exception e) {
			System.out.println("Caso teste adicionar mesma cor sucesso " + e.toString());

		}
	}
	public static void testeJogadoresLimite() {
		geral.adicionarJogador("Outr", Cor.VERMELHO);
		geral.adicionarJogador("Fulano", Cor.PRETO);
		geral.adicionarJogador("JohnDoe", Cor.BRANCO);
		try {
			geral.adicionarJogador("JaneDoe", Cor.AMARELO); 
			System.err.println("Caso adicionar fora do limite jogador feito com INSUCESSO ");
		} catch (Exception e) {
			System.out.println("Caso adicionar fora do limite jogador feito com sucesso "  + e.toString());
		}

	}
	
	public static void testeDistribuirCartas() {
		if(geral.jogadores.getJogadores().size() > 0) {
			geral.cartas.distribuirCartas(Jogador.jogadores);
			if(geral.jogadores.getJogadores().get(0).getCartas().size() > 0)
				System.out.println("Caso Teste distribuir cartas feito com sucesso");
			else
				System.err.println("Caso Teste distribuir cartas  jogadores insucesso");
		}
	}
//	public static void testeJogadorTemObjetivo() {
//		if(geral.jogadores.getJogadores().get(0).objetivo.length() > 0) {
//			System.out.println("Caso jogador tem Objetivo feito com sucesso");
//		}
//		else {
//			System.out.println("Caso jogador tem Objetivo feito com INSUCESSO");
//		}
//	}
	public static void testePodeTrocarCartaTrueTresIguais() {
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta("África do Sul", Forma.TRIANGULO));
		cartas.add(new Carta("Alaska", Forma.TRIANGULO));
		cartas.add(new Carta("Aral", Forma.TRIANGULO));
		int numeroTropas = geral.cartas.podeTrocarCarta(cartas);
		if(numeroTropas > 0)
			System.out.println("Caso teste trocar cartas três formas iguais feito com sucesso");
		else
			System.err.println("Caso teste trocar cartas três formas iguais feito com INSUCESSO");
	}
	public static void testePodeTrocarCartaTrueTresDiferentes() {
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta("África do Sul", Forma.TRIANGULO));
		cartas.add(new Carta("Alemanha", Forma.CIRCULO));
		cartas.add(new Carta("Argentina", Forma.QUADRADO));
		int numeroTropas = geral.cartas.podeTrocarCarta(cartas);
		if(numeroTropas > 0)
			System.out.println("Caso teste trocar cartas três formas diff feito com sucesso");
		else
			System.err.println("Caso teste trocar cartas três formas diff feito com INSUCESSO");
	}
	public static void testeNaoTemTresCartasParaTrocar() {
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta("África do Sul", Forma.TRIANGULO));
		cartas.add(new Carta("Alemanha", Forma.CIRCULO));
		try {
			int numeroTropas = geral.cartas.podeTrocarCarta(cartas);
			System.err.println("Caso Teste nao tem tres cartas para trocar feito com INSUCESSO");
		} catch (Exception e) {
			System.out.println("Caso Teste nao tem tres cartas para trocar feito com sucesso "  + e.toString());
		}
	}
	public static void testeNaoPodeTrocar() {
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta("África do Sul", Forma.TRIANGULO));
		cartas.add(new Carta("Alaska", Forma.TRIANGULO));
		cartas.add(new Carta("Alemanha", Forma.CIRCULO));
		int numeroTropas = geral.cartas.podeTrocarCarta(cartas);
		if(numeroTropas == 0) 
			System.out.println("Caso Teste nao pode trocar feito com sucesso");
		else
			System.err.println("Caso Teste nao pode feito com INSUCESSO");
	}
	public static void testeAdicionarTropasIniciais() {
		Geral.getGeral().distribuirExercitosIniciais();
		for (int i = 0; i < Territorio.territorios.size(); i++) {
			if(Territorio.territorios.get(i).GetDono() == null || Territorio.territorios.get(i).GetTropas() != 1) {
				System.err.println("Caso Teste tropas iniciais feito com INSUCESSO");
				return;
			}
			
		}

		System.out.println("Caso Teste tropas iniciais feito com sucesso");
	}
	
//	public static void testeObjetivoJogador() {
//		ObjetivoJogador objetivo = (ObjetivoJogador) ObjetivoJogador.obj.randomizarObjetivo();
//		switch (objetivo.jogadorAlvo.toString()) {
//		case "PRETO": {
//			System.out.println("Caso teste objetivo matar jogador feito com sucesso");
//			break;
//		}
//		case "VERDE": {
//			System.out.println("Caso teste objetivo matar jogador feito com sucesso");
//			break;
//		}
//		case "AZUL": {
//			System.out.println("Caso teste objetivo matar jogador feito com sucesso");
//			break;
//		}
//		case "BRANCO": {
//			System.out.println("Caso teste objetivo matar jogador feito com sucesso");
//			break;
//		}
//		case "VERMELHO": {
//			System.out.println("Caso teste objetivo matar jogador feito com sucesso");
//			break;
//		}
//		case "AMARELO": {
//			System.out.println("Caso teste objetivo matar jogador feito com sucesso");
//			break;
//		}
//		default:
//			System.out.println("Caso teste objetivo matar jogador feito com INSUCESSO");
//			break;
//		}
//	}
//	public static void testeObjetivoTerritorio() {
//		ObjetivoTerritorio objetivo = (ObjetivoTerritorio) ObjetivoTerritorio.obj.randomizarObjetivo();
//		if(objetivo.nmTerritorios == 24 || objetivo.nmTerritorios == 18) {
//			System.out.println("Caso teste objetivo territorio feito com sucesso");
//		}
//		else {
//			System.out.println("Caso teste objetivo territorio feito com INSUCESSO");
//		}
//	}
	public static void testeAtacanteCom1Tropa() {
		try {
			Geral.getGeral().ataque(2, 3,  "África do Sul",  "Alaska");
			System.err.println("Caso teste de ataque foi realizado com INSUCESSO");
			
		} catch (Exception e) {
			System.out.println("Caso teste de não pode atacar foi realizado com sucesso: " + e.toString());
		}
		
	}
	public static void testeDadoseAtaque() {
		Territorio.territorios.get(0).SetTropas(4);
		int atacAntes = Territorio.territorios.get(0).GetTropas();
		int defesAntes = Territorio.territorios.get(1).GetTropas();

		System.out.println("AtacAntes: " + atacAntes + "\t DefAntes2: " + defesAntes);
		
		Geral.getGeral().ataque(3,Territorio.territorios.get(1).GetTropas(), "Africa do Sul",  "Alasca");
		int tropasDepois = Territorio.territorios.get(0).GetTropas();
		int tropasDepoisDef = Territorio.territorios.get(1).GetTropas();
		
		System.out.println("AtacDpss: " + tropasDepois + "\t DefDps2: " + tropasDepoisDef);
		if(atacAntes == tropasDepois && defesAntes == tropasDepoisDef) {
			System.err.println("Caso teste de ataque foi realizado com INSUCESSO");
		}
		else {
			System.out.println("Caso teste de ataque foi realizado com sucesso" );
		}
	}
	public static void testeConquista() {
		boolean conquista = false;
		if(geral.conquista("Alasca","Africa do Sul"))
			System.out.println("Caso de teste Conquista feito com sucesso");
		else
			System.err.println("Caso de teste Conquista feito com INSUCESSO");
	}
	
	public static void testeRecebimentoCartaPosConquista(String jogador) {
		Jogador jogador1 = geral.jogadores.getJogadorPorNome(jogador);
		int numCartasAntes = jogador1.getCartas().size();
		testeConquista();
		int numCartasDepois = jogador1.getCartas().size();
		if(numCartasAntes < numCartasDepois)
			System.out.println("Caso teste receber carta pos conquista feito com sucesso");
		else
			System.err.println("Caso teste receber carta pos conquista feito com INsucesso");
	}
	public static void main(String[] args) {
		testeInclusãoJogador();
		testeRandomizarJogadores();
		testeCorJaExiste();
		testeJogadoresLimite();
		//testeDistribuirCartas();
		testeAdicionarTropasIniciais(); // Distruibuir cartas está aqui
		//testeJogadorTemObjetivo();
		testePodeTrocarCartaTrueTresIguais();
		testePodeTrocarCartaTrueTresDiferentes();
		testeNaoTemTresCartasParaTrocar();
		testeNaoPodeTrocar();
		//testeObjetivoJogador();
		testeAtacanteCom1Tropa();
		testeDadoseAtaque();
		testeRecebimentoCartaPosConquista("Bruno"); // Teste conquista esta atrelado
	}
}
