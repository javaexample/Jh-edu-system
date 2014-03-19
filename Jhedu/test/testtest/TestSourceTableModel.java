package testtest;

import static org.junit.Assert.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import model.SourceModel;
import model.SourceTableModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSourceTableModel implements TableModelListener {

	boolean called = false;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_row_delet() {
		SourceTableModel model = new SourceTableModel(new String[]{"A", "B", "C"});
	
		model.addRow(new SourceModel(0, "tt", "2012"));
		model.addRow(new SourceModel(1, "tt", "2012"));
		model.addRow(new SourceModel(2, "tt", "2012"));
		
		model.addTableModelListener(this);
		
		model.removeRow(1);
		
		assertTrue("called ", called);
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		assertEquals ( 1, e.getFirstRow());
		assertEquals ( TableModelEvent.DELETE, e.getType());
		called = true; 
	}
	
	@Test
	public void test_delete_all_sources() {
		final SourceTableModel model = new SourceTableModel(new String[]{"A", "B", "C"});
		
		model.addRow(new SourceModel(0, "tt", "2012"));
		model.addRow(new SourceModel(1, "tt", "2012"));
		model.addRow(new SourceModel(2, "tt", "2012"));
	
		model.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				assertEquals ( 0, e.getFirstRow());
				assertEquals ( 3, e.getLastRow());
				
				assertEquals ( 0, model.getRowCount() );
			}
		});
		model.clearSources();
		
		
	}
	
}
