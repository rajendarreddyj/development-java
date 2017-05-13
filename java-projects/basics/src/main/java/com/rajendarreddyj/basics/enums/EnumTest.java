package com.rajendarreddyj.basics.enums;

import java.util.Iterator;
import java.util.logging.Logger;

/**
 * EnumTest
 *
 * 
 * A example of how the TypeSafeEnum class an be subclassed and used.
 *
 * 
 */
public class EnumTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    static class Fruit extends TypeSafeEnum {
        public Fruit(final String name) {
            super(name, Fruit.class);
        }

        public static Fruit APPLE = new Fruit("APPLE");
        public static Fruit PEAR = new Fruit("PEAR");
        public static Fruit ORANGE = new Fruit("ORANGE");
    }

    static class Animal extends TypeSafeEnum {
        public Animal(final String name) {
            super(name, Animal.class);
        }

        public static Animal DOG = new Animal("DOG");
        public static Animal RICK = new Animal("Rick Santorum");
        public static Animal CAT = new Animal("CAT");
    }

    public static void main(final String[] args) {
        logger.info("Animal:");
        for (Iterator<?> i = TypeSafeEnum.enumValues(Animal.class); i.hasNext();) {
            TypeSafeEnum e = (TypeSafeEnum) i.next();
            logger.info("enumVal = " + e.getValue() + ", name = " + e.getName());
        }
        logger.info("Fruit:");
        for (Iterator<?> i = TypeSafeEnum.enumValues(Fruit.class); i.hasNext();) {
            TypeSafeEnum e = (TypeSafeEnum) i.next();
            logger.info("enumVal = " + e.getValue() + ", name = " + e.getName());
        }
    }
}
