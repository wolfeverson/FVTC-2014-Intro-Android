package com.Wolf.browserdemo2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends Activity 
{
	WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        browser = (WebView)findViewById(R.id.webView);
        String html ="<html><body><p>Hello Cruel World!</p></body></html>";
        browser.loadData(html, "text/html", "UTF-8");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
