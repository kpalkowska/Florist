package com.web;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.spring.model.ProductModel;
import com.spring.service.ProductService;

import lombok.Data;

@ManagedBean(name = "graphicImageBean")
@RequestScoped
public @Data class GraphicImageBean {

	private static String FIRST_OFFER_PARAM = "firstOffer";
	private static String SECOND_OFFER_PARAM = "secondOffer";

	@ManagedProperty("#{productService}")
	private ProductService productService;

	@ManagedProperty("#{productBean}")
	private ProductBean productBean;

	public StreamedContent getFotoToDisplay() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			Map<String, String> params = context.getExternalContext().getRequestParameterMap();
			String idString = params.get("foto_idx");
			String target = params.get("target");

			if (StringUtils.isNotEmpty(target)) {
				byte[] foto = null;
				if (FIRST_OFFER_PARAM.equalsIgnoreCase(target)) {
					foto = productBean.getFirstOffer().getFoto();
				} else if (SECOND_OFFER_PARAM.equalsIgnoreCase(target)) {
					foto = productBean.getSecondOffer().getFoto();
				} else {
					foto = new byte[] {};
				}
				return new DefaultStreamedContent(new ByteArrayInputStream(foto));
			} else {
				int id = -1;
				if (StringUtils.isNumeric(idString)) {
					id = Integer.valueOf(idString);
					List<ProductModel> products = productBean.getProducts();
					byte[] foto = products.get(id).getFoto();

					return new DefaultStreamedContent(new ByteArrayInputStream(foto));
				} else {
					return new DefaultStreamedContent();
				}
			}
		}
	}

	public StreamedContent getOneOfRoses() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			byte[] foto = productService.findProductByTypeRose().getFoto();
			return new DefaultStreamedContent(new ByteArrayInputStream(foto));
		}
	}

	public StreamedContent getOneOfTulips() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			byte[] foto = productService.findProductByTypeTulips().getFoto();
			return new DefaultStreamedContent(new ByteArrayInputStream(foto));
		}
	}
}
