package org.core.java;

public class StringOperations {
	public static void main(String args[])
	{	
		String name="Sekhar";
		if(name.startsWith("Se"))
		{
			System.out.println("String starts with Se");
		}
		 if(name.endsWith("ar"))
		{
			System.out.println("String ends with ar");
		}
		 System.out.println(name.charAt(2));
		 String newText = name.replace(' ', '/');
		 String myString = "Hema" + " Sekhar";
		  
		    System.out.println(myString);
	}

}
