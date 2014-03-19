package dao.column;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * column.config 파일을 읽어서 columnMapper 인스턴스를 생성
 * 
 * @author Youngjae
 *
 */
public class ColumnInfoLoader {
	
	private HashMap<String, ColumnMapper> columnMap = new HashMap<>();
	
	public void loadColumns(InputStream in, String charset) {
		Scanner scanner = new Scanner(in, charset);
		int p0, p1;
		
		// 0:소스번호+:int
		while ( scanner.hasNextLine()) {
			String line = scanner.nextLine().trim();
			
			// "0" 파트
			p0 = 0;
			p1 = line.indexOf(':');
			int index = Integer.parseInt(line.substring(p0, p1));
			
			// "소스번호+" 파트
			p0 = p1 + 1;
			p1 = line.indexOf(':', p0);
			String columnName = line.substring(p0, p1);
			
			boolean isBaseColumn = false;
			if ( columnName.endsWith("+")) {
				isBaseColumn = true;
				columnName  = columnName.substring(0, columnName.length()-1);
			}
			
			String type = line.substring(p1 + 1);
			
			
			
			ColumnMapper column = new ColumnMapper(index, columnName, type, isBaseColumn );
			columnMap.put(columnName, column);
			
		}
	}

	public int getColumnSize() {
		return columnMap.size();
	}
	
	public ColumnMapper findColumnMapper (String columnName) {
		ColumnMapper mapper = columnMap.get(columnName);
		
		if ( mapper == null) {
			throw new RuntimeException("cannot find column mapper : " + columnName);
		}
		
		return mapper;
	}

	public Collection<ColumnMapper> getAllMapper() {
		return columnMap.values();
	}

	public List<ColumnMapper> getBaseColumns() {
		ArrayList<ColumnMapper> mappers = new ArrayList<>();
		
		Iterator<String> it = columnMap.keySet().iterator();
		
		while ( it.hasNext()) {
			ColumnMapper col = columnMap.get(it.next());
			if ( col.isBaseColumn()) {
				mappers.add(col);				
			}
		}
		
		Collections.sort(mappers, new Comparator<ColumnMapper>() {

			@Override
			public int compare(ColumnMapper o1, ColumnMapper o2) {
				int value = o1.getIndex() - o2.getIndex();
				
				if ( value < 0 ) {
					return -1;
				} else if ( value > 0 ) {
					return 1;
				} else {
					return 0;
				}
			}
			
		});
		return mappers;
	}
	
	
}
