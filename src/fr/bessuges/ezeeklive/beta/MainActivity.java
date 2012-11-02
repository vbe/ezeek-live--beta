package fr.bessuges.ezeeklive.beta;

import fr.bessuges.ezeeklive.beta.players.pad.NotePlayer;
import fr.bessuges.ezeeklive.beta.players.pad.PadPlayerFragment;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	PadPlayerFragment mPadPlayer;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__main);
		
		mPadPlayer = (PadPlayerFragment)getFragmentManager().findFragmentById(R.id.frag_player);
		mPadPlayer.setPlayer(new NotePlayer());
	}

}
