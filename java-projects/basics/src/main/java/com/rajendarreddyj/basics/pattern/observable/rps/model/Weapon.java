package com.rajendarreddyj.basics.pattern.observable.rps.model;

import java.util.Random;

/**
 * A Weapon represents one of the three choices in the Rock-Paper-Scissors game.
 * 
 * @author rajendarreddy
 */
public enum Weapon {
    ROCK, SCISSORS, PAPER;
    /**
     * Returns a randomly chosen weapon from the three choices.
     * 
     * @return ROCK, PAPER, or SCISSORS, chosen at random
     */
    public static Weapon random() {
        Random rand = new Random();
        int weapon = rand.nextInt(3);
        return Weapon.values()[weapon];
    }

    /**
     * Returns whether this weapon beats the given other weapon if they match up against each other in a game.
     * 
     * @param other
     *            the other weapon to compare against
     * @return true if this weapon defeats the other; false if other wins or there is a tie
     */
    public boolean defeats(final Weapon other) {
        return ((this == ROCK) && (other == SCISSORS)) || ((this == SCISSORS) && (other == PAPER)) || ((this == PAPER) && (other == ROCK));
    }
}
