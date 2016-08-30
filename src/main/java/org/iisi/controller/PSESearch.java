package org.iisi.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "psesearch")
@SessionScoped
public class PSESearch implements Serializable {
	private static final long serialVersionUID = -2322823282417821899L;
	private static final Logger LOGGER = LoggerFactory.getLogger(PSESearch.class);

}
