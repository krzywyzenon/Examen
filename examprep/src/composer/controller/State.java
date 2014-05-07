package composer.controller;

import java.io.Serializable;
import java.util.Map;

import composer.advancedgui.Page;
import composer.advancedgui.shapes.NoteDrawing;
import composer.sound.Song;

public class State implements Serializable 
{
	private static final long serialVersionUID = -7805892302184595880L;
	
	private Song song;

	private Map<Integer, Page> pages;
	
	private Map<NoteDrawing, Integer> relations;
	
	public State(Song song, Map<Integer, Page> pages, Map<NoteDrawing, Integer> relations)
	{
		this.song = song;
		this.pages = pages;
		this.relations = relations;
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
}
