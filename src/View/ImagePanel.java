package View;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

// Um JPanel simples que sempre desenha n imagens, uma sobre a outra, todas comecando em (x,y) = (0,0)
// Por default, tem o layout como null
// Por default, tem o tamanho da primeira imagem recebido (é a que fica mais atras; é a background)
// Por default, adiciona um mouse listener pra exibir locais de clique

@SuppressWarnings("serial")
public class ImagePanel extends JPanel
	{
		BufferedImage[] imagensFundo;
		public ImagePanel(String... caminhoImagem)
		{
			// cosntroi array de imgs
			imagensFundo = contruirArrayImagens(caminhoImagem);
			
			// setta tamanho e layout
			this.setLayout(null);
			this.setSize(imagensFundo[0].getWidth(), imagensFundo[0].getHeight());
			
			// add um listener registrar clique
			this.addMouseListener(new MouseInputAdapter()
					{
						@Override
						public void mouseClicked(MouseEvent e) 
						{
						    System.out.println("Último clique: ("+e.getX()+","+e.getY()+")");
						}
					});
		}
		
		private BufferedImage[] contruirArrayImagens(String[] caminhos)
		{
			BufferedImage[] arrayImgs = new BufferedImage[caminhos.length];
			
			for(int i = 0; i < arrayImgs.length; i++)
			{
				try 
				{
					arrayImgs[i] = ImageIO.read(getClass().getResource(caminhos[i]));
				} catch (IOException e) 
				{
					System.out.print("Falha ao carregar a imagem '%s': " + e.getMessage());
					e.printStackTrace();
					System.exit(0);
				}
			}
			
			return arrayImgs;
		}
		
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			for(BufferedImage img : imagensFundo)
			{
				g.drawImage(img, 0, 0, null);
			}
		}
	}