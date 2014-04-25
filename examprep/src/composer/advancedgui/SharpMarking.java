package composer.advancedgui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SharpMarking extends NoteDrawing {

	private static final long serialVersionUID = 1L;

	int fromX;
	int fromY;
	int length = 40;
	public SharpMarking()
	{
		
	}
	
	public SharpMarking(int startX, int startY)
	{
		this.fromX = startX;
		this.fromY = startY;
	}
	@Override
	public void paintComponent(Graphics g) 
	{
		Graphics gNew = g.create();
		Graphics2D g2D = (Graphics2D) gNew;
		g2D.setStroke(new BasicStroke(4F));
		g.drawLine(fromX, fromY, fromX, fromY + length);
		g.drawLine(fromX + 5, fromY - 5, fromX + 5, fromY + length - 5);
		g2D.drawLine(fromX - 1, fromY + length/4 + 4, fromX + 7, fromY + length/4);
		g2D.drawLine(fromX - 1, fromY + length/2 + 4 , fromX + 7, fromY + length/2);
	}

	@Override
	public void setParameters(int bFX, int bFY) 
	{
		this.fromX = bFX;
		this.fromY = bFY;
	}

}
