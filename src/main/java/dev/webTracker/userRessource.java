package dev.webTracker;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import facade.userFacade;

@Path("user")
public class userRessource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5722001419571307360L;
	private static final Logger log4j = LogManager.getLogger("cpFileLogger");
	
	@Context
	private HttpServletRequest request;
	
	@Context
	private HttpServletResponse response;

	public userRessource() {}
	
	private userFacade userFacade(){
		userFacade uf = null;
		uf = (userFacade) request.getSession().getAttribute("facade");
		
		return uf;
	}
	
	public Response getUserParam(){
		userFacade uf = userFacade();
		if(uf==null){
			log4j.trace("session with no facade");
			return ResponseUtility.badLogin("user");
		}
		
		
		return null;
	}
	
	
	
}
