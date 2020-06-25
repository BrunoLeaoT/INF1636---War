package Model;

public enum Continente
{
	Africa("�frica", 3, 19),
	AmericaDoNorte("Am�rica do Norte", 5, 8),
	AmericaDoSul("Am�rica do Sul", 2, 6),
	Asia("Asia", 7, 15),
	Europa("Europa", 5, 8),
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