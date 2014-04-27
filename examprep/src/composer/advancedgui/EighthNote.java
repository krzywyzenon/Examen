package composer.advancedgui;

import java.awt.Graphics;

public class EighthNote extends NoteDrawing {

	private static final long serialVersionUID = -6192915439494680655L;
	int beakFromX;
	int beakFromY;
	int[] xPoints;
	int[] yPoints;
	
	
	
	public EighthNote()
	{
		xPoints = new int[3];
		yPoints = new int[3];
	}
	
	public EighthNote(int bFX, int bFY, boolean checkForC)
	{
		xPoints = new int[3];
		yPoints = new int[3];
		this.ballFromX = bFX;
		this.ballFromY = bFY;
		this.stickFromX= this.ballFromX + 15;
		this.stickFromY = this.ballFromY - 40;
		this.xPoints[0] = this.stickFromX + 15;
		this.xPoints[1] = this.stickFromX + 10;
		this.xPoints[2] = this.stickFromX + 10;
		this.yPoints[0] = this.stickFromY + 8; 
		this.yPoints[1] = this.stickFromY + 6;
		this.yPoints[2] = this.stickFromY;
		this.checkIfNoteIsC = checkForC;
		setCParameters();
		
	}
	@Override
	public void paintComponent(Graphics g) {
		g.fillOval(ballFromX, ballFromY, 20, 15);
		g.fillRect(stickFromX, stickFromY , 5, 50);
		g.fillRect(stickFromX, stickFromY , 10, 5);
		g.fillPolygon(xPoints, yPoints, 3);
		paintShortLine(g);
	}

	@Override
	public void setParameters(int bFX, int bFY, boolean checkC) {
		this.ballFromX = bFX;
		this.ballFromY = bFY;
		this.stickFromX= this.ballFromX + 15;
		this.stickFromY = this.ballFromY - 40;
		this.xPoints[0] = this.stickFromX + 15;
		this.xPoints[1] = this.stickFromX + 10;
		this.xPoints[2] = this.stickFromX + 10;
		this.yPoints[0] = this.stickFromY + 8; 
		this.yPoints[1] = this.stickFromY + 6;
		this.yPoints[2] = this.stickFromY;
		this.checkIfNoteIsC = checkC;
		setCParameters();

	}

}
