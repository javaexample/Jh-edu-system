package backup;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

import view.NewEmployeeDialog;
import view.SupervisorDialog;
import view.component.SourceViewPanel;
import model.EmployeeModel;
import model.Role;
import model.Role.RoleLevel;
import dao.DAORegistry;
import dao.EmployeeDAO;


public class LoginDialog extends JFrame implements ActionListener {

	private JRootPane jrp; // jrootpane ����
	private Container con; // container ����
	private JLabel lb1 = new JLabel("   ���̵�    ");
	private JLabel lb2 = new JLabel("  ��й�ȣ  ");
	private JTextField tf1 = new JTextField(""); // �����ؽ�Ʈ�ʵ� ����
	private JPasswordField pf1 = new JPasswordField(); // �����佺�����ʵ� ����
	private JButton bt1 = new JButton("ȸ������");
	private JButton btLogin = new JButton("�α���");

	private DAORegistry registory ;
	public LoginDialog(DAORegistry registory) {

		super("�α���"); // ����
		this.init(); // init����
		this.start(); // start����
		

		// //////ȭ���߾ӿ� ���� �κ�//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////
		
		this.setSize(250, 150); // ���� ������ ȭ���� ũ�� ���� 205.120
		
		this.registory = registory;
		
	}

	public void init() { // ȭ�� ���� ���� �κ�

		jrp = this.getRootPane(); // ���̷�Ʈ�� ���� ���۳�Ʈ ������
		con = jrp.getContentPane(); // �����̳� ������
		con.setLayout(new BorderLayout()); // �����̳��� ���̾ƿ� �Ŵ��� ����
		JPanel jp1 = new JPanel(new BorderLayout()); // ���̾ƿ��� ������ �����ǳ� ����

		jp1.add("West", lb1);
		jp1.add("Center", tf1);

		JPanel jp2 = new JPanel(new BorderLayout());
		jp2.add("West", lb2);
		jp2.add("Center", pf1);

		jp1.add("South", jp2);
		con.add("North", jp1);

		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jp3.add(bt1);
		jp3.add(btLogin);
		con.add("South", jp3); // �����̳� ���ʿ� ����° �����ǳ� �ٿ���
		
		EtchedBorder eb = new EtchedBorder(EtchedBorder.RAISED);
		jp1.setBorder(eb);
		
		tf1.setToolTipText("���̵� �Է��ϼ���"); //���콺 ���ٴ�� �۶ߴ°�
		pf1.setToolTipText("��й�ȣ�� �Է��ϼ���");
		
		installListener();

	}
	
	private void installListener() {
		btLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				processLogin();
			}
		});
		
		KeyListener keyListener = new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				if ( e.getKeyChar() == 10 ) {
					processLogin();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}
			
		};
		
		pf1.addKeyListener(keyListener);
		tf1.addKeyListener(keyListener);
	}
	
	private boolean checkValue (String value) {
		return value.trim().length() > 0 ;
	}
	private void processLogin() {
		String id = tf1.getText().trim();
		String password = new String(pf1.getPassword()).trim();
		
		
		if ( !checkValue(id)) {
			JOptionPane.showMessageDialog(this, "���̵� �Է��ϼ���");
			return ;
		}
		if ( !checkValue(password)) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���");			
			return ;
		}
		
		try {
			EmployeeDAO dao = registory.getEmployeeDAO();
			
			EmployeeModel emp = dao.getEmployee(id, password) ;
			
			Role role = null;
			
			if ( emp.getPart().equals("������")) {
				role = new Role(Role.RoleLevel.SUPERVISOR);
				
			} else {
				role = new Role(Role.RoleLevel.TEAM_SUPPORT);
			}

			showDialog(role);
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void showDialog(Role role) {
		
		this.setVisible(false); // �α��� â�� ����
		
		if ( role.getLevel() == RoleLevel.SUPERVISOR) {
			SupervisorDialog dialog = new SupervisorDialog(role);
			dialog.setVisible(true);
		} else if ( role.getLevel() == RoleLevel.TEAM_SUPPORT ) {
			SourceViewPanel dialog = new SourceViewPanel(role);
			dialog.setVisible(true);
		}
		
		
	}

	public void start() {

		this.addWindowListener(new WindowAdapter() {
			public void WondowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		bt1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bt1) {

			new NewEmployeeDialog();
		}

	}
}