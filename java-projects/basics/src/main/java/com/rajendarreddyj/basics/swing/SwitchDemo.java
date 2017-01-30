package com.rajendarreddyj.basics.swing;

import javax.swing.JOptionPane;

public class SwitchDemo {
    public static void main(final String[] args) {
        String month = JOptionPane.showInputDialog("Enter the Month Number");
        int monthNumber = Integer.parseInt(month);
        switch (monthNumber) {
        case 1:
            System.out.println("JANUARY");
            System.out.println("Happy New Year");
            break;
        case 2:
            System.out.println("FEBRUARY");
            System.out.println("The Shortest Month of the Year");
            break;
        case 3:
            System.out.println("MARCH");
            System.out.println("A daffodil Month");
            break;
        case 4:
            System.out.println("APRIL");
            System.out.println("April first is a fools day");
            break;
        case 5:
            System.out.println("MAY");
            System.out.println("Hottest Month");
            break;
        case 6:
            System.out.println("JUNE");
            break;
        case 7:
            System.out.println("JULY");
            break;
        case 8:
            System.out.println("AUGUST");
            break;
        case 9:
            System.out.println("SEPTEMBER");
            break;
        case 10:
            System.out.println("OCTOBER");
            break;
        case 11:
            System.out.println("NOVEMBER");
            break;
        case 12:
            System.out.println("DECEMBER");
            break;
        default:
            System.out.println("The number you have entered does not match with any month");
            break;
        }

    }
}
