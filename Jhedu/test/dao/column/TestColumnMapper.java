package dao.column;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestColumnMapper {

	private ColumnInfoLoader loader;

	@Before
	public void setUp() throws Exception {
		ClassLoader cloader = this.getClass().getClassLoader();
		InputStream in = cloader.getResourceAsStream("resources/column.config");
		loader = new ColumnInfoLoader();
		loader.loadColumns(in, "ms949");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test_loading() {		
		assertEquals(36, loader.getColumnSize());
	}
	
	@Test
	public void test_finding() {
		ColumnMapper mapper = loader.findColumnMapper("소스번호");
		assertEquals (0, mapper.getIndex());
		assertEquals ("int", mapper.getColumnType());
		
		mapper = loader.findColumnMapper("현금영수증발급");
		assertEquals ("boolean", mapper.getColumnType());
		
	}
	
	

}
