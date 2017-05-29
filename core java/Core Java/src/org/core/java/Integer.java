package org.core.java;

public class Integer {
	int score=0;
	public static void main(String args[])
	{
		Integer objectName=new Integer();
		objectName.six();
		objectName.four();
		objectName.printScore();
		
	}

	public void single()
	{
		score=score+1;
	}
	public void four()
	{
		score=score+4;
	}
	public void six()
	{
		score=score+6;
	}
	public void printScore()
	{
		System.out.println("your score is " + score);
	}
}
