package composer.controller;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

import composer.data.MidiDataExtractor;
import composer.sound.Note;
import composer.sound.Song;

/**
 * 
 * @author Tomek
 * Class which plays back the song.
 *
 */
public class PlayController implements Runnable
{
	Synthesizer synth = null;
	MidiChannel[] mc;
	Soundbank sB;
	Instrument[] i;
	Instrument instr;
	Song song;

	public PlayController(Song song, MidiDataExtractor instrument)
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
		instr = i[instrument.value()];
		synth.loadInstrument(instr);
	}
    
	public void playSong(Song song) throws InterruptedException
	{
		mc[5].programChange(instr.getPatch().getProgram());
		Thread.sleep(250);
				
	    
	    for(Object o : song)
	    {
	    	Note note = (Note) o;
	    	if(!note.isSilent())
	    	{
	    		mc[5].noteOn(note.getTone(), VolumeController.getVolume());
	    		
	    		Thread.sleep(note.getLength());
	    		Integer currentIndex = song.indexOf(note);
	    		if(song.indexOf(note)<song.size() -1)
	    		{
	    			if(song.get(currentIndex + 1).isSilent())
		    		{
		    			for(int i = 100; i>=0; i--)
		    			{
		    				mc[5].controlChange(7, i);
		    				Thread.sleep(i/5);
		    			}
		    			mc[5].noteOff(note.getTone());
		    			Thread.sleep(200);
		    			mc[5].controlChange(7, 100);
		    		}
	    			else
	    			{
	    				mc[5].noteOff(note.getTone());
	    			}
	    		}
	    		
	    		
	    	}
	    	else
	    	{
//	    		Thread.sleep(note.getLength());
	    		Thread.sleep(note.getLength());
	    	}
	    	
	    }
	    
	    //after playing the last note the sound simply dies out instead of being finished rapidly
		for(int i = 100; i>=0; i--){
			mc[5].controlChange(7, i);
			Thread.sleep(i);
			
		}
		
		synth.close();
		
			
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
