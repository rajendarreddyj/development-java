package com.rajendarreddyj.basics.swing.demo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author rajendarreddy
 *
 */
public class JCheckBoxDemo extends JPanel {

    private static final long serialVersionUID = 1L;
    // Four accessory choices provide for 16 different combinations
    JCheckBox jcbChin;
    JCheckBox jcbGlasses;
    JCheckBox jcbHair;
    JCheckBox jcbTeeth;

    /* The image for each combination is contained in a
     separate image file whose name indicates the accessories.
     The filenames are "geek-XXXX.gif" where XXXX can be one
     * of the following 16 choices.
     */

    StringBuffer choices;
    JLabel jlbPicture;
    CheckBoxListener myListener = null;

    public JCheckBoxDemo() {

        // Add an item listener for each of the check boxes.
        // This is the listener class which contains business logic
        this.myListener = new CheckBoxListener();

        // Create check boxes with default selection true

        this.jcbChin = new JCheckBox("Chin");
        this.jcbChin.setMnemonic(KeyEvent.VK_C);
        this.jcbChin.setSelected(true);
        this.jcbChin.addItemListener(this.myListener);

        this.jcbGlasses = new JCheckBox("Glasses");
        this.jcbGlasses.setMnemonic(KeyEvent.VK_G);
        this.jcbGlasses.setSelected(true);
        this.jcbGlasses.addItemListener(this.myListener);

        this.jcbHair = new JCheckBox("Hair");
        this.jcbHair.setMnemonic(KeyEvent.VK_H);
        this.jcbHair.setSelected(true);
        this.jcbHair.addItemListener(this.myListener);

        this.jcbTeeth = new JCheckBox("Teeth");
        this.jcbTeeth.setMnemonic(KeyEvent.VK_T);
        this.jcbTeeth.setSelected(true);
        this.jcbTeeth.addItemListener(this.myListener);

        // Indicates what's on the geek.
        this.choices = new StringBuffer("cght");// Default Image has all the parts.

        // Set up the picture label
        this.jlbPicture = new JLabel(new ImageIcon("src/main/resources/images/geek-" + this.choices.toString().trim() + ".gif"));
        this.jlbPicture.setToolTipText(this.choices.toString().trim());

        // Put the check boxes in a column in a panel
        JPanel jplCheckBox = new JPanel();
        jplCheckBox.setLayout(new GridLayout(0, 1)); // 0 rows, 1 Column
        jplCheckBox.add(this.jcbChin);
        jplCheckBox.add(this.jcbGlasses);
        jplCheckBox.add(this.jcbHair);
        jplCheckBox.add(this.jcbTeeth);

        this.setLayout(new BorderLayout());
        this.add(jplCheckBox, BorderLayout.WEST);
        this.add(this.jlbPicture, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    // Listens to the check boxes events
    class CheckBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(final ItemEvent e) {
            int index = 0;
            char c = '-';
            Object source = e.getSource();
            if (source == JCheckBoxDemo.this.jcbChin) {
                index = 0;
                c = 'c';
            } else if (source == JCheckBoxDemo.this.jcbGlasses) {
                index = 1;
                c = 'g';
            } else if (source == JCheckBoxDemo.this.jcbHair) {
                index = 2;
                c = 'h';
            } else if (source == JCheckBoxDemo.this.jcbTeeth) {
                index = 3;
                c = 't';
            }

            if (e.getStateChange() == ItemEvent.DESELECTED) {
                c = '-';
            }

            JCheckBoxDemo.this.choices.setCharAt(index, c);
            JCheckBoxDemo.this.jlbPicture.setIcon(new ImageIcon("src/main/resources/images/geek-" + JCheckBoxDemo.this.choices.toString().trim() + ".gif"));
            JCheckBoxDemo.this.jlbPicture.setToolTipText(JCheckBoxDemo.this.choices.toString());
        }
    }

    public static void main(final String s[]) {
        JFrame frame = new JFrame("JCheckBox Demo");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setContentPane(new JCheckBoxDemo());
        frame.pack();
        frame.setVisible(true);
    }

}
