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
	public String Nome;
	public Continente Continente;
	private Jogador Dono;
	private int Tropas;
	private Territorio[] territoriosFronteiricos;
	private String[] territoriosAdjacentes;
	// Delimitacao do territorio é estabelecida por um quadrilatero (4 pontos).
	public Point Ponto1;
	public Point Ponto2;
	public Point Ponto3;
	public Point Ponto4;
	
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
				if(territorios.get(index).Dono.getNome().compareTo(atacante) == 0)
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
	public void SetDono(Jogador jogador, int qtdTropas) throws IllegalArgumentException
	{
		// Check qtdTropas.
		if(qtdTropas < 1)
			throw new IllegalArgumentException("quantidadeTropas deve ser maior que 1");
		if(jogador == null)
			throw new IllegalArgumentException("Jogador inválido.");
		
		Dono = jogador;
	}
	
	/// Geta jogador dono do territorio.
	public Jogador getDono()
	{
		return Dono;
	}
	
	public String getNome()
	{
		return Nome;
	}
	
	public void addTropas(int tropasAAdicionar)
	{
		Tropas += tropasAAdicionar;
	}
	
	public void rmTropas(int tropasARemover)
	{
		Tropas += tropasARemover;
	}
	
	public int getTropas()
	{
		return Tropas;
	}
	
	public boolean podeAtacar(Territorio possivelVitima)
	{
		// Se territorio vitima nao eh do mesmo dono
		// Se territorio vitima faz fronteira
		// Se territorio atacante tem mais de uma tropa
		if(this.getDono() != possivelVitima.getDono())
			if(this.fazFronteira(possivelVitima))
				if(this.Tropas > 1)
					return true;
		
		return false;
	}
	
	public boolean fazFronteira(Territorio candidatoAFronteirico)
	{
		for(Territorio t : this.territoriosFronteiricos)
			if(t.Nome == candidatoAFronteirico.Nome)
				return true;
		
		return false;
	}
	
	// Seta quantidade de tropas.
	public static void setTropas(String territorio, int qtdTropas)
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
