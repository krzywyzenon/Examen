package composer.data;

public enum Tones implements MidiDataExtractor
{
	A1(57),
	AIS1(58),
	B1(59),
	C(60),
	CIS(61),
	D(62),
	DIS(63),
	E(64),
	F(65),
	FIS(66),
	G(67),
	GIS(68),
	A(69),
	AIS(70),
	B(71),
	C2(72),
	CIS2(73),
	D2(74),
	DIS2(75),
	E2(76),
	F2(77),
	FIS2(78);
	
	private Integer tone;
	
	
	private Tones(Integer tone)
	{
		this.tone = tone;
	}
	
	public Integer value()
	{
		return tone;
	}
	

}
