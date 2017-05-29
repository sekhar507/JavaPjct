package org.core.java;

public class Actor {

	String name="default value";//member variable or instance variable
	public static void main(String[] args) {
		
		Actor chiru=new Actor();
		Actor Nag=new Actor();
		chiru.name="Ramcharan";
		chiru.dance();
		Nag.name="chaitanya"; 
		Nag.act();
	}
	public void act()
	{
		System.out.println("Acting");
		System.out.println(name  + " also acting");
		
	}
	public void dance()
	{
		System.out.println("Iam dancing");
		System.out.println(name + " also dancing");
		
	}

}
