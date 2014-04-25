package composer.controller;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

import composer.sound.Note;
import composer.sound.Song;

public class Player implements Runnable
{
	Synthesizer synth = null;
	MidiChannel[] mc;
	Soundbank sB;
	Instrument[] i;
	Song song;

	public Player(Song song)
	{
		this.song = song;
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
		} catch (MidiUnavailableException e) {
			
			e.printStackTrace();
		}
		
		mc = synth.getChannels();
		sB = synth.getDefaultSoundbank();
		i = sB.getInstruments();
		synth.loadInstrument(i[0]);
	}
    
	public void playSong(Song song) throws InterruptedException
	{
		Thread.sleep(250);
				
		int waitAfterLast = 0;
	    
	    for(Object o : song)
	    {
	    	//mc[5].controlChange(7, 1000);
	    	Note note = (Note) o;
	    	mc[5].noteOn(note.getTone(), VolumeController.getVolume());
			
	    	Thread.sleep(note.getLength());
	    	
	    	waitAfterLast = note.getLength();
	    }
	    
	    
		Thread.sleep(waitAfterLast);
			
	}
	
    @Override
	public void run() 
    {
    	try 
    	{
			playSong(song);
		} 
    	catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
    }
}
