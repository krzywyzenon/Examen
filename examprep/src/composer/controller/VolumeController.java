package composer.controller;

public class VolumeController 
{
	private static Integer VOLUME = 600;
	
	public static Integer getVolume() 
	{
		return (VOLUME == null) ? 600 : VOLUME;
	}

	public static void setVolumeUp(Integer volume) 
	{
		VOLUME = (VOLUME == null) ? 0 : VOLUME;
		VOLUME += volume;
	}
	
	public static void setVolumeDown(Integer volume)
	{
		VOLUME = (VOLUME == null) ? 0 : VOLUME;
		VOLUME = (VOLUME > 0) ? VOLUME - volume : 0; 
	}
	
	public static void resetVolume()
	{
		VOLUME = null;
	}

}
