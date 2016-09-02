package org.iisi.bean;

import java.io.Serializable;

public class Edit implements Serializable{
	private static final long serialVersionUID = -2322823282417821899L;

	private String Pid;
	private String Time;
	private String Reply;
	

	public Edit(String Pid, String Time, String Reply) {
		this.Pid = Pid;
		this.Time = Time;
		this.Reply = Reply;
		

	}

	public String getPid() {
		return Pid;

	}

	public String getTime() {
		return Time;

	}

	public String getReply() {
		return Reply;

	}

	

	public void setPid(String Pid) {
		this.Pid = Pid;

	}

	public void setTime(String Time) {
		this.Time = Time;

	}

	public void setReply(String Reply) {
		this.Reply = Reply;

	}

	
}
