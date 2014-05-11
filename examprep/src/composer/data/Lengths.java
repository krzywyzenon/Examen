package composer.data;

public enum Lengths implements MidiDataExtractor
{
	EIGHT(200),
	QUARTER(400),
	HALF(800),
	WHOLE(1600);
	
	Integer length;
	
	private Lengths(Integer length)
	{
		this.length = length;
	}

	@Override
	public Integer value() 
	{
		return length;
	}


}
