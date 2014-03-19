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
		// TODO 구현해야함.
		return dataList.size();
	}
	
	public Vector getDataVector() {
		// TODO 구현해야함.
		return null; 
	}
	
	public void addRow(SourceModel newSource) {
		if ( dataList.add(newSource) ) {
			int index = dataList.indexOf(newSource);
			fireTableRowsInserted(index, index);
		} else {
			throw new RuntimeException("소스 입력 실패 : " + newSource) ;
		}
		
	}
	
	public SourceModel getSourceAt(int rowIndex) {
		// TODO 범위 체크 코드 필요.
		return dataList.get(rowIndex);
	}
		
	public void updateSourceAt(SourceModel source) {
		int index = dataList.indexOf(source);
		
		if ( index < 0) {
			throw new RuntimeException (" 존재하지 않습니다. : " + source) ;
		}
		
		dataList.remove(index);
		dataList.add(index, source);
		
		fireTableRowsUpdated(index, index);
		
	}
	
	public void removeRow(int rowIndex) {
		// TODO row 값 범위 확인해야함.
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
			throw new RuntimeException(" 범위를 벗어난 column index : " + columnIndex );
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
			throw new RuntimeException(" 범위를 벗어난 column index : " + columnIndex );
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
