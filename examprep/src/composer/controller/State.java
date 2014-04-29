package composer.controller;

import java.io.Serializable;
import java.util.Map;

import composer.advancedgui.Page;
import composer.sound.Song;

public class State implements Serializable 
{
	private static final long serialVersionUID = -7805892302184595880L;
	
	private Song song;

	private Map<Integer, Page> pages;
	
	public State(Song song, Map<Integer, Page> pages)
	{
		this.song = song;
		this.pages = pages;
	}

	public Song getSong() {
		return song;
	}
	
	public Map<Integer, Page> getPages() {
		return pages;
	}
}
