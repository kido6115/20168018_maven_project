package controller;

import javax.faces.bean.ManagedBean;
import bean.Employee;
import db.JDBCLogin;

@ManagedBean(name = "login")

public class Login {
	private String Eid;
	private String Pwd;
	private Employee employee = new Employee();

	public String login() {
		boolean empExist = empExist(employee);
		if (empExist) {
			System.out.println(employee.getEid());
			System.out.println(employee.getPwd());
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
		// UserDAO dao = new UserDAOImpl();
		//
		// Member member = dao.getUserByAccount(user.getAccount(),
		// user.getPassword());
		// if(member != null) {
		// return true;
		// }else{
		// return false;
		// }

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
