package com.sai.rest.pojo;

import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = -6114781478676199561L;
	private String firstName;
	private String lastName;
	private boolean isAdmin;
	
	public User()
	{
		//default constructor
	}
	
	public User(String firstName,String lastName,boolean isAdmin)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public boolean isAdmin()
	{
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}
	
	@Override
	public boolean equals(Object object)
	{		
		if(object != null && object instanceof User)
		{
			User user = (User)object;
			return this.firstName.equals(user.getFirstName()) && this.lastName.equals(user.getLastName());
		}
		else
		{
			return false;
		}
	}
}
