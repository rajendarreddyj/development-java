package com.rajendarreddyj.basics.swing.demo.addressbook;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author rajendarreddy
 *
 */
public class AddressBookDemo implements ActionListener {

    ArrayList<PersonInfo> personsList;
    PersonDAO pDAO;
    JFrame appFrame;
    JLabel jlbName, jlbAddress, jlbPhone, jlbEmail;
    JTextField jtfName, jtfAddress, jtfPhone, jtfEmail;
    JButton jbbSave, jbnDelete, jbnClear, jbnUpdate, jbnSearch, jbnForward, jbnBack, jbnExit;
    String name, address, email;
    int phone;
    int recordNumber; // used to naviagate using >> and buttons
    Container cPane;

    public static void main(final String args[]) {
        new AddressBookDemo();
    }

    public AddressBookDemo() {
        this.name = "";
        this.address = "";
        this.email = "";
        this.phone = -1; // Stores 0 to indicate no Phone Number
        this.recordNumber = -1;
        this.createGUI();
        this.personsList = new ArrayList<>();
        // creating PersonDAO object
        this.pDAO = new PersonDAO();
    }

    public void createGUI() {

        /*Create a frame, get its contentpane and set layout*/
        this.appFrame = new JFrame("Address Book");
        this.cPane = this.appFrame.getContentPane();
        this.cPane.setLayout(new GridBagLayout());
        // Arrange components on contentPane and set Action Listeners to each JButton

        this.arrangeComponents();
        this.appFrame.setSize(240, 300);
        this.appFrame.setResizable(false);
        this.appFrame.setVisible(true);

    }

