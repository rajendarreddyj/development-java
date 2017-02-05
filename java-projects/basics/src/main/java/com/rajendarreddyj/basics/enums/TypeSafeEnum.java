package com.rajendarreddyj.basics.enums;

/**
 * TypeSafeEnum
 *
 *
 */
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Yet another type safe enumeration base class. This class supports the creation of multiple enumeration subclasses. In
 * each subclass the enumeration values start at 0.
 * 
 * <p>
 * Documentation for this class can be found <a href="http://www.test.com/software/java/misl/enum/type_safe_enum.html">
 * here</a>. Apparently TypeSafeEnums are supported in the new version of Java. So at some point the classes that use
 * this class should be replaced with something more generic.
 * </p>
 */
@SuppressWarnings("rawtypes")
public abstract class TypeSafeEnum {
    private static class EnumInfo {
        public int hashCode;
        public int count;
        public ArrayList<TypeSafeEnum> values;

        EnumInfo(final int hash) {
            this.hashCode = hash;
            this.count = 0;
            this.values = new ArrayList<>();
        }
    } // class enumInfo

    private static ArrayList<EnumInfo> infoVec = new ArrayList<>();
    private String mName;
    private int mValue;

    public TypeSafeEnum(final String name, final Class cls) {
        this.mName = name;
        EnumInfo elem = findInfo(cls, true);
        this.mValue = elem.count;
        elem.count++;
        elem.values.add(this);
    } // TypeSafeEnum constructor

    public static Iterator enumValues(final Class cls) {
        Iterator iter = null;
        EnumInfo elem = findInfo(cls, false);
        if (elem != null) {
            iter = elem.values.iterator();
        }
        return iter;
    } // enumValues

    public String getName() {
        return this.mName;
    }

    public int getValue() {
        return this.mValue;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    /**
     * Find the entry for the enumeration, if it exists. If not, add it to the end of the enumInfo. Note that this
     * function has linear time, but the assumption is that there will not a large number of enumeration classes.
     */
    private static EnumInfo findInfo(final Class cls, final boolean add) {
        EnumInfo foundElem = null;
        int hashCode = cls.hashCode();
        for (Iterator<EnumInfo> iter = infoVec.iterator(); iter.hasNext();) {
            EnumInfo elem = iter.next();
            if (elem.hashCode == hashCode) {
                foundElem = elem;
                break;
            }
        }
        if ((foundElem == null) && add) {
            foundElem = new EnumInfo(hashCode);
            infoVec.add(foundElem);
        }
        return foundElem;
    } // findInfo
}
