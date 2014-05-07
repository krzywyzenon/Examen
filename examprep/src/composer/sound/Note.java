package composer.sound;

import java.io.Serializable;

import composer.data.NoteData;

public class Note implements Serializable
{

	private static final long serialVersionUID = 324375778088678131L;
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
	public void setTone(NoteData tone) {
		this.tone = tone.tone();
	}
	
	public Integer getLength() {
		return length;
	}
}
