package fr.bessuges.ezeeklive.beta.views;

import fr.bessuges.ezeeklive.beta.views.gestures.TapGestureListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class PadView extends View {
	private Paint mTouchStrokePaint;
	private Paint mTouchBackgroundPaint;
	private RectF [] mTouchesOutline, mTouchesBackground;
	private int rows = 2, cols = 4;
	private float [] mColsX, mRowsY; 
	
	private GestureDetector mTapDetector;
	
	private OnTouchTapListener mOnTouchTapListener = null;
	
	private static final int STROKE_WIDTH = 5;

	public PadView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mTapDetector = new GestureDetector(this.getContext(), new TapGestureListener());	
		init();
	}
	
	public void SetOnTouchTapListener(OnTouchTapListener listener){
		mOnTouchTapListener = listener;
	}

	private void init() {
		mTouchStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mTouchStrokePaint.setColor(Color.BLACK);
		mTouchStrokePaint.setStrokeWidth(5);

		mTouchBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mTouchBackgroundPaint.setColor(Color.LTGRAY);
		mTouchBackgroundPaint.setStrokeWidth(STROKE_WIDTH);
		
		
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		float xpad = (getPaddingLeft() + getPaddingRight());
		float ypad = (getPaddingTop() + getPaddingBottom());
		
		float ww = (float)w - xpad;
		float hh = (float)h - ypad;
		
		createTouches(ww, hh); 
	}
	
	
	// TODO onMeasure : http://developer.android.com/training/custom-views/custom-drawing.html
	
	private void createTouches(float ww, float hh) {
		mColsX = new float[cols];
		mRowsY = new float[rows];
		mTouchesOutline = new RectF[rows * cols];
		mTouchesBackground = new RectF[rows * cols];
		
		float wTouch = ww / cols, hTouch = hh / rows;
		int count = 0;
		for(int row = 0 ; row < rows ; row++){
			float top = row * hTouch, bottom = top + hTouch;
			mRowsY[row] = bottom;
			for(int col = 0 ; col < cols ; col++){
				float left = col * wTouch, right = left + wTouch;		
				mColsX[col] = right;					

				mTouchesOutline[count] = new RectF(left, top, right, bottom);
				mTouchesBackground[count] = new RectF(left + STROKE_WIDTH, top + STROKE_WIDTH, 
						right - STROKE_WIDTH, bottom - STROKE_WIDTH);
				count++;
			}
			
		}
	}

	@Override
	protected void onDraw(Canvas canvas){
		for(RectF touch : mTouchesOutline){
			canvas.drawRect(touch, mTouchStrokePaint);
		}		
		
		for(RectF touch : mTouchesBackground){
			canvas.drawRect(touch, mTouchBackgroundPaint);
		}		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean handled = false;
		if(mTapDetector.onTouchEvent(event)){
			handleTap(event);
			handled = true;
		}
		return handled;
	}

	private void handleTap(MotionEvent event) {int row = -1, col = -1;
		float lower = 0;		
		for(int i = 0 ; i < rows ; i++){
			if(event.getY() >= lower && event.getY() < mRowsY[i]){
				row = i;
				break;
			}
			lower = mRowsY[i];
		}
		
		lower = 0;
		for(int i = 0 ; i < cols ; i++){
			if(event.getX() >= lower && event.getX() < mColsX[i]){
				col = i;
				break;
			}
			lower = mColsX[i];
		}
		System.out.println("touch: #" + (row * cols + col));	
		if(mOnTouchTapListener != null){
			mOnTouchTapListener.onTap(row * cols + col);
		}	
	}
	
	public interface OnTouchTapListener {
		public abstract void onTap(int touchNumber);
	}
}
