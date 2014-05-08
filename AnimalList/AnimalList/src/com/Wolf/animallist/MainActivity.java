package com.Wolf.animallist;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	TextView txtMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtMain = (TextView)findViewById(R.id.txtViewMain);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
    	menu.add(Menu.NONE, 1, Menu.NONE, "Add Animal");
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
    	addAnimalDialog();
    	return true;
    }
    
    public void addAnimalDialog()
    {
    	LayoutInflater inflater = LayoutInflater.from(this);
    	final View addView = inflater.inflate(R.layout.add, null);
    	
    	new AlertDialog.Builder(this).setTitle("Add Animal").setView(addView).
    			setPositiveButton("Ok", new DialogInterface.OnClickListener() 
    			{
    				public void onClick(DialogInterface dialog, int whichButton)
    				{
		    			EditText txtAnimal = (EditText)addView.findViewById(R.id.txtNewItem);
		    			String sAnimal = (txtAnimal).getText().toString();
		    			TextView txtMain = (TextView)findViewById(R.id.txtViewMain);
		    			txtMain.append("\n\n" + sAnimal);
	    			}
    			})
    			
    			.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    			{
    				public void onClick(DialogInterface dialog, int whichButton)
    				{
    					//nothing
    				}
    			}).show();
    	
    }
    
}
