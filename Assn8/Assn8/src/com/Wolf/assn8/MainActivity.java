package com.Wolf.assn8;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;


//The winning and new game mechanics are a bit buggy still.  
public class MainActivity extends Activity 
{
	 Point pt = new Point();
	 Board board = new Board(); 

	 
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(new DrawView(this));
	    
	}
	
	public class DrawView extends View implements OnTouchListener
	{
		public DrawView(Context context)
		{
			super(context);
			this.setOnTouchListener(this);    		
		}
	
	    @Override
	    protected void onDraw(Canvas canvas)
	    {
	    	canvas.drawColor(Color.WHITE);
	    	board.Draw(canvas);
	    }
	    
	     
	    public boolean onTouch(View view, MotionEvent event)
	    {
	    	if (event.getAction() ==  MotionEvent.ACTION_DOWN)
	    	{
	    		pt.x = (int) event.getX();
	    		pt.y = (int) event.getY();
	    		if (board.hitTest(pt) != "-1")
	    		{
	    			invalidate();	
	    		}
	    		
	    	}
	    	return true;
	    }
	}
	
	//required 'helper' class
	//0,1,2
	//3,4,5
	//6,7,8
	public class Board
	{
		private int Size = 400;
		private int third = Size/3;
		private Rect[] cells = new Rect[9];
		private int cellsOccupied[] = {0,0,0,0,0,0,0,0,0};//0,1,2 = empty, x, o
		private int CurrentPlayer = 1; //x, then o, then x....
		private int TurnsTaken = 0;
		
		public void NewGame()
		{
			for (int i = 0; i < 9; i++)
			{
				TurnsTaken = 0;
				cellsOccupied[i]= 0;
				CurrentPlayer = 1;
			}				
		}	

		
		public void ChangePlayer()
		{
			if (CurrentPlayer == 1)
			{
				CurrentPlayer = 2;
			}
			else
			{
				CurrentPlayer = 1;
			}
			TurnsTaken++;
		}
		
		public String hitTest(Point pt) 
		{
			for (int i = 0; i < 9; i++)
			{
				if (cells[i].contains(pt.x, pt.y))
				{
					if (cellsOccupied[i]== 0)
					{
					cellsOccupied[i] = CurrentPlayer;
					WinTest();
					GoAndroidGo();
					WinTest();
					}
					else
					{
						Toast.makeText(MainActivity.this, "Cell Taken...", Toast.LENGTH_SHORT).show();
					}
				}
			}
			return null;
		}
		
		public void GameOver(int won, int too, int tree)
		{
			if (cellsOccupied[won] == cellsOccupied[too] && cellsOccupied[too] == cellsOccupied[tree])
			{
				if (cellsOccupied[won] > 0)
				{
					if (cellsOccupied[won] == 1)
					{
						Toast.makeText(MainActivity.this, "Player Wins", Toast.LENGTH_LONG).show();
						ShowDialogBox();
					}
					else
					{
						Toast.makeText(MainActivity.this, "Android Wins", Toast.LENGTH_LONG).show();
						ShowDialogBox();
					}	
				}
			}
			else if (TurnsTaken == 9)
			{
				Toast.makeText(MainActivity.this, "No Winner", Toast.LENGTH_LONG).show();		
				ShowDialogBox();
			}
		}
		
		public void ShowDialogBox()
		{
			AlertDialog.Builder alertDialog= new AlertDialog.Builder(MainActivity.this); 
            alertDialog.setTitle("New Game?");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
            {
                @Override
                public void onClick(DialogInterface dialog, int which) 
                {
                	NewGame();
                }
            });
            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() 
            {
                @Override
                public void onClick(DialogInterface dialog, int which) 
                {
                	//kills app
                	MainActivity.this.finish();
                	System.exit(0);
                }
            });
            alertDialog.show();
		}
		
		public void WinTest()
		{
			//not actually game over..just all the winning combos.
				GameOver(0,1,2);
				GameOver(3,4,5);
				GameOver(5,6,7);
				GameOver(0,3,6);
				GameOver(1,4,7);
				GameOver(2,5,8);
				GameOver(0,4,8);
				GameOver(2,4,6);
		}
		
		public void DrawX(Point current, Canvas canvas, Paint paint)
		{
			canvas.drawLine(current.x, current.y, current.x+third, current.y+third, paint);
			canvas.drawLine(current.x+third, current.y, current.x, current.y+third, paint);
		}
		
		public void DrawO(Point current, Canvas canvas, Paint paint)
		{
			canvas.drawCircle(current.x+third/2, current.y+third/2, third/2, paint);
		}
		
		
		public void GoAndroidGo()
		{
			ChangePlayer();
			boolean TurnComplete = false;
			while(TurnComplete == false && TurnsTaken < 9)
			{
				int[] AndroidAI = {4,0,2,6,8,1,3,5,7};
				for (int i = 0;i < 9; i++)
				{
					if (cellsOccupied[AndroidAI[i]] == 0)
					{
						cellsOccupied[AndroidAI[i]] = 2;
						TurnComplete = true;
						ChangePlayer();
						break;
					}					
				}
			}
		}
		
		public void Draw(Canvas canvas) 
		{
	    	Paint paint = new Paint();
	    	paint.setColor(0xFFFF0000);
	    	paint.setStyle(Style.STROKE);

			int Cnt = 0;
			Point current = new Point(0,0);
			//create rects
			for (int i = 0; i < 3; i++)//vertical
			{
				for (int k = 0; k <3; k++)//horizontal
				{
					cells[Cnt] = new Rect(current.x, current.y, current.x+third, current.y+third);
					Cnt++;
					current.x+=third;
				}
				current.x=0;
				current.y+=third;
			}
			//draw rects
			for (int i = 0; i < 9; i++)
			{
				canvas.drawRect(cells[i], paint);
			}
			//draw shapes
			Cnt = 0;
			current.x = 0;
			current.y = 0;
			for (int i = 0; i < 3; i++)//vertical
			{
				for (int k = 0; k <3; k++)//horizontal
				{
					if (cellsOccupied[Cnt] ==2)
					{
						DrawO(current, canvas, paint);
					}
					else if (cellsOccupied[Cnt] == 1)
					{
						DrawX(current, canvas, paint);						
					}
					Cnt++;
					current.x+=third;
				}
				current.x=0;
				current.y+=third;
			}
		}
	}	
}