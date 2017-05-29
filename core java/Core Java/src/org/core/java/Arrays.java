package org.core.java;
public class Arrays {
	public static void main(String args[])
	{
		int marks[]={1,2,3,4,5,6};
		//array declaring with unlimited size is 
	 								//	int marks=int[];		
		
		for(int i=0;i<marks.length;i++)
		 //foreach loop
			for(int mark: marks)
		{
			i=i+mark;
			System.out.println(i);
		}
		
	}
}
