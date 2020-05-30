package Model;

public enum Continente
{
	Africa(2, 19),
	AmericaDoNorte(4, 8),
	AmericaDoSul(3, 6),
	Asia(5, 15),
	Europa(3, 8),
	Oceania(2, 4);
	
	public final int Bonus;
	public final int NumeroTerritorios;
	
	private Continente(int bonustropa, int qtdTerritorios)
	{
		Bonus = bonustropa;
		NumeroTerritorios = qtdTerritorios;
	}
}