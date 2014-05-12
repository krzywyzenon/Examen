package composer.advancedgui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

import com.google.common.collect.ImmutableMap;
import composer.data.MidiDataExtractor;
import composer.data.Tones;

public class GuiHelper
{
	public static final Integer ADD = 0;
	public static final Integer EDIT = 1;
	
	private static final Map<String, Integer> BOXES_STARTPOINTS = ImmutableMap.of(
			"First", 0,
			"Second", 60,
			"Third", 120,
			"Fourth", 180,
			"Fifth", 240);
			
	private static final File VIOLIN_KEY_FILE = new File("resources/violinkey.png");
	private static final File PLAY_KEY_FILE = new File("resources/play.png");
	private static final File NEXT_PAGE_KEY_FILE = new File("resources/nextpage.png");
	private static final File PREVIOUS_PAGE_KEY_FILE = new File("resources/previouspage.png");
	private static final File CLEAR_KEY_FILE = new File("resources/clearbutton.png");
	private static final File SAVES_DIRECTORY = new File("saves");
	private static final File MAIN_BACKGROUND_FILE = new File("resources/wood.png");
	private static final File PANEL_BACKGROUND_FILE = new File("resources/leftwood1.png");
	private static final File PAGE_BACKGROUND_FILE = new File("resources/papyrus.png");
	private static final File DELETE_LAST_NOTE_FILE = new File("resources/deletenote.png");
	private static final File TITLE_BUTTON_FILE = new File("resources/titlebutton.png");
	
	private static final String FIRST = "First";
	private static final String SECOND = "Second";
	private static final String THIRD = "Third";
	private static final String FOURTH = "Fourth";
	private static final String FIFTH = "Fifth";
	
	private static final Integer ORIGINAL_FIRST_ROW_VERTICAL_COORD = 100;
	private static final Integer ORIGINAL_SECOND_ROW_VERTICAL_COORD = 115; 
	private static final Integer ORIGINAL_THIRD_ROW_VERTICAL_COORD = 130; 
	private static final Integer ORIGINAL_FOURTH_ROW_VERTICAL_COORD = 145;
	private static final Integer ORIGINAL_FIFTH_ROW_VERTICAL_COORD = 160; 
	private static final Integer ORIGINAL_SIXTH_ROW_VERTICAL_COORD = 175; 
	
	private static final Integer FIRST_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(FIRST);
	private static final Integer SECOND_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(SECOND);
	private static final Integer THIRD_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(THIRD);
	private static final Integer FOURTH_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(FOURTH);
	private static final Integer FIFTH_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(FIFTH);
	
	private static final Integer BOX_VERTICAL_STARTING_POINT = 10;
	
	private static final Integer BOX_WIDTH = 40;
	private static final Integer BOX_HEIGHT = 70;
	
	private static final Integer B1_TONE = ORIGINAL_SIXTH_ROW_VERTICAL_COORD;
	private static final Integer C_TONE = (ORIGINAL_FIFTH_ROW_VERTICAL_COORD + ORIGINAL_SIXTH_ROW_VERTICAL_COORD)/2 + 2;
	private static final Integer D_TONE = ORIGINAL_FIFTH_ROW_VERTICAL_COORD;
	private static final Integer E_TONE = (ORIGINAL_FOURTH_ROW_VERTICAL_COORD + ORIGINAL_FIFTH_ROW_VERTICAL_COORD)/2 + 2;
	private static final Integer F_TONE = ORIGINAL_FOURTH_ROW_VERTICAL_COORD;
	private static final Integer G_TONE = (ORIGINAL_THIRD_ROW_VERTICAL_COORD + ORIGINAL_FOURTH_ROW_VERTICAL_COORD)/2 + 2;
	private static final Integer A_TONE = ORIGINAL_THIRD_ROW_VERTICAL_COORD;
	private static final Integer B_TONE = (ORIGINAL_SECOND_ROW_VERTICAL_COORD + ORIGINAL_THIRD_ROW_VERTICAL_COORD)/2 + 2;
	private static final Integer C2_TONE = ORIGINAL_SECOND_ROW_VERTICAL_COORD;
	private static final Integer D2_TONE = (ORIGINAL_FIRST_ROW_VERTICAL_COORD + ORIGINAL_SECOND_ROW_VERTICAL_COORD)/2 + 2;
	private static final Integer E2_TONE = ORIGINAL_FIRST_ROW_VERTICAL_COORD;
	private static final Integer F2_TONE = ORIGINAL_FIRST_ROW_VERTICAL_COORD - 8;
	//Sharps
	private static final Integer C_SHARP = C_TONE + 1000;
	private static final Integer D_SHARP = D_TONE + 1000;
	private static final Integer F_SHARP = F_TONE + 1000;
	private static final Integer G_SHARP = G_TONE + 1000;
	private static final Integer A_SHARP = A_TONE + 1000;
	private static final Integer C2_SHARP = C2_TONE + 1000;
	private static final Integer D2_SHARP = D2_TONE + 1000;
	private static final Integer F2_SHARP = F2_TONE + 1000;
	
			
	private static final Map<Integer, MidiDataExtractor> TONES = ImmutableMap.<Integer, MidiDataExtractor>builder()
			.put(B1_TONE, Tones.B1)
			.put(C_TONE, Tones.C)
			.put(C_SHARP, Tones.CIS)
			.put(D_TONE, Tones.D)
			.put(D_SHARP, Tones.DIS)
			.put(E_TONE, Tones.E)
			.put(F_TONE, Tones.F)
			.put(F_SHARP, Tones.FIS)
			.put(G_TONE, Tones.G)
			.put(G_SHARP, Tones.GIS)
			.put(A_TONE, Tones.A)
			.put(A_SHARP, Tones.AIS)
			.put(B_TONE, Tones.B)
			.put(C2_TONE, Tones.C2)
			.put(C2_SHARP, Tones.CIS2)
			.put(D2_TONE, Tones.D2)
			.put(D2_SHARP, Tones.DIS2)
			.put(E2_TONE, Tones.E2)
			.put(F2_TONE, Tones.F2)
			.put(F2_SHARP, Tones.FIS2)
			.build();
	
