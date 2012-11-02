package fr.bessuges.ezeeklive.beta.views.gestures;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class TapGestureListener extends GestureDetector.SimpleOnGestureListener {
	@Override
	public boolean onDown(MotionEvent e) {
		return true;
	}
}
