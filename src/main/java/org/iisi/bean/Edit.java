package org.iisi.bean;

import java.io.Serializable;
import java.sql.Date;




public class Edit implements Serializable {
	private static final long serialVersionUID = -2322823282417821899L;

	private String Pid;
	private Date Time;
	private String Reply;

	public Edit(String Pid, Date Time, String Reply) {
		this.Pid = Pid;
		this.Time = Time;
		this.Reply = Reply;

	}

	public Date getTime() {
		return Time;
	}

	public void setTime(Date Time) {
		this.Time = Time;
	}

	public String getPid() {
		return Pid;

	}

	public String getReply() {
		return Reply;

	}

	public void setPid(String Pid) {
		this.Pid = Pid;

	}

	public void setReply(String Reply) {
		this.Reply = Reply;

	}

}
