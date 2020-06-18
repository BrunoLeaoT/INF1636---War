package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/// Classe Territorio
/// Representa um territorio no mapa.
public class Territorio 
{
	public static ArrayList<Territorio> territorios; 
	
	public String Nome;
	public Continente Continente;
	private Jogador Dono;
	private int Tropas;
	private String[] territoriosAdjacentes;
	// Delimitacao do territorio é estabelecida por um quadrilatero (4 pontos).
	public Point Ponto1;
	public Point Ponto2;
	public Point Ponto3;
	public Point Ponto4;
	
	public Territorio() {
		addTerritorios();
	}
	public void addTerritorios() {
		territorios = new ArrayList<Territorio>();
		territorios.add(new Territorio("Africa do Sul", Continente.Africa, "Angola,Somalia"));
		territorios.get(0).SetDelimitacao(458, 483, 544, 483, 463, 526, 531, 528);
		territorios.add(new Territorio("Angola", Continente.Africa, "Africa do Sul,Somalia,Nigeria"));
		territorios.get(1).SetDelimitacao(444, 420, 510, 420, 451, 469, 483, 469);
		territorios.add(new Territorio("Argelia", Continente.Africa,"Nigeria,Egito"));
		territorios.get(2).SetDelimitacao(351, 269, 456, 269, 353, 333, 435, 333);
		territorios.add(new Territorio("Egito", Continente.Africa,"Somalia,Nigeria,Argelia"));
		territorios.get(3).SetDelimitacao(482, 296, 523, 296, 484, 362, 528, 359);
		territorios.add(new Territorio("Nigeria", Continente.Africa,"Argelia,Angola,Egito,Somalia,Brasil"));
		territorios.get(4).SetDelimitacao(357, 347, 471, 347, 365, 402, 477, 402);
		territorios.add(new Territorio("Somalia", Continente.Africa, "Angola,Egito,Africa do Sul,Nigeria"));
		territorios.get(5).SetDelimitacao(521, 391, 596, 390, 519, 468, 568, 468);
		territorios.add(new Territorio("Alasca", Continente.AmericaDoNorte,"Calgary,Vancouver,Siberia"));
		territorios.get(6).SetDelimitacao(4,59,46,62, 6, 95, 33, 95);
		territorios.add(new Territorio("Calgary", Continente.AmericaDoNorte, "Alasca,Vancouver,Groelandia"));
		territorios.get(7).SetDelimitacao(74,60,189,67, 73, 101, 169, 103);
		territorios.add(new Territorio("California", Continente.AmericaDoNorte,"Vancouver,Texas,Mexico"));
		territorios.get(8).SetDelimitacao(14,156,63,157, 8, 239, 54, 234);
		territorios.add(new Territorio("Groelandia", Continente.AmericaDoNorte, "Calgary"));
		territorios.get(9).SetDelimitacao(211,28,296,25, 209,70, 283, 69);
		territorios.add(new Territorio("Mexico", Continente.AmericaDoNorte, "California,Texas,Venezuela"));
		territorios.get(10).SetDelimitacao(44,278,68,275, 55,335,86, 327);
		territorios.add(new Territorio("Nova York", Continente.AmericaDoNorte, "Texas,Quebec"));
		territorios.get(11).SetDelimitacao(127,186,189,188,111,239,179,236);
		territorios.add(new Territorio("Quebec", Continente.AmericaDoNorte,"Vancouver,Nova York,Texas"));
		territorios.get(12).SetDelimitacao(165,104,251,112,154,145,242,148);
		territorios.add(new Territorio("Texas", Continente.AmericaDoNorte, "Nova York,California,Mexico,Quebec,Vancouver"));
		territorios.get(13).SetDelimitacao(80,190,110,191,61,234,93,234);
		territorios.add(new Territorio("Vancouver", Continente.AmericaDoNorte, "Alasca,California,Calgary,Quebec,Texas"));
		territorios.get(14).SetDelimitacao(42,110,140,114,37,146,135,145);
		territorios.add(new Territorio("Arabia Saudita", Continente.Asia,"Jordania,Iraque"));
		territorios.get(15).SetDelimitacao(557,326,641,322,560,369,629,378);
		territorios.add(new Territorio("Bangladesh", Continente.Asia, "India,Coreia do Sul,Tailandia,Indonesia"));
		territorios.get(16).SetDelimitacao(754,310,786,312,755,392,790,389);
		territorios.add(new Territorio("Cazaquistao", Continente.Asia, "Russia,China,Turquia,Mongolia,Siberia,Letonia,Japa"));
		territorios.get(17).SetDelimitacao(705,135,829,133,709,174,828,170);
		territorios.add(new Territorio("China", Continente.Asia,"Cazaquistao,Coreia do Norte,Coreia do Sul,India,Paquistao,Mongolia,Turquia"));
		territorios.get(18).SetDelimitacao(691,209,745,207,698,258,746,259);
		territorios.add(new Territorio("Coreia do Sul", Continente.Asia,"China,Coreia do Norte,India,Bangladesh,Tailandia,Japao"));
		territorios.get(19).SetDelimitacao(749,259,838,261,750,276,838,278);
		territorios.add(new Territorio("Coreia do Norte", Continente.Asia,"China,Coreia do Sul,Japao"));
		territorios.get(20).SetDelimitacao(765,237,829,237,765,250,836,254);
		territorios.add(new Territorio("Estonia", Continente.Asia,"Russia,Letonia,Suecia"));
		territorios.get(21).SetDelimitacao(544,60,638,61,551,98,629,97);
		territorios.add(new Territorio("India", Continente.Asia,"China,Paquistao,Bangladesh"));
		territorios.get(22).SetDelimitacao(703,298,749,296,698,371,750,373);
		territorios.add(new Territorio("Ira", Continente.Asia,"Iraque,Paquistao,Siria"));
		territorios.get(23).SetDelimitacao(628,265,651,265,629,306,660,306);
		territorios.add(new Territorio("Iraque", Continente.Asia,"Ira,Arabia Saudita,Jordania"));
		territorios.get(24).SetDelimitacao(592,264,615,264,598,305,620,304);
		territorios.add(new Territorio("Japao", Continente.Asia,"Mongolia,Coreia do Sul,Coreia do Norte,Cazaquistao"));
		territorios.get(25).SetDelimitacao(848,166,882,165,848,234,878,235);
		territorios.add(new Territorio("Jordania", Continente.Asia,"Arabia Saudita,Iraque"));
		territorios.get(26).SetDelimitacao(530,258,567,261,531,299,559,299);
		territorios.add(new Territorio("Letonia", Continente.Asia,"Estonia,Russia,Cazaquistao,Turquia"));
		territorios.get(27).SetDelimitacao(534,110,648,110,538,152,647,152);
		territorios.add(new Territorio("Mongolia", Continente.Asia,"Japao,China,Cazaquistao"));
		territorios.get(28).SetDelimitacao(745,182,827,182,747,205,824,204);
		territorios.add(new Territorio("Paquistao", Continente.Asia,"China,Turquia,Siria,Ira,India"));
		territorios.get(29).SetDelimitacao(652,250,688,247,660,272,697,269);
		territorios.add(new Territorio("Russia", Continente.Asia,"Siberia,Estonia,Cazaquistao,Letonia"));
		territorios.get(30).SetDelimitacao(671,59,759,63,664,129,750,128);
		territorios.add(new Territorio("Siberia", Continente.Asia,"Russia,Cazaquistao,Alasca"));
		territorios.get(31).SetDelimitacao(800,51,871,51,788,128,863,127);
		territorios.add(new Territorio("Siria", Continente.Asia,"Turquia,Paquistao,Iraque,Jordania"));
		territorios.get(32).SetDelimitacao(553,214,630,213,557,237,627,237);
		territorios.add(new Territorio("Tailandia", Continente.Asia,"Bangaladesh,Coreia do Sul"));
		territorios.get(33).SetDelimitacao(810,284,851,281,804,364,842,363);
		territorios.add(new Territorio("Turquia", Continente.Asia,"Cazaquistao,Letonia,Siria,Paquistao,China"));
		territorios.get(34).SetDelimitacao(552,161,687,160,551,200,688,206);
		territorios.add(new Territorio("Argentina", Continente.AmericaDoSul,"Brasil,Peru"));
		territorios.get(35).SetDelimitacao(198,442,257,441,184,574,232,578);
		territorios.add(new Territorio("Brasil", Continente.AmericaDoSul,"Peru,Venezuela,Argentina,Nigeria"));
		territorios.get(36).SetDelimitacao(190,340,263,339,196,424,256,430);
		territorios.add(new Territorio("Peru", Continente.AmericaDoSul,"Venezuela,Brasil,Argentina"));
		territorios.get(37).SetDelimitacao(139,401,175,406,126,486,178,467);
		territorios.add(new Territorio("Venezuela", Continente.AmericaDoSul,"Peru,Brasil,Mexico"));
		territorios.get(38).SetDelimitacao(107,349,161,348,101,401,140,392);
		territorios.add(new Territorio("Espanha", Continente.Europa,"Franca"));
		territorios.get(39).SetDelimitacao(350,201,389,204,345,236,395,237);
		territorios.add(new Territorio("Franca", Continente.Europa,"Italia,Espanha"));
		territorios.get(40).SetDelimitacao(384,156,436,153,395,205,425,211);
		territorios.add(new Territorio("Italia", Continente.Europa,"Franca,Romenia,Polonia"));
		territorios.get(41).SetDelimitacao(448,167,485,166,443,234,479,231);
		territorios.add(new Territorio("Polonia", Continente.Europa,"Ucrania,Italia,Romenia,Letonia"));
		territorios.get(42).SetDelimitacao(484,126,516,127,486,155,512,151);
		territorios.add(new Territorio("Reino Unido", Continente.Europa,"Suecia,Franca,Groelandia"));
		territorios.get(43).SetDelimitacao(354,78,411,80,347,141,405,139);
		territorios.add(new Territorio("Romenia", Continente.Europa,"Suecia,Franca,Groelandia"));
		territorios.get(44).SetDelimitacao(488,192,517,192,489,236,519,233);
		territorios.add(new Territorio("Suecia", Continente.Europa,"Reino Unido,Estonia"));
		territorios.get(45).SetDelimitacao(427,30,505,33,425,105,500,103);
		territorios.add(new Territorio("Ucrania", Continente.Europa,"Letonia,Turquia,Polonia,Romenia"));
		territorios.get(46).SetDelimitacao(511,164,538,167,517,189,541,193);
		territorios.add(new Territorio("Australia", Continente.Oceania,"Perth,Indonesia,Nova Zelandia"));
		territorios.get(47).SetDelimitacao(771,522,840,526,758,594,829,597);
		territorios.add(new Territorio("Indonesia", Continente.Oceania,"Perth,Australia"));
		territorios.get(48).SetDelimitacao(761,415,876,412,770,457,868,460);
		territorios.add(new Territorio("Nova Zelandia", Continente.Oceania,"Australia"));
		territorios.get(49).SetDelimitacao(851,539,874,537,835,609,867,609);
		territorios.add(new Territorio("Perth", Continente.Oceania,"Australia,Indonesia"));
		territorios.get(50).SetDelimitacao(691,473,763,474,684,568,742,568);
	}
	

	
	public Territorio(String nome, Continente cont, String terriAdja)
	{
		Nome = nome;
		Continente = cont;
		Ponto1 = new Point(0,0);
		Ponto2 = new Point(0,0);
		Ponto3 = new Point(0,0);
		Ponto4 = new Point(0,0);
		descontruirTerritoriosAjdacentes(terriAdja);
	}
	
