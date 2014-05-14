package composer.advancedgui.shapes;

import java.awt.Graphics;

import javax.swing.JComponent;

import composer.advancedgui.GuiHelper;
import composer.advancedgui.Staff;

/**
 * 
 * @author Tomek
 * Abstract class from which all the notes' and markings' shapes inherit
 */
public abstract class NoteDrawing extends JComponent
{
	private static final long serialVersionUID = 1L;
	public static final boolean CHECK = true;
	public static final boolean SKIP_CHECK = false;
	
	protected boolean checkIfNoteIsLow = false;
	protected boolean inverted = false;
	protected Integer staff;

	protected int ballFromX;
	protected int ballFromY;
	protected int stickFromX;
	protected int stickFromY;
	
	protected int coordinateForCNote;
	protected int coordinateForB1Note;
	protected int coordinateForA1Note;
	
	protected int coordForRowOfNoteC;
	protected int coordForRowOfNoteLowA;
	
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
		repaint();
	}
	
	public abstract void normal(Graphics g);
	
	public abstract void inverted(Graphics g);
	
	public abstract void setParameters(int bFX, int bFY, boolean checkC);
	
	/**
	 * Method for drawing rows for the low C, B and A notes. These rows are not usually visible and are drawn only if the respective
	 * note appears
	 * @param g
	 */
	public void paintShortLine(Graphics g)
	{
		if(checkIfNoteIsLow && (this.ballFromY == this.coordinateForCNote || this.ballFromY == this.coordinateForB1Note))
		{
			g.drawLine(ballFromX - 5,coordForRowOfNoteC, ballFromX + 25, coordForRowOfNoteC);			
		}
		else if(checkIfNoteIsLow && this.ballFromY == this.coordinateForA1Note)
		{
			g.drawLine(ballFromX - 5,coordForRowOfNoteC, ballFromX + 25, coordForRowOfNoteC);			
			g.drawLine(ballFromX - 5,coordForRowOfNoteLowA, ballFromX + 25, coordForRowOfNoteLowA);			
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
	
	/**
	 * Method sets coordinates for low C, B, A notes and their corresponding rows.
	 */
	public void setLowParameters()
	{
		this.coordinateForA1Note = (2 * Staff.getStaffVerticalBeginningCoordinates().get(Staff.getActiveStaff()) + 11 * Staff.getSpaceBetweenRows()) / 2 + 2;
		this.coordinateForCNote = (2 * Staff.getStaffVerticalBeginningCoordinates().get(Staff.getActiveStaff()) + 9 * Staff.getSpaceBetweenRows()) / 2 + 2;
		this.coordinateForB1Note = (Staff.getStaffVerticalBeginningCoordinates().get(Staff.getActiveStaff()) + 5 * Staff.getSpaceBetweenRows());
		this.coordForRowOfNoteC = GuiHelper.getCLineCoord();
		this.coordForRowOfNoteLowA = GuiHelper.getA1LineCoord();
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
