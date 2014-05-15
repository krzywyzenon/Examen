package composer.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import composer.advancedgui.shapes.NoteDrawing;

/**
 * 
 * @author Tomek
 * Class holding information about connections between graphic and sound representations of notes in the song
 *
 */
public class SoundDrawRelations implements Serializable
{
	
	private static final long serialVersionUID = -4102369720630056643L;
	private static Map<NoteDrawing, Integer> drawingsAndSoundsRelations = new HashMap<NoteDrawing, Integer>();
	
	public static Map<NoteDrawing, Integer> getDrawingsAndSoundsRelations() 
	{
		return drawingsAndSoundsRelations;
	}
	public static void setDrawingsAndSoundsRelations
	(
			Map<NoteDrawing, Integer> drawingsAndSoundsRelations) {
		SoundDrawRelations.drawingsAndSoundsRelations = drawingsAndSoundsRelations;
	}
	
	public static void resetRelations()
	{
		drawingsAndSoundsRelations = new HashMap<NoteDrawing, Integer>();
	}

}
