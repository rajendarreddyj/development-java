package com.rajendarreddyj.basics.swing.demo;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author rajendarreddy
 *
 */
public class JTextAreaDemo extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    JTextField jtfInput;
    JTextArea jtAreaOutput;
    String newline = "\n";

    public JTextAreaDemo() {
        this.createGui();
    }

    public void createGui() {
        this.jtfInput = new JTextField(20);
        this.jtfInput.addActionListener(this);
        this.jtAreaOutput = new JTextArea(5, 20);
        this.jtAreaOutput.setCaretPosition(this.jtAreaOutput.getDocument().getLength());
        this.jtAreaOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(this.jtAreaOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        GridBagLayout gridBag = new GridBagLayout();
        Container contentPane = this.getContentPane();
        contentPane.setLayout(gridBag);
        GridBagConstraints gridCons1 = new GridBagConstraints();
        gridCons1.gridwidth = GridBagConstraints.REMAINDER;
        gridCons1.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(this.jtfInput, gridCons1);
        GridBagConstraints gridCons2 = new GridBagConstraints();
        gridCons2.weightx = 1.0;
        gridCons2.weighty = 1.0;
        contentPane.add(scrollPane, gridCons2);
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        String text = this.jtfInput.getText();
        this.jtAreaOutput.append(text + this.newline);
        this.jtfInput.selectAll();
    }

    public static void main(final String[] args) {
        JTextAreaDemo jtfTfDemo = new JTextAreaDemo();
        jtfTfDemo.pack();
        jtfTfDemo.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
        jtfTfDemo.setVisible(true);
    }
}