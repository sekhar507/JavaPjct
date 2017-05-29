package org.file;

public class Splitwise {
	public static void main(String args[]) {
		StringBuffer buf = new StringBuffer("OHAO	FD20150610054	311537655	80001975	MARATHON	PETROLEUM	COMPANY	LP");
		String temp1;
		StringBuffer temp2;
		String temp3;
		temp1=buf.substring(0, 14)+ "\t" +buf.substring(15, 29);
		System.out.println(temp1);
		temp2=buf.replace(29, 38, "12345678 ");
		System.out.println(temp2);
		
		temp3=temp1+temp2.substring(29,38)+temp2.substring(40, 46)+"\t"+"\t"+temp2.substring(47, 56);
		System.out.println(temp3);

	}
}