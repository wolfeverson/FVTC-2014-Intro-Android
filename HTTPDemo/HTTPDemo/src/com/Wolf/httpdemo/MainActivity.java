package com.Wolf.httpdemo;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	public static final String TAG = "myDebug";
	public static final String URL_BASE = "http://itweb.fvtc.edu/foote/Android/AmazonXML/";
	public static final String FILE_NAME = URL_BASE + "FileList.txt";
	TextView title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		title = (TextView)findViewById(R.id.title);
		Log.println(Log.DEBUG, TAG, "http file : " + FILE_NAME);
		
		//CAll getContent method
		String filelist = getContents(FILE_NAME);
		title.setText(filelist);
	}
	

	private String getContents(String _url) 
	{
		//retrieve contents from webpage;
		String responseBody;
		try
		{
			HttpClient client = new DefaultHttpClient();
			ResponseHandler<String> responsehandler = new BasicResponseHandler();
			HttpGet getMethod = new HttpGet(_url);
			
			Log.println(Log.DEBUG, TAG, "pre");
			
			responseBody = client.execute(getMethod, responsehandler);
		}
		catch (Exception ex)
		{
			Log.println(Log.DEBUG, TAG, "http fail" + ex.getMessage());
			return null;
		}
		
		
		return responseBody;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
