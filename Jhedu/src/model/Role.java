package model;

public class Role {
	private RoleLevel level;
	
	public enum RoleLevel {
		SUPERVISOR,         // 최고 관리자 
		TEAM_SUPPORT,       // 학습 지원
		TEAM_MANAGE,        // 학습 관리
		TEAM_ACCOUNT,       // 회계
		TEAM_PARTTIMER      // 아르바이트 등
	};
	
	public Role(RoleLevel level) {
		this.level = level;
	}
	
	
	public RoleLevel getLevel() {
		return level;
	}
}
