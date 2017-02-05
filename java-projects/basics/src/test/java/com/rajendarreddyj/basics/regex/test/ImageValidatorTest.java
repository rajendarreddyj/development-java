package com.rajendarreddyj.basics.regex.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rajendarreddyj.basics.regex.ImageValidator;

public class ImageValidatorTest {
    private ImageValidator imageValidator;

    @BeforeClass
    public void initData() {
        this.imageValidator = new ImageValidator();
    }

    @DataProvider
    public Object[][] ValidImageProvider() {
        return new Object[][] { { new String[] { "a.jpg", "a.gif", "a.png", "a.bmp", "..jpg", "..gif", "..png", "..bmp", "a.JPG", "a.GIF", "a.PNG", "a.BMP",
                "a.JpG", "a.GiF", "a.PnG", "a.BmP", "jpg.jpg", "gif.gif", "png.png", "bmp.bmp" } } };
    }

    @DataProvider
    public Object[][] InvalidImageProvider() {
        return new Object[][] { { new String[] { ".jpg", ".gif", ".png", ".bmp", " .jpg", " .gif", " .png", " .bmp", "a.txt", "a.exe", "a.", "a.mp3", "jpg",
                "gif", "png", "bmp" } } };
    }

    @Test(dataProvider = "ValidImageProvider")
    public void ValidImageTest(final String[] Image) {
        for (String temp : Image) {
            boolean valid = this.imageValidator.validate(temp);
            System.out.println("Image is valid : " + temp + " , " + valid);
            Assert.assertEquals(true, valid);
        }
    }

    @Test(dataProvider = "InvalidImageProvider", dependsOnMethods = "ValidImageTest")
    public void InValidImageTest(final String[] Image) {
        for (String temp : Image) {
            boolean valid = this.imageValidator.validate(temp);
            System.out.println("Image is valid : " + temp + " , " + valid);
            Assert.assertEquals(false, valid);
        }
    }
}
