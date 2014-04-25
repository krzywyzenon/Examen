package composer.data;

public enum Lengths implements NoteData
{
	EIGHT(125),
	QUARTER(250),
	HALF(500),
	WHOLE(1000);
	
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
