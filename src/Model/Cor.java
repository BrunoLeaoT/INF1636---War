package Model;

public enum Cor{
	VERDE, PRETO, AZUL, VERMELHO, AMARELO,BRANCO;
	
	public static Cor getCorPorString(String cor) {
		switch (cor) {
		case "Amarelo": {
			return Cor.AMARELO;
		}
		case "Verde": {
			return Cor.VERDE;
		}
		case "Vermelho": {
			return Cor.VERMELHO;
		}
		case "Azul": {
			return Cor.AZUL;
		}
		case "Preto": {
			return Cor.PRETO;
		}
		case "Branco": {
			return Cor.BRANCO;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + cor);
		}
	}
}

