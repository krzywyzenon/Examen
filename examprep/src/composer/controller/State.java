package composer.controller;

import java.io.Serializable;
import java.util.Map;

import composer.advancedgui.Page;
import composer.advancedgui.shapes.NoteDrawing;
import composer.sound.Song;

/**
 * 
 * @author Tomek
 * Object for saving songs
 *
 */
public class State implements Serializable 
{
	private static final long serialVersionUID = -7805892302184595880L;
	
	private Song song;

	private Map<Integer, Page> pages;
	
	//This connects note drawings to the sounds in the song by pointing at sounds' indexes
	private Map<NoteDrawing, Integer> relations;
	
	private String title;
	
	public State(Song song, Map<Integer, Page> pages, Map<NoteDrawing, Integer> relations, String title)
	{
		this.song = song;
		this.pages = pages;
		this.relations = relations;
		this.title = title;
	}

	public Song getSong() {
		return song;
	}
	
	public Map<Integer, Page> getPages() {
		return pages;
	}
	
	public Map<NoteDrawing, Integer> getRelations()
	{
		return relations;
	}

	public String getTitle() {
		return title;
	}
}
