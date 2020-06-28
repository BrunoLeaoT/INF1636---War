package Model;

import java.util.Arrays;
import java.util.Random;

public class Dados 
{
	// rand só precisa ser inicializado uma vez para todos os dados.
	static private Random rand; 
	private int[] resultados;
	
	public Dados(int qtdDados)
	{
		if(rand == null)
			rand = new Random(); 
		
		if(qtdDados < 1)
			throw new IllegalArgumentException("Voce nao pode criar um conjunto de dados com menos de um dados");
		
		resultados = new int[qtdDados];
	}
	
	// calcula e retorna os resultados
	public int[] jogarDados()
	{
		for(int r : resultados)
			r = rand.nextInt(6) + 1;

		return resultados;
	}
	
	// organiza e retorna os resultados
	public int[] organizarResultados() 
	{
		Arrays.sort(resultados);
		return resultados;
	}
	
	public int[] getResultados()
	{
		return resultados;
	}
	
	// Joga o conjunto de dados, organiza e retorna os resultados em ordem ascendente.
	public int[] jogarEOrganizar()
	{
		jogarDados();
		organizarResultados();
		return resultados;
	}
}
