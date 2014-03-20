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
	
	
	public List<SourceModel> findBySourceType(String searchColumn, String searchValue) throws SQLException {
		
		String sql = "select "
				
				+ "소스번호, 소스종류, 유입날짜, 유입시간, "
				+ "일반전화,휴대전화,이름, 성별, 나이, 이메일,주소,문의내용, 담당자,"
				+ "요망날짜, 요망시간, 소스상태, 교재상태, 결제상태,"
				+ "마감날짜, 비고, 오더일자, 기수, 급수, 과목수, 할인율, 등록금, 결제방법, 카드종류,"
				+ "카드번호, 유효기간월, 유효기간년, 할부개월, 은행명, 현금영수증발급, 카드전표발급, 승인번호 "
				
				+ "from crmdb where " + searchColumn 
				+ " = ? ";
		
		Connection conn = connManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		System.out.println("query : " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			pstmt.setString(1, searchValue);
			
			rs = pstmt.executeQuery();
			
			ArrayList<SourceModel> list = new ArrayList<SourceModel>();
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
	 * 담당자 이름별로 등록된 소스를 조회
	 * @param columnValue 담당자 이름
	 * @return
	 * @throws SQLException
	 */
	public List<SourceModel> getSource(String columnName, String columnValue) throws SQLException {
		
		
		String sql = "select "
				+ "소스번호, 소스종류, 유입날짜, 유입시간, "
				+ "일반전화,휴대전화,이름, 성별, 나이, 이메일,주소,문의내용, 담당자,"
				+ "요망날짜, 요망시간, 소스상태, 교재상태, 결제상태,"
				+ "마감날짜, 비고, 오더일자, 기수, 급수, 과목수, 할인율, 등록금, 결제방법, 카드종류,"
				+ "카드번호, 유효기간월, 유효기간년, 할부개월, 은행명, 현금영수증발급, 카드전표발급, 승인번호 "
				
//				+ "소스번호, 소스종류, 유입날짜, 유입시간, "
//				+ "일반전화,휴대전화,이름, 성별, 나이, 이메일,주소,문의내용, 담당자,"
//				+ "요망날짜, 요망시간, 소스상태, 교재상태, 결제상태 "
				+ "from crmdb " 
				+ "where " + columnName 
				+ " = ?" ;
		String clause0 = "where 담당자 = ?";
		String clause = "where 결제상태 = '승인요청'";
		String clause2 = "where 교재상태 = '배송대기'";
		
		Connection conn = connManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			pstmt.setString(1, columnValue);
			
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
		updateQuery += "소스종류 = ?, "; //1
		updateQuery += "유입날짜 = ?, "; 
		updateQuery += 	"유입시간 = ?, ";
		updateQuery += "일반전화 = ?, ";
		updateQuery += "휴대전화 = ?, "; // 5
		updateQuery += "이름 = ?, ";
		updateQuery += "성별 = ?, ";
		updateQuery += "나이 = ?, ";
		updateQuery += "이메일 = ?, ";
		updateQuery += "주소 = ?, ";  // 10
		updateQuery += "문의내용 = ?, ";
		updateQuery += "담당자 = ?,";
		updateQuery +=  "요망날짜 = ?, ";
		updateQuery += "요망시간 = ?, ";
		updateQuery += "소스상태 = ?, "; // 15
		updateQuery += "교재상태 = ?, ";
		updateQuery += "결제상태 = ?,";
		updateQuery +=  "마감날짜 = ?, ";
		updateQuery += "비고 = ?, ";
		updateQuery += "오더일자 = ?, "; // 20
		updateQuery += "기수 = ?, ";
		updateQuery += "급수 = ?, ";
		updateQuery += "과목수 = ?, ";
		updateQuery += "할인율 = ?, ";
		updateQuery += "등록금 = ?, "; // 25
		updateQuery += "결제방법 = ?, ";
		updateQuery += "카드종류 = ?, ";
		updateQuery += "카드번호 = ?, ";
		updateQuery += "유효기간월 = ?, ";
		updateQuery += "유효기간년 = ?, "; // 30
		updateQuery += "할부개월 = ?, ";
		updateQuery += "은행명 = ?, ";
		updateQuery += "현금영수증발급 = ?, ";
		updateQuery += "카드전표발급 = ?, ";
		updateQuery += "승인번호 = ? ";   // 35
		
		updateQuery += "WHERE 소스번호 = " + source.getId() ;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connManager.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(updateQuery);
			
			pstmt.setString(1, source.getSourceType());
			pstmt.setString(2, source.getWhenContact());
			pstmt.setString(3, source.getContactTime());
			pstmt.setString(4, source.getHomePhone());
			pstmt.setString(5, source.getCellPhone());
			pstmt.setString(6, source.getName());
			pstmt.setString(7, source.getGender());
			pstmt.setString(8, source.getAge());
			pstmt.setString(9, source.getEmail());
			pstmt.setString(10, source.getAddress());
			pstmt.setString(11, source.getInquiry());
			pstmt.setString(12, source.getChargedEmployee());
			pstmt.setString(13, source.getRequiredDate());
			pstmt.setString(14, source.getRequiredTime());
			pstmt.setString(15, source.getSourceState());
			pstmt.setString(16, source.getTextBookState());
			pstmt.setString(17, source.getSettlementState());
			pstmt.setString(18, source.getDueDate());
			pstmt.setString(19, source.getExplanaryNote());
			pstmt.setString(20, source.getOrderDate());
			pstmt.setString(21, source.getSemesterCode());
			pstmt.setString(22, source.getLevel());
			pstmt.setString(23, source.getCourseCount());
			pstmt.setString(24, source.getDiscountRate());
			pstmt.setString(25, source.getRegistrationFee());
			pstmt.setString(26, source.getSettlementType());
			pstmt.setString(27, source.getCardType());
			pstmt.setString(28, source.getCardNumber());
			pstmt.setString(29, source.getMonthExpired());
			pstmt.setString(30, source.getYearExpired());
			pstmt.setString(31, source.getInstallmentMonths());
			pstmt.setString(32, source.getBankName());
			pstmt.setString(33, source.getCashReceiptRequired());
			pstmt.setString(34, source.getSlipRequired());
			pstmt.setString(35, source.getApprovalNum());
//			pstmt.setString(4, "");
//			pstmt.setString(4, "");
//			pstmt.setString(4, "");
//			pstmt.setString(4, "");
			
			int numOfChanged = pstmt.executeUpdate();
			
			if ( numOfChanged != 1) {
				throw new SQLException("변경 실패했습니다. : " + source);
			}
			
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			connManager.close(conn, pstmt, null) ;
		}
		
	}
	
	
	/**
	 * 새로운 소스를 입력하는 쿼리
	 * 
	 * @param newSource
	 * @return
	 * @throws SQLException
	 */
	public SourceModel insertSource(SourceModel newSource) throws SQLException {

		// TODO SourceEditPanel 에서 편집할때 호출되는 부분
		//      column 전부 나열해줘야 함.
		
		String insertQuery = "INSERT INTO crmdb  "
				+ "( 소스종류, 유입날짜, 유입시간, 일반전화, 휴대전화, 이름, 성별, 문의내용 ) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?) "
				+ " select @@Identity";
		
		Connection conn = connManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(insertQuery);
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			
			sourceToStatement(newSource, pstmt);
			
			int numOfChanged = pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			
			if ( rs.next()) {
				newSource.setId(rs.getInt(1));
			}
			
			
			if ( numOfChanged != 1) {
				throw new SQLException("소스 생성에 실패했습니다. : " + newSource );
			}
			
			conn.commit();
			return newSource ;
			
		} catch (SQLException e) {
			conn.rollback();
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
		
		
		
		String query = "SELECT "
				+ "소스번호, 소스종류, 유입날짜, 유입시간, "
				+ "일반전화,휴대전화,이름, 성별, 나이, 이메일,주소,문의내용, 담당자,"
				+ "요망날짜, 요망시간, 소스상태, 교재상태, 결제상태,"
				+ "마감날짜, 비고, 오더일자, 기수, 급수, 과목수, 할인율, 등록금, 결제방법, 카드종류,"
				+ "카드번호, 유효기간월, 유효기간년, 할부개월, 은행명, 현금영수증발급, 카드전표발급, 승인번호 "
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
		
		
		source.setDueDate(	rs.getString("마감날짜") );
		source.setExplanaryNote(rs.getString("비고") );
		source.setOrderDate(rs.getString("오더일자") );
		source.setSemesterCode(rs.getString("기수"));
		source.setLevel(rs.getString("급수"));
		source.setCourseCount(rs.getString("과목수"));
		source.setDiscountRate(rs.getString("할인율"));
		source.setRegistrationFee(rs.getString("등록금"));
		source.setSettlementType(rs.getString( "결제방법"));
		source.setCardType(rs.getString("카드종류"));
		source.setCardNumber(rs.getString("카드번호"));
		source.setMonthExpired(rs.getString("유효기간월"));
		source.setYearExpired(rs.getString("유효기간년"));
		source.setInstallmentMonths(rs.getString( "할부개월"));
		source.setBankName(rs.getString("은행명"));
		source.setCashReceiptRequired(rs.getString("현금영수증발급"));
		source.setSlipRequired(rs.getString("카드전표발급"));
		source.setApprovalNum(rs.getString("승인번호"));
		
		return source;
	}
	
	private void sourceToStatement(SourceModel source, PreparedStatement pstmt) throws SQLException {
		// TODO  
//		source.setSourceType("전화");
//		source.setWhenContact("2014-03-20");
//		source.setContactTime("22:00");
//		source.setHomePhone("02-444-4443");
//		source.setCellPhone("001-4344-4443");
//		source.setName("김김김");
//		source.setGender("남자");
//		source.setInquiry("잘 되는가???");;
		//( 소스종류, 유입날짜, 유입시간, 일반전화, 휴대전화, 이름, 성별, 문의내용 ) values (?, ?, ?, ?, ?, ?, ?, ?)";
		pstmt.setString(1, source.getSourceType());
		pstmt.setString(2, source.getWhenContact());
		pstmt.setString(3, source.getContactTime());
		pstmt.setString(4, source.getHomePhone());
		pstmt.setString(5, source.getCellPhone());
		pstmt.setString(6, source.getName());
		pstmt.setString(7, source.getGender());
		pstmt.setString(8, source.getInquiry());
	}


	/**
	 * 일반 전화로 담당자 검색
	 * @param homePhone
	 * @return
	 * @throws SQLException
	 */
	public List<String> findSourcesByHomephone(String homePhone) throws SQLException {
		String query = "select 담당자  from crmdb "
				+ "where 일반전화 = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = connManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, homePhone);
			
			rs = pstmt.executeQuery();
		
			List<String> empList = new ArrayList<>();
			while ( rs.next()) {
				empList.add(rs.getString(1));
			}
			
			return empList;
			
			
		} catch (SQLException e) {
			throw e;
		} finally {
			connManager.close(conn, pstmt, rs);
		}
	}
	
	/**
	 * 휴대 전화로 담당자 검색
	 * @param cellPhone
	 * @return
	 * @throws SQLException
	 */
	public List<String> findSourcesByCellphone(String cellPhone) throws SQLException {
		String query = "select 담당자  from crmdb "
				+ "where 휴대전화 = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = connManager.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, cellPhone);
			
			rs = pstmt.executeQuery();
		
			List<String> empList = new ArrayList<>();
			while ( rs.next()) {
				empList.add(rs.getString(1));
			}
			
			return empList;
			
			
		} catch (SQLException e) {
			throw e;
		} finally {
			connManager.close(conn, pstmt, rs);
		}
	}
	
//	public void updateSource(SourceModel source) throws SQLException {
//		
//		
//	}
	
}
