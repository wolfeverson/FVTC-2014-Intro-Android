package com.Wolf.touchdemo;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity 
{
	Point pt = new Point();
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }   
    
    class DrawView extends View implements OnTouchListener
    {
    	public DrawView(Context context)
    	{
    		super(context);
    		this.setOnTouchListener(this);
    	}
    	
    	@Override
    	protected void onDraw(Canvas canvas)
    	{
    		canvas.drawColor(Color.DKGRAY);
    		Paint paint = new Paint();
    		paint.setColor(Color.MAGENTA);
    		canvas.drawCircle(pt.x, pt.y, 5, paint);
    	}
    	

		@Override
		public boolean onTouch(View view, MotionEvent event) 
		{
			//Every touch generates a down, zero or more moves, and up.
			//if (event.getAction() == MotionEvent.ACTION_DOWN)
			//{
				pt.x = (int)event.getX();
				pt.y = (int)event.getY();
				invalidate();
			//}
			
			
			
			
			return true;
		}
    }
}
