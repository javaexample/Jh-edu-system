package Crm;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.UIManager;

import view.LoginPanel;
import dao.DAORegistry;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class NewMain extends JFrame {

	public static void main(String[] args) {

		try { // 이쁘게 해주는거
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}

		try {
			DAORegistry registry = DAORegistry.getInstance();
			NewMain frame = new NewMain(registry);
			frame.setVisible(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public NewMain(DAORegistry registry) {
		LoginPanel loginPanel = new LoginPanel(registry);
		getContentPane().add(loginPanel, BorderLayout.CENTER);

		setTitle("로그인");
		setSize(200, 105);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
