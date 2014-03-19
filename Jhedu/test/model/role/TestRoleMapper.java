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
		
		ColumnAccess acc = mapper.getColumn("������", "�ҽ���ȣ");
		assertFalse ( acc.isWritable());
		
		assertTrue ( mapper.getColumn("�н�����", "�������").isWritable() );
		assertFalse ( mapper.getColumn("�н�����", "�����").isWritable() );
		
		assertTrue ( mapper.getColumn("ȸ��", "��������").isWritable() );
		assertTrue ( mapper.getColumn("ȸ��", "���ι�ȣ").isWritable() );
		assertFalse ( mapper.getColumn("ȸ��", "���").isWritable() );
	}
	
	/**
	 * �н� ��� �÷� ����
	 */
	@Test
	public void test_accessible_columns_for_TEAM_MANAGE() {
		
		String [] columns = ("�ҽ���ȣ,�ҽ�����,���Գ�¥,���Խð�" +
				",�Ϲ���ȭ,�޴���ȭ,�̸�,����,����,�̸���,�ּ�" +
				",���ǳ���,�����,�����¥,����ð�," +
				"�������,��������").split(",");
		
		for (int i = 0; i < columns.length; i++) {
			assertTrue (mapper.isAccessible("�н��", columns[i]));
		}
		
		assertTrue ( mapper.isAccessible("�н��", "�������"));
	}
	
	
}
