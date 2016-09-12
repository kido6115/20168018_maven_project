package org.iisi.controller;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iisi.bean.Edit;
import org.iisi.db.EditJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@ManagedBean(name = "editpse")
@SessionScoped
public class EditPSEController implements Basic{
	private static final Logger LOGGER = LoggerFactory.getLogger(AcceptPSEController.class);
   private List<Edit> editPSEList;
	public String editPSE(){
		EditJDBC edit = new EditJDBC();
		try {
			 editPSEList = edit.edit(eid);
			for(Edit list:editPSEList){
			LOGGER.debug(ToStringBuilder.reflectionToString(list));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(),e);
		}
		return "editPSE.xhtml";
	}
	public List<Edit> getEditPSEList() {
		return editPSEList;
	}
	public void setEditPSEList(List<Edit> editPSEList) {
		this.editPSEList = editPSEList;
	}

}
