package com.Wolf.assn6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.ListActivity;
import android.util.Log;

public class fileIO 
{
	private static final String sFile = "savefile.txt";
	
    public void writeFile(ListActivity activity, ArrayList<Item> Items)
    {
    	try
    	{	
	    	OutputStreamWriter out = new OutputStreamWriter(activity.openFileOutput(sFile, 0));
	    	String allstring = "";
	        Log.i("hit", "pre");
	        Log.i("hit", Integer.toString(Items.size()));	        
	    	for (int k = 0; k < Items.size(); k++)
	    	{
	    			if (k < Items.size())
	    			{
	    				String thisstring;
	    				thisstring = Items.get(k).name + "," + Items.get(k).isCheck + "|" + "\r\n";
	    				out.write(thisstring);
	    				allstring += thisstring;
	    			}
	    			else
	    			{
	    				String thisstring;
	    				thisstring = Items.get(k).name + "," + Items.get(k).isCheck + "|";
	    				out.write(thisstring);
	    				allstring += thisstring;
	    			}
	    		}
	    	out.close();
    	}
    	catch (java.io.FileNotFoundException e)
    	{
    		//Mrr.
    	}
    	catch (Exception ex)
    	{
    		//Mrr
    	}  
    }
    
    public String readFile(ListActivity activty)
    {
    	String sRet = "";
    	try
    	{
    		InputStream is = activty.openFileInput(sFile);
    		
    		if (is != null)
    		{
    			InputStreamReader isr = new InputStreamReader(is);
    			BufferedReader br = new BufferedReader(isr);
    			String sLine = null;
    			StringBuffer sb = new StringBuffer();
    			
    			do  //this line isn't typed correctly?
    			{
    				sLine = br.readLine();
    				sRet = sRet + sLine;
    			} while (sLine != null);
    			is.close();
    			//sRet = sb.toString();
    		}
    	}
    	catch (java.io.FileNotFoundException e)
    	{
    		//Mrr.
    	}
    	catch (Exception ex)
    	{
    		//Mrr
    	}    	
    	Log.i("ItemIO", "ended successfully");
    	return sRet;    	
    }
}
