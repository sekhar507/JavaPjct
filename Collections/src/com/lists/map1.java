package com.lists;

import java.util.HashMap;
import java.util.Map;

public class map1
{
	public static void main(String args[])
	{
		/*Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(22,"sekhar");
		for(Map.Entry<Integer, String> entry : map.entrySet()){
            System.out.printf("Key : %s and Value: %s %n", entry.getKey(),
                                                       entry.getValue());
        }*/
		Map<Integer,String> m1=new HashMap<Integer,String>();
		m1.put(1,"Sekhar");
		m1.put(2,"sandeep");
		m1.put(3,"dileep");
		m1.put(4,"avenesh");
		m1.put(5,"linga");
		m1.put(6,"pramod");
		m1.put(7,"karthik");
		for(Map.Entry<Integer,String> entry:m1.entrySet())
		{
			System.out.printf("Key:value %s: %s %n ",entry.getKey(),entry.getValue());
		}
		
	}
}