package com.rajendarreddyj.basics.swing;

import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class SwitchDemo {
    private static final Logger logger = Logger.getAnonymousLogger();
    public static void main(final String[] args) {
        String month = JOptionPane.showInputDialog("Enter the Month Number");
        int monthNumber = Integer.parseInt(month);
        switch (monthNumber) {
        case 1:
            logger.info("JANUARY");
            logger.info("Happy New Year");
            break;
        case 2:
            logger.info("FEBRUARY");
            logger.info("The Shortest Month of the Year");
            break;
        case 3:
            logger.info("MARCH");
            logger.info("A daffodil Month");
            break;
        case 4:
            logger.info("APRIL");
            logger.info("April first is a fools day");
            break;
        case 5:
            logger.info("MAY");
            logger.info("Hottest Month");
            break;
        case 6:
            logger.info("JUNE");
            break;
        case 7:
            logger.info("JULY");
            break;
        case 8:
            logger.info("AUGUST");
            break;
        case 9:
            logger.info("SEPTEMBER");
            break;
        case 10:
            logger.info("OCTOBER");
            break;
        case 11:
            logger.info("NOVEMBER");
            break;
        case 12:
            logger.info("DECEMBER");
            break;
        default:
            logger.info("The number you have entered does not match with any month");
            break;
        }
    }
}
