package fr.bessuges.ezeeklive.beta.players.pad;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;

public interface PadPlayer {	
	public abstract void touch(int position);
	public abstract int getRowsCount();
	public abstract int getColsCount();
}