	public void descontruirTerritoriosAjdacentes(String terrs) {
		String []aux = terrs.split(",");
		territoriosAdjacentes = aux;
	}
	
	public boolean temTerrAdjacente(String atacante, String territorioAtacante) {
		for (int i = 0; i < this.territoriosAdjacentes.length; i++) {
			int index = getTerritorioPorNome(this.territoriosAdjacentes[i]);
			if(this.territoriosAdjacentes[i].compareTo(territorioAtacante) == 0) 
				if(territorios.get(index).Dono.nome.compareTo(atacante) == 0)
					return true;
		}
		return false;
	}
	/// Estabelece delimitacao do territorio.
	// Refatorar isso para menos parametros.
	public void SetDelimitacao(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) 
	{
		Ponto1.setLocation(x1, y1);
		Ponto2.setLocation(x2, y2);
		Ponto3.setLocation(x3, y3);
		Ponto4.setLocation(x4, y4);
	}
	
	/// Seta o novo dono do territorio, assim como a quantidade de tropas que traz consigo.
	public static void SetDono(String territorio, Jogador jogador, int qtdTropas) throws IllegalArgumentException
	{
		int index = getTerritorioPorNome(territorio);
		// Check qtdTropas.
		if(qtdTropas < 1)
			throw new IllegalArgumentException("quantidadeTropas deve ser maior que 1");
		if(jogador == null)
			throw new IllegalArgumentException("Jogador inválido.");
		territorios.get(index).Dono = jogador;
		territorios.get(index).Tropas = qtdTropas;
	}
	
