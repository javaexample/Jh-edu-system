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
				
				+ "�ҽ���ȣ, �ҽ�����, ���Գ�¥, ���Խð�, "
				+ "�Ϲ���ȭ,�޴���ȭ,�̸�, ����, ����, �̸���,�ּ�,���ǳ���, �����,"
				+ "�����¥, ����ð�, �ҽ�����, �������, ��������,"
				+ "������¥, ���, ��������, ���, �޼�, �����, ������, ��ϱ�, �������, ī������,"
				+ "ī���ȣ, ��ȿ�Ⱓ��, ��ȿ�Ⱓ��, �Һΰ���, �����, ���ݿ������߱�, ī����ǥ�߱�, ���ι�ȣ "
				
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
	 * ����� �̸����� ��ϵ� �ҽ��� ��ȸ
	 * @param columnValue ����� �̸�
	 * @return
	 * @throws SQLException
	 */
	public List<SourceModel> getSource(String columnName, String columnValue) throws SQLException {
		
		
		String sql = "select "
				+ "�ҽ���ȣ, �ҽ�����, ���Գ�¥, ���Խð�, "
				+ "�Ϲ���ȭ,�޴���ȭ,�̸�, ����, ����, �̸���,�ּ�,���ǳ���, �����,"
				+ "�����¥, ����ð�, �ҽ�����, �������, ��������,"
				+ "������¥, ���, ��������, ���, �޼�, �����, ������, ��ϱ�, �������, ī������,"
				+ "ī���ȣ, ��ȿ�Ⱓ��, ��ȿ�Ⱓ��, �Һΰ���, �����, ���ݿ������߱�, ī����ǥ�߱�, ���ι�ȣ "
				
//				+ "�ҽ���ȣ, �ҽ�����, ���Գ�¥, ���Խð�, "
//				+ "�Ϲ���ȭ,�޴���ȭ,�̸�, ����, ����, �̸���,�ּ�,���ǳ���, �����,"
//				+ "�����¥, ����ð�, �ҽ�����, �������, �������� "
				+ "from crmdb " 
				+ "where " + columnName 
				+ " = ?" ;
		String clause0 = "where ����� = ?";
		String clause = "where �������� = '���ο�û'";
		String clause2 = "where ������� = '��۴��'";
		
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
	 * �ҽ��� ������Ʈ�ϴ� �޼ҵ�
	 * @param source
	 */
	public void updateSource(SourceModel source) throws SQLException {
		String updateQuery = "UPDATE crmdb SET ";
		updateQuery += "�ҽ����� = ?, "; //1
		updateQuery += "���Գ�¥ = ?, "; 
		updateQuery += 	"���Խð� = ?, ";
		updateQuery += "�Ϲ���ȭ = ?, ";
		updateQuery += "�޴���ȭ = ?, "; // 5
		updateQuery += "�̸� = ?, ";
		updateQuery += "���� = ?, ";
		updateQuery += "���� = ?, ";
		updateQuery += "�̸��� = ?, ";
		updateQuery += "�ּ� = ?, ";  // 10
		updateQuery += "���ǳ��� = ?, ";
		updateQuery += "����� = ?,";
		updateQuery +=  "�����¥ = ?, ";
		updateQuery += "����ð� = ?, ";
		updateQuery += "�ҽ����� = ?, "; // 15
		updateQuery += "������� = ?, ";
		updateQuery += "�������� = ?,";
		updateQuery +=  "������¥ = ?, ";
		updateQuery += "��� = ?, ";
		updateQuery += "�������� = ?, "; // 20
		updateQuery += "��� = ?, ";
		updateQuery += "�޼� = ?, ";
		updateQuery += "����� = ?, ";
		updateQuery += "������ = ?, ";
		updateQuery += "��ϱ� = ?, "; // 25
		updateQuery += "������� = ?, ";
		updateQuery += "ī������ = ?, ";
		updateQuery += "ī���ȣ = ?, ";
		updateQuery += "��ȿ�Ⱓ�� = ?, ";
		updateQuery += "��ȿ�Ⱓ�� = ?, "; // 30
		updateQuery += "�Һΰ��� = ?, ";
		updateQuery += "����� = ?, ";
		updateQuery += "���ݿ������߱� = ?, ";
		updateQuery += "ī����ǥ�߱� = ?, ";
		updateQuery += "���ι�ȣ = ? ";   // 35
		
		updateQuery += "WHERE �ҽ���ȣ = " + source.getId() ;
		
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
				throw new SQLException("���� �����߽��ϴ�. : " + source);
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
	 * ���ο� �ҽ��� �Է��ϴ� ����
	 * 
	 * @param newSource
	 * @return
	 * @throws SQLException
	 */
	public SourceModel insertSource(SourceModel newSource) throws SQLException {

		// TODO SourceEditPanel ���� �����Ҷ� ȣ��Ǵ� �κ�
		//      column ���� ��������� ��.
		
		String insertQuery = "INSERT INTO crmdb  "
				+ "( �ҽ�����, ���Գ�¥, ���Խð�, �Ϲ���ȭ, �޴���ȭ, �̸�, ����, ���ǳ��� ) "
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
				throw new SQLException("�ҽ� ������ �����߽��ϴ�. : " + newSource );
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
		
		
		
		String query = "SELECT "
				+ "�ҽ���ȣ, �ҽ�����, ���Գ�¥, ���Խð�, "
				+ "�Ϲ���ȭ,�޴���ȭ,�̸�, ����, ����, �̸���,�ּ�,���ǳ���, �����,"
				+ "�����¥, ����ð�, �ҽ�����, �������, ��������,"
				+ "������¥, ���, ��������, ���, �޼�, �����, ������, ��ϱ�, �������, ī������,"
				+ "ī���ȣ, ��ȿ�Ⱓ��, ��ȿ�Ⱓ��, �Һΰ���, �����, ���ݿ������߱�, ī����ǥ�߱�, ���ι�ȣ "
				+ "FROM crmdb ";
		
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
		
		String time = rs.getString("���Խð�");
		source.setContactTime(time);
		
		String phone0 = rs.getString("�Ϲ���ȭ");
		source.setHomePhone(phone0);
		
		String phone1 = rs.getString("�޴���ȭ");
		source.setCellPhone(phone1);
		
		String name = rs.getString("�̸�");
		source.setName(name);
		
		String gender = rs.getString("����");
		source.setGender(gender);
		
		String age = rs.getString("����");
		source.setAge(age);
		
		String email = rs.getString("�̸���");
		source.setEmail( email );
		
		String address = rs.getString("�ּ�");
		source.setAddress(address);
		
		String inquiry = rs.getString("���ǳ���");
		source.setInquiry(inquiry);
		
		String chargedEmp = rs.getString("�����");
		source.setChargedEmployee(chargedEmp);
		
		String date0 = rs.getString("�����¥");
		source.setRequiredDate(date0);
		
		String date1 = rs.getString("����ð�");
		source.setRequiredTime(date1);
		
		String sourceState = rs.getString("�ҽ�����");
		source.setSourceState(sourceState);
		
		String textBookState = rs.getString("�������");
		source.setTextBookState(textBookState);
		
		String settlmentSate = rs.getString("��������");
		source.setSettlementState(settlmentSate);
		
		
		source.setDueDate(	rs.getString("������¥") );
		source.setExplanaryNote(rs.getString("���") );
		source.setOrderDate(rs.getString("��������") );
		source.setSemesterCode(rs.getString("���"));
		source.setLevel(rs.getString("�޼�"));
		source.setCourseCount(rs.getString("�����"));
		source.setDiscountRate(rs.getString("������"));
		source.setRegistrationFee(rs.getString("��ϱ�"));
		source.setSettlementType(rs.getString( "�������"));
		source.setCardType(rs.getString("ī������"));
		source.setCardNumber(rs.getString("ī���ȣ"));
		source.setMonthExpired(rs.getString("��ȿ�Ⱓ��"));
		source.setYearExpired(rs.getString("��ȿ�Ⱓ��"));
		source.setInstallmentMonths(rs.getString( "�Һΰ���"));
		source.setBankName(rs.getString("�����"));
		source.setCashReceiptRequired(rs.getString("���ݿ������߱�"));
		source.setSlipRequired(rs.getString("ī����ǥ�߱�"));
		source.setApprovalNum(rs.getString("���ι�ȣ"));
		
		return source;
	}
	
	private void sourceToStatement(SourceModel source, PreparedStatement pstmt) throws SQLException {
		// TODO  
//		source.setSourceType("��ȭ");
//		source.setWhenContact("2014-03-20");
//		source.setContactTime("22:00");
//		source.setHomePhone("02-444-4443");
//		source.setCellPhone("001-4344-4443");
//		source.setName("����");
//		source.setGender("����");
//		source.setInquiry("�� �Ǵ°�???");;
		//( �ҽ�����, ���Գ�¥, ���Խð�, �Ϲ���ȭ, �޴���ȭ, �̸�, ����, ���ǳ��� ) values (?, ?, ?, ?, ?, ?, ?, ?)";
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
	 * �Ϲ� ��ȭ�� ����� �˻�
	 * @param homePhone
	 * @return
	 * @throws SQLException
	 */
	public List<String> findSourcesByHomephone(String homePhone) throws SQLException {
		String query = "select �����  from crmdb "
				+ "where �Ϲ���ȭ = ? ";
		
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
	 * �޴� ��ȭ�� ����� �˻�
	 * @param cellPhone
	 * @return
	 * @throws SQLException
	 */
	public List<String> findSourcesByCellphone(String cellPhone) throws SQLException {
		String query = "select �����  from crmdb "
				+ "where �޴���ȭ = ? ";
		
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
