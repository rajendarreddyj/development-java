package com.rajendarreddyj.basics.swing.demo;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author rajendarreddy
 *
 */
public class JListDemo extends JFrame {

    private static final long serialVersionUID = 1L;
    JList<String> list;
    String[] listColorNames = { "black", "blue", "green", "yellow", "white" };
    Color[] listColorValues = { Color.BLACK, Color.BLUE, Color.GREEN, Color.YELLOW, Color.WHITE };
    Container contentpane;

    public JListDemo() {
        super("List Source Demo");
        this.contentpane = this.getContentPane();
        this.contentpane.setLayout(new FlowLayout());
        this.list = new JList<>(this.listColorNames);
        this.list.setSelectedIndex(0);
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.contentpane.add(new JScrollPane(this.list));
        this.list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(final ListSelectionEvent e) {
                JListDemo.this.contentpane.setBackground(JListDemo.this.listColorValues[JListDemo.this.list.getSelectedIndex()]);
            }
        });
        this.setSize(200, 200);
        this.setVisible(true);
    }

    public static void main(final String[] args) {
        JListDemo test = new JListDemo();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}