package controller;

import javax.faces.bean.ManagedBean;

import Db.JDBCChangePwd;

@ManagedBean(name="Changepwd" , eager=true)
public class Changepwd {
	private String oldpwd;
	private String newpwd;
	private String checkpwd;
	public String getOldpwd() {
		return oldpwd;
	}
	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getCheckpwd() {
		return checkpwd;
	}
	public void setCheckpwd(String checkpwd) {
		this.checkpwd = checkpwd;
	}
	
	
	
	
	public void edit(){
		JDBCChangePwd db = new JDBCChangePwd();	
		int status = db.ChangePwd(Eid, oldpwd,newpwd);
		if(newpwd.equals(checkpwd)==false)
		{
			System.out.println("aa");

		}
		else if(oldpwd.equals(newpwd)==true)
		{
			//舊密碼與新密碼不可以一樣喔
			response.sendRedirect("changepwd.jsp?status=2");
		}
		else if(status==4)
		{
			session.removeAttribute("Employee");
			response.sendRedirect("index.jsp?status="+status+"#Login");

		}
		else {
		
		response.sendRedirect("changepwd.jsp?status="+status);

		}
	}
	
	
	
	
}
