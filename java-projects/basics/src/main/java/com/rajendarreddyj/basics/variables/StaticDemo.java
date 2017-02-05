package com.rajendarreddyj.basics.variables;

public class StaticDemo {
    // static variable - the same on all instances of a class
    static int X = 10;
    // non static variable
    int Y = 5;

    @SuppressWarnings("static-access")
    public static void main(final String[] args) {
        StaticDemo instance1 = new StaticDemo();
        StaticDemo instance2 = new StaticDemo();
        System.out.println("instance1.X = " + instance1.X + " instance1.Y = " + instance2.Y);
        System.out.println("instance2.X = " + instance1.X + " instance2.Y = " + instance2.Y);
        instance1.X = 15;
        instance1.Y = 10;
        System.out.println("After updating X value to 15 and Y value to 10 from intance1 :");
        System.out.println("instance1.X = " + instance1.X + " instance1.Y = " + instance1.Y);
        System.out.println("instance2.X = " + instance2.X + " instance2.Y = " + instance2.Y);
    }
}
