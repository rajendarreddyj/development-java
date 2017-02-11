package com.rajendarreddyj.basics.swing.demo;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @author rajendarreddy
 *
 */
public class JInternalFrameDemo extends JFrame {

    private static final long serialVersionUID = 1L;
    JDesktopPane jdpDesktop;
    static int openFrameCount = 0;

    public JInternalFrameDemo() {
        super("JInternalFrame Demo");
        // Make the main window positioned as 50 pixels from each edge of the
        // screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(inset, inset, screenSize.width - (inset * 2), screenSize.height - (inset * 2));
        // Add a Window Exit Listener
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
        // Create and Set up the GUI.
        this.jdpDesktop = new JDesktopPane();
        // A specialized layered pane to be used with JInternalFrames
        this.createFrame(); // Create first window
        this.setContentPane(this.jdpDesktop);
        this.setJMenuBar(this.createMenuBar());
        // Make dragging faster by setting drag mode to Outline
        this.jdpDesktop.putClientProperty("JDesktopPane.dragMode", "outline");
    }

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Frame");
        menu.setMnemonic(KeyEvent.VK_N);
        JMenuItem menuItem = new JMenuItem("New IFrame");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                JInternalFrameDemo.this.createFrame();
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);
        return menuBar;
    }

    protected void createFrame() {
        MyInternalFrame frame = new MyInternalFrame();
        frame.setVisible(true);
        // Every JInternalFrame must be added to content pane using JDesktopPane
        this.jdpDesktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    public static void main(final String[] args) {
        JInternalFrameDemo frame = new JInternalFrameDemo();
        frame.setVisible(true);
    }

    class MyInternalFrame extends JInternalFrame {

        private static final long serialVersionUID = 1L;
        static final int xPosition = 30, yPosition = 30;

        public MyInternalFrame() {
            super("IFrame #" + (++openFrameCount), true, // resizable
                    true, // closable
                    true, // maximizable
                    true);// iconifiable
            this.setSize(300, 300);
            // Set the window's location.
            this.setLocation(xPosition * openFrameCount, yPosition * openFrameCount);
        }
    }
}
