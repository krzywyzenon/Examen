package composer.advancedgui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	Image img;
	
	public BackgroundPanel()
	{
		img = null;
	}
	
	public BackgroundPanel(Image image)
	{
		this.img = image;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
	}
	
	public void paintComponent(Graphics g)
	{
		if(img != null){
			
			g.drawImage(img, 0, 0, null);
		}
		else
		{
			super.paintComponent(g);
			
		}
	}

}
