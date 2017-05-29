package org.core.java;

import java.util.ArrayList;

public class Coll {

public static void main(String args[])
{
Coll ObjName=new Coll();
ObjName.arrayList();

}
public void arrayList()
{
	ArrayList<String> name = new ArrayList<String>();
	name.add("Sekhar");
	name.add("Dilip");
	name.add("priyatham");
	name.add("pramod");
	name.add("sandy");
	for(String s:name)
	{
		System.out.println(s);
	}
	
}
}
