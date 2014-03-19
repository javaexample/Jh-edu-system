package testtest;

import java.sql.*;
import java.sql.SQLException;
import java.io.*;

public class Connect {

	public static void main(String[] args) throws Exception {
		
		String url = "jdbc:sqlserver://211.233.89.202:1433;DatabaseName=sjedu";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url, "아이디", "비번");
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM dbo.CRMSAUCE");
		while (rs.next()) {
			String field1 = rs.getString("CrmID");
			String field2 = rs.getString("CrmDateTime");
			String field3 = rs.getString("CrmTel");
			String field4 = rs.getString("CrmPhone");
			String field5 = rs.getString("CrmName");
			String field6 = rs.getString("CrmSex");
			String field7 = rs.getString("CrmAge");
			String field8 = rs.getString("CrmEmail");
			String field9 = rs.getString("CrmAdd");
			String field10 = rs.getString("CrmInquiry");
			String field11 = rs.getString("CrmNote");
			String field12 = rs.getString("CrmCharge");
			String field13 = rs.getString("CrmContent");
			String field14 = rs.getString("CrmShipping");

			System.out.println(field1 + ", " + field2 + ", " + field3 + ", "
					+ field4 + ", " + field5 + ", " + field6 + ", " + field7
					+ ", " + field8 + ", " + field9 + ", " + field10 + ", "
					+ field11 + ", " + field12 + ", " + field13 + ", "
					+ field14);

		}
		rs.close();
		stmt.close();
		conn.close();
	}
}
