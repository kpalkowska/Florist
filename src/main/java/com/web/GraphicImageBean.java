package com.web;

import java.io.ByteArrayInputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import lombok.Data;

@ManagedBean(name = "graphicImageBean")
@SessionScoped
public @Data class GraphicImageBean {
		
	@ManagedProperty("#{productBean}")
	ProductBean productBean;
    
	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	
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
	
	public StreamedContent getOneOfRoses() {
	    FacesContext context = FacesContext.getCurrentInstance();

	    if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	        return new DefaultStreamedContent();
	    }
	    else {
	        byte[] foto = productBean.getService().findProductByTypeRose().getFoto();
	        return new DefaultStreamedContent(new ByteArrayInputStream(foto));
	    }
	}
	
	public StreamedContent getOneOfTulips() {
	    FacesContext context = FacesContext.getCurrentInstance();

	    if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	        return new DefaultStreamedContent();
	    }
	    else {
	        byte[] foto = productBean.getService().findProductByTypeTulips().getFoto();
	        return new DefaultStreamedContent(new ByteArrayInputStream(foto));
	    }
	}
}
