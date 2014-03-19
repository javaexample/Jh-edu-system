package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnManager {

	String dbUrl = "jdbc:sqlserver://211.233.89.202:1433;DatabaseName=sjedu";

	public ConnManager() throws ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl, "sjedu", "jhoneedu@");
	}
	
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if ( conn!= null )
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if ( pstmt != null) 
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if ( rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
