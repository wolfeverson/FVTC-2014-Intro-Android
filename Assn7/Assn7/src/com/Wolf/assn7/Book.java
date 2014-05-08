package com.Wolf.assn7;


public class Book 
{
	String Title;
	String MainAuthor;
	String Authors;
	String Publisher;
	String Date;
	String Price;
	String Image; //holds URL?
	
	public Book(String _title, String _mainAuth, String _Authors, String _pub, String _date, String _price, String _image)
	{
		Title = _title;
		MainAuthor = _mainAuth;
		Authors = _Authors;
		Publisher = _pub;
		Date = _date;
		Price = _price;
		Image = _image;
	}
	
	@Override
	public String toString()
	{
		return Title;
	}
}
