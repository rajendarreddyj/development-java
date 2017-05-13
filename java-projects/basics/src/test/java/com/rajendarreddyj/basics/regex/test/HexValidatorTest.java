package com.rajendarreddyj.basics.regex.test;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rajendarreddyj.basics.regex.HexValidator;

public class HexValidatorTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    private HexValidator hexValidator;

    @BeforeClass
    public void initData() {
        this.hexValidator = new HexValidator();
    }

    @DataProvider
    public Object[][] ValidHexProvider() {
        return new Object[][] { { new String[] { "#1f1f1F", "#AFAFAF", "#1AFFa1", "#222fff", "#F00" } } };
    }

    @DataProvider
    public Object[][] InvalidHexProvider() {
        return new Object[][] { { new String[] { "123456", "#afafah", "#123abce", "aFaE3f", "F00", "#afaf", "#F0h" } } };
    }

    @Test(dataProvider = "ValidHexProvider")
    public void ValidHexTest(final String[] hex) {
        for (String temp : hex) {
            boolean valid = this.hexValidator.validate(temp);
            logger.info("Hex is valid : " + temp + " , " + valid);
            Assert.assertEquals(true, valid);
        }
    }

    @Test(dataProvider = "InvalidHexProvider", dependsOnMethods = "ValidHexTest")
    public void InValidHexTest(final String[] hex) {
        for (String temp : hex) {
            boolean valid = this.hexValidator.validate(temp);
            logger.info("Hex is valid : " + temp + " , " + valid);
            Assert.assertEquals(false, valid);
        }
    }
}