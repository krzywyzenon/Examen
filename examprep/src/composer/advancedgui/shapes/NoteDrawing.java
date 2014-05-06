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
	boolean checkIfNoteIsC = false;
	protected boolean inverted = false;

	int ballFromX;
	int ballFromY;
	int stickFromX;
	int stickFromY;
	
	int coordinateForCNote;
	
	int hiddenLineCoord;
	
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
		if(checkIfNoteIsC && this.ballFromY == this.coordinateForCNote)
		{
			g.drawLine(ballFromX - 5,hiddenLineCoord, ballFromX + 25, hiddenLineCoord);			
		}
	}

	public int getBallFromY()
	{
		return ballFromY;
	}
	
	public int getCoordinateForCNote()
	{
		return coordinateForCNote;
	}
	
	public boolean getCheckIfC()
	{
		return checkIfNoteIsC;
	}
	
	public void setCParameters()
	{
		this.coordinateForCNote = (2 * Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff()) + 9 * Staff.getSpaceBetweenRows()) / 2 + 2;
		this.hiddenLineCoord = GuiHelper.getHiddenLineCoord();
	}
	public boolean isInverted() {
		return inverted;
	}
	
	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}
}
