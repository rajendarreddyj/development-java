package com.rajendarreddyj.basics.swing;

import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class DoWhileDemo {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        String command;
        boolean isWrongCommand;
        do {
            command = JOptionPane.showInputDialog("Enter the Command");
            if (command.equals("Hello")) {
                logger.info("Hello.....COMMAND IS RIGHT");
                isWrongCommand = false;
            } else {
                isWrongCommand = true;
            }
        } while (isWrongCommand);
    }
}
