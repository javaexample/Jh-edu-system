package model;

import java.util.Vector;

public class SourceModel {
	final public static String SOURCE_TYPE_CALL = "";
	final public static String SOURCE_TYPE_EMAIL = "";

	
	/*
	 * �ҽ���ȣ
	 */
	private int id;
	
	/*
	 * �ҽ� ����
	 */
	private String sourceType;
	
	/*
	 * ���� ��¥
	 */
	private String whenContact ;

	public SourceModel(int id, String sourceType, String whenContact) {
		super();
		this.id = id;
		this.sourceType = sourceType;
		this.whenContact = whenContact;
	}

	public SourceModel(Vector modelVector) {
		this.id = (Integer) modelVector.get(0);
		this.sourceType = (String) modelVector.get(1);
		this.whenContact = (String) modelVector.get(2);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getWhenContact() {
		return whenContact;
	}

	public void setWhenContact(String whenContact) {
		this.whenContact = whenContact;
	}

	@Override
	public String toString() {
		return "�ҽ����� [id=" + id + ", sourceType=" + sourceType
				+ ", whenContact=" + whenContact + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		SourceModel other = (SourceModel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	

}
