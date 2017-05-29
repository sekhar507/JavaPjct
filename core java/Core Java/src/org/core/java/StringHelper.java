package org.core.java;

public class StringHelper {
public String swap(String str)
{
	int length=str.length();
	if(length<2)
		return str;
	String strMin2char=str.substring(0,length-2);
	char secondLastChar=str.charAt(length-2);
	char lastChar=str.charAt(length-1);
	return strMin2char+lastChar+secondLastChar;
	
}
}

