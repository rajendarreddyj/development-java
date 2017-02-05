package com.rajendarreddyj.basics.pattern.factorydesign;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

/**
 * This "factory" class has a helper method for loading images more easily from the disk. It is an example of the
 * Factory Method design pattern.
 * 
 * @author rajendarreddy
 *
 */
public class ImageFactory {
    /**
     * Reads an image file from the hard disk and returns a corresponding Image object. The image is guaranteed to be
     * fully loaded upon return.
     * 
     * @param filename
     *            The path name of the file containing the image to load
     * @param panel
     *            the drawing panel on which the image will be displayed
     * @pre filename != null
     * @return the image loaded, or null if no image could be read
     */
    public static Image loadImage(final String filename, final DrawingPanel panel) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage(filename);
        // have to use media tracker to wait for image to actually load
        MediaTracker mt = new MediaTracker(panel);
        mt.addImage(img, 1);
        try {
            mt.waitForAll();
        } catch (InterruptedException e) {
        }
        return img;
    }
}
