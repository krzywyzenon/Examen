package composer.advancedgui;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import composer.advancedgui.shapes.EighthNote;
import composer.advancedgui.shapes.FullNote;
import composer.advancedgui.shapes.HalfNote;
import composer.advancedgui.shapes.NoteDrawing;
import composer.advancedgui.shapes.QuarterNote;
import composer.advancedgui.shapes.SharpMarking;

public class Page extends JComponent 
{
	
	private boolean isActive;
	private int pageNumber = 1;
	
	private static final long serialVersionUID = 1L;
	private static final EighthNote EIGHTH_NOTE = new EighthNote(GuiHelper.getFirstBoxStartingPoint() + 8, 62, NoteDrawing.SKIP_CHECK);
	private static final QuarterNote QUARTER_NOTE = new QuarterNote(GuiHelper.getSecondBoxStartingPoint() + 10, 62, NoteDrawing.SKIP_CHECK); 
	private static final HalfNote HALF_NOTE = new HalfNote(GuiHelper.getThirdBoxStartingPoint() + 10, 60, NoteDrawing.SKIP_CHECK);
	private static final FullNote FULL_NOTE = new FullNote(GuiHelper.getFourthBoxStartingPoint() + 10, 60, NoteDrawing.SKIP_CHECK);
	private static final SharpMarking SHARP_MARKING = new SharpMarking(GuiHelper.getFifthBoxStartingPoint() + 15,30);
	
	Staff firstStaff = new Staff(Staff.getStaffBeginningCoordinates().get(1), Staff.VIOLIN_KEY);
	Staff secondStaff = new Staff(Staff.getStaffBeginningCoordinates().get(2), Staff.NO_VIOLIN_KEY);
	Staff thirdStaff = new Staff(Staff.getStaffBeginningCoordinates().get(3), Staff.NO_VIOLIN_KEY);
	
	private final List<NoteDrawing> drawnNotes = new ArrayList<NoteDrawing>();
	
	public Page()
	{
		isActive = true;
	}
	public Page(int page)
	{
		isActive = true;
		this.pageNumber = page;
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(GuiHelper.getImage(GuiHelper.getPageBackgroundFile()), 0, 0, null);
		firstStaff.paintComponent(g);
		g.drawLine(150, Staff.getStaffBeginningCoordinates().get(1), 150, Staff.getStaffBeginningCoordinates().get(1) + 4 * Staff.getSpaceBetweenRows());
		g.drawLine(152, Staff.getStaffBeginningCoordinates().get(1), 152, Staff.getStaffBeginningCoordinates().get(1) + 4 * Staff.getSpaceBetweenRows());
		
		g.drawRect(GuiHelper.getFirstBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		g.drawRect(GuiHelper.getSecondBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		g.drawRect(GuiHelper.getThirdBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		g.drawRect(GuiHelper.getFourthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		g.drawRect(GuiHelper.getFifthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		
		g.drawString("Page: " + pageNumber, 500, 50);
		
		EIGHTH_NOTE.paintComponent(g);
		QUARTER_NOTE.paintComponent(g);
		HALF_NOTE.paintComponent(g);
		FULL_NOTE.paintComponent(g);
		
		SHARP_MARKING.paintComponent(g);
		
		secondStaff.paintComponent(g);
		
		thirdStaff.paintComponent(g);
		
		for(NoteDrawing nD : drawnNotes)
		{
			nD.paintComponent(g);
		}
	}

	public List<NoteDrawing> getDrawnNotes() {
		return drawnNotes;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
