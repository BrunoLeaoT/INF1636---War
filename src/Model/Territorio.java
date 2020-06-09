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
		territorios.add(new Territorio("Africa do Sul", Continente.Africa));
		territorios.get(0).SetDelimitacao(747, 645, 825, 646, 748, 691, 823, 694);
		territorios.add(new Territorio("Angola", Continente.Africa));
		territorios.get(1).SetDelimitacao(731, 584, 797, 582, 736, 637, 786, 636);
		territorios.add(new Territorio("Argelia", Continente.Africa));
		territorios.get(2).SetDelimitacao(626, 450, 747, 452, 633, 501, 728, 498);
		territorios.add(new Territorio("Egito", Continente.Africa));
		territorios.get(3).SetDelimitacao(766, 461, 804, 458, 772, 528, 813, 522);
		territorios.add(new Territorio("Nigeria", Continente.Africa));
		territorios.get(4).SetDelimitacao(641, 510, 754, 512, 662, 572, 766, 567);
		territorios.add(new Territorio("Somalia", Continente.Africa));
		territorios.get(5).SetDelimitacao(806, 538, 847, 540, 807, 632, 839, 639);
		territorios.add(new Territorio("Alasca", Continente.AmericaDoNorte));
		territorios.get(6).SetDelimitacao(282,218,326,222, 272, 261, 318, 256);
		territorios.add(new Territorio("Calgary", Continente.AmericaDoNorte));
		territorios.get(7).SetDelimitacao(358,226,475,227, 360, 267, 463, 267);
		territorios.add(new Territorio("California", Continente.AmericaDoNorte));
		territorios.get(8).SetDelimitacao(310,323,355,323, 302, 401, 338, 405);
		territorios.add(new Territorio("Groelandia", Continente.AmericaDoNorte));
		territorios.get(9).SetDelimitacao(498,196,581,194, 499,233, 575, 234);
		territorios.add(new Territorio("Mexico", Continente.AmericaDoNorte));
		territorios.get(10).SetDelimitacao(308,446,357,439, 306,505,392, 506);
		territorios.add(new Territorio("Nova York", Continente.AmericaDoNorte));
		territorios.get(11).SetDelimitacao(413,349,479,351,397,438,459,439);
		territorios.add(new Territorio("Quebec", Continente.AmericaDoNorte));
		territorios.get(12).SetDelimitacao(463,269,533,275,459,303,527,303);
		territorios.add(new Territorio("Texas", Continente.AmericaDoNorte));
		territorios.get(13).SetDelimitacao(373,355,399,356,353,390,383,391);
		territorios.add(new Territorio("Vancouver", Continente.AmericaDoNorte));
		territorios.get(14).SetDelimitacao(331,275,433,277,323,310,425,313);
		territorios.add(new Territorio("Arabia Saudita", Continente.Asia));
		territorios.get(15).SetDelimitacao(845,491,926,485,848,543,922,545);
		territorios.add(new Territorio("Bangladesh", Continente.Asia));
		territorios.get(16).SetDelimitacao(1042,478,1079,475,1041,558,1081,556);
		territorios.add(new Territorio("Cazaquistao", Continente.Asia));
		territorios.get(17).SetDelimitacao(995,301,1121,301,998,338,1117,341);
		territorios.add(new Territorio("China", Continente.Asia));
		territorios.get(18).SetDelimitacao(979,374,1042,378,985,418,1041,414);
		territorios.add(new Territorio("Coreia do Sul", Continente.Asia));
		territorios.get(19).SetDelimitacao(1039,426,1125,426,1036,444,1126,441);
		territorios.add(new Territorio("Coreia do Norte", Continente.Asia));
		territorios.get(20).SetDelimitacao(1054,403,1116,405,1051,418,1120,420);
		territorios.add(new Territorio("Estonia", Continente.Asia));
		territorios.get(21).SetDelimitacao(830,194,926,192,840,264,914,264);
		territorios.add(new Territorio("India", Continente.Asia));
		territorios.get(22).SetDelimitacao(997,450,1044,451,995,546,1035,545);
		territorios.add(new Territorio("Ira", Continente.Asia));
		territorios.get(23).SetDelimitacao(915,418,934,420,915,465,940,466);
		territorios.add(new Territorio("Iraque", Continente.Asia));
		territorios.get(24).SetDelimitacao(873,430,902,427,877,458,902,458);
		territorios.add(new Territorio("Japao", Continente.Asia));
		territorios.get(25).SetDelimitacao(1137,320,1165,325,1135,402,1166,402);
		territorios.add(new Territorio("Jordania", Continente.Asia));
		territorios.get(26).SetDelimitacao(814,414,859,417,814,464,851,464);
		territorios.add(new Territorio("Letonia", Continente.Asia));
		territorios.get(27).SetDelimitacao(822,277,935,276,826,315,935,315);
		territorios.add(new Territorio("Mongolia", Continente.Asia));
		territorios.get(28).SetDelimitacao(1031,346,1117,348,1036,372,1111,370);
		territorios.add(new Territorio("Paquistao", Continente.Asia));
		territorios.get(29).SetDelimitacao(950,417,977,415,952,441,977,442);
		territorios.add(new Territorio("Russia", Continente.Asia));
		territorios.get(30).SetDelimitacao(955,221,1044,225,953,294,1034,293);
		territorios.add(new Territorio("Siberia", Continente.Asia));
		territorios.get(31).SetDelimitacao(1036,215,1162,215,1073,294,1157,299);
		territorios.add(new Territorio("Siria", Continente.Asia));
		territorios.get(32).SetDelimitacao(838,378,920,380,842,404,916,403);
		territorios.add(new Territorio("Tailandia", Continente.Asia));
		territorios.get(33).SetDelimitacao(1099,451,1135,449,1093,534,1123,541);
		territorios.add(new Territorio("Turquia", Continente.Asia));
		territorios.get(34).SetDelimitacao(835,324,983,324,839,368,975,371);
		territorios.add(new Territorio("Argentina", Continente.AmericaDoSul));
		territorios.get(35).SetDelimitacao(477,621,530,630,461,745,509,745);
		territorios.add(new Territorio("Brasil", Continente.AmericaDoSul));
		territorios.get(36).SetDelimitacao(482,505,550,498,485,592,543,598);
		territorios.add(new Territorio("Peru", Continente.AmericaDoSul));
		territorios.get(37).SetDelimitacao(426,570,467,573,417,633,467,630);
		territorios.add(new Territorio("Venezuela", Continente.AmericaDoSul));
		territorios.get(38).SetDelimitacao(392,526,453,513,386,569,427,561);
		territorios.add(new Territorio("Espanha", Continente.Europa));
		territorios.get(39).SetDelimitacao(639,365,673,365,632,404,674,405);
		territorios.add(new Territorio("Franca", Continente.Europa));
		territorios.get(40).SetDelimitacao(676,318,735,317,675,365,721,367);
		territorios.add(new Territorio("Italia", Continente.Europa));
		territorios.get(41).SetDelimitacao(735,334,776,324,728,388,765,383);
		territorios.add(new Territorio("Polonia", Continente.Europa));
		territorios.get(42).SetDelimitacao(773,285,801,287,774,322,801,321);
		territorios.add(new Territorio("Reino Unido", Continente.Europa));
		territorios.get(43).SetDelimitacao(639,243,700,244,635,306,696,307);
		territorios.add(new Territorio("Romenia", Continente.Europa));
		territorios.get(44).SetDelimitacao(774,348,799,350,776,393,809,392);
		territorios.add(new Territorio("Suecia", Continente.Europa));
		territorios.get(45).SetDelimitacao(716,198,795,195,707,273,792,266);
		territorios.add(new Territorio("Ucrania", Continente.Europa));
		territorios.get(46).SetDelimitacao(802,328,823,321,801,348,824,348);
		territorios.add(new Territorio("Australia", Continente.Oceania));
		territorios.get(47).SetDelimitacao(1064,676,1119,674,1050,751,1196,752);
		territorios.add(new Territorio("Indonesia", Continente.Oceania));
		territorios.get(48).SetDelimitacao(1051,582,1156,580,1052,621,1160,622);
		territorios.add(new Territorio("Nova Zelandia", Continente.Oceania));
		territorios.get(49).SetDelimitacao(1136,705,1158,705,1125,774,1153,775);
		territorios.add(new Territorio("Perth", Continente.Oceania));
		territorios.get(50).SetDelimitacao(989,665,1044,667,973,731,1030,729);
	}
	

	
	public Territorio(String nome, Continente cont)
	{
		Nome = nome;
		Continente = cont;
		Ponto1 = new Point(0,0);
		Ponto2 = new Point(0,0);
		Ponto3 = new Point(0,0);
		Ponto4 = new Point(0,0);
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
	public void SetDono(Jogador jogador, int qtdTropas) throws IllegalArgumentException
	{		
		// Check qtdTropas.
		if(qtdTropas < 1 || qtdTropas > 3)
			throw new IllegalArgumentException("quantidadeTropas deve variar de 1 a 3.");
		if(jogador == null)
			throw new IllegalArgumentException("Jogador inválido.");
		
		Dono = jogador;
		Tropas = qtdTropas;
	}
	
	/// Geta jogador dono do territorio.
	public Jogador GetDono()
	{
		return Dono;
	}
	
	/// Seta quantidade de tropas.
	public void SetTropas(int qtdTropas)
	{
		// Check qtdTropas.
		if(qtdTropas < 1)
			throw new IllegalArgumentException("quantidadeTropas deve deve ser maior que 0");
		
		Tropas = qtdTropas;
	}
	/// Remove quantidade de tropas.
	public boolean removeTropas(int qtdTropas)
	{
		// Check qtdTropas.
		if(qtdTropas < 0)
			throw new IllegalArgumentException("quantidadeTropas deve deve ser maior ou igual que 0");
		
		Tropas = Tropas - qtdTropas;
		if(Tropas < 0)
			Tropas = 0;
		if(Tropas == 0)
			return true;
		return false;
	}
	/// Geta quantidade de tropas.
	public int GetTropas()
	{
		return Tropas;
	}
	public Territorio getTerritorioPorNome(String nomeTerritorio) {
		for(int i = 0; i < Territorio.territorios.size(); i++) {
			if(territorios.get(i).Nome.compareTo(nomeTerritorio) == 0) {
				return territorios.get(i);
			}
		}
		return null;
	}
	public void adicionarTropaPorNome(String nomeTerritorio, int tropas, Jogador jogador) {
		for(int i = 0; i < Territorio.territorios.size(); i++) {
			if(territorios.get(i).Nome.compareTo(nomeTerritorio) == 0) {
				//System.out.println(territorios.get(i).Nome);
				territorios.get(i).SetDono(jogador, tropas);
				break;
			}
		}
	}
}
