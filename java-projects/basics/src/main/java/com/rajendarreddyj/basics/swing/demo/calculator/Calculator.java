package com.rajendarreddyj.basics.swing.demo.calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

/**
 * @author rajendarreddy
 *
 */
public class Calculator extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    // Variables
    final int MAX_INPUT_LENGTH = 20;
    final int INPUT_MODE = 0;
    final int RESULT_MODE = 1;
    final int ERROR_MODE = 2;
    int displayMode;

    boolean clearOnNextDigit, percent;
    double lastNumber;
    String lastOperator;

    private JMenu jmenuFile, jmenuHelp;
    private JMenuItem jmenuitemExit, jmenuitemAbout;

    private JLabel jlbOutput;
    private JButton jbnButtons[];
    private JPanel jplMaster, jplBackSpace, jplControl;

    /*
     * Font(String name, int style, int size)
      Creates a new Font from the specified name, style and point size.
     */

    Font f12 = new Font("Times New Roman", 0, 12);
    Font f121 = new Font("Times New Roman", 1, 12);

    // Constructor
    public Calculator() {
        /* Set Up the JMenuBar.
         * Have Provided All JMenu's with Mnemonics
         * Have Provided some JMenuItem components with Keyboard Accelerators
         */

        this.jmenuFile = new JMenu("File");
        this.jmenuFile.setFont(this.f121);
        this.jmenuFile.setMnemonic(KeyEvent.VK_F);

        this.jmenuitemExit = new JMenuItem("Exit");
        this.jmenuitemExit.setFont(this.f12);
        this.jmenuitemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        this.jmenuFile.add(this.jmenuitemExit);

        this.jmenuHelp = new JMenu("Help");
        this.jmenuHelp.setFont(this.f121);
        this.jmenuHelp.setMnemonic(KeyEvent.VK_H);

        this.jmenuitemAbout = new JMenuItem("About Calculator");
        this.jmenuitemAbout.setFont(this.f12);
        this.jmenuHelp.add(this.jmenuitemAbout);

        JMenuBar mb = new JMenuBar();
        mb.add(this.jmenuFile);
        mb.add(this.jmenuHelp);
        this.setJMenuBar(mb);

        // Set frame layout manager

        this.setBackground(Color.gray);

        this.jplMaster = new JPanel();

        this.jlbOutput = new JLabel("0");
        this.jlbOutput.setHorizontalTextPosition(JLabel.RIGHT);
        this.jlbOutput.setBackground(Color.WHITE);
        this.jlbOutput.setOpaque(true);

        // Add components to frame
        this.getContentPane().add(this.jlbOutput, BorderLayout.NORTH);

        this.jbnButtons = new JButton[23];
        // GridLayout(int rows, int cols, int hgap, int vgap)

        JPanel jplButtons = new JPanel(); // container for Jbuttons

        // Create numeric Jbuttons
        for (int i = 0; i <= 9; i++) {
            // set each Jbutton label to the value of index
            this.jbnButtons[i] = new JButton(String.valueOf(i));
        }

        // Create operator Jbuttons
        this.jbnButtons[10] = new JButton("+/-");
        this.jbnButtons[11] = new JButton(".");
        this.jbnButtons[12] = new JButton("=");
        this.jbnButtons[13] = new JButton("/");
        this.jbnButtons[14] = new JButton("*");
        this.jbnButtons[15] = new JButton("-");
        this.jbnButtons[16] = new JButton("+");
        this.jbnButtons[17] = new JButton("sqrt");
        this.jbnButtons[18] = new JButton("1/x");
        this.jbnButtons[19] = new JButton("%");

        this.jplBackSpace = new JPanel();
        this.jplBackSpace.setLayout(new GridLayout(1, 1, 2, 2));

        this.jbnButtons[20] = new JButton("Backspace");
        this.jplBackSpace.add(this.jbnButtons[20]);

        this.jplControl = new JPanel();
        this.jplControl.setLayout(new GridLayout(1, 2, 2, 2));

        this.jbnButtons[21] = new JButton(" CE ");
        this.jbnButtons[22] = new JButton("C");

        this.jplControl.add(this.jbnButtons[21]);
        this.jplControl.add(this.jbnButtons[22]);

        // Setting all Numbered JButton's to Blue. The rest to Red
        for (int i = 0; i < this.jbnButtons.length; i++) {
            this.jbnButtons[i].setFont(this.f12);

            if (i < 10) {
                this.jbnButtons[i].setForeground(Color.blue);
            } else {
                this.jbnButtons[i].setForeground(Color.red);
            }
        }

        // Set panel layout manager for a 4 by 5 grid
        jplButtons.setLayout(new GridLayout(4, 5, 2, 2));

        // Add buttons to keypad panel starting at top left
        // First row
        for (int i = 7; i <= 9; i++) {
            jplButtons.add(this.jbnButtons[i]);
        }

        // add button / and sqrt
        jplButtons.add(this.jbnButtons[13]);
        jplButtons.add(this.jbnButtons[17]);

        // Second row
        for (int i = 4; i <= 6; i++) {
            jplButtons.add(this.jbnButtons[i]);
        }

        // add button * and x^2
        jplButtons.add(this.jbnButtons[14]);
        jplButtons.add(this.jbnButtons[18]);

        // Third row
        for (int i = 1; i <= 3; i++) {
            jplButtons.add(this.jbnButtons[i]);
        }

        // adds button - and %
        jplButtons.add(this.jbnButtons[15]);
        jplButtons.add(this.jbnButtons[19]);

        // Fourth Row
        // add 0, +/-, ., +, and =
        jplButtons.add(this.jbnButtons[0]);
        jplButtons.add(this.jbnButtons[10]);
        jplButtons.add(this.jbnButtons[11]);
        jplButtons.add(this.jbnButtons[16]);
        jplButtons.add(this.jbnButtons[12]);

        this.jplMaster.setLayout(new BorderLayout());
        this.jplMaster.add(this.jplBackSpace, BorderLayout.WEST);
        this.jplMaster.add(this.jplControl, BorderLayout.EAST);
        this.jplMaster.add(jplButtons, BorderLayout.SOUTH);

        // Add components to frame
        this.getContentPane().add(this.jplMaster, BorderLayout.SOUTH);
        this.requestFocus();

        // activate ActionListener
        for (int i = 0; i < this.jbnButtons.length; i++) {
            this.jbnButtons[i].addActionListener(this);
        }

        this.jmenuitemAbout.addActionListener(this);
        this.jmenuitemExit.addActionListener(this);

        this.clearAll();

        // add WindowListener for closing frame and ending program
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(final WindowEvent e) {
                System.exit(0);
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } // End of Contructor Calculator

    // Perform action
    @Override
    public void actionPerformed(final ActionEvent e) {
        double result = 0;

        if (e.getSource() == this.jmenuitemAbout) {
            JDialog dlgAbout = new CustomABOUTDialog(this, "About Java Swing Calculator", true);
            dlgAbout.setVisible(true);
        } else if (e.getSource() == this.jmenuitemExit) {
            System.exit(0);
        }

        // Search for the button pressed until end of array or key found
        for (int i = 0; i < this.jbnButtons.length; i++) {
            if (e.getSource() == this.jbnButtons[i]) {
                switch (i) {
                case 0:
                    this.addDigitToDisplay(i);
                    break;

                case 1:
                    this.addDigitToDisplay(i);
                    break;

                case 2:
                    this.addDigitToDisplay(i);
                    break;

                case 3:
                    this.addDigitToDisplay(i);
                    break;

                case 4:
                    this.addDigitToDisplay(i);
                    break;

                case 5:
                    this.addDigitToDisplay(i);
                    break;

                case 6:
                    this.addDigitToDisplay(i);
                    break;

                case 7:
                    this.addDigitToDisplay(i);
                    break;

                case 8:
                    this.addDigitToDisplay(i);
                    break;

                case 9:
                    this.addDigitToDisplay(i);
                    break;

                case 10: // +/-
                    this.processSignChange();
                    break;

                case 11: // decimal point
                    this.addDecimalPoint();
                    break;

                case 12: // =
                    this.processEquals();
                    break;

                case 13: // divide
                    this.processOperator("/");
                    break;

                case 14: // *
                    this.processOperator("*");
                    break;

                case 15: // -
                    this.processOperator("-");
                    break;

                case 16: // +
                    this.processOperator("+");
                    break;

                case 17: // sqrt
                    if (this.displayMode != this.ERROR_MODE) {
                        try {
                            if (this.getDisplayString().indexOf("-") == 0) {
                                this.displayError("Invalid input for function!");
                            }

                            result = Math.sqrt(this.getNumberInDisplay());
                            this.displayResult(result);
                        }

                        catch (Exception ex) {
                            this.displayError("Invalid input for function!");
                            this.displayMode = this.ERROR_MODE;
                        }
                    }
                    break;

                case 18: // 1/x
                    if (this.displayMode != this.ERROR_MODE) {
                        try {
                            if (this.getNumberInDisplay() == 0) {
                                this.displayError("Cannot divide by zero!");
                            }

                            result = 1 / this.getNumberInDisplay();
                            this.displayResult(result);
                        }

                        catch (Exception ex) {
                            this.displayError("Cannot divide by zero!");
                            this.displayMode = this.ERROR_MODE;
                        }
                    }
                    break;

                case 19: // %
                    if (this.displayMode != this.ERROR_MODE) {
                        try {
                            result = this.getNumberInDisplay() / 100;
                            this.displayResult(result);
                        }

                        catch (Exception ex) {
                            this.displayError("Invalid input for function!");
                            this.displayMode = this.ERROR_MODE;
                        }
                    }
                    break;

                case 20: // backspace
                    if (this.displayMode != this.ERROR_MODE) {
                        this.setDisplayString(this.getDisplayString().substring(0, this.getDisplayString().length() - 1));

                        if (this.getDisplayString().length() < 1) {
                            this.setDisplayString("0");
                        }
                    }
                    break;

                case 21: // CE
                    this.clearExisting();
                    break;

                case 22: // C
                    this.clearAll();
                    break;
                }
            }
        }
    }

    void setDisplayString(final String s) {
        this.jlbOutput.setText(s);
    }

    String getDisplayString() {
        return this.jlbOutput.getText();
    }

    void addDigitToDisplay(final int digit) {
        if (this.clearOnNextDigit) {
            this.setDisplayString("");
        }

        String inputString = this.getDisplayString();

        if (inputString.indexOf("0") == 0) {
            inputString = inputString.substring(1);
        }

        if ((!inputString.equals("0") || (digit > 0)) && (inputString.length() < this.MAX_INPUT_LENGTH)) {
            this.setDisplayString(inputString + digit);
        }

        this.displayMode = this.INPUT_MODE;
        this.clearOnNextDigit = false;
    }

    void addDecimalPoint() {
        this.displayMode = this.INPUT_MODE;

        if (this.clearOnNextDigit) {
            this.setDisplayString("");
        }

        String inputString = this.getDisplayString();

        // If the input string already contains a decimal point, don't
        // do anything to it.
        if (inputString.indexOf(".") < 0) {
            this.setDisplayString(new String(inputString + "."));
        }
    }

    void processSignChange() {
        if (this.displayMode == this.INPUT_MODE) {
            String input = this.getDisplayString();

            if ((input.length() > 0) && !input.equals("0")) {
                if (input.indexOf("-") == 0) {
                    this.setDisplayString(input.substring(1));
                } else {
                    this.setDisplayString("-" + input);
                }
            }

        }

        else if (this.displayMode == this.RESULT_MODE) {
            double numberInDisplay = this.getNumberInDisplay();

            if (numberInDisplay != 0) {
                this.displayResult(-numberInDisplay);
            }
        }
    }

    void clearAll() {
        this.setDisplayString("0");
        this.lastOperator = "0";
        this.lastNumber = 0;
        this.displayMode = this.INPUT_MODE;
        this.clearOnNextDigit = true;
    }

    void clearExisting() {
        this.setDisplayString("0");
        this.clearOnNextDigit = true;
        this.displayMode = this.INPUT_MODE;
    }

    double getNumberInDisplay() {
        String input = this.jlbOutput.getText();
        return Double.parseDouble(input);
    }

    void processOperator(final String op) {
        if (this.displayMode != this.ERROR_MODE) {
            double numberInDisplay = this.getNumberInDisplay();

            if (!this.lastOperator.equals("0")) {
                try {
                    double result = this.processLastOperator();
                    this.displayResult(result);
                    this.lastNumber = result;
                }

                catch (DivideByZeroException e) {
                }
            }

            else {
                this.lastNumber = numberInDisplay;
            }

            this.clearOnNextDigit = true;
            this.lastOperator = op;
        }
    }

    void processEquals() {
        double result = 0;

        if (this.displayMode != this.ERROR_MODE) {
            try {
                result = this.processLastOperator();
                this.displayResult(result);
            }

            catch (DivideByZeroException e) {
                this.displayError("Cannot divide by zero!");
            }

            this.lastOperator = "0";
        }
    }

    double processLastOperator() throws DivideByZeroException {
        double result = 0;
        double numberInDisplay = this.getNumberInDisplay();

        if (this.lastOperator.equals("/")) {
            if (numberInDisplay == 0) {
                throw (new DivideByZeroException());
            }

            result = this.lastNumber / numberInDisplay;
        }

        if (this.lastOperator.equals("*")) {
            result = this.lastNumber * numberInDisplay;
        }

        if (this.lastOperator.equals("-")) {
            result = this.lastNumber - numberInDisplay;
        }

        if (this.lastOperator.equals("+")) {
            result = this.lastNumber + numberInDisplay;
        }

        return result;
    }

    void displayResult(final double result) {
        this.setDisplayString(Double.toString(result));
        this.lastNumber = result;
        this.displayMode = this.RESULT_MODE;
        this.clearOnNextDigit = true;
    }

    void displayError(final String errorMessage) {
        this.setDisplayString(errorMessage);
        this.lastNumber = 0;
        this.displayMode = this.ERROR_MODE;
        this.clearOnNextDigit = true;
    }

    public static void main(final String args[]) {
        Calculator calci = new Calculator();
        // Container contentPane = calci.getContentPane();
        // contentPane.setLayout(new BorderLayout());
        calci.setTitle("Java Swing Calculator");
        calci.setSize(241, 217);
        calci.pack();
        calci.setLocation(400, 250);
        calci.setVisible(true);
        calci.setResizable(false);
    }

} // End of Swing Calculator Class.

