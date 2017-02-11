package com.rajendarreddyj.basics.swing.demo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 * @author rajendarreddy
 *
 */
public class JPasswordFieldDemo {

    public static void main(final String[] argv) {
        final JFrame frame = new JFrame("JPassword Demo");
        JLabel jlbPassword = new JLabel("Enter the password: ");
        JPasswordField jpwName = new JPasswordField(10);
        jpwName.setEchoChar('*');
        jpwName.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                JPasswordField input = (JPasswordField) e.getSource();
                char[] password = input.getPassword();
                if (isPasswordCorrect(password)) {
                    JOptionPane.showMessageDialog(frame, "Correct  password.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Sorry. Try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JPanel jplContentPane = new JPanel(new BorderLayout());
        jplContentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jplContentPane.add(jlbPassword, BorderLayout.WEST);
        jplContentPane.add(jpwName, BorderLayout.CENTER);
        frame.setContentPane(jplContentPane);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    private static boolean isPasswordCorrect(final char[] inputPassword) {
        char[] actualPassword = { 'p', 'a', 's', 's', 'w', 'o', 'r', 'd' };
        if (inputPassword.length != actualPassword.length) {
            return false; // Return false if lengths are unequal
        }
        for (int i = 0; i < inputPassword.length; i++) {
            if (inputPassword[i] != actualPassword[i]) {
                return false;
            }
        }
        return true;
    }
}
