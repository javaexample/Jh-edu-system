package model;

public class Role {
	private String rollName ;
	private RoleLevel level;
	
	public enum RoleLevel {
		SUPERVISOR, // 최고 관리자 
		ROLE1       // 학습 지원
	};
	
	public Role(RoleLevel level) {
		this.level = level;
	}
	
	
	public RoleLevel getLevel() {
		return level;
	}
}
