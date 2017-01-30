package com.rajendarreddyj.basics.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class SimpleApplet extends Applet {
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
        System.out.println("starting...");
    }

    @Override
    public void stop() {
        System.out.println("stopping...");
    }

    @Override
    public void destroy() {
        System.out.println("preparing to unload...");
    }

    @Override
    public void paint(final Graphics g) {
        System.out.println("Paint");
        g.setColor(Color.green);
        g.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        g.setColor(Color.red);
        g.drawString(this.text, 15, 25);
    }
}
