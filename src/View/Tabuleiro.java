package View;
import java.awt.BorderLayout;
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


import Controller.DadosPraView;
import Controller.DistribuicaoExercito;
import Model.Dado;

import static javax.swing.JOptionPane.showMessageDialog;

public class Tabuleiro extends JFrame implements MouseListener  {
	public final int LARG_DEFAULT=1600;
	public final int ALT_DEFAULT=1200;
	JLabel background;
	public Map coords;
	public Map<String, String> tropasPorTerritorios;
	public Map<String, String> donoTerritorio;
	public boolean adicionandoTropas = false;
	JLabel tropasNoTerritorioLabels[] = new JLabel[51];
	String jogadorDaVez = "Bruno";
	int tropasParaSeremAdd = 0;
	DistribuicaoExercito disExercito = new DistribuicaoExercito();
	
	public Tabuleiro() {
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getValoresTabuleiro();
		addBackground();
	}
	
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
	}
	
	public void adicionarBtnTropas() {
		JButton btnAddTropas = new JButton("Add Tropas");
        btnAddTropas.setLocation(0,0);
        btnAddTropas.setBounds(60, 400, 110, 30);
        background.add(btnAddTropas);
        btnAddTropas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int tropas = disExercito.getTropasASeremAdicionadasNaRodada(jogadorDaVez);
        		iniciandoAddExercitos(tropas);
        	}
		 });
	}
	
	public void preencherTropasPorTerritorio() {
		Object[] territorios = tropasPorTerritorios.entrySet().toArray();
		//Inicializar labels
		for(int i = 0; i < tropasNoTerritorioLabels.length; i++) {
			tropasNoTerritorioLabels[i] = new JLabel();
		}
		
		System.out.println(territorios.length);
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
		System.out.println(pontos.get(0));
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
	
	public void iniciandoAddExercitos(int tropas) {
		adicionandoTropas = true;
		tropasParaSeremAdd = tropas;
		showMessageDialog(this, "Vamos começar a adicionar suas tropas da rodada \n Para adicionar uma tropa clique no territorio desejado \n Voce tem direito a: " + tropas + " tropas");
	}
	
	public void adicionarTropa(String nomeTer) {
		if(donoTerritorio.get(nomeTer).compareTo(jogadorDaVez) == 0) {
			showMessageDialog(this, "Uma tropa adicionada ao territorio " + nomeTer);
			tropasParaSeremAdd--;
		}
		else
			showMessageDialog(this, "Territorio não te pertence");
		if(tropasParaSeremAdd == 0) {
			showMessageDialog(this, "Todas Tropas adicionadas");
			adicionandoTropas = false;
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
		if(adicionandoTropas) {
			adicionarTropa(nomeTer);
		}
		else {
			if(nomeTer != null) 
				showMessageDialog(this, nomeTer + ": "+ tropasPorTerritorios.get(nomeTer) +"\n Dono:" + donoTerritorio.get(nomeTer));
		}
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

