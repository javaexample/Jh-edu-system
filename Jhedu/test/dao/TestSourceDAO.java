package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import model.SourceModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestSourceDAO {

	SourceDAO dao  = null ;
	
	@Before
	public void setUp() throws Exception {
		dao = DAORegistry.getInstance().getSourceDAO();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Ignore  @Test
	public void test_find_by_ID() throws SQLException {
		SourceModel source = dao.getSourceById(2);
		assertEquals ("���̷���?", source.getSourceType());
	}
	
	@Ignore @Test
	public void test_find_by_state() throws SQLException {
		List<SourceModel> sources = dao.findBySourceState(new String[] { "Ȧ��", "����"} );
		
		assertEquals (12, sources.size());
		
	}
	
	@Ignore @Test
	public void test_find_source_by_type() throws SQLException {
		List<SourceModel> sources = dao.findBySourceType("�̸�", "�迵��");
		
		assertEquals(2, sources.size());
	}
	
	@Test
	public void test_find_by_empName() throws SQLException {
		List<SourceModel> sources = dao.getSource("�����", "����ö");
		
		assertEquals ( 162, sources.size());
	}
	
	
	@Test
	public void test_insert_new_source() throws SQLException {
		SourceModel source = new SourceModel();
		source.setSourceType("��ȭ");
		source.setWhenContact("2014-03-20");
		source.setContactTime("22:00");
		source.setHomePhone("02-444-4443");
		source.setCellPhone("001-4344-4443");
		source.setName("����");
		source.setGender("����");
		source.setInquiry("�� �Ǵ°�???");;
		
		dao.insertSource(source);
	}
	
	@Test
	public void test_duplicated_phone() throws SQLException {
		List<String> empList = dao.findSourcesByHomephone("02-6155-0604");
		assertEquals ( "����ö", empList.get(0));
		assertEquals(3, empList.size());
	}
	
	/*
	 * 16	
	 * �Ź�	
	 * 02/25	
	 * 14:46	
	 * NULL	
	 * 010-2912-9398	
	 * NULL	
	 * ����	
	 * NULL	
	 * kimcgn@naver.com	
	 * NULL	
	 * NULL	
	 * ����ö			
	 * 02/27	
	 * 2014-02-27 
	 * 5:33 PM 
	 * 
	 * �ٸ����������� �ϰԵ̴ٰ� �̾��ϴٰ��Ͻ� �ٸ�����̶� ���� ����ϰڴٰ��Ͻø鼭
2014-02-27 12:04 PM ������ȭ���, 4�ù��뿡 �����޶�2014-02-25 5:15 PM ��ȸ �ı��� ���� ���ؼ� ���� ������ ��� ���ϵ� ���о�ô�. �ʹ� ���ϰ� ���� �ѰŰ���. ���� ���� ��ȭ�شٴϱ� ��Ʋ���� �ð��ָ� �ȵǳ� �׷��� �˰ڴٰ� ���ϸ� ����11���� �����帰�ٰ� �����帲.
2014-02-25 2:51 PM1. ���ڹް� �������� 2. �������� 20% �ǳ��ؼ� 35�� �������� ������ȴ��� �ϰ�� �;��ϴ´�ģ�� �ʹ� ���ϰ� ��峪3. ���� ���ϸ� �ȴٰ� ���������
4. ���Ϸ� �ڷ� ������Ȱ� ���� �����غ��� 5���� �����帮�����

kimcgn@naver.com	���	01/00	NULL	2��	7	20%	NULL	��ۿϷ�	��������	ARS	�Ｚī��	NULL	NULL	2018	2����	NULL	NULL	��û	10125502
	 */
	@Test
	public void test_update_source() throws SQLException {
		SourceModel source = dao.getSourceById(16);
		
		source.setSourceType("x1");
		source.setWhenContact("x2");
		source.setContactTime("x3");
		source.setHomePhone("x4");
		source.setCellPhone("x5");
		source.setName("x6");
		source.setGender("x7");
		source.setAge("x8");
		source.setEmail("x9");
		source.setAddress("x10");
		source.setInquiry("x11");
		source.setChargedEmployee("x12");
		source.setRequiredDate("x13");
		source.setRequiredTime("x14");
		source.setSourceState("x15");
		source.setTextBookState("x16");
		source.setSettlementState("x17");
		source.setDueDate("x18");
		source.setExplanaryNote("x19");
		source.setOrderDate("x20");
		source.setSemesterCode("x21");
		source.setLevel("x22");
		source.setCourseCount("x23");
		source.setDiscountRate("x24");
		source.setRegistrationFee("x25");
		source.setSettlementType("x26");
		source.setCardType("x27");
		source.setCardNumber("x28");
		source.setMonthExpired("x29");
		source.setYearExpired("x30");
		source.setInstallmentMonths("x31");
		source.setBankName("x32");
		source.setCashReceiptRequired("x33");
		source.setSlipRequired("x34");
		source.setApprovalNum("x35");
		
		dao.updateSource(source);
		
		
		
	}

}
