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
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import Controller.AtaqueController;
import Controller.DadosPraView;
import Controller.DistribuicaoExercito;
import Controller.Jogada;

import static javax.swing.JOptionPane.showMessageDialog;

public class Tabuleiro extends JFrame implements MouseListener {
	// Variaveis de Interface
	public final int LARG_DEFAULT=1024;
	public final int ALT_DEFAULT=768;
	JLabel background;
	JLabel tropasNoTerritorioLabels[] = new JLabel[51];
	JComboBox<String> cartasComboBox;
	JComboBox<String> comboTerr;
	JButton btnAddTropas;
	JButton btnTerminarRodada;
	JButton btnMoverTropas;
	// Modulos externos
	DistribuicaoExercito disExercito;
	AtaqueController ataque;
	Jogada jogada;
	// Variaveis do jogo
	public Map coords;
	public Map<String, String> tropasPorTerritorios;
	public Map<String, String> donoTerritorio;
	public boolean adicionandoTropas = false;
	public boolean movendoTropas = false;
	String jogadorDaVez = "";
	int tropasParaSeremAdd = 0;
	// Isso é para movimentação de exército
	int []territorioOrigem;
	int []territorioDestino;

	
	public Tabuleiro() {
		disExercito = new DistribuicaoExercito();
		jogada = Jogada.getJogada();
		ataque = new AtaqueController();
		DadosPraView.getDados().setObserver(this);
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getValoresTabuleiro();
		addBackground();
	}
	
	/******************* Relativo a interface **********************/ 
	public void getValoresTabuleiro() {
		coords = DadosPraView.getDados().getCoords();
		tropasPorTerritorios = DadosPraView.getDados().getTropasPorTerritorio();
		donoTerritorio = DadosPraView.getDados().getJogadores();
		try {
			updateComboCartas();			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	public void addBackground() {
        setLayout(new BorderLayout());
        background=new JLabel(new ImageIcon("images/war_tabuleiro_mapa.png"));
        background.setLayout(null);
        add(background);
        adicionarBtnTropas();
        adicionarBtnFimRodada();
        controleDeRodadas();
        mostarComboCartas();
        mostarComboTerritorios();
        adicionarBtnMoverTropas();
	}
	
	public void adicionarBtnTropas() {
		btnAddTropas = new JButton("Add Tropas");
        btnAddTropas.setBounds(0, 570, 110, 30);
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
		btnTerminarRodada.setBounds(110, 570, 110, 30);
        background.add(btnTerminarRodada);
        btnTerminarRodada.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controleDeRodadas();
        	}
        });
	}
	
	public void adicionarBtnMoverTropas() {
		btnMoverTropas = new JButton("Mover tropas");
		btnMoverTropas.setBounds(480, 580, 150, 30);
        background.add(btnMoverTropas);
        btnMoverTropas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		setmovendoTropas();
        	}
        });
	}
	
	
	
	public void mostarComboCartas() {
		ArrayList<String> cartas = DadosPraView.getDados().getCartasJogador(jogadorDaVez);
		cartasComboBox = new JComboBox<String>();
		cartasComboBox.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent arg0) {
				
	        }
	    });
		cartasComboBox.setBounds(220, 570, 120, 20);
	    JLabel lblName = new JLabel("Suas cartas");
        lblName.setBounds(220, 540, 120, 20);
        background.add(lblName);
        background.add(cartasComboBox);
        JButton trocaCartas = new JButton("Trocar Cartas");
        trocaCartas.setBounds(220, 600, 120, 20);
        trocaCartas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tropasParaSeremAdd += disExercito.trocaDeCartas(jogadorDaVez);
        		System.out.println(disExercito.trocaDeCartas(jogadorDaVez));
        	}
		 });
        background.add(trocaCartas);
        updateComboCartas();
	}

	public void updateComboCartas() {
		cartasComboBox.removeAllItems();
		ArrayList<String> cartas = DadosPraView.getDados().getCartasJogador(jogadorDaVez);
		for (int i = 0; i < cartas.size(); i++)
			cartasComboBox.addItem(cartas.get(i));
	}
	
	public void mostarComboTerritorios() {
		comboTerr = new JComboBox<String>();
		comboTerr.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent arg0) {
				
	        }
	    });
		comboTerr.setBounds(480, 610, 150, 30);
	    JLabel lblName = new JLabel("Seus territorios");
        lblName.setBounds(380, 610, 100, 20);
        background.add(lblName);
        background.add(comboTerr);
        updateComboTerr();
	}
	
	public void updateComboTerr() {
		Object[] territorios = tropasPorTerritorios.entrySet().toArray();
		comboTerr.removeAllItems();
		for (int i = 0; i < territorios.length; i++) {
			String nomeTer = territorios[i].toString().split("=")[0];
			if(terrPertenceAoJogador(nomeTer))
				comboTerr.addItem(territorios[i].toString());
		}
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
	

	
	
	/*************** Relativo as jogadas ******************/
	
	public void controleDeRodadas() {
		jogadorDaVez = jogada.proximaRodada();
		disExercito.getTropasASeremAdicionadasNaRodada(jogadorDaVez);
		showMessageDialog(this, "Jogador da vez: " + jogadorDaVez);
		btnAddTropas.setVisible(true);
		try {
			updateComboCartas();	
			updateComboTerr();
		} catch (Exception e) {
			System.out.println(e);
		}

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
				updateComboTerr();
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
	
	public void setmovendoTropas() {
		if(movendoTropas)
			movendoTropas = false;
		else {
			showMessageDialog(this, "Iniciamos a movimentação de tropas, clique primeiro no território de origem e depois no de destino. \n Lembre-se que pode apenas territorios com fronteira");
			movendoTropas= true;
		}
		
	}

	public void moverTropas(int [] ponto) {
		if(territorioOrigem == null) {
			territorioOrigem  = new int[2];
			territorioOrigem = ponto;
		}
		else if(territorioDestino == null) {
			territorioDestino  = new int[2];
			territorioDestino = ponto;
			
			String terrOrg = verificarCliqueTerritorio(territorioOrigem[0], territorioOrigem[1]);
			String terrDest = verificarCliqueTerritorio(territorioDestino[0], territorioDestino[1]);
			String message = "Quantas tropas você deseja mover do território ";
			message += terrOrg + "(" + tropasPorTerritorios.get(terrOrg) + ") \n para o território ";
			message += terrDest + "(" + tropasPorTerritorios.get(terrDest) + ") \n";
		    String tropas = JOptionPane.showInputDialog(this,message);
		    // get the user's input. note that if they press Cancel, 'name' will be null
		    System.out.printf("Tropas: " + tropas);
		    territorioDestino = null;
		    territorioOrigem = null;
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
				if(ataque.realizarAtaque(terrAtac, terrDef))
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
		System.out.println(x+":"+y);
		String nomeTer = verificarCliqueTerritorio(x,y);
		if(nomeTer == null) {
			return;
		}
		if(movendoTropas) {
			int []xy = {x,y};
			moverTropas(xy);
		}
		else {
			if(terrPertenceAoJogador(nomeTer)) {
				if(adicionandoTropas) {
					adicionarTropa(nomeTer);
				}
				else {
					if(nomeTer != null) 
						showMessageDialog(this, "Esse territórtio te pertence!\n" + nomeTer + ": "+ tropasPorTerritorios.get(nomeTer));
				}
			}
			else
				criarAtaquePainel(nomeTer);
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

