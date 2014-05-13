package composer.controller;

import java.util.Map;

import composer.advancedgui.GuiHelper;
import composer.advancedgui.Staff;
import composer.data.Lengths;
import composer.data.MidiDataExtractor;
import composer.sound.Note;
import composer.sound.Song;

/**
 * 
 * @author Tomek
 * Controller class which adds the correct note to the song upon receiving coordinates from GUI
 *
 */
public class SongProcessor 
{
	private static Song SONG = new Song();
	
	/**
	 * Method which adds the note to the song
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 * @param length - length of the note
	 * @return - returns vertical coordinate on which the note is drawn and index of the tone in the song
	 */
	public static Integer[] addNote(Integer noteVerticalCoordinate, Lengths length)
	{
		MidiDataExtractor tone;
		noteVerticalCoordinate = readjustVerticalCoordinate(noteVerticalCoordinate);
		Object[] temp = getMidiTone(noteVerticalCoordinate);
		tone = (MidiDataExtractor) temp[0];
		if(isLocallySharp(noteVerticalCoordinate))
		{
			System.out.println("it is locally sharp");
			tone = GuiHelper.getTones().get(GuiHelper.getTonesToSharp().get(noteVerticalCoordinate));
			GuiHelper.getTemporarySharpTones().put(noteVerticalCoordinate, false);
		}
		System.out.println(tone.value());
		Note note = new Note(tone, length);	
		SONG.add(note);
		Integer[] data = {(Integer) temp[1], SONG.indexOf(note)};
		return data;
	}
	
	/**
	 * Method gets the midi tone which corresponds to the vertical coordinate of the note drawing
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 * @return - returns midi tone of the note and vertical coordinate on which the note is drawn
	 */
	public static Object[] getMidiTone(Integer noteVerticalCoordinate)
	{
		MidiDataExtractor tone;
		if(isGloballySharp(noteVerticalCoordinate))
		{
			tone = GuiHelper.getTones().get(GuiHelper.getTonesToSharp().get(noteVerticalCoordinate));
		}
		else
		{
			tone = GuiHelper.getTones().get(noteVerticalCoordinate);
		}
		
		Object[] data = {tone, noteVerticalCoordinate};
		return data;
	}
	
	/**
	 * Method checks if the note should be globally played sharp
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 * @return
	 */
	public static boolean isGloballySharp(Integer noteVerticalCoordinate)
	{
		if(GuiHelper.getSharpTones().containsKey(noteVerticalCoordinate))
		{
			return GuiHelper.getSharpTones().get(noteVerticalCoordinate);		
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Method checks if the note should be locally played sharp
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 * @return
	 */
	public static boolean isLocallySharp(Integer noteVerticalCoordinate)
	{
		if(GuiHelper.getTemporarySharpTones().containsKey(noteVerticalCoordinate))
		{
			return GuiHelper.getTemporarySharpTones().get(noteVerticalCoordinate);		
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Method marks the note as the global sharp
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 * @return
	 */
	public static void makeNoteSharpGlobally(Integer noteVerticalCoordinate)
	{
		noteVerticalCoordinate = (Staff.getActiveStaff() != 1) ? noteVerticalCoordinate - (Staff.getStaffVerticalBeginningCoordinates().get(Staff.getActiveStaff()) - Staff.getStaffVerticalBeginningCoordinates().get(1)) : noteVerticalCoordinate;
		if(GuiHelper.getSharpTones().containsKey(noteVerticalCoordinate))
		{
			GuiHelper.getSharpTones().put(noteVerticalCoordinate, true);
		}
			
	}
	
	/**
	 * Method marks the note as the local sharp
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 * @return
	 */
	public static void makeNoteLocallySharp(Integer noteVerticalParameter)
	{
		noteVerticalParameter = (Staff.getActiveStaff() != 1) ? noteVerticalParameter - (Staff.getStaffVerticalBeginningCoordinates().get(Staff.getActiveStaff()) - Staff.getStaffVerticalBeginningCoordinates().get(1)) : noteVerticalParameter;
		if(GuiHelper.getTemporarySharpTones().containsKey(noteVerticalParameter))
		{
			GuiHelper.getTemporarySharpTones().put(noteVerticalParameter, true);
			System.out.println("made locally sharp");
		}
		
	}
	
	/**
	 * Method cleans all the sharp markings from the song (not their graphic representations, but their function)
	 */
	public static void cleanAllSharps()
	{
		Map<Integer, Boolean> sharpNotesMap = GuiHelper.getSharpTones();
		for(Map.Entry<Integer, Boolean> entry : sharpNotesMap.entrySet())
		{
			sharpNotesMap.put(entry.getKey(), false);
		}
	}
	
	private static Integer readjustVerticalCoordinate(Integer noteVerticalCoordinate)
	{
		if(Staff.getActiveStaff() != 1)
			noteVerticalCoordinate = noteVerticalCoordinate - (Staff.getStaffVerticalBeginningCoordinates().get(Staff.getActiveStaff()) - Staff.getStaffVerticalBeginningCoordinates().get(1));
		return noteVerticalCoordinate;
	}
	
	public static Song getSong() {
		return SONG;
	}
	
	public static void setSong(Song song) {
		SONG = song;
	}

}
