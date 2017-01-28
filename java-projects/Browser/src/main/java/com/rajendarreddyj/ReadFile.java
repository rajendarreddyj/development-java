package com.rajendarreddyj;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;

public class ReadFile extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTextField addressBar;
    private final JEditorPane display = new JEditorPane();

    // The constructor
    public ReadFile() {
        // Title of the browser
        super("Rajendar Browser");

        this.addressBar = new JTextField("http://");
        this.addressBar.addActionListener(e -> ReadFile.this.Action(e.getActionCommand()));

        // Add addressBar object to the JFrame
        this.add(this.addressBar, BorderLayout.NORTH);

        this.display.setEditable(false);
        this.display.addHyperlinkListener(h -> {
            if (h.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                ReadFile.this.Action(h.getURL().toString());
            }

        }

        );
        // Add display object to the JFrame
        this.add(new JScrollPane(this.display), BorderLayout.CENTER);
        this.setSize(1024, 768);
        this.setVisible(true);

    }

    // Load to display on the screen
    private void Action(final String userText) {
        try {
            // Displays the WebPage using setPage method !**
            this.display.setPage(userText);
            this.addressBar.setText(userText);
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
}