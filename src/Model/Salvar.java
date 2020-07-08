package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;

public class Salvar {
	static Jogadores jogadores;
	static Territorios territorios;
	
	public Salvar() {
		jogadores = Jogadores.getInstancia();
		territorios = Territorios.getInstancia();
	}
	
	public static boolean salvarJogo() {
		try {
			String partida = "Jogadores: \n";
			partida += transformarJogadoresEmString();
			partida += "Territorios: \n";
			partida += transformarTerritoriosEmString();
			partida += "RodadaDaVez:"+Partida.getInstancia().getTurnoETrocaEmString();
			executarGravaçãoDados(partida);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	
	private static void fazerJogo(String partida) throws IllegalAccessException {
		String aux[] = partida.split("Territorios: ");
		try {		
			setJogadores(aux[0]);
			String[] territorios = aux[1].split("RodadaDaVez:");
			setTerritorios(territorios[0]);
			setDadosRodada(territorios[1]);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new IllegalAccessException("Não foi possível salvar o jogo");
		}
		
		
	}

	private static void setDadosRodada(String dados) {
		String dadosRodada[] = dados.split("-");
		Partida.getInstancia().setTurnoETroca(Integer.parseInt(dadosRodada[0]), Integer.parseInt(dadosRodada[1].split("\n")[0]));
	}

	private static boolean setJogadores(String jogadores) {
		// TODO Auto-generated method stub
		String aux[] = jogadores.split("\n");
		
		for (int i = 0; i < aux.length; i++) {
			String[] dados = aux[i].split(";");
			try {
				
				System.out.println(dados[0].compareTo("Jogadores:"));
				if(dados[0].compareTo("Jogadores: ") == 0)
					continue;
				String nome = dados[0];
				System.out.println(aux[i]);
				Cor cor = Cor.getCorPorString(dados[1]);
				String obj = dados[2];
				int index =  Integer.parseInt(dados[3]);
				Partida.getInstancia().adicionarJogador(nome, cor);
				Objetivo objetivo = setObjetivo(obj);
				if(objetivo == null)
					throw new IllegalClassFormatException("Objetivo não pode ser construido");
				Jogadores.getInstancia().selectJogadorByIndex(index).setObjetivo(objetivo);
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	private static Objetivo setObjetivo(String obj) {
		String aux[] = obj.split(":");
		if(aux[0].compareTo("Jogador")==0)
			return getObjJogador(aux[1]);
		else if(aux[0].compareTo("Territorio")==0)
			return getObjTerritorio(aux[1]);
		else if(aux[0].compareTo("Continente")==0)
			return getContinente(aux[1]);
		else
			return null;
	}

	private static Objetivo getContinente(String objetivo) {
		String continentes[] = objetivo.split(",");
		Continente conts[] = new Continente[2];
		boolean maisUmQualquer = false;
		if(continentes[0].compareTo("PrecisaOutroQualquer") == 0) {
			continentes[0] = continentes[1];
			continentes[1] = continentes[2];
			continentes[2] = null;
			maisUmQualquer = true;
		}
		for (int i = 0; i < conts.length; i++) {
			if(continentes[0] == null)
				break;
			conts[i] = Continente.getContinentePorString(continentes[i]);
		}
		System.out.println("Continente"  +conts[0]);
		System.out.println(conts[1]);
		return new ObjetivoContinente(maisUmQualquer, conts);
	}

	private static Objetivo getObjTerritorio(String objetivo) {
		String objTerritorio[] = objetivo.split("-");
		if(objTerritorio[1].compareTo("2")==0)
			return new ObjetivoTerritorio(true, Integer.parseInt(objTerritorio[0]));
		else
			return new ObjetivoTerritorio(false, Integer.parseInt(objTerritorio[0]));
	}

	private static Objetivo getObjJogador(String objetivo) {
		return new ObjetivoJogador(Cor.getCorPorString(objetivo));
	}

	private static void setTerritorios(String territorios) throws Exception {
		String aux[] = territorios.split("\n");
		for (int i = 1; i < aux.length; i++) {
			try {
				String[] dados = aux[i].split("-");
				if(dados[0].compareTo("Territorios:") == 0)
					continue;
				String nome = dados[0];
				String dono = dados[1];
				int tropas = Integer.parseInt(dados[2]);
				Territorio terr = Territorios.getInstancia().selectTerritorioByName(nome);
				System.out.println("Dono" + dono);
				if(Jogadores.getInstancia().selectJogadorByName(dono) == null)
					throw new IllegalClassFormatException("Jogador dono do território não existe");
				terr.setDono(Jogadores.getInstancia().selectJogadorByName(dono));
				terr.addTropas(tropas);
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new IllegalAccessError("Erro ao resgatar territorios");
			}
		}
	}
	
	private static String transformarTerritoriosEmString() {
		String territoriosString = "";
		Territorio terr;
		for (int i = 0; i < territorios.getSize(); i++) {
			terr = territorios.selectTerritorioByIndex(i);
			territoriosString += terr.getNome() + "-" + terr.getDono().getNome() + "-" + terr.getTropas() + "-" + i;
			territoriosString += "\n";
		}
		return territoriosString;
	}
	
	private static String transformarJogadoresEmString() {
		String jogadoresString= "";
		Jogador jog;
		for (int i = 0; i < jogadores.getQtdJogadores(); i++) {
			jog = jogadores.selectJogadorByIndex(i);
			jogadoresString += jog.getNome() + ";" + jog.getCor().toString() + ";" + jog.getObjetivo().objetivoEmString() + ";" + i;
			ArrayList<Carta> cartas = jog.getCartas();
			for (int j = 0; j < cartas.size(); j++) {
				jogadoresString += cartas.get(i).getTerritorioNome();
				if( (j +1) < cartas.size())
					jogadoresString += ",";
			}
			
			jogadoresString += "\n";
		}
		return jogadoresString;
	}
	
	public static void main(String[] args) {
		try {
			Partida.getInstancia().adicionarJogador("Bruno Çeãp", Cor.Amarelo);
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
			if((territorios.selectTerritorioByIndex(i).getTropas() <= 0) && (territorios.selectTerritorioByIndex(i).getNome().length() <=0) && (territorios.selectTerritorioByIndex(i).getDono() == null) ){
				System.out.println("Erro incializando territorios");
			}
		}
		System.out.println("Inicializou suave territorios");
	}
}
