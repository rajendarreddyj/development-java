package com.rajendarreddyj.basics.regex.test;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rajendarreddyj.basics.regex.HTMLTagValidator;

public class HTMLTagValidatorTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    private HTMLTagValidator htmlTagValidator;

    @BeforeClass
    public void initData() {
        this.htmlTagValidator = new HTMLTagValidator();
    }

    @DataProvider
    public Object[][] ValidHTMLTagProvider() {
        return new Object[][] { new Object[] { "<b>" }, new Object[] { "<input value='>'>" }, new Object[] { "<input value='<'>" }, new Object[] { "<b/>" },
                new Object[] { "<a href='http://www.google.com'>" }, new Object[] { "<br>" }, new Object[] { "<br/>" },
                new Object[] { "<input value=\"\" id='test'>" }, new Object[] { "<input value='' id='test'>" } };
    }

    @DataProvider
    public Object[][] InvalidHTMLTagProvider() {
        return new Object[][] { new Object[] { "<input value=\" id='test'>" }, new Object[] { "<input value=' id='test'>" },
                new Object[] { "<input value=> >" } };
    }

    @Test(dataProvider = "ValidHTMLTagProvider")
    public void ValidHTMLTagTest(final String tag) {
        boolean valid = this.htmlTagValidator.validate(tag);
        logger.info("HTMLTag is valid : " + tag + " , " + valid);
        Assert.assertEquals(true, valid);
    }

    @Test(dataProvider = "InvalidHTMLTagProvider", dependsOnMethods = "ValidHTMLTagTest")
    public void InValidHTMLTagTest(final String tag) {
        boolean valid = this.htmlTagValidator.validate(tag);
        logger.info("HTMLTag is valid : " + tag + " , " + valid);
        Assert.assertEquals(false, valid);
    }
}