	/// Geta jogador dono do territorio.
	public Jogador GetDono()
	{
		return Dono;
	}
	
	// Seta quantidade de tropas.
	public static void SetTropas(String territorio, int qtdTropas)
	{
		int index = getTerritorioPorNome(territorio);
		// Check qtdTropas.
		if(qtdTropas < 1)
			throw new IllegalArgumentException("quantidadeTropas deve deve ser maior que 0");
		
		territorios.get(index).Tropas = qtdTropas;
	}
	// Remove quantidade de tropas.
	public boolean removeTropas(String territorio,int qtdTropas)
	{
		int index = getTerritorioPorNome(territorio);
		// Check qtdTropas.
		if(qtdTropas < 0)
			throw new IllegalArgumentException("quantidadeTropas deve deve ser maior ou igual que 0");
		
		territorios.get(index).Tropas = territorios.get(index).Tropas - qtdTropas;
		if(territorios.get(index).Tropas < 0)
			territorios.get(index).Tropas = 0;
		if(territorios.get(index).Tropas == 0)
			return true;
		return false;
	}
	
	// Get a quantidade de tropas.
	public static int GetTropas(String territorio)
	{
		int index = getTerritorioPorNome(territorio);
		return territorios.get(index).Tropas;
	}

	public static int getTerritorioPorNome(String nomeTerritorio) {
		for(int i = 0; i < Territorio.territorios.size(); i++) {
			if(territorios.get(i).Nome.compareTo(nomeTerritorio) == 0) {
				return i;
			}
		}
		return -1;
	}
	
	public void adicionarTropaPorNome(String nomeTerritorio, int tropas, Jogador jogador) {
		for(int i = 0; i < Territorio.territorios.size(); i++) {
			if(territorios.get(i).Nome.compareTo(nomeTerritorio) == 0) {
				//System.out.println(territorios.get(i).Nome);
				territorios.get(i).SetDono(nomeTerritorio,jogador, tropas);
				break;
			}
		}
	}
}
