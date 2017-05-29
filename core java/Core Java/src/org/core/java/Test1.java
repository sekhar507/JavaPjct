package org.core.java;
import static org.junit.Assert.*;

import org.junit.Test;


public class Test1 {
	
	
	StringHelper help=new StringHelper();
	@Test
	public void testReverse2()
	{
		assertEquals("BA",help.swap("AB"));
	}
	@Test
	public void testReverse4()
	{
		assertEquals("ABCD",help.swap("ABDC"));
	}
	@Test
	public void testReverse10()
	{
		assertEquals("hemasekhar",help.swap("hemasekhra"));
	}
	

}
