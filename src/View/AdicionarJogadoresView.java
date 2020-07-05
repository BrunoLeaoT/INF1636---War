package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.AdicionarJogadoresController;

@SuppressWarnings("serial")
public class AdicionarJogadoresView extends JFrame
{
	public final int LARG_DEFAULT=350;
	public final int ALT_DEFAULT=150;
	private AdicionarJogadoresController viewController;
	
	private JTextField nameTextField;
	private JComboBox<String> coresComboBox; 
	private String nomeCorEscolhida = "Amarelo"; // primeiro valor eh amarelo, por padrao
	
	public static void main(String[] args) 
	{
		AdicionarJogadoresView aj = new AdicionarJogadoresView(new AdicionarJogadoresController());
		aj.setVisible(true);
	}
	
	public AdicionarJogadoresView(AdicionarJogadoresController controller)
	{
		// props
		viewController = controller;
		this.setTitle("Adicionar jogadores");
		this.setSize(LARG_DEFAULT, ALT_DEFAULT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(0,2));
		this.setResizable(false);
		
		// objects
		addNameField();
		addComboBox();
		addBotaoAddJogador();
		addBotaoComecarJogo();
	}
	
	public void addNameField() {
		JLabel lbl = new JLabel("Nome: ");
		lbl.setForeground(Color.black);
		lbl.setHorizontalAlignment(JLabel.CENTER);
        this.add(lbl);
        
        nameTextField = new JTextField();
        nameTextField.setColumns(10);
        this.add(nameTextField);
        
	}
	
	public void addComboBox() 
	{
		JLabel lbl = new JLabel("Cor: ");
		lbl.setForeground(Color.black);
		lbl.setHorizontalAlignment(JLabel.CENTER);
        this.add(lbl);
		
		coresComboBox = new JComboBox<String>();
		coresComboBox.addItem("Amarelo");
		coresComboBox.addItem("Azul");
		coresComboBox.addItem("Branco");
		coresComboBox.addItem("Preto");
		coresComboBox.addItem("Verde");
		coresComboBox.addItem("Vermelho");
		
		coresComboBox.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent arg0) {
				nomeCorEscolhida = (String) coresComboBox.getSelectedItem();
	        }
	    });
        this.add(coresComboBox);
	}
	
	public void addBotaoAddJogador() 
	{
        JButton botaoAddJogador = new JButton("Adicionar jogador");        
        botaoAddJogador.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		try 
        		{
        			System.out.println("Adicionar Jogador");
        			viewController.tryAddJogador(nameTextField.getText(), nomeCorEscolhida);
        			JOptionPane.showMessageDialog(new JFrame(), "Jogador adicionado", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        		}
        		catch(Exception ex)
        		{
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        this.add(botaoAddJogador);
	}
	
	public void addBotaoComecarJogo() 
	{
        JButton botaoComecarJogo = new JButton("Começar partida!");        
        botaoComecarJogo.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		try 
        		{
        			System.out.println("Comecar Jogo");
        			viewController.tryIniciarPartida();
        			dispose();
        		}
        		catch(Exception ex)
        		{
        			JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        this.add(botaoComecarJogo);
	}
}
