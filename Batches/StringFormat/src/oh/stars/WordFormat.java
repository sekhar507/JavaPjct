package oh.stars;

import java.util.Scanner;

public class WordFormat {
	public static void main(String args[])
	{
		System.out.println("Enter Your String....");
		Scanner toread=new Scanner(System.in);
		String print=toread.nextLine();
		System.out.println(print);
		 String[] arr = print.split(" ");    

		 for ( String ss : arr) {

		       System.out.println(ss);
		       
		  }
		
	}

}
