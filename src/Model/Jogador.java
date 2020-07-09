package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Jogador {
		
	private String nome;
	private Cor cor;
	private Objetivo objetivo;
	private int tropasDisponiveis;
	private ArrayList<Territorio> territorios;
	private ArrayList<Carta> cartas;
	private Map<CartaForma, Integer> mapCartaFormaQtd;
	
	public Jogador(String nomeRecebido, Cor corRecebida) 
	{
		if(nomeRecebido.equals(""))
			throw new IllegalArgumentException("Nome não pode ser vazio");
		
		nome = nomeRecebido;
		cor = corRecebida;
		objetivo = null;
		tropasDisponiveis = 0;
		cartas = new ArrayList<Carta>();
		territorios = new ArrayList<Territorio>();
		
		// inicializa mapCartaFormaQtd
		mapCartaFormaQtd = new HashMap<CartaForma, Integer>();
		mapCartaFormaQtd.put(CartaForma.Circulo, 0);
		mapCartaFormaQtd.put(CartaForma.Quadrado, 0);
		mapCartaFormaQtd.put(CartaForma.Triangulo, 0);
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}
	
	public void setObjetivo(Objetivo o)
	{
		objetivo = o;
	}
	
	public boolean verificarVitoria()
	{
		return this.objetivo.verificarVitoria(this);
	}
	
	public String getNome() {
		return nome;
	}
	
	public Cor getCor() {
		return cor;
	}
	
	public int getTropasDisponiveis()
	{
		return tropasDisponiveis;
	}
	
	public void rmTropasDisponiveis(int qtd) throws Exception
	{
		if(tropasDisponiveis - qtd < 0)
			throw new Exception("Jogador não possui tropas disponíveis suficiente");
		
		tropasDisponiveis -= qtd;
	}
	
	public void addCarta(Carta c)
	{
		cartas.add(c);
		updateMapCartas(c.getCartaForma(), true);
	}
	
	private ArrayList<Carta> rmCartasForma(CartaForma forma, int qtd)
	{
		//TODO
		// Falta check se existe essa qtd de cartas no map
		ArrayList<Carta> cartasRemovidas = new ArrayList<Carta>();
		
		// Remove cartas do jogador
		int posCartas = 0;
		while(posCartas < cartas.size() && qtd > 0)
		{
			if(cartas.get(posCartas).getCartaForma() == forma || cartas.get(posCartas).getCartaForma() == CartaForma.Coringa)
			{
				// Updata map
				updateMapCartas(forma, false);
				
				// Remove carta da lista
				cartasRemovidas.add(cartas.remove(posCartas));
				qtd--;
			}
			
			posCartas++;
		}
		
		return cartasRemovidas;
	}
	
	// updata o map de Cartas de acordo com a forma escolhida para ser adiocionada/removida
	// boolean isAdicao = true siginifca carta adicionada, isAdicao = false significa carta removida.
	private void updateMapCartas(CartaForma formaCartaRemovida, boolean isAdicao)
	{
		int alteracao;
		if(isAdicao)
			alteracao = 1;
		else
			alteracao = -1;
		
		if(formaCartaRemovida != CartaForma.Coringa)
			mapCartaFormaQtd.put(formaCartaRemovida, mapCartaFormaQtd.get(formaCartaRemovida) + alteracao);
		else
		{
			mapCartaFormaQtd.put(CartaForma.Circulo, mapCartaFormaQtd.get(formaCartaRemovida) + alteracao);
			mapCartaFormaQtd.put(CartaForma.Quadrado, mapCartaFormaQtd.get(formaCartaRemovida) + alteracao);
			mapCartaFormaQtd.put(CartaForma.Triangulo, mapCartaFormaQtd.get(formaCartaRemovida) + alteracao);
		}
	}
	
	// wrapper que previne tentar fazer troca quando nao dá.
	// remove necessidade de checks em realizarTrocaCartas e rmCartasForma.
	// ja adiciona a tropas disponiveis a qtd de exercitos obtidos
	public ArrayList<Carta> tentaTrocarCartas(int numeroTrocaPartida)
	{
		if(podeTrocarCartas())
		{
			ArrayList<Carta> cartasTrocadas = realizarTrocaCartas();
			tropasDisponiveis += calculaTropasTroca(numeroTrocaPartida);
			return cartasTrocadas;
		}
		else
			return null;
	}
	
	public boolean podeTrocarCartas()
	{		
		if((mapCartaFormaQtd.get(CartaForma.Circulo) >= 3 || mapCartaFormaQtd.get(CartaForma.Quadrado) >= 3 || mapCartaFormaQtd.get(CartaForma.Triangulo) >= 3)
				|| (mapCartaFormaQtd.get(CartaForma.Circulo) >=1 && mapCartaFormaQtd.get(CartaForma.Circulo) >= 1 && mapCartaFormaQtd.get(CartaForma.Circulo) >= 1))
			return true;
		else
			return false;
	}
	
	private ArrayList<Carta> realizarTrocaCartas()
	{
		if(mapCartaFormaQtd.get(CartaForma.Circulo) >= 3)
			return rmCartasForma(CartaForma.Circulo, 3);
		else if(mapCartaFormaQtd.get(CartaForma.Quadrado) >= 3)
			return rmCartasForma(CartaForma.Quadrado, 3);
		else if(mapCartaFormaQtd.get(CartaForma.Triangulo) >= 3)
			return rmCartasForma(CartaForma.Triangulo, 3);
		else if(mapCartaFormaQtd.get(CartaForma.Quadrado) >= 1 && mapCartaFormaQtd.get(CartaForma.Quadrado) >= 1 && mapCartaFormaQtd.get(CartaForma.Quadrado) >= 1)
		{
			ArrayList<Carta> cartasRemovidas = new ArrayList<Carta>();
			cartasRemovidas.addAll(rmCartasForma(CartaForma.Circulo, 1));
			cartasRemovidas.addAll(rmCartasForma(CartaForma.Quadrado, 1));
			cartasRemovidas.addAll(rmCartasForma(CartaForma.Triangulo, 1));
			return cartasRemovidas;
		}
		// Nunca vai acontecer por causa de tentaTrocarCartas;
		else
			return null;
	}
	
	public void addTerritorio(Territorio novo) {
		territorios.add(novo);
	}
	
	// Territorios devem ter o mesmo hash; so pode haver uma instancia de terr no codigo.
	public void rmTerritorio(Territorio velho) {
		territorios.remove(velho);
	}
	
	// atualiza e retorna tropasDisponiveis para o jogador distribuir no inicio do turno
	public void atualizaTropasDisponiveis()
	{
		tropasDisponiveis += calculaTropasTerritorios();
		tropasDisponiveis += calculaTropasContinentes();
	}
	
	private int calculaTropasTerritorios()
	{
		return territorios.size()/2;
	}
	
	private int calculaTropasContinentes()
	{
		int bonusContinente = 0;
		
		for (Continente c : Continente.values())
		{
			if(hasContinente(c))
				bonusContinente += c.getBonus();
		}
		
		return bonusContinente;
	}
	
	private int calculaTropasTroca(int numeroTrocaPartida)
	{
		if(numeroTrocaPartida < 5)
			return 4 + 2 * numeroTrocaPartida;
		else
			return (numeroTrocaPartida - 2) * 5;
	}
	
	public boolean hasContinente(Continente cont)
	{
		int count = 0;
		for(Territorio t : territorios)
		{
			if(t.getContinente() == cont)
				count++;
		}
		
		if(count == cont.getNumeroTerritorios())
			return true;
		else
			return false;
	}

	public ArrayList<Carta> getCartas() 
	{
		return cartas;
	}
	
	public Carta getCarta(int index)
	{
		return cartas.get(index);
	}
	
	public int getQtdCartas()
	{
		return cartas.size();
	}
}
