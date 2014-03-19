package model;

import java.util.HashMap;
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
	private String age;
	
	/** 10. 이메일*/
	private String email;
	
	/** 11. 주소*/
	private String address;
	
	/** 12. 문의내용 */
	private String inquiry ;
	
	/** 13. 담당자 */
	private String chargedEmployee;
	
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
	private String installmentMonths;
	
	/** 33. 은행명*/
	private String bankName;
	
	/** 34. 현금영수증 */
	private String cachReceiptRequired ;
	
	/** 35. 카드전표 */
	private String slipRequired;
	
	/** 36. 승인번호 */
	private String approvalNum;
	
	
	
	
	
	
	

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public String getContactTime() {
		return contactTime;
	}

	public void setContactTime(String contactTime) {
		this.contactTime = contactTime;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInquiry() {
		return inquiry;
	}

	public void setInquiry(String inquiry) {
		this.inquiry = inquiry;
	}

	public String getChargedEmployee() {
		return chargedEmployee;
	}

	public void setChargedEmployee(String chargedEmployee) {
		this.chargedEmployee = chargedEmployee;
	}

	public String getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getRequiredTime() {
		return requiredTime;
	}

	public void setRequiredTime(String requiredTime) {
		this.requiredTime = requiredTime;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getExplanaryNote() {
		return ExplanaryNote;
	}

	public void setExplanaryNote(String explanaryNote) {
		ExplanaryNote = explanaryNote;
	}

	public String getSourceState() {
		return sourceState;
	}

	public void setSourceState(String sourceState) {
		this.sourceState = sourceState;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getSemesterCode() {
		return semesterCode;
	}

	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public String getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}

	public String getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}

	public String getSettlementState() {
		return settlementState;
	}

	public void setSettlementState(String settlementState) {
		this.settlementState = settlementState;
	}

	public String getTextBookState() {
		return textBookState;
	}

	public void setTextBookState(String textBookState) {
		this.textBookState = textBookState;
	}

	public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getMonthExpired() {
		return monthExpired;
	}

	public void setMonthExpired(String monthExpired) {
		this.monthExpired = monthExpired;
	}

	public String getYearExpired() {
		return yearExpired;
	}

	public void setYearExpired(String yearExpired) {
		this.yearExpired = yearExpired;
	}

	public String getInstallmentMonths() {
		return installmentMonths;
	}

	public void setInstallmentMonths(String installmentMonths) {
		this.installmentMonths = installmentMonths;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String isCachReceiptRequired() {
		return cachReceiptRequired;
	}

	public void setCachReceiptRequired(String cachReceiptRequired) {
		this.cachReceiptRequired = cachReceiptRequired;
	}

	public String getSlipRequired() {
		return slipRequired;
	}

	public void setSlipRequired(String slipRequired) {
		this.slipRequired = slipRequired;
	}

	public String getApprovalNum() {
		return approvalNum;
	}

	public void setApprovalNum(String approvalNum) {
		this.approvalNum = approvalNum;
	}

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

	public HashMap<String, String> asMapData() {
		
		HashMap<String, String> mapData = new HashMap<>();
		
		mapData.put("소스번호", "" + sourceId);
		mapData.put("소스종류", sourceType);
		
		mapData.put("유입날짜", whenContact);
		mapData.put("유입시간",  contactTime);
		mapData.put("일반전화", homePhone);
		mapData.put("휴대전화", cellPhone);
		mapData.put("이름", name);
		mapData.put("성별", gender);
		mapData.put("나이", age);
		mapData.put("이메일", email);
		mapData.put("주소", address);
		mapData.put("문의내용", inquiry);
		mapData.put("담당자", chargedEmployee);
		mapData.put("요망날짜", requiredDate);
		mapData.put("요망시간", requiredTime);
		mapData.put("마감날짜", dueDate);
		mapData.put("비고", ExplanaryNote);
		mapData.put("소스상태", sourceState);
		mapData.put("오더일자", orderDate);
		mapData.put("기수", semesterCode);
		mapData.put("급수", level);
		mapData.put("과목수", CourseName);
		mapData.put("할인율", discountRate);
		mapData.put("등록금", registrationFee);
		mapData.put("결제상태", settlementState);
		mapData.put("교재상태", textBookState);
		mapData.put("결제방법", settlementType);
		mapData.put("카드종류", cardType);
		mapData.put("카드번호", cardNumber);
		mapData.put("유효기간월", monthExpired );
		mapData.put("유효기간년", yearExpired);
		mapData.put("할부개월", installmentMonths);
		mapData.put("은행명", bankName);
		mapData.put("현금영수증발급", cachReceiptRequired);
		mapData.put("카드전표발급", slipRequired);
		mapData.put("승인번호", approvalNum);
		
		
		return mapData;
		
		
	}
	
	
	

}
