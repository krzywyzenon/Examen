package composer.advancedgui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JMenuBar;

/**
 * 
 * @author Tomek
 * Class is the extenstion of the JMenuBar, created to enable setting the chosen image as the background
 *
 */
public class MyMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	private Image img;
	
	public MyMenuBar()
	{
		img = null;
	}
	
	public MyMenuBar(Image image)
	{
		this.img = image;
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
