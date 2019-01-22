package facade;

import DAODB.DAODBentry;
import DAODB.DAODBuser;
import DAOabstract.DAOentry;
import DAOabstract.DAOuser;
import beans.User;

public class userFacade {

	DAOentry entryDAO;
	DAOuser userDAO;
	User currentUser;
	
	private userFacade() {
		// TODO Auto-generated constructor stub
	}
	
	private userFacade(User user){
		this.entryDAO = new DAODBentry(user);
		this.userDAO = new DAODBuser();
		this.currentUser = user;
	}
	
	public static userFacade login(String name, String password){
		DAODBuser du = new DAODBuser();
		userFacade uf = null;
		User user = du.getUser(name, password);
		if(user!=null){
			uf = new userFacade(user);
		}
		return uf;
	}
	
	
	
}
