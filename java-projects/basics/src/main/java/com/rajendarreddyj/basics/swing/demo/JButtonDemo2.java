package com.rajendarreddyj.basics.swing.demo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * @author rajendarreddy
 *
 */
public class JButtonDemo2 {
    JFrame jtfMainFrame;
    JButton jbnButton1, jbnButton2;
    JTextField jtfInput;
    JPanel jplPanel;

    public JButtonDemo2() {
        this.jtfMainFrame = new JFrame("Which Button Demo");
        this.jtfMainFrame.setSize(50, 50);
        this.jbnButton1 = new JButton("Button 1");
        this.jbnButton2 = new JButton("Button 2");
        this.jtfInput = new JTextField(20);
        this.jplPanel = new JPanel();
        this.jbnButton1.setMnemonic(KeyEvent.VK_I); // Set ShortCut Keys
        this.jbnButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                JButtonDemo2.this.jtfInput.setText("Button 1!");
            }
        });
        this.jbnButton2.setMnemonic(KeyEvent.VK_I);
        this.jbnButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                JButtonDemo2.this.jtfInput.setText("Button 2!");
            }
        });
        this.jplPanel.setLayout(new FlowLayout());
        this.jplPanel.add(this.jtfInput);
        this.jplPanel.add(this.jbnButton1);
        this.jplPanel.add(this.jbnButton2);
        this.jtfMainFrame.getContentPane().add(this.jplPanel, BorderLayout.CENTER);
        this.jtfMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jtfMainFrame.pack();
        this.jtfMainFrame.setVisible(true);
    }

    public static void main(final String[] args) {
        // Set the look and feel to Java Swing Look
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }
        new JButtonDemo2();
    }
}
