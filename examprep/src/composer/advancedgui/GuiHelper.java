package composer.advancedgui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.common.collect.ImmutableMap;
import composer.data.MidiDataExtractor;
import composer.data.Tones;

/**
 * 
 * @author Tomek
 * This is helper class, containing most of the variables necessary for the GUI creation. It also has helper methods for getting,
 * checking and counting coordinates, and creating image files.
 *
 */
public class GuiHelper
{
	//Those two variables determine the mode in which gui is working (adding a new note or editing the existing one).
	//They are used in order to correctly set the size of the "hitbox" while grabbing the note in order to drag it.
	public static final Integer ADD = 0;
	public static final Integer EDIT = 1;
	
	//Map containing the original horizontal starting coordinates of the "boxes" with the notes for grabbing
	private static final Map<String, Integer> BOXES_STARTPOINTS = ImmutableMap.<String, Integer>builder()
			.put("First", 0)
			.put("Second", 60)
			.put("Third", 120)
			.put("Fourth", 180)
			.put("Fifth", 240)
			.put("Sixth", 300)
			.build();
			
	private static final File VIOLIN_KEY_FILE = new File("resources/violinkey.png");
	private static final File PLAY_KEY_FILE = new File("resources/play.png");
	private static final File NEXT_PAGE_KEY_FILE = new File("resources/nextpage.png");
	private static final File PREVIOUS_PAGE_KEY_FILE = new File("resources/previouspage.png");
	private static final File CLEAR_KEY_FILE = new File("resources/clearbutton.png");
	private static final File SAVES_DIRECTORY = new File("saves");
	private static final File MAIN_BACKGROUND_FILE = new File("resources/wood.png");
	private static final File PANEL_BACKGROUND_FILE = new File("resources/leftwood1.png");
	private static final File PAGE_BACKGROUND_FILE = new File("resources/papyrus.png");
	private static final File MENU_BACKGROUND_FILE = new File("resources/menuback.png");
	private static final File DELETE_LAST_NOTE_FILE = new File("resources/deletenote.png");
	private static final File TITLE_BUTTON_FILE = new File("resources/titlebutton.png");
	
	//Those are created to avoid using string literals
	private static final String FIRST = "First";
	private static final String SECOND = "Second";
	private static final String THIRD = "Third";
	private static final String FOURTH = "Fourth";
	private static final String FIFTH = "Fifth";
	private static final String SIXTH = "Sixth";
	
	//Vertical coords of the first staff's lines.
	private static final Integer ORIGINAL_FIRST_ROW_VERTICAL_COORD = 100;
	private static final Integer ORIGINAL_SECOND_ROW_VERTICAL_COORD = 115; 
	private static final Integer ORIGINAL_THIRD_ROW_VERTICAL_COORD = 130; 
	private static final Integer ORIGINAL_FOURTH_ROW_VERTICAL_COORD = 145;
	private static final Integer ORIGINAL_FIFTH_ROW_VERTICAL_COORD = 160; 
	private static final Integer ORIGINAL_SIXTH_ROW_VERTICAL_COORD = 175; 
	private static final Integer ORIGINAL_SEVENTH_ROW_VERTICAL_COORD = 190; 
	
	//These coordinates are the ones which are exposed to other classes
	private static final Integer FIRST_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(FIRST);
	private static final Integer SECOND_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(SECOND);
	private static final Integer THIRD_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(THIRD);
	private static final Integer FOURTH_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(FOURTH);
	private static final Integer FIFTH_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(FIFTH);
	private static final Integer SIXTH_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(SIXTH);
	
	private static final Integer BOX_VERTICAL_STARTING_POINT = 10;
	
	private static final Integer BOX_WIDTH = 40;
	private static final Integer BOX_HEIGHT = 70;
	
