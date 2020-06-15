package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import Controller.Ataque;
import Controller.DadosPraView;
import Controller.DistribuicaoExercito;
import Controller.Jogada;
import Model.Dado;

import static javax.swing.JOptionPane.showMessageDialog;

public class Tabuleiro extends JFrame implements MouseListener  {
	public final int LARG_DEFAULT=1600;
	public final int ALT_DEFAULT=1200;
	DistribuicaoExercito disExercito;
	Jogada jogada;
	JLabel background;
	JLabel tropasNoTerritorioLabels[] = new JLabel[51];
	public Map coords;
	public Map<String, String> tropasPorTerritorios;
	public Map<String, String> donoTerritorio;
	public boolean adicionandoTropas = false;
	String jogadorDaVez = "";
	int tropasParaSeremAdd = 0;
	JButton btnAddTropas;
	JButton btnTerminarRodada;
	Ataque ataque;
	
	public Tabuleiro() {
		disExercito = new DistribuicaoExercito();
		jogada = Jogada.getJogada();
		ataque = new Ataque();
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getValoresTabuleiro();
		addBackground();
	}
	
	/** Relativo a interface**/ 
	
	public void getValoresTabuleiro() {
		coords = DadosPraView.getDados().getCoords();
		tropasPorTerritorios = DadosPraView.getDados().getTropasPorTerritorio();
		donoTerritorio = DadosPraView.getDados().getJogadores();
	}

	
	public void addBackground() {
        setLayout(new BorderLayout());
        background=new JLabel(new ImageIcon("images/war_tabuleiro_mapa.png"));
        background.setLayout(null);
        preencherTropasPorTerritorio();
        add(background);
        adicionarBtnTropas();
        adicionarBtnFimRodada();
        controleDeRodadas();
        mostarComboCartas();
	}
	
