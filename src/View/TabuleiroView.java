package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputAdapter;

import Controller.AdicionarJogadoresController;
import Controller.AtaqueController;
import Controller.Salvamento;
import Controller.TabuleiroController;

import Model.Observador;
import Model.Partida;

public class TabuleiroView extends JFrame
{
	//props
	public final int LARG_DEFAULT=1024;
	public final int ALT_DEFAULT=800;
	
	//views
	private TabuleiroController viewController;
	private Salvamento salvamentoController;
	private AtaqueController ataqueController;
	
	// objs
	public SelecaoLabel labelSelecao;
	private ImagePanel painelFundo;
	
	// booleans para controlar alvos
	private boolean clicouBotaoAtacar;
	private boolean clicouBotaoInserirTropas;
	private boolean clicouBotaoRemanejar;
	
	// boolean para contralar momentos da vez do jogador (primeiro ele insere tropas, depois ataca, depois remaneja)
	private boolean acabouFaseAtaque;
	private boolean acabouFaseInserirTropas;
	private boolean acabouFaseRemanejar;
	
	public static void main(String[] args) 
	{
		TabuleiroView t = new TabuleiroView(new TabuleiroController());
		t.setVisible(true);
	}
	
	public TabuleiroView(TabuleiroController controller)
	{
		// props
		viewController = controller;
		salvamentoController = new Salvamento();
		ataqueController = new AtaqueController();
		this.setTitle("Tabuleiro Jogo");
		this.setSize(LARG_DEFAULT, ALT_DEFAULT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		
		// objects
		// panels
		addPainelFundo();

		// botoes
		addBotaoPassarTurno();
		addBotaoSalvarJogo();
		addBotaoVerObjetivo();
		addBotaoVerCartas();
		addBotaoAtacar();
		addBotaoAddTropas();
		addBotaoRemanejar();
		
		//labels
		addSelecaoLabel();
		// label de vez
		// label de tropas disponiveis
		// label de territorios
		
		// listeners
		addMouseClickListenerToPanel();
	}
	
	// panels
	private void addPainelFundo()
	{
		String caminhoImagemMar = "/images/war_tabuleiro_fundo.png";
		String caminhoImagemMapa = "/images/war_tabuleiro_mapa copy.png";
		painelFundo = new ImagePanel(caminhoImagemMar, caminhoImagemMapa);
		this.add(painelFundo);
	}
	
	// butoes
	private void addBotaoPassarTurno()
	{
		JButton botaoPassarTurno = new JButton("Acabar turno"); 
		botaoPassarTurno.setBounds((LARG_DEFAULT/2) - 105, 625, 210, 50);
		botaoPassarTurno.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		try 
        		{
        			System.out.println("Passar vez");
        			// controller
        		}
        		catch(Exception ex)
        		{
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        painelFundo.add(botaoPassarTurno);
	}
	
	private void addBotaoSalvarJogo()
	{
		JButton botaoSalvarJogo = new JButton("Salvar jogo"); 
		botaoSalvarJogo.setBounds(10, 10, 100, 25);
		botaoSalvarJogo.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		try 
        		{
        			System.out.println("Salvar jogo");
        			System.out.println(salvamentoController.salvarJogo());
        		}
        		catch(Exception ex)
        		{
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        painelFundo.add(botaoSalvarJogo);
	}
	
	private void addBotaoVerObjetivo()
	{
		JButton botaoVerObjetivo = new JButton("Ver Objetivo"); 
		botaoVerObjetivo.setBounds(890, 10, 110, 25);
		botaoVerObjetivo.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		try 
        		{
        			System.out.println("Ver objetivo");
        			viewController.tryShowObjetivo();
        		}
        		catch(Exception ex)
        		{
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        painelFundo.add(botaoVerObjetivo);
	}
	
	private void addBotaoVerCartas()
	{
		JButton botaoVerCartas = new JButton("Ver Cartas"); 
		botaoVerCartas.setBounds(890, 45, 110, 25);
		botaoVerCartas.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		try 
        		{
        			System.out.println("Ver cartas");
        			viewController.tryShowCartas();
        		}
        		catch(Exception ex)
        		{
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        painelFundo.add(botaoVerCartas);
	}
	
	private void addBotaoAtacar()
	{
		JButton botaoAtacar = new JButton("Atacar"); 
		botaoAtacar.setBounds(18, 667, 115, 60);
		botaoAtacar.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		try 
        		{
        			System.out.println("Atacar");
        			clicouBotaoAtacar = true;
        			clicouBotaoRemanejar = false;
        			clicouBotaoInserirTropas = false;
        		}
        		catch(Exception ex)
        		{
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        painelFundo.add(botaoAtacar);
	}
	
	private void addBotaoAddTropas()
	{
		JButton botaoAddTropas = new JButton("Adicionar tropas"); 
		botaoAddTropas.setBounds(138, 667, 200, 25);
		botaoAddTropas.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		try 
        		{
        			System.out.println("Adicionar tropas");
        			clicouBotaoAtacar = false;
        			clicouBotaoRemanejar = false;
        			clicouBotaoInserirTropas = true;
        		}
        		catch(Exception ex)
        		{
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        painelFundo.add(botaoAddTropas);
	}
	
	private void addBotaoRemanejar()
	{
		JButton botaoRemanejar = new JButton("Remanejar tropas"); 
		botaoRemanejar.setBounds(138, 702, 200, 25);
		botaoRemanejar.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		try 
        		{
        			System.out.println("Remanejar tropas");
        			clicouBotaoAtacar = false;
        			clicouBotaoRemanejar = true;
        			clicouBotaoInserirTropas = false;
        		}
        		catch(Exception ex)
        		{
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        painelFundo.add(botaoRemanejar);
	}
	
	// labels
	private void addSelecaoLabel()
	{
		labelSelecao = new SelecaoLabel();
		labelSelecao.setBounds((this.LARG_DEFAULT/2) - 300, 15, 600, 30);
		viewController.addObservadorPartidaSelecaoLabel(labelSelecao);
		painelFundo.add(labelSelecao);
	}
	
	// listeners
	private void addMouseClickListenerToPanel()
	{
		this.painelFundo.addMouseListener(new MouseInputAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int coordX = e.getX();
				int coordY = e.getY();
				
				System.out.println("PENIS");
				
				try 
				{
				// se nao esta realizando nenhuma acao, selecioan o terr clicado
				if(!clicouBotaoAtacar && !clicouBotaoRemanejar && !clicouBotaoInserirTropas)
					viewController.trySelecionarTerritorio(coordX, coordY);
				else if(clicouBotaoAtacar)
					ataqueController.tryRealizarAtaque(coordX, coordY);
				else if(clicouBotaoRemanejar)
					//ataqueController.tryRealizarAtaque(coordX, coordY);
					;
				else if(clicouBotaoInserirTropas)
					//ataqueController.tryRealizarAtaque(coordX, coordY);
					;
				
				clicouBotaoAtacar = false;
    			clicouBotaoRemanejar = true;
    			clicouBotaoInserirTropas = false;
				}
				catch(Exception ex)
        		{
					System.out.println(ex.toString());
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
			}
		});
	}
	
}
