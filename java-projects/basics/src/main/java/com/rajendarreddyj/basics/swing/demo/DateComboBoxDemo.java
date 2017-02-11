package com.rajendarreddyj.basics.swing.demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author rajendarreddy
 *
 */
public class DateComboBoxDemo extends JPanel {

    private static final long serialVersionUID = 1L;
    static JFrame frame;
    JLabel jlbResult;
    String datePattern_Current;

    public DateComboBoxDemo() {
        String[] datePatterns = { "dd MMMMM yyyy", "dd.MM.yy", "MM/dd/yy", "yyyy.MM.dd G 'at' hh:mm:ss z", "EEE, MMM d, ''yy", "h:mm a", "H:mm:ss:SSS",
                "K:mm a,z", "yyyy.MMMMM.dd GGG hh:mm aaa" };
        this.datePattern_Current = datePatterns[0];
        // Set up the UI for selecting a pattern.
        JLabel jlbHeading = new JLabel("Enter Date pattern /Select from list:");
        JComboBox<String> patternList = new JComboBox<>(datePatterns);
        patternList.setEditable(true);
        patternList.setAlignmentX(Component.LEFT_ALIGNMENT);
        patternList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                @SuppressWarnings("unchecked")
                JComboBox<String> jcmbDates = (JComboBox<String>) e.getSource();
                String seletedDate = (String) jcmbDates.getSelectedItem();
                DateComboBoxDemo.this.datePattern_Current = seletedDate;
                DateComboBoxDemo.this.showDateinLabel();
            }
        });
        // Create the UI for displaying result
        JLabel jlbResultHeading = new JLabel("Current Date/Time", JLabel.LEFT);
        this.jlbResult = new JLabel(" ");
        this.jlbResult.setForeground(Color.black);
        this.jlbResult.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        // Lay out everything
        JPanel jpnDate = new JPanel();
        jpnDate.setLayout(new BoxLayout(jpnDate, BoxLayout.Y_AXIS));
        jpnDate.add(jlbHeading);
        jpnDate.add(patternList);
        JPanel jpnResults = new JPanel();
        jpnResults.setLayout(new GridLayout(0, 1));
        jpnResults.add(jlbResultHeading);
        jpnResults.add(this.jlbResult);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        jpnDate.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpnResults.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(jpnDate);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(jpnResults);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.showDateinLabel();
    } // constructor

    /** Formats and displays today's date. */
    public void showDateinLabel() {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(this.datePattern_Current);
        try {
            String dateString = formatter.format(today);
            this.jlbResult.setForeground(Color.black);
            this.jlbResult.setText(dateString);
        } catch (IllegalArgumentException e) {
            this.jlbResult.setForeground(Color.red);
            this.jlbResult.setText("Error: " + e.getMessage());
        }
    }

    public static void main(final String s[]) {
        frame = new JFrame("JComboBox Usage Demo");
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setContentPane(new DateComboBoxDemo());
        frame.pack();
        frame.setVisible(true);
    }
}
