package com.rajendarreddyj.basics.pattern.observable.rps.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

/**
 * A layout manager that "cascades" components separated by 20px in each dimension. Not particularly useful, but an
 * illustration of implementing a custom layout manager strategy.
 * 
 * @author rajendarreddy
 */
public class CascadingLayout implements LayoutManager {
    @Override
    public void addLayoutComponent(final String arg0, final Component arg1) {
    }

    /**
     * Lays out the components in the given container in a cascading style from top-left.
     */
    @Override
    public void layoutContainer(final Container container) {
        int x = 0;
        int y = 0;
        for (Component comp : container.getComponents()) {
            // position/size the component
            comp.setSize(comp.getPreferredSize());
            comp.setLocation(x, y);
            x += 40;
            y += 40;
        }
    }

    @Override
    public Dimension minimumLayoutSize(final Container arg0) {
        return null;
    }

    @Override
    public Dimension preferredLayoutSize(final Container arg0) {
        return null;
    }

    @Override
    public void removeLayoutComponent(final Component arg0) {
    }
}
