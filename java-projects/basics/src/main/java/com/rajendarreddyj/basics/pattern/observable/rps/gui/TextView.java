package com.rajendarreddyj.basics.pattern.observable.rps.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;

import com.rajendarreddyj.basics.pattern.observable.rps.model.Game;

/**
 * A text view displays the game state as buttons with text for each weapon.
 * 
 * @author rajendarreddy
 */
public class TextView extends View {
    private static final long serialVersionUID = 1L;
    private JButton move1;
    private JButton move2;

    /**
     * Constructs a new text view to display data from the given game.
     * 
     * @param game
     *            the game to display
     * @pre game != null
     */
    public TextView(final Game game) {
        super(game);
        this.move1 = new JButton(" ");
        this.move2 = new JButton(" ");
        this.setState();
        this.setLayout(new FlowLayout());
        this.add(this.move1);
        this.add(this.move2);
    }

    /**
     * Updates the state of this view based on an event that has occurred in the game model.
     */
    @Override
    public void update(final Observable arg0, final Object arg1) {
        this.setState();
    }

    // Updates this view's state when it is constructed or when events occur.
    private void setState() {
        // update the weapon buttons
        String weapon1 = " ";
        if (this.getGame().getWeapon1() != null) {
            weapon1 = this.getGame().getWeapon1().toString();
            this.move1.setText(weapon1);
        }
        String weapon2 = " ";
        if (this.getGame().getWeapon2() != null) {
            weapon2 = this.getGame().getWeapon2().toString();
            this.move2.setText(weapon2);
        }
        // highlight the winner
        int winner = this.getGame().winner();
        this.move1.setBackground(null);
        this.move2.setBackground(null);
        if (winner == 1) {
            this.move1.setBackground(Color.RED);
        } else if (winner == 2) {
            this.move2.setBackground(Color.RED);
        }
    }
}
