package com.Entity;

public class User 
{
	private int user_id;
	private String user_name;
	private String user_email;
	private String user_password;
	private String user_retypepassword;
	
	public User(String user_name, String user_email, String user_password, String user_retypepassword)
	{
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_retypepassword = user_retypepassword;
	}
	
	public User(int user_id,String user_name, String user_email, String user_password, String user_retypepassword)
	{
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_password = user_password;
		this.user_retypepassword = user_retypepassword;
	}
	
	public User(String user_email, String user_password)
	{
		this.user_email = user_email;
		this.user_password = user_password;
	}
	
	public void setUsername(String user_name)
	{
		this.user_name = user_name;
	}

	public void setUseremail(String user_email)
	{
		this.user_email = user_email;
	}
	
	public void setUserpassword(String user_password)
	{
		this.user_password = user_password;
	}
	
	public void setUserretypepassword(String user_retypepassword)
	{
		this.user_retypepassword = user_retypepassword;
	}
	
	public String getUsername()
	{
		return this.user_name;
	}
	
	public String getUseremail()
	{
		return this.user_email;
	}
	
	public String getUserpassword()
	{
		return this.user_password;
	}
	
	public String getUserretypepassword()
	{
		return this.user_retypepassword;
	}

	public int getUser_id() {
		return this.user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
