package View;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import Controller.DadosPraView;
import Controller.DistribuicaoExercito;
import Controller.Jogada;
import Controller.Salvamento;
public class NovoJogo extends JFrame{
	public final int LARG_DEFAULT=400;
	public final int ALT_DEFAULT=800;
	JLabel background;
	JPanel form;
	JComboBox<String> comboBox;
	ArrayList<String> colors;
	JTextField textField;
	String corSendoEscolhida;
	Map<String, String> jogadores ;
	DadosPraView dadosPraView;
	Jogada jogada;
	Salvamento salvamento;
	public NovoJogo() {
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addColors();
		addInterface();
		jogadores = new HashMap<String, String>();
		dadosPraView = DadosPraView.getDados();
		jogada = Jogada.getJogada();
		salvamento = new Salvamento();
	}
	
	public void addColors() {
		colors = new ArrayList<String>();
		colors.add("Amarelo");
		colors.add("Verde");
		colors.add("Preto");
		colors.add("Azul");
		colors.add("Branco");
		colors.add("Vermelho");
	}
	
	public void removeColor(String cor) {
		int index = colors.indexOf(cor);
		colors.remove(index);
	}
	
	public static void main(String[] args) {
		NovoJogo f=new NovoJogo();
		f.setTitle("Novo Jogo");
		f.setVisible(true);

	}
	
	public void addInterface() {
        background=new JLabel(new ImageIcon("images/bgconfiguracao.png"));
        add(background);
        background.setLayout(new FlowLayout());
        makeForm();
        
	}
	
	public void makeForm() {
		form = new JPanel();
		form.setOpaque(false);
		background.add(form);
		form.setLayout(new GridLayout(0,2));
		addInput();
		addComboBox();
        addJogador();
        btnContinuarJogo();
        
		JLabel tip = new JLabel("Jogue com 3 a 6 jogadores");
		tip.setForeground(Color.white);
		tip.setBounds(65, 31, 46, 14);
        form.add(tip);
        
        iniciarJogo();
	}

	public void addJogador() {
        JButton b1 = new JButton("Adicionar jogador");
        form.add(b1);
        
        b1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(colors.indexOf(corSendoEscolhida) > -1) {
        			if(jogadores.containsKey(textField.getText())) {
        				JOptionPane.showMessageDialog(b1,"Jogador com mesmo nome já existe");
        				return;
        			}
        			
        			jogadores.put(textField.getText(), corSendoEscolhida);
        			removeColor(corSendoEscolhida);
        			boolean ret = dadosPraView.adicionarJogador(textField.getText(), corSendoEscolhida);
        			if(ret)
        				JOptionPane.showMessageDialog(b1,"Inclusão Efetuada");
        			else
        				JOptionPane.showMessageDialog(b1,"Inclusão não efetuada, verifque se o nome ou a cor estão diferentes de outros participantes");
        		}
        		else {
        			JOptionPane.showMessageDialog(b1,"Cor já escolhida");
        		}
        	}
        });
	}
	
	public void btnContinuarJogo() {
		 JButton buttonSalvar = new JButton("Continuar Jogo");
		 buttonSalvar.setBounds(300, 670, 100, 100);
	     form.add(buttonSalvar);
	        
	     buttonSalvar.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 if(salvamento.carregarJogo())
	    			 JOptionPane.showMessageDialog(buttonSalvar, "Jogo carregado com sucesso");
	    		 else
	    			 JOptionPane.showMessageDialog(buttonSalvar, "Não tem jogo salvo");
	    	 }
	     });
	}
	
	public void iniciarJogo() {
        JButton iniciar = new JButton("Iniciar Jogo");
        form.add(iniciar);
        
        iniciar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(jogadores.size() < 3 || jogadores.size() > 6 ) {
        			JOptionPane.showMessageDialog(form,"Número de jogadores inválido");
    				return;	
        		}
        		jogada.setJogadores(jogadores);
        		if(jogada.iniciarJogo()) 
	        		Tabuleiro.main(null);
        		else 
        			JOptionPane.showMessageDialog(form,"Erro ao iniciar jogo");
        	}
		 });
	}
	
	public void addInput() {
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.white);
        lblName.setBounds(200, 200, 46, 14);
        form.add(lblName);
        
        textField = new JTextField();
        textField.setBounds(128, 28, 86, 20);
        form.add(textField);
        textField.setColumns(10);
        
	}
	
	public void addComboBox() {
		comboBox = new JComboBox<String>();
		comboBox.addItem("Cor");
		for (int i = 0; i < colors.size(); i++)
			comboBox.addItem(colors.get(i));
	    comboBox.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent arg0) {
				corSendoEscolhida = (String) comboBox.getSelectedItem();
	        }
	    });
	    comboBox.setBounds(180, 285, 91, 20);
	    JLabel lblName = new JLabel("Cor");
	    lblName.setForeground(Color.white);
        lblName.setBounds(65, 31, 46, 14);
        form.add(lblName);
	    form.add(comboBox);
	}
}
