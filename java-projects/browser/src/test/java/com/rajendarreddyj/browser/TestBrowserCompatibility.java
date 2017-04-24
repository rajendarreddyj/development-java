package com.rajendarreddyj.browser;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class TestBrowserCompatibility {

    BrowserCompatibility bc = new BrowserCompatibility();

    @Test
    public void checkIE7WindowsVista() {
        Assert.assertEquals(true, this.bc.showOldBrowserPopup("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Trident/4.0)"));
    }

    @Test
    public void checkIE7BWindowsVista() {
        Assert.assertEquals(true, this.bc.showOldBrowserPopup("Mozilla/4.0(compatible; MSIE 7.0b; Windows NT 6.0)"));
    }

    @Test
    public void checkIE8Windows7() {
        Assert.assertEquals(true, this.bc.showOldBrowserPopup("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)"));
    }

    @Test
    public void checkIE8X64Windows7X64() {
        Assert.assertEquals(true, this.bc.showOldBrowserPopup("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Win64; x64; Trident/4.0)"));
    }

    @Test
    public void checkIE8X86Windows7X64() {
        Assert.assertEquals(true, this.bc.showOldBrowserPopup("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; WOW64; Trident/4.0)"));
    }

    @Test
    public void checkChrome58Linux() {
        Assert.assertEquals(false,
                this.bc.showOldBrowserPopup("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36"));
    }

    @Test
    public void checkFirefox40Windows7X64() {
        Assert.assertEquals(false, this.bc.showOldBrowserPopup("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1"));
    }

    @Test
    public void checkFirefox25Windows7X64() {
        Assert.assertEquals(false, this.bc.showOldBrowserPopup("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0"));
    }

    @Test
    public void checkFirefox24Windows7X64() {
        Assert.assertEquals(true, this.bc.showOldBrowserPopup("Mozilla/5.0 (Windows NT 6.0; WOW64; rv:24.0) Gecko/20100101 Firefox/24.0"));
    }
}
