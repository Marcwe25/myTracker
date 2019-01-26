package facade;

import java.util.List;
import java.util.Set;

import DAODB.DAODBentry;
import DAODB.DAODBuser;
import DAOabstract.DAOTrackedObject;
import DAOabstract.DAOentry;
import DAOabstract.DAOuser;
import beans.TrackedObject;
import beans.User;

public class userFacade {

	  private DAOuser daoUser;

	  private DAOentry daoEntry;

	  private DAOTrackedObject daoDAOTrackedObject;

	
	private userFacade() {
		// TODO Auto-generated constructor stub
	}
	
	public userFacade(User user){
		this.daoUser = new DAODBuser(user);
		this.daoEntry = new DAODBentry(user);
		this.daoDAOTrackedObject = new DAOTrackedObject(user);
	}
	
	public Set<TrackedObject> getTracketTrackedObjectsList() {
		  return daoUser.getEntries();
	}

	public Set<TrackedObject> addEntry(String date, Integer quantity, String objectName, int objectValue) {
		  daoEntry.addEntry(objectName, objectValue, date, quantity);
		  return this.getTracketTrackedObjectsList();
	}
	
	public Set<TrackedObject> addTrackedTrackedObject() {
		  return null;
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
