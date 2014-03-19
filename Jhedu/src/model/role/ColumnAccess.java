package model.role;

import dao.column.ColumnMapper;

public class ColumnAccess {
	private ColumnMapper mapper;
	private boolean writable = false;
	
	public ColumnAccess(ColumnMapper mapper) {
		this(mapper, false);
	}
	
	public ColumnAccess(ColumnMapper mapper, boolean writable) {
		this.mapper = mapper;
		this.writable = writable;
	}
	
	public void setWritable(boolean w) {
		this.writable = w;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mapper == null) ? 0 : mapper.hashCode());
		result = prime * result + (writable ? 1231 : 1237);
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
		ColumnAccess other = (ColumnAccess) obj;
		if (mapper == null) {
			if (other.mapper != null)
				return false;
		} else if (!mapper.equals(other.mapper))
			return false;
		if (writable != other.writable)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ColumnAccess [" + mapper.getColumnName() + ", writable=" + writable
				+ "]";
	}

	public boolean isWritable() {
		return writable;
	}
	
	
	
	
}