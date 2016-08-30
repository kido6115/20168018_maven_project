package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import bean.Employee;
import db.JDBCLogin;

@ManagedBean(name = "login")
@SessionScoped
public class Login {
	
	private Employee employee = new Employee();

	public String login() {
		boolean empExist = empExist(employee);
		if (empExist) {
			System.out.println(employee.getEid());
			System.out.println(employee.getPwd());
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
