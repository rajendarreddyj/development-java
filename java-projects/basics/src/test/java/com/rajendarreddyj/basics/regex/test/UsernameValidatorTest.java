package com.rajendarreddyj.basics.regex.test;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rajendarreddyj.basics.regex.UsernameValidator;

public class UsernameValidatorTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    private UsernameValidator usernameValidator;

    @BeforeClass
    public void initData() {
        this.usernameValidator = new UsernameValidator();
    }

    @DataProvider
    public Object[][] ValidUsernameProvider() {
        return new Object[][] {
                { new String[] { "RAjendar34 *()(", "rajendar_2002", "rajendar-2002", "rj3-4_dar", "`~!@#$%^&*()-_+", "raj-e//", "=:;{}|,\"" } } };
    }

    @DataProvider
    public Object[][] InvalidUsernameProvider() {
        return new Object[][] { { new String[] { "rj", "ra@jend?/ sfs]*(*", "áéíóú123456789_-" } } };
    }

    @Test(dataProvider = "ValidUsernameProvider")
    public void ValidUsernameTest(final String[] Username) {
        for (String temp : Username) {
            boolean valid = this.usernameValidator.validate(temp);
            logger.info("Username is valid : " + temp + " , " + valid);
            Assert.assertEquals(true, valid);
        }
    }

    @Test(dataProvider = "InvalidUsernameProvider", dependsOnMethods = "ValidUsernameTest")
    public void InValidUsernameTest(final String[] Username) {
        for (String temp : Username) {
            boolean valid = this.usernameValidator.validate(temp);
            logger.info("username is valid : " + temp + " , " + valid);
            Assert.assertEquals(false, valid);
        }
    }
}