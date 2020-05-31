package Controller;

import Model.Geral;

public class TesteController {
	static Ataque ataque = new Ataque();
	public static void testeConquista() {
		boolean conquista = false;
		if(ataque.ataqueAoOponente("África do Sul", "Egito")) {
			conquista  = ataque.conquista("Bruno", "Egito", 2);
		}
		if(conquista)
			System.out.println("Caso de teste Controller Conquista feito com sucesso");
		else
			System.out.println("Caso de teste Controller Conquista feito com INSUCESSO");
	}
	public static void main(String[] args) {
		Model.Teste.main(null);
		testeConquista();
	}
}
