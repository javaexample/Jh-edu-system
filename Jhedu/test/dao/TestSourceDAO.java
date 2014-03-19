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

	@Ignore @Test
	public void test_insert () throws ClassNotFoundException, SQLException {
		
		SourceModel newSource = new SourceModel(50, "dd", "2012");
		
		dao.insertSource(newSource);
		
		SourceModel sourceAdded = dao.getSourceById(newSource.getId() ) ;
		
		assertEquals(newSource, sourceAdded);
		
	}
	
	@Ignore @Test
	public void test_find_by_ID() throws SQLException {
		SourceModel source = dao.getSourceById(2);
		assertEquals ("¿ÖÀÌ·¯Áö?", source.getSourceType());
	}
	
	@Test
	public void test_find_by_state() throws SQLException {
		List<SourceModel> sources = dao.findBySourceState(new String[] { "È¦µù", "°¡¸Á"} );
		
		assertEquals (12, sources.size());
		
	}
	
	@Test
	public void test_find_source_by_type() throws SQLException {
		List<SourceModel> sources = dao.findBySourceType("ÀÌ¸§", "±è¿µÀç");
		
		assertEquals(2, sources.size());
	}

}
