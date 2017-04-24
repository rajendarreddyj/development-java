package com.rajendarreddyj.browser;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class WebBrowser extends JFrame {
    private static final long serialVersionUID = 1L;
    private JEditorPane myEditor; // displays web page
    private JLabel myNextURL; // if link clicked, go here
    private JTextField myURLDisplay; // user-entered url
    private JButton myBackButton; // generates call of doBack()
    private JButton myNextButton; // generates call of doNext()
    private JButton myGoButton; // goes to file located in myURLDisplay
    private JButton myHomeButton; // goes to myHome
    private String myHome; // url of user selected homepage

    private JMenuItem myHomeMenuItem; // home in Go menu
    private JMenuItem myNextMenuItem; // next in Go menu
    private JMenuItem myBackMenuItem; // back in Go menu

    /**
     * Construct the web browser, calling code should set size of frame.
     */
    public WebBrowser() {
        this.myEditor = new JEditorPane();
        this.myEditor.setEditable(false); // allows links to be followed
        this.myHome = ""; // no home page, but not null
        this.initGui();
    }

    /**
     * Make menus
     */
    private void makeMenu() {

        JMenuBar menubar = new JMenuBar(); // where all menus go

        // create file menu, add quit to it

        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        fileMenu.add(new AbstractAction("Quit") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });

        // create go menu, for next/back/home

        JMenu goMenu = new JMenu("Go");
        menubar.add(goMenu);

        // store in instance variables back/next menu items

        this.myBackMenuItem = new JMenuItem(new AbstractAction("Back") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(final ActionEvent e) {
                WebBrowser.this.doBack();
            }
        });
        goMenu.add(this.myBackMenuItem);

        this.myNextMenuItem = new JMenuItem(new AbstractAction("Next") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(final ActionEvent e) {
                WebBrowser.this.doNext();
            }
        });
        goMenu.add(this.myNextMenuItem);

        this.myHomeMenuItem = new JMenuItem(new AbstractAction("Home") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(final ActionEvent e) {
                WebBrowser.this.doHome();
            }
        });
        goMenu.add(this.myHomeMenuItem);

        // tools menu, set home page for now
        final JMenu toolsMenu = new JMenu("Tools");
        menubar.add(toolsMenu);

        toolsMenu.add(new AbstractAction("Set homepage") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(final ActionEvent e) {
                WebBrowser.this.myHome = WebBrowser.this.myURLDisplay.getText();
            }
        });

        this.setJMenuBar(menubar);
    }

    /**
     * Generates appropriate buttons for the browser. Back, Next, Home are enabled, arguably should NOT be at first
     * though back/next should not be enabled at first.
     * 
     * @return returns a JPanel containing all the buttons
     */
    private JPanel makeButtons() {
        this.myBackButton = new JButton("back", new ImageIcon("back.gif"));

        this.myNextButton = new JButton("next", new ImageIcon("next.gif"));
        this.myHomeButton = new JButton("home", new ImageIcon("home.gif"));

        // add listener so backButton calls doBack()
        this.myBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                WebBrowser.this.doBack();

            }
        });

        // add listener so nextButton calls doNext()
        this.myNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                WebBrowser.this.doNext();
            }
        });

        // add listener so homeButton calls doHome()
        this.myHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                WebBrowser.this.doHome();
            }
        });

        // create panel with back,next,home
        // flow layout used, alignment set to 0 (far left)
        JPanel panel = new JPanel(new FlowLayout(0));
        panel.add(this.myBackButton);
        panel.add(this.myNextButton);
        panel.add(this.myHomeButton);
        return panel;
    }

    /**
     * Make user-entered URL/text field and back/next/home/go buttons
     * 
     * @return returns JPanel containing buttons
     */

    private JPanel makeTopPanel() {

        this.myGoButton = new JButton("Go", new ImageIcon("gosmall.gif"));
        this.myGoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                WebBrowser.this.showPage(WebBrowser.this.myURLDisplay.getText());
            }
        });
        this.myURLDisplay = new JTextField(35);

        JLabel topLabel = new JLabel("Address ");
        JPanel urlPanel = new JPanel(new BorderLayout());
        urlPanel.add(topLabel, BorderLayout.WEST);
        urlPanel.add(this.myURLDisplay, BorderLayout.CENTER);
        urlPanel.add(this.myGoButton, BorderLayout.EAST);
        urlPanel.add(this.makeButtons(), BorderLayout.NORTH);

        // if user presses return, load/show the URL
        this.myURLDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                WebBrowser.this.showPage(e.getActionCommand());
            }
        });

        return urlPanel;
    }

    /**
     * Make the panel where "would-be" clicked URL is displayed and the
     * 
     * @return returns a JPanel containing the HTML window and the panel at the bottom that lists the URL of a
     *         rolled-over link
     */
    private JPanel makeBottomPanel() {
        // bottomLabel is a hack. Because the myNextURL label
        // is initially empty it doesn't show up, the bottomLabel does

        this.myNextURL = new JLabel();
        JLabel bottomLabel = new JLabel(" ");
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(bottomLabel, BorderLayout.WEST);
        bottomPanel.add(this.myNextURL, BorderLayout.CENTER);
        return bottomPanel;
    }

    /**
     * make all UI components, lay them out, ready to use
     */
    private void initGui() {
        this.makeMenu();
        JPanel topPanel = this.makeTopPanel();
        JPanel bottomPanel = this.makeBottomPanel();

        // make editor respond to link-clicks/mouse-overs, make it scroll
        this.myEditor.addHyperlinkListener(new LinkFollower());
        JScrollPane scroller = new JScrollPane(this.myEditor);

        // add components to frame, make it exit when closed
        // default for contentPane is BorderLayout, use this

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.getContentPane().add(scroller, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        this.setTitle("WebBrowser");
    }

    /**
     * called when next button pressed
     */
    protected void doNext() {
        JOptionPane.showMessageDialog(null, "you pressed the next button");
    }

    /**
     * called when back button pressed
     */
    protected void doBack() {
        JOptionPane.showMessageDialog(null, "you pressed the back button");
    }

    /**
     * Displays homepage.
     */
    protected void doHome() {
        if (!this.myHome.equals("")) {
            this.showPage(this.myHome);
        }
    }

    /**
     * displays the url the user enters and echo the url in the textfield (even if it came from there!) the echo makes
     * it so link-clicking shows the URL
     */
    private void showPage(final String url) {
        try {
            this.myEditor.setPage(url);
            this.myURLDisplay.setText(url);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "could not load " + url);
        }
    }

    /**
     * Show where link would take us if clicked
     * 
     * @param s
     *            URL of link
     */

    private void showNextURL(final String s) {
        this.myNextURL.setText(s);
    }

    /**
     * Inner class to deal with link-clicks and mouse-overs
     */

    private class LinkFollower implements HyperlinkListener {
        @Override
        public void hyperlinkUpdate(final HyperlinkEvent evt) {
            if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                // user clicked a link, load it and show it
                URL url = null;
                try {
                    url = evt.getURL();
                    WebBrowser.this.showPage(url.toString());
                } catch (Exception e) {
                    String s = evt.getURL().toString();
                    JOptionPane.showMessageDialog(WebBrowser.this, "loading problem for " + s + " " + e, "Load Problem", JOptionPane.ERROR_MESSAGE);
                }
            } else if (evt.getEventType() == HyperlinkEvent.EventType.ENTERED) {
                // user moused-into a link, show what would load

                try {
                    WebBrowser.this.showNextURL(evt.getURL().toString());
                } catch (Exception e) {
                    // nothing to do, if URL fails, don't pre-announce
                }
            } else if (evt.getEventType() == HyperlinkEvent.EventType.EXITED) {
                // user moused-out of a link, erase what was shown

                WebBrowser.this.showNextURL("");
            }
        }
    }

    /** opens a new browser window */
    public static void main(final String args[]) {
        WebBrowser b = new WebBrowser();
        b.setSize(600, 600);
        b.setLocation(10, 20);
        b.setVisible(true);
    }
}
