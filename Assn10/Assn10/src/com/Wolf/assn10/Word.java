package com.Wolf.assn10;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Word 
{
	int WordID;
	String Word;
	String[] Answers = new String[4];//is a 4 array to have a 1-3.  there is no 0
	int CorrectAnswer;
	int SelectedAnswer;
	
	@Override
	public String toString()
	{
		return Word;
	}
	
	public Word()
	{
		
	}

	public Word Populate(int _id, MainActivity context)
	{
		//populates one word at a time - as needed
		Word tempWord = new Word();
		DatabaseHelper helper;
		SQLiteDatabase db;
        helper = new DatabaseHelper(context.getApplicationContext());
        db = helper.getWritableDatabase();		
        String sSql = "Select * from Words Where ID = " + _id;
    	Cursor cursor = db.rawQuery(sSql, null);
    	cursor.moveToFirst();
    	tempWord.WordID = Integer.parseInt(cursor.getString(0));
    	tempWord.Word = cursor.getString(1);
    	//get questions
    	sSql = "select * from Questions Where WordId = " + _id + " ORDER BY RANDOM()";
    	Cursor questions = db.rawQuery(sSql, null);
    	questions.moveToFirst();
    	for (int i = 1; i <=3; i++)//1-3, no 0
    	{   
    		if (Integer.parseInt(questions.getString(3)) == 1)
    		{
    			tempWord.CorrectAnswer = i;
    		}		
    	tempWord.Answers[i] = questions.getString(2);     
    	questions.moveToNext();
    	}   
    	return tempWord;
	}
}