    public void arrangeComponents() {
        this.jlbName = new JLabel("Name");
        this.jlbAddress = new JLabel("Address");
        this.jlbPhone = new JLabel("Phone");
        this.jlbEmail = new JLabel("Email");
        this.jtfName = new JTextField(20);
        this.jtfAddress = new JTextField(20);
        this.jtfPhone = new JTextField(20);
        this.jtfEmail = new JTextField(20);
        this.jbbSave = new JButton("Save");
        this.jbnDelete = new JButton("Delete");
        this.jbnClear = new JButton("Clear");
        this.jbnUpdate = new JButton("Update");
        this.jbnSearch = new JButton("Search");
        this.jbnForward = new JButton(">>");
        this.jbnBack = new JButton("<<");
        this.jbnExit = new JButton("Exit");
        /*add all initialized components to the container*/
        GridBagConstraints gridBagConstraintsx01 = new GridBagConstraints();
        gridBagConstraintsx01.gridx = 0;
        gridBagConstraintsx01.gridy = 0;
        gridBagConstraintsx01.insets = new Insets(5, 5, 5, 5);
        this.cPane.add(this.jlbName, gridBagConstraintsx01);
        GridBagConstraints gridBagConstraintsx02 = new GridBagConstraints();
        gridBagConstraintsx02.gridx = 1;
        gridBagConstraintsx02.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx02.gridy = 0;
        gridBagConstraintsx02.gridwidth = 2;
        gridBagConstraintsx02.fill = GridBagConstraints.BOTH;
        this.cPane.add(this.jtfName, gridBagConstraintsx02);
        GridBagConstraints gridBagConstraintsx03 = new GridBagConstraints();
        gridBagConstraintsx03.gridx = 0;
        gridBagConstraintsx03.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx03.gridy = 1;
        this.cPane.add(this.jlbAddress, gridBagConstraintsx03);
        GridBagConstraints gridBagConstraintsx04 = new GridBagConstraints();
        gridBagConstraintsx04.gridx = 1;
        gridBagConstraintsx04.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx04.gridy = 1;
        gridBagConstraintsx04.gridwidth = 2;
        gridBagConstraintsx04.fill = GridBagConstraints.BOTH;
        this.cPane.add(this.jtfAddress, gridBagConstraintsx04);
        GridBagConstraints gridBagConstraintsx05 = new GridBagConstraints();
        gridBagConstraintsx05.gridx = 0;
        gridBagConstraintsx05.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx05.gridy = 2;
        this.cPane.add(this.jlbPhone, gridBagConstraintsx05);
        GridBagConstraints gridBagConstraintsx06 = new GridBagConstraints();
        gridBagConstraintsx06.gridx = 1;
        gridBagConstraintsx06.gridy = 2;
        gridBagConstraintsx06.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx06.gridwidth = 2;
        gridBagConstraintsx06.fill = GridBagConstraints.BOTH;
        this.cPane.add(this.jtfPhone, gridBagConstraintsx06);
        GridBagConstraints gridBagConstraintsx07 = new GridBagConstraints();
        gridBagConstraintsx07.gridx = 0;
        gridBagConstraintsx07.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx07.gridy = 3;
        this.cPane.add(this.jlbEmail, gridBagConstraintsx07);
        GridBagConstraints gridBagConstraintsx08 = new GridBagConstraints();
        gridBagConstraintsx08.gridx = 1;
        gridBagConstraintsx08.gridy = 3;
        gridBagConstraintsx08.gridwidth = 2;
        gridBagConstraintsx08.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx08.fill = GridBagConstraints.BOTH;
        this.cPane.add(this.jtfEmail, gridBagConstraintsx08);
        GridBagConstraints gridBagConstraintsx09 = new GridBagConstraints();
        gridBagConstraintsx09.gridx = 0;
        gridBagConstraintsx09.gridy = 4;
        gridBagConstraintsx09.insets = new Insets(5, 5, 5, 5);
        this.cPane.add(this.jbbSave, gridBagConstraintsx09);
        GridBagConstraints gridBagConstraintsx10 = new GridBagConstraints();
        gridBagConstraintsx10.gridx = 1;
        gridBagConstraintsx10.gridy = 4;
        gridBagConstraintsx10.insets = new Insets(5, 5, 5, 5);
        this.cPane.add(this.jbnDelete, gridBagConstraintsx10);
        GridBagConstraints gridBagConstraintsx11 = new GridBagConstraints();
        gridBagConstraintsx11.gridx = 2;
        gridBagConstraintsx11.gridy = 4;
        gridBagConstraintsx11.insets = new Insets(5, 5, 5, 5);
        this.cPane.add(this.jbnUpdate, gridBagConstraintsx11);
        GridBagConstraints gridBagConstraintsx12 = new GridBagConstraints();
        gridBagConstraintsx12.gridx = 0;
        gridBagConstraintsx12.gridy = 5;
        gridBagConstraintsx12.insets = new Insets(5, 5, 5, 5);
        this.cPane.add(this.jbnBack, gridBagConstraintsx12);
        GridBagConstraints gridBagConstraintsx13 = new GridBagConstraints();
        gridBagConstraintsx13.gridx = 1;
        gridBagConstraintsx13.gridy = 5;
        gridBagConstraintsx13.insets = new Insets(5, 5, 5, 5);
        this.cPane.add(this.jbnSearch, gridBagConstraintsx13);
        GridBagConstraints gridBagConstraintsx14 = new GridBagConstraints();
        gridBagConstraintsx14.gridx = 2;
        gridBagConstraintsx14.gridy = 5;
        gridBagConstraintsx14.insets = new Insets(5, 5, 5, 5);
        this.cPane.add(this.jbnForward, gridBagConstraintsx14);
        GridBagConstraints gridBagConstraintsx15 = new GridBagConstraints();
        gridBagConstraintsx15.gridx = 1;
        gridBagConstraintsx15.insets = new Insets(5, 5, 5, 5);
        gridBagConstraintsx15.gridy = 6;
        this.cPane.add(this.jbnClear, gridBagConstraintsx15);
        GridBagConstraints gridBagConstraintsx16 = new GridBagConstraints();
        gridBagConstraintsx16.gridx = 2;
        gridBagConstraintsx16.gridy = 6;
        gridBagConstraintsx16.insets = new Insets(5, 5, 5, 5);
        this.cPane.add(this.jbnExit, gridBagConstraintsx16);
        this.jbbSave.addActionListener(this);
        this.jbnDelete.addActionListener(this);
        this.jbnClear.addActionListener(this);
        this.jbnUpdate.addActionListener(this);
        this.jbnSearch.addActionListener(this);
        this.jbnForward.addActionListener(this);
        this.jbnBack.addActionListener(this);
        this.jbnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.jbbSave) {
            this.savePerson();
            this.clear();
        } else if (e.getSource() == this.jbnDelete) {
            this.deletePerson();
            this.clear();
        } else if (e.getSource() == this.jbnUpdate) {
            this.updatePerson();
            this.clear();
        } else if (e.getSource() == this.jbnSearch) {
            this.searchPerson();
        } else if (e.getSource() == this.jbnForward) {
            this.displayNextRecord();
        } else if (e.getSource() == this.jbnBack) {
            this.displayPreviousRecord();
        } else if (e.getSource() == this.jbnClear) {
            this.clear();
        } else if (e.getSource() == this.jbnExit) {
            System.exit(0);
        }
    }

    // Save the Person into the Address Book
    public void savePerson() {
        this.name = this.jtfName.getText();
        this.name = this.name.toUpperCase(); // Save all names in Uppercase
        this.address = this.jtfAddress.getText();
        try {
            this.phone = Integer.parseInt("" + this.jtfPhone.getText());
        } catch (Exception e) {
            /*System.out.print("Input is a string");
            
             JOptionPane.showMessageDialog(null, "Please enter Phone Number");*/
        }
        this.email = this.jtfEmail.getText();
        if (this.name.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter person name.");
        } else {
            // create a PersonInfo object and pass it to PersonDAO to save it
            PersonInfo person = new PersonInfo(this.name, this.address, this.phone, this.email);
            this.pDAO.savePerson(person);
            JOptionPane.showMessageDialog(null, "Person Saved");
        }
    }

    public void deletePerson() {
        this.name = this.jtfName.getText();
        this.name = this.name.toUpperCase();
        if (this.name.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter person name to delete.");
        } else {
            // remove Person of the given name from the Address Book database
            int numberOfDeleted = this.pDAO.removePerson(this.name);
            JOptionPane.showMessageDialog(null, numberOfDeleted + " Record(s) deleted.");
        }
    }

    public void updatePerson() {
        if ((this.recordNumber >= 0) && (this.recordNumber < this.personsList.size())) {
            PersonInfo person = this.personsList.get(this.recordNumber);
            int id = person.getId();
            /*get values from text fields*/
            this.name = this.jtfName.getText();
            this.address = this.jtfAddress.getText();
            this.phone = Integer.parseInt(this.jtfPhone.getText());
            this.email = this.jtfEmail.getText();
            /*update data of the given person name*/
            person = new PersonInfo(id, this.name, this.address, this.phone, this.email);
            this.pDAO.updatePerson(person);
            JOptionPane.showMessageDialog(null, "Person info record updated successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No record to Update");
        }
    }

    // Perform a Case-Insensitive Search to find the Person
    public void searchPerson() {
        this.name = this.jtfName.getText();
        this.name = this.name.toUpperCase();
        /*clear contents of arraylist if there are any from previous search*/
        this.personsList.clear();
        this.recordNumber = 0;
        if (this.name.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter person name to search.");
        } else {
            /*get an array list of searched persons using PersonDAO*/
            this.personsList = this.pDAO.searchPerson(this.name);
            if (this.personsList.size() == 0) {
                JOptionPane.showMessageDialog(null, "No records found.");
                // Perform a clear if no records are found.
                this.clear();
            } else {
                /*downcast the object from array list to PersonInfo*/
                PersonInfo person = this.personsList.get(this.recordNumber);
                // displaying search record in text fields
                this.jtfName.setText(person.getName());
                this.jtfAddress.setText(person.getAddress());
                this.jtfPhone.setText("" + person.getPhone());
                this.jtfEmail.setText(person.getEmail());
            }
        }
    }

    public void displayNextRecord() {
        // inc in recordNumber to display next person info, already stored in
        // personsList during search
        this.recordNumber++;
        if (this.recordNumber >= this.personsList.size()) {
            JOptionPane.showMessageDialog(null, "You have reached end of " + "search results");
            /*if user has reached the end of results, disable forward button*/
            this.jbnForward.setEnabled(false);
            this.jbnBack.setEnabled(true);
            // dec by one to counter last inc
            this.recordNumber--;
        } else {
            this.jbnBack.setEnabled(true);
            PersonInfo person = this.personsList.get(this.recordNumber);
            // displaying search record in text fields
            this.jtfName.setText(person.getName());
            this.jtfAddress.setText(person.getAddress());
            this.jtfPhone.setText("" + person.getPhone());
            this.jtfEmail.setText(person.getEmail());
        }
    }

    public void displayPreviousRecord() {
        // dec in recordNumber to display previous person info, already
        // stored in personsList during search
        this.recordNumber--;
        if (this.recordNumber < 0) {
            JOptionPane.showMessageDialog(null, "You have reached begining " + "of search results");
            /*if user has reached the begining of results, disable back button*/
            this.jbnForward.setEnabled(true);
            this.jbnBack.setEnabled(false);
            // inc by one to counter last dec
            this.recordNumber++;
        } else {
            this.jbnForward.setEnabled(true);
            PersonInfo person = this.personsList.get(this.recordNumber);
            // displaying search record in text fields
            this.jtfName.setText(person.getName());
            this.jtfAddress.setText(person.getAddress());
            this.jtfPhone.setText("" + person.getPhone());
            this.jtfEmail.setText(person.getEmail());
        }
    }

    public void clear() {
        this.jtfName.setText("");
        this.jtfAddress.setText("");
        this.jtfPhone.setText("");
        this.jtfEmail.setText("");
        /*clear contents of arraylist*/
        this.recordNumber = -1;
        this.personsList.clear();
        this.jbnForward.setEnabled(true);
        this.jbnBack.setEnabled(true);
    }
}
