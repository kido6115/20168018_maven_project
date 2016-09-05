package org.iisi.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.iisi.db.JDBCLogin;

public interface Basic {
	FacesContext context = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	String eid = (String) session.getAttribute("eid");
	JDBCLogin login = new JDBCLogin();
	String dept = Integer.toString(login.getStatus(eid));

}
