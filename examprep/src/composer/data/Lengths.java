package composer.data;

public enum Lengths implements MidiDataExtractor
{
	EIGHT(250),
	QUARTER(500),
	HALF(1000),
	WHOLE(2000);
	
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
