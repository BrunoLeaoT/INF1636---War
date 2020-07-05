package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controller.TabuleiroController;

public class TabuleiroView extends JFrame
{
	public final int LARG_DEFAULT=1024;
	public final int ALT_DEFAULT=800;
	
	private TabuleiroController viewController;
	private ImagePanel painelFundo;
	
	public static void main(String[] args) 
	{
		TabuleiroView t = new TabuleiroView(new TabuleiroController());
		t.setVisible(true);
	}
	
	public TabuleiroView(TabuleiroController controller)
	{
		// props
		viewController = controller;
		this.setTitle("Tabuleiro Jogo");
		this.setSize(LARG_DEFAULT, ALT_DEFAULT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		
		// objects
		addPainelFundo();
		addBotaoPassarTurno();
		addBotaoSalvarJogo();
		addBotaoVerObjetivo();
		addBotaoVerCartas();
		
	}
	
	private void addPainelFundo()
	{
		String caminhoImagemMar = "/images/war_tabuleiro_fundo.png";
		String caminhoImagemMapa = "/images/war_tabuleiro_mapa copy.png";
		painelFundo = new ImagePanel(caminhoImagemMar, caminhoImagemMapa);
		this.add(painelFundo);
	}
	
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
	
}
