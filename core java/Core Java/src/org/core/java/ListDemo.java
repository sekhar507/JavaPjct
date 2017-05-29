package org.core.java;

import java.util.ArrayList;

public class ListDemo {
	public static void main(String[] args){
		ListDemo ld=new ListDemo();
		ld.name();
	}
	
 
public void name() {
	 ArrayList<String> aray =new ArrayList<String>();
	 aray.add("sai");
	 aray.add("shekar");
	 aray.add("sandy");
	 for(String str:aray){
		 System.out.println(str);
	 }

}
}