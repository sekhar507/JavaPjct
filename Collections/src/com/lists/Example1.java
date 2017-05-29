package com.lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Example1 {

	
	public static void main(String args[])
	{
		ArrayList<String> list=new ArrayList<String>();
		list.add("sekhar");
		list.add("sandeep");
		list.add("dileep");
		Collections.sort(list);
		/*Iterator i =null;
		i=list.iterator();
		while(i.hasNext())
		{
			System.out.println(i.next());
		}*/
		for(String j : list)
		{
			System.out.println(j);
		}
		
	
	
	}
	
	
}