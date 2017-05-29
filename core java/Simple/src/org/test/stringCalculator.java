package org.test;

public class stringCalculator {

	public static int add(int i, int j) {
		// TODO Auto-generated method stub
		return i + j;
	}

	public static void checkNameLenght() {
		// TODO Auto-generated method stub
		
	}

	public static String checkNameLenght(String string1, String string2) {
		
		String fullName = string1 + string2;
		
		String result = "";
		
		if(fullName.length() >= 21){
			result = "failed";
		} else {
			result = "passed";
		}
		
		return result;
	}

}
