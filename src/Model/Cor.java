package Model;

public enum Cor{
	Verde, Preto, Azul, Vermelho, Amarelo, Branco;
	
	public static Cor getCorPorString(String cor) {
		switch (cor) {
		case "Amarelo": {
			return Cor.Amarelo;
		}
		case "Verde": {
			return Cor.Verde;
		}
		case "Vermelho": {
			return Cor.Vermelho;
		}
		case "Azul": {
			return Cor.Azul;
		}
		case "Preto": {
			return Cor.Preto;
		}
		case "Branco": {
			return Cor.Branco;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + cor);
		}
	}
}

