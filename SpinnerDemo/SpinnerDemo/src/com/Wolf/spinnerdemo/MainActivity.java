package com.Wolf.spinnerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

public class MainActivity extends Activity  implements AdapterView.OnItemSelectedListener
{
	private static final String[] items={"lorem", "ipsum", "dolor", "sit", "amet", "consectetuer", "adipiscing", "elit", "morbi", "vel", "ligula", 
		"vitae", "arcu", "aliquet", "mollis", "etiam", "vel", "erat", "placerat", "ante", "porttitor", "sodales", "pellentesque", "augue", "purus"};
	private TextView selection;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selection = (TextView)findViewById(R.id.selection);        
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        
        spinner.setOnItemSelectedListener(this);
        
        //bindingSource
        ArrayAdapter<String> arr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        
        //select from array
        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        //hookup
        spinner.setAdapter(arr);
    }

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) 
	{
		// TODO Auto-generated method stub
		selection.setText(items[position]);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) 
	{
		// TODO Auto-generated method stub
		selection.setText("Mrr");
	}   
}
