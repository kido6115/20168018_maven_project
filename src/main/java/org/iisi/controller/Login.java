package org.iisi.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.iisi.bean.Employee;
import org.iisi.db.JDBCLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "login")
@SessionScoped
public class Login {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearch.class);

	
	private Employee employee = new Employee();

	public String login() {
		boolean empExist = empExist(employee);
		if (empExist) {
			
			LOGGER.debug(employee.getEid());
			LOGGER.debug(employee.getPwd());
			FacesContext context = FacesContext.getCurrentInstance();
		    HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		    session.setAttribute("eid",employee.getEid());
			return "index2.xhtml";
		} else {
			
			return "index.xhtml";

		}
	}

	protected boolean empExist(Employee employee) {
		JDBCLogin db = new JDBCLogin();
		int status = db.CheckEmployee(employee.getEid(), employee.getPwd());
		System.out.println(status);
		if (status == 1) {
			return true;
		} else {
			return false;
		}
	
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void Employee(Employee employee) {
		this.employee = employee;
	}

}
