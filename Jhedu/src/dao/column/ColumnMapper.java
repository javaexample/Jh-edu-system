package dao.column;
/**
 * 디비 테이블의 컬럼 맵핑 정보를 나타냄
 * 
 * @author chminseo
 *
 * @param <T>
 */
public class ColumnMapper {
	
	private int index ;
	private String columnName;
	private String columnType ; // type in database
	
	public ColumnMapper(int index, String columnName, String type) {
		this.index = index;
		this.columnName = columnName;
		this.columnType = type;
	}
	
	public int getIndex() {
		return this.index;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result
				+ ((columnType == null) ? 0 : columnType.hashCode());
		result = prime * result + index;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		ColumnMapper other = (ColumnMapper) obj;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (columnType == null) {
			if (other.columnType != null)
				return false;
		} else if (!columnType.equals(other.columnType))
			return false;
		if (index != other.index)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Column[" +index + ", " + 
				columnName + ", t:" + 
				columnType + "]";
	}
	
	
}
