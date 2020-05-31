package Model;

import java.awt.Point;
import java.util.ArrayList;

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
	private Point Ponto1;
	private Point Ponto2;
	private Point Ponto3;
	private Point Ponto4;
	
	public Territorio() {
		
		territorios = new ArrayList<Territorio>();
		territorios.add(new Territorio("África do Sul", Continente.Africa));
		territorios.add(new Territorio("Alaska", Continente.AmericaDoNorte));
		territorios.add(new Territorio("Alemanha", Continente.Europa));
		territorios.add(new Territorio("Aral", Continente.Asia));
		territorios.add(new Territorio("Argélia", Continente.Africa));
		territorios.add(new Territorio("Argentina", Continente.AmericaDoSul));
		territorios.add(new Territorio("Egito", Continente.Oceania));
		territorios.add(new Territorio("Bolívia", Continente.AmericaDoSul));
	}
	
	public Territorio(String nome, Continente cont)
	{
		Nome = nome;
		Continente = cont;
		Ponto1 = new Point(0,0);
		Ponto1 = new Point(0,0);
		Ponto1 = new Point(0,0);
		Ponto1 = new Point(0,0);
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
