package View;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import Model.Observador;

@SuppressWarnings("serial")
public class SelecaoLabel extends JLabel implements Observador
{
	public SelecaoLabel()
	{
		this.setText("Clique em um territorio para selecioná-lo");
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
}