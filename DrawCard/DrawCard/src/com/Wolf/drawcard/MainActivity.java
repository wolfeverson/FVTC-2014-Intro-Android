package com.Wolf.drawcard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity 
{
	Bitmap bmpAce;
	public static int CARD_WIDTH = 170;
	public static int CARD_HEIGHT = 205;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
        bmpAce = createAceofDiamonds();
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
    		super.onDraw(canvas);
    		Paint paint = new Paint();
    		canvas.drawColor(0xFFC0C0C0);
    		canvas.drawBitmap(bmpAce, 50,	70, paint);
    	}
    }
    
    public Bitmap createAceofDiamonds()
    {
    	Bitmap.Config conf = Bitmap.Config.ARGB_8888;
    	Bitmap bmp = Bitmap.createBitmap(76, 100, conf);
    	Canvas canvas = new Canvas(bmp);
    	Paint paint = new Paint();
    	paint.setColor(Color.RED);
    	paint.setStyle(Style.FILL);
    	
    	Point[] p = new Point[4];
    	p[0] = new Point(38, 0);
    	p[1] = new Point(75, 50);    	
    	p[2] = new Point(38, 99);    	
    	p[3] = new Point(0, 50);    	
    	
    	
    	Path path = new Path();
    	path.moveTo(p[0].x, p[0].y);
    	for (int k = 1; k < p.length; k++)
    	{
    		path.lineTo(p[k].x, p[k].y);
    	}
   		path.lineTo(p[0].x, p[0].y);
    	
   		canvas.drawPath(path, paint);
    	
    	return bmp;
    }
}
