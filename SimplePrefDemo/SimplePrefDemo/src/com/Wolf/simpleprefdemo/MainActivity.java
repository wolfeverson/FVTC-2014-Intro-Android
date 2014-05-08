package com.Wolf.simpleprefdemo;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	private static final int EDIT_ID = Menu.FIRST + 2;
	private TextView checkbox = null;
	private TextView ringtone = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        checkbox = (TextView)findViewById(R.id.checkbox);
        ringtone = (TextView)findViewById(R.id.ringtone);
    }
    @Override
    public void onResume()
    {
    	super.onResume();
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    	checkbox.setText(new Boolean(prefs.getBoolean("checkbox", false)).toString());
    	ringtone.setText(prefs.getString("ringtone", "<unset>"));
    }
   


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        //return true;
    	menu.add(Menu.NONE, EDIT_ID, Menu.NONE, "Edit Perfs").
    		setIcon(R.drawable.ic_launcher)
    		.setAlphabeticShortcut('e');
    	
    	return (super.onCreateOptionsMenu(menu));
    	
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch (item.getItemId())
    	{
    	case EDIT_ID:
    		startActivity(new Intent(this, EditPreferences.class));
    		return (true);
    	}    	
    	
    	return (super.onOptionsItemSelected(item));
    }
    
}
