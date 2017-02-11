package com.rajendarreddyj.basics.swing.demo;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author rajendarreddy
 *
 */
public class JlabelDemo extends JPanel {

    private static final long serialVersionUID = 1L;
    JLabel jlbLabel1, jlbLabel2, jlbLabel3;

    public JlabelDemo() {
        ImageIcon icon = new ImageIcon("src/main/resources/images/java-swing.jpg", "Java File Icon");
        // Creating an Icon
        this.setLayout(new GridLayout(3, 1));
        // 3 rows, 1 column Panel having Grid Layout
        this.jlbLabel1 = new JLabel("Image with Text", icon, JLabel.CENTER);
        // We can position of the text, relative to the icon:
        this.jlbLabel1.setVerticalTextPosition(JLabel.BOTTOM);
        this.jlbLabel1.setHorizontalTextPosition(JLabel.CENTER);
        this.jlbLabel2 = new JLabel("Text Only Label");
        this.jlbLabel3 = new JLabel(icon); // Label of Icon Only
        // Add labels to the Panel
        this.add(this.jlbLabel1);
        this.add(this.jlbLabel2);
        this.add(this.jlbLabel3);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("jLabel Demo");
        frame.addWindowListener(new WindowAdapter() {

            // Shows code to Add Window Listener
            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setContentPane(new JlabelDemo());
        frame.pack();
        frame.setVisible(true);
    }
}