	private static final Map<Integer, Boolean> SHARP_TONES = new HashMap<Integer,Boolean>();
	static
	{
		SHARP_TONES.put(C_TONE, false);
		SHARP_TONES.put(D_TONE, false);
		SHARP_TONES.put(F_TONE, false);
		SHARP_TONES.put(G_TONE, false);
		SHARP_TONES.put(A_TONE, false);
		SHARP_TONES.put(C2_TONE, false);
		SHARP_TONES.put(D2_TONE, false);
		SHARP_TONES.put(F2_TONE, false);
		
	}
	
	private static final Map<Integer, Boolean> TEMPORARY_SHARP_TONES = new HashMap<Integer,Boolean>();
	static
	{
		TEMPORARY_SHARP_TONES.put(C_TONE, false);
		TEMPORARY_SHARP_TONES.put(D_TONE, false);
		TEMPORARY_SHARP_TONES.put(F_TONE, false);
		TEMPORARY_SHARP_TONES.put(G_TONE, false);
		TEMPORARY_SHARP_TONES.put(A_TONE, false);
		TEMPORARY_SHARP_TONES.put(C2_TONE, false);
		TEMPORARY_SHARP_TONES.put(D2_TONE, false);
		TEMPORARY_SHARP_TONES.put(F2_TONE, false);
		
	}
	private static final Map<Integer, Integer> TONES_TO_SHARP = ImmutableMap.<Integer, Integer>builder()
			.put(C_TONE, C_SHARP)
			.put(D_TONE, D_SHARP)
			.put(F_TONE, F_SHARP)
			.put(G_TONE, G_SHARP)
			.put(A_TONE, A_SHARP)
			.put(C2_TONE, C2_SHARP)
			.put(D2_TONE, D2_SHARP)
			.put(F2_TONE, F2_SHARP)
			.build();

	public static Integer getFirstBoxStartingPoint() {
		return FIRST_BOX_STARTING_POINT;
	}


	public static Integer getSecondBoxStartingPoint() {
		return SECOND_BOX_STARTING_POINT;
	}


	public static Integer getThirdBoxStartingPoint() {
		return THIRD_BOX_STARTING_POINT;
	}


	public static Integer getFourthBoxStartingPoint() {
		return FOURTH_BOX_STARTING_POINT;
	}


	public static Integer getFifthBoxStartingPoint() {
		return FIFTH_BOX_STARTING_POINT;
	}


	public static Integer getBoxVerticalStartingPoint() {
		return BOX_VERTICAL_STARTING_POINT;
	}


	public static Integer getBoxWidth() {
		return BOX_WIDTH;
	}


	public static Integer getBoxHeight() {
		return BOX_HEIGHT;
	}
	
	public static Map<Integer, MidiDataExtractor> getTones() {
		return TONES;
	}


	public static Map<Integer, Boolean> getSharpTones() {
		return SHARP_TONES;
	}


	public static Map<Integer, Boolean> getTemporarySharpTones() {
		return TEMPORARY_SHARP_TONES;
	}


