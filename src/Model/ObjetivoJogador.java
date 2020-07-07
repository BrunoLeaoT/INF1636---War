package Model;

public class ObjetivoJogador extends Objetivo
{
	private Cor corJogadorAlvo;
	
	public ObjetivoJogador(Cor corVitima)
	{
		corJogadorAlvo = corVitima;
	}

	@Override
	public boolean verificarVitoria(Jogador jogador) {
		Territorios territorios = Territorios.getInstancia();
		for (int i = 0; i < territorios.getSize() ; i++) {
			if(territorios.selectTerritorioByIndex(i).getDono().getCor().compareTo(corJogadorAlvo) == 0)
				return false;
		}
		return true;
	}
	
	public Cor getCorAlvo()
	{
		return corJogadorAlvo; 
	}

	@Override
	public String objetivoEmString() {
		return "Jogador:"+corJogadorAlvo.toString();
	}

}
