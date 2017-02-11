package com.rajendarreddyj.basics.swing.demo;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author rajendarreddy
 *
 */
public class FlowLayoutDemo {
    public static boolean RIGHT_TO_LEFT = false;

    public static void addComponents(final Container contentPane) {
        if (RIGHT_TO_LEFT) {
            contentPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        contentPane.setLayout(new FlowLayout());

        contentPane.add(new JLabel("JLabel 1"));
        contentPane.add(new JButton("JButton 2"));
        contentPane.add(new JCheckBox("JCheckBox 3"));
        contentPane.add(new JTextField("Long-Named JTextField 4"));
        contentPane.add(new JButton("JButton 5"));
    }

    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("FlowLayout Source Demo") {
            private static final long serialVersionUID = 1L;

            @Override
            public Dimension getMinimumSize() {
                Dimension prefSize = this.getPreferredSize();
                return new Dimension(100, prefSize.height);
            }
        };
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the content pane and components in FlowLayout
        addComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}