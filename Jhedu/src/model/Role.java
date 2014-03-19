package model;

public class Role {
	private String rollName ;
	private RoleLevel level;
	
	public enum RoleLevel {
		SUPERVISOR, // �ְ� ������ 
		ROLE1       // �н� ����
	};
	
	public Role(RoleLevel level) {
		this.level = level;
	}
	
	
	public RoleLevel getLevel() {
		return level;
	}
}
