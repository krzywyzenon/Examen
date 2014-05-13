package composer.data;

/**
 * 
 * @author Tomek
 * Enumeration with the instruments which are available to the user
 *
 */
public enum Instruments implements MidiDataExtractor{
	PIANO(0),
	HARPSICHORD(6),
	TUBULAR_BELL(14),
	ORGANS(19),
	VIOLIN(40),
	CELLO(42),
	CONTRABAS(43);
	
	private Integer instrumentNumber;
	
	private Instruments(Integer instrument)
	{
		this.instrumentNumber = instrument;
	}

	@Override
	public Integer value() {
		return instrumentNumber;
	}

}
