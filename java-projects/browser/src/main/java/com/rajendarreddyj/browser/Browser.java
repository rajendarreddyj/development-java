package com.rajendarreddyj.browser;

import java.awt.BorderLayout;
import java.util.logging.Logger;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;

public class Browser extends JFrame {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static final long serialVersionUID = 1L;
    private final JTextField addressBar;
    private final JEditorPane display = new JEditorPane();

    // The constructor
    public Browser() {
        // Title of the browser
        super("Rajendar Browser");
        this.addressBar = new JTextField("http://");
        this.addressBar.addActionListener(e -> Browser.this.Action(e.getActionCommand()));

        // Add addressBar object to the JFrame
        this.add(this.addressBar, BorderLayout.NORTH);

        this.display.setEditable(false);
        this.display.addHyperlinkListener(h -> {
            if (h.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                Browser.this.Action(h.getURL().toString());
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
            logger.info("Error!");
        }
    }
}