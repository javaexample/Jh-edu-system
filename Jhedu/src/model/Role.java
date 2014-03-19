package model;

public class Role {
	private RoleLevel level;
	
	private String roleName;
	public Role(RoleLevel level) {
		this.level = level;
		this.roleName = level.asRoleName();
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public RoleLevel getLevel() {
		return level;
	}
}
