package org.iisi.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iisi.bean.CheckPSE;
import org.iisi.bean.CkeckSub;
import org.iisi.db.JDBCCheckPSE;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "modifypse")
@RequestScoped
public class ModifyPSEController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckPSEController.class);
	private List<CkeckSub> checkSubList;

	public String modify(){
		FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params = 
	      fc.getExternalContext().getRequestParameterMap();
	     String pid =  params.get("pid");
	     HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

	     request.setAttribute("PID",pid);
	     JDBCCheckPSE checkPSE = new JDBCCheckPSE();
	      checkSubList=checkPSE.ListPSE(Integer.parseInt(pid));
	      for(CkeckSub list : checkSubList){
	      LOGGER.debug(ToStringBuilder.reflectionToString(list));
	      }
	     LOGGER.debug(pid);
		return "modifyPSE.xhtml";
	}

}
