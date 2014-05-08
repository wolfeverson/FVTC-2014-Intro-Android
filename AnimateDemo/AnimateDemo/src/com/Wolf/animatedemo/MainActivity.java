package com.Wolf.animatedemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener
{
	Button btnMove;
	TextView txtView;
	int nCount = 0;
	private static final String TAG = "myDebug";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnMove = (Button)findViewById(R.id.btnMove);
        txtView = (TextView)findViewById(R.id.txtView);
        btnMove.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View arg0) 
	{
		txtView.setText("Count : " + nCount++);
		Animation move = AnimationUtils.loadAnimation(this, R.anim.move);
		txtView.startAnimation(move);
	}
    
}
