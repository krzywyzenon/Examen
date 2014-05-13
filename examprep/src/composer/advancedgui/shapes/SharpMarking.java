package composer.advancedgui.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import composer.advancedgui.Staff;


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
		this.inverted = false;
		this.staff = Staff.getActiveStaff();
	}

	@Override
	public void setParameters(int bFX, int bFY, boolean c) 
	{
		this.fromX = bFX;
		this.fromY = bFY;
	}

	@Override
	public void normal(Graphics g) 
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
	public void inverted(Graphics g) 
	{
		//Never used - only notes' graphic representations have their inverted versions
	}

}
