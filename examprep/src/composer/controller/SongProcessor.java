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
 * Controller class which adds the correct note to the song upon receiving coordinates from GUI and takes care of markings
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
	public static Integer[] addNote(Integer noteVerticalCoordinate, Lengths length, boolean isRest)
	{	
		if(!isRest)
		{
			MidiDataExtractor tone;
			Object[] toneInfo = getMidiTone(noteVerticalCoordinate);
			tone = (MidiDataExtractor) toneInfo[0];
			noteVerticalCoordinate = (Integer) toneInfo[1];
			System.out.println(tone.value());
			Note note = new Note(tone, length);	
			SONG.add(note);
			Integer[] toneData = {(Integer) toneInfo[1], SONG.indexOf(note)};
			return toneData;
		}
		
		Note note = new Note(length);
		SONG.add(note);
		Integer[] toneData = {null, SONG.indexOf(note)};
		return toneData;
	}
	
	/**
	 * Method gets the midi tone which corresponds to the vertical coordinate of the note drawing
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 * @return - returns midi tone of the note and vertical coordinate on which the note is drawn
	 */
	public static Object[] getMidiTone(Integer noteVerticalCoordinate)
	{
		noteVerticalCoordinate = reAdjustVerticalCoordinate(noteVerticalCoordinate);
		MidiDataExtractor tone;
		if(isGloballySharp(noteVerticalCoordinate))
		{
			tone = GuiHelper.getTones().get(GuiHelper.getTonesToSharp().get(noteVerticalCoordinate));
			GuiHelper.getTemporarySharpTones().put(noteVerticalCoordinate, false);
		}
		
		else if(isGloballyFlat(noteVerticalCoordinate))
		{
			tone = GuiHelper.getTones().get(GuiHelper.getTonesToFlat().get(noteVerticalCoordinate));
			GuiHelper.getTemporaryFlatTones().put(noteVerticalCoordinate, false);
		}
		else if(isLocallySharp(noteVerticalCoordinate))
		{
			tone = GuiHelper.getTones().get(GuiHelper.getTonesToSharp().get(noteVerticalCoordinate));
			GuiHelper.getTemporarySharpTones().put(noteVerticalCoordinate, false);
		}
		else if(isLocallyFlat(noteVerticalCoordinate))
		{
			tone = GuiHelper.getTones().get(GuiHelper.getTonesToFlat().get(noteVerticalCoordinate));
			GuiHelper.getTemporaryFlatTones().put(noteVerticalCoordinate, false);
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
		return checkIfNoteIsMarked(GuiHelper.getSharpTones(), noteVerticalCoordinate);
	}
	
	/**
	 * Method checks if the note should be globally played flat
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 * @return
	 */
	private static boolean isGloballyFlat(Integer noteVerticalCoordinate) 
	{
		return checkIfNoteIsMarked(GuiHelper.getFlatTones(), noteVerticalCoordinate);
	}
	
	/**
	 * Method checks if the note should be locally played sharp
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 * @return
	 */
	public static boolean isLocallySharp(Integer noteVerticalCoordinate)
	{
		return checkIfNoteIsMarked(GuiHelper.getTemporarySharpTones(), noteVerticalCoordinate);
	}
	
	/**
	 * Method checks if the note should be locally played flat
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 * @return
	 */
	private static boolean isLocallyFlat(Integer noteVerticalCoordinate) 
	{
		return checkIfNoteIsMarked(GuiHelper.getTemporaryFlatTones(), noteVerticalCoordinate);
	}
	
	/**
	 * Method marks the note as the global sharp
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 */
	public static void makeNoteSharpGlobally(Integer noteVerticalCoordinate)
	{
		markTheNotes(GuiHelper.getSharpTones(), reAdjustVerticalCoordinate(noteVerticalCoordinate));
	}
	
	/**
	 * Method marks the note as the local sharp
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 */
	public static void makeNoteLocallySharp(Integer noteVerticalCoordinate)
	{
		markTheNotes(GuiHelper.getTemporarySharpTones(), reAdjustVerticalCoordinate(noteVerticalCoordinate));
	}
	
	/**
	 * Method marks the note as the global flat
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 */
	public static void makeNoteFlatGlobally(Integer noteVerticalCoordinate)
	{
		markTheNotes(GuiHelper.getFlatTones(), reAdjustVerticalCoordinate(noteVerticalCoordinate));
	}
	
	/**
	 * Method marks the note as the local flat
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 */
	public static void makeNoteLocallyFlat(Integer noteVerticalCoordinate) 
	{
		markTheNotes(GuiHelper.getTemporaryFlatTones(), reAdjustVerticalCoordinate(noteVerticalCoordinate));
	}
	
	/**
	 * Method cleans all the sharp markings from the song (not their graphic representations, but their function)
	 */
	public static void cleanAllSharps()
	{
		Map<Integer, Boolean> sharpNotesMap = GuiHelper.getSharpTones();
		invalidateMarkings(sharpNotesMap);
		sharpNotesMap = GuiHelper.getTemporarySharpTones();
		invalidateMarkings(sharpNotesMap);
	}
	
	/**
	 * Method cleans all the flat markings from the song (not their graphic representations, but their function)
	 */
	public static void cleanAllFlats() 
	{
		Map<Integer, Boolean> flatNotesMap = GuiHelper.getFlatTones();
		invalidateMarkings(flatNotesMap);
		flatNotesMap = GuiHelper.getTemporaryFlatTones();
		invalidateMarkings(flatNotesMap);
	}
	
	/**
	 * Method calculates vertical coordinate of the note in relation to the first staff on the screen
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn
	 * @return noteVerticalCoordinate
	 */
	private static Integer reAdjustVerticalCoordinate(Integer noteVerticalCoordinate)
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
	
	/**
	 * Helper method to invalidate markings in the map which contains them
	 * @param mapToClean - map destined for cleaning
	 */
	private static void invalidateMarkings(Map<Integer, Boolean> mapToClean)
	{
		for(Map.Entry<Integer, Boolean> entry : mapToClean.entrySet())
		{
			mapToClean.put(entry.getKey(), false);
		}
	}
	
	/**
	 * Helper method to check if the note is marked flat or sharp
	 * @param mapToCheck - map containing proper sharp/flat markings
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn 
	 * @return
	 */
	private static boolean checkIfNoteIsMarked(Map<Integer, Boolean> mapToCheck, Integer noteVerticalCoordinate)
	{
		if(mapToCheck.containsKey(noteVerticalCoordinate))
		{
			return mapToCheck.get(noteVerticalCoordinate);		
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Helper method to mark the note as flat or sharp
	 * @param mapToCheck - map containing proper sharp/flat markings
	 * @param noteVerticalCoordinate - vertical coordinate on which the note is drawn 
	 * @return
	 */
	private static void markTheNotes(Map<Integer, Boolean> mapForMarking, Integer noteVerticalCoordinate)
	{
		if(mapForMarking.containsKey(noteVerticalCoordinate))
		{
			mapForMarking.put(noteVerticalCoordinate, true);
		}
	}




}
