package Model;

class Carta 
{
	private String nomeTerritorio;
	private CartaForma forma;
	
	public String getTerritorioNome() {
		return nomeTerritorio;
	}
	
	public CartaForma getCartaForma() {
		return forma;
	}
	
	public Carta(String nome,CartaForma forma) {
		nomeTerritorio = nome;
		this.forma = forma;
	}
}
