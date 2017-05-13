package com.rajendarreddyj.basics.swing.demo;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author rajendarreddy
 *
 */
public class JFramePopupMenu extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getAnonymousLogger();
    private JPanel jContentPane = null;
    private JButton jbnPopup = null;
    private JTextField jtfNumOfMenus = null;
    private JLabel lblNumElem = null;

    private XJPopupMenu scrollablePopupMenu = new XJPopupMenu(this);

    private JButton getBtnPopup() {

        if (this.jbnPopup == null) {
            this.jbnPopup = new JButton();
            this.jbnPopup.setText("View Scrollable popup menu ");
            int n = Integer.parseInt(this.getTxtNumElem().getText());

            for (int i = 0; i < n; i++) {
                XCheckedButton xx = new XCheckedButton(" JMenuItem  " + (i + 1));
                xx.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        logger.info(e.toString());
                        JFramePopupMenu.this.scrollablePopupMenu.hidemenu();
                    }

                });

                // Add Custom JSeperator after 2nd and 7th MenuItem.
                if ((i == 2) || (i == 7)) {
                    this.scrollablePopupMenu.addSeparator();
                }
                this.scrollablePopupMenu.add(xx);
            }

            this.jbnPopup.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(final MouseEvent e) {
                    Component source = (Component) e.getSource();
                    JFramePopupMenu.this.scrollablePopupMenu.show(source, e.getX(), e.getY());
                }
            });
        }

        return this.jbnPopup;
    }

    private JTextField getTxtNumElem() {
        if (this.jtfNumOfMenus == null) {
            this.jtfNumOfMenus = new JTextField();
            this.jtfNumOfMenus.setColumns(3);
            this.jtfNumOfMenus.setText("60");
        }
        return this.jtfNumOfMenus;
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFramePopupMenu thisClass = new JFramePopupMenu();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });
    }

    public JFramePopupMenu() {
        super();
        this.initialize();
    }

    private void initialize() {
        this.setSize(274, 109);
        this.setContentPane(this.getJContentPane());
        this.setTitle(" Scrollable JPopupMenu ");
    }

    private JPanel getJContentPane() {
        if (this.jContentPane == null) {
            this.lblNumElem = new JLabel();
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setHgap(8);
            flowLayout.setVgap(8);
            this.jContentPane = new JPanel();
            this.jContentPane.setLayout(flowLayout);
            this.jContentPane.add(this.getBtnPopup(), null);
            this.jContentPane.add(this.lblNumElem, null);
            this.jContentPane.add(this.getTxtNumElem(), null);
        }

        return this.jContentPane;
    }
}