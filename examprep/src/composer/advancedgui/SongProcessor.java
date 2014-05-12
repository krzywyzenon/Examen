package composer.advancedgui;

import java.util.Map;

import composer.data.Lengths;
import composer.data.MidiDataExtractor;
import composer.sound.Note;
import composer.sound.Song;

public class SongProcessor 
{
	private static Song SONG = new Song();
	
	public static Integer[] addNote(Integer noteVerticalCoordinate, Lengths length)
	{
		MidiDataExtractor tone;
		Object[] temp = getMidiTone(noteVerticalCoordinate);
		tone = (MidiDataExtractor) temp[0];
		if(isLocallySharp(noteVerticalCoordinate))
		{
			tone = GuiHelper.getTones().get(GuiHelper.getTonesToSharp().get(noteVerticalCoordinate));
			GuiHelper.getTemporarySharpTones().put(noteVerticalCoordinate, false);
		}
		Note note = new Note(tone, length);	
		SONG.add(note);
		Integer[] data = {(Integer) temp[1], SONG.indexOf(note)};
		return data;
	}
	
	public static Object[] getMidiTone(Integer noteVerticalCoordinate)
	{
		if(Staff.getActiveStaff() != 1)
		noteVerticalCoordinate = noteVerticalCoordinate - (Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff()) - Staff.getStaffBeginningCoordinates().get(1));
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
	
	public static void makeNoteSharpGlobally(Integer noteVerticalCoordinate)
	{
		noteVerticalCoordinate = (Staff.getActiveStaff() != 1) ? noteVerticalCoordinate - (Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff()) - Staff.getStaffBeginningCoordinates().get(1)) : noteVerticalCoordinate;
		if(GuiHelper.getSharpTones().containsKey(noteVerticalCoordinate))
		{
			GuiHelper.getSharpTones().put(noteVerticalCoordinate, true);
		}
			
	}
	
	public static void makeNoteLocallySharp(Integer noteVerticalParameter)
	{
		noteVerticalParameter = (Staff.getActiveStaff() != 1) ? noteVerticalParameter - (Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff()) - Staff.getStaffBeginningCoordinates().get(1)) : noteVerticalParameter;
		if(GuiHelper.getTemporarySharpTones().containsKey(noteVerticalParameter))
		{
			GuiHelper.getTemporarySharpTones().put(noteVerticalParameter, true);
		}
		
	}
	
	public static void cleanAllSharps()
	{
		Map<Integer, Boolean> sharpNotesMap = GuiHelper.getSharpTones();
		for(Map.Entry<Integer, Boolean> entry : sharpNotesMap.entrySet())
		{
			sharpNotesMap.put(entry.getKey(), false);
		}
	}
	
	public static Song getSong() {
		return SONG;
	}
	
	public static void setSong(Song song) {
		SONG = song;
	}

}
