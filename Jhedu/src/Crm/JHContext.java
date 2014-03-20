package Crm;

import java.util.List;

import model.EmployeeModel;
import model.Role;
import model.role.RoleMapper;
import dao.DAORegistry;
import dao.column.ColumnInfoLoader;
import dao.column.ColumnMapper;

public class JHContext {
	DAORegistry registry ;
	ColumnInfoLoader columnLoader;
	RoleMapper roleMapper;
	
	Role role ; // 로그인 하면 설정
	
	private int reloadInterval  = 3; // as seconds
	private EmployeeModel employee;
	
	
	public DAORegistry getDAORegistry() {
		return registry;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Role getRole() {
		 return this.role;
	}

	public RoleMapper getRoleMapper() {
		return this.roleMapper;
	}

	public String[] getBaseColumnNames() {
		
		List<ColumnMapper> mappers = columnLoader.getBaseColumns();
		String [] names = new String[mappers.size()];
		for (int i = 0; i < names.length; i++) {
			names[i] = mappers.get(i).getColumnName();
		}
		return names;
	}
	
	public int getReloadInterval() {
		return this.reloadInterval;
	}

	public EmployeeModel getCurrentEmployee(){
		return this.employee;
	}
	public void setEmployee(EmployeeModel emp) {
		this.employee = emp;
		
	}
	
}