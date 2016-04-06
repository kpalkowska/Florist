package com.web;

import java.io.ByteArrayInputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.spring.service.ProductService;

import lombok.Data;

@ManagedBean(name = "graphicImageBean")
public @Data class GraphicImageBean {
	
	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	
	@ManagedProperty("#{productService}")
	private ProductService productService;
	
	@ManagedProperty("#{product.id}")
	private Long productId;

	@ManagedProperty("#{productBean}")
	private ProductBean productBean;

	private StreamedContent fotoToDisplay;
	
	public StreamedContent getOneOfRoses() {
	    FacesContext context = FacesContext.getCurrentInstance();

	    if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	        return new DefaultStreamedContent();
	    }
	    else {
	        byte[] foto = productService.findProductByTypeRose().getFoto();
	        return new DefaultStreamedContent(new ByteArrayInputStream(foto));
	    }
	}
	
	public StreamedContent getOneOfTulips() {
	    FacesContext context = FacesContext.getCurrentInstance();

	    if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	        return new DefaultStreamedContent();
	    }
	    else {
	        byte[] foto = productService.findProductByTypeTulips().getFoto();
	        return new DefaultStreamedContent(new ByteArrayInputStream(foto));
	    }
	}
}
