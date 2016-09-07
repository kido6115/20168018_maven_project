package org.iisi.controller;

import org.iisi.db.JDBCCheckPSE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
@ManagedBean(name = "acceptpse")
@SessionScoped
public class AcceptPSEController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AcceptPSEController.class);

	public String accept(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		String pid = params.get("pid");
		LOGGER.debug(pid);
		JDBCCheckPSE cp = new JDBCCheckPSE();
		int check = cp.check(Integer.parseInt(pid), null, 1);
		LOGGER.debug("通過成功" + Integer.toString(check));
		
		return "checkPSE.xhtml";
	}

}
