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
		
		String sql = "select 소스번호, 소스종류, 유입날짜 from crmdb where 소스종류 = ? ";
		
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
		
		
		String sql = "select 소스번호, 소스종류, 유입날짜, 유입시간, "
				+ "일반전화,휴대전화,이름, 성별, 나이, 이메일,주소,문의내용, 담당자,"
				+ "요망날짜, 요망시간, 소스상태, 교재상태, 결제상태 "
				+ "from crmdb";
		
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
				SourceModel aSource = rsToSource(rs);
				
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
	 * 소스를 업데이트하는 메소드
	 * @param source
	 */
	public void updateSource(SourceModel source) throws SQLException {
		String updateQuery = "UPDATE crmdb SET ";
		updateQuery += "소스종류 = ?, "; 
		updateQuery += "유입날짜 = ? ";
		updateQuery += "WHERE 소스번호 = " + source.getId() ;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connManager.getConnection();
			pstmt = conn.prepareStatement(updateQuery);
			
			pstmt.setString(1, source.getSourceType());
			pstmt.setString(2, source.getWhenContact());
			
			int numOfChanged = pstmt.executeUpdate();
			
			if ( numOfChanged != 1) {
				throw new SQLException("변경 실패했습니다. : " + source);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			connManager.close(conn, pstmt, null) ;
		}
		
	}
	
	public SourceModel insertSource(SourceModel newSource) throws SQLException {

		String insertQuery = "INSERT INTO crmdb  ( 소스번호, 소스종류, 유입날짜) values (?, ?, ?)";
		
		Connection conn = connManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(insertQuery);
		
		try {
			
			pstmt.setInt(1, newSource.getId());
			pstmt.setString(2, newSource.getSourceType());
			pstmt.setString(3, newSource.getWhenContact());
			
			int numOfChanged = pstmt.executeUpdate();
			
			if ( numOfChanged != 1) {
				throw new SQLException("소스 생성에 실패했습니다. : " + newSource );
			}
			
			return newSource ;
			
		} catch (SQLException e) {
			throw e;
		} finally {
			connManager.close(conn, pstmt, null);
		}
		
		
	}


	public SourceModel getSourceById(int id) throws SQLException {
		
		String query = " SELECT * FROM crmdb WHERE 소스번호 = ?";
		
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
				throw new SQLException("존재하지 않는 회원 번호 : " + id);
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			connManager.close(conn, pstmt, rs);
		}
		
	}
	
	/**
	 * 소스 상태별로 조회하기
	 * 
	 * @param states - 홀딩, 가망, 오더, 폐기 들 중 하나씩
	 * @return
	 */
	public List<SourceModel> findBySourceState(String [] states) throws SQLException {
		
		
		
		String query = "SELECT 소스번호, 소스종류, 유입날짜, 유입시간, "
				+ "일반전화,휴대전화,이름, 성별, 나이, 이메일,주소,문의내용, 담당자,"
				+ "요망날짜, 요망시간, 소스상태, 교재상태, 결제상태 "
				+ "FROM crmdb ";
		
		query += "WHERE " ; 
		for (int i = 0; i < states.length; i++) {
			query += "소스상태 = ? ";
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
	
		int id = rs.getInt("소스번호");
		String type = rs.getString("소스종류");
		String date = rs.getString("유입날짜");
		SourceModel source = new SourceModel(id, type, date);
		
		String time = rs.getString("유입시간");
		source.setContactTime(time);
		
		String phone0 = rs.getString("일반전화");
		source.setHomePhone(phone0);
		
		String phone1 = rs.getString("휴대전화");
		source.setCellPhone(phone1);
		
		String name = rs.getString("이름");
		source.setName(name);
		
		String gender = rs.getString("성별");
		source.setGender(gender);
		
		String age = rs.getString("나이");
		source.setAge(age);
		
		/*
		 * "select 소스번호, 소스종류, 유입날짜, 
		 * 
		 * 유입시간, "
				+ "일반전화,휴대전화,이름, 성별, 나이, 이메일,주소,문의내용, 담당자,"
				+ "요망날짜, 요망시간, 소스상태, 교재상태, 결제상태 "
				+ "from crmdb";
		 */
		
		String email = rs.getString("이메일");
		source.setEmail( email );
		
		String address = rs.getString("주소");
		source.setAddress(address);
		
		String inquiry = rs.getString("문의내용");
		source.setInquiry(inquiry);
		
		String chargedEmp = rs.getString("담당자");
		source.setChargedEmployee(chargedEmp);
		
		String date0 = rs.getString("요망날짜");
		source.setRequiredDate(date0);
		
		String date1 = rs.getString("요망시간");
		source.setRequiredTime(date1);
		
		String sourceState = rs.getString("소스상태");
		source.setSourceState(sourceState);
		
		String textBookState = rs.getString("교재상태");
		source.setTextBookState(textBookState);
		
		String settlmentSate = rs.getString("결제상태");
		source.setSettlementState(settlmentSate);
		
		return source;
	}
	
	private void sourceToStatement(SourceModel source, PreparedStatement pstmt) throws SQLException {
		// TODO  
//		pstmt.setInt(1, source.getId());
//		pstmt.setString(2, source.getSourceType());
//		pstmt.setString(3, source.getWhenContact());
	}
}
