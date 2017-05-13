package com.rajendarreddyj.basics.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Logger;

public class SimpleApplet extends Applet {
    private static final Logger logger = Logger.getAnonymousLogger();
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String text = "I'm a simple applet";

    @Override
    public void init() {
        this.text = "I'm a simple applet";
        this.setBackground(Color.cyan);
    }

    @Override
    public void start() {
        logger.info("starting...");
    }

    @Override
    public void stop() {
        logger.info("stopping...");
    }

    @Override
    public void destroy() {
        logger.info("preparing to unload...");
    }

    @Override
    public void paint(final Graphics g) {
        logger.info("Paint");
        g.setColor(Color.green);
        g.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        g.setColor(Color.red);
        g.drawString(this.text, 15, 25);
    }
}
