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
		Thread.sleep(250);
				
	    
	    for(Object o : song)
	    {
	    	Note note = (Note) o;
	    	mc[5].programChange(instr.getPatch().getProgram());
	    	mc[5].noteOn(note.getTone(), VolumeController.getVolume());
			
	    	Thread.sleep(note.getLength());
	    	if(song.indexOf(note)<song.size() -1)
	    	mc[5].noteOff(note.getTone());
	    	
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