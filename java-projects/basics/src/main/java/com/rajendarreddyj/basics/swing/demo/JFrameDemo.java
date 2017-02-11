package com.rajendarreddyj.basics.swing.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author rajendarreddy
 *
 */
public class JFrameDemo {
    public static void main(final String s[]) {
        JFrame frame = new JFrame("JFrame Demo");
        // Add a window listner for close button
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
        // This is an empty content area in the frame
        JLabel jlbempty = new JLabel("");
        jlbempty.setPreferredSize(new Dimension(175, 100));
        frame.getContentPane().add(jlbempty, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
