package com.Wolf.rectdemo;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

public class MainActivity extends Activity 
{
	Rect[] cells = new Rect[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {

    	
        super.onCreate(savedInstanceState);
        setContentView(new ShowRects(this));
    }
    
    
    private class ShowRects extends View
    {
    	public ShowRects(Context context)
    	{
    		super(context);
    	}
    	@Override
    	protected void onDraw(Canvas canvas)
    	{
    		//create and draw rectangle
    		Rect r = new Rect();
    		r.left = 80;
    		r.right = 30;		//These are coordinates.  (Top, Left) , (Bottom, Right)
    		r.top = 30;
    		r.bottom = 80;
    		
    		Paint paint = new Paint();
    		paint.setColor(Color.rgb(100, 149, 237));  
    		canvas.drawColor(Color.MAGENTA);
    		//canvas.drawRect(r, paint);
    		
    		//draw outline
    		paint.setStyle(Style.STROKE);
    		paint.setColor(Color.BLACK);
    		//canvas.drawRect(r, paint);
    		//create a series of rectangles
 
    		for (int k = 0; k < cells.length; k ++)
    		{
    			cells[k] = new Rect();
    			cells[k].left = 50 + k * 20;
    			cells[k].top = 50 + k * 20;
    			cells[k].right = 100 + k * 20;
    			cells[k].bottom = 100+ k * 20;
        		paint.setStyle(Style.FILL);
        		paint.setColor(Color.rgb(100, 149, 237));     			
    			canvas.drawRect(cells[k], paint);    	
    			paint.setColor(Color.BLACK);
    			paint.setStyle(Style.STROKE);
    			canvas.drawRect(cells[k], paint);
    		}	
    		
    		//apply to canvas
    	}
    }
    
}
