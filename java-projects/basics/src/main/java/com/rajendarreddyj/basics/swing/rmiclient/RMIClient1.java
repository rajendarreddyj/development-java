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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("deprecation")
public class RMIClient1 extends JFrame implements ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JLabel text, clicked;
    JButton button;
    JPanel panel;
    JTextField textField;
    Socket socket = null;
    PrintWriter out = null;
    static Send send;

    RMIClient1() { // Begin Constructor
        this.text = new JLabel("Text to send:");
        this.textField = new JTextField(20);
        this.button = new JButton("Click Me");
        this.button.addActionListener(this);
        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.panel.setBackground(Color.white);
        this.getContentPane().add(this.panel);
        this.panel.add("North", this.text);
        this.panel.add("Center", this.textField);
        this.panel.add("South", this.button);
    } // End Constructor

    @Override
    public void actionPerformed(final ActionEvent event) {
        Object source = event.getSource();
        if (source == this.button) {
            // Send data over socket
            String text = this.textField.getText();
            try {
                send.sendData(text);
            } catch (java.rmi.RemoteException e) {
                System.out.println("Cannot send data to server");
            }
            this.textField.setText(new String(""));
        }
    }

    public static void main(final String[] args) {
        RMIClient1 frame = new RMIClient1();
        frame.setTitle("Client One");
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
            System.out.println("Cannot look up remote server object");
        } catch (java.rmi.RemoteException e) {
            System.out.println("Cannot look up remote server object");
        } catch (java.net.MalformedURLException e) {
            System.out.println("Cannot look up remote server object");
        }
    }
}
