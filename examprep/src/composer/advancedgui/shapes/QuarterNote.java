package composer.advancedgui.shapes;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class QuarterNote extends NoteDrawing
{
	
	private static final long serialVersionUID = 1L;

	
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
	public QuarterNote(int bFX, int bFY, boolean checkForC, boolean inverted)
	{
		this.ballFromX = bFX;
		this.ballFromY = bFY;
		this.stickFromX= this.ballFromX + 15;
		this.stickFromY = this.ballFromY - 40;
		this.checkIfNoteIsC = checkForC;
		this.inverted = inverted;
		setCParameters();
		System.out.println("Create");
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
	
	public void normal(Graphics g)
	{
		g.fillOval(ballFromX, ballFromY, 20, 15);
		g.fillRect(stickFromX, stickFromY , 5, 50);
		paintShortLine(g);
	}
	
	public void inverted(Graphics g)
	{
		g.fillOval(ballFromX, ballFromY, 20, 15);
		g.fillRect(ballFromX + 1, ballFromY + 3 , 5, 50);
		paintShortLine(g);
		
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Hello world");
	}
	
}