package org.core.java;

public class Methodparameters {
	int score=0;
	public static void main(String args[])
	{
		Methodparameters objectName=new Methodparameters();
		objectName.totalscore(4,2,1,2,3,3);
		objectName.totalscore(6,6,6,6,6,6);
		objectName.totalscore(6,4,4,4,4,4);
		objectName.totalscore(6,0,0,0,0,0);
		objectName.printScore();
		
		
		
	}
	public int totalscore(int ball1,int ball2,int ball3,int ball4,int ball5,int ball6)
	{
		score=score+ball1+ball2+ball3+ball4+ball5+ball6;
		return score;
	}
	public void printScore()
	{
		System.out.println("your total score is " + score);
	}

}
