package com.rajendarreddyj.basics.pattern.observable.rps.model;

import java.util.Observable;

/**
 * A RockStrategy is dumb and always chooses the rock (Weapon.ROCK).
 * 
 * @author rajendarreddy
 */
public class RockStrategy implements RPSStrategy {
    private static final long serialVersionUID = 1L;

    /**
     * Chooses rock for for this strategy.
     * 
     * @return Weapon.ROCK
     */
    @Override
    public Weapon chooseWeapon() {
        return Weapon.ROCK;
    }

    /**
     * Required by Observer interface; not used in this strategy.
     */
    @Override
    public void update(final Observable o, final Object arg) {
    }
}
