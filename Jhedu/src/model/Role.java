package model;

public class Role {
	private RoleLevel level;
	
	public enum RoleLevel {
		SUPERVISOR,         // �ְ� ������ 
		TEAM_SUPPORT,       // �н� ����
		TEAM_MANAGE,        // �н� ����
		TEAM_ACCOUNT,       // ȸ��
		TEAM_PARTTIMER      // �Ƹ�����Ʈ ��
	};
	
	public Role(RoleLevel level) {
		this.level = level;
	}
	
	
	public RoleLevel getLevel() {
		return level;
	}
}
