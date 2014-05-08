package com.Wolf.geolocation;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.Toast;
import org.w3c.dom.Element;

public class MainActivity extends Activity 
{

	private LocationManager mgr = null;
	private String format;
	private WebView browser;
	private HttpClient client;
	private List<Forecast> forecasts = new ArrayList<Forecast>();
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mgr = (LocationManager)getSystemService(LOCATION_SERVICE);
        format = getString(R.string.url);
        browser = (WebView)findViewById(R.id.webkit);
        
        client = new DefaultHttpClient();
    }

    @Override
    public void onResume()
    {
    	super.onResume();
    	mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3600000, 1000, onLocationChange);
    }

    @Override
    public void onPause()
    {
    	super.onPause();
    	mgr.removeUpdates(onLocationChange);
    }
    
    @Override
    public void onDestroy()
    {
    	super.onDestroy();
    	client.getConnectionManager().shutdown();
    }
    
    private void updateForecast(Location loc)
    {
    	String url = String.format(format, loc.getLatitude(), loc.getLongitude());
    	HttpGet getMethod = new HttpGet(url);
    	try
    	{
    		ResponseHandler<String> responsehandler = new BasicResponseHandler();
    		String responsebody = client.execute(getMethod, responsehandler);
    		
    		buildForecasts(responsebody);
    		String page = generatePage();
    		browser.loadDataWithBaseURL(null, page, "text/html", "UTF-8", null);
    	}
    	catch (Throwable t)
    	{
    		android.util.Log.e("WeatherDemo", "Exception fetching data", t);
    		Toast.makeText(this, "Request Failed", Toast.LENGTH_LONG).show();
    	}
    }
    
    void buildForecasts(String _raw) throws Exception
    {
    	//process the xml response
		DocumentBuilder builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
		Document doc=builder.parse(new InputSource(new StringReader(_raw)));
		NodeList times=doc.getElementsByTagName("start-valid-time");
		
		for (int i=0;i<times.getLength();i++) 
		{
			Element time=(Element)times.item(i);
			Forecast forecast=new Forecast();
			forecasts.add(forecast);
			forecast.setTime(time.getFirstChild().getNodeValue());
		}
		NodeList temps=doc.getElementsByTagName("value");
		
		for (int i=0;i<temps.getLength();i++) 
		{
			Element temp=(Element)temps.item(i);
			Forecast forecast=forecasts.get(i);	
			forecast.setTemp(new Integer(temp.getFirstChild().getNodeValue()));
		}	
		NodeList icons=doc.getElementsByTagName("icon-link");
		
		for (int i=0;i<icons.getLength();i++) 
		{
			Element icon=(Element)icons.item(i);
			Forecast forecast=forecasts.get(i);
			forecast.setIcon(icon.getFirstChild().getNodeValue());
		}
    }
    
    String generatePage()
    {
		StringBuilder bufResult=new StringBuilder("<html><body><table>");
				
		bufResult.append("<tr><th width=\"50%\">Time</th><th>Temperature</th><th>Forecast</th></tr>");
		
		for (Forecast forecast : forecasts) {
			bufResult.append("<tr><td align=\"center\">");
			bufResult.append(forecast.getTime());
			bufResult.append("</td><td align=\"center\">");
			bufResult.append(forecast.getTemp());
			bufResult.append("</td><td><img src=\"");
			bufResult.append(forecast.getIcon());
			bufResult.append("\"></td></tr>");
		}
		
		bufResult.append("</table></body></html>");
		
		return(bufResult.toString());

    }
    
    LocationListener onLocationChange = new LocationListener()
    {
    	public void onLocationChanged(Location loc)
    	{
    		updateForecast(loc);
    	}

		@Override
		public void onProviderDisabled(String arg0) 
		{
			//Do nothing
		}

		@Override
		public void onProviderEnabled(String arg0) 
		{
			//do nothing
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) 
		{
			//do nothing
		}
    };
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }     
}
