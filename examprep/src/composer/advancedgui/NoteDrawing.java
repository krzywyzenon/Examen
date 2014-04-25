package composer.advancedgui;

import java.awt.Graphics;

import javax.swing.JComponent;

public abstract class NoteDrawing extends JComponent 
{
	
	private static final long serialVersionUID = 1L;
	int ballFromX;
	int ballFromY;
	int stickFromX;
	int stickFromY;
	
	public abstract void paintComponent(Graphics g);
	
	public abstract void setParameters(int bFX, int bFY);


}
