package composer.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import composer.advancedgui.Page;

/**
 * 
 * @author Tomek
 * Controller class for page objects - contains information about the number of pages in the current song and about which page is active.
 * The class enables clearing the pages of all the notes.
 */
public class PageController 
{
	private static Map<Integer, Page> PAGES = new HashMap<>();
	static
	{
		PAGES.put(1, new Page());
	}
	
	private static Integer activePage = 1;
	
	public static Map<Integer, Page> getPages() 
	{
		return PAGES;
	}
	
	public static void setPages(Map<Integer, Page> pages) 
	{
		PAGES = pages;
	}

	public static Integer getActivePage() {
		return activePage;
	}

	public static void setActivePage(Integer activePage) {
		PageController.activePage = activePage;
	}
	
	public static void clearNotes()
	{
		for(Entry<Integer, Page> entry : PAGES.entrySet())
		{	
			entry.getValue().getDrawnNotes().clear();
			if(entry.getKey() != 1)
			{
				PAGES.remove(entry.getKey());
			}
		}
	}

}
