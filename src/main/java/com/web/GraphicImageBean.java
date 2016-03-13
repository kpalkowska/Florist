package com.web;

import java.io.ByteArrayInputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import lombok.Data;

@ManagedBean(name = "graphicImageBean")
@SessionScoped
public @Data class GraphicImageBean {
		
	@ManagedProperty("#{productBean}")
	ProductBean productBean;
    
	public StreamedContent getStreamedImageById() {
	    FacesContext context = FacesContext.getCurrentInstance();

	    if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	        return new DefaultStreamedContent();
	    }
	    else {
	        String id = context.getExternalContext().getRequestParameterMap().get("productId");
	        long productId = Long.valueOf(id);
	        byte[] foto = productBean.getService().findProductById(productId).getFoto();
	        return new DefaultStreamedContent(new ByteArrayInputStream(foto));
	    }
	}
}
