package com.rajendarreddyj.basics.pattern.observable.rps.gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.rajendarreddyj.basics.pattern.observable.rps.model.Game;

/**
 * This is a superclass for all views in the RPS game. It is an abstract class because it exists only to be a parent,
 * not to be directly instantiated.
 * 
 * @author rajendarreddy
 */
public abstract class View extends JPanel implements Observer {
    private static final long serialVersionUID = 1L;
    private Game game;

    /**
     * Constructs a new view to display data from the given game.
     * 
     * @param game
     *            the game to display
     * @pre game != null
     */
    public View(final Game game) {
        this.game = game;
        game.addObserver(this);
    }

    /**
     * Returns this view's corresponding game object.
     * 
     * @return the game being displayed
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Called by the GUI to inform the view that it is being detached from the model.
     */
    public void goAway() {
        this.game.deleteObserver(this);
    }

    /**
     * Each view must implement its own updating behavior to update its state whenever the state of the game changes.
     */
    @Override
    public abstract void update(Observable o, Object arg);
}
