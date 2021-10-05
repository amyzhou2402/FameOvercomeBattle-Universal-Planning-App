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
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author willi
 */
public class GuestHomeScreenController {
    
    PageSwitcher pSwitch = new PageSwitcher();
    Database dBase = new Database();
    
    @FXML
    private Button btnAboutMenu;
    @FXML
    private TextField txtGuestID;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtRsvpEventID;
    @FXML
    private RadioButton rsvpYes;
    @FXML
    private RadioButton rsvpNo;
    @FXML 
    private TextField txtDietaryRequirements;
    @FXML 
    private TextField updateEventID;
    @FXML 
    private TextField updateDietaryRequirements;
     @FXML
    private RadioButton rsvpUpdateYes;
    @FXML
    private RadioButton rsvpUpdateNo;
    @FXML
    private Button btnConfirm;
    @FXML
    private Label lblRsvpStatus;
    @FXML
    private TextField txtDecision;
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
    public void initialize() throws SQLException {
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
        String selectedText = txtGuestID.getText().toUpperCase();
        List<event> eventToReturn = new ArrayList<>();
        try {
            ResultSet rs = dBase.getResultSet("SELECT EVENTID, EVENTNAME, EVENTADDRESS, EVENTTIME FROM EVENT WHERE EVENTID = (SELECT EVENTID FROM INVITATION WHERE GUESTID =  '"+selectedText+"');");

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
    private void handleSearchButton(ActionEvent event) throws SQLException, IOException {
        initialize();
    }
    
    
    @FXML
    private void handleConfirmButton(ActionEvent event) throws SQLException, IOException{
        String guestID = txtGuestID.getText().toUpperCase();
        String eventID = txtRsvpEventID.getText();
        String decision = txtDecision.getText();
        String dietaryRequirements = txtDietaryRequirements.getText();
        
        Date date = new Date();
        String currDate = date.toString();
        
        try{
            dBase.updateRSVP(guestID, eventID, decision, dietaryRequirements, currDate);
            System.out.println("Lmao");
            lblRsvpStatus.setText("RSVP Has Been Updated!");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        
        
    }
    
    
    
    
    
    @FXML
    private void handleAboutMenuButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event,"GuestAboutPage.fxml");
    }
    

}
