package com.Wolf.geolocation;

public class Forecast 
{
	String time="";
	Integer temp=null;
	String iconUrl="";
	
	String getTime() {
		return(time);
	}

	void setTime(String time) {
		this.time=time.substring(0,16).replace('T', ' ');
	}
	
	Integer getTemp() {
		return(temp);
	}
	
	void setTemp(Integer temp) {
		this.temp=temp;
	}
	
	String getIcon() {
		return(iconUrl);
	}
	
	void setIcon(String iconUrl) {
		this.iconUrl=iconUrl;
	}
}

