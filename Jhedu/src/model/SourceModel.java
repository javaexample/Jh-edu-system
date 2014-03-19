package model;

import java.util.Vector;

public class SourceModel {
	final public static String SOURCE_TYPE_CALL = "";
	final public static String SOURCE_TYPE_EMAIL = "";

	
	/** 1. 소스번호 */
	private int sourceId;
	
	/** 2. 소스 종류 */
	private String sourceType;
	
	/** 3. 유입 날짜 */
	private String whenContact ;
	
	/** 4. 유입 시간 */
	private String contactTime;
	
	/** 5. 집전화  */
	private String homePhone;
	
	/** 6. 휴대전화*/
	private String cellPhone;

	/** 7. 이름 */
	private String name;
	
	/** 8. 성별[남자,여자]*/
	private String gender ;
	
	/** 9. 나이 */
	private int age;
	
	/** 10. 이메일*/
	private String email;
	
	/** 11. 주소*/
	private String address;
	
	/** 12. 문의내용 */
	private String inquiry ;
	
	/** 13. 담당자 */
	private EmployeeModel chargedEmployee;
	
	/** 14. 요망날짜 */
	private String requiredDate;
	
	/** 15. 요망 시간 */
	private String requiredTime;
	
	/** 16. 마감 날짜 */
	private String dueDate;
	
	/** 17. 비고 */
	private String ExplanaryNote;
	
	/** 18. 소스 상태 [가망, 홀딩, 오더, 폐기]*/
	private String sourceState;
	
	/** 19. 오더 일자 */
	private String orderDate ;
	
	/** 20. 기수 - 특정 포맷이 있음.*/
	private String semesterCode ;
	
	/** 21. 급수 - 2급, 3급 */
	private String level ;
	
	/** 22. 과목명 */
	private String CourseName;
	
	/** 23. 할인율 */
	private String discountRate;
	
	/** 24. 등록금 */
	private String registrationFee;
	
	/** 25. 결제상태 [결제대기, 결제승인]*/
	private String settlementState;
	
	/** 26. 교재 상태[미배송,배송대기,배송완료]*/
	private String textBookState;
	
	/** 27. 결제방법[결제대기, 결제승인]*/
	private String settlementType;
	
	/** 28. 카드종류 */
	private String cardType;
	
	/** 29. 카드번호 */
	private String cardNumber;
	
	/** 30. 카드 유효기간 월*/
	private String monthExpired;
	
	/** 31. 카드 유효기간 년*/
	private String yearExpired;
	
	/** 32. 할부 개월 */
	private int installmentMonths;
	
	/** 33. 은행명*/
	private String bankName;
	
	/** 34. 현금영수증 */
	private boolean cachReceiptRequired ;
	
	/** 35. 카드전표 */
	private boolean slipRequired;
	
	/** 36. 승인번호 */
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
		return "소스정보 [id=" + sourceId + ", sourceType=" + sourceType
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
