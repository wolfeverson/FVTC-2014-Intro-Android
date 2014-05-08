package com.Wolf.listviewdemo;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity 
{
	private static final String[] items={"lorem", "ipsum", "dolor", "sit", "amet", "consectetuer", "adipiscing", "elit", "morbi", "vel", "ligula", 
		"vitae", "arcu", "aliquet", "mollis", "etiam", "vel", "erat", "placerat", "ante", "porttitor", "sodales", "pellentesque", "augue", "purus"};
	private TextView selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hook up array to listview
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        selection = (TextView)findViewById(R.id.selection);
    }
    
    public void onListItemClick(ListView parent, View v, int position, long id)
    {
    	Toast.makeText(this, "Toast.", Toast.LENGTH_LONG).show();
    	//set text view to selected item in list
        selection.setText(items[position]);
    }
    
}
