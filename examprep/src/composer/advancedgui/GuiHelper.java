package composer.advancedgui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

import com.google.common.collect.ImmutableMap;
import composer.data.NoteData;
import composer.data.Tones;

public class GuiHelper //TODO zrobic dzwiek do C i uzupelnic Sharpy
{
	private static final Map<String, Integer> ROWS_HORIZONTAL_COORDINATES = ImmutableMap.of(
			"First", 100,
			"Second", 115,
			"Third", 130,
			"Fourth", 145,
			"Fifth", 160
			);
	
	private static final Map<String, Integer> BOXES_STARTPOINTS = ImmutableMap.of(
			"First", 0,
			"Second", 60,
			"Third", 120,
			"Fourth", 180,
			"Fifth", 240);
			
	private static final File VIOLIN_KEY_FILE = new File("resources/violinkey.jpg");
	private static final File PLAY_KEY_FILE = new File("resources/play.png");
	
	private static final String FIRST = "First";
	private static final String SECOND = "Second";
	private static final String THIRD = "Third";
	private static final String FOURTH = "Fourth";
	private static final String FIFTH = "Fifth";
	
	private static final Integer FIRST_ROW_VERTICAL_COORD = ROWS_HORIZONTAL_COORDINATES.get(FIRST);
	private static final Integer SECOND_ROW_VERTICAL_COORD = ROWS_HORIZONTAL_COORDINATES.get(SECOND);
	private static final Integer THIRD_ROW_VERTICAL_COORD = ROWS_HORIZONTAL_COORDINATES.get(THIRD);
	private static final Integer FOURTH_ROW_VERTICAL_COORD = ROWS_HORIZONTAL_COORDINATES.get(FOURTH);
	private static final Integer FIFTH_ROW_VERTICAL_COORD = ROWS_HORIZONTAL_COORDINATES.get(FIFTH);
	
	private static final Integer ROW_HORIZONTAL_STARTING_POINT = 0;
	private static final Integer ROW_HORIZONTAL_ENDING_POINT = 550;
	
	private static final Integer FIRST_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(FIRST);
	private static final Integer SECOND_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(SECOND);
	private static final Integer THIRD_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(THIRD);
	private static final Integer FOURTH_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(FOURTH);
	private static final Integer FIFTH_BOX_STARTING_POINT = BOXES_STARTPOINTS.get(FIFTH);
	
	private static final Integer BOX_VERTICAL_STARTING_POINT = 10;
	
	private static final Integer BOX_WIDTH = 40;
	private static final Integer BOX_HEIGHT = 70;
	
	private static final Integer D_TONE = FIFTH_ROW_VERTICAL_COORD;
	private static final Integer E_TONE = (FOURTH_ROW_VERTICAL_COORD + FIFTH_ROW_VERTICAL_COORD)/2 + 2;
	private static final Integer F_TONE = FOURTH_ROW_VERTICAL_COORD;
	private static final Integer G_TONE = (THIRD_ROW_VERTICAL_COORD + FOURTH_ROW_VERTICAL_COORD)/2 + 2;
	private static final Integer A_TONE = THIRD_ROW_VERTICAL_COORD;
	private static final Integer B_TONE = (SECOND_ROW_VERTICAL_COORD + THIRD_ROW_VERTICAL_COORD)/2 + 2;
	private static final Integer C2_TONE = SECOND_ROW_VERTICAL_COORD;
	private static final Integer D2_TONE = (FIRST_ROW_VERTICAL_COORD + SECOND_ROW_VERTICAL_COORD)/2 + 2;
	private static final Integer E2_TONE = FIRST_ROW_VERTICAL_COORD;
	private static final Integer F2_TONE = FIRST_ROW_VERTICAL_COORD - 8;
	//Sharps
	private static final Integer C_SHARP = 0;
	private static final Integer D_SHARP = D_TONE + 1000;
	private static final Integer F_SHARP = F_TONE + 1000;
	private static final Integer G_SHARP = G_TONE + 1000;
	private static final Integer A_SHARP = A_TONE + 1000;
	
			
	private static final Map<Integer, NoteData> TONES = ImmutableMap.<Integer, NoteData>builder()
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
			.put(D2_TONE, Tones.D2)
			.put(E2_TONE, Tones.E2)
			.put(F2_TONE, Tones.F2)
			.build();
	
	private static final Map<Integer, Boolean> SHARP_TONES = new HashMap<Integer,Boolean>();
	static
	{
		SHARP_TONES.put(D_TONE, false);
		SHARP_TONES.put(F_TONE, false);
		SHARP_TONES.put(G_TONE, false);
		SHARP_TONES.put(A_TONE, false);
		
	}
	private static final Map<Integer, Integer> TONES_TO_SHARP = ImmutableMap.<Integer, Integer>builder()
			.put(D_TONE, D_SHARP)
			.put(F_TONE, F_SHARP)
			.put(G_TONE, G_SHARP)
			.put(A_TONE, A_SHARP)
			.build();
	
	

