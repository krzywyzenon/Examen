package composer.advancedgui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * 
 * @author Tomek
 * Class is the extenstion of the JPanel, created to enable setting the chosen image as the background
 *
 */
public class BackgroundPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	private Image img;
	
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
