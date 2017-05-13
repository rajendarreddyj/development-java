package com.rajendarreddyj.basics.swing.demo.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author rajendarreddy
 *
 */
public class PersonDAO {
    private static final Logger logger = Logger.getAnonymousLogger();
    private ArrayList<PersonInfo> personsList;
    private String userid = "ra";
    private String password = "";
    static String url = "jdbc:hsqldb:mem:memoryDB";

    private Connection con;

    // constructor
    public PersonDAO() {
        this.personsList = new ArrayList<>();
        this.getConnection(); // Create Connection to the Oracle Database
        this.createTableIfNotExists(); // Person Table needs to be created in the Oracle Database.
    }

    public Connection getConnection() {

        try {
            Class.forName("org.hsqldb.jdbcDriver");

        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }

        try {
            this.con = DriverManager.getConnection(url, this.userid, this.password);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        return this.con;
    }

    public void createTableIfNotExists() {
        try {
            String sql = "create table if not exists Person (id Integer, name Varchar(30), address Varchar(30), phone Integer, email Varchar(50));";
            // Create a Prepared statement
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            logger.info(e.toString());
        }
    }

    public ArrayList<PersonInfo> searchPerson(final String name) {
        try {
            String sql = "SELECT * FROM Person WHERE name like '%" + name + "%'";

            // Create a prepared statement
            Statement s = this.con.createStatement();

            ResultSet rs = s.executeQuery(sql);

            String pname = "";
            String address = "";
            String email = "";
            int id, phone;

            while (rs.next()) {
                id = rs.getInt("id");
                pname = rs.getString("name");
                address = rs.getString("address");
                phone = rs.getInt("phone");
                email = rs.getString("email");

                // Create a PersonInfo object
                PersonInfo person = new PersonInfo(id, pname, address, phone, email);

                // Add the person object to array list
                this.personsList.add(person);
            }
        } catch (Exception e) {
            logger.info(e.toString());
        }

        return this.personsList;

    }

    public void savePerson(final PersonInfo person) {
        try {
            String sql = "INSERT INTO Person(name, address, " + "phone, email) VALUES (?,?,?,?) ";

            // Create a Preparedstatement
            PreparedStatement ps = this.con.prepareStatement(sql);

            ps.setString(1, person.getName());
            ps.setString(2, person.getAddress());
            ps.setInt(3, person.getPhone());
            ps.setString(4, person.getEmail());

            ps.executeUpdate();
        } catch (Exception e) {
            logger.info(e.toString());
        }
    }

    public void updatePerson(final PersonInfo person) {
        try {
            String sql = "UPDATE Person SET name = ?, address=? , " + "phone=? , email=? where id=?";

            // Create a Prepared statement
            PreparedStatement ps = this.con.prepareStatement(sql);

            ps.setString(1, person.getName());
            ps.setString(2, person.getAddress());
            ps.setInt(3, person.getPhone());
            ps.setString(4, person.getEmail());
            ps.setInt(5, person.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            logger.info(e.toString());
        }
    }

    public int removePerson(final String name) {
        int no = 0;
        try {
            String sql = "DELETE FROM Person WHERE name = ?";
            // Create a Prepared statement
            PreparedStatement ps = this.con.prepareStatement(sql);
            ps.setString(1, name);
            no = ps.executeUpdate();
        } catch (Exception e) {
            logger.info(e.toString());
        }
        return no;
    }

}
