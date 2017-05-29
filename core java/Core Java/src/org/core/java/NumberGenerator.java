package org.core.java;

import java.util.Scanner;

public class NumberGenerator {

	public static void main(String[] args) {
		System.out.printf("Enter a Number:  ");
		Scanner toread = new Scanner(System.in);
		int number = toread.nextInt();

		int steps = 0;
		while (number != 1) {
			if (number % 2 == 0) {
				System.out.println(number + " is even, so I take half: "
						+ number / 2);
				number /= 2;
			} else {
				System.out.println(number + " is odd, so I make 3n + 1: "
						+ (number * 3 + 1));
				number = number * 3 + 1;
			}
			steps++;
		}
		System.out.println("The process took " + steps
				+ (steps < 2 ? " step" : " steps") + " to reach 1");

	}
}
