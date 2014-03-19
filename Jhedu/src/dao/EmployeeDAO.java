package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.EmployeeModel;

public class EmployeeDAO {

	private ConnManager connManager ;
	
	public EmployeeDAO(ConnManager manager ) throws ClassNotFoundException, SQLException {
		this.connManager = manager;
	}
	
	public boolean existsEmployee(String empID) throws SQLException {
		String query = "Select 회원번호 from crmuserdb where 아이디 = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = connManager.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, empID);
			rs = pstmt.executeQuery();
			
			return rs.next();
			
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			connManager.close(conn, pstmt, rs);
		}
	}
	

	public EmployeeModel getEmployee(String empID, String empPassword) throws SQLException {
String query = "Select 회원번호, 아이디, 비밀번호, 이름, 부서명 from crmuserdb where 아이디 = ? and 비밀번호 = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeModel emp = null;
		try {
			conn = connManager.getConnection();
			conn.setAutoCommit(false); // 
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, empID );
			pstmt.setString(2, empPassword);
			
			rs = pstmt.executeQuery();
			
			if ( rs.next()) {
				rs.getString("회원번호");
				String id = rs.getString("아이디");
				String password = rs.getString("비밀번호");
				String name = rs.getString("이름");
				String part = rs.getString("부서명");
				
				emp = new EmployeeModel(id, password, name, part);
				return emp;
			} else {	
				throw new SQLException("아이디나 비밀번호가 틀립니다.");
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			connManager.close(conn, pstmt, rs);
		}
	}
	
	public EmployeeModel findByID(String empID) throws SQLException {
		String query = "Select 회원번호, 아이디, 비밀번호, 이름, 부서명 from crmuserdb where 아이디 = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeModel emp = null;
		try {
			conn = connManager.getConnection();
			conn.setAutoCommit(false); // 
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, empID);
			rs = pstmt.executeQuery();
			
			if ( rs.next()) {
				rs.getString("회원번호");
				String id = rs.getString("아이디");
				String password = rs.getString("비밀번호");
				String name = rs.getString("이름");
				String part = rs.getString("부서명");
				
				emp = new EmployeeModel(id, password, name, part);
				return emp;
			} else {	
				throw new SQLException("존재하지 않습니다. : " + empID);
			}
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			connManager.close(conn, pstmt, rs);
		}
		
	}
	public EmployeeModel insertEmployee(EmployeeModel emp) throws SQLException {
		String insertQuery = "INSERT INTO crmuserdb (아이디, 비밀번호, 이름, 부서명)"
				+ " values ( ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connManager.getConnection();
			conn.setAutoCommit(false); // 
			
			pstmt = conn.prepareStatement(insertQuery);
			
			pstmt.setString(1, emp.getEmpId());
			pstmt.setString(2, emp.getPassword());
			pstmt.setString(3, emp.getName());
			pstmt.setString(4, emp.getPart());
			
			int numOfChanged = pstmt.executeUpdate();
			
			if ( numOfChanged != 1) {
				throw new SQLException("직원 정보 입력에 실패했습니다. : " + emp );
			}
			
			conn.commit(); 
			
			return emp ;
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			connManager.close(conn, pstmt, null);
		}
		
	}
}