	public void adicionarBtnTropas() {
		btnAddTropas = new JButton("Add Tropas");
        btnAddTropas.setLocation(0,0);
        btnAddTropas.setBounds(60, 400, 110, 30);
        background.add(btnAddTropas);
        btnAddTropas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int tropas = disExercito.getTropasASeremAdicionadasNaRodada(jogadorDaVez);
        		iniciandoAddExercitos(tropas);
        		btnAddTropas.setVisible(false);
        	}
		 });
	}
	
	public void adicionarBtnFimRodada() {
		btnTerminarRodada = new JButton("Fim Rodada");
		btnTerminarRodada.setLocation(0,200);
		btnTerminarRodada.setBounds(100, 500, 110, 30);
        background.add(btnTerminarRodada);
        btnTerminarRodada.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controleDeRodadas();
        	}
		 });
	}
	
	public void mostarComboCartas() {
		ArrayList<String> cartas = DadosPraView.getDados().getCartasJogador(jogadorDaVez);
		JComboBox<String> comboBox = new JComboBox<String>();
		for (int i = 0; i < cartas.size(); i++)
			comboBox.addItem(cartas.get(i));
	    comboBox.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent arg0) {
				
	        }
	    });
	    comboBox.setBounds(160, 285, 120, 20);
	    JLabel lblName = new JLabel("Suas cartas");
        lblName.setBounds(160, 255, 120, 20);
        background.add(lblName);
        background.add(comboBox);
        JButton trocaCartas = new JButton("Trocar Cartas");
        trocaCartas.setBounds(160, 315, 120, 20);
        trocaCartas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tropasParaSeremAdd += disExercito.trocaDeCartas(jogadorDaVez);
        		System.out.println(disExercito.trocaDeCartas(jogadorDaVez));
        	}
		 });
        background.add(trocaCartas);
	}
	
	public void preencherTropasPorTerritorio() {
		Object[] territorios = tropasPorTerritorios.entrySet().toArray();
		//Inicializar labels
		for(int i = 0; i < tropasNoTerritorioLabels.length; i++) {
			tropasNoTerritorioLabels[i] = new JLabel();
		}
		
		for (int i = 0; i <1; i++) {
			String nomeTer = territorios[i].toString().split("=")[0];
			//System.out.println(nomeTer);
			int []pontoMedio = pegarCentro((ArrayList<Point>) coords.get(nomeTer));
			//System.out.println(pontoMedio[0] + ":" + pontoMedio[1]);
			tropasNoTerritorioLabels[i].setText(tropasPorTerritorios.get(nomeTer));
			tropasNoTerritorioLabels[i].setLocation(pontoMedio[0], pontoMedio[1]);
			//System.out.println(tropasNoTerritorioLabels[i].getX() + ":" + tropasNoTerritorioLabels[i].getY());
			tropasNoTerritorioLabels[i].setSize(10, 10);
			add(tropasNoTerritorioLabels[i]);
		}
	}
	
	public int[] pegarCentro(ArrayList<Point> pontos){
		int []xy = new int[2];
		//int xMedio = pontos.get(0).x + ((pontos.get(3).x -  pontos.get(0).x)/2);
		//int yMedio = pontos.get(1).y + ((pontos.get(2).y -  pontos.get(1).y)/2);
		xy[0] = pontos.get(0).x;
		xy[1] =  pontos.get(0).y;
		
		return xy;
	}
	
	public String verificarCliqueTerritorio(int x, int y) {
		Object[] territorios = tropasPorTerritorios.entrySet().toArray();
		
		for (int i = 0; i < territorios.length; i++) {
			String nomeTer = territorios[i].toString().split("=")[0];
			if(checarLocalEmCoord((ArrayList<Point>) coords.get(nomeTer), x, y, nomeTer)){
				return nomeTer;
			}
		}
		return null;
	}
	
	private boolean checarLocalEmCoord(ArrayList<Point> object, int x, int y, String nome) {
		if(x >= object.get(0).x && x <= object.get(1).x && x >= object.get(2).x && x <= object.get(3).x) {
			if(y >= object.get(0).y && y >= object.get(1).y && y <= object.get(2).y && y <= object.get(3).y) {
				return true;
			}
		}
		return false;
	}
	

	
	
	/* Relativo as jogadas*/
	public void controleDeRodadas() {
		jogadorDaVez = jogada.proximaRodada();
		disExercito.getTropasASeremAdicionadasNaRodada(jogadorDaVez);
		showMessageDialog(this, "Jogador da vez: " + jogadorDaVez);
		btnAddTropas.setVisible(true);
		
	}
	
	public void iniciandoAddExercitos(int tropas) {
		adicionandoTropas = true;
		tropasParaSeremAdd = tropas;
		showMessageDialog(this, "Vamos começar a adicionar suas tropas da rodada \n Para adicionar uma tropa clique no territorio desejado \n Voce tem direito a: " + tropas + " tropas");
	}
	
	public void adicionarTropa(String nomeTer) {
		if(terrPertenceAoJogador(nomeTer)) {
			try {
				disExercito.adicionandoTropa(nomeTer);
				showMessageDialog(this, "Uma tropa adicionada ao territorio " + nomeTer);
				tropasParaSeremAdd--;
			} catch (Exception e) {
				showMessageDialog(this,e.toString());
			}

		}
		else
			showMessageDialog(this, "Territorio não te pertence");
		if(tropasParaSeremAdd == 0) {
			showMessageDialog(this, "Todas Tropas adicionadas");
			adicionandoTropas = false;
		}
	}
	
	public boolean terrPertenceAoJogador(String nomeTer) {
		if(donoTerritorio.get(nomeTer).compareTo(jogadorDaVez) == 0) {
			return true;
		}
		return false;
	}
	
	public void criarAtaquePainel(String nomeTer) {
		Object[] possibilities = {"6", "5", "4","3","2","1"};
		String message =  "Esse territorio (" + nomeTer + ") pertence ao jogador " + donoTerritorio.get(nomeTer) +
                "\n Deseja atacar ele? Selecione o nivel dos dados";
		String title = "Ataque";
		Icon icon = null;
		String s = (String)JOptionPane.showInputDialog(this, message, title,  JOptionPane.PLAIN_MESSAGE, icon, possibilities,possibilities[0]);
		if ((s != null) && (s.length() > 0)) {
			//ataque.ataqueAoOponente(territorioAtacante, territorioDefesa, valor)
			Object[] territorios = tropasPorTerritorios.entrySet().toArray();
			for (int i = 0; i < territorios.length; i++) {
				 territorios[i] = territorios[i].toString().split("=")[0];
			}
			String terr = (String)JOptionPane.showInputDialog(this, "Com qual territorio?", title,  JOptionPane.PLAIN_MESSAGE, icon, territorios,territorios[0]);
			executarAtaque(terr,nomeTer,s);
		}
		
	}
	public void executarAtaque(String terrAtac, String terrDef,String valor) {
		if ((terrAtac != null) && (terrAtac.length() > 0)) {
			try {
				if(ataque.ataqueAoOponente(jogadorDaVez,terrAtac, terrDef, Integer.parseInt(valor)))
					showMessageDialog(this, "Territorio Conquistado");
				else
					showMessageDialog(this, "Territorio não Conquistado");
			} catch (Exception e) {
				showMessageDialog(this, e);
			}

		}
	}
	
	public static void main(String[] args) {
		Tabuleiro f=new Tabuleiro();
		f.setTitle("WARZIN");
		f.setVisible(true);
		f.addMouseListener(f);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x=e.getX(),y=e.getY();
		x-=75;
		y-=75;
		String nomeTer = verificarCliqueTerritorio(x,y);
		if(terrPertenceAoJogador(nomeTer)) {
			if(adicionandoTropas) {
				adicionarTropa(nomeTer);
			}
			else {
				if(nomeTer != null) 
					showMessageDialog(this, nomeTer + ": "+ tropasPorTerritorios.get(nomeTer) +"\n Dono:" + donoTerritorio.get(nomeTer));
			}
		}
		else
			criarAtaquePainel(nomeTer);
	
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	
}

