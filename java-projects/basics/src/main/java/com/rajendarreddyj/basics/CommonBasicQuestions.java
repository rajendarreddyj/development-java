package com.rajendarreddyj.basics;

/**
 * @author rajendarreddy.jagapathi
 */
public class CommonBasicQuestions {
    static int a = 1111;
    static {
        a = a-- - --a;
    }
    // Below empty will new execute
    {
        a = a++ + ++a;
        System.out.println("NEVER EXECUTE");
    }

    public static void main(final String[] args) {
        // unicode characters for space \u0020
        // unicode character for CR(end of line) \u000d
        // \u000d
        System.out.println("RAJENDAR in comments with unicode CR");
        System.out.println(a);
        String s = "rajendar";
        System.out.println(s);
        s = "J" + 1 + 2 + "RAJ";
        System.out.println(s);
        System.out.println(Fruit.TYPE);
        final class Constants {
            public static final String name = "PI";
        }
        Thread thread = new Thread(() -> System.out.println(Constants.name));
        thread.start();
        Integer i1 = 127;
        Integer i2 = 127;
        // true
        System.out.println(i1 == i2);
        Integer i3 = 128;
        Integer i4 = 128;
        // false
        System.out.println(i3 == i4);
        int i5 = 128;
        int i6 = 128;
        System.out.println(i5 == i6);
        Float i7 = 128f;
        Float i8 = 128f;
        // false
        System.out.println(i7 == i8);
        // -128 to 127 is the default size. But javadoc also says that the size of the Integer cache may be controlled
        // by the -XX:AutoBoxCacheMax=<size> option
        // Charter 0 to 127
        // Integer-Byte-Short-Long -128 to 127
        System.out.println(Math.min(Double.MIN_VALUE, 0.0d));
        System.out.println(Math.min(Integer.MIN_VALUE, 0.0d));
    }

    interface iFruit {
        public String TYPE = "rjante";
    }

    class Fruit implements iFruit {

    }
}
