package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import DAODB.DAODBTrackedObject;
import DAODB.DAODBentry;
import DAODB.DAODBuser;
import beans.Entry;
import beans.User;

import java.sql.*;

public class dbUtility {
	
		//PROPERTIES FOR DATABASE b
		Properties prop = Utility.getProp();
		String DRIVER_URL = prop.getProperty("DRIVER_URL");
		String DATABASE_LINK = prop.getProperty("DATABASE_LINK");
		String DATABASE_NAME = prop.getProperty("DATABASE_NAME");
		String connectionURL = DATABASE_LINK + "/" + DATABASE_NAME ;
		
		
		/**create simple database*/
		public void createDatabase(){
			
			//LOAD THE DRIVER
			try {
				Class.forName(DRIVER_URL);
				System.out.println(DRIVER_URL + " loaded.");
			} catch (ClassNotFoundException e) {
				System.err.print("ClassNotFoundException: ");
				System.err.println(e.getMessage());
				System.out.println("\n Make sure your CLASSPATH variable " +
                "contains %DERBY_HOME%\\lib\\derby.jar (${DERBY_HOME}/lib/derby.jar). \n");}
			
			//START DATABASE
			System.out.println("creating database " + DATABASE_NAME + "...");
			try {
				Connection cn = DriverManager.getConnection(connectionURL + ";create=true");
				System.out.println("database " + DATABASE_NAME + " created");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	    /**
	     * Turn on built-in user authentication and user authorization.
	     *
	     * @param conn a connection to the database.
	     */
	    public static void turnOnBuiltInUsers()  {
	        System.out.println("Turning on authentication.");
	        Connection conn = null ;
	        try {
				conn = ConnectionPool.getConnection();
			} catch (CoreException e) {
				e.printStackTrace();
			}
	        try {
				Statement s = conn.createStatement();

				// Setting and Confirming requireAuthentication
				s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
				    "'derby.connection.requireAuthentication', 'true')");
				ResultSet rs = s.executeQuery(
				    "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
				    "'derby.connection.requireAuthentication')");
				rs.next();
				System.out.println("Value of requireAuthentication is " +
				    rs.getString(1));
				// Setting authentication scheme to Derby
				s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
				    "'derby.authentication.provider', 'BUILTIN')");

				// Creating some sample users
				s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
				    "'derby.user." + Links.get("USER") + "', '"+ Links.get("PASSWORD") + "')");

				// Setting default connection mode to no access
				// (user authorization)
				s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
				    "'derby.database.defaultConnectionMode', 'noAccess')");
				// Confirming default connection mode
				rs = s.executeQuery (
				    "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
				    "'derby.database.defaultConnectionMode')");
				rs.next();
				System.out.println("Value of defaultConnectionMode is " +
				    rs.getString(1));

				// Defining read-write users
				s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
				    "'derby.database.fullAccessUsers', '"+ Links.get("USER") + "')");

				// Defining read-only users
