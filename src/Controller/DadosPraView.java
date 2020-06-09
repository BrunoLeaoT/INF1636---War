package Controller;

import java.util.ArrayList;
import java.util.Map;

import Model.Geral;

public class DadosPraView {
	private static DadosPraView dados = null;
	
	private DadosPraView() {
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
	
	public ArrayList<String> getJogadores() {
		return Geral.getGeral().getJogadoresToVIew();
	}
}
