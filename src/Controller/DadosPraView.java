package Controller;

import java.util.ArrayList;
import java.util.Map;

import Model.Cor;
import Model.Geral;

public class DadosPraView {
	private static DadosPraView dados = null;
	
	private DadosPraView() {
		// Isso é só pra testar sem criar novo jogo
//		Geral.getGeral().adicionarJogador("Bruno", Cor.VERDE);
//		Geral.getGeral().adicionarJogador("Stefano", Cor.AMARELO);
//		Geral.getGeral().adicionarJogador("Taia", Cor.PRETO);
//		Geral.getGeral().distribuirExercitosIniciais();

	}
	
	public static DadosPraView getDados(){
		if(dados == null)
			dados = new DadosPraView();
		return dados;
	}
	public Map getCoords() {
		return Geral.getGeral().getMapcoords();
	}
	
	public Map getTropasPorTerritorio() {
		return Geral.getGeral().getTropasPorTerritorios();
	}
	
	public Map<String, String> getJogadores() {
		return Geral.getGeral().getJogadoresToVIew();
	}
	
	public ArrayList<String> getCartasJogador(String jogador) {
		return Geral.getGeral().getCartasJogador(jogador);
	}

	public boolean adicionarJogador(String nome, String cor) {
		Cor corEscolhida = Cor.getCorPorString(cor);
		try {
			Geral.getGeral().adicionarJogador(nome, corEscolhida);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
	}
	

}
