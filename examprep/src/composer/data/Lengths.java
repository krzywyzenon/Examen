package composer.data;

/**
 * 
 * @author Tomek
 * Enumeration with note lengths
 */
public enum Lengths implements MidiDataExtractor
{
	EIGHT(200),
	QUARTER(400),
	HALF(800),
	WHOLE(1600),
	REST(0);
	
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
