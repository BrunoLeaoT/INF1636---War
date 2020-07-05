package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Controller.TabuleiroController;

public class ObjetivoView extends JFrame
{
	public final int LARG_DEFAULT=500;
	public final int ALT_DEFAULT=200;
	
	public static void main(String[] args) 
	{
		ObjetivoView o = new ObjetivoView("Lorem ipsum pra carambum an bessa dem montaum");
		o.setVisible(true);
	}
	
	public ObjetivoView(String textoObjetivo)
	{
		// props
		this.setTitle("Objetivo");
		this.setSize(LARG_DEFAULT, ALT_DEFAULT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		
		// objects
		addBotaoOk();
		addTextArea(textoObjetivo);
	}
	
	private void addTextArea(String textoObjetivo)
	{
		JTextArea areaTexto = new JTextArea(textoObjetivo);
		areaTexto.setFont(new Font("Serif", Font.BOLD, 16));
		areaTexto.setLineWrap(true);
		areaTexto.setWrapStyleWord(true);
		areaTexto.setEditable(false);
		areaTexto.setBounds(25, 25, 430, 80);
		this.add(areaTexto);
	}
	
	private void addBotaoOk()
	{
		JButton botaoBotaoOk = new JButton("Ok"); 
		botaoBotaoOk.setBounds((LARG_DEFAULT/2) - 25, 120, 50, 25);
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
