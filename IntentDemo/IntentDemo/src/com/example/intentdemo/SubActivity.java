package com.example.intentdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SubActivity extends Activity 
{
	private static final String TAG = "myDebug";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);
        
        Button addButton = (Button)findViewById(R.id.btnAdd);
        //setup listener
        addButton.setOnClickListener(new View.OnClickListener() 
	        {@Override
				public void onClick(View v) 
				{
	        		//write to sharedpref
	        		SharedPreferences details = getSharedPreferences("button", MODE_PRIVATE);
	        		SharedPreferences.Editor editor = details.edit();
	        		editor.clear();
	        		editor.putString("button", "add");
	        		editor.commit();
					Log.println(Log.DEBUG, TAG, "add");
					finish();					
				}});
		//listener end
        Button backButton = (Button)findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() 
	        {@Override
				public void onClick(View v) 
				{
	        		SharedPreferences details = getSharedPreferences("button", MODE_PRIVATE);
	        		SharedPreferences.Editor editor = details.edit();
	        		editor.clear();
	        		editor.putString("button", "back");
	        		editor.commit();
					Log.println(Log.DEBUG, TAG, "back");
					finish();
				}});    
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
