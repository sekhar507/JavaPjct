package com.hello;

import com.opensymphony.xwork2.ActionSupport;

public class LoggingEx extends ActionSupport {
	
	/**
	 * @author HEMA
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	public String execute()
	{
		if(uname.equals("gayathri"))
		{
			return "success";
		}
		else
		{
			return "error";
		}
		
	}
	

}
