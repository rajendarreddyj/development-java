package com.rajendarreddyj.basics.pattern.observable;

import com.rajendarreddyj.basics.pattern.observable.rps.gui.RockPaperScissorsGui;

/**
 * This class just exists to run the overall program and pop up the GUI on screen.
 * 
 * @author rajendarreddy
 *
 */
public class RockPaperScissorsMain {
    /**
     * @param args
     */
    public static void main(final String[] args) {
        try {
            new RockPaperScissorsGui();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
