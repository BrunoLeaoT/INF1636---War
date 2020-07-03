package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;

import com.sun.tools.javac.Main;

public class Salvar {
	static Jogadores jogadores;
	static Territorios territorios;
	
	public Salvar() {
		jogadores = Jogadores.getInstancia();
		territorios = Territorios.getInstancia();
	}
	
	public static boolean salvarJogo() {
		String partida = "Jogadores: \n";
		partida += transformarJogadoresEmString();
		partida += "Territorios: \n";
		partida += transformarTerritoriosEmString();
		try {
			executarGravaçãoDados(partida);
			return true;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return false;
		}
	}
	
	private static void executarGravaçãoDados(String partida) throws IOException {
		PrintWriter outputStream = new PrintWriter(new FileWriter("partida.txt"));
		String[] part = partida.split("\n");
		for (int i = 0; i < part.length; i++)
			outputStream.println(part[i]);
		outputStream.close();
	}
	
	public static boolean carregarJogo() {
		try {
			String aux;
			if((aux = executarLeituraDeDados()) == null)
				return false;
			fazerJogo(aux);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	private static String executarLeituraDeDados() throws IOException {
		BufferedReader inputStream = new BufferedReader(new FileReader("partida.txt"));
		String partida = "",aux;
		while((aux = inputStream.readLine()) != null)
			partida += aux + "\n";
		inputStream.close();
		return partida;
	}
	
	private static void fazerJogo(String partida) {
		String aux[] = partida.split("Territorios: ");
		try {		
			setJogadores(aux[0]);
			setTerritorios(aux[1].split("\n")[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static boolean setJogadores(String jogadores) {
		// TODO Auto-generated method stub
		String aux[] = jogadores.split("\n");
		for (int i = 0; i < aux.length; i++) {
			String[] dados = aux[i].split(" ");
			if(dados[0].compareTo("Jogadores:") == 0)
				continue;
			System.out.println(dados[0]);
			String nome = dados[0];
			Cor cor = Cor.getCorPorString(dados[1]);
			String obj = dados[2];
			int index =  Integer.parseInt(dados[3]);
			try {
				Partida.getInstancia().adicionarJogador(nome, cor);
				// Setar o obj sla como ainda
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	private static void setTerritorios(String territorios) throws Exception {
		String aux[] = territorios.split("\n");
		for (int i = 0; i < aux.length; i++) {
			String[] dados = aux[i].split(" ");
			if(dados[0].compareTo("Territorios:") == 0)
				continue;
			String nome = dados[0];
			String dono = dados[1];
			int tropas = Integer.parseInt(dados[2]);
			int index = Integer.parseInt(dados[3]);
			Territorio terr = Territorios.getInstancia().selectTerritorioByName(nome);
			if(Jogadores.getInstancia().selectJogadorByName(dono) == null)
				throw new IllegalClassFormatException("Jogador dono do território não existe");
			terr.setDono(Jogadores.getInstancia().selectJogadorByName(dono));
			terr.addTropas(tropas);
		}
	}
	
	private static String transformarTerritoriosEmString() {
		String territoriosString = "";
		Territorio terr;
		for (int i = 0; i < territorios.getSize(); i++) {
			terr = territorios.selectTerritorioByIndex(i);
			territoriosString += terr.Nome + " " + terr.getDono().getNome() + " " + terr.getTropas() + " " + i;
			territoriosString += "\n";
		}
		return territoriosString;
	}
	
	private static String transformarJogadoresEmString() {
		String jogadoresString= "";
		Jogador jog;
		for (int i = 0; i < jogadores.getQtdJogadores(); i++) {
			jog = jogadores.selectJogadorByIndex(i);
			jogadoresString += jog.getNome() + " " + jog.getCor().toString() + " " + jog.getObjetivo().toString() + " " + i;
			ArrayList<Carta> cartas = jog.getCartas();
			for (int j = 0; j < cartas.size(); j++) {
				jogadoresString += cartas.get(i).getTerritorio();
				if( (j +1) < cartas.size())
					jogadoresString += ",";
			}
			
			jogadoresString += "\n";
		}
		return jogadoresString;
	}
	
	public static void main(String[] args) {
		try {
			Partida.getInstancia().adicionarJogador("Bruno", Cor.Amarelo);
			Partida.getInstancia().adicionarJogador("Stefano", Cor.Vermelho);
			Partida.getInstancia().adicionarJogador("Ivan", Cor.Azul);
			Partida.getInstancia().comecarPartida();
			new Salvar();
			salvarJogo();
			Territorios.getInstancia();
			carregarJogo();
			verificarJogadores();
			verificarTerritorios();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void verificarJogadores() {
		for (int i = 0; i < jogadores.getQtdJogadores(); i++) {
			if((jogadores.selectJogadorByIndex(i).getNome().length()) <= 0 && (jogadores.selectJogadorByIndex(i).getCor().toString().length() <=0))
				System.out.println("Erro inicializando jogador");
		}
		System.out.println("Inicializou suave jogador");
	}
	private static void verificarTerritorios() {
		// TODO Auto-generated method stub
		for (int i = 0; i < territorios.getSize(); i++) {
			if((territorios.getInstancia().selectTerritorioByIndex(i).getTropas() <= 0) && (territorios.getInstancia().selectTerritorioByIndex(i).Nome.length() <=0) && (territorios.getInstancia().selectTerritorioByIndex(i).getDono() == null) ){
				System.out.println("Erro incializando territorios");
			}
		}
		System.out.println("Inicializou suave territorios");
	}
}
