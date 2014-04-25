package garbage;

import composer.sound.Note;

public class SongWriter {
	
	public static void addNoteToSong(Note note, SongOld song)
	{
		song.getSong().add(note);
	}

}
