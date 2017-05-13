package com.rajendarreddyj.basics.swing.rmiclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("deprecation")
public class RMIClient2 extends JFrame implements ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getAnonymousLogger();
    JLabel text, clicked;
    JButton button;
    JPanel panel;
    JTextArea textArea;
    Socket socket = null;
    PrintWriter out = null;
    static Send send;

    RMIClient2() { // Begin Constructor
        this.text = new JLabel("Text received:");
        this.textArea = new JTextArea();
        this.button = new JButton("Click Me");
        this.button.addActionListener(this);
        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.panel.setBackground(Color.white);
        this.getContentPane().add(this.panel);
        this.panel.add("North", this.text);
        this.panel.add("Center", this.textArea);
        this.panel.add("South", this.button);
    } // End Constructor

    @Override
    public void actionPerformed(final ActionEvent event) {
        Object source = event.getSource();
        if (source == this.button) {
            try {
                String text = send.getData();
                this.textArea.append(text);
            } catch (java.rmi.RemoteException e) {
                logger.info("Cannot access data in server");
            }
        }
    }

    public static void main(final String[] args) {
        RMIClient2 frame = new RMIClient2();
        frame.setTitle("Client Two");
        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(l);
        frame.pack();
        frame.setVisible(true);
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            String name = "//" + args[0] + "/Send";
            send = ((Send) Naming.lookup(name));
        } catch (java.rmi.NotBoundException e) {
            logger.info("Cannot access data in server");
        } catch (java.rmi.RemoteException e) {
            logger.info("Cannot access data in server");
        } catch (java.net.MalformedURLException e) {
            logger.info("Cannot access data in server");
        }
    }
}
