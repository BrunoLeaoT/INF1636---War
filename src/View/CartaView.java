package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import Controller.CartaController;
import Controller.TabuleiroController;

@SuppressWarnings("serial")
public class CartaView extends JFrame
{
	public final int LARG_DEFAULT=500;
	public final int ALT_DEFAULT=200;
	
	private CartaController viewController;
	
	public static void main(String[] args) 
	{
		CartaView o = new CartaView(new CartaController());
		o.setVisible(true);
	}
	
	public CartaView(CartaController controller)
	{		
		// props
		viewController = controller;
		this.setTitle("Cartas");
		this.setSize(LARG_DEFAULT, ALT_DEFAULT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		
		// objects
		addTable();
		addBotaoTroca();
		addBotaoOk();
	}
	
	private void addTable()
	{
		String[] nomesColunas = {"Nome país", "Forma"};
		String[][] tableData = viewController.tryGetCartas();		
		
		JTable tabelaCartas = new JTable(tableData, nomesColunas);
		tabelaCartas.setBounds(25, 25, 430, 80);
		
		this.add(tabelaCartas);		
	}
	
	private void addBotaoTroca()
	{
		JButton botaoBotaoTroca = new JButton("Fazer troca"); 
		botaoBotaoTroca.setBounds(100, 120, 100, 25);
		botaoBotaoTroca.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		try 
        		{
        			System.out.println("Fazer troca");
        			viewController.tryFazerTroca();
        			JOptionPane.showMessageDialog(new JFrame(), "Troca realizada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        			dispose();
        		}
        		catch(Exception ex)
        		{
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
		
		this.add(botaoBotaoTroca);
	}
	
	private void addBotaoOk()
	{
		JButton botaoBotaoOk = new JButton("Ok"); 
		botaoBotaoOk.setBounds(300, 120, 50, 25);
		botaoBotaoOk.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		try 
        		{
        			System.out.println("Ok");
        			dispose();
        		}
        		catch(Exception ex)
        		{
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
		this.add(botaoBotaoOk);
	}
}
