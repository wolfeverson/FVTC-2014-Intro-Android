package com.Wolf.assn6;

public class Item 
{
	 String name;
	 boolean isCheck;
	
	public Item(String _Name)
	{
		this.name = _Name;
		this.isCheck = false;
	}
	public Item(String _Name, boolean Checked)
	{
		this.name = _Name;
		this.isCheck = Checked;		
	}
	
	public boolean isChecked()
	{
		return isCheck;
	}
	
	public void SetCheck(boolean _Check)
	{
		this.isCheck = _Check;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
