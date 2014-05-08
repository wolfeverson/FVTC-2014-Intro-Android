package com.Wolf.assn7;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	//Gets EVERY book onCreate.  Then sorts/displays as needed.
	TextView txtTitle;
	TextView txtAuthor;
	TextView txtDate;
	TextView txtPublisher;
	TextView txtPrice;
	ImageView imgMain;
	Spinner spinAuthor;
	Spinner spinTitles;
	ArrayList<Book> Books;
	ArrayList<Book> AuthBookList;
	
	String FooteURL = "http://itweb.fvtc.edu/foote/Android/AmazonXML/";
	String FileURL = "FileList.txt";
	String AuthorURL;

	@Override
	protected void onCreate(Bundle Kitten) 
	{
		super.onCreate(Kitten);
		setContentView(R.layout.activity_main);
		//variables
		Log.i("appState","appStart");
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtAuthor = (TextView)findViewById(R.id.txtAuthor);
		txtDate = (TextView)findViewById(R.id.txtDate);
		txtPublisher = (TextView)findViewById(R.id.txtPublisher);
		txtPrice = (TextView)findViewById(R.id.txtPrice);
		imgMain = (ImageView)findViewById(R.id.imgMain);
		Spinner spinAuthor = (Spinner)findViewById(R.id.spinList);
		//get data
		String AuthList = GetWebContent(FooteURL + FileURL);
		ArrayList<String> AuthorArray = StringExplode(AuthList);
		Books = GetBooks(AuthorArray);
		//SetFirstSpinner
		try
		{
			ArrayAdapter<String> Authoradapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, AuthorArray);
			Authoradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinAuthor.setAdapter(Authoradapter);
			spinAuthor.setOnItemSelectedListener(new OnItemSelectedListener()
			{
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,int position, long id) 
				{
					String SpinnerItem = parent.getItemAtPosition(position).toString();	
					onAuthorSelected(SpinnerItem);
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) 
				{
					//do nothing
				}
			});
		}
		catch (Exception ex)
		{
			Log.i("FirstSpinner", "Failed");
		}
	}
	public void onTitleSelected(int _pos)
	{
		Book tempbook = AuthBookList.get(_pos);
		txtTitle.setText(tempbook.Title);
		txtAuthor.setText(tempbook.Authors);
		txtDate.setText(tempbook.Date);
		txtPublisher.setText(tempbook.Publisher);
		txtPrice.setText(tempbook.Price);
		imgMain.setImageBitmap(LoadImage(tempbook.Image));
	}
	public Bitmap LoadImage(String _url)
	{
		Bitmap bmp = null;
		try
		{
			URL aURL = new URL(_url);
			URLConnection conn = aURL.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			bmp = BitmapFactory.decodeStream(bis);
			bis.close();
			is.close();
		}
		catch (Exception ex)
		{
			Log.i("LoadImage", "Failed");
		}
		return bmp;
	}

	
	public void onAuthorSelected(String _auth)
	{
		//Assuming .xml name is the same as the first author on the book
		String auth = _auth.substring(0, _auth.length()-4); //removes ".xml"
		AuthBookList = new ArrayList<Book>();
		if (auth == "AlbertTerhune")
		{
			auth = "AlbertPaysonTerhune";
		}
		else if (auth == "GordonSHaight")
		{
			auth = "GordonS.Haight";
		}

		for (int i = 0; i < Books.size(); i++)
		{
			String MainAuth = Books.get(i).MainAuthor.replace(" ", "").trim(); //removes spaces from array.  
			if (MainAuth.equalsIgnoreCase(auth))
			{
				AuthBookList.add(Books.get(i));
			}
		}	
		//SetSecondSpinner
		try
		{
			Spinner spinTitles = (Spinner)findViewById(R.id.spinTitles);
			ArrayAdapter<Book> TitleAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_spinner_item, AuthBookList);
			TitleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);			
			spinTitles.setAdapter(TitleAdapter);
			spinTitles.setOnItemSelectedListener(new OnItemSelectedListener()			
			{
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,int position, long id) 
				{
					//String SpinnerItem = parent.getItemAtPosition(position).toString();	
					onTitleSelected(position);
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) 
				{
					//do nothing
				}
			});
		}
		catch (Exception ex)
		{
			Log.i("SecondSpinner", "Failed");
		}
	}
	
	public ArrayList<Book> GetBooks(ArrayList<String> _auth)
	{
		try
		{
			Books = new ArrayList<Book>();
			int m = _auth.size();
			for (int i = 0; i < m; i++)
			{
				String currAuthor = _auth.get(i);
				String BookXML = GetWebContent(FooteURL + currAuthor);
				ConvertXMLtoBooks(BookXML);
			}
		}
		catch (Exception ex)
		{
			Log.i("GetBooks", "Failed");
		}
		return Books;
	}
	
	public void ConvertXMLtoBooks(String _input)
	{
		try
		{
			//Input - Entire Author XML Sheet
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(_input)));
			NodeList nlDetails = doc.getElementsByTagName("Details");
			int numDetails = nlDetails.getLength();
			for (int i = 0; i < numDetails ; i++) 
			{
				//Get Specific Book
				Element nDetails = (Element)nlDetails.item(i);
				//GetTitle
				NodeList nlProductName = nDetails.getElementsByTagName("ProductName");
				Node nProductName = nlProductName.item(0);
				String Title = nProductName.getFirstChild().getNodeValue();
				//GetAuthors - Storing them all as one string - Lazy.
				NodeList nlAuthors = nDetails.getElementsByTagName("Author");	
				int numAuthors =nlAuthors.getLength();
				String Authors = "";
				String mainAuth = "";
				for (int k = 0; k < numAuthors; k++)
				{
					Node nAuthor = nlAuthors.item(k);
					String tempAuth = nAuthor.getFirstChild().getNodeValue();
					if (k == 0)
					{
						Authors = tempAuth;
						mainAuth = tempAuth;
					}
					else
					{
						Authors = Authors + ", " + tempAuth;						
					}
				}	
				//GetPublisher
				NodeList nlPublisher = nDetails.getElementsByTagName("Manufacturer");
				Node nPublisher = nlPublisher.item(0);
				String Publisher = nPublisher.getFirstChild().getNodeValue();
				//GetDate
				NodeList nlDate = nDetails.getElementsByTagName("ReleaseDate");
				Node nDate = nlDate.item(0);
				String Date = nDate.getFirstChild().getNodeValue();				
				//GetPrice - as string.
				NodeList nlPrice = nDetails.getElementsByTagName("ListPrice");
				Node nPrice = nlPrice.item(0);
				String Price = nPrice.getFirstChild().getNodeValue();					
				//Get Img - URL
				NodeList nlImage = nDetails.getElementsByTagName("ImageUrlMedium");
				Node nImage = nlImage.item(0);
				String Image = nImage.getFirstChild().getNodeValue();					
				//Put together....
				Book tempbook = new Book(Title, mainAuth, Authors, Publisher, Date, Price, Image);
				Books.add(tempbook);
			}
		}
		catch (Exception ex)
		{
			Log.i("ConvertXMLtoBooks", "Failed");
		}
	}
	
	public String GetWebContent(String _path)
	{
		String Response = "";
		try
		{
			HttpClient client = new DefaultHttpClient();
			ResponseHandler<String> responsehandler = new BasicResponseHandler();		
			HttpGet getMethod = new HttpGet(_path);
			Response = client.execute(getMethod, responsehandler); //point of failure
		}
		catch (Exception ex)
		{
			Log.i("GetContent", "Failed");
			Response = null;
		}
		return Response;
	}
	
	public ArrayList<String> StringExplode(String _string)
	{
		try
		{
			String[] boom = _string.split("\\r?\\n");
			ArrayList<String> List = new ArrayList<String>(Arrays.asList(boom));
			return List;
		}
		catch (Exception ex)
		{
			Log.i("StringExplode", "Failed");
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 

	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
