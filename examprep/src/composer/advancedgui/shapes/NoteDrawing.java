package composer.advancedgui.shapes;

import java.awt.Graphics;

import javax.swing.JComponent;

import composer.advancedgui.GuiHelper;
import composer.advancedgui.Staff;

public abstract class NoteDrawing extends JComponent
{
	private static final long serialVersionUID = 1L;
	public static final boolean CHECK = true;
	public static final boolean SKIP_CHECK = false;
	boolean checkIfNoteIsLow = false;
	protected boolean inverted = false;
	protected Integer staff;

	int ballFromX;
	int ballFromY;
	int stickFromX;
	int stickFromY;
	
	int coordinateForCNote;
	int coordinateForB1Note;
	int coordinateForA1Note;
	
	int hiddenLineCoord;
	int a1LineCoord;
	
	public void paintComponent(Graphics g)
	{
		if(inverted)
		{
			inverted(g);
		}
		else
		{
			normal(g);
		}
	}
	
	public abstract void normal(Graphics g);
	
	public abstract void inverted(Graphics g);
	
	public abstract void setParameters(int bFX, int bFY, boolean checkC);
	
	public void paintShortLine(Graphics g)
	{
		if(checkIfNoteIsLow && (this.ballFromY == this.coordinateForCNote || this.ballFromY == this.coordinateForB1Note))
		{
			g.drawLine(ballFromX - 5,hiddenLineCoord, ballFromX + 25, hiddenLineCoord);			
		}
		else if(checkIfNoteIsLow && this.ballFromY == this.coordinateForA1Note)
		{
			g.drawLine(ballFromX - 5,hiddenLineCoord, ballFromX + 25, hiddenLineCoord);			
			g.drawLine(ballFromX - 5,a1LineCoord, ballFromX + 25, a1LineCoord);			
		}
	}

	public int getBallFromY()
	{
		return ballFromY;
	}
	public int getBallFromX()
	{
		return ballFromX;
	}
	
	public int getCoordinateForCNote()
	{
		return coordinateForCNote;
	}
	
	public boolean getCheckIfC()
	{
		return checkIfNoteIsLow;
	}
	
	public void setLowParameters()
	{
		this.coordinateForA1Note = (2 * Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff()) + 11 * Staff.getSpaceBetweenRows()) / 2 + 2;
		this.coordinateForCNote = (2 * Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff()) + 9 * Staff.getSpaceBetweenRows()) / 2 + 2;
		this.coordinateForB1Note = (Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff()) + 5 * Staff.getSpaceBetweenRows());
		this.hiddenLineCoord = GuiHelper.getCLineCoord();
		this.a1LineCoord = GuiHelper.getA1LineCoord();
	}
	public boolean isInverted() {
		return inverted;
	}
	
	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

	public Integer staffForThisNote() {
		return staff;
	}
	
}
