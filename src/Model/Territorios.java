package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Territorios 
{
	static private Territorios singleton;
	private ArrayList<Territorio> ListaTerritorios;
	
	static public Territorios getInstancia()
	{
		if(singleton == null)
			singleton = new Territorios();
		return singleton;
	}
	
	private Territorios()
	{
		ListaTerritorios = new ArrayList<Territorio>();
		InicializaTerritorios();
	}
	
	public void shuffleTerritorios()
	{
		Collections.shuffle(ListaTerritorios);
	}
	
	public int getSize()
	{
		return ListaTerritorios.size();
	}
	
	public Territorio selectTerritorioByIndex(int index)
	{
		return ListaTerritorios.get(index);
	}
	
	public Territorio selectTerritorioByName(String territorioNome) throws Exception
	{
		for(Territorio t : ListaTerritorios)
			if(t.getNome().compareTo(territorioNome) == 0)
				return t;
		
		throw new Exception("Nome passado nao existe na lista de territorios");
	}
	
	private void InicializaTerritorios()
	{
		// Inicializa a porra toda
		ListaTerritorios.add(new Territorio("Africa do Sul", Continente.Africa, "Angola,Somalia"));
		ListaTerritorios.get(0).SetDelimitacao(458, 483, 544, 483, 463, 526, 531, 528);
		ListaTerritorios.add(new Territorio("Angola", Continente.Africa, "Africa do Sul,Somalia,Nigeria"));
		ListaTerritorios.get(1).SetDelimitacao(444, 420, 510, 420, 451, 469, 483, 469);
		ListaTerritorios.add(new Territorio("Argelia", Continente.Africa,"Nigeria,Egito"));
		ListaTerritorios.get(2).SetDelimitacao(351, 269, 456, 269, 353, 333, 435, 333);
		ListaTerritorios.add(new Territorio("Egito", Continente.Africa,"Somalia,Nigeria,Argelia"));
		ListaTerritorios.get(3).SetDelimitacao(482, 296, 523, 296, 484, 362, 528, 359);
		ListaTerritorios.add(new Territorio("Nigeria", Continente.Africa,"Argelia,Angola,Egito,Somalia,Brasil"));
		ListaTerritorios.get(4).SetDelimitacao(357, 347, 471, 347, 365, 402, 477, 402);
		ListaTerritorios.add(new Territorio("Somalia", Continente.Africa, "Angola,Egito,Africa do Sul,Nigeria"));
		ListaTerritorios.get(5).SetDelimitacao(521, 391, 596, 390, 519, 468, 568, 468);
		ListaTerritorios.add(new Territorio("Alasca", Continente.AmericaDoNorte,"Calgary,Vancouver,Siberia"));
		ListaTerritorios.get(6).SetDelimitacao(4,59,46,62, 6, 95, 33, 95);
		ListaTerritorios.add(new Territorio("Calgary", Continente.AmericaDoNorte, "Alasca,Vancouver,Groelandia"));
		ListaTerritorios.get(7).SetDelimitacao(74,60,189,67, 73, 101, 169, 103);
		ListaTerritorios.add(new Territorio("California", Continente.AmericaDoNorte,"Vancouver,Texas,Mexico"));
		ListaTerritorios.get(8).SetDelimitacao(14,156,63,157, 8, 239, 54, 234);
		ListaTerritorios.add(new Territorio("Groelandia", Continente.AmericaDoNorte, "Calgary"));
		ListaTerritorios.get(9).SetDelimitacao(211,28,296,25, 209,70, 283, 69);
		ListaTerritorios.add(new Territorio("Mexico", Continente.AmericaDoNorte, "California,Texas,Venezuela"));
		ListaTerritorios.get(10).SetDelimitacao(44,278,68,275, 55,335,86, 327);
		ListaTerritorios.add(new Territorio("Nova York", Continente.AmericaDoNorte, "Texas,Quebec"));
		ListaTerritorios.get(11).SetDelimitacao(127,186,189,188,111,239,179,236);
		ListaTerritorios.add(new Territorio("Quebec", Continente.AmericaDoNorte,"Vancouver,Nova York,Texas"));
		ListaTerritorios.get(12).SetDelimitacao(165,104,251,112,154,145,242,148);
		ListaTerritorios.add(new Territorio("Texas", Continente.AmericaDoNorte, "Nova York,California,Mexico,Quebec,Vancouver"));
		ListaTerritorios.get(13).SetDelimitacao(80,190,110,191,61,234,93,234);
		ListaTerritorios.add(new Territorio("Vancouver", Continente.AmericaDoNorte, "Alasca,California,Calgary,Quebec,Texas"));
		ListaTerritorios.get(14).SetDelimitacao(42,110,140,114,37,146,135,145);
		ListaTerritorios.add(new Territorio("Arabia Saudita", Continente.Asia,"Jordania,Iraque"));
		ListaTerritorios.get(15).SetDelimitacao(557,326,641,322,560,369,629,378);
		ListaTerritorios.add(new Territorio("Bangladesh", Continente.Asia, "India,Coreia do Sul,Tailandia,Indonesia"));
		ListaTerritorios.get(16).SetDelimitacao(754,310,786,312,755,392,790,389);
		ListaTerritorios.add(new Territorio("Cazaquistao", Continente.Asia, "Russia,China,Turquia,Mongolia,Siberia,Letonia,Japa"));
		ListaTerritorios.get(17).SetDelimitacao(705,135,829,133,709,174,828,170);
		ListaTerritorios.add(new Territorio("China", Continente.Asia,"Cazaquistao,Coreia do Norte,Coreia do Sul,India,Paquistao,Mongolia,Turquia"));
		ListaTerritorios.get(18).SetDelimitacao(691,209,745,207,698,258,746,259);
		ListaTerritorios.add(new Territorio("Coreia do Sul", Continente.Asia,"China,Coreia do Norte,India,Bangladesh,Tailandia,Japao"));
		ListaTerritorios.get(19).SetDelimitacao(749,259,838,261,750,276,838,278);
		ListaTerritorios.add(new Territorio("Coreia do Norte", Continente.Asia,"China,Coreia do Sul,Japao"));
		ListaTerritorios.get(20).SetDelimitacao(765,237,829,237,765,250,836,254);
		ListaTerritorios.add(new Territorio("Estonia", Continente.Asia,"Russia,Letonia,Suecia"));
		ListaTerritorios.get(21).SetDelimitacao(544,60,638,61,551,98,629,97);
		ListaTerritorios.add(new Territorio("India", Continente.Asia,"China,Paquistao,Bangladesh"));
		ListaTerritorios.get(22).SetDelimitacao(703,298,749,296,698,371,750,373);
		ListaTerritorios.add(new Territorio("Ira", Continente.Asia,"Iraque,Paquistao,Siria"));
		ListaTerritorios.get(23).SetDelimitacao(628,265,651,265,629,306,660,306);
		ListaTerritorios.add(new Territorio("Iraque", Continente.Asia,"Ira,Arabia Saudita,Jordania"));
		ListaTerritorios.get(24).SetDelimitacao(592,264,615,264,598,305,620,304);
		ListaTerritorios.add(new Territorio("Japao", Continente.Asia,"Mongolia,Coreia do Sul,Coreia do Norte,Cazaquistao"));
		ListaTerritorios.get(25).SetDelimitacao(848,166,882,165,848,234,878,235);
		ListaTerritorios.add(new Territorio("Jordania", Continente.Asia,"Arabia Saudita,Iraque"));
		ListaTerritorios.get(26).SetDelimitacao(530,258,567,261,531,299,559,299);
		ListaTerritorios.add(new Territorio("Letonia", Continente.Asia,"Estonia,Russia,Cazaquistao,Turquia"));
		ListaTerritorios.get(27).SetDelimitacao(534,110,648,110,538,152,647,152);
		ListaTerritorios.add(new Territorio("Mongolia", Continente.Asia,"Japao,China,Cazaquistao"));
		ListaTerritorios.get(28).SetDelimitacao(745,182,827,182,747,205,824,204);
		ListaTerritorios.add(new Territorio("Paquistao", Continente.Asia,"China,Turquia,Siria,Ira,India"));
		ListaTerritorios.get(29).SetDelimitacao(652,250,688,247,660,272,697,269);
		ListaTerritorios.add(new Territorio("Russia", Continente.Asia,"Siberia,Estonia,Cazaquistao,Letonia"));
		ListaTerritorios.get(30).SetDelimitacao(671,59,759,63,664,129,750,128);
		ListaTerritorios.add(new Territorio("Siberia", Continente.Asia,"Russia,Cazaquistao,Alasca"));
		ListaTerritorios.get(31).SetDelimitacao(800,51,871,51,788,128,863,127);
		ListaTerritorios.add(new Territorio("Siria", Continente.Asia,"Turquia,Paquistao,Iraque,Jordania"));
		ListaTerritorios.get(32).SetDelimitacao(553,214,630,213,557,237,627,237);
		ListaTerritorios.add(new Territorio("Tailandia", Continente.Asia,"Bangaladesh,Coreia do Sul"));
		ListaTerritorios.get(33).SetDelimitacao(810,284,851,281,804,364,842,363);
		ListaTerritorios.add(new Territorio("Turquia", Continente.Asia,"Cazaquistao,Letonia,Siria,Paquistao,China"));
		ListaTerritorios.get(34).SetDelimitacao(552,161,687,160,551,200,688,206);
		ListaTerritorios.add(new Territorio("Argentina", Continente.AmericaDoSul,"Brasil,Peru"));
		ListaTerritorios.get(35).SetDelimitacao(198,442,257,441,184,574,232,578);
		ListaTerritorios.add(new Territorio("Brasil", Continente.AmericaDoSul,"Peru,Venezuela,Argentina,Nigeria"));
		ListaTerritorios.get(36).SetDelimitacao(190,340,263,339,196,424,256,430);
		ListaTerritorios.add(new Territorio("Peru", Continente.AmericaDoSul,"Venezuela,Brasil,Argentina"));
		ListaTerritorios.get(37).SetDelimitacao(139,401,175,406,126,486,178,467);
		ListaTerritorios.add(new Territorio("Venezuela", Continente.AmericaDoSul,"Peru,Brasil,Mexico"));
		ListaTerritorios.get(38).SetDelimitacao(107,349,161,348,101,401,140,392);
		ListaTerritorios.add(new Territorio("Espanha", Continente.Europa,"Franca"));
		ListaTerritorios.get(39).SetDelimitacao(350,201,389,204,345,236,395,237);
		ListaTerritorios.add(new Territorio("Franca", Continente.Europa,"Italia,Espanha"));
		ListaTerritorios.get(40).SetDelimitacao(384,156,436,153,395,205,425,211);
		ListaTerritorios.add(new Territorio("Italia", Continente.Europa,"Franca,Romenia,Polonia"));
		ListaTerritorios.get(41).SetDelimitacao(448,167,485,166,443,234,479,231);
		ListaTerritorios.add(new Territorio("Polonia", Continente.Europa,"Ucrania,Italia,Romenia,Letonia"));
		ListaTerritorios.get(42).SetDelimitacao(484,126,516,127,486,155,512,151);
		ListaTerritorios.add(new Territorio("Reino Unido", Continente.Europa,"Suecia,Franca,Groelandia"));
		ListaTerritorios.get(43).SetDelimitacao(354,78,411,80,347,141,405,139);
		ListaTerritorios.add(new Territorio("Romenia", Continente.Europa,"Suecia,Franca,Groelandia"));
		ListaTerritorios.get(44).SetDelimitacao(488,192,517,192,489,236,519,233);
		ListaTerritorios.add(new Territorio("Suecia", Continente.Europa,"Reino Unido,Estonia"));
		ListaTerritorios.get(45).SetDelimitacao(427,30,505,33,425,105,500,103);
		ListaTerritorios.add(new Territorio("Ucrania", Continente.Europa,"Letonia,Turquia,Polonia,Romenia"));
		ListaTerritorios.get(46).SetDelimitacao(511,164,538,167,517,189,541,193);
		ListaTerritorios.add(new Territorio("Australia", Continente.Oceania,"Perth,Indonesia,Nova Zelandia"));
		ListaTerritorios.get(47).SetDelimitacao(771,522,840,526,758,594,829,597);
		ListaTerritorios.add(new Territorio("Indonesia", Continente.Oceania,"Perth,Australia"));
		ListaTerritorios.get(48).SetDelimitacao(761,415,876,412,770,457,868,460);
		ListaTerritorios.add(new Territorio("Nova Zelandia", Continente.Oceania,"Australia"));
		ListaTerritorios.get(49).SetDelimitacao(851,539,874,537,835,609,867,609);
		ListaTerritorios.add(new Territorio("Perth", Continente.Oceania,"Australia,Indonesia"));
		ListaTerritorios.get(50).SetDelimitacao(691,473,763,474,684,568,742,568);
	}
}
