package com.Wolf.app4;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	TextView txtMain;
	ImageView imgMain;
	
	int[] imgIds = { -1, R.drawable.rainbowdashlarge, R.drawable.celestialarge, R.drawable.lunalarge };
	int[] txtIds = { -1, R.raw.rainbow, R.raw.celestia, R.raw.luna};
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMain = (TextView)findViewById(R.id.txtViewMain);
        imgMain = (ImageView)findViewById(R.id.imgViewMain);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public void showRain(View v)
    {
    	imgMain.setImageResource(R.drawable.rainbowdashlarge);
    	txtMain.setText(readFile(R.raw.rainbow));
    }
    
    public void showLuna(View v)
    {
    	imgMain.setImageResource(R.drawable.lunalarge);
    	txtMain.setText(readFile(R.raw.luna));
    }   
    
    public void showCelestia(View v)
    {
    	imgMain.setImageResource(R.drawable.celestialarge);
    	txtMain.setText(readFile(R.raw.celestia));   	
    }
    
    private String readFile(int fileId)
    {
    	InputStream is;
    	InputStreamReader isr;
    	BufferedReader br;
    	StringBuffer sb;
    	
    	try
    	{
    		is=getResources().openRawResource(fileId);
    		isr=new InputStreamReader(is);
    		br = new BufferedReader(isr);
    		sb = new StringBuffer();
    		
    		String s;
    		while((s = br.readLine()) != null)
    		{
    			sb.append(s + "\n");
    		}
    		br.close();
    		isr.close();
    		is.close();
    	}
    	catch (Exception ex)
    	{
    		return "Error";
    	}
    	return sb.toString();
    	
    }
    
}
