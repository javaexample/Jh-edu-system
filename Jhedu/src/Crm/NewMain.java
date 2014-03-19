package Crm;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.role.RoleMapper;
import view.LoginPanel;
import dao.DAORegistry;
import dao.column.ColumnInfoLoader;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;

public class NewMain extends JFrame {

	public static void main(String[] args) {

		try { // 이쁘게 해주는거
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}

		try {
			JHContext context = new JHContext();
			context.registry = DAORegistry.getInstance();
			context.columnLoader = installColumnLoader(args);
			context.roleMapper = installRoleMapper(context.columnLoader, args);
			
			NewMain frame = new NewMain(context);
			frame.setVisible(true);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e ) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	static ColumnInfoLoader installColumnLoader(String [] args ) {
		InputStream in = NewMain.class.getResourceAsStream("/resources/column.config");
		ColumnInfoLoader loader =  new ColumnInfoLoader();
		loader.loadColumns(in, "ms949");
		return loader;
	}
	
	static RoleMapper installRoleMapper(ColumnInfoLoader loader, String [] args)
			throws ParserConfigurationException, SAXException, IOException {
		InputStream in = NewMain.class.getResourceAsStream("/resources/role.xml");
		RoleMapper mapper = new RoleMapper(loader);
		mapper.loadRoles(in, "ms949");
		return mapper;
	}

	
	public NewMain(JHContext context) {
		LoginPanel loginPanel = new LoginPanel(context);
		getContentPane().add(loginPanel, BorderLayout.CENTER);

		setTitle("로그인");
		setSize(200, 105);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
