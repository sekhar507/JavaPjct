package org.core.java;

public class Wraper {
/*  1.wraper classes are First class Objects 
	2.ther are primitive data types in java like int,double,float e.t.c...
	3.so for those we have classes with names as Integer,Float,Double Bollean,Byte e.t.c 
	4.These classes are called as wraper classes
	Ex::int i=5;(primitive)
	Integer wraperI=Integer.valueOf(5);
	Integer wrapperI=5;//auto boxing after java....
           auto boxing----->conversion from primitive to wraper
From java 5 the new feature was introduced that is called auto boxing

uses:: 
		1. for Collections like array list and  hash maps we can  store only wraper values.
			we can't Store primitive values...
		2.for conversion like charToInt and stringToInt
			here i showed char and boolean in the same way there are Integer ,char,e.t.c
*/
	public static void main(String args[])
	{
		Wraper name=new Wraper();
		name.testWraper();
	}
public void testWraper()
{
	char ch='a';
	Character.isLowerCase('a');
	System.out.println("No error");
	boolean bool=Boolean.valueOf("True");
}
}
