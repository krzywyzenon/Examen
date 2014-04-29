package composer.data;

public enum Lengths implements NoteData
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
	public Integer tone() 
	{
		return null;
	}

	@Override
	public Integer length() 
	{
		return length;
	}

}
