package composer.advancedgui.shapes;

import java.awt.Graphics;

import composer.advancedgui.GuiHelper;
import composer.advancedgui.Staff;

public class RestMarking extends NoteDrawing 
{
	private static final long serialVersionUID = 2322863143779107310L;
	
	Integer fromX;
	Integer fromY;

	public RestMarking()
	{
		this.inverted = false;
	}
	
	public RestMarking(Integer startX, Integer startY)
	{
		this.fromX = startX;
		this.fromY = startY;
		this.inverted = false;
		this.staff = Staff.getActiveStaff();
	}
	@Override
	public void normal(Graphics g) {
		g.drawImage(GuiHelper.getImage(GuiHelper.getRestMarkFile()),fromX, fromY,null);

	}

	@Override
	public void inverted(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setParameters(int bFX, int bFY, boolean checkC) {
		this.fromX = bFX - 10;
		this.fromY = bFY - 35;

	}

}
