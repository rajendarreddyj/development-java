package com.rajendarreddyj.basics.pattern.observable.rps.model;

import java.util.Observable;

/**
 * A RandomStrategy chooses its RPS move at random each time.
 * 
 * @author rajendarreddy
 */
public class RandomStrategy implements RPSStrategy {
    private static final long serialVersionUID = 1L;

    /**
     * Chooses the next random weapon for this strategy.
     * 
     * @return the randomly chosen weapon
     */
    @Override
    public Weapon chooseWeapon() {
        return Weapon.random();
    }

    /**
     * Required by Observer interface; not used in this strategy.
     */
    @Override
    public void update(final Observable o, final Object arg) {
    }
}
