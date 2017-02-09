package com.rajendarreddyj.basics.formatters;

/**
 * @rajendarreddy rajendarreddy
 *
 */
public class PrintFormat {
	public static void main(String[] args) {
		int a=1,b=2,c=3,d=4;
		System.out.print(a+"\t"+b);
		System.out.println(b+"\n"+b);
		System.out.print(":"+c);
		System.out.println();//this throws cursor to the next line
		System.out.println("Hello\\Hi\""+d);
		
		
	}

}
