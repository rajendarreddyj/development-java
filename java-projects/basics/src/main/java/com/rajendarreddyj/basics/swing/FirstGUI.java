package com.rajendarreddyj.basics.swing;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This program displays a simple Graphical User Interface (GUI) to demonstrate the Java Swing library. It just pops up
 * several buttons and lets us change their color when we click on them.
 * 
 * @author rajendarreddy
 *
 */
public class FirstGUI {
    private JButton button;
    private JButton button2;
    private JFrame frame;

    // Constructs the GUI and displays it on the screen.
    public FirstGUI() {
        // create some components (buttons)
        this.button = new JButton("Click here to get a 4.0");
        this.button.setBackground(Color.RED);
        this.button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        this.button2 = new JButton("Click here to get a 0.0");
        this.button2.setBackground(Color.YELLOW);
        // attach event listener to handle mouse clicks
        MyAwesomeListener listener = new MyAwesomeListener();
        this.button.addActionListener(listener);
        this.button2.addActionListener(listener);
        // create overall window (frame) and put buttons in it
        this.frame = new JFrame("First GUI");
        this.frame.setLocation(300, 100);
        this.frame.setSize(400, 300);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // layout manager decides how to position/size the components in the frame
        this.frame.setLayout(new BorderLayout());
        this.frame.add(this.button, BorderLayout.NORTH);
        this.frame.add(this.button2, BorderLayout.SOUTH);
        this.frame.add(new Button("WEST"), BorderLayout.WEST);
        this.frame.add(new Button("EAST"), BorderLayout.EAST);
        this.frame.setVisible(true);
    }

    // This class handles "action events", aka mouse clicks, in buttons.
    public class MyAwesomeListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent e) {
            if (e.getSource() == FirstGUI.this.button) {
                FirstGUI.this.button2.setBackground(Color.BLUE);
            } else {
                // event source is button2
                FirstGUI.this.button.setBackground(Color.BLUE);
            }
        }
    }

    /**
     * This method just exists to run the overall program and pop up the GUI on the screen.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        new FirstGUI();
    }
}
