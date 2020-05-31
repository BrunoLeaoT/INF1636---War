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
	
	public int[] JogaDados(int quantidadeDados) throws IllegalArgumentException
	{
		int[] dados = new int[quantidadeDados];
		// Check.
		if(quantidadeDados < 0)
			throw new IllegalArgumentException("quantidadeDados deve ser maior ou igual a zero");
		
		int i = 0;
		int soma = 0;
		while(i < quantidadeDados)
		{
			dados[i] = JogaDado();
			i++;
		}
		
		return dados;
	}
	
	public int[] organizarDados(int[] dados) {
		if(dados.length > 1) {
			if(dados[0] < dados[1]) {
				int aux = dados[0];
				dados[0] = dados[1];
				dados[1] = aux;
			}
		}
		if(dados.length == 3) {
			if(dados[1] < dados[2]) {
				int aux = dados[1];
				dados[1] = dados[2];
				dados[2] = aux;
			}
			if(dados[0] < dados[1]) {
				int aux = dados[0];
				dados[0] = dados[1];
				dados[1] = aux;
			}
		}
		return dados;
	}
}
