package composer.advancedgui;

import java.util.Map;

import composer.data.Lengths;
import composer.data.MidiDataExtractor;
import composer.sound.Note;
import composer.sound.Song;

public class SongProcessor 
{
	private static Song SONG = new Song();
	
	public static Integer[] addNote(Integer toneValue, Lengths length)
	{
		MidiDataExtractor tone;
		Object[] temp = getMidiTone(toneValue);
		tone = (MidiDataExtractor) temp[0];
		if(isLocallySharp(toneValue))
		{
			tone = GuiHelper.getTones().get(GuiHelper.getTonesToSharp().get(toneValue));
			GuiHelper.getTemporarySharpTones().put(toneValue, false);
		}
		Note note = new Note(tone, length);	
		SONG.add(note);
		Integer[] data = {(Integer) temp[1], SONG.indexOf(note)};
		return data;
	}
	
	public static Object[] getMidiTone(Integer toneValue)
	{
		if(Staff.getActiveStaff() != 1)
		toneValue = toneValue - (Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff()) - Staff.getStaffBeginningCoordinates().get(1));
		MidiDataExtractor tone;
		if(isGloballySharp(toneValue))
		{
			tone = GuiHelper.getTones().get(GuiHelper.getTonesToSharp().get(toneValue));
		}
		else
		{
			tone = GuiHelper.getTones().get(toneValue);
		}
		
		Object[] data = {tone, toneValue};
		return data;
	}
	
	public static boolean isGloballySharp(Integer toneValue)
	{
		if(GuiHelper.getSharpTones().containsKey(toneValue))
		{
			return GuiHelper.getSharpTones().get(toneValue);		
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isLocallySharp(Integer toneValue)
	{
		if(GuiHelper.getTemporarySharpTones().containsKey(toneValue))
		{
			return GuiHelper.getTemporarySharpTones().get(toneValue);		
		}
		else
		{
			return false;
		}
	}
	
	public static void makeNoteSharpGlobally(Integer toneValue)
	{
		toneValue = (Staff.getActiveStaff() != 1) ? toneValue - (Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff()) - Staff.getStaffBeginningCoordinates().get(1)) : toneValue;
		if(GuiHelper.getSharpTones().containsKey(toneValue))
		{
			GuiHelper.getSharpTones().put(toneValue, true);
		}
			
	}
	
	public static void makeNoteSharp(Integer toneValue)
	{
		toneValue = (Staff.getActiveStaff() != 1) ? toneValue - (Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff()) - Staff.getStaffBeginningCoordinates().get(1)) : toneValue;
		if(GuiHelper.getTemporarySharpTones().containsKey(toneValue))
		{
			GuiHelper.getTemporarySharpTones().put(toneValue, true);
		}
		
	}
	
	public static void cleanAllSharps()
	{
		Map<Integer, Boolean> map = GuiHelper.getSharpTones();
		for(Map.Entry<Integer, Boolean> entry : map.entrySet())
		{
			map.put(entry.getKey(), false);
		}
	}
	
	public static Song getSong() {
		return SONG;
	}
	
	public static void setSong(Song song) {
		SONG = song;
	}

}
