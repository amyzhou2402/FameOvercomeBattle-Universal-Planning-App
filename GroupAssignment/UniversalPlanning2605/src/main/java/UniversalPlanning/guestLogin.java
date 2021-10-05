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

public class guestLogin {
    
public guestLogin() {
        this(null,null,null);
    }

    
    public StringProperty getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(StringProperty accessCode) {
        this.accessCode = accessCode;
    }

    public StringProperty getFirstName() {
        return firstName;
    }

    public void setFirstName(StringProperty firstName) {
        this.firstName = firstName;
    }

    public StringProperty getLastName() {
        return lastName;
    }

    public void setLastName(StringProperty lastName) {
        this.lastName = lastName;
    }
    
     public StringProperty accessCode() {
        return accessCode;
    }

    public StringProperty firstName() {
        return firstName;
    }

    public StringProperty lastName() {
        return lastName;
    }
    
    
    private StringProperty accessCode;
    private StringProperty firstName;
    private StringProperty lastName;
    
   
public guestLogin(String accessCode, String firstName, String lastName){
    this.accessCode = new SimpleStringProperty(accessCode);
    this.firstName = new SimpleStringProperty(firstName);
    this.lastName = new SimpleStringProperty(lastName);
}
    
      
    
}
