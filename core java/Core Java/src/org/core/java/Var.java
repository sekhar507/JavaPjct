package org.core.java;

public class Var {
	//Final methods cant be overriden 
	//Final classes cant be extend
	int instanceVariable=10;//member variable or instance variable
	public void method1()
	{
		int LocalVariable=20;//Local variable
		int instanceVariable=5;//we can use clas variables in any where in the calss
	}
	public void method2()
	{
		/*int LocalVariable=10;*/
		int instanceVariable=2;//we can use clas variables in any where in the calss
	}
	public static void main(String args[])
	{
		final int i=20;//we cant change furthur
		//int i=10; it will give an error
		//System.out.println(localVariable)//we can not access local variable out side of method
		
	}

}
