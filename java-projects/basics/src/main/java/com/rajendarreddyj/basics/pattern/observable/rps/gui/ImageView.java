package com.rajendarreddyj.basics.pattern.observable.rps.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.net.URL;
import java.util.Observable;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.rajendarreddyj.basics.pattern.observable.rps.model.Game;

/**
 * An image view displays the game state as buttons with image icons for each weapon.
 * 
 * @author rajendarreddy
 */
public class ImageView extends View {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final long serialVersionUID = 1L;
    private JButton move1;
    private JButton move2;

    /**
     * Constructs a new image view to display data from the given game.
     * 
     * @param game
     *            the game to display
     * @pre game != null
     */
    public ImageView(final Game game) {
        super(game);
        this.move1 = new JButton(this.getIcon("src/main/resources/images/blank.png"));
        this.move2 = new JButton(this.getIcon("src/main/resources/images/blank.png"));
        this.setState();
        this.setLayout(new FlowLayout());
        // this.setLayout(new CascadingLayout());
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
        String file1 = "blank.png";
        String file2 = "blank.png";
        if (this.getGame().getWeapon1() != null) {
            file1 = "src/main/resources/images/" + this.getGame().getWeapon1().toString().toLowerCase() + ".png";
        }
        if (this.getGame().getWeapon2() != null) {
            file2 = "src/main/resources/images/" + this.getGame().getWeapon2().toString().toLowerCase() + ".png";
        }
        this.move1.setIcon(this.getIcon(file1));
        this.move2.setIcon(this.getIcon(file2));
        // highlight the winner
        int winner = this.getGame().winner();
        this.move1.setBackground(null);
        this.move2.setBackground(null);
        if (winner == 1) {
            this.move1.setBackground(Color.YELLOW);
        } else if (winner == 2) {
            this.move2.setBackground(Color.YELLOW);
        }
    }

    private ImageIcon getIcon(final String filename) {
        URL url = RockPaperScissorsGui.class.getResource(filename);
        if (url == null) {
            logger.info("using file");
            return new ImageIcon(filename);
        } else {
            logger.info(url.toString());
            logger.info("using URL / JAR");
            return new ImageIcon(url);
        }
    }
}
