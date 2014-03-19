package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.SourceModel;

public class SourceDAO {

	private ConnManager connManager ;
	public SourceDAO(ConnManager manager ) throws ClassNotFoundException, SQLException {
		
		this.connManager = manager;
	}
	
	
	public List<SourceModel> findBySourceType(String sourceType) throws SQLException {
		
		String sql = "select �ҽ���ȣ, �ҽ�����, ���Գ�¥ from crmdb where �ҽ����� = ? ";
		
		Connection conn = connManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			pstmt.setString(1, sourceType);
			
			rs = pstmt.executeQuery();
			
			ArrayList<SourceModel> list = new ArrayList<SourceModel>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String type = rs.getString(2);
				String whenContacted = rs.getString(3);
				
				SourceModel aSource = new SourceModel(id, type, whenContacted);
				
				list.add(aSource);
			}
			return list;
			
		} catch (SQLException e) {
			throw e;
		} finally {
			connManager.close(conn, pstmt, rs);
		}
	}
	
	public List<SourceModel> getSource() throws SQLException {
		
		
		String sql = "select �ҽ���ȣ, �ҽ�����, ���Գ�¥ from crmdb";
		
		Connection conn = connManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = pstmt.executeQuery();
			
			ArrayList<SourceModel> list = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String type = rs.getString(2);
				String whenContacted = rs.getString(3);
				
				SourceModel aSource = new SourceModel(id, type, whenContacted);
				
				list.add(aSource);
			}
			return list;
			
		} catch (SQLException e) {
			throw e;
		} finally {
			connManager.close(conn, pstmt, rs);
		}		
	}
	
	/**
	 * �ҽ��� ������Ʈ�ϴ� �޼ҵ�
	 * @param source
	 */
	public void updateSource(SourceModel source) throws SQLException {
		String updateQuery = "UPDATE crmdb SET ";
		updateQuery += "�ҽ����� = ?, "; 
		updateQuery += "���Գ�¥ = ? ";
		updateQuery += "WHERE �ҽ���ȣ = " + source.getId() ;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connManager.getConnection();
			pstmt = conn.prepareStatement(updateQuery);
			
			pstmt.setString(1, source.getSourceType());
			pstmt.setString(2, source.getWhenContact());
			
			int numOfChanged = pstmt.executeUpdate();
			
			if ( numOfChanged != 1) {
				throw new SQLException("���� �����߽��ϴ�. : " + source);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			connManager.close(conn, pstmt, null) ;
		}
		
	}
	
	public SourceModel insertSource(SourceModel newSource) throws SQLException {

		String insertQuery = "INSERT INTO crmdb  ( �ҽ���ȣ, �ҽ�����, ���Գ�¥) values (?, ?, ?)";
		
		Connection conn = connManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(insertQuery);
		
		try {
			
			pstmt.setInt(1, newSource.getId());
			pstmt.setString(2, newSource.getSourceType());
			pstmt.setString(3, newSource.getWhenContact());
			
			int numOfChanged = pstmt.executeUpdate();
			
			if ( numOfChanged != 1) {
				throw new SQLException("�ҽ� ������ �����߽��ϴ�. : " + newSource );
			}
			
			return newSource ;
			
		} catch (SQLException e) {
			throw e;
		} finally {
			connManager.close(conn, pstmt, null);
		}
		
		
	}


	public SourceModel getSourceById(int id) throws SQLException {
		
		String query = " SELECT * FROM crmdb WHERE �ҽ���ȣ = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
			conn = connManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			if ( rs.next() ) {
				SourceModel source = rsToSource(rs);
				return source;
			} else {
				throw new SQLException("�������� �ʴ� ȸ�� ��ȣ : " + id);
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			connManager.close(conn, pstmt, rs);
		}
		
	}
	
	/**
	 * �ҽ� ���º��� ��ȸ�ϱ�
	 * 
	 * @param states - Ȧ��, ����, ����, ��� �� �� �ϳ���
	 * @return
	 */
	public List<SourceModel> findBySourceState(String [] states) throws SQLException {
		
		String query = "SELECT �ҽ���ȣ, �ҽ�����, ���Գ�¥ FROM crmdb ";
		
		query += "WHERE " ; 
		for (int i = 0; i < states.length; i++) {
			query += "�ҽ����� = ? ";
			if ( i < states.length -1) {
				query += "or ";
			}
		}

		Connection conn = null ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = connManager.getConnection();
			pstmt = conn.prepareStatement(query);
			 
			for (int i = 0; i < states.length; i++) {
				pstmt.setString(i+1, states[i]);
			}
			
			ArrayList<SourceModel> sourceList = new ArrayList<>();
			
			rs = pstmt.executeQuery();
			
			while ( rs.next() ){
				sourceList.add(rsToSource(rs));
			}
			return sourceList;
			
		} catch (SQLException e) {
			throw e;
		} finally {
			connManager.close(conn, pstmt, rs);
		}
	}
	
	
	private SourceModel rsToSource(ResultSet rs) throws SQLException {
		
		int id = rs.getInt("�ҽ���ȣ");
		String type = rs.getString("�ҽ�����");
		String date = rs.getString("���Գ�¥");
		SourceModel source = new SourceModel(id, type, date);
		
		return source;
	}
	
	private void sourceToStatement(SourceModel source, PreparedStatement pstmt) throws SQLException {
		// TODO  
//		pstmt.setInt(1, source.getId());
//		pstmt.setString(2, source.getSourceType());
//		pstmt.setString(3, source.getWhenContact());
	}
}
