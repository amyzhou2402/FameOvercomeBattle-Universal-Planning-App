/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniversalPlanning;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author josiah
 */
public class invitation {

    public invitation() {
        this(null,null,null,null);
    }

    public StringProperty getInvitationID() {
        return invitationID;
    }

    public void setInvitationID(StringProperty invitationID) {
        this.invitationID = invitationID;
    }

    public StringProperty getEventID() {
        return eventID;
    }

    public void setEventID(StringProperty eventID) {
        this.eventID = eventID;
    }

    public StringProperty getGuestID() {
        return guestID;
    }

    public void setGuestID(StringProperty guestID) {
        this.guestID = guestID;
    }

    public StringProperty getAdminID() {
        return adminID;
    }

    public void setAdminID(StringProperty adminID) {
        this.adminID = adminID;
    }
    
    
    private StringProperty invitationID;
    private StringProperty eventID;
    private StringProperty guestID;
    private StringProperty adminID;
    
    public invitation(String invitationID, String eventID, String guestID, String adminID){
        this.invitationID = new SimpleStringProperty(invitationID);
        this.eventID = new SimpleStringProperty(eventID);
        this.guestID = new SimpleStringProperty(guestID);
        this.adminID = new SimpleStringProperty(adminID);
    }
    
    
    
}
    

