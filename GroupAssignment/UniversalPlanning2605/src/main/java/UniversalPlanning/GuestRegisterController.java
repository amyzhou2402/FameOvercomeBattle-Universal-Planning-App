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
 * @author willi
 */
public class GuestRegisterController {
    
    PageSwitcher pSwitch = new PageSwitcher();
    Database dBase = new Database();
    
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private Button btnGetAccessCode;
    @FXML
    private Label lblAccessCode;
    @FXML
    private Button btnHomeMenu;
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
    private TextField txtAccessCode;
    @FXML
    private TextField editFirstName;
    @FXML
    private TextField editLastName;
    @FXML 
    private Button updateGuestDetails;
    @FXML 
    private Label guestUpdateMsg;
           
         
    
    @FXML
    private void handleGuestCreation(ActionEvent event) throws IOException{
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        
        String firstName = txtFirstName.getText().toUpperCase().trim().replaceAll("[-'<>?/~`!@#$%^&*():|[0-9]]*","");
        String lastName = txtLastName.getText().toUpperCase().trim().replaceAll("[-'<>?/~`!@#$%^&*():|[0-9]]*","");
        Integer accessCode = rand.nextInt(9999);
        
       String accessCodeString = firstName + lastName + accessCode.toString();
        
        try{
            dBase.createGuest(firstName, lastName, accessCodeString);
            lblAccessCode.setText(accessCodeString);
            lblAccessCode.setVisible(true);
        }
         
        catch(SQLException e){
            System.out.println(e.getMessage());
            lblAccessCode.setText("Error in creating access code!");
            lblAccessCode.setVisible(true);
        }
        finally{
            pSwitch.switcher(event,"AdminGuestRegisterScreen.fxml");
        }
        
    }
    
    @FXML
    private void updateGuestDetails(ActionEvent event) throws IOException{
        String accessCode = txtAccessCode.getText().toUpperCase();
        String firstName = editFirstName.getText();
        String lastName = editLastName.getText();
        
        try{
            dBase.updateGuest(firstName, lastName, accessCode);
            lblAccessCode.setText("Guest details Updated!");
            guestUpdateMsg.setVisible(true);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            lblAccessCode.setText("Error in updating guest details!");
            lblAccessCode.setVisible(true);
        }
        finally{
            pSwitch.switcher(event,"AdminGuestRegisterScreen.fxml");
        }
       
    }
    
    @FXML
    public void initialize() {
        
        accessCode.setCellValueFactory(
                cellData -> cellData.getValue().accessCode());
        firstName.setCellValueFactory(
                cellData -> cellData.getValue().firstName());
        lastName.setCellValueFactory(
                cellData -> cellData.getValue().lastName());
        
        guestTableView.setItems(this.getGuestListData());
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
    private void handleEventMenuButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event,"AdminEventsPage.fxml");
    }
    @FXML
    private void handleHomeMenuButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event,"AdminHomeScreen.fxml");
    }
    @FXML
    private void handleAboutMenuButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event,"AdminAboutPage.fxml");
    }
}
