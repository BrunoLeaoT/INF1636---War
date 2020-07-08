package View;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import Model.Observador;

@SuppressWarnings("serial")
public class JogadorDaVezLabel extends JLabel implements Observador
{
	public JogadorDaVezLabel(String textoInicial)
	{
		this.setText(textoInicial);
		this.setLayout(null);
		this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setHorizontalAlignment(CENTER);
        setOpaque(false);
	}
	
	@Override
    protected void paintComponent(Graphics g)
	{
        super.paintComponent(g);
    }
	
	@Override
	public void atualizarObservacao(Object o)
	{
		if(o instanceof String)
		{
			this.setText((String)o);
		}
	}
	
	public void toggleVisibility()
	{
		if(this.isVisible())
			this.setVisible(false);
		else
			this.setVisible(true);
	}
}