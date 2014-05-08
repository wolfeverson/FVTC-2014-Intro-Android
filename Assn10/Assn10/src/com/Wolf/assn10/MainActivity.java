package com.Wolf.assn10;
//pewpewRAWR
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	int CurrentQ = 0;
	Word[] Questions = new Word[6];//1-5, no 0;
	TextView Question;
	RadioButton radOne;
	RadioButton radTwo;	
	RadioButton radThree;	

    @Override
    protected void onCreate(Bundle Kitten) 
    {
        super.onCreate(Kitten);
        setContentView(R.layout.activity_main);  
        for (int i = 1; i < 5; i++)
        {
        	Questions[i]= null;
        }
        TextView Question = (TextView)findViewById(R.id.txtQuestion);     
        Question.setText("Guess the main ingredient for these drinks.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onClickNext(View view)
    {
    	if (CurrentQ < 5)
    	{
			CurrentQ += 1;
    		if (Questions[CurrentQ] == null)
    		{
    			Word tempWord = new Word();
    			tempWord = tempWord.Populate(CurrentQ, this);
    			Questions[CurrentQ] = tempWord;
    		}
    		showQuestion(CurrentQ);
    	}
    	else
    	{
    		//last question
    		Toast.makeText(this, "End of the Line", Toast.LENGTH_LONG).show();
    	}
    }
    
    public void onClickPrevious(View view)
    {
    	if (CurrentQ > 1)
    	{
			CurrentQ -= 1;
    		showQuestion(CurrentQ);
    	}
    	else
    	{
    		//first question
    		Toast.makeText(this, "The Beginning", Toast.LENGTH_LONG).show();
    	}
    }
    
    public void radOneClicked(View view)
    {
    	Questions[CurrentQ].SelectedAnswer = 1;
    }
    
    public void radTwoClicked(View view)
    {
    	Questions[CurrentQ].SelectedAnswer = 2;   	
    }   
    
    public void radThreeClicked(View view)
    {
    	Questions[CurrentQ].SelectedAnswer = 3;    	
    }    
    
    public void onClickGrade(View view)
    {
		int Points = 0;
    	for (int i = 1; i < 6; i++)
    	{
    		if (Questions[i].CorrectAnswer == Questions[i].SelectedAnswer)
    		{
    			Points++;
    		}
    	}
    	Toast.makeText(this, "You scored " + String.valueOf(Points) + " points!", Toast.LENGTH_LONG).show();
    }
       
    public void showQuestion(int _id)
    {
    	TextView Question = (TextView)findViewById(R.id.txtQuestion);     
    	RadioButton radOne = (RadioButton)findViewById(R.id.radOne);
    	RadioButton radTwo = (RadioButton)findViewById(R.id.radTwo);   	
    	RadioButton radThree = (RadioButton)findViewById(R.id.radThree);  
    	
    	Word tempWord = Questions[_id];
    	
    	Question.setText(tempWord.Word);  
    	radOne.setText(tempWord.Answers[1]);
    	radTwo.setText(tempWord.Answers[2]);
    	radThree.setText(tempWord.Answers[3]);

    	
    	switch(tempWord.SelectedAnswer)
    	{
    	case 1:
    		radOne.setChecked(true);
    		radTwo.setChecked(false);
    		radThree.setChecked(false);   
    		break;
    	case 2:
    		radOne.setChecked(false);
    		radTwo.setChecked(true);
    		radThree.setChecked(false);     		
    		break;
    	case 3:
    		radOne.setChecked(false);
    		radTwo.setChecked(false);
    		radThree.setChecked(true);      		
    		break;
    	case 0:
    		radOne.setChecked(false);
    		radTwo.setChecked(false);
    		radThree.setChecked(false);      		
    		break;   		
    	}
    }
}
