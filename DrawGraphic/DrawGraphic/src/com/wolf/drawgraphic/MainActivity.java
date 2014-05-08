package com.wolf.drawgraphic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	Bitmap bmp;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(new DrawView(this));
		
		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
		bmp = Bitmap.createBitmap(300, 300, conf);
		
		//Create canvas using bitmap
		Canvas canvas = new Canvas(bmp);
		
		Paint paint = new Paint();
		paint.setColor(0xFFFF0000);  //Red - alpha, red, green, blue
		canvas.drawRect(0, 0, 200, 200, paint);
	}
	
	class DrawView extends View 
	{

		public DrawView(Context context) 
		{
			super(context);
		}
		
		@Override
		protected void onDraw(Canvas canvas)
		{
			//draw a red(paint) rectangle on a cyan canvas
			super.onDraw(canvas);
			Paint paint = new Paint();
			canvas.drawColor(Color.CYAN);			
			canvas.drawBitmap(bmp,  0,  0, paint);
			paint.setColor(Color.GRAY);
			paint.setTextAlign(Paint.Align.CENTER);
			paint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD_ITALIC));
			paint.setTextSize(35);
			
			canvas.drawText("Meow", 120, 160, paint);
			canvas.drawLine(0, 50, 100, 50, paint);
			canvas.drawLine(50, 0, 50, 100, paint);
			
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
