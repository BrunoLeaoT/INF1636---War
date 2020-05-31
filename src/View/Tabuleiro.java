package View;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
public class Tabuleiro extends JFrame{
	public final int LARG_DEFAULT=1600;
	public final int ALT_DEFAULT=1200;
	public Tabuleiro() {
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addBackground();
	}
	public static void main(String[] args) {
		Tabuleiro f=new Tabuleiro();
		f.setTitle("WARZIN");
		f.setVisible(true);

	}
	public void addBackground() {
        setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("C:/Users/55219/eclipse-workspace/War/images/war_tabuleiro_mapa.png"));
        add(background);
        background.setLayout(new FlowLayout());
	}
}

