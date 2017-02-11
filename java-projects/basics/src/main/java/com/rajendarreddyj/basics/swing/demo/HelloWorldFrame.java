package com.rajendarreddyj.basics.swing.demo;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author rajendarreddy
 *
 */
public class HelloWorldFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(final String args[]) {
        new HelloWorldFrame();
    }

    HelloWorldFrame() {
        JLabel jlbHelloWorld = new JLabel("Hello World");
        this.add(jlbHelloWorld);
        this.setSize(100, 100);
        // pack();
        this.setVisible(true);
    }
}
