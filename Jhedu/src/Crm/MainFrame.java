package Crm;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.UIManager;

import model.Role;
import model.RoleLevel;
import view.FormNewSourceDialog;
import view.LoginPanel;
import view.NewEmployeeDialog;
import view.PaymentDialog;
import view.SourceManagerDialog;
import view.SupervisorDialog;
import view.component.SourceViewPanel;
import dao.DAORegistry;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.sql.SQLException;

public class MainFrame extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		try { // 이쁘게 해주는거
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}
		
		try {
			MainFrame frame = new MainFrame();
			frame.setSize(400, 400);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public MainFrame() throws ClassNotFoundException, SQLException {

		
		final DAORegistry registry = DAORegistry.getInstance();
		
		JButton btnSourceframe = new JButton("SourceFrame");
		btnSourceframe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SourceViewPanel frame = new SourceViewPanel(new Role(RoleLevel.TEAM_SUPPORT));
				frame.setVisible(true);
			}
		});
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		getContentPane().add(btnSourceframe);

		JButton btnPaymentdialog = new JButton("PaymentDialog");
		btnPaymentdialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaymentDialog pDialog = new PaymentDialog();
				pDialog.setVisible(true);
			}
		});
		getContentPane().add(btnPaymentdialog);

		JButton btnLogindialog = new JButton("직원로그인");
		btnLogindialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPanel login = new LoginPanel(registry);
				login.setVisible(true);
			}
		});
		getContentPane().add(btnLogindialog);

		JButton btnFormnewsourcedialog = new JButton("FormNewSourceDialog");
		btnFormnewsourcedialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormNewSourceDialog dialog = new FormNewSourceDialog();
				dialog.setVisible(true);
			}
		});
		getContentPane().add(btnFormnewsourcedialog);

		JButton btnNewcustomerdialog = new JButton("직원입력창");
		btnNewcustomerdialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewEmployeeDialog dialog = new NewEmployeeDialog();
				dialog.setVisible(true);
			}
		});
		getContentPane().add(btnNewcustomerdialog);

		JButton btnSourcemngframe = new JButton("SourceMngFrame");
		btnSourcemngframe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SourceManagerDialog dialog = new SourceManagerDialog();
				dialog.setVisible(true);
			}
		});
		getContentPane().add(btnSourcemngframe);
		
		JButton btnSourceviewdialog = new JButton("학습지원");
		btnSourceviewdialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SourceViewPanel dialog = new SourceViewPanel(new Role(RoleLevel.TEAM_SUPPORT));
				dialog.setVisible(true);
			}
		});
		getContentPane().add(btnSourceviewdialog);
		
		JButton button = new JButton("[\uCC3D]\uAD00\uB9AC\uC790");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupervisorDialog dialog = new SupervisorDialog(new Role(RoleLevel.SUPERVISOR));
				dialog.setVisible(true);
			}
		});
		getContentPane().add(button);

	}

}
