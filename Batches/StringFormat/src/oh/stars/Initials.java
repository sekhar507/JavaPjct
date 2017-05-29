package oh.stars;

import java.io.*;

class Initials {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		char x;
		int l;
		System.out.print("Enter any sentence: ");
		s = br.readLine();
		s = " " + s; // adding a space infront of the inputted sentence or a
						
		l = s.length(); // finding the length of the sentence</span>
		System.out.print("Output = ");

	
		for (int j = l; j >= 0; j--) {
			for (int i = 0; i < l; i++) {
				x = s.charAt(i); // taking out one character at a time from the
									// given sentence.

				if (x == ' ') // if the character is a space, printing the next

					System.out.print(s.charAt(i + 1));
			}

		}

	}
}
