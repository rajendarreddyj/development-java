package com.rajendarreddyj.basics.swing.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author rajendarreddy
 *
 */
public class JButtonDemo1 extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    protected static JButton jbnLeft, jbnMiddle, jbnRight;

    public JButtonDemo1() {
        // Create Icons that can be used with the jButtons
        ImageIcon leftButtonIcon = new ImageIcon("src/main/resources/images/rightarrow.jpg");
        ImageIcon middleButtonIcon = new ImageIcon("src/main/resources/images/java-swing.jpg");
        ImageIcon rightButtonIcon = new ImageIcon("src/main/resources/images/leftarrow.jpg");
        // ImageIcon rightButtonIcon = createImageIcon("src/main/resources/images/leftarrow.jpg");
        jbnLeft = new JButton("Disable centre button", leftButtonIcon);
        jbnLeft.setVerticalTextPosition(AbstractButton.CENTER);
        jbnLeft.setHorizontalTextPosition(AbstractButton.LEADING);
        jbnLeft.setMnemonic(KeyEvent.VK_D);
        // Alt-D clicks the button
        jbnLeft.setActionCommand("disable");
        jbnLeft.setToolTipText("disable the Centre button."); // Adding Tool
        // tips
        jbnMiddle = new JButton("Centre button", middleButtonIcon);
        jbnMiddle.setVerticalTextPosition(AbstractButton.BOTTOM);
        jbnMiddle.setHorizontalTextPosition(AbstractButton.CENTER);
        jbnMiddle.setMnemonic(KeyEvent.VK_M);
        // Alt-M clicks the button
        jbnMiddle.setToolTipText("Centre button");
        jbnRight = new JButton("Enable centre button", rightButtonIcon);
        // Use the default text position of CENTER, TRAILING (RIGHT).
        jbnRight.setMnemonic(KeyEvent.VK_E);
        // Alt-E clicks the button
        jbnRight.setActionCommand("enable");
        jbnRight.setEnabled(false);
        // Disable the Button at creation time
        // Listen for actions on Left and Roght Buttons
        jbnLeft.addActionListener(this);
        jbnRight.addActionListener(this);
        jbnRight.setToolTipText("Enable the Centre button.");
        // Add Components to the frame, using the default FlowLayout.
        this.add(jbnLeft);
        this.add(jbnMiddle);
        this.add(jbnRight);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if ("disable".equals(e.getActionCommand())) {
            jbnMiddle.setEnabled(false);
            jbnLeft.setEnabled(false);
            jbnRight.setEnabled(true);
        } else {
            jbnMiddle.setEnabled(true);
            jbnLeft.setEnabled(true);
            jbnRight.setEnabled(false);
        }
    }

    // Returns an ImageIcon, or null if the path was invalid.
    protected static ImageIcon createImageIcon(final String path) {
        URL imgURL = JButtonDemo1.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find image in system: " + path);
            return null;
        }
    }

    // Create the GUI and show it.
    private static void createGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        // Create and set up the frame.
        JFrame frame = new JFrame("jButton demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create and set up the content pane.
        JButtonDemo1 buttonContentPane = new JButtonDemo1();
        buttonContentPane.setOpaque(true); // content panes must be opaque
        frame.getRootPane().setDefaultButton(jbnLeft);
        frame.setContentPane(buttonContentPane);
        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                createGUI();
            }
        });
    }
}
