package facade;

import java.time.Clock;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import DAODB.DAODBTrackedObject;
import DAODB.DAODBentry;
import DAODB.DAODBuser;
import DAOabstract.DAOTrackedObject;
import DAOabstract.DAOentry;
import DAOabstract.DAOuser;
import beans.Entry;
import beans.TrackedObject;
import beans.User;

public class  userFacade {

	  private DAOuser daoUser;

	  private DAOentry daoEntry;

	  private DAOTrackedObject daoDAOTrackedObject;
	  
	  User user;
	  
	  Map<Long,TrackedObject> trackedObjectMap;
	  
	
	private userFacade() {
		daoDAOTrackedObject = new DAODBTrackedObject();
		daoEntry = new DAODBentry();
		daoUser = new DAODBuser();
		
	}
	
	public userFacade(User user){
		this.daoUser = new DAODBuser();
		this.daoEntry = new DAODBentry();
		this.daoDAOTrackedObject = new DAODBTrackedObject();
		this.user = user;
	}
	
	public void updateTrackedObjectMap(java.util.Date fromDate){
		List<Entry> le = daoEntry.getEntryByUserIdAfter(user.getId(),fromDate);
		for(Entry entry : le){
			trackedObjectMap.put(entry.getObjectId(), null);
		}
		for(Long id : trackedObjectMap.keySet()){
			trackedObjectMap.put(id, daoDAOTrackedObject.getrackedObjectById(id));
		}
	}
	
	public Collection<TrackedObject> getTrackedObjectList(java.util.Date fromDate){
		updateTrackedObjectMap(fromDate);
		return trackedObjectMap.values();
	}

	public void addTrackedObject(TrackedObject tObject) {
		daoDAOTrackedObject.createTrackedObject(tObject.getName(), tObject.getValue(),tObject.getCategory());
		trackedObjectMap.put(tObject.getId(), tObject);
	}

	public void addEntries(List<Entry> entries) {
		for(Entry entry : entries){
			entry.setUserId(user.getId());
			daoEntry.addEntry(entry);			
		}
	}
	
	public void undo(String str){
		String[] values = str.split("=", 1);
		switch (values[0]) {
		case "entry":
			daoEntry.removeEntry(new Date, user_id);
			break;
		case "tObject":
			
			break;

		default:
			break;
		}
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
