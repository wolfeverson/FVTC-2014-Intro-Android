package com.Wolf.assn6;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity 
{
	fileIO ofileIO;
	ArrayList<Item> Items = new ArrayList<Item>();
    ListView listview;
    ArrayAdapter<Item> myarrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);    	
   
        ofileIO = new fileIO();
        String sb = ofileIO.readFile(this);
        Items = ConvertFile(sb);            
        
        ArrayAdapter<Item> myarrayAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_multiple_choice, Items);     	


        

        
        listview = getListView();    
        listview.setAdapter(myarrayAdapter);
        listview.setItemsCanFocus(false);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        menu.add(Menu.NONE, 1 , Menu.NONE, "Save");
        menu.add(Menu.NONE, 2 , Menu.NONE, "Add");              
        menu.add(Menu.NONE, 3 , Menu.NONE, "RemoveChecked");        
        menu.add(Menu.NONE, 4 , Menu.NONE, "RemoveAll");   
        menu.add(Menu.NONE, 5 , Menu.NONE, "Refresh");  //Curse android's asynchronous model. 
        return true;
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        Log.i("hit", Integer.toString(Items.size()));	 
    	super.onListItemClick(l, v, position, id);
    	Items.get(position).isCheck = !(Items.get(position).isCheck);
    }
    
    //@Override
    //public boolean onMenuItemSelected(MenuItem item)
    public boolean onOptionsItemSelected(MenuItem item) 
    {
	        switch (item.getItemId()) 
	        {
	        	case 1:
		        {
		        	ofileIO.writeFile(this, Items);
		        	break;
		        }
	        	case 2:
		        {
		        	AddItemDialog();        	
		        	break;
		        }	        
	        	case 3:
		        {
		        	for (int k = 0; k < Items.size() ; k++)
		        	{
		        		if (Items.get(k).isChecked())
		        		{
		        			Items.remove(k);
		        		}
		        	}     	
		        	break;
		        }	 
	        	case 4:
		        {
		        	Items.clear(); 	        	
		        	break;
		        }	 
	        	case 5:
	        	{
	        		//do stuff.  This refreshes.  Which happens anyways, but not in time on other clicks.
	        		break;
	        	}
	        }  


        
        myarrayAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_multiple_choice, Items);          
        listview = getListView();    
        listview.setAdapter(myarrayAdapter);
        listview.setItemsCanFocus(false);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);        
        
        return true;
    }

    public ArrayList<Item> ConvertFile(String _string)
    {
    	ArrayList<Item> temps = new ArrayList<Item>();    	
    	String boom[] = _string.split("\\|");  //have to escape pipes.  Not sure why.  Otherwise returns char array
    	int m = boom.length - 1; // This process adds a blank line at the end...
    	Log.i("hit", String.valueOf(m));
    	for (int i = 0; i < m; i++)
    	{
    		String boom2[] = boom[i].split(",");
    		Item tempitem = new Item(boom2[0], Boolean.valueOf(boom2[1]));
    		temps.add(tempitem);
    	}
    	
    	
    	return temps;
    }
    
    public void AddItemDialog()
    {
    	LayoutInflater inflater = LayoutInflater.from(this);
    	final View addView = inflater.inflate(R.layout.add, null);
    	
    	new AlertDialog.Builder(this).setTitle("Add Item").setView(addView)
    			.setPositiveButton("Ok", new DialogInterface.OnClickListener() 
    			{
    				public void onClick(DialogInterface dialog, int whichButton)
    				{
		    			EditText txtNew = (EditText)addView.findViewById(R.id.txtNewItem);
		    			String sItem = (txtNew).getText().toString();
		    			Item tempItem = new Item(sItem);
		    			Items.add(tempItem);    			
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

    
