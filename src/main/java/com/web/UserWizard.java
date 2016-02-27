package com.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import com.spring.model.UserModel;

import lombok.Data;

@ManagedBean
@ViewScoped
public @Data class UserWizard implements Serializable {
 
    private UserModel user = new UserModel();
     
    private boolean skip;
     
    public void save() {        
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
}