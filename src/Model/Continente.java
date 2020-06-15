package Model;

import java.util.ArrayList;

public enum Continente
{
	Africa("África", 2, 19),
	AmericaDoNorte("América do Norte", 4, 8),
	AmericaDoSul("América do Sul", 3, 6),
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
	
	public static int temAlgumContinente(String jogador) {
		ArrayList<Territorio> territorios = Territorio.territorios;
		int africa = 0, amNorte = 0, amSul = 0, asia = 0, europa = 0, oceania = 0;
		int soma = 0;
		for (int i = 0; i < territorios.size(); i++) {
			if(jogador.compareTo(territorios.get(i).GetDono().nome) == 0) {
				Continente cont = territorios.get(i).Continente;
				if(cont.compareTo(Africa)  == 0) {
					africa++;
					if(africa == Africa.NumeroTerritorios)
						soma += Africa.Bonus;
				}
				else if(cont.compareTo(AmericaDoNorte)  == 0) {
					amNorte++;
					if(amNorte == AmericaDoNorte.NumeroTerritorios)
						soma += AmericaDoNorte.Bonus;
				}
				else if(cont.compareTo(AmericaDoSul)  == 0) {
					amSul++;
					if(amSul == AmericaDoSul.NumeroTerritorios)
						soma += AmericaDoSul.Bonus;
				}
				else if(cont.compareTo(Asia)  == 0) {
					asia++;
					if(asia == Asia.NumeroTerritorios)
						soma += Asia.Bonus;
				}
				else if(cont.compareTo(Europa)  == 0) {
					europa++;
					if(europa == Europa.NumeroTerritorios)
						soma += Europa.Bonus;
				}
				else if(cont.compareTo(Oceania)  == 0) {
					oceania++;
					if(oceania == Oceania.NumeroTerritorios)
						soma += Oceania.Bonus;
				}
				else {
					System.out.println("Unexpected continente: " + cont);
				}
			}	
		}
		return soma;
	}
}