	//These are coordinates of the notes - they are not the sound coordinates however, but the coordinates of the drawn notes.
	private static final Integer A1_TONE = (ORIGINAL_SIXTH_ROW_VERTICAL_COORD + ORIGINAL_SEVENTH_ROW_VERTICAL_COORD)/2 + 2;
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
	private static final Integer A1_SHARP = A1_TONE + 1000;
	private static final Integer C_SHARP = C_TONE + 1000;
	private static final Integer D_SHARP = D_TONE + 1000;
	private static final Integer F_SHARP = F_TONE + 1000;
	private static final Integer G_SHARP = G_TONE + 1000;
	private static final Integer A_SHARP = A_TONE + 1000;
	private static final Integer C2_SHARP = C2_TONE + 1000;
	private static final Integer D2_SHARP = D2_TONE + 1000;
	private static final Integer F2_SHARP = F2_TONE + 1000;
	
	//Map connecting the coordinate of the note drawings to the tones which they represent		
	private static final Map<Integer, MidiDataExtractor> TONES = ImmutableMap.<Integer, MidiDataExtractor>builder()
			.put(A1_TONE, Tones.A1)
			.put(A1_SHARP, Tones.AIS1)
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
	
	//Map used to determine if the given note should be globally played as sharp or not
	private static final Map<Integer, Boolean> GLOBAL_SHARP_TONES = new HashMap<Integer,Boolean>();
	static
	{
		GLOBAL_SHARP_TONES.put(A1_TONE, false);
		GLOBAL_SHARP_TONES.put(C_TONE, false);
		GLOBAL_SHARP_TONES.put(D_TONE, false);
		GLOBAL_SHARP_TONES.put(F_TONE, false);
		GLOBAL_SHARP_TONES.put(G_TONE, false);
		GLOBAL_SHARP_TONES.put(A_TONE, false);
		GLOBAL_SHARP_TONES.put(C2_TONE, false);
		GLOBAL_SHARP_TONES.put(D2_TONE, false);
		GLOBAL_SHARP_TONES.put(F2_TONE, false);
		
	}
	
	//Map used to determine if the given note should be locally played as sharp or not
	private static final Map<Integer, Boolean> LOCAL_SHARP_TONES = new HashMap<Integer,Boolean>();
	static
	{
		LOCAL_SHARP_TONES.put(A1_TONE, false);
		LOCAL_SHARP_TONES.put(C_TONE, false);
		LOCAL_SHARP_TONES.put(D_TONE, false);
		LOCAL_SHARP_TONES.put(F_TONE, false);
		LOCAL_SHARP_TONES.put(G_TONE, false);
		LOCAL_SHARP_TONES.put(A_TONE, false);
		LOCAL_SHARP_TONES.put(C2_TONE, false);
		LOCAL_SHARP_TONES.put(D2_TONE, false);
		LOCAL_SHARP_TONES.put(F2_TONE, false);
		
	}
	
	//Map which binds tones with their sharp counterparts
	private static final Map<Integer, Integer> TONES_TO_SHARP = ImmutableMap.<Integer, Integer>builder()
			.put(A1_TONE, A1_SHARP)
			.put(C_TONE, C_SHARP)
			.put(D_TONE, D_SHARP)
			.put(F_TONE, F_SHARP)
			.put(G_TONE, G_SHARP)
			.put(A_TONE, A_SHARP)
			.put(C2_TONE, C2_SHARP)
			.put(D2_TONE, D2_SHARP)
			.put(F2_TONE, F2_SHARP)
			.build();
	private static final Map<Integer, Boolean> GLOBAL_FLAT_TONES = new HashMap<Integer,Boolean>();
	static
	{
		GLOBAL_FLAT_TONES.put(E2_TONE, false);
		GLOBAL_FLAT_TONES.put(D2_TONE, false);
		GLOBAL_FLAT_TONES.put(B_TONE, false);
		GLOBAL_FLAT_TONES.put(A_TONE, false);
		GLOBAL_FLAT_TONES.put(G_TONE, false);
		GLOBAL_FLAT_TONES.put(E_TONE, false);
		GLOBAL_FLAT_TONES.put(D_TONE, false);
		GLOBAL_FLAT_TONES.put(B1_TONE, false);
	}
	
