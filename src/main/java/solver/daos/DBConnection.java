package solver.daos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
		private static final String DbDriver="com.mysql.cj.jdbc.Driver";
		public static final String schema="sql9359791";
		private static final String DbURL="jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9359791";
		private static final String username="sql9359791";
		private static final String password="eRBKt5y1Z7";
	public static Connection startConnection(Connection conn) {
		if(isOpen(conn))
			closeConnection(conn);
		try {
			Class.forName(DbDriver);
			conn=DriverManager.getConnection(DbURL, username, password); //apre connesione
			conn.setSchema(schema);	
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	public static boolean isOpen(Connection conn) {
		if(conn==null)
			return false;
		else 
			return true;
	}
	public static Connection closeConnection(Connection conn) {
		if(!isOpen(conn))
			return null;
		try {
			conn.close();
			conn=null;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	public static void main(String[] argv) {
		Connection c=null;
		c=DBConnection.startConnection(c);
		if(DBConnection.isOpen(c))
			System.out.println("connessione stabilita");
	}
}

