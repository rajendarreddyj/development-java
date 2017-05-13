package com.rajendarreddyj.basics.collections;

import java.util.logging.Logger;

public class MyKey {
    private static final Logger logger = Logger.getAnonymousLogger();
    private String name;
    private int id;

    public MyKey(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        logger.info("Calling hashCode()");
        return this.id;
    }

    @Override
    public String toString() {
        return "MyKey [name=" + this.name + ", id=" + this.id + "]";
    }

    @Override
    public boolean equals(final Object obj) {
        logger.info("Calling equals() for key: " + obj);
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        MyKey other = (MyKey) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
