package composer.advancedgui;

import java.util.Map;

import composer.data.Lengths;
import composer.data.NoteData;
import composer.sound.Note;
import composer.sound.Song;

public class SongProcessor 
{
	private static Song SONG = new Song();
	
	private static Lengths LENGTH = null;
	
	public static Integer[] addNote(Integer toneValue)
	{
		if(Staff.getActiveStaff() != 1)
		toneValue = toneValue - (Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff()) - Staff.getStaffBeginningCoordinates().get(1));
		NoteData tone;
		if(isGloballySharp(toneValue))
		{
			tone = GuiHelper.getTones().get(GuiHelper.getTonesToSharp().get(toneValue));
		}
		else if(isSharp(toneValue))
		{
			tone = GuiHelper.getTones().get(GuiHelper.getTonesToSharp().get(toneValue));
			GuiHelper.getTemporarySharpTones().put(toneValue, false);
		}
		else
		{
			tone = GuiHelper.getTones().get(toneValue);
		}
		Note note = new Note(tone, LENGTH);	
		SONG.add(note);
		Integer[] data = {toneValue, SONG.indexOf(note)};
		return data;
	}
	
	public static NoteData getMidiTone(Integer toneValue)
	{
		if(Staff.getActiveStaff() != 1)
			toneValue = toneValue - (Staff.getStaffBeginningCoordinates().get(Staff.getActiveStaff()) - Staff.getStaffBeginningCoordinates().get(1));
		NoteData tone;
		if(isGloballySharp(toneValue))
		{
			tone = GuiHelper.getTones().get(GuiHelper.getTonesToSharp().get(toneValue));
		}
//		else if(isSharp(toneValue))
//		{
//			tone = GuiHelper.getTones().get(GuiHelper.getTonesToSharp().get(toneValue));
//			GuiHelper.getTemporarySharpTones().put(toneValue, false);
//		}
		else
		{
			tone = GuiHelper.getTones().get(toneValue);
		}
		
		return tone;
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
	
	public static boolean isSharp(Integer toneValue)
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
	
	public static Lengths getLENGTH() {
		return LENGTH;
	}


	public static void setLENGTH(Lengths lENGTH) {
		LENGTH = lENGTH;
	}


	public static Song getSong() {
		return SONG;
	}
	
	public static void setSong(Song song) {
		SONG = song;
	}

}
