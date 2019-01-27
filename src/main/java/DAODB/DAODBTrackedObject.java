package DAODB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAOabstract.DAOTrackedObject;
import beans.TrackedObject;
import core.ConnectionPool;
import core.CoreException;

public class DAODBTrackedObject implements DAOTrackedObject {

	@Override
	public TrackedObject getrackedObjectById(Long id) {
		Connection cn = null;
		String sqls = "";
		TrackedObject ob = null;
		try{
			cn = ConnectionPool.getConnection();
			sqls = "select * from app.Object where id=?";
			PreparedStatement ps = cn.prepareStatement(sqls);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Long nid = rs.getLong(1);
				String name = rs.getString(2);
				int value = rs.getInt(3);
				ob = new TrackedObject(nid, name, value);
			}
		}
		catch (Exception e){
			e.printStackTrace();
			System.err.println(sqls);
		}
		finally {
			try {
				ConnectionPool.returnCon(cn);
			} catch (CoreException e) {
				System.err.println("error while returning connection");
				e.printStackTrace();
			}
		}
		return ob;
	}

	@Override
	public void removeObject(Long id) {
		Connection cn = null;
		String sqls = "";
		try{
			sqls = "delete from app.trackedobjects where id =?";
			cn = ConnectionPool.getConnection();
			PreparedStatement ps = cn.prepareStatement(sqls);
			ps.setLong(1, id);
			ps.execute();
		} catch (Exception e){
			e.printStackTrace();
			System.err.println(sqls);
		} finally {
			try {
				ConnectionPool.returnCon(cn);
			} catch (CoreException e) {
				System.err.println("error while returning connection");
				e.printStackTrace();
			}
		}
	}

	@Override
	public TrackedObject getrackedObjectByNameAndValue(String name, int value) {
		Connection cn = null;
		String sqls ="";
		TrackedObject to = null;
		try {
			sqls = "select* from app.trackedobjects where name=? and value=?";
			cn=ConnectionPool.getConnection();
			PreparedStatement ps = cn.prepareStatement(sqls);
			ps.setString(1, name);
			ps.setInt(2, value);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				long id = rs.getLong(1);
				String nname = rs.getString(2);
				int nvalue = rs.getInt(3);
				to = new TrackedObject(id, nname, nvalue);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionPool.returnCon(cn);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return to;
	}

	@Override
	public Long createTrackedObject(String name, String value) {
		Connection cn = null;
		String sql = "insert into app.trackedobject (name,value) values (?,?)";
		Long id = -1l;
		try {
			cn = ConnectionPool.getConnection();
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.execute();
			String str = "SELECT IDENTITY_VAL_LOCAL() FROM app.trackedobject";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(str);
			while(rs.next()){
				id = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionPool.returnCon(cn);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	
	public static void main(String[] args) {
		
	}
}
