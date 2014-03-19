package model.role;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import dao.column.ColumnInfoLoader;
import dao.column.ColumnMapper;

public class TestRoleMapper {
	
	ColumnInfoLoader columnLoader;
	RoleMapper mapper;
	InputStream in;
	String charset = "ms949";
	
	@Before
	public void setUp() throws Exception {
		ClassLoader clsLoader = this.getClass().getClassLoader();
		
		columnLoader = new ColumnInfoLoader();
		columnLoader.loadColumns(
				clsLoader.getResourceAsStream("resources/column.config"),
				charset);
		
		mapper = new RoleMapper(columnLoader);		
		this.in = clsLoader.getResourceAsStream("resources/role.xml");
		mapper.loadRoles(in, charset );
	}

	@After
	public void tearDown() throws Exception {
		
		in.close();
	}

	@Test
	public void test_init_roles() throws ParserConfigurationException, SAXException, IOException {
		
		ColumnAccess acc = mapper.getColumn("관리자", "소스번호");
		assertFalse ( acc.isWritable());
		
		assertTrue ( mapper.getColumn("학습지원", "교재상태").isWritable() );
		assertFalse ( mapper.getColumn("학습지원", "담당자").isWritable() );
		
		assertTrue ( mapper.getColumn("회계", "결제상태").isWritable() );
		assertTrue ( mapper.getColumn("회계", "승인번호").isWritable() );
		assertFalse ( mapper.getColumn("회계", "기수").isWritable() );
	}
	
	/**
	 * 학습 운영팀 컬럼 정보
	 */
	@Test
	public void test_accessible_columns_for_TEAM_MANAGE() {
		
		String [] columns = ("소스번호,소스종류,유입날짜,유입시간" +
				",일반전화,휴대전화,이름,성별,나이,이메일,주소" +
				",문의내용,담당자,요망날짜,요망시간," +
				"교재상태,결제상태").split(",");
		
		for (int i = 0; i < columns.length; i++) {
			assertTrue (mapper.isAccessible("학습운영", columns[i]));
		}
		
		assertTrue ( mapper.isAccessible("학습운영", "교재상태"));
	}
	
	
}