	public static Map<Integer, Integer> getTonesToSharp() {
		return TONES_TO_SHARP;
	}
	
	public static Integer countVerticalCoordinate(Integer verticalPosition, Integer firstRowVertivalCoordinate ,JTextArea text)
	{
		Integer coordinate = null;
		if(verticalPosition >= firstRowVertivalCoordinate - 15 && verticalPosition < firstRowVertivalCoordinate - 5)
		{
			coordinate = firstRowVertivalCoordinate - 8;
		}
		else if(verticalPosition >= firstRowVertivalCoordinate - 5 && verticalPosition < (firstRowVertivalCoordinate + firstRowVertivalCoordinate + Staff.getSpaceBetweenRows())/2)
		{
			coordinate = firstRowVertivalCoordinate;
			text.append("\nfirst");
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + firstRowVertivalCoordinate + Staff.getSpaceBetweenRows())/2 && verticalPosition < (firstRowVertivalCoordinate + Staff.getSpaceBetweenRows()))
		{
			coordinate = (firstRowVertivalCoordinate + firstRowVertivalCoordinate + Staff.getSpaceBetweenRows())/2 + 2;
			text.append("\nsecond half ");
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + Staff.getSpaceBetweenRows()) && verticalPosition < (firstRowVertivalCoordinate + Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows())/2)
		{
			coordinate = (firstRowVertivalCoordinate + Staff.getSpaceBetweenRows());
			text.append("\nsecond");
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows())/2 && verticalPosition < (firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows()))
		{
			coordinate = (firstRowVertivalCoordinate + Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows())/2 + 2;
			text.append("\nthird half");
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows()) && verticalPosition < (firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows())/2)
		{
			coordinate = (firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows());
			text.append("\nthird");
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows())/2 && verticalPosition < (firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows()))
		{
			coordinate = (firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows())/2 + 2;
			text.append("\nfour half");
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows()) && verticalPosition < (firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows())/2)
		{
			coordinate = (firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows());
			text.append("\nfourth");
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows())/2 && verticalPosition < (firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows()))
		{
			coordinate = (firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows())/2 + 2;
			text.append("\nfifth half");
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows()) && verticalPosition < (firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 5*Staff.getSpaceBetweenRows())/2)
		{
			coordinate = firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows();
			text.append("\nfifth");
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 5*Staff.getSpaceBetweenRows())/2 && verticalPosition < firstRowVertivalCoordinate + 5*Staff.getSpaceBetweenRows())
		{
			coordinate = (firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 5*Staff.getSpaceBetweenRows())/2 + 2;
			text.append("\n And finaly C");
		}
		else
		{
			coordinate = firstRowVertivalCoordinate + 5*Staff.getSpaceBetweenRows();
		}
		
		return coordinate;
	}
	
	public static boolean isCursorWithinLimits(int x, int y, int destinedX, int destinedY, Integer mode)
	{
		Integer horizontalLimit = (mode == ADD)? getBoxWidth() : 20;
		Integer verticalLimit = (mode == ADD) ? getBoxHeight() : 60;
		if((x>=destinedX && x <= destinedX + horizontalLimit) && (y >= destinedY && y <= destinedY + verticalLimit))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static File getViolinKeyFile() {
		return VIOLIN_KEY_FILE;
	}


	public static File getPlayKeyFile() {
		return PLAY_KEY_FILE;
	}
	
	public static File getNextPageKeyFile() {
		return NEXT_PAGE_KEY_FILE;
	}


	public static File getPreviousPageKeyFile() {
		return PREVIOUS_PAGE_KEY_FILE;
	}


	public static File getClearKeyFile() {
		return CLEAR_KEY_FILE;
	}


	public static File getDeleteLastNoteFile() {
		return DELETE_LAST_NOTE_FILE;
	}


	public static File getTitleButtonFile() {
		return TITLE_BUTTON_FILE;
	}


	public static File getMainBackgroundFile() {
		return MAIN_BACKGROUND_FILE;
	}


	public static File getPanelBackgroundFile() {
		return PANEL_BACKGROUND_FILE;
	}


	public static File getPageBackgroundFile() {
		return PAGE_BACKGROUND_FILE;
	}


	public static File getSavesDirectory()
	{
		return SAVES_DIRECTORY;
	}
	
	public static Integer getActiveStaffBeginningCoordinate()
	{
		return Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff());
	}
	
	public static Integer getHiddenLineCoord()
	{
		return Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff()) + 5 * Staff.getSpaceBetweenRows();
	}


	public static BufferedImage getImage(File file)
	{
		try
		{
			return ImageIO.read(file);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	

}
