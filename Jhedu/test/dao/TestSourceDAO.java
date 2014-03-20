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
		assertEquals ("왜이러지?", source.getSourceType());
	}
	
	@Ignore @Test
	public void test_find_by_state() throws SQLException {
		List<SourceModel> sources = dao.findBySourceState(new String[] { "홀딩", "가망"} );
		
		assertEquals (12, sources.size());
		
	}
	
	@Ignore @Test
	public void test_find_source_by_type() throws SQLException {
		List<SourceModel> sources = dao.findBySourceType("이름", "김영재");
		
		assertEquals(2, sources.size());
	}
	
	@Test
	public void test_find_by_empName() throws SQLException {
		List<SourceModel> sources = dao.getSource("담당자", "윤병철");
		
		assertEquals ( 162, sources.size());
	}
	
	
	@Test
	public void test_insert_new_source() throws SQLException {
		SourceModel source = new SourceModel();
		source.setSourceType("전화");
		source.setWhenContact("2014-03-20");
		source.setContactTime("22:00");
		source.setHomePhone("02-444-4443");
		source.setCellPhone("001-4344-4443");
		source.setName("김김김");
		source.setGender("남자");
		source.setInquiry("잘 되는가???");;
		
		dao.insertSource(source);
	}
	
	@Test
	public void test_duplicated_phone() throws SQLException {
		List<String> empList = dao.findSourcesByHomephone("02-6155-0604");
		assertEquals ( "윤병철", empList.get(0));
		assertEquals(3, empList.size());
	}
	
	/*
	 * 16	
	 * 신문	
	 * 02/25	
	 * 14:46	
	 * NULL	
	 * 010-2912-9398	
	 * NULL	
	 * 남자	
	 * NULL	
	 * kimcgn@naver.com	
	 * NULL	
	 * NULL	
	 * 윤병철			
	 * 02/27	
	 * 2014-02-27 
	 * 5:33 PM 
	 * 
	 * 다른교육원에서 하게됫다고 미안하다고하심 다른사람이랑 같이 등록하겠다고하시면서
2014-02-27 12:04 PM 지금통화곤란, 4시반쯤에 연락달라2014-02-25 5:15 PM 교회 식구가 상을 당해서 지금 정신이 없어서 메일도 못읽어봤다. 너무 급하게 댕기긴 한거같음. 내일 오전 전화준다니까 이틀정도 시간주면 안되냐 그래서 알겠다고 내일모래 오전11시쯤 연락드린다고 말씀드림.
2014-02-25 2:51 PM1. 문자받고 연락오심 2. 언제까지 20% 되냐해서 35명 한정으로 말씀드렸더니 하고는 싶어하는눈친데 너무 급하게 당겼나3. 오늘 당일만 된다고 말씀드렸음
4. 메일로 자료 보내드렸고 내용 검토해보고 5시쯤 연락드리기로함

kimcgn@naver.com	폐기	01/00	NULL	2급	7	20%	NULL	배송완료	결제승인	ARS	삼성카드	NULL	NULL	2018	2개월	NULL	NULL	요청	10125502
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
