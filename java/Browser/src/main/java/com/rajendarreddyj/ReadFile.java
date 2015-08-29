package com.rajendarreddyj;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class ReadFile extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField addressBar;
	private JEditorPane display = new JEditorPane();

	// The constructor
	public ReadFile() {
		// Title of the browser
		super("Rajendar Browser");

		addressBar = new JTextField("http://");
		addressBar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Action(e.getActionCommand());

			}
		});

		// Add addressBar object to the JFrame
		add(addressBar, BorderLayout.NORTH);

		display.setEditable(false);
		display.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent h) {
				if (h.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
					Action(h.getURL().toString());

			}
		}

		);
		// Add display object to the JFrame
		add(new JScrollPane(display), BorderLayout.CENTER);
		setSize(1024, 768);
		setVisible(true);

	}

	// Load to display on the screen
	private void Action(String userText) {
		try {
			// Displays the WebPage using setPage method !**
			display.setPage(userText);
			addressBar.setText(userText);
		} catch (Exception e) {
			System.out.println("Error!");
		}
	}
}