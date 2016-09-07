package org.iisi.controller;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.iisi.db.JDBCCheckPSE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean(name = "declinepse")
@SessionScoped
public class DeclinePSEController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeclinePSEController.class);

	public String decline(){
		FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params = 
	      fc.getExternalContext().getRequestParameterMap();
	      String pid =  params.get("pid");
	      LOGGER.debug(pid);
	      JDBCCheckPSE cp = new JDBCCheckPSE();
	      int check = cp.check(Integer.parseInt(pid), null, 2);
	      LOGGER.debug("退回成功"+Integer.toString(check));
		return "chckPSE.xhtml";
	}

}
