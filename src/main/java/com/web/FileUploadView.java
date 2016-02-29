package com.web;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.model.ProductModel;
import com.spring.service.LogService;

@ManagedBean
public class FileUploadView {
 
	@Autowired
	private LogService logService;
	
	
    public void handleFileUpload(FileUploadEvent event) {
    	if(event != null){
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
       
    	}
    	else 
    		logService.logInfo("foto :: baaad....");
    }
}