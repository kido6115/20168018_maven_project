package org.iisi.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.iisi.db.JDBCChangePwd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "changepassword")
@SessionScoped

public class ChangePasswordController implements Eid {
	private static final Logger LOGGER = LoggerFactory.getLogger(ChangePasswordController.class);
	private String oldPwd;
	private String newPwd;
	private String checkPwd;
	public String change(){
//		String eid;
//		FacesContext context = FacesContext.getCurrentInstance();
//		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
//		eid = (String) session.getAttribute("eid");
		LOGGER.debug(oldPwd);
		LOGGER.debug(newPwd);
		LOGGER.debug(checkPwd);


		JDBCChangePwd db = new JDBCChangePwd();	
		int status = db.ChangePwd(eid, oldPwd,newPwd);
		LOGGER.debug("Change password status is "+status);

		return "changepwd.xhtml";
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getCheckPwd() {
		return checkPwd;
	}
	public void setCheckPwd(String checkPwd) {
		this.checkPwd = checkPwd;
	}

}
