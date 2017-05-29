package org.core.java;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyClassTest {
	
	 @Test
	  public void testMultiply() {
	    MyClass tester = new MyClass();
	    assertEquals(200, tester.multiply(20, 10));
	  }

}
