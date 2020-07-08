package Model;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/// Classe Territorio
/// Representa um territorio no mapa.
public class Territorio implements Observado
{	
	private String Nome;
	private Continente Continente;
	private Jogador Dono;
	private int Tropas;
	private Territorio[] territoriosFronteiricos;
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
	
	public void descontruirTerritoriosAjdacentes(String terrs) {
		String []aux = terrs.split(",");
		territoriosAdjacentes = aux;
	}
	
	public boolean temTerrAdjacente(String atacante, String territorioAtacante) throws Exception {
		for (int i = 0; i < this.territoriosAdjacentes.length; i++) {
			Territorio t = Territorios.getInstancia().selectTerritorioByName(this.territoriosAdjacentes[i]);
			if(this.territoriosAdjacentes[i].compareTo(territorioAtacante) == 0) 
				if(t.getDono().getNome().compareTo(atacante) == 0)
					return true;
		}
		return false;
	}
	/// Estabelece delimitacao do territorio.
	// Refatorar isso para menos parametros.
	public void SetDelimitacao(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) 
	{
		int[] xArray = {x1, x2, x3, x4};
		int[] yArray = {y1,y2,y3, y4};
		delimitacao = new Polygon(xArray, yArray, 4);
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
		
		// checa se existem caminhos dentre as fronteiras e tal
		// TODO
		
		this.rmTropas(qtdTropas);
		destino.addTropas(qtdTropas);
		notificarObservadores();
	}
	
	/// Seta o novo dono do territorio, assim como a quantidade de tropas que traz consigo.
	public void setDono(Jogador jogador) throws IllegalArgumentException
	{
		Dono = jogador;
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
		Tropas = Math.min(Tropas - tropasARemover, 0);
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
	
	public boolean delimitacaoPossuiPonto(Point ponto)
	{
		return delimitacao.contains(ponto);
	}

	@Override
	public void addObservador(Observador obs) 
	{
		observadoresDeTropas.add(obs);
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
