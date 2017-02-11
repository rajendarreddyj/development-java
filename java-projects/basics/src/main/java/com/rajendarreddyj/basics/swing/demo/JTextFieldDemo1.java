package com.rajendarreddyj.basics.swing.demo;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author rajendarreddy
 *
 */
public class JTextFieldDemo1 extends JFrame {

    private static final long serialVersionUID = 1L;
    // Class Declarations
    JTextField jtfText1, jtfUneditableText;
    String disp = "";
    TextHandler handler = null;

    // Constructor
    public JTextFieldDemo1() {
        super("TextField Test Demo1");
        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());
        this.jtfText1 = new JTextField(10);
        this.jtfUneditableText = new JTextField("Uneditable text field", 20);
        this.jtfUneditableText.setEditable(false);
        container.add(this.jtfText1);
        container.add(this.jtfUneditableText);
        this.handler = new TextHandler();
        this.jtfText1.addActionListener(this.handler);
        this.jtfUneditableText.addActionListener(this.handler);
        this.setSize(325, 100);
        this.setVisible(true);
    }

    // Inner Class TextHandler
    private class TextHandler implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            if (e.getSource() == JTextFieldDemo1.this.jtfText1) {
                JTextFieldDemo1.this.disp = "text1 : " + e.getActionCommand();
            } else if (e.getSource() == JTextFieldDemo1.this.jtfUneditableText) {
                JTextFieldDemo1.this.disp = "text3 : " + e.getActionCommand();
            }
            JOptionPane.showMessageDialog(null, JTextFieldDemo1.this.disp);
        }
    }

    // Main Program that starts Execution
    public static void main(final String args[]) {
        JTextFieldDemo1 test = new JTextFieldDemo1();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
