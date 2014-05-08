package com.Wolf.gallery;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		populateMenu(menu);
		return true;
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    
    	int nItem = item.getItemId();
    	imgMain.setImageResource(imgIds[nItem]);
    	txtMain.setText(readFile(txtIds[nItem]));
    	String sItem = item.toString();
    	return true;
    }
    
    private void populateMenu(Menu menu)
    {
    	menu.add(Menu.NONE, 1, Menu.NONE, "Rainbow Dash").setIcon(R.drawable.rainbowdashsmall);
    	menu.add(Menu.NONE, 2, Menu.NONE, "Princess Celestia").setIcon(R.drawable.celestiasmall);
    	menu.add(Menu.NONE, 3, Menu.NONE, "Princess Luna").setIcon(R.drawable.lunasmall);
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
