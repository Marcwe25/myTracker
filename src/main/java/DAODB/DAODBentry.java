package DAODB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

import org.apache.catalina.tribes.util.Arrays;

import DAOabstract.DAOentry;
import beans.TrackEntry;
import beans.User;
import core.ConnectionPool;
import core.CoreException;

public class DAODBentry implements DAOentry {

	Statement statement;
	
	
	public DAODBentry(User user) {
		super();
		Set<String> te = user.getTrackedEntries();
		String[] tea = new String[te.size()];
		te.toArray(tea);
		String[] sa = new String[3+te.size()];
		sa[0]="app.tracked";
		sa[1]="id";
		sa[2]="date";
		System.arraycopy(tea, 0, sa, 3, tea.length);
		this.statement = new Statement(sa);
	}

	@Override
	public TrackEntry addEntry(TrackEntry trackEntry) {
		Connection cn = null;
		try {
			cn = ConnectionPool.getConnection();
			PreparedStatement ps = cn.prepareStatement(statement.create());
			ps.setTimestamp(1, Timestamp.valueOf(trackEntry.getDate()));
			for(int i=0;i<statement.otherColumns.length;i++){
				ps.setString(i+2, trackEntry.getTrackObject().get(statement.otherColumns[i]));
			}
			ps.execute();
		} catch (CoreException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionPool.returnCon(cn);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public TrackEntry removeTrackEntry(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrackEntry getEntry(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		User user = new User("aa","bb");
		user.getTrackedEntries().add("blue");
		user.getTrackedEntries().add("green");
		user.getTrackedEntries().add("green");
		user.getTrackedEntries().add("rose");
		user.getTrackedEntries().add("green");
		TrackEntry te = new TrackEntry(LocalDateTime.now(), new HashMap<String, String>());
		te.getTrackObject().put("blue", "586");
		te.getTrackObject().put("green", "6456");
		DAODBentry dd = new DAODBentry(user);
		dd.addEntry(te);
		System.out.println(dd.statement.create());
	}

}
