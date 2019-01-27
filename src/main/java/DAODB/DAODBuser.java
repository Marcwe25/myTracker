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
	
	public DAODBuser() {
		super();
		this.user = new User();
	}

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
	public User getUser(){
		return user;
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
				long id = rs.getLong(1);
				String name = rs.getString(2);
				String pass = rs.getString(3);
				user = new User(name,pass);
				user.setId(id);
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
		String sqls ="";
		try {
			cn = ConnectionPool.getConnection();
			sqls = "update app.users set name='"+user.getUsername()+"' , password='"+user.getPassword()+"' where id="+user.getId();
			Statement st = cn.createStatement();
			st.execute(sqls);
		} catch (CoreException | SQLException e) {
			e.printStackTrace();
			System.out.println(sqls);
		}
		
		return user;
	}

	@Override
	public User addUser(User user) {
		Connection cn = null;
		String sqls = "";
		Long id =-1l;
		try {
			cn = ConnectionPool.getConnection();
			sqls = "insert into app.users  (name,password) values ('"+user.getUsername()+"','"+user.getPassword()+"')";
			Statement pt = cn.createStatement();
			pt.execute(sqls);
			String str = "SELECT IDENTITY_VAL_LOCAL() FROM app.users";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(str);
			while(rs.next()){
				id = rs.getLong(1);
			}

		} catch (CoreException | SQLException e) {
			e.printStackTrace();
			System.err.println(sqls);
		}
		
		return user;
	}




	public static void main(String[] args) {
		DAODBuser du = new DAODBuser();
		User user = du.getUser("admin", "123");
//		du.addUser(new User("admin","123"));
//		User user = du.getUser("admin","123");
		System.out.println(user.getUsername());
		System.out.println(user.getId());
		System.out.println("entries:");
		System.out.println(user.getEntries());
	}
}
