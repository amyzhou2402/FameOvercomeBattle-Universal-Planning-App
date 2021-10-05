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

/**
 *
 * @author josiah
 */
public class AdminEventsPageController {

    PageSwitcher pSwitch = new PageSwitcher();
    Database dBase = new Database();

    @FXML
    private TextField txtEventName;
    @FXML
    private TextField txtEventAddress;
    @FXML
    private TextField txtDateTime;
    @FXML
    private Button btnCreateButton;
    @FXML
    private TextField txtEventID;
    @FXML
    private TextField txtEditEventName;
    @FXML
    private TextField txtEditEventAddress;
    @FXML
    private TextField txtEditEventDateTime;
    @FXML
    private Button btnEditButton;
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
    private void handleEventCreation(ActionEvent event) throws SQLException, IOException {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        Integer eventID = rand.nextInt(99999);
        String stringEventID = eventID.toString();

        String eventName = txtEventName.getText();
        String eventAddress = txtEventAddress.getText();
        String eventDateTime = txtDateTime.getText();

        try {
            dBase.createEvent(stringEventID, eventName, eventAddress, eventDateTime);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            pSwitch.switcher(event, "AdminEventsPage.fxml");
        }

    }

    @FXML
    private void handleEventEditButton(ActionEvent event) throws SQLException, IOException {

        String eventID = txtEventID.getText();
        String eventName = txtEditEventName.getText();
        String eventAddress = txtEditEventAddress.getText();
        String eventDateTime = txtEditEventDateTime.getText();

        try {
            dBase.updateEvent(eventID, eventName, eventAddress, eventDateTime);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            pSwitch.switcher(event, "AdminEventsPage.fxml");
        }
    }

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
    private void handleHomeMenuButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event, "AdminHomeScreen.fxml");
    }

    @FXML
    private void handleAboutMenuButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event, "AdminAboutPage.fxml");
    }
    
     @FXML
    private void handleGuestMenuButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event,"AdminGuestRegisterScreen.fxml");
    }

}