	private static final Map<Integer, Boolean> LOCAL_FLAT_TONES = new HashMap<Integer,Boolean>();
	static
	{
		LOCAL_FLAT_TONES.put(E2_TONE, false);
		LOCAL_FLAT_TONES.put(D2_TONE, false);
		LOCAL_FLAT_TONES.put(B_TONE, false);
		LOCAL_FLAT_TONES.put(A_TONE, false);
		LOCAL_FLAT_TONES.put(G_TONE, false);
		LOCAL_FLAT_TONES.put(E_TONE, false);
		LOCAL_FLAT_TONES.put(D_TONE, false);
		LOCAL_FLAT_TONES.put(B1_TONE, false);
		
	}
	
	private static final Map<Integer, Integer> TONES_TO_FLAT = ImmutableMap.<Integer, Integer>builder()
			.put(E2_TONE, D2_SHARP)
			.put(D2_TONE, C2_SHARP)
			.put(B_TONE, A_SHARP)
			.put(A_TONE, G_SHARP)
			.put(G_TONE, F_SHARP)
			.put(E_TONE, D_SHARP)
			.put(D_TONE, C_SHARP)
			.put(B1_TONE, A1_SHARP)
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


	public static Integer getSixthBoxStartingPoint() {
		return SIXTH_BOX_STARTING_POINT;
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
		return GLOBAL_SHARP_TONES;
	}


	public static Map<Integer, Boolean> getTemporarySharpTones() {
		return LOCAL_SHARP_TONES;
	}


	public static Map<Integer, Integer> getTonesToSharp() {
		return TONES_TO_SHARP;
	}
	
	public static Map<Integer, Boolean> getFlatTones() {
		return GLOBAL_FLAT_TONES;
	}
	
	public static Map<Integer, Boolean> getTemporaryFlatTones() {
		return LOCAL_FLAT_TONES;
	}
	
	public static Map<Integer, Integer> getTonesToFlat() {
		return TONES_TO_FLAT;
	}
	
