package model;

import java.util.Vector;

public class SourceModel {
	final public static String SOURCE_TYPE_CALL = "";
	final public static String SOURCE_TYPE_EMAIL = "";

	
	/** 1. �ҽ���ȣ */
	private int sourceId;
	
	/** 2. �ҽ� ���� */
	private String sourceType;
	
	/** 3. ���� ��¥ */
	private String whenContact ;
	
	/** 4. ���� �ð� */
	private String contactTime;
	
	/** 5. ����ȭ  */
	private String homePhone;
	
	/** 6. �޴���ȭ*/
	private String cellPhone;

	/** 7. �̸� */
	private String name;
	
	/** 8. ����[����,����]*/
	private String gender ;
	
	/** 9. ���� */
	private int age;
	
	/** 10. �̸���*/
	private String email;
	
	/** 11. �ּ�*/
	private String address;
	
	/** 12. ���ǳ��� */
	private String inquiry ;
	
	/** 13. ����� */
	private EmployeeModel chargedEmployee;
	
	/** 14. �����¥ */
	private String requiredDate;
	
	/** 15. ��� �ð� */
	private String requiredTime;
	
	/** 16. ���� ��¥ */
	private String dueDate;
	
	/** 17. ��� */
	private String ExplanaryNote;
	
	/** 18. �ҽ� ���� [����, Ȧ��, ����, ���]*/
	private String sourceState;
	
	/** 19. ���� ���� */
	private String orderDate ;
	
	/** 20. ��� - Ư�� ������ ����.*/
	private String semesterCode ;
	
	/** 21. �޼� - 2��, 3�� */
	private String level ;
	
	/** 22. ����� */
	private String CourseName;
	
	/** 23. ������ */
	private String discountRate;
	
	/** 24. ��ϱ� */
	private String registrationFee;
	
	/** 25. �������� [�������, ��������]*/
	private String settlementState;
	
	/** 26. ���� ����[�̹��,��۴��,��ۿϷ�]*/
	private String textBookState;
	
	/** 27. �������[�������, ��������]*/
	private String settlementType;
	
	/** 28. ī������ */
	private String cardType;
	
	/** 29. ī���ȣ */
	private String cardNumber;
	
	/** 30. ī�� ��ȿ�Ⱓ ��*/
	private String monthExpired;
	
	/** 31. ī�� ��ȿ�Ⱓ ��*/
	private String yearExpired;
	
	/** 32. �Һ� ���� */
	private int installmentMonths;
	
	/** 33. �����*/
	private String bankName;
	
	/** 34. ���ݿ����� */
	private boolean cachReceiptRequired ;
	
	/** 35. ī����ǥ */
	private boolean slipRequired;
	
	/** 36. ���ι�ȣ */
	private int approvalNum;
	
	
	
	
	
	
	

	public SourceModel(int id, String sourceType, String whenContact) {
		super();
		this.sourceId = id;
		this.sourceType = sourceType;
		this.whenContact = whenContact;
	}

	public SourceModel(Vector modelVector) {
		this.sourceId = (Integer) modelVector.get(0);
		this.sourceType = (String) modelVector.get(1);
		this.whenContact = (String) modelVector.get(2);
	}

	public int getId() {
		return sourceId;
	}

	public void setId(int id) {
		this.sourceId = id;
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
		return "�ҽ����� [id=" + sourceId + ", sourceType=" + sourceType
				+ ", whenContact=" + whenContact + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sourceId;
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
		if (sourceId != other.sourceId)
			return false;
		return true;
	}
	
	
	

}
