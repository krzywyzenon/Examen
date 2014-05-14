package composer.advancedgui.shapes;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import composer.advancedgui.GuiHelper;
import composer.advancedgui.Staff;

public class FlatMarking extends NoteDrawing {
	
	private static final long serialVersionUID = 1L;
	int fromX;
	int fromY;
	int length = 40;
	
	
	private static final File B_MOLL_FILE = new File("resources/bmoll.png");
	Image bmoll;
	
	public FlatMarking()
	{
		bmoll = GuiHelper.getImage(B_MOLL_FILE);
	}
	
	public FlatMarking(int startX, int startY)
	{
		this.fromX = startX;
		this.fromY = startY;
		this.inverted = false;
		this.staff = Staff.getActiveStaff();
		bmoll = GuiHelper.getImage(B_MOLL_FILE);
	}

	@Override
	public void normal(Graphics g) {
		g.drawImage(bmoll,fromX, fromY,null);

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
