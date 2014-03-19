package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import Crm.NewMain.JHContext;

import view.component.SourceViewPanel;
import view.component.SupervisorPanel;
import model.EmployeeModel;
import model.Role;
import model.RoleLevel;
import dao.EmployeeDAO;

public class LoginPanel extends JPanel implements ActionListener {

	private Container con; // container ����
	private JLabel lb1 = new JLabel("   ���̵�    ");
	private JLabel lb2 = new JLabel("  ��й�ȣ  ");
	private JTextField tf1 = new JTextField(""); // �����ؽ�Ʈ�ʵ� ����
	private JPasswordField pf1 = new JPasswordField(); // �����佺�����ʵ� ����
	private JButton bt1 = new JButton("ȸ������");
	private JButton btLogin = new JButton("�α���");

//	private DAORegistry registory;
	private JHContext context;

	public LoginPanel(JHContext context) {

		this.init(); // init����
		this.start(); // start����

		this.context = context;

	}

	// ȭ�� ����
	public void init() { 

		con = this;
		con.setLayout(new BorderLayout()); 
		JPanel jp1 = new JPanel(new BorderLayout()); 

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
		con.add("South", jp3); 

		// ���콺 ���ٴ�� �۶ߴ°�
		tf1.setToolTipText("���̵� �Է��ϼ���"); 
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

		//����ġ�� ��ư����.
		KeyListener keyListener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == 10) {
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

	private boolean checkValue(String value) {
		return value.trim().length() > 0;
	}

	private void processLogin() {
		String id = tf1.getText().trim();
		String password = new String(pf1.getPassword()).trim();

		if (!checkValue(id)) {
			JOptionPane.showMessageDialog(this, "���̵� �Է��ϼ���");
			return;
		}
		if (!checkValue(password)) {
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���");
			return;
		}

		try {
			EmployeeDAO dao = context.getDAORegistry().getEmployeeDAO();

			EmployeeModel emp = dao.getEmployee(id, password);

			Role role = null;

			if (emp.getPart().equals("������")) {
				role = new Role(RoleLevel.SUPERVISOR);
			} else {
				role = new Role(RoleLevel.TEAM_SUPPORT);
			}

			context.setRole(role);
			showDialog(context);

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void showDialog(JHContext ctx) {
		Component nextComp = null;

		Role role = ctx.getRole();
		
		//���Ѻ��� ������ ����Ǵ� �κ�
		if (role.getLevel() == RoleLevel.SUPERVISOR)
		{
			nextComp = new SupervisorPanel(ctx);

		} else if (role.getLevel() == RoleLevel.TEAM_SUPPORT) {
			nextComp = new SourceViewPanel(ctx);

		}
		// TODO �̷� ������ �ǳ��� �ø��� �˴ϴ�.
		// else if ( role.getLevel() == RoleLevel.ALBA) {
		// nextComp = new AlbaPanle(role);
		// }

		Container container = this.getParent();
		while (!(container instanceof JFrame)) {
			container = container.getParent();
		}

		JFrame parent = (JFrame) container;

		parent.remove(this);
		parent.add(nextComp);
		parent.setResizable(true);
		parent.setSize(1024, 768);
		parent.setLocationRelativeTo(null);

		parent.revalidate();
	}

	public void start() {
		bt1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bt1) {

			new NewEmployeeDialog();
		}

	}
}