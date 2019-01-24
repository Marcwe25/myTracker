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
	
	//fields
	User user;
	
	//constructor
	public DAODBuser(User user) {
		super();
		this.user = user;
	}

	public DAODBuser(String UserName) {
		super();
		this.user = new User(UserName);
	}

	//method
	@Override
	public User getUser() {
		return this.user;
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
	public User getUser(long id) {
		Connection cn = null;
		User user = null;
		try {
			cn = ConnectionPool.getConnection();
			String sqls = "select * from app.users where id='"+Long.toString(id)+"'";
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
		Connection cn = null;
		try {
			cn = ConnectionPool.getConnection();
			String sqls = "update app.users set name='"+user.getUsername()+"' and password='"+user.getPassword()+"where id="+user.getId()+"'";
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
	public User addUser(User user) {
		Connection cn = null;
		try {
			cn = ConnectionPool.getConnection();
			String sqls = "insert into app.users  (name=,password) values ('"+user.getUsername()+"','"+user.getPassword()+")'";
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




	public static void main(String[] args) {
		DAODBuser du = new DAODBuser();
		User user = du.getUser("admin","123");
		System.out.println(user.getUsername());
	}
}
