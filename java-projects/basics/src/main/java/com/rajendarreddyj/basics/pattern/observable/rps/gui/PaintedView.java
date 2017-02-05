package com.rajendarreddyj.basics.pattern.observable.rps.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

import com.rajendarreddyj.basics.pattern.observable.rps.model.Game;
import com.rajendarreddyj.basics.pattern.observable.rps.model.Weapon;

/**
 * A painted view displays the game state as 2D graphics such as lines, shapes, and colors on a JPanel.
 * 
 * @author rajendarreddy
 */
public class PaintedView extends View {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new painted view to display data from the given game.
     * 
     * @param game
     *            the game to display
     * @pre game != null
     */
    public PaintedView(final Game game) {
        super(game);
    }

    /**
     * Draws this painted view on the screen, painting the state of the game as a set of 2D graphical shapes.
     * 
     * @param g
     *            the graphics pen to use
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.drawWeapon(g2, this.getGame().getWeapon1(), 50, 50);
        this.drawWeapon(g2, this.getGame().getWeapon2(), 250, 50);
    }

    // Draws one player's weapon at the given x/y position.
    private void drawWeapon(final Graphics2D g2, final Weapon weapon, final int x, final int y) {
        if (weapon == Weapon.ROCK) {
            Ellipse2D.Double rock = new Ellipse2D.Double(x, y, 50, 50);
            g2.setPaint(Color.GRAY);
            g2.fill(rock);
        } else if (weapon == Weapon.PAPER) {
            Rectangle2D.Double paper = new Rectangle2D.Double(x, y, 50, 50);
            g2.setPaint(Color.WHITE);
            g2.fill(paper);
        } else if (weapon == Weapon.SCISSORS) {
            Ellipse2D.Double handle1 = new Ellipse2D.Double(x, y, 20, 20);
            Ellipse2D.Double handle2 = new Ellipse2D.Double(x, y + 30, 20, 20);
            Line2D.Double blade1 = new Line2D.Double(x + 15, y + 15, x + 50, y + 40);
            Line2D.Double blade2 = new Line2D.Double(x + 15, y + 35, x + 50, y + 10);
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.YELLOW);
            g2.draw(handle1);
            g2.draw(handle2);
            g2.setColor(Color.LIGHT_GRAY);
            g2.draw(blade1);
            g2.draw(blade2);
        }
        // else weapon == null
    }

    /**
     * Updates the state of this view based on an event that has occurred in the game model (repaints the view).
     */
    @Override
    public void update(final Observable o, final Object arg) {
        this.repaint();
    }
}
