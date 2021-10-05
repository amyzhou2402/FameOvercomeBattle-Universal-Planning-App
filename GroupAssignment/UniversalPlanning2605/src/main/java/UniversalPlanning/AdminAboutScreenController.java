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

/**
 *
 * @author willi
 */
public class AdminAboutScreenController {
    
    PageSwitcher pSwitch = new PageSwitcher();
    
    @FXML
    private Button btnGuestMenu;
    @FXML
    private Button btnHomeMenu;
    @FXML
    private Button btnEventMenu;
    
    
    @FXML
    private void handleGuestMenuButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event,"AdminGuestRegisterScreen.fxml");
    }
    
    @FXML
    private void handleEventMenuButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event,"AdminEventsPage.fxml");
    }
    
    @FXML
    private void handleHomeMenuButtonAction(ActionEvent event) throws IOException {
        pSwitch.switcher(event,"AdminHomeScreen.fxml");
    }
}
