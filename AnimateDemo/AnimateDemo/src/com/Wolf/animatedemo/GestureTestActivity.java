package com.Wolf.animatedemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class GestureTestActivity extends Activity implements OnGestureListener
{
	private static final String TAG = "myDebug";
	private int iCardNo = 0;
	private boolean IsFront = true;
	private GestureDetector gd;
	TextView txtView;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        
        gd = new GestureDetector(this);
		txtView = (TextView)findViewById(R.id.txtView);
		txtView.setText("onCreate");
		
	}
	
	@Override
	public boolean onDown(MotionEvent arg0) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) 
	{
		Log.v(TAG, "Fling");
		int x1 = (int)arg0.getX();
		int x2 = (int)arg1.getX();
		
		String s;
		int numCards = 3;
		
		if (x1 < x2) //fling left...move right
		{
			Animation move = AnimationUtils.loadAnimation(this, R.anim.moveright);
			move.setAnimationListener(new MyAnimationListener());
			txtView.startAnimation(move);
			iCardNo = (iCardNo + numCards - 1) % numCards;
			s = "Fling Right : Previous Card : " + iCardNo;
		}
		else //fling right... move left
		{
			Animation move = AnimationUtils.loadAnimation(this, R.anim.moveleft);
			move.setAnimationListener(new MyAnimationListener());
			txtView.startAnimation(move);
			iCardNo = (iCardNo + numCards + 1) % numCards;
			s = "Fling Left : Previous Card : " + iCardNo;
		}
		txtView.setText(s);
		return true;
	}

	@Override
	public void onLongPress(MotionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override 
	public boolean onTouchEvent(MotionEvent me)
	{
		return gd.onTouchEvent(me);
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	class MyAnimationListener implements Animation.AnimationListener
	{

		@Override
		public void onAnimationEnd(Animation animation) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationRepeat(Animation animation) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationStart(Animation animation) 
		{
			// TODO Auto-generated method stub
			
		}
		
	}
}
