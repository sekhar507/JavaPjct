package org.core.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JunitExample {
	@Test
	public void methodName()
	{
		String abcdString = "ABCDT";
		
		/*int i = 10;
		
		int j =20;
		
		int total = i + j;*/
		
		StringTest temp = new StringTest();
		
	String name = 	temp.getName();
		
		assertEquals(name,"kmar");
		
		//assertEquals(20, total);
		
		//assertEquals(true,"ABCD".equalsIgnoreCase(abcdString));
		//assertEquals(9,Math.abs(8) );// if it is equal to  9 then it will give green bar
	
	}

}
