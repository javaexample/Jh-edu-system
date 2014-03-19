package model;

public enum RoleLevel {
	SUPERVISOR("관리자"),         // 최고 관리자 
	TEAM_SUPPORT("학습지원"),       // 학습 지원
	TEAM_MANAGE("학습운영"),        // 학습 운영
	TEAM_ACCOUNT("회계"),       // 회계
	TEAM_PARTTIMER("아르바이트");      // 아르바이트 등
	
	private String roleName;
	
	private RoleLevel (String name) {
		this.roleName = name;
	}
	
	public String asRoleName() {
		return roleName;
	}
	@Override
	public String toString() {
		return "[역할:" + this.roleName + "]";
	}
	
}