	/**
	 * Methods which count the vertical coordinate at which the note should be placed
	 * @param verticalPosition - position of the current note upon releasing mouse button
	 * @param firstRowVertivalCoordinate - vertical coordinate of the first row in the staff upon which the note is drawn
	 * @param text - just for controll purposes - will be deleted in final version
	 * @return
	 */
	public static Integer countVerticalCoordinate(Integer verticalPosition, Integer firstRowVertivalCoordinate)
	{
		Integer coordinate = null;
		if(verticalPosition >= firstRowVertivalCoordinate - 15 && verticalPosition < firstRowVertivalCoordinate - 5)
		{
			coordinate = firstRowVertivalCoordinate - 8;
		}
		else if(verticalPosition >= firstRowVertivalCoordinate - 5 && verticalPosition < (firstRowVertivalCoordinate + firstRowVertivalCoordinate + Staff.getSpaceBetweenRows())/2)
		{
			coordinate = firstRowVertivalCoordinate;
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + firstRowVertivalCoordinate + Staff.getSpaceBetweenRows())/2 && verticalPosition < (firstRowVertivalCoordinate + Staff.getSpaceBetweenRows()))
		{
			coordinate = (firstRowVertivalCoordinate + firstRowVertivalCoordinate + Staff.getSpaceBetweenRows())/2 + 2;
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + Staff.getSpaceBetweenRows()) && verticalPosition < (firstRowVertivalCoordinate + Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows())/2)
		{
			coordinate = (firstRowVertivalCoordinate + Staff.getSpaceBetweenRows());
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows())/2 && verticalPosition < (firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows()))
		{
			coordinate = (firstRowVertivalCoordinate + Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows())/2 + 2;
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows()) && verticalPosition < (firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows())/2)
		{
			coordinate = (firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows());
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows())/2 && verticalPosition < (firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows()))
		{
			coordinate = (firstRowVertivalCoordinate + 2*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows())/2 + 2;
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows()) && verticalPosition < (firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows())/2)
		{
			coordinate = (firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows());
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows())/2 && verticalPosition < (firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows()))
		{
			coordinate = (firstRowVertivalCoordinate + 3*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows())/2 + 2;
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows()) && verticalPosition < (firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 5*Staff.getSpaceBetweenRows())/2)
		{
			coordinate = firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows();
		}
		else if(verticalPosition >= (firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 5*Staff.getSpaceBetweenRows())/2 && verticalPosition < firstRowVertivalCoordinate + 5*Staff.getSpaceBetweenRows())
		{
			coordinate = (firstRowVertivalCoordinate + 4*Staff.getSpaceBetweenRows() + firstRowVertivalCoordinate + 5*Staff.getSpaceBetweenRows())/2 + 2;
		}
		else if(verticalPosition >= firstRowVertivalCoordinate + 5*Staff.getSpaceBetweenRows() && verticalPosition < (2 *firstRowVertivalCoordinate + 11*Staff.getSpaceBetweenRows())/2)
		{
			coordinate = firstRowVertivalCoordinate + 5*Staff.getSpaceBetweenRows();
		}
		else
		{
			coordinate = (2 *firstRowVertivalCoordinate + 11*Staff.getSpaceBetweenRows())/2 + 2;
		}
		
		return coordinate;
	}
	
	/**
	 * The method checks if cursor is within desired "hitbox". Depending on the mode (ADD/EDIT) the "hitbox" size differs
	 * @param horizontalPosition - cursor horizontal position
	 * @param verticalPosition - cursor vertical position
	 * @param horizontalBeginningCoordinate - "hitbox" horizontal beggining coord
	 * @param verticalBeginningCoordinate - "hitbox" vertical beggining coord
	 * @param mode - mode in which the program is functioning (ADD or EDIT)
	 * @return
	 */
	public static boolean isCursorWithinLimits(int horizontalPosition, int verticalPosition, int horizontalBeginningCoordinate, 
			int verticalBeginningCoordinate, Integer mode)
	{
		Integer horizontalLimit = (mode == ADD)? getBoxWidth() : 20;
		Integer verticalLimit = (mode == ADD) ? getBoxHeight() : 60;
		if((horizontalPosition>=horizontalBeginningCoordinate && horizontalPosition <= horizontalBeginningCoordinate + horizontalLimit) 
				&& (verticalPosition >= verticalBeginningCoordinate && verticalPosition <= verticalBeginningCoordinate + verticalLimit))
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


	public static File getMenuBackgroundFile() {
		return MENU_BACKGROUND_FILE;
	}


	public static File getSavesDirectory()
	{
		return SAVES_DIRECTORY;
	}
	
	/**
	 * Method returns vertical beginning coordinate of the active staff
	 * @return
	 */
	public static Integer getActiveStaffBeginningCoordinate()
	{
		return Staff.getStaffVerticalBeginningCoordinates().get(Staff.getActiveStaff());
	}
	
	/**
	 * Method returns vertical coordinate of the row destined for lower C note
	 * @return
	 */
	public static Integer getCLineCoord()
	{
		return Staff.getStaffVerticalBeginningCoordinates().get(Staff.getActiveStaff()) + 5 * Staff.getSpaceBetweenRows();
	}

	/**
	 * Method returns vertical coordinate of the row destined for lower A note
	 * @return
	 */
	public static Integer getA1LineCoord()
	{
		return Staff.getStaffVerticalBeginningCoordinates().get(Staff.getActiveStaff()) + 6 * Staff.getSpaceBetweenRows();
	}


	/**
	 * Method creates an image from the file
	 * @param file - the file containing image
	 * @return
	 */
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
