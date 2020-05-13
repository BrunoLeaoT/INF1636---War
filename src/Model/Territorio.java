package Model;

import java.awt.Point;
import java.awt.geom.Point2D;

/// Classe Territorio
/// Representa um territorio no mapa.
public class Territorio 
{
	public String Nome;
	
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
	
	// Refatorar isso para menos parametros.
	public void SetDelimitacao(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) 
	{
		Ponto1.setLocation(x1, y1);
		Ponto2.setLocation(x2, y2);
		Ponto3.setLocation(x3, y3);
		Ponto4.setLocation(x4, y4);
	}
}
