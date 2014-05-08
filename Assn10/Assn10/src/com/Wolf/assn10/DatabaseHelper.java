package com.Wolf.assn10;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{

	public DatabaseHelper(Context context) 
	{
		super(context, "WordList.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		//tables
		ArrayList<String> ArrSQL = new ArrayList<String>();
		
		ArrSQL.add( "Create table Words (ID int, Word text);");
		ArrSQL.add( "Create table Questions (ID int, WordID int, Def text, IsCorrect int);"); //sqlite does not have boolean
		//words
		/*
		ArrSQL.add( "Insert into Words(ID, Word) VALUES (1,'Word1');");
		ArrSQL.add( "Insert into Words(ID, Word) VALUES (2,'Word2');");		
		ArrSQL.add( "Insert into Words(ID, Word) VALUES (3,'Word3');");
		ArrSQL.add( "Insert into Words(ID, Word) VALUES (4,'Word4');");		
		ArrSQL.add( "Insert into Words(ID, Word) VALUES (5,'Word5');");				
		//Questions
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (1,1,'DefWord1Right',1);");
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (2,2,'DefWord2Right',1);");		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (3,3,'DefWord3Right',1);");	
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (4,4,'DefWord4Right',1);");		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (5,5,'DefWord5Right',1);");		
		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (6,1,'DefWord1Wrong',0);");
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (7,2,'DefWord2Wrong',0);");		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (8,3,'DefWord3Wrong',0);");	
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (9,4,'DefWord4Wrong',0);");		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (10,5,'DefWord5Wrong',0);");		
		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (11,1,'DefWord1Wrong',0);");
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (12,2,'DefWord2Wrong',0);");		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (13,3,'DefWord3Wrong',0);");	
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (14,4,'DefWord4Wrong',0);");		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (15,5,'DefWord5Wrong',0);");			
		*/
		
		//Using this word choice with repetitive content - using a third table would be more efficient.  
		ArrSQL.add( "Insert into Words(ID, Word) VALUES (1,'Cosmopolitan');");
		ArrSQL.add( "Insert into Words(ID, Word) VALUES (2,'Hurricane');");		
		ArrSQL.add( "Insert into Words(ID, Word) VALUES (3,'Margarita');");
		ArrSQL.add( "Insert into Words(ID, Word) VALUES (4,'Screwdriver');");		
		ArrSQL.add( "Insert into Words(ID, Word) VALUES (5,'Mai Tai');");				
		//Questions
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (1,1,'Vodka',1);");
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (2,2,'Rum',1);");		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (3,3,'Tequila',1);");	
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (4,4,'Vodka',1);");		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (5,5,'Rum',1);");		
		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (6,1,'Tequila',0);");
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (7,2,'Tequila',0);");		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (8,3,'Rum',0);");	
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (9,4,'Rum',0);");		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (10,5,'Tequila',0);");		
		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (11,1,'Rum',0);");
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (12,2,'Scotch',0);");		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (13,3,'Whisky',0);");	
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (14,4,'Tequila',0);");		
		ArrSQL.add( "Insert into Questions (ID, WordID, Def, IsCorrect) VALUES (15,5,'Brandy',0);");			
		
		
		
		for (int i = 0; i < ArrSQL.size(); i++)
		{
			db.execSQL(ArrSQL.get(i));			
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	}
}