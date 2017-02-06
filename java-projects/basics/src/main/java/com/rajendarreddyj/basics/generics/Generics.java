package com.rajendarreddyj.basics.generics;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Generics {

    // definition of a generic method
    public static <T> List<T> fromArrayToList(final T[] a) {
        return Arrays.stream(a).collect(Collectors.toList());
    }

    // definition of a generic method
    public static <T, G> List<G> fromArrayToList(final T[] a, final Function<T, G> mapperFunction) {
        return Arrays.stream(a).map(mapperFunction).collect(Collectors.toList());
    }

    // example of a generic method that has Number as an upper bound for T
    public static <T extends Number> List<T> fromArrayToListWithUpperBound(final T[] a) {
        return Arrays.stream(a).collect(Collectors.toList());
    }

    // example of a generic method with a wild card, this method can be used
    // with a list of any subtype of Building
    public static void paintAllBuildings(final List<? extends Building> buildings) {
        buildings.forEach(Building::paint);
    }

}