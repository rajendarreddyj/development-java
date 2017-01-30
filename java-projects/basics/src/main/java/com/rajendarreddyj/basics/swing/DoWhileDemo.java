package com.rajendarreddyj.basics.swing;

import javax.swing.JOptionPane;

public class DoWhileDemo {
    public static void main(final String[] args) {
        String command;
        boolean isWrongCommand;
        do {
            command = JOptionPane.showInputDialog("Enter the Command");
            if (command.equals("Hello")) {
                System.out.println("Hello.....COMMAND IS RIGHT");
                isWrongCommand = false;
            } else {
                isWrongCommand = true;
            }
        } while (isWrongCommand);
    }
}
