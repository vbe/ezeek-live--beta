package fr.bessuges.ezeeklive.beta.players.pad;

import fr.bessuges.ezeeklive.beta.R;
import fr.bessuges.ezeeklive.beta.R.layout;
import fr.bessuges.ezeeklive.beta.music.Note;
import fr.bessuges.ezeeklive.beta.players.pad.PadView.OnTouchTapListener;
import android.app.Fragment;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PadPlayerFragment extends Fragment {
	
	private PadView mPad;
	private PadPlayer mPlayer;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		mPad = (PadView) inflater.inflate(R.layout.frag__player, container, false);
		
		mPad.SetOnTouchTapListener(new OnTouchTapListener() {
			@Override
			public void onTap(int touchNumber) {
				if(mPlayer != null){
					mPlayer.touch(touchNumber);
				}
			}
		});	
		
		return mPad;
	}
	
	public void setPlayer(PadPlayer player){
		mPlayer = player;
		mPad.setRowsCount(player.getRowsCount());
		mPad.setColsCount(player.getColsCount());
		mPad.invalidate();
	}

}
