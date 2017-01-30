package com.rajendarreddyj.basics.others;

//: Cloning.java
// The clone() operation works for only a few
// items in the standard Java library.
import java.util.Enumeration;
import java.util.Vector;

class Int {
	private int i;

	public Int(int ii) {
		i = ii;
	}

	public void increment() {
		i++;
	}

	public String toString() {
		return Integer.toString(i);
	}
}

public class Cloning {
	@SuppressWarnings("unchecked")
    public static void main(String[] args) {
		Vector<Int> v = new Vector<>();
		for (int i = 0; i <= 10; i++)
			v.addElement(new Int(i));
		System.out.println("v: " + v);
		Vector<Int> v2 = (Vector<Int>) v.clone();
		// Increment all v2's elements:
		for (Enumeration<Int> e = v2.elements(); e.hasMoreElements();)
			((Int) e.nextElement()).increment();
		// See if it changed v's elements:
		System.out.println("v: " + v);
	}
} // /:~ 