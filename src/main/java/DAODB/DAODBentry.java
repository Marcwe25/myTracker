package DAODB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import DAOabstract.DAOentry;
import beans.entry;
import core.ConnectionPool;
import core.CoreException;

public class DAODBentry implements DAOentry {

	
	public static void main(String[] args) {}

	@Override
	public List<entry> getEntryByTrackedObjectId(Long id) {
		List<entry> ls = null;
		Connection cn = null;
		String sqls = "";
		try {
			cn = ConnectionPool.getConnection();
			sqls = "select * from app.entry where object_id=?";
			cn = ConnectionPool.getConnection();
			PreparedStatement ps = cn.prepareStatement(sqls);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			ls = new ArrayList<>();
			while(rs.next()){
				long nid = rs.getLong("id");
				long objectId = rs.getLong("object_id");
				long userId = rs.getLong("user_id");
				java.util.Date date = rs.getDate("created");
				int quantity = rs.getInt("value");
				beans.entry entry = new entry();
				entry.setId(nid);
				entry.setObjectId(objectId);
				entry.setUserId(userId);
				entry.setDate(date);
				entry.setQuantity(quantity);
				ls.add(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionPool.returnCon(cn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ls;
	}

	@Override
	public List<entry> getEntryByUserId(Long id) {
		List<entry> ls = null;
		Connection cn = null;
		String sqls = "";
		try {
			cn = ConnectionPool.getConnection();
			sqls = "select * from app.entry where user_id=?";
			cn = ConnectionPool.getConnection();
			PreparedStatement ps = cn.prepareStatement(sqls);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			ls = new ArrayList<>();
			while(rs.next()){
				long nid = rs.getLong("id");
				long objectId = rs.getLong("object_id");
				long userId = rs.getLong("user_id");
				java.util.Date date = rs.getDate("created");
				int quantity = rs.getInt("value");
				beans.entry entry = new entry();
				entry.setId(nid);
				entry.setObjectId(objectId);
				entry.setUserId(userId);
				entry.setDate(date);
				entry.setQuantity(quantity);
				ls.add(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionPool.returnCon(cn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ls;
	}

	@Override
	public void removeEntry(Long id) {
		Connection cn = null;
		String sqls = "";
		try {
			cn = ConnectionPool.getConnection();
			sqls = "delete from table app.entry where id=?";
			PreparedStatement ps = cn.prepareStatement(sqls);
			ps.setLong(1, id);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionPool.returnCon(cn);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Long addEntry(Long object_id, Long user_id, Date date, int quantity) {
		long id =-1l;
		Connection cn = null;
		String sqls = "insert into app.entry (object_id,user_id,created,value) values (?,?,?,?)";
		try {
			cn = ConnectionPool.getConnection();
			PreparedStatement ps = cn.prepareStatement(sqls);
			ps.setLong(1, object_id);
			ps.setLong(2, user_id);
			ps.setDate(3, new java.sql.Date(date.getTime()));
			ps.setInt(4, quantity);
			ps.execute();
			String str = "SELECT IDENTITY_VAL_LOCAL() FROM app.entry";
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

}