class DivideByZeroException extends Exception {
    private static final long serialVersionUID = 1L;

    public DivideByZeroException() {
        super();
    }

    public DivideByZeroException(final String s) {
        super(s);
    }
}

class CustomABOUTDialog extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    JButton jbnOk;

    CustomABOUTDialog(final JFrame parent, final String title, final boolean modal) {
        super(parent, title, modal);
        this.setBackground(Color.black);

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        StringBuffer text = new StringBuffer();
        text.append("Calculator Information\n\n");
        text.append("Version:   1.0");

        JTextArea jtAreaAbout = new JTextArea(5, 21);
        jtAreaAbout.setText(text.toString());
        jtAreaAbout.setFont(new Font("Times New Roman", 1, 13));
        jtAreaAbout.setEditable(false);

        p1.add(jtAreaAbout);
        p1.setBackground(Color.red);
        this.getContentPane().add(p1, BorderLayout.CENTER);

        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.jbnOk = new JButton(" OK ");
        this.jbnOk.addActionListener(this);

        p2.add(this.jbnOk);
        this.getContentPane().add(p2, BorderLayout.SOUTH);

        this.setLocation(408, 270);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                Window aboutDialog = e.getWindow();
                aboutDialog.dispose();
            }
        });

        this.pack();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.jbnOk) {
            this.dispose();
        }
    }

}