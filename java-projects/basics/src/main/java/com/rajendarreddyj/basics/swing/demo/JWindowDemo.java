package com.rajendarreddyj.basics.swing.demo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JWindow;

/**
 * @author rajendarreddy
 *
 */
public class JWindowDemo extends JWindow {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getAnonymousLogger();
    private int X = 0;
    private int Y = 0;

    public JWindowDemo() {
        this.setBounds(60, 60, 100, 100);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0); // An Exit Listener
            }
        });
        // Print (X,Y) coordinates on Mouse Click
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(final MouseEvent e) {
                JWindowDemo.this.X = e.getX();
                JWindowDemo.this.Y = e.getY();
                logger.info("The (X,Y) coordinate of window is (" + JWindowDemo.this.X + "," + JWindowDemo.this.Y + ")");
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(final MouseEvent e) {
                JWindowDemo.this.setLocation(JWindowDemo.this.getLocation().x + (e.getX() - JWindowDemo.this.X),
                        JWindowDemo.this.getLocation().y + (e.getY() - JWindowDemo.this.Y));
            }
        });
        this.setVisible(true);
    }

    public static void main(final String[] args) {
        new JWindowDemo();
    }
}
