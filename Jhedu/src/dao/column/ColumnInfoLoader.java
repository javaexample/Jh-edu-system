package dao.column;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class ColumnInfoLoader {
	
	private HashMap<String, ColumnMapper> columnMap = new HashMap<>();
	
	public void loadColumns(InputStream in, String charset) {
		Scanner scanner = new Scanner(in, charset);
		int p0, p1;
		while ( scanner.hasNextLine()) {
			String line = scanner.nextLine().trim();
			
			p0 = 0;
			p1 = line.indexOf(':');
			
			int index = Integer.parseInt(line.substring(p0, p1));
			
			p0 = p1 + 1;
			p1 = line.indexOf(':', p0);
			String columnName = line.substring(p0, p1);
			
			String type = line.substring(p1 + 1) ;
						
			ColumnMapper column = new ColumnMapper(index, columnName, type );
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
	
	
}
