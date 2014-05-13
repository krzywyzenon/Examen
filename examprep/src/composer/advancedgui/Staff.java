package composer.advancedgui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

import com.google.common.collect.ImmutableMap;

/**
 * 
 * @author Tomek
 * Graphic representation of the staff - contains information about rows coordinates, space between rows and if the given staff
 * should have a violin key or not
 */
public class Staff extends JComponent 
{
	public static final boolean VIOLIN_KEY = true;
	public static final boolean NO_VIOLIN_KEY = false;
	
	private static final long serialVersionUID = 1L;
	private static Integer activeStaff = 1;
	private Integer rowsBeginning;
	private static final Integer SPACE_BETWEEN_ROWS = 15;
	private static final Integer ROW_HORIZONTAL_STARTING_POINT = 0;
	private static final Integer ROW_HORIZONTAL_ENDING_POINT = 550;
	private boolean violinKey;
	
	private static final Map<Integer, Integer> STAFF_VERTICAL_BEGINNING_COORDINATES = ImmutableMap.of(
			1, 100,
			2, 250,
			3, 400);
	
	private List<Integer> coordinates = new ArrayList<Integer>();
	
	
	public Staff(Integer beginning, boolean violinKey)
	{
		this.rowsBeginning = beginning;
		this.setViolinKey(violinKey);
		for(int i = 0; i<5; i++)
		{
			coordinates.add(rowsBeginning + i*SPACE_BETWEEN_ROWS);
		}
	}
	
	public void paintComponent(Graphics g)
	{
		if(isViolinKey())
		g.drawImage(GuiHelper.getImage(GuiHelper.getViolinKeyFile()), 0, rowsBeginning -20, 100, 100, null);
		for(Integer coord : coordinates)
		{
			g.drawLine(ROW_HORIZONTAL_STARTING_POINT, coord, ROW_HORIZONTAL_ENDING_POINT, coord);
		}
	}

	public static Integer getActiveStaff() {
		 
		return activeStaff;
	}

	public static void setActiveStaff(Integer activeStaff) {
		Staff.activeStaff = activeStaff;
	}

	public static Map<Integer, Integer> getStaffVerticalBeginningCoordinates() {
		return STAFF_VERTICAL_BEGINNING_COORDINATES;
	}

	public static Integer getSpaceBetweenRows() {
		return SPACE_BETWEEN_ROWS;
	}

	public boolean isViolinKey() {
		return violinKey;
	}

	public void setViolinKey(boolean violinKey) {
		this.violinKey = violinKey;
	}

}
