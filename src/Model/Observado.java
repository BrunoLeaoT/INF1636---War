package Model;

public interface Observado 
{
	public void addObservador(Observador obs);
	public void rmObservador(Observador obs);
	public void notificarObservadores();
}
