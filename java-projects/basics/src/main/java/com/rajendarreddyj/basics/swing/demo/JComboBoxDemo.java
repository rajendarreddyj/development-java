package com.rajendarreddyj.basics.swing.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author rajendarreddy
 *
 */
public class JComboBoxDemo extends JPanel {

    private static final long serialVersionUID = 1L;
    JLabel jlbPicture;

    public JComboBoxDemo() {
        String[] comboTypes = { "Numbers", "Alphabets", "Symbols" };
        // Create the combo box, and set 2nd item as Default
        JComboBox<String> comboTypesList = new JComboBox<>(comboTypes);
        comboTypesList.setSelectedIndex(2);
        comboTypesList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                @SuppressWarnings("unchecked")
                JComboBox<String> jcmbType = (JComboBox<String>) e.getSource();
                String cmbType = (String) jcmbType.getSelectedItem();
                JComboBoxDemo.this.jlbPicture.setIcon(new ImageIcon("src/main/resources/images/" + cmbType.trim().toLowerCase() + ".jpg"));
            }
        });
        // Set up the picture
        this.jlbPicture = new JLabel(new ImageIcon("" + comboTypes[comboTypesList.getSelectedIndex()] + ".jpg"));
        this.jlbPicture.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        this.jlbPicture.setPreferredSize(new Dimension(177, 122 + 10));
        // Layout the demo
        this.setLayout(new BorderLayout());
        this.add(comboTypesList, BorderLayout.NORTH);
        this.add(this.jlbPicture, BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public static void main(final String s[]) {
        JFrame frame = new JFrame("JComboBox Usage Demo");
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setContentPane(new JComboBoxDemo());
        frame.pack();
        frame.setVisible(true);
    }
}
