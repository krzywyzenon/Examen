package composer.advancedgui.shapes;

import java.awt.Graphics;


public class QuarterNote extends NoteDrawing
{
	
	private static final long serialVersionUID = 1L;
	//int ballFromX;
	//int ballFromY;
	//int stickFromX;
	//int stickFromY;
	
	public QuarterNote()
	{
		
	}
	
	public QuarterNote(int bFX, int bFY, boolean checkForC)
	{
		this.ballFromX = bFX;
		this.ballFromY = bFY;
		this.stickFromX= this.ballFromX + 15;
		this.stickFromY = this.ballFromY - 40;
		this.checkIfNoteIsC = checkForC;
		setCParameters();
	}
	
	public void paintComponent(Graphics g)
	{
		g.fillOval(ballFromX, ballFromY, 20, 15);
		g.fillRect(stickFromX, stickFromY , 5, 50);
		paintShortLine(g);
	}
	
	public void setParameters(int bFX, int bFY, boolean checkForC)
	{
		this.ballFromX = bFX;
		this.ballFromY = bFY;
		this.stickFromX= this.ballFromX + 15;
		this.stickFromY = this.ballFromY - 40;
		this.checkIfNoteIsC = checkForC;
		setCParameters();
	}
	
}