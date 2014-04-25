package composer.advancedgui;

import java.awt.Graphics;

class QuarterNote extends NoteDrawing
{
	
	private static final long serialVersionUID = 1L;
	//int ballFromX;
	//int ballFromY;
	//int stickFromX;
	//int stickFromY;
	
	public QuarterNote()
	{
		
	}
	
	public QuarterNote(int bFX, int bFY)
	{
		this.ballFromX = bFX;
		this.ballFromY = bFY;
		this.stickFromX= this.ballFromX + 15;
		this.stickFromY = this.ballFromY - 40;
	}
	
	public void paintComponent(Graphics g)
	{
		g.fillOval(ballFromX, ballFromY, 20, 15);
		g.fillRect(stickFromX, stickFromY , 5, 50);
	}
	
	public void setParameters(int bFX, int bFY)
	{
		this.ballFromX = bFX;
		this.ballFromY = bFY;
		this.stickFromX= this.ballFromX + 15;
		this.stickFromY = this.ballFromY - 40;
	}
	
}