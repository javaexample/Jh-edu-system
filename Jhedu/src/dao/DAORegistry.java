package dao;

import java.sql.SQLException;

public class DAORegistry {

	private static DAORegistry registry ;
	
	private  SourceDAO sourceDAO;
	private EmployeeDAO empDAO;
	private ConnManager connManager ;
	public DAORegistry () throws ClassNotFoundException, SQLException {
		connManager = new ConnManager();
		
		sourceDAO = new SourceDAO(connManager);
		empDAO = new EmployeeDAO(connManager);
	}
	
	public static DAORegistry getInstance() throws ClassNotFoundException, SQLException{
		if ( registry  == null ) {
			registry = new DAORegistry();
		}
		
		return registry;
		
	}
	
	public SourceDAO getSourceDAO(){
		return sourceDAO;
	}

	public EmployeeDAO getEmployeeDAO() {
		return empDAO;
	}
}
