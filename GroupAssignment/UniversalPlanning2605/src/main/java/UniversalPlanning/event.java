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
public class event {

    public event() {
        this(null,null,null,null);
    }

    public StringProperty getEventID() {
        return eventID;
    }

    public void setEventID(StringProperty eventID) {
        this.eventID = eventID;
    }

    public StringProperty getEventName() {
        return eventName;
    }

    public void setEventName(StringProperty eventName) {
        this.eventName = eventName;
    }

    public StringProperty getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(StringProperty eventAddress) {
        this.eventAddress = eventAddress;
    }

    public StringProperty getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(StringProperty eventDateTime) {
        this.eventDateTime = eventDateTime;
    }
    
    
    private StringProperty eventID;
    private StringProperty eventName;
    private StringProperty eventAddress;
    private StringProperty eventDateTime;
    
    public event(String eventID, String eventName, String eventAddress, String eventDateTime){
    this.eventID = new SimpleStringProperty(eventID);
    this.eventName = new SimpleStringProperty(eventName);
    this.eventAddress = new SimpleStringProperty(eventAddress);
    this.eventDateTime = new SimpleStringProperty(eventDateTime);
}
    
}
