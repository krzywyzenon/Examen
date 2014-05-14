package composer.advancedgui.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import composer.advancedgui.Staff;


public class FullNote extends NoteDrawing 
{
	private static final long serialVersionUID = 1L;

	public FullNote()
	{
		
	}
	
	public FullNote(int bFX, int bFY, boolean checkForC)
	{
		this.ballFromX = bFX;
		this.ballFromY = bFY;
		this.checkIfNoteIsLow = checkForC;
		setLowParameters();
		this.staff = Staff.getActiveStaff();
	}

	@Override
	public void setParameters(int bFX, int bFY, boolean checkForC) {
		this.ballFromX = bFX;
		this.ballFromY = bFY;
		this.checkIfNoteIsLow = checkForC;
		setLowParameters();
	}

	@Override
	public void normal(Graphics g) {
		Graphics gNew = g.create();
		Graphics2D g2D = (Graphics2D) gNew;
		g2D.setStroke(new BasicStroke(4F));
		g2D.drawOval(ballFromX, ballFromY, 20, 15);
		paintShortLine(g);
		
	}

	@Override
	public void inverted(Graphics g) {
		//Never used as the note's graphic representation is a simple oval
		System.out.println("Inveerted");
	}
	
	public void setInverted(boolean inverted)
	{
		this.inverted = false;
	}

}
