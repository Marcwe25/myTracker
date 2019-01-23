package DAOabstract;

import beans.User;

public interface DAOuser {

	abstract User getUsers();
	
	abstract User getUser(long id);
	
	abstract User updateUser(User user);
	
	abstract User addUser(User user);
}
