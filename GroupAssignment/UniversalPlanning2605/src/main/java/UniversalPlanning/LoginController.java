/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniversalPlanning;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author willi
 */
public class LoginController {

    PageSwitcher pSwitch = new PageSwitcher();
    Database dBase = new Database();

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnAdminLogin;
    @FXML
    private TextField txtAccessCode;
    @FXML
    private Button btnGuestLogin;
    @FXML
    private Label lblAdminLoginMessage;
    @FXML
    private Label lblAccessCodeMessage;



    @FXML
    public void initialize() {
        System.out.println("Loading Login...");
        lblAdminLoginMessage.setVisible(false);
        lblAccessCodeMessage.setVisible(false);
    }


    
    @FXML
    private void handleAdminLoginButtonAction(ActionEvent event) throws IOException {
        
        String user = txtUsername.getText().trim();
        String pass = txtPassword.getText();
        
        if(dBase.tryAdminLogin(user,pass)){
            pSwitch.switcher(event, "AdminHomeScreen.fxml");
        }else{
            lblAdminLoginMessage.setText("Incorrect Username/Password!");
            lblAdminLoginMessage.setVisible(true);
            
        }
    }

    @FXML
    private void handleGuestLoginButtonAction(ActionEvent event) throws IOException {
        
        String accessCodeInput = txtAccessCode.getText().trim().toUpperCase();
        
        if(dBase.tryGuestLogin(accessCodeInput)){
            pSwitch.switcher(event, "GuestHomeScreen.fxml");
            lblAccessCodeMessage.setText("Login Successful");
            lblAccessCodeMessage.setVisible(true);
        }else{
            lblAccessCodeMessage.setText("Incorrect Access Code!");
            lblAccessCodeMessage.setVisible(true);
            
        }
    }

}   
