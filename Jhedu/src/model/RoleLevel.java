package model;

public enum RoleLevel {
	SUPERVISOR("������"),         // �ְ� ������ 
	TEAM_SUPPORT("�н�����"),       // �н� ����
	TEAM_MANAGE("�н��"),        // �н� �
	TEAM_ACCOUNT("ȸ��"),       // ȸ��
	TEAM_PARTTIMER("�Ƹ�����Ʈ");      // �Ƹ�����Ʈ ��
	
	private String roleName;
	
	private RoleLevel (String name) {
		this.roleName = name;
	}
	
	public String asRoleName() {
		return roleName;
	}
	@Override
	public String toString() {
		return "[����:" + this.roleName + "]";
	}
	
}