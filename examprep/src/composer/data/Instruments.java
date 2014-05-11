package composer.data;

public enum Instruments implements MidiDataExtractor{
	PIANO(0),
	HARPSICHORD(6),
	TUBULAR_BELL(14),
	ORGANS(19),
	VIOLIN(40);
	
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
