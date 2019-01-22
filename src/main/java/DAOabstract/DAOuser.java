package DAOabstract;

import beans.User;

public interface DAOuser {

	abstract User getUser();
	
	abstract User updateUser(User user);
	
	abstract User addUser(User user);
	
	abstract User getUser(String userName, String Password);
	
}
