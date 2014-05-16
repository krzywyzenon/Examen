package composer.advancedgui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

import com.google.common.collect.ImmutableMap;
import composer.advancedgui.shapes.FlatMarking;
import composer.advancedgui.shapes.EighthNote;
import composer.advancedgui.shapes.FullNote;
import composer.advancedgui.shapes.HalfNote;
import composer.advancedgui.shapes.NoteDrawing;
import composer.advancedgui.shapes.QuarterNote;
import composer.advancedgui.shapes.RestMarking;
import composer.advancedgui.shapes.SharpMarking;

/**
 * 
 * @author Tomek
 * The class is the graphic representation of a single page which is shown in the ComposerSheet - thus it contains information about
 * all the staffs and notes which are draw at the given page
 */
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
	private static final FlatMarking FLAT_MARKING = new FlatMarking(GuiHelper.getSixthBoxStartingPoint() + 5,15);
	private static final RestMarking REST_MARKING = new RestMarking(GuiHelper.getSeventhBoxStartingPoint() + 5, 15);
	
	private static Staff firstStaff = new Staff(Staff.getStaffVerticalBeginningCoordinates().get(1), Staff.VIOLIN_KEY);
	private static Staff secondStaff = new Staff(Staff.getStaffVerticalBeginningCoordinates().get(2), Staff.NO_VIOLIN_KEY);
	private static Staff thirdStaff = new Staff(Staff.getStaffVerticalBeginningCoordinates().get(3), Staff.NO_VIOLIN_KEY);
	
	private final List<NoteDrawing> drawnNotes = new ArrayList<NoteDrawing>();
	
	private static final Map<Integer, Staff> STAVES = ImmutableMap.of(
			1, firstStaff,
			2, secondStaff,
			3, thirdStaff);
	
	//This constructor is called only upon creating the first page
	public Page()
	{
		isActive = true;
	}
	public Page(int pageNumber)
	{
		isActive = true;
		this.pageNumber = pageNumber;
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(GuiHelper.getImage(GuiHelper.getPageBackgroundFile()), 0, 0, null);
		firstStaff.paintComponent(g);
		g.drawLine(150, Staff.getStaffVerticalBeginningCoordinates().get(1), 150, Staff.getStaffVerticalBeginningCoordinates().get(1) + 4 * Staff.getSpaceBetweenRows());
		g.drawLine(152, Staff.getStaffVerticalBeginningCoordinates().get(1), 152, Staff.getStaffVerticalBeginningCoordinates().get(1) + 4 * Staff.getSpaceBetweenRows());
		
		g.drawRect(GuiHelper.getFirstBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		g.drawRect(GuiHelper.getSecondBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		g.drawRect(GuiHelper.getThirdBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		g.drawRect(GuiHelper.getFourthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		g.drawRect(GuiHelper.getFifthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		g.drawRect(GuiHelper.getSixthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		g.drawRect(GuiHelper.getSeventhBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		
		g.drawString("Page: " + pageNumber, 500, 50);
		
		EIGHTH_NOTE.paintComponent(g);
		QUARTER_NOTE.paintComponent(g);
		HALF_NOTE.paintComponent(g);
		FULL_NOTE.paintComponent(g);
		
		SHARP_MARKING.paintComponent(g);
		
		FLAT_MARKING.paintComponent(g);
		
		REST_MARKING.paintComponent(g);
		
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
	
	public static Map<Integer, Staff> getStaves() {
		return STAVES;
	}

}
