package com.rajendarreddyj.basics.regex.test;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rajendarreddyj.basics.regex.PasswordValidator;

public class PasswordValidatorTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    private PasswordValidator passwordValidator;

    @BeforeClass
    public void initData() {
        this.passwordValidator = new PasswordValidator();
    }

    @DataProvider
    public Object[][] ValidPasswordProvider() {
        return new Object[][] { { new String[] { "rajendar1A@", "raJEn12$", } } };
    }

    @DataProvider
    public Object[][] InvalidPasswordProvider() {
        return new Object[][] { { new String[] { "rJ1E@", "rajendar12@", "rajendAr12*", "rajendaR$$", "RAJENDAR12$" } } };
    }

    @Test(dataProvider = "ValidPasswordProvider")
    public void ValidPasswordTest(final String[] password) {
        for (String temp : password) {
            boolean valid = this.passwordValidator.validate(temp);
            logger.info("Password is valid : " + temp + " , " + valid);
            Assert.assertEquals(true, valid);
        }
    }

    @Test(dataProvider = "InvalidPasswordProvider", dependsOnMethods = "ValidPasswordTest")
    public void InValidPasswordTest(final String[] password) {
        for (String temp : password) {
            boolean valid = this.passwordValidator.validate(temp);
            logger.info("Password is valid : " + temp + " , " + valid);
            Assert.assertEquals(false, valid);
        }
    }
}
