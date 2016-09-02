package org.iisi.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public interface Eid {
	FacesContext context = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	String eid = (String) session.getAttribute("eid");

}
