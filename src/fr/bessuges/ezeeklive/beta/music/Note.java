package fr.bessuges.ezeeklive.beta.music;

public enum Note {
	
	C3(130.81), C3s(138.59), D3(146.83), D3s(155.56), E3(164.81), F3(174.61), F3s(185.00), G3(196.00), G3s(207.65),
	A3(220.00), A3s(233.08), B3(246.94),
	
	C4(261.63), C4s(277.18), D4(293.66), D4s(311.13), E4(329.63), F4(349.23), F4s(369.99), G4(392.00), G4s(415.30),
	A4(440.00), A4s(466.16), B4(493.88),
	
	C5(523.25), C5s(554.37), D5(587.33), D5s(622.25), E5(659.26), F5(698.46), F5s(739.99), G5(783.99), G5s(830.61),
	A5(880.00), A5s(932.33), B5(987.77);
	
	private double mFrequency;
	
	
	Note(double frequency){
		mFrequency = frequency;
	}

	public double frequency() {
		return mFrequency;
	}

}
