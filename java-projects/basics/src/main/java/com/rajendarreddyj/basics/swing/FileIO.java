package com.rajendarreddyj.basics.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FileIO extends JFrame implements ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getAnonymousLogger();
    JLabel text;
    JButton button;
    JPanel panel;
    JTextField textField;
    private boolean _clickMeMode = true;

    FileIO() { // Begin Constructor
        this.text = new JLabel("Text to save to file:");
        this.button = new JButton("Click Me");
        this.button.addActionListener(this);
        this.textField = new JTextField(30);
        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.panel.setBackground(Color.white);
        this.getContentPane().add(this.panel);
        this.panel.add(BorderLayout.NORTH, this.text);
        this.panel.add(BorderLayout.CENTER, this.textField);
        this.panel.add(BorderLayout.SOUTH, this.button);
    } // End Constructor

    @Override
    public void actionPerformed(final ActionEvent event) {
        Object source = event.getSource();
        // The equals operator (==) is one of the few operators
        // allowed on an object in the Java programming language
        if (source == this.button) {
            String s = null;
            // Write to file
            if (this._clickMeMode) {
                try {
                    String text = this.textField.getText();
                    byte b[] = text.getBytes();
                    String outputFileName = System.getProperty("user.home", File.separatorChar + "home" + File.separatorChar + "zelda") + File.separatorChar
                            + "text.txt";
                    FileOutputStream out = new FileOutputStream(outputFileName);
                    out.write(b);
                    out.close();
                } catch (java.io.IOException e) {
                    logger.info("Cannot write to text.txt");
                }
                // Read from file
                try {
                    String inputFileName = System.getProperty("user.home", File.separatorChar + "home" + File.separatorChar + "zelda") + File.separatorChar
                            + "text.txt";
                    File inputFile = new File(inputFileName);
                    FileInputStream in = new FileInputStream(inputFile);
                    byte bt[] = new byte[(int) inputFile.length()];
                    in.read(bt);
                    s = new String(bt);
                    in.close();
                } catch (java.io.IOException e) {
                    logger.info("Cannot read from text.txt");
                }
                // Clear text field
                this.textField.setText("");
                // Display text read from file
                this.text.setText("Text retrieved from file:");
                this.textField.setText(s);
                this.button.setText("Click Again");
                this._clickMeMode = false;
            } else {
                // Save text to file
                this.text.setText("Text to save to file:");
                this.textField.setText("");
                this.button.setText("Click Me");
                this._clickMeMode = true;
            }
        }
    }

    public static void main(final String[] args) {
        FileIO frame = new FileIO();
        frame.setTitle("Example");
        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(l);
        frame.pack();
        frame.setVisible(true);
    }
}