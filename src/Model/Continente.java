package Model;

public enum Continente
{
	Africa("�frica", 2, 19),
	AmericaDoNorte("Am�rica do Norte", 4, 8),
	AmericaDoSul("Am�rica do Sul", 3, 6),
	Asia("Asia", 5, 15),
	Europa("Europa", 3, 8),
	Oceania("Oceania", 2, 4);
	
	public final String Nome;
	public final int Bonus;
	public final int NumeroTerritorios;
	
	private Continente(String nome, int bonustropa, int qtdTerritorios)
	{
		Nome = nome;
		Bonus = bonustropa;
		NumeroTerritorios = qtdTerritorios;
	}
}