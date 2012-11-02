package fr.bessuges.ezeeklive.beta.players.pad;

import android.os.Handler;
import fr.bessuges.ezeeklive.beta.audio.AudioPlayer;
import fr.bessuges.ezeeklive.beta.music.Note;

public class NotePlayer implements PadPlayer {
	
	private AudioPlayer mAudioPlayer;
	private Handler handler = new Handler();
	private Note [] notes = {
			Note.E3, Note.F3, Note.F3s, Note.G3, Note.G3s, Note.A3, Note.A3s, Note.B3, 
			Note.C4, Note.C4s, Note.D4, Note.D4s, Note.E4, Note.F4, Note.F4s, Note.G4, Note.G4s, Note.A4, Note.A4s, Note.B4,
			Note.C5, Note.C5s, Note.D5, Note.D5s, Note.E5, Note.F5, Note.F5s, Note.G5, Note.G5s, Note.A5, Note.A5s, Note.B5
		};
	
	
	public NotePlayer(){
		mAudioPlayer = new AudioPlayer();
	}

	@Override
	public void touch(int position) {
		if(position >= notes.length) return;

		final int notePosition = position;		
		final Thread thread = new Thread(new Runnable() {
            public void run() {
            	mAudioPlayer.genTone(notes[notePosition].frequency());
                handler.post(new Runnable() {

                    public void run() {
                    	mAudioPlayer.playSound();
                    }
                });
            }
        });
        thread.start();
		
	}

	@Override
	public int getRowsCount() {
		return 4;
	}

	@Override
	public int getColsCount() {
		return 8;
	}

}
