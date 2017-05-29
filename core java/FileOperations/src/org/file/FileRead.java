package org.file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FileRead {

	public static void main(String args[]) {
		String strLine = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"F:/Chess/1122.txt"));
			while ((strLine = br.readLine()) != null) {
				System.out.println(strLine);
			}

		}

		catch (FileNotFoundException e)

		{
			System.out.println("Im in File not found Exception");
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Im in IO Exception");
		}

	}

}



