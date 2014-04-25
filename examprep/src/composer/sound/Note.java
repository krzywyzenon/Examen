package composer.sound;

import composer.data.NoteData;

public class Note
{

	private Integer tone;
	private Integer length;
	
	
	public Note()
	{
		
	}
	public Note(NoteData tone, NoteData length)
	{
		this.tone = tone.tone();
		this.length = length.length();
	}
	
	public Integer getTone() {
		return tone;
	}
	
	public Integer getLength() {
		return length;
	}
}
