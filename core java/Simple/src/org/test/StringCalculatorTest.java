package org.test;
import static org.junit.Assert.*;

import org.junit.Test;

public class StringCalculatorTest {
	
	@Test
	public void add(){	
		int sum = stringCalculator.add(40, 20);
		assertEquals(sum, 60);	
	}
	
	@Test
	public void fullNameTest(){
		
		String result = stringCalculator.checkNameLenght("Saikumar", "Shankaiahgari");
		assertEquals(result, "failed");
	}
	

}
