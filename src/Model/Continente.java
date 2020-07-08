package Model;

public enum Continente
{
	Africa("�frica", 3, 6),
	AmericaDoNorte("Am�rica do Norte", 5, 9),
	AmericaDoSul("Am�rica do Sul", 2, 4),
	Asia("Asia", 7, 20),
	Europa("Europa", 5, 8),
	Oceania("Oceania", 2, 4);
	
	private final String Nome;
	private final int Bonus;
	private final int NumeroTerritorios;
	
	private Continente(String nome, int bonustropa, int qtdTerritorios)
	{
		Nome = nome;
		Bonus = bonustropa;
		NumeroTerritorios = qtdTerritorios;
	}
	
	public String getNome()
	{
		return Nome;
	}
	
	public int getBonus()
	{
		return Bonus;
	}
	
	public int getNumeroTerritorios()
	{
		return NumeroTerritorios;
	}
	
	// Para fun��o de carrega jogo salvo
	public static Continente getContinentePorString(String cont) {
		if(cont.compareTo("�frica") == 0)
			return Africa;
		else if (cont.compareTo("Am�ricadoSul") == 0)
			return AmericaDoSul;
		else if (cont.compareTo("Am�ricadoNorte") == 0)
			return AmericaDoNorte;
		else if (cont.compareTo("Asia") == 0)
			return Asia;
		else if (cont.compareTo("Europa") == 0)
			return Europa;
		else if (cont.compareTo("Oceania") == 0)
			return Oceania;
		else
			return null;
	}
}