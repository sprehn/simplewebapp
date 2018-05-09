package com.firstdata.simplewebapp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/** A backing been for the login/logout form. */
@Named("login")
@RequestScoped
public class LoginBean {

    @Inject
    private CurrentUserBean currentUser;
    private String username;
    private String password;

    public String getCurrentUser() {
        return currentUser.getName();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return currentUser.isLoggedIn();
    }

    public String getServerId() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    public String getSessionId() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getId();
    }

    /*
     * Actions
     */
    
    public String login() {
        // accept anything for now
        this.password = null;
        this.currentUser.setName(this.username);
        return "/index.xhtml?faces-redirect=true";
    }

    public String logout() {
        this.currentUser.setName(null);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

}
