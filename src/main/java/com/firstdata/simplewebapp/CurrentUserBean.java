
package com.firstdata.simplewebapp;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * This bean stores user information in the HttpSession.
 * This state needs to be shared accross multiple applications servers if we 
 * want to avoid session stickiness and bad user experience if the current app 
 * server goes down.
 * 
 */
@Named
@SessionScoped
public class CurrentUserBean implements Serializable {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isLoggedIn() {
        return this.name != null && !this.name.isEmpty();
    }
    
}
