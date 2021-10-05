/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniversalPlanning;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Random;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author willi
 */
public class AdminHomeScreenController {
    
    PageSwitcher pSwitch = new PageSwitcher();
    Database dBase = new Database();
    
    @FXML
    private Button btnGuestMenu;
    @FXML
    private Button btnEventMenu;
    @FXML
    private Button btnAboutMenu;
    @FXML
    private TableView<guestLogin> guestTableView;
    @FXML
    private TableColumn<guestLogin, String> accessCode;
    @FXML
    private TableColumn<guestLogin, String> firstName;
    @FXML
    private TableColumn<guestLogin, String> lastName;
    @FXML
    private TableView<event> eventTableView;
    @FXML
    private TableColumn<event, String> eventID;
    @FXML
    private TableColumn<event, String> eventName;
    @FXML
    private TableColumn<event, String> eventAddress;
    @FXML
    private TableColumn<event, String> eventDateTime;
    @FXML
    private TableView<invitation> invitationTableView;
    @FXML
    private TableColumn<invitation, String> invitationID;
    @FXML
    private TableColumn<invitation, String> invitationEventID;
    @FXML
    private TableColumn<invitation, String> invitationGuestID;
    @FXML
    private TableColumn<invitation, String> invitationAdminID;
    @FXML
    private TextField txtInvitationEventID;
    @FXML
    private TextField txtInvitationGuestID;
    @FXML
    private TextField txtInvitationAdminID;
    @FXML
    private Button BtnSubmitInvitation;
    @FXML
    private TextField txtPieChartEventID;
    @FXML
    private Button btnPieChartSubmit;
    
 
    
    @FXML
    private void handleInvitationCreation(ActionEvent event) throws SQLException, IOException{
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        Integer invitationID = rand.nextInt(99999);
        String stringInvitationID = invitationID.toString();
        
        String eventID = txtInvitationEventID.getText();
        String guestID = txtInvitationGuestID.getText().toUpperCase();
        String adminID = txtInvitationAdminID.getText();
        
        try{
        dBase.guestInvitation(stringInvitationID, eventID, guestID, adminID);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            pSwitch.switcher(event, "AdminHomeScreen.fxml");
        }
        
    }
    
    
    @FXML
    public void initialize() throws SQLException{
        
        accessCode.setCellValueFactory(
                cellData -> cellData.getValue().accessCode());
        firstName.setCellValueFactory(
                cellData -> cellData.getValue().firstName());
        lastName.setCellValueFactory(
                cellData -> cellData.getValue().lastName());
        
        guestTableView.setItems(this.getGuestListData());
        initializeInvitation();
        initializeEvents(); 
    }

        private ObservableList<guestLogin> getGuestListData() {
        List<guestLogin> guestToReturn = new ArrayList<>();
        try {
                ResultSet rs = dBase.getResultSet("SELECT * FROM guestLogin;");
            
            while (rs.next()) {
                guestToReturn.add(
                        new guestLogin(rs.getString(1),
                                rs.getString(2),
                                rs.getString(3))
                // create a new music object
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(guestToReturn);
    }
    
    @FXML
    public void initializeEvents() throws SQLException {
        eventID.setCellValueFactory(
                cellData -> cellData.getValue().getEventID());
        eventName.setCellValueFactory(
                cellData -> cellData.getValue().getEventName());
        eventAddress.setCellValueFactory(
                cellData -> cellData.getValue().getEventAddress());
        eventDateTime.setCellValueFactory(
                cellData -> cellData.getValue().getEventDateTime());

        eventTableView.setItems(this.getEventListData());
    }

    private ObservableList<event> getEventListData() throws SQLException {
        List<event> eventToReturn = new ArrayList<>();
        try {
            ResultSet rs = dBase.getResultSet("SELECT * FROM event;");

            while (rs.next()) {
                eventToReturn.add(
                        new event(rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4)));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(eventToReturn);
    }
    
    @FXML
    public void initializeInvitation() throws SQLException{
        invitationID.setCellValueFactory(
                cellData -> cellData.getValue().getInvitationID());
        invitationEventID.setCellValueFactory(
                cellData -> cellData.getValue().getEventID());
        invitationGuestID.setCellValueFactory(
                cellData -> cellData.getValue().getGuestID());
        invitationAdminID.setCellValueFactory(
                cellData -> cellData.getValue().getAdminID());
        
        invitationTableView.setItems(this.getInvitationListData());
        
    }
     private ObservableList<invitation> getInvitationListData() throws SQLException {
        List<invitation> invitationToReturn = new ArrayList<>();
        try {
            ResultSet rs = dBase.getResultSet("SELECT INVITATIONID, EVENTID, GUESTID, ADMINID FROM INVITATION;");

            while (rs.next()) {
                invitationToReturn.add(
                        new invitation(rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4)));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList(invitationToReturn);
    }
    
    
   
    
    

    
    
    @FXML
    private void handleGuestMenuButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event,"AdminGuestRegisterScreen.fxml");
    }
    
    @FXML
    private void handleEventMenuButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event,"AdminEventsPage.fxml");
    }
    
    @FXML
    private void handleAboutMenuButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event,"AdminAboutPage.fxml");
    }
    
}
