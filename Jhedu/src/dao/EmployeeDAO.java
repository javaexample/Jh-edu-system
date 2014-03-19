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
		String query = "Select ȸ����ȣ from crmuserdb where ���̵� = ?";
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
String query = "Select ȸ����ȣ, ���̵�, ��й�ȣ, �̸�, �μ��� from crmuserdb where ���̵� = ? and ��й�ȣ = ?";
		
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
				rs.getString("ȸ����ȣ");
				String id = rs.getString("���̵�");
				String password = rs.getString("��й�ȣ");
				String name = rs.getString("�̸�");
				String part = rs.getString("�μ���");
				
				emp = new EmployeeModel(id, password, name, part);
				return emp;
			} else {	
				throw new SQLException("���̵� ��й�ȣ�� Ʋ���ϴ�.");
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			connManager.close(conn, pstmt, rs);
		}
	}
	
	public EmployeeModel findByID(String empID) throws SQLException {
		String query = "Select ȸ����ȣ, ���̵�, ��й�ȣ, �̸�, �μ��� from crmuserdb where ���̵� = ?";
		
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
				rs.getString("ȸ����ȣ");
				String id = rs.getString("���̵�");
				String password = rs.getString("��й�ȣ");
				String name = rs.getString("�̸�");
				String part = rs.getString("�μ���");
				
				emp = new EmployeeModel(id, password, name, part);
				return emp;
			} else {	
				throw new SQLException("�������� �ʽ��ϴ�. : " + empID);
			}
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			connManager.close(conn, pstmt, rs);
		}
		
	}
	public EmployeeModel insertEmployee(EmployeeModel emp) throws SQLException {
		String insertQuery = "INSERT INTO crmuserdb (���̵�, ��й�ȣ, �̸�, �μ���)"
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
				throw new SQLException("���� ���� �Է¿� �����߽��ϴ�. : " + emp );
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