	public static Map<String, Integer> getRowsHorizontalCoordinates() {
		return ROWS_HORIZONTAL_COORDINATES;
	}

	
	public static Map<String, Integer> getBoxesStartpoints() {
		return BOXES_STARTPOINTS;
	}


	public static Integer getFirstRowVerticalCoord() {
		return FIRST_ROW_VERTICAL_COORD;
	}


	public static Integer getSecondRowVerticalCoord() {
		return SECOND_ROW_VERTICAL_COORD;
	}


	public static Integer getThirdRowVerticalCoord() {
		return THIRD_ROW_VERTICAL_COORD;
	}


	public static Integer getFourthRowVerticalCoord() {
		return FOURTH_ROW_VERTICAL_COORD;
	}


	public static Integer getFifthRowVerticalCoord() {
		return FIFTH_ROW_VERTICAL_COORD;
	}


	public static Integer getRowHorizontalStartingPoint() {
		return ROW_HORIZONTAL_STARTING_POINT;
	}


	public static Integer getRowHorizontalEndingPoint() {
		return ROW_HORIZONTAL_ENDING_POINT;
	}


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
	
	public static Map<Integer, NoteData> getTones() {
		return TONES;
	}


	public static Map<Integer, Boolean> getSharpTones() {
		return SHARP_TONES;
	}


	public static Map<Integer, Integer> getTonesToSharp() {
		return TONES_TO_SHARP;
	}


	public static Integer verticalCoordinate(Integer y, JTextArea text)
	{
		Integer coordinate = null;
		if(y >= FIRST_ROW_VERTICAL_COORD - 15 && y < FIRST_ROW_VERTICAL_COORD - 5)
		{
			coordinate = FIRST_ROW_VERTICAL_COORD - 8;
		}
		else if(y >= FIRST_ROW_VERTICAL_COORD - 5 && y < (FIRST_ROW_VERTICAL_COORD + SECOND_ROW_VERTICAL_COORD)/2)
		{
			coordinate = FIRST_ROW_VERTICAL_COORD;
			text.append("\nfirst");
		}
		else if(y >= (FIRST_ROW_VERTICAL_COORD + SECOND_ROW_VERTICAL_COORD)/2 && y < SECOND_ROW_VERTICAL_COORD)
		{
			coordinate = (FIRST_ROW_VERTICAL_COORD + SECOND_ROW_VERTICAL_COORD)/2 + 2;
			text.append("\nsecond half ");
		}
		else if(y >= SECOND_ROW_VERTICAL_COORD && y < (SECOND_ROW_VERTICAL_COORD + THIRD_ROW_VERTICAL_COORD)/2)
		{
			coordinate = SECOND_ROW_VERTICAL_COORD;
			text.append("\nsecond");
		}
		else if(y >= (SECOND_ROW_VERTICAL_COORD + THIRD_ROW_VERTICAL_COORD)/2 && y < THIRD_ROW_VERTICAL_COORD)
		{
			coordinate = (SECOND_ROW_VERTICAL_COORD + THIRD_ROW_VERTICAL_COORD)/2 + 2;
			text.append("\nthird half");
		}
		else if(y >= THIRD_ROW_VERTICAL_COORD && y < (THIRD_ROW_VERTICAL_COORD + FOURTH_ROW_VERTICAL_COORD)/2)
		{
			coordinate = THIRD_ROW_VERTICAL_COORD;
			text.append("\nthird");
		}
		else if(y >= (THIRD_ROW_VERTICAL_COORD + FOURTH_ROW_VERTICAL_COORD)/2 && y < FOURTH_ROW_VERTICAL_COORD)
		{
			coordinate = (THIRD_ROW_VERTICAL_COORD + FOURTH_ROW_VERTICAL_COORD)/2 + 2;
			text.append("\nfour half");
		}
		else if(y >= FOURTH_ROW_VERTICAL_COORD && y < (FOURTH_ROW_VERTICAL_COORD + FIFTH_ROW_VERTICAL_COORD)/2)
		{
			coordinate = FOURTH_ROW_VERTICAL_COORD;
			text.append("\nfourth");
		}
		else if(y >= (FOURTH_ROW_VERTICAL_COORD + FIFTH_ROW_VERTICAL_COORD)/2 && y < FIFTH_ROW_VERTICAL_COORD)
		{
			coordinate = (FOURTH_ROW_VERTICAL_COORD + FIFTH_ROW_VERTICAL_COORD)/2 + 2;
			text.append("\nfifth half");
		}
		else
		{
			coordinate = FIFTH_ROW_VERTICAL_COORD;
			text.append("\nfifth");
		}
		
		return coordinate;
	}
	
	public static boolean isCursorWithinLimits(int x, int y, int destinedX, int destinedY)
	{
		if((x>=destinedX && x <= destinedX + GuiHelper.getBoxWidth()) && (y >= destinedY && y <= destinedY + GuiHelper.getBoxHeight()))
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

//100 120 140 160 180 