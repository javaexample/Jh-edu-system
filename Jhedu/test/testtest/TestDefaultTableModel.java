package testtest;

import static org.junit.Assert.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

public class TestDefaultTableModel {

	
	@Test
	public void test() {
		DefaultTableModel model = new DefaultTableModel();
		
		
		model.addTableModelListener(new TestListener());

		model.addRow(new Object[] {"000", "kim"});
		
	}
	
	static class TestListener implements TableModelListener{

		@Override
		public void tableChanged(TableModelEvent e) {
			System.out.println(e.getColumn() + ", " + e.getFirstRow() + ", " + e.getSource());
		}
		
	}


}
