package composer.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveAndLoad 
{
	public static void saveSong(State state, File file) throws FileNotFoundException, IOException
	{
		ObjectOutputStream output = null;
		
		output = new ObjectOutputStream(new FileOutputStream(file));
		
		if(output != null)
		{
			output.writeObject(state);
			output.close();
		}
	}
	
	public static State loadSong(File file) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		State state = null;
		ObjectInputStream input = null;
		input = new ObjectInputStream(new FileInputStream(file));
		state = (State) input.readObject();
		input.close();
		return state;
	}

}
