package model;

import javax.swing.table.AbstractTableModel;

/**
 * �н� �������� table model �����Դϴ�.
 * 
 * @author Youngjae
 *
 */
public class TeamSupportTableModel extends AbstractTableModel {

	private SourceTableModel globalModel ;
	public TeamSupportTableModel(SourceTableModel globalModel ) {
		this.globalModel = globalModel ;
	}
	
	@Override
	public int getRowCount() {
		return globalModel.getRowCount();
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	private int computeColumnIndex(int index) {
		int globalIndex = 7 + index ;
		
		return globalIndex ;
		
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		int indexAtGlobalModel = computeColumnIndex(columnIndex) ;
		return indexAtGlobalModel;
	}

}
