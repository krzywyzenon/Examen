package composer.advancedgui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PageController 
{
	private final static Map<Integer, Page> PAGES = new HashMap<>();
	static
	{
		PAGES.put(1, new Page());
	}
	
	private static Integer activePage = 1;
	
	public static Map<Integer, Page> getPages() 
	{
		return PAGES;
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