//	        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
//	            "'derby.database.readOnlyAccessUsers', 'guest')");

				// Confirming full-access users
				rs = s.executeQuery(
				    "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
				    "'derby.database.fullAccessUsers')");
				rs.next();
				System.out.println("Value of fullAccessUsers is " + rs.getString(1));

				// Confirming read-only users
				rs = s.executeQuery(
				    "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
				    "'derby.database.readOnlyAccessUsers')");
				rs.next();
				System.out.println("Value of readOnlyAccessUsers is " +
				    rs.getString(1));

				// We would set the following property to TRUE only
				// when we were ready to deploy.
				s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
				    "'derby.database.propertiesOnly', '" + Links.get("SYS_OVERID") + "')");
				s.close();
				ConnectionPool.returnCon(conn);
			} catch (SQLException | CoreException e) {
				e.printStackTrace();
			}
	    }
	    
		public static void createTable(){
			Connection cn = null;
			Statement st = null;
			try {
				System.out.println("connecting to database...");
				cn = ConnectionPool.getConnection();
				st = cn.createStatement();
				System.out.println("connected succesfully to database...");
				System.out.println("creating table in database...");
				st.execute("create table app.tracked(id integer primary key generated always as identity, date timestamp not null)");
				System.out.println("table created");
				
			} catch (SQLException | CoreException e) {
				e.printStackTrace();
			} finally {
				try {
					System.out.println("closing connection....");
					if(st!=null){st.close();}
					if(cn!=null){ConnectionPool.returnCon(cn);}
					System.out.println("connection closed");
				} catch (CoreException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void createUserTable(){
			Connection cn = null;
			Statement st = null;
			try {
				System.out.println("connecting to database...");
				cn = ConnectionPool.getConnection();
				st = cn.createStatement();
				System.out.println("connected succesfully to database...");
				System.out.println("creating table in database...");
				st.execute("create table app.users(id integer primary key generated always as identity, name varchar(20) unique not null, password varchar(10) not null)");
				System.out.println("table created");
				
			} catch (SQLException | CoreException e) {
				e.printStackTrace();
			} finally {
				try {
					System.out.println("closing connection....");
					if(st!=null){st.close();}
					if(cn!=null){ConnectionPool.returnCon(cn);}
					System.out.println("connection closed");
				} catch (CoreException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void createTrackedObjectTable(){
			Connection cn = null;
			Statement st = null;
			try {
				System.out.println("connecting to database...");
				cn = ConnectionPool.getConnection();
				st = cn.createStatement();
				System.out.println("connected succesfully to database...");
				System.out.println("creating table TrackedObjects in database...");
				st.execute("create table app.TrackedObjects(id integer primary key generated always as identity, name varchar(20) unique not null, value int not null, category varchar(20), target int,frequency varchar(62))");
				System.out.println("table created TrackedObjects");
				
			} catch (SQLException | CoreException e) {
				e.printStackTrace();
			} finally {
				try {
					System.out.println("closing connection....");
					if(st!=null){st.close();}
					if(cn!=null){ConnectionPool.returnCon(cn);}
					System.out.println("connection closed");
				} catch (CoreException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
		public static void createEntryTable(){
			Connection cn = null;
			Statement st = null;
			try {
				System.out.println("connecting to database...");
				cn = ConnectionPool.getConnection();
				st = cn.createStatement();
				System.out.println("connected succesfully to database...");
				System.out.println("creating table entry in database...");
				st.execute("create table app.entry(id integer primary key generated always as identity, object_id integer, user_id integer, created timestamp not null, value int not null, foreign key (object_id) references app.TrackedObjects(id),foreign key (user_id) references app.users(id) )");
				System.out.println("table entry created");
				
			} catch (SQLException | CoreException e) {
				e.printStackTrace();
			} finally {
				try {
					System.out.println("closing connection....");
					if(st!=null){st.close();}
					if(cn!=null){ConnectionPool.returnCon(cn);}
					System.out.println("connection closed");
				} catch (CoreException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void createFrequencyTable(){
			
			Connection cn = null;
			String sql = "create table app.frequency(user_id integer, object_id integer, frequecy varchar(65), foreign key (object_id) references app.TrackedObjects(id), foreign key (user_id) references app.users(id), primary key (user_id,object_id))";
			try {
				cn =ConnectionPool.getConnection();
				Statement st = cn.createStatement();
				st.execute(sql);
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
		
		public void addData(){
			DAODBTrackedObject dt = new DAODBTrackedObject();
			dt.createTrackedObject("vit k", 200, "vitamine");
			dt.createTrackedObject("vit a", 200, "vitamine");
			dt.createTrackedObject("vit b", 400, "vitamine");
			dt.createTrackedObject("vit c", 500, "vitamine");
//			DAODBuser du = new DAODBuser();
//			du.addUser(new User("marc", "456"));
			DAODBentry de = new DAODBentry();
			de.addEntry(new Entry(2l,1l,new java.util.Date(),3));
			de.addEntry(new Entry(2l,101l,new java.util.Date(),7));
			de.addEntry(new Entry(3l,101l,new java.util.Date(),4));
			de.addEntry(new Entry(4l,1l,new java.util.Date(),2));
			
			
		}
		
		public static void main(String[] args) {
			System.out.println("iiiiiiiiii");
			dbUtility dbu = new dbUtility();
//			dbu.createDatabase();
//			dbu.turnOnBuiltInUsers();
//			createUserTable();
//			createTrackedObjectTable();
//			createEntryTable();
//			dbu.addData();
//			DAODBTrackedObject dt = new DAODBTrackedObject();
//			dt.updateTrackedObject(2l, "fod", 4, "go");
//			DAODBentry de = new DAODBentry();
//			List<Entry> ls = de.getEntryByUserIdAfter(101l, new java.util.Date());
//			for (Entry entry : ls){
//				System.out.println(entry);
//			}
			createTrackedObjectTable();
			createEntryTable();
			createFrequencyTable();
		}

}
