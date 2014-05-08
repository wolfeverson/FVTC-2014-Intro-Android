package edu.fvtc.stringsdemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
{

	// Modular declarations
	private static final String TAG = "myDebug";
	TextView lblName;
	EditText txtName;
	ImageView imgSeafood;
	
	int[] imgIds = { -1, R.drawable.lobster, R.drawable.shrimp, R.drawable.fish };
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lblName = (TextView)findViewById(R.id.lblName);
        txtName = (EditText)findViewById(R.id.txtName);
        imgSeafood = (ImageView)findViewById(R.id.imgSeafood);
        
        // Make a Toast
        Toast.makeText(this, "I <3 Android!", 5000).show();
        
        // Write to the log
        Log.println(Log.DEBUG, TAG, "I really <3 Android!!");
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
    	Toast.makeText(this, item.toString(), 5000).show();
    	
    	// Get the Id of the menuitem selected
    	int iItem = item.getItemId();
    	
    	// Set the image based the item id
    	imgSeafood.setImageResource(imgIds[iItem]);
    	return true;
    }
    
    private void populateMenu(Menu menu)
    {
    	menu.add(Menu.NONE, 1, Menu.NONE, "Lobster").setIcon(R.drawable.lobster);
    	menu.add(Menu.NONE, 2, Menu.NONE, "Shrimp").setIcon(R.drawable.shrimp);
    	menu.add(Menu.NONE, 3, Menu.NONE, "Fish").setIcon(R.drawable.fish);
    }
    
    
    public void ShowName(View v)
    {
    	String format = getString(R.string.funkyformat);
    	String sName = String.format(format, TextUtils.htmlEncode(txtName.getText().toString()));
    	lblName.setText(Html.fromHtml(sName));
    }

	
    
}
