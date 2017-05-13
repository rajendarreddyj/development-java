package com.rajendarreddyj.basics.regex.test;

import java.util.Vector;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rajendarreddyj.basics.regex.HTMLLinkExtrator;
import com.rajendarreddyj.basics.regex.HTMLLinkExtrator.HtmlLink;

public class HTMLLinkExtratorTest {
    private static final Logger logger = Logger.getAnonymousLogger();
    private HTMLLinkExtrator htmlLinkExtrator;

    @BeforeClass
    public void initData() {
        this.htmlLinkExtrator = new HTMLLinkExtrator();
    }

    @DataProvider
    public Object[][] HTMLContentProvider() {
        return new Object[][] { new Object[] { "abc hahaha <a href='http://www.google.com'>google</a>" },
                new Object[] { "abc hahaha <a HREF='http://www.google.com'>google</a>" },
                new Object[] {
                        "abc hahaha <A HREF='http://www.google.com'>google</A> , " + "abc hahaha <A HREF='http://www.google.com' target='_blank'>google</A>" },
                new Object[] { "abc hahaha <A HREF='http://www.google.com' target='_blank'>google</A>" },
                new Object[] { "abc hahaha <A target='_blank' HREF='http://www.google.com'>google</A>" },
                new Object[] { "abc hahaha <a HREF=http://www.google.com>google</a>" }, };
    }

    @Test(dataProvider = "HTMLContentProvider")
    public void ValidHTMLLinkTest(final String html) {
        Vector<HtmlLink> links = this.htmlLinkExtrator.grabHTMLLinks(html);
        Assert.assertTrue(links.size() != 0);
        for (int i = 0; i < links.size(); i++) {
            HtmlLink htmlLinks = links.get(i);
            logger.info(htmlLinks.toString());
        }
    }
}
