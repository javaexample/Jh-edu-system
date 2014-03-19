package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import model.EmployeeModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEmployeeDAO {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_insert_new_employee() throws ClassNotFoundException, SQLException {
		EmployeeDAO dao = DAORegistry.getInstance().getEmployeeDAO();
		
		EmployeeModel emp = new EmployeeModel("test_emp","test password", "±è±è¼ö", "È¸°è");
		
		emp = dao.insertEmployee(emp);
	}
	
	

}
