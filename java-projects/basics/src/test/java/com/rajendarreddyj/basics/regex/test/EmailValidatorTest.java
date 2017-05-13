package com.rajendarreddyj.basics.regex.test;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rajendarreddyj.basics.regex.EmailValidator;

public class EmailValidatorTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    private EmailValidator emailValidator;

    @BeforeClass
    public void initData() {
        this.emailValidator = new EmailValidator();
    }

    @DataProvider
    public Object[][] ValidEmailProvider() {
        return new Object[][] { { new String[] { "rajendar@yahoo.com", "rajendar-100@yahoo.com", "rajendar.100@yahoo.com", "rajendar111@rajendar.com",
                "rajendar-100@rajendar.net", "rajendar.100@rajendar.com.au", "rajendar@1.com", "rajendar@gmail.com.com" } } };
    }

    @DataProvider
    public Object[][] InvalidEmailProvider() {
        return new Object[][] { { new String[] { "rajendar", "rajendar@.com.my", "rajendar123@gmail.a", "rajendar123@.com", "rajendar123@.com.com",
                ".rajendar@rajendar.com", "rajendar()*@gmail.com", "rajendar@%*.com", "rajendar..2002@gmail.com", "rajendar.@gmail.com",
                "rajendar@rajendar@gmail.com", "rajendar@gmail.com.1a" } } };
    }

    @Test(dataProvider = "ValidEmailProvider")
    public void ValidEmailTest(final String[] Email) {
        for (String temp : Email) {
            boolean valid = this.emailValidator.validate(temp);
            logger.info("Email is valid : " + temp + " , " + valid);
            Assert.assertEquals(true, valid);
        }
    }

    @Test(dataProvider = "InvalidEmailProvider", dependsOnMethods = "ValidEmailTest")
    public void InValidEmailTest(final String[] Email) {
        for (String temp : Email) {
            boolean valid = this.emailValidator.validate(temp);
            logger.info("Email is valid : " + temp + " , " + valid);
            Assert.assertEquals(false, valid);
        }
    }
}
