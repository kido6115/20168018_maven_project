package org.iisi.bean;

import java.io.Serializable;

public class RemainHour implements Serializable{
	private static final long serialVersionUID = -2322823282417821899L;

	private float remainHour;
	private String kname;
	
	public RemainHour(float remainHour, String kname) {
		this.remainHour = remainHour;
		this.kname = kname;
		

	}

	public float getremainHour() {
		return remainHour;

	}

	public String getkname() {
		return kname;

	}

	
	public void setremainHour(float remainHour) {
		this.remainHour = remainHour;

	}

	public void setkname(String kname) {
		this.kname = kname;

	}
}
