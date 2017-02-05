package com.rajendarreddyj.basics.filereadwrite;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class TestPrintStream {
    public static void main(final String[] args) {
        System.out.println("January");
        System.out.println("February");
        PrintStream ps = null;
        try {
            ps = new PrintStream("sample.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
        System.setOut(ps);
        System.out.println("March");
        System.out.println("April");
        ps.close();
    }
}
