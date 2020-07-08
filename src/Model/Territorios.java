package Model;

import java.awt.Point;
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
	
	public Territorio selectTerritorioByCoordenada(int x, int y) throws Exception
	{
		Point p = new Point(x,y);
		
		for(Territorio t : ListaTerritorios)
			if(t.delimitacaoPossuiPonto(p))
				return t;
		
		throw new Exception("Não foi encontrado um território nessa posição/coordenada");
	}
	
	private void InicializaTerritorios()
	{
		// Inicializa a porra toda
		ListaTerritorios.add(new Territorio("Africa do Sul", Continente.Africa, "Angola,Somalia"));
		ListaTerritorios.get(0).SetDelimitacao(531, 544, 612, 542, 537, 588, 606, 593);
		ListaTerritorios.add(new Territorio("Angola", Continente.Africa, "Africa do Sul,Somalia,Nigeria"));
		ListaTerritorios.get(1).SetDelimitacao(518, 485, 580, 483, 525, 533, 571, 533);
		ListaTerritorios.add(new Territorio("Argelia", Continente.Africa,"Nigeria,Egito"));
		ListaTerritorios.get(2).SetDelimitacao(424, 356, 534, 343, 424, 399, 509, 399);
		ListaTerritorios.add(new Territorio("Egito", Continente.Africa,"Somalia,Nigeria,Argelia"));
		ListaTerritorios.get(3).SetDelimitacao(553, 358, 593, 357, 562, 427, 601, 426);
		ListaTerritorios.add(new Territorio("Nigeria", Continente.Africa,"Argelia,Angola,Egito,Somalia,Brasil"));
		ListaTerritorios.get(4).SetDelimitacao(433, 409, 547, 411, 450, 461, 564, 468);
		ListaTerritorios.add(new Territorio("Somalia", Continente.Africa, "Angola,Egito,Africa do Sul,Nigeria"));
		ListaTerritorios.get(5).SetDelimitacao(592, 433, 635, 435, 594, 535, 630, 537);
		ListaTerritorios.add(new Territorio("Alasca", Continente.AmericaDoNorte,"Calgary,Vancouver,Siberia"));
		ListaTerritorios.get(6).SetDelimitacao(72,118,118,122, 65, 160, 109, 159);
		ListaTerritorios.add(new Territorio("Calgary", Continente.AmericaDoNorte, "Alasca,Vancouver,Groelandia"));
		ListaTerritorios.get(7).SetDelimitacao(144,121,253,125, 145, 168, 240,169);
		ListaTerritorios.add(new Territorio("California", Continente.AmericaDoNorte,"Vancouver,Texas,Mexico"));
		ListaTerritorios.get(8).SetDelimitacao(90,220,144,220, 84, 301, 126, 299);
		ListaTerritorios.add(new Territorio("Groelandia", Continente.AmericaDoNorte, "Calgary"));
		ListaTerritorios.get(9).SetDelimitacao(287,91,369,91, 282,132, 364, 132);
		ListaTerritorios.add(new Territorio("Mexico", Continente.AmericaDoNorte, "California,Texas,Venezuela"));
		ListaTerritorios.get(10).SetDelimitacao(106,320,132,320, 120,381,169, 377);//
		ListaTerritorios.add(new Territorio("Nova York", Continente.AmericaDoNorte, "Texas,Quebec"));
		ListaTerritorios.get(11).SetDelimitacao(204,246,268,247,194,329,260,335);
		ListaTerritorios.add(new Territorio("Quebec", Continente.AmericaDoNorte,"Vancouver,Nova York,Texas"));
		ListaTerritorios.get(12).SetDelimitacao(236,168,324,172,228,211,322,210);
		ListaTerritorios.add(new Territorio("Texas", Continente.AmericaDoNorte, "Nova York,California,Mexico,Quebec,Vancouver"));
		ListaTerritorios.get(13).SetDelimitacao(159,249,193,249,138,291,172,288);
		ListaTerritorios.add(new Territorio("Vancouver", Continente.AmericaDoNorte, "Alasca,California,Calgary,Quebec,Texas"));
		ListaTerritorios.get(14).SetDelimitacao(122,176,222,174,113,212,210,213);
		ListaTerritorios.add(new Territorio("Arabia Saudita", Continente.Asia,"Jordania,Iraque"));
		ListaTerritorios.get(15).SetDelimitacao(632,389,717,386,640,439,706,443);
		ListaTerritorios.add(new Territorio("Bangladesh", Continente.Asia, "India,Coreia do Sul,Tailandia,Indonesia"));
		ListaTerritorios.get(16).SetDelimitacao(840,362,868,363,836,453,871,453);
		ListaTerritorios.add(new Territorio("Cazaquistao", Continente.Asia, "Russia,China,Turquia,Mongolia,Siberia,Letonia,Japa"));
		ListaTerritorios.get(17).SetDelimitacao(779,197,907,201,786,236,904,235);
		ListaTerritorios.add(new Territorio("China", Continente.Asia,"Cazaquistao,Coreia do Norte,Coreia do Sul,India,Paquistao,Mongolia,Turquia"));
		ListaTerritorios.get(18).SetDelimitacao(770,271,819,270,772,322,823,319);
		ListaTerritorios.add(new Territorio("Coreia do Sul", Continente.Asia,"China,Coreia do Norte,India,Bangladesh,Tailandia,Japao"));
		ListaTerritorios.get(19).SetDelimitacao(824,325,910,329,824,342,912,339);
		ListaTerritorios.add(new Territorio("Coreia do Norte", Continente.Asia,"China,Coreia do Sul,Japao"));
		ListaTerritorios.get(20).SetDelimitacao(840,299,904,300,837,317,906,314);
		ListaTerritorios.add(new Territorio("Estonia", Continente.Asia,"Russia,Letonia,Suecia"));
		ListaTerritorios.get(21).SetDelimitacao(620,123,711,120,627,165,704,161);
		ListaTerritorios.add(new Territorio("India", Continente.Asia,"China,Paquistao,Bangladesh"));
		ListaTerritorios.get(22).SetDelimitacao(785,347,826,346,779,419,825,419);
		ListaTerritorios.add(new Territorio("Ira", Continente.Asia,"Iraque,Paquistao,Siria"));
		ListaTerritorios.get(23).SetDelimitacao(700,322,722,321,704,365,736,365);
		ListaTerritorios.add(new Territorio("Iraque", Continente.Asia,"Ira,Arabia Saudita,Jordania"));
		ListaTerritorios.get(24).SetDelimitacao(664,320,690,321,668,361,692,361);
		ListaTerritorios.add(new Territorio("Japao", Continente.Asia,"Mongolia,Coreia do Sul,Coreia do Norte,Cazaquistao"));
		ListaTerritorios.get(25).SetDelimitacao(924,224,951,224,924,295,953,295);
		ListaTerritorios.add(new Territorio("Jordania", Continente.Asia,"Arabia Saudita,Iraque"));
		ListaTerritorios.get(26).SetDelimitacao(608,318,658,318,611,365,642,356);
		ListaTerritorios.add(new Territorio("Letonia", Continente.Asia,"Estonia,Russia,Cazaquistao,Turquia"));
		ListaTerritorios.get(27).SetDelimitacao(608,173,720,171,612,215,722,213);
		ListaTerritorios.add(new Territorio("Mongolia", Continente.Asia,"Japao,China,Cazaquistao"));
		ListaTerritorios.get(28).SetDelimitacao(814,245,900,242,823,268,895,271);
		ListaTerritorios.add(new Territorio("Paquistao", Continente.Asia,"China,Turquia,Siria,Ira,India"));
		ListaTerritorios.get(29).SetDelimitacao(718,299,753,296,738,342,773,341);
		ListaTerritorios.add(new Territorio("Russia", Continente.Asia,"Siberia,Estonia,Cazaquistao,Letonia"));
		ListaTerritorios.get(30).SetDelimitacao(746,123,834,125,741,193,821,193);
		ListaTerritorios.add(new Territorio("Siberia", Continente.Asia,"Russia,Cazaquistao,Alasca"));
		ListaTerritorios.get(31).SetDelimitacao(876,113,944,113,862,194,944,191);
		ListaTerritorios.add(new Territorio("Siria", Continente.Asia,"Turquia,Paquistao,Iraque,Jordania"));
		ListaTerritorios.get(32).SetDelimitacao(626,275,708,274,632,301,706,302);
		ListaTerritorios.add(new Territorio("Tailandia", Continente.Asia,"Bangaladesh,Coreia do Sul"));
		ListaTerritorios.get(33).SetDelimitacao(884,351,925,351,882,433,918,431);
		ListaTerritorios.add(new Territorio("Turquia", Continente.Asia,"Cazaquistao,Letonia,Siria,Paquistao,China"));
		ListaTerritorios.get(34).SetDelimitacao(622,221,764,222,626,266,762,269);
		ListaTerritorios.add(new Territorio("Argentina", Continente.AmericaDoSul,"Brasil,Peru"));
		ListaTerritorios.get(35).SetDelimitacao(267,515,322,517,244,637,304,641);
		ListaTerritorios.add(new Territorio("Brasil", Continente.AmericaDoSul,"Peru,Venezuela,Argentina,Nigeria"));
		ListaTerritorios.get(36).SetDelimitacao(262,405,331,399,274,494,332,493);
		ListaTerritorios.add(new Territorio("Peru", Continente.AmericaDoSul,"Venezuela,Brasil,Argentina"));
		ListaTerritorios.get(37).SetDelimitacao(216,462,248,459,204,531,257,527);
		ListaTerritorios.add(new Territorio("Venezuela", Continente.AmericaDoSul,"Peru,Brasil,Mexico"));
		ListaTerritorios.get(38).SetDelimitacao(180,417,228,417,172,472,210,467);
		ListaTerritorios.add(new Territorio("Espanha", Continente.Europa,"Franca"));
		ListaTerritorios.get(39).SetDelimitacao(427,263,461,264,423,300,470,304);
		ListaTerritorios.add(new Territorio("Franca", Continente.Europa,"Italia,Espanha"));
		ListaTerritorios.get(40).SetDelimitacao(462,215,524,218,463,260,463,260);
		ListaTerritorios.add(new Territorio("Italia", Continente.Europa,"Franca,Romenia,Polonia"));
		ListaTerritorios.get(41).SetDelimitacao(527,229,561,227,519,293,560,291);
		ListaTerritorios.add(new Territorio("Polonia", Continente.Europa,"Ucrania,Italia,Romenia,Letonia"));
		ListaTerritorios.get(42).SetDelimitacao(560,184,590,185,562,219,589,217);
		ListaTerritorios.add(new Territorio("Reino Unido", Continente.Europa,"Suecia,Franca,Groelandia"));
		ListaTerritorios.get(43).SetDelimitacao(434,144,479,144,428,203,482,203);
		ListaTerritorios.add(new Territorio("Romenia", Continente.Europa,"Suecia,Franca,Groelandia"));
		ListaTerritorios.get(44).SetDelimitacao(566,237,583,240,563,299,598,294);
		ListaTerritorios.add(new Territorio("Suecia", Continente.Europa,"Reino Unido,Estonia"));
		ListaTerritorios.get(45).SetDelimitacao(501,96,580,92,298,171,588,165);
		ListaTerritorios.add(new Territorio("Ucrania", Continente.Europa,"Letonia,Turquia,Polonia,Romenia"));
		ListaTerritorios.get(46).SetDelimitacao(589,221,611,221,594,251,611,215);
		ListaTerritorios.add(new Territorio("Australia", Continente.Oceania,"Perth,Indonesia,Nova Zelandia"));
		ListaTerritorios.get(47).SetDelimitacao(860,567,900,569,824,646,898,643);
		ListaTerritorios.add(new Territorio("Indonesia", Continente.Oceania,"Perth,Australia"));
		ListaTerritorios.get(48).SetDelimitacao(837,481,939,484,848,507,942,523);
		ListaTerritorios.add(new Territorio("Nova Zelandia", Continente.Oceania,"Australia"));
		ListaTerritorios.get(49).SetDelimitacao(928,603,946,602,923,643,944,645);
		ListaTerritorios.add(new Territorio("Perth", Continente.Oceania,"Australia,Indonesia"));
		ListaTerritorios.get(50).SetDelimitacao(790,635,854,546,766,635,816,632);
	}
}
