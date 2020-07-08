package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

import Model.Observador;

@SuppressWarnings("serial")
class territorioLabel extends JLabel implements Observador
{
	public territorioLabel()
	{
		this.setText("1");
		this.setLayout(null);
		this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setHorizontalAlignment(CENTER);
        //this.setSize(50, 40);
        Font font = new Font("Verdana", Font.PLAIN, 8);
        this.setFont(font);
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
			String aux[] = ((String)o).split(";");
			this.setText("<html><span> "+ aux[0] +"-"+aux[1]+ "</span> <br>"+aux[2]+"</html>");
		}
	}
}