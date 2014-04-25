package composer.advancedgui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class FullNote extends NoteDrawing 
{
	private static final long serialVersionUID = 1L;

	public FullNote()
	{
		
	}
	
	public FullNote(int bFX, int bFY)
	{
		this.ballFromX = bFX;
		this.ballFromY = bFY;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics gNew = g.create();
		Graphics2D g2D = (Graphics2D) gNew;
		g2D.setStroke(new BasicStroke(4F));
		g2D.drawOval(ballFromX, ballFromY, 20, 15);

	}

	@Override
	public void setParameters(int bFX, int bFY) {
		this.ballFromX = bFX;
		this.ballFromY = bFY;
	}

}
