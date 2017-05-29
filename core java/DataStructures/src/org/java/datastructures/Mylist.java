package org.java.datastructures;

import java.util.Scanner;

public class Mylist {
	int info;
	Mylist link;

	public Mylist() {
		this.link = null;
	}

}

class LinkedListDemo1
{
	public static void main(String[] args) {
		
		Scanner s= new Scanner(System.in);
		
		
		Mylist a = new Mylist();
		Mylist b = new Mylist();
		Mylist c = new Mylist();
		
		System.out.println("Please enter the value of A");
		a.info=s.nextInt();
		System.out.println("Please enter the value of B");
		b.info=s.nextInt();
		System.out.println("Please enter the value of C");
		c.info=s.nextInt();
		// Linking Nodes
		
		a.link=b;
		b.link=c;
		while(a!=null)
		{
			System.out.print(a.info + "-->");
			a=a.link;
		}
		System.out.println("null");
		
		
		
		//what tool is used in for moving war file from one to other
		//Devil Putty
		
		
		
		
		
		
	}
	
}
