package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class SourceTableModel extends AbstractTableModel {
	
	private String [] columnNames;
	
	private ArrayList<SourceModel> dataList ;
	
	public SourceTableModel (String [] columnNames, Vector values) {
		this.columnNames = columnNames ;
		this.dataList = new ArrayList<>(values);
	}
	
	public SourceTableModel (String [] columnNames) {
		this.columnNames = columnNames;
		this.dataList = new ArrayList<>();
	}
	
	public int getRowCount() {
		// TODO �����ؾ���.
		return dataList.size();
	}
	
	public Vector getDataVector() {
		// TODO �����ؾ���.
		return null; 
	}
	
	public void addRow(SourceModel newSource) {
		if ( dataList.add(newSource) ) {
			int index = dataList.indexOf(newSource);
			fireTableRowsInserted(index, index);
		} else {
			throw new RuntimeException("�ҽ� �Է� ���� : " + newSource) ;
		}
		
	}
	
	public SourceModel getSourceAt(int rowIndex) {
		// TODO ���� üũ �ڵ� �ʿ�.
		return dataList.get(rowIndex);
	}
		
	public void updateSourceAt(SourceModel source) {
		int index = dataList.indexOf(source);
		
		if ( index < 0) {
			throw new RuntimeException (" �������� �ʽ��ϴ�. : " + source) ;
		}
		
		dataList.remove(index);
		dataList.add(index, source);
		
		fireTableRowsUpdated(index, index);
		
	}
	
	public void removeRow(int rowIndex) {
		// TODO row �� ���� Ȯ���ؾ���.
		dataList.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
		
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length ;
	}
	
	@Override
	public String getColumnName(int columnIndex ) {
		if ( columnIndex < 0 || columnIndex >= columnNames.length ){
			throw new RuntimeException(" ������ ��� column index : " + columnIndex );
		}
		
		return columnNames[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object cellData = null;
		SourceModel source = dataList.get(rowIndex);
		
		if ( columnIndex == 0  ) {
			cellData = source.getId();
		} else if ( columnIndex == 1) {
			cellData = source.getSourceType();
		} else if ( columnIndex == 2) {
			cellData = source.getWhenContact();
		} else {
			throw new RuntimeException(" ������ ��� column index : " + columnIndex );
		}
		
		return cellData ;
	}
	
	public void clearSources() {
		int size = dataList.size();
		dataList.clear();
		
		fireTableRowsDeleted(0, size);
		
	}

	public void addSources(List<SourceModel> sourceList) {
		
		Iterator<SourceModel> it = sourceList.iterator();
		
		while ( it.hasNext() ) {
			dataList.add(it.next() );
		}
		
		fireTableStructureChanged();
		
	}

}
