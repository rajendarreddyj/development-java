package com.rajendarreddyj.basics.pattern.singleton;

/**
 * @author rajendarreddy
 *
 */
public class SingletonObjectDemo2 {
    public static void main(final String args[]) {
        SingletonClass2.getSingletonObject();
        // Your Business Logic
        System.out.println("Singleton object obtained");
    }
}

class SingletonClass2 {

    private static SingletonClass2 singletonObject;

    /** A private Constructor prevents any other class from instantiating. */
    private SingletonClass2() {
        // Optional Code
    }

    // Lazy Initialization (If required then only)
    public static SingletonClass2 getSingletonObject() {
        if (singletonObject == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (SingletonClass2.class) {
                if (singletonObject == null) {
                    singletonObject = new SingletonClass2();
                }
            }
        }
        return singletonObject;
    }

    // Override the Object clone method to prevent cloning
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
