/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniversalPlanning;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author willi
 */
public class Database {

    public static Connection conn;
    

    public static void openConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:universalPlanning.db");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void createEvent(String eventID, String eventName, String eventAddress, String eventDateTime) throws SQLException{
        openConnection();
        
        PreparedStatement createEvent = conn.prepareStatement("INSERT INTO event (EVENTID, EVENTNAME, EVENTADDRESS, EVENTTIME) "
                + "VALUES ('"+eventID+"', '"+eventName+"',"
                + " '"+eventAddress+"', '"+eventDateTime+"');");
        createEvent.execute();
        System.out.println("New event "+ eventID + "has been created.");
        
    }
    
    public static void updateEvent(String eventID, String eventName, String eventAddress, String eventDateTime) throws SQLException{
        openConnection();
        
        PreparedStatement updateEvent = conn.prepareStatement("UPDATE event SET EVENTNAME = '"+eventName+"', EVENTADDRESS = '"+eventAddress+"', EVENTTIME = '"+eventDateTime+"' WHERE EVENTID = '"+eventID+"';");
        updateEvent.execute();
       
    }
    
    public static void createGuest(String firstName, String lastName, String accessCode) throws SQLException{
        openConnection();
      
        
        PreparedStatement insertData = conn.prepareStatement("INSERT INTO guestLogin (ACCESSCODE, FIRSTNAME, LASTNAME) "
                 + "VALUES ('"+accessCode+"', '"+firstName+"', '"+lastName+"');");
        insertData.execute();
     
    }
    
    public static void updateGuest (String firstName, String lastName, String accessCodeSelect) throws SQLException{
        openConnection();
        
        PreparedStatement updateGuest = conn.prepareStatement("UPDATE guestLogin SET FIRSTNAME = '"+firstName+"', "
                + "LASTNAME = '"+lastName+"' "
                + "WHERE ACCESSCODE = '"+accessCodeSelect+"';");
        updateGuest.execute();
    }
    
    public static void guestInvitation(String invitationID, String eventID, String guestID, String adminID) throws SQLException{
        openConnection();
        PreparedStatement invitation = conn.prepareStatement("INSERT INTO invitation (INVITATIONID, EVENTID, GUESTID, ADMINID) VALUES('"+invitationID+"', '"+eventID+"', '"+guestID+"', '"+adminID+"');");
        invitation.execute();
    }
    
    public static void updateRSVP(String guestID, String eventID, String Decision, String DietaryRequirements, String currDateTime) throws SQLException{
        openConnection();
        PreparedStatement updateRSVP = conn.prepareStatement("UPDATE invitation SET RSVPDECISION = '"+Decision+"', "
                + "DIETARYREQUIREMENTS = '"+DietaryRequirements+"',"
                + " RSVPDATETIME = '"+currDateTime+"' "
                + "WHERE invitation.GUESTID = '"+guestID+"';");
        updateRSVP.execute();
    }

    public ResultSet getResultSet(String sqlstatement) throws SQLException {
        openConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sqlstatement);
        return rs;
    }

    public static void createLoginTable() {

        PreparedStatement createLoginTable = null;
        PreparedStatement createGuest = null;
        PreparedStatement createEvent = null;
        PreparedStatement createInvitation = null;
        PreparedStatement createRsvp = null;
        PreparedStatement insertData = null;
        ResultSet rs = null;

        try {
            openConnection();
            System.out.println("Checking Admin Login table");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "adminLogin", null);
            if (!rs.next()) {
                createLoginTable = conn.prepareStatement("CREATE TABLE adminLogin("
                        + "USERNAME VARCHAR (50) PRIMARY KEY,"
                        + " PASSWORD VARCHAR(50));");
                createLoginTable.execute();
                insertData = conn.prepareStatement("INSERT INTO adminLOGIN (USERNAME, PASSWORD) "
                        + "VALUES ('admin','password');");
                insertData.execute();
                System.out.println("Creating Admin Login Table");
            } else {
                System.out.println("Admin Login table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
 
        try {
            System.out.println("Checking Guest Login table");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "guestLogin", null);
            if (!rs.next()) {
                createGuest = conn.prepareStatement("CREATE TABLE guestLogin("
                        + "ACCESSCODE VARCHAR (50) PRIMARY KEY,"
                        + " FIRSTNAME VARCHAR(20),"
                        + " LASTNAME VARCHAR(20));");
                createGuest.execute();
                System.out.println("Creating Guest Login Table");
            } else {
                System.out.println("Guest Login table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            System.out.println("Checking Event Table");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "event", null);
            if (!rs.next()) {
                createEvent = conn.prepareStatement("CREATE TABLE event("
                        + "EVENTID VARCHAR (5) PRIMARY KEY,"
                        + " EVENTNAME VARCHAR (50),"
                        + " EVENTADDRESS VARCHAR(50),"
                        + " EVENTTIME DATETIME);");
                createEvent.execute();
                System.out.println("Creating Event  Table");
            } else {
                System.out.println("Event table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         try {
            System.out.println("Checking Invitation Table");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "invitation", null);
            if (!rs.next()) {
                createInvitation = conn.prepareStatement("CREATE TABLE invitation("
                        + "INVITATIONID VARCHAR (5) PRIMARY KEY, "
                        + "EVENTID VARCHAR(5) not null REFERENCES event(EVENTID), "
                        + "GUESTID VARCHAR(50) not null REFERENCES guestLogin(ACCESSCODE), "
                        + "ADMINID VARCHAR(50) REFERENCES adminLogin(USERNAME), "
                        + "RSVPDECISION char(1), "
                        + "DIETARYREQUIREMENTS varchar(50), "
                        + "RSVPDATETIME varchar(50));");
                createInvitation.execute();
                System.out.println("Creating Invitation Table");
            } else {
                System.out.println("Invitation table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         
         try {
            System.out.println("Checking rsvp Table");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "rsvp", null);
            if (!rs.next()) {
                createRsvp = conn.prepareStatement("CREATE TABLE rsvp("
                        + "RSVPID VARCHAR (5) PRIMARY KEY, "
                        + "INVITATIONID VARCHAR(5) not null REFERENCES invitation(INVITATIONID), "
                        + "DECISION char(1) not null, "
                        + "DIETARYREQUIREMENTS VARCHAR(50), "
                        + "RSVPDATETIME DATETIME);");
                createRsvp.execute();
                System.out.println("Creating rsvp Table");
            } else {
                System.out.println("rsvp exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public boolean tryAdminLogin(String username, String password) {

        boolean loginSuccessful = false;

        try {
            openConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * "
                    + "FROM adminLogin WHERE USERNAME = ? AND PASSWORD = ?;");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                loginSuccessful = true;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginSuccessful;
    }
    public boolean tryGuestLogin(String accessCode) {

        boolean loginSuccessful = false;

        try {
            openConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * "
                    + "FROM guestLogin WHERE ACCESSCODE = ?;");
            ps.setString(1, accessCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                loginSuccessful = true;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loginSuccessful;
    }    

}
