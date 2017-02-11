package com.rajendarreddyj.basics.pattern.factorydesign;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;

/**
 * This class demonstrates the prototype pattern by using prototypes of fonts and colors. It also uses our ImageFactory
 * class that demonstrates the Factory Method pattern.
 * 
 * @author rajendarreddy
 *
 */
public class FontColorImageExample {
    /**
     * @param args
     */
    public static void main(final String[] args) {
        DrawingPanel panel = new DrawingPanel(600, 500);
        panel.setBorder(BorderFactory.createTitledBorder("Test"));
        Graphics g = panel.getGraphics();
        // deriving colors and fonts based on existing ones (prototypes)
        Color c = Color.GREEN.darker();
        g.setColor(c);
        g.fillOval(20, 70, 100, 50);
        Image img1 = ImageFactory.loadImage("src/main/resources/images/tinman.png", panel);
        Image img2 = ImageFactory.loadImage("src/main/resources/images/cuteicecream.jpg", panel);
        g.drawImage(img1, 100, 20, panel);
        g.drawImage(img2, 300, 50, 200, 300, panel);
        // derive new font, same as existing (prototype) font, but bigger and bold
        Font f = g.getFont(); // new Font("Comic Sans MS", Font.BOLD, 64);
        f = f.deriveFont(Font.BOLD, 64);
        g.setFont(f);
        g.drawString("Testing", 11, 200);
    }
}