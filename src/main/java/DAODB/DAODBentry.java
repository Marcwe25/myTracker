package DAODB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import DAOabstract.DAOentry;
import beans.Entry;
import core.ConnectionPool;
import core.CoreException;

public class DAODBentry implements DAOentry {

	
	public static void main(String[] args) {}

	@Override
	public List<Entry> getEntryByTrackedObjectId(Long id) {
		List<Entry> ls = null;
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
				java.util.Date date = rs.getTimestamp("created");
				int quantity = rs.getInt("value");
				beans.Entry Entry = new Entry();
				Entry.setId(nid);
				Entry.setObjectId(objectId);
				Entry.setUserId(userId);
				Entry.setDate(date);
				Entry.setQuantity(quantity);
				ls.add(Entry);
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
	public List<Entry> getEntryByUserId(Long id) {
		List<Entry> ls = null;
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
				java.util.Date date = rs.getTimestamp("created");
				int quantity = rs.getInt("value");
				beans.Entry Entry = new Entry();
				Entry.setId(nid);
				Entry.setObjectId(objectId);
				Entry.setUserId(userId);
				Entry.setDate(date);
				Entry.setQuantity(quantity);
				ls.add(Entry);
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
	public List<Entry> getEntryByUserIdAfter(Long id, Date fromDate) {
		List<Entry> ls = null;
		Connection cn = null;
		String sqls = "";
		try {
			cn = ConnectionPool.getConnection();
			sqls = "select * from app.entry where user_id=? and created>=?";
			cn = ConnectionPool.getConnection();
			PreparedStatement ps = cn.prepareStatement(sqls);
			ps.setLong(1, id);
			ps.setDate(2, new java.sql.Date(fromDate.getTime()));
			ResultSet rs = ps.executeQuery();
			ls = new ArrayList<>();
			while(rs.next()){
				long nid = rs.getLong("id");
				long objectId = rs.getLong("object_id");
				long userId = rs.getLong("user_id");
				java.util.Date date = rs.getTimestamp("created");
				int quantity = rs.getInt("value");
				beans.Entry Entry = new Entry();
				Entry.setId(nid);
				Entry.setObjectId(objectId);
				Entry.setUserId(userId);
				Entry.setDate(date);
				Entry.setQuantity(quantity);
				ls.add(Entry);
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
	public void removeEntry(Date date, long user_id) {
		Connection cn = null;
		String sqls = "";
		try {
			cn = ConnectionPool.getConnection();
			sqls = "delete from table app.entry where created=? and user_id=?";
			PreparedStatement ps = cn.prepareStatement(sqls);
			ps.setTimestamp(1,new java.sql.Timestamp(date.getTime()));
			ps.setLong(2, user_id);
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
	public Long addEntry(Entry entry) {
		long id =-1l;
		Connection cn = null;
		String sqls = "insert into app.entry (object_id,user_id,created,value) values (?,?,?,?)";
		try {
			cn = ConnectionPool.getConnection();
			PreparedStatement ps = cn.prepareStatement(sqls);
			ps.setLong(1, entry.getObjectId());
			ps.setLong(2, entry.getUserId());
			ps.setTimestamp(3, new java.sql.Timestamp(entry.getDate().getTime()));
			ps.setInt(4, entry.getQuantity());
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
