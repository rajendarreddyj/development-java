package com.rajendarreddyj.basics.pattern.observable.rps.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.rajendarreddyj.basics.pattern.observable.rps.model.Game;

/**
 * This class represents a GUI for a basic rock-paper-scissors game. Today's version also loads the RPS game history
 * data every time the GUI loads and saves it every time you close the window.
 * 
 * @author rajendarreddy
 */
public class RockPaperScissorsGui {
    // file we use to save our serialized game state
    private static final String SAVE_FILE = "rps.dat";
    private JFrame frame;
    private JButton play;
    private JButton switchViews;
    private JButton history;
    private View view;
    private Game game;

    /**
     * Constructs the GUI and displays it on the screen.
     */
    public RockPaperScissorsGui() throws Exception {
        this.game = Game.load(SAVE_FILE);
        this.setupComponents();
        this.handleEvents();
        this.doLayout();
        this.frame.setVisible(true);
    }

    // sets up graphical components in the window
    private void setupComponents() {
        this.frame = new JFrame("Rock-Paper-Scissors");
        this.frame.setLocation(300, 100);
        this.frame.setSize(400, 300);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view = new ImageView(this.game);
        this.play = new JButton("Play");
        this.switchViews = new JButton("Switch Views");
        this.history = new JButton("History");
    }

    // attaches various listeners to handle events
    private void handleEvents() {
        ActionListener listener = new ButtonListener();
        this.play.addActionListener(listener);
        this.switchViews.addActionListener(listener);
        this.history.addActionListener(listener);
        this.frame.addWindowListener(new Saver());
    }

    // sets up containers for layout in the window
    private void doLayout() {
        Container south = new JPanel(new FlowLayout());
        south.add(this.play);
        south.add(this.switchViews);
        south.add(this.history);
        this.frame.add(this.view, BorderLayout.CENTER);
        this.frame.add(south, BorderLayout.SOUTH);
    }

    // switches views in (Image -> Painted -> Text) order
    private void switchViews() {
        this.frame.remove(this.view);
        // hey view, stop listening now.
        this.view.goAway();
        if (this.view instanceof TextView) {
            this.view = new ImageView(this.game);
        } else if (this.view instanceof ImageView) {
            this.view = new PaintedView(this.game);
        } else {
            this.view = new TextView(this.game);
        }
        this.frame.add(this.view, BorderLayout.CENTER);
        // tell Java to update the layout on the screen
        this.frame.validate();
    }

    // displays history of past games on screen as an option pane
    private void showHistory() {
        String matches = "";
        for (String match : this.game.getHistory()) {
            matches += match + "\n";
        }
        JOptionPane.showMessageDialog(this.frame, matches);
    }

    // This listener responds to clicks on the "Play" button.
    private class ButtonListener implements ActionListener {
        /**
         * Called when the Play button is clicked. Plays a new game round of tic-tac-toe.
         */
        @Override
        public void actionPerformed(final ActionEvent event) {
            if (event.getSource() == RockPaperScissorsGui.this.play) {
                RockPaperScissorsGui.this.game.playRound();
            } else if (event.getSource() == RockPaperScissorsGui.this.switchViews) {
                RockPaperScissorsGui.this.switchViews();
            } else {
                RockPaperScissorsGui.this.showHistory();
            }
        }
    }

    // Listens to window closing events in this GUI so that we can save
    // the game history just before the program shuts down.
    private class Saver extends WindowAdapter {
        @Override
        public void windowClosing(final WindowEvent event) {
            try {
                RockPaperScissorsGui.this.game.save(SAVE_FILE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(RockPaperScissorsGui.this.frame, "Save failed: " + e.getMessage());
            }
        }
    }
}
