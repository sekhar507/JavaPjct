package org.file;

import java.io.File;

public class FileListFromFolder {
	public static void main(String args[])
	{
		File file =new File("F:/Chess/");
		String[] fileList=file.list();
		for(String name:fileList)
		{
			System.out.println(name);
		}
		
	}

}
