package com.struts;

public class LoginAction
{
	public String execute()
	{
	
		try
		{
		return "success";
		
		}catch(Exception e)
		{
			return "failure";
		}
	}

}
