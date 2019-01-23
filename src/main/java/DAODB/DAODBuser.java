package DAODB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAOabstract.DAOuser;
import beans.User;
import core.ConnectionPool;
import core.CoreException;

public class DAODBuser implements DAOuser {
	
	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getUser(String userName, String Password){
		Connection cn = null;
		User user = null;
		try {
			cn = ConnectionPool.getConnection();
			String sqls = "select * from app.users where name='"+userName+"' and password='"+Password+"'";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sqls);
			if(rs.next()){
				String name = rs.getString(2);
				String pass = rs.getString(3);
				user = new User(name,pass);
			}
		} catch (CoreException | SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}


	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		DAODBuser du = new DAODBuser();
		User user = du.getUser("admin","123");
		System.out.println(user.getUsername());
	}
}
