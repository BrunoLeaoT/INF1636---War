package Model;

import java.awt.Point;

/// Classe Territorio
/// Representa um territorio no mapa.
class Territorio 
{
	String Nome;
	private Jogador Dono;
	private int Tropas;
	
	// Delimitacao do territorio é estabelecida por um quadrilatero (4 pontos).
	private Point Ponto1;
	private Point Ponto2;
	private Point Ponto3;
	private Point Ponto4;
	
	public Territorio(String nome)
	{
		Nome = nome;
		
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
	
	/// Geta quantidade de tropas.
	public int GetTropas()
	{
		return Tropas;
	}
}
