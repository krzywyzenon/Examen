package composer.advancedgui.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class HalfNote extends NoteDrawing {

	private static final long serialVersionUID = 1L;

	public HalfNote()
	{
		
	}
	
	public HalfNote(int bFX, int bFY, boolean checkForC)
	{
		this.ballFromX = bFX;
		this.ballFromY = bFY;
		this.stickFromX= this.ballFromX + 17;
		this.stickFromY = this.ballFromY - 40;
		this.checkIfNoteIsC = checkForC;
		setCParameters();
	}
	
	public HalfNote(int bFX, int bFY, boolean checkForC, boolean inverted)
	{
		this.ballFromX = bFX;
		this.ballFromY = bFY;
		this.stickFromX= this.ballFromX + 17;
		this.stickFromY = this.ballFromY - 40;
		this.checkIfNoteIsC = checkForC;
		this.inverted = inverted;
		setCParameters();
	}
	

	@Override
	public void setParameters(int bFX, int bFY, boolean checkForC) {
		this.ballFromX = bFX;
		this.ballFromY = bFY;
		this.stickFromX= this.ballFromX + 17;
		this.stickFromY = this.ballFromY - 40;
		this.checkIfNoteIsC = checkForC;
		setCParameters();

	}

	@Override
	public void normal(Graphics g) 
	{
		Graphics gNew = g.create();
		Graphics2D g2D = (Graphics2D) gNew;
		g2D.setStroke(new BasicStroke(4F));
		g2D.drawOval(ballFromX, ballFromY, 20, 15);
		g.fillRect(stickFromX, stickFromY , 5, 50);
		paintShortLine(g);
		
	}

	@Override
	public void inverted(Graphics g) {
		Graphics gNew = g.create();
		Graphics2D g2D = (Graphics2D) gNew;
		g2D.setStroke(new BasicStroke(4F));
		g2D.drawOval(ballFromX, ballFromY, 20, 15);
		g.fillRect(ballFromX + 1, ballFromY + 3 , 5, 50);
		paintShortLine(g);
		
	}

}
