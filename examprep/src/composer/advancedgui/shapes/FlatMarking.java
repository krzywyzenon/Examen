package composer.advancedgui.shapes;

import java.awt.Graphics;

import composer.advancedgui.GuiHelper;
import composer.advancedgui.Staff;

public class FlatMarking extends NoteDrawing {
	
	private static final long serialVersionUID = 1L;
	int fromX;
	int fromY;
	int length = 40;
	
	
	public FlatMarking()
	{
		this.inverted = false;
	}
	
	public FlatMarking(int startX, int startY)
	{
		this.fromX = startX;
		this.fromY = startY;
		this.inverted = false;
		this.staff = Staff.getActiveStaff();
	}

	@Override
	public void normal(Graphics g) {
		g.drawImage(GuiHelper.getImage(GuiHelper.getFlatMarkFile()),fromX, fromY,null);

	}

	@Override
	public void inverted(Graphics g) {
		//never used
	}

	@Override
	public void setParameters(int bFX, int bFY, boolean checkC) {
		this.fromX = bFX - 10;
		this.fromY = bFY - 35;

	}

}
