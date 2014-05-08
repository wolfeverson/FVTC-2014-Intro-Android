package com.Wolf.dragdrop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(new DrawView(this));
	}
	
	class DrawView extends View implements OnTouchListener 
	{
		int lastTouchX, lastTouchY; //last location.
		int currTouchX, currTouchY; //current location
		int dx, dy;  //delta last-current
		boolean bDragging; //touch down (holding)
		Bitmap card = BitmapFactory.decodeResource(getResources(), R.drawable.acespades);
		
		
		public DrawView(Context context)
		{
			super(context);
			this.setOnTouchListener(this);
		}
		
		@Override
		protected void onDraw(Canvas canvas)
		{
			//connects canvas ondraw to base class.  repeated several times/second
			super.onDraw(canvas);
			canvas.drawBitmap(card, currTouchX, currTouchY, null);
		}
		
		@Override
		public boolean onTouch(View view, MotionEvent event) 
		{
			int x = (int)event.getX();
			int y = (int)event.getY();
			
			switch(event.getAction())
			{
				case MotionEvent.ACTION_DOWN:
				{
					//define a rectangle for collision detection.
					Rect bbox = new Rect(currTouchX, currTouchY, currTouchX + 90, currTouchY + 135);
					if (!bbox.contains(x, y))   break;  //if statement with 1-line do not need brackets.
					
					bDragging = true;
					dx = x - lastTouchX;
					dy = y - lastTouchY;
					break;
				}
				case MotionEvent.ACTION_MOVE:
				{
					if (!bDragging) break;
					
					dx = x - lastTouchX;
					dy = y - lastTouchY;
					
					lastTouchX = x;
					lastTouchY = y;
					currTouchX += dx;
					currTouchY += dy;
					invalidate();
					break;					
				}
				case MotionEvent.ACTION_UP:
				{
					if (bDragging)  bDragging = false;
				}
			}
			return true;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
