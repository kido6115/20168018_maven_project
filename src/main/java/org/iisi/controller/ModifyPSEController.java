package org.iisi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.iisi.bean.CkeckSub;
import org.iisi.bean.ModifyDto;
import org.iisi.db.JDBCCheckPSE;
import org.iisi.db.JDBCEditPSE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "modifypse")
@SessionScoped
public class ModifyPSEController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ModifyPSEController.class);
	private List<CkeckSub> checkSubList;
	private List<ModifyDto> modifyList;

	public String modify() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		String pid = params.get("pid");
		//
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		request.setAttribute("PID", pid);
		//
		JDBCCheckPSE checkPSE = new JDBCCheckPSE();
		checkSubList = checkPSE.ListPSE(Integer.parseInt(pid));
		modifyList = new LinkedList<ModifyDto>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		for (CkeckSub list : checkSubList) {

			LOGGER.debug(ToStringBuilder.reflectionToString(list));
			try {
				Date sDate =  sdf.parse(list.getstartdate());
				Date eDate =  sdf.parse(list.getenddate());
				modifyList.add(new ModifyDto(list, sDate, eDate, list.getstarttime(), list.getendtime(),
						list.getkname(), list.getpctotal(), list.getps()));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				LOGGER.debug(e.getMessage(), e);
				;
			}
		}
		for(ModifyDto mList:modifyList){
			LOGGER.debug(ToStringBuilder.reflectionToString(mList));
		}
		LOGGER.debug(pid);
		return "modifyPSE.xhtml";
	}

	public String submitModifyPSE(List<ModifyDto> submitList) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		String pid = params.get("pid");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/mm/dd");
		for (ModifyDto list : submitList) {
			Date startDate=list.getStartDate();
			Date endDate = list.getEndDate();
			String startTime=list.getStartTime();
			String endTime=list.getEndTime();
			String kid=list.getKid();
			int pctotal=list.getHour();
			String ps=list.getPs();
			int pcid=list.getCheckSub().getpcid();
			JDBCEditPSE edit = new JDBCEditPSE();	
			
			edit.eSubPSE(Integer.parseInt(pid), pcid, kid, sdf.format(startDate),sdf.format(endDate), startTime, endTime, Integer.toString(pctotal), ps);
		}
		
		return null;
	}

	public List<CkeckSub> getCheckSubList() {
		return checkSubList;
	}

	public void setCheckSubList(List<CkeckSub> checkSubList) {
		this.checkSubList = checkSubList;
	}

	public List<ModifyDto> getModifyList() {
		return modifyList;
	}

	public void setModifyList(List<ModifyDto> modifyList) {
		this.modifyList = modifyList;
	}


}
