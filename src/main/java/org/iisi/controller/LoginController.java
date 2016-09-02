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
public class LoginController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourSearchController.class);

	
	private Employee employee = new Employee();

	public String login() {
		boolean empExist = empExist(employee);
		if (empExist) {
			
			LOGGER.debug("EID is "+employee.getEid());
			LOGGER.debug("Password is "+employee.getPwd());
		

			
		
		    
			return "index2.xhtml";
		} else {
			
			return "index.xhtml";

		}
	}

	protected boolean empExist(Employee employee) {
		
		JDBCLogin db = new JDBCLogin();
		int status = db.CheckEmployee(employee.getEid(), employee.getPwd());
	

		LOGGER.debug("Login status is "+Integer.toString(status));

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
