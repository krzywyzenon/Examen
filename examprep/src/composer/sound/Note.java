package composer.sound;

import java.io.Serializable;

import composer.data.MidiDataExtractor;

public class Note implements Serializable
{

	private static final long serialVersionUID = 324375778088678131L;
	private Integer tone;
	private Integer length;
	
	
	public Note()
	{
		
	}
	public Note(MidiDataExtractor tone, MidiDataExtractor length)
	{
		this.tone = tone.value();
		this.length = length.value();
	}
	
	public Integer getTone() {
		return tone;
	}
	public void setTone(MidiDataExtractor tone) {
		this.tone = tone.value();
	}
	
	public Integer getLength() {
		return length;
	}
}
