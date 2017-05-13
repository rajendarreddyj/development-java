package com.rajendarreddyj.basics.swing.demo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;

/**
 * This class implements a scrollable Popup Menu
 * 
 * @author rajendarreddy
 *
 */
public class XJPopupMenu extends JPopupMenu implements ActionListener {
    private static final long serialVersionUID = 1;
    private static final Logger logger = Logger.getAnonymousLogger();
    private JPanel panelMenus = new JPanel();
    private JScrollPane scroll = null;
    private JFrame jframe = null;
    public static final Icon EMPTY_IMAGE_ICON = new ImageIcon("src/main/resources/images/menu_spacer.gif");

    public XJPopupMenu(final JFrame jframe) {
        super();
        this.jframe = jframe;
        this.setLayout(new BorderLayout());
        this.panelMenus.setLayout(new GridLayout(0, 1));
        this.panelMenus.setBackground(UIManager.getColor("MenuItem.background"));
        // panelMenus.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        this.init(jframe);

    }

    private void init(final JFrame jframe) {
        super.removeAll();
        this.scroll = new JScrollPane();
        this.scroll.setViewportView(this.panelMenus);
        this.scroll.setBorder(null);
        this.scroll.setMinimumSize(new Dimension(240, 40));

        this.scroll.setMaximumSize(new Dimension(this.scroll.getMaximumSize().width,
                this.getToolkit().getScreenSize().height - this.getToolkit().getScreenInsets(jframe.getGraphicsConfiguration()).top
                        - this.getToolkit().getScreenInsets(jframe.getGraphicsConfiguration()).bottom - 4));
        super.add(this.scroll, BorderLayout.CENTER);
        // super.add(scroll);
    }

    @Override
    public void show(final Component invoker, final int x, final int y) {
        this.init(this.jframe);
        // this.pack();
        this.panelMenus.validate();
        int maxsize = this.scroll.getMaximumSize().height;
        int realsize = this.panelMenus.getPreferredSize().height;

        int sizescroll = 0;

        if (maxsize < realsize) {
            sizescroll = this.scroll.getVerticalScrollBar().getPreferredSize().width;
        }
        this.scroll.setPreferredSize(new Dimension(this.scroll.getPreferredSize().width + sizescroll + 20, this.scroll.getPreferredSize().height));
        this.pack();
        this.setInvoker(invoker);
        if (sizescroll != 0) {
            // Set popup size only if scrollbar is visible
            this.setPopupSize(new Dimension(this.scroll.getPreferredSize().width + 20, this.scroll.getMaximumSize().height - 20));
        }
        // this.setMaximumSize(scroll.getMaximumSize());
        Point invokerOrigin = invoker.getLocationOnScreen();
        this.setLocation((int) invokerOrigin.getX() + x, (int) invokerOrigin.getY() + y);
        this.setVisible(true);
    }

    public void hidemenu() {
        if (this.isVisible()) {
            this.setVisible(false);
        }
    }

    public void add(final AbstractButton menuItem) {
        // menuItem.setMargin(new Insets(0, 20, 0 , 0));
        if (menuItem == null) {
            return;
        }
        this.panelMenus.add(menuItem);
        menuItem.removeActionListener(this);
        menuItem.addActionListener(this);
        if (menuItem.getIcon() == null) {
            menuItem.setIcon(EMPTY_IMAGE_ICON);
        }
        if (!(menuItem instanceof XCheckedButton)) {
            logger.info(menuItem.getName());
        }
    }

    @Override
    public void addSeparator() {
        this.panelMenus.add(new XSeperator());
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        this.hidemenu();
    }

    @Override
    public Component[] getComponents() {
        return this.panelMenus.getComponents();
    }

    private static class XSeperator extends JSeparator {
        private static final long serialVersionUID = 1L;

        XSeperator() {
            ComponentUI ui = XBasicSeparatorUI.createUI(this);
            XSeperator.this.setUI(ui);
        }

        private static class XBasicSeparatorUI extends BasicSeparatorUI {

            public static ComponentUI createUI(final JComponent c) {
                return new XBasicSeparatorUI();
            }

            @Override
            public void paint(final Graphics g, final JComponent c) {
                Dimension s = c.getSize();

                if (((JSeparator) c).getOrientation() == JSeparator.VERTICAL) {
                    g.setColor(c.getForeground());
                    g.drawLine(0, 0, 0, s.height);

                    g.setColor(c.getBackground());
                    g.drawLine(1, 0, 1, s.height);
                } else // HORIZONTAL
                {
                    g.setColor(c.getForeground());
                    g.drawLine(0, 7, s.width, 7);

                    g.setColor(c.getBackground());
                    g.drawLine(0, 8, s.width, 8);
                }
            }
        }
    }

}
