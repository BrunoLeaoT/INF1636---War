package Model;

import java.util.Random;

/// Classe Dado
/// Responsável por pela manipulação de dados de 6 lados.
public class Dado 
{
	static private Random rand; 
	
	// rand só precisa ser inicializado uma vez para todos os dados.
	public Dado()
	{
		if(rand == null)
			rand = new Random(); 
	}
	
	public int JogaDado()
	{
		return rand.nextInt(6) + 1;
	}
	
	public int JogaDados(int quantidadeDados) throws IllegalArgumentException
	{
		// Check.
		if(quantidadeDados < 0)
			throw new IllegalArgumentException("quantidadeDados deve ser maior ou igual a zero");
		
		int i = 0;
		int soma = 0;
		while(i < quantidadeDados)
		{
			soma += JogaDado();
			i++;
		}
		
		return soma;
	}
}
