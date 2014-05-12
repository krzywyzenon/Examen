package composer.advancedgui.shapes;

import java.awt.Graphics;

import composer.advancedgui.Staff;


public class EighthNote extends NoteDrawing {

	private static final long serialVersionUID = -6192915439494680655L;
	int beakFromX;
	int beakFromY;
	int[] xPoints;
	int[] yPoints;
	int[] invXPoints;
	int[] invYPoints;
	
	
	
	public EighthNote()
	{
		xPoints = new int[3];
		yPoints = new int[3];
		invXPoints = new int[3]; 
		invYPoints = new int[3]; 
		this.staff = Staff.getActiveStaff();
	}
	
	public EighthNote(int bFX, int bFY, boolean checkForC)
	{
		xPoints = new int[3];
		yPoints = new int[3];
		invXPoints = new int[3]; 
		invYPoints = new int[3]; 
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
		this.checkIfNoteIsLow = checkForC;
		setLowParameters();
		this.staff = Staff.getActiveStaff();
		
	}
	public EighthNote(int bFX, int bFY, boolean checkForC, boolean inverted)
	{
		xPoints = new int[3];
		yPoints = new int[3];
		invXPoints = new int[3]; 
		invYPoints = new int[3]; 
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
		this.invXPoints[0] = this.ballFromX + 15;
		this.invXPoints[1] = this.ballFromX + 10;
		this.invXPoints[2] = this.ballFromX + 10;
		this.invYPoints[0] = this.ballFromY + 44;
		this.invYPoints[1] = this.ballFromY + 54;
		this.invYPoints[2] = this.ballFromY + 48;
		this.checkIfNoteIsLow = checkForC;
		this.inverted = inverted;
		setLowParameters();
		this.staff = Staff.getActiveStaff();
		
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
		this.invXPoints[0] = this.ballFromX + 15;
		this.invXPoints[1] = this.ballFromX + 10;
		this.invXPoints[2] = this.ballFromX + 10;
		this.invYPoints[0] = this.ballFromY + 44;
		this.invYPoints[1] = this.ballFromY + 54;
		this.invYPoints[2] = this.ballFromY + 48;
		this.checkIfNoteIsLow = checkC;
		setLowParameters();

	}

	@Override
	public void normal(Graphics g) {
		g.fillOval(ballFromX, ballFromY, 20, 15);
		g.fillRect(stickFromX, stickFromY , 5, 50);
		g.fillRect(stickFromX, stickFromY , 10, 5);
		g.fillPolygon(xPoints, yPoints, 3);
		paintShortLine(g);
	}

	@Override
	public void inverted(Graphics g) {
		g.fillOval(ballFromX, ballFromY, 20, 15);
		g.fillRect(ballFromX + 1, ballFromY + 3, 5, 50);
		g.fillRect(ballFromX + 1, ballFromY + 48, 10, 5);
		g.fillPolygon(invXPoints, invYPoints, 3);
		paintShortLine(g);
		
	}

}
