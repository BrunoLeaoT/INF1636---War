package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controller.InicioController;

@SuppressWarnings("serial")
public class InicioView extends JFrame
{
	public final int LARG_DEFAULT=400;
	public final int ALT_DEFAULT=685;
	
	private InicioController viewController;
	private ImagePanel painelFundo;
	
	public static void main(String[] args) 
	{
		InicioView i = new InicioView(new InicioController());
		i.setVisible(true);
	}
	
	public InicioView(InicioController controller)
	{
		// props
		viewController = controller;
		this.setTitle("Inicio Jogo");
		this.setSize(LARG_DEFAULT, ALT_DEFAULT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		
		// objects
		painelFundo = new ImagePanel("/images/bgconfiguracao.png");
		addButtons();
		this.add(painelFundo);
	}
	
	public void addButtons()
	{
		// botao novo jogo
		JButton botaoNovoJogo = new JButton("Novo jogo");
		botaoNovoJogo.setBounds((LARG_DEFAULT/2) - 100, 500, 200, 25);
		botaoNovoJogo.addActionListener(new ActionListener() 
		{
        	public void actionPerformed(ActionEvent e) 
        	{
        		try 
        		{
        			System.out.println("Novo Jogo");
        			viewController.tryConfigurarNovoJogo();
        			dispose();
        		}
        		catch(Exception ex)
        		{
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
        	}
		 });
		painelFundo.add(botaoNovoJogo);
		
		// botao carregar jogo
		JButton botaoCarregarJogo = new JButton("Carregar jogo");
		botaoCarregarJogo.setBounds((LARG_DEFAULT/2) - 100, 535, 200, 25);
		botaoCarregarJogo.addActionListener(new ActionListener() 
		{
        	public void actionPerformed(ActionEvent e) 
        	{
        		try 
        		{
        			System.out.println("Carregar Jogo");
        			// chamar funcao do controller que trata de carregar
        			// e fazer dispose da view atual
        		}
        		catch(Exception ex)
        		{
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
        	}
		 });
		painelFundo.add(botaoCarregarJogo);
	}
}

