package org.file;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameFilter {
	public static void main(String args[])
	{
		File file=new File("F:/Chess/");
		String[] fileList=file.list(new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name)
			{
				if(name.toLowerCase().endsWith(".pdf"))
				{
					return true;
				}
				else 
					return false;
			}
		}				
	);
		for(String f:fileList)
		{
			System.out.println(f);
		}
	}
}