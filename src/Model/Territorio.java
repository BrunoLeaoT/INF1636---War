package Model;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

/// Classe Territorio
/// Representa um territorio no mapa.
public class Territorio implements Observado
{	
	private String Nome;
	private Continente Continente;
	private Jogador Dono;
	private int Tropas;
	private String[] territoriosAdjacentes;
	private ArrayList<Observador> observadoresDeTropas;
	
	// Delimitacao do territorio é estabelecida por um quadrilatero (4 pontos).
	private Polygon delimitacao;
	
	public Territorio(String nome, Continente cont, String terriAdja)
	{
		Nome = nome;
		Continente = cont;
		Dono = null;
		Tropas = 0;
		observadoresDeTropas = new ArrayList<Observador>();
		descontruirTerritoriosAjdacentes(terriAdja);
	}
	
	public void descontruirTerritoriosAjdacentes(String terrs) 
	{
		String []aux = terrs.split(",");
		territoriosAdjacentes = aux;
	}
	
	/// Estabelece delimitacao do territorio.
	// Refatorar isso para menos parametros.
	public void SetDelimitacao(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) 
	{
		int[] xArray = {x1, x2, x3, x4};
		int[] yArray = {y1,y2,y3, y4};
		delimitacao = new Polygon(xArray, yArray, 4);
	}
	
	boolean verificarClique(Point ponto) {
		int x[] = delimitacao.xpoints;
		int y[] = delimitacao.ypoints;
		if(ponto.x >= x[0] && ponto.x <= x[1] && ponto.x >= x[2] && ponto.x <= x[3]) {
			if(ponto.y >= y[0] && ponto.y >= y[1] && ponto.y <= y[2] && ponto.y <= y[3]) {
				return true;
			}
		}
		return false;
	}
	
	public String getCentroid() {
	    int x = 0, y = 0;
	    int pointCount = delimitacao.npoints;
	    int ys[] = delimitacao.ypoints;
	    int xs[] = delimitacao.xpoints;
	    System.out.println(pointCount);
	    for (int i = 0;i < pointCount;i++){
	        x += xs[i];
	        y += ys[i];
	    }

	    x = x/pointCount;
	    y = y/pointCount;
	    return x+";"+y;
	}
	
	public void remanejarTropas(Territorio destino, int qtdTropas) throws Exception
	{
		if(qtdTropas > this.getTropas() - 1)
			throw new Exception("territorio de origem deve ter no minimo uma tropa após remanejamento");
		
		if(!this.fazFronteira(destino.getNome()))
			throw new Exception("Territorio de destino não faz fronteira com o de origem");
		
		this.rmTropas(qtdTropas);
		destino.addTropas(qtdTropas);
		notificarObservadores();
	}
	
	/// Seta o novo dono do territorio e atualiza a lista de territorios do dono
	public void setDono(Jogador jogador) throws IllegalArgumentException
	{
		// atualiza territorios de dono antigo
		if(Dono != null)
			Dono.rmTerritorio(this);
		
		// setta novo dono
		Dono = jogador;
		
		// atualiza territorios do novo dono
		Dono.addTerritorio(this);
		
		notificarObservadores();
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
	
	public Continente getContinente()
	{
		return Continente;
	}
	
	public void addTropas(int tropasAAdicionar)
	{
		Tropas += tropasAAdicionar;
		notificarObservadores();
	}
	
	public void rmTropas(int tropasARemover)
	{
		// Tropas nao pode assumir valor inferiror a zero
		Tropas = Math.max(Tropas - tropasARemover, 0);
		notificarObservadores();
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
			if(this.fazFronteira(possivelVitima.getNome()))
				if(this.Tropas > 1)
					return true;
		
		return false;
	}
	
	public boolean fazFronteira(String nomeAdjacente)
	{
		for(String s : territoriosAdjacentes)
			if(s.equals(nomeAdjacente))
				return true;
		
		return false;
	}
	
	public boolean delimitacaoPossuiPonto(Point ponto)
	{
		return delimitacao.contains(ponto);
	}

	@Override
	public void addObservador(Observador obs) 
	{
		observadoresDeTropas.add(obs);
		
		// notifica logo que é adicionado para que as labels ja comecem com texto
		notificarObservadores();
	}

	@Override
	public void rmObservador(Observador obs)
	{
		observadoresDeTropas.remove(obs);
	}

	@Override
	public void notificarObservadores()
	{
		for(Observador obs : observadoresDeTropas)
			obs.atualizarObservacao(String.format("%s;%d;%s", this.Dono.getCor().name(), this.Tropas, this.Nome));
	}
}
