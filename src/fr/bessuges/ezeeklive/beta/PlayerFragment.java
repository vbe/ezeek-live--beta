package fr.bessuges.ezeeklive.beta;

import fr.bessuges.ezeeklive.beta.views.PadView;
import fr.bessuges.ezeeklive.beta.views.PadView.OnTouchTapListener;
import android.app.Fragment;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlayerFragment extends Fragment {
	
	private PadView mPad;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		mPad = (PadView) inflater.inflate(R.layout.frag__player, container, false);
		
		mPad.SetOnTouchTapListener(new OnTouchTapListener() {
			@Override
			public void onTap(int touchNumber) {
				final int note = touchNumber;
				final Thread thread = new Thread(new Runnable() {
		            public void run() {
		                genTone(note);
		                handler.post(new Runnable() {

		                    public void run() {
		                        playSound();
		                    }
		                });
		            }
		        });
		        thread.start();
			}
		});	
		
		return mPad;
	}
	
	
	
	
	private final int duration = 3; // seconds
    private final int sampleRate = 8000;
    private final int numSamples = duration * sampleRate;
    private final double sample[] = new double[numSamples];
    private final double [] freqOfTone = {261.63, 293.66, 329.63, 349.23, 392.00, 440.00, 493.88, 523.25}; // hz

    private final byte generatedSnd[] = new byte[2 * numSamples];

    Handler handler = new Handler();


    void genTone(int index){
        // fill out the array
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone[index]));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (final double dVal : sample) {
            // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

        }
    }

    void playSound(){
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.play();
    }

}
