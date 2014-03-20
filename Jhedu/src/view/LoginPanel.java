package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import Crm.JHContext;
import view.SourceEditDialog.SourceUpdateListener;
import view.component.SourceViewPanel;
import view.component.SupervisorPanel;
import model.EmployeeModel;
import model.Role;
import model.RoleLevel;
import model.SourceModel;
import dao.EmployeeDAO;

public class LoginPanel extends JPanel implements ActionListener {

	private Container con; // container 선언
	private JLabel lb1 = new JLabel("   아이디    ");
	private JLabel lb2 = new JLabel("  비밀번호  ");
	private JTextField tf1 = new JTextField(""); // 제이텍스트필드 선언
	private JPasswordField pf1 = new JPasswordField(); // 제이페스워드필드 선언
	private JButton bt1 = new JButton("회원가입");
	private JButton btLogin = new JButton("로그인");

//	private DAORegistry registory;
	private JHContext context;

	public LoginPanel(JHContext context) {

		this.init(); // init실행
		this.start(); // start실행

		this.context = context;

	}

	// 화면 구성
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

		// 마우스 갖다대면 글뜨는거
		tf1.setToolTipText("아이디를 입력하세요"); 
		pf1.setToolTipText("비밀번호를 입력하세요");

		installListener();

	}

	private void installListener() {
		btLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				processLogin();
			}
		});

		//엔터치면 버튼눌림.
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
			JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
			return;
		}
		if (!checkValue(password)) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
			return;
		}

		try {
			EmployeeDAO dao = context.getDAORegistry().getEmployeeDAO();

			EmployeeModel emp = dao.getEmployee(id, password);
			
			context.setEmployee(emp);
			
			Role role = null;

			if (emp.getPart().equals("관리자")) {
				role = new Role(RoleLevel.SUPERVISOR);
			} else if ( emp.getPart().equals("학습지원")){
				role = new Role(RoleLevel.TEAM_SUPPORT);
			} else if ( emp.getPart().equals("회계") ) {
				role = new Role(RoleLevel.TEAM_ACCOUNT);
			} else if ( emp.getPart().equals("학습운영")) {
				role = new Role(RoleLevel.TEAM_MANAGE);
			} else if ( emp.getPart().equals("아르바이트")) {
				role = new Role(RoleLevel.TEAM_PARTTIMER) ;
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
		
		//권한별로 프레임 변경되는 부분
		
		if ( role.getLevel() == RoleLevel.TEAM_PARTTIMER ) {
			
			SourceInsertDialog dialog = new SourceInsertDialog(ctx);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					System.exit(0);
				}
			});
			dialog.setSize(350, 300);
			dialog.setLocationRelativeTo(this);
			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);
			
			
		} else {
			if (role.getLevel() == RoleLevel.SUPERVISOR)
			{
				nextComp = new SupervisorPanel(ctx);

			} 
			else {
				nextComp = new SourceViewPanel(ctx);

			}

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
		
		this.setVisible(false);
		
	}

	public void start() {
		bt1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bt1) {

			new NewEmployeeDialog();
		}
	}
	
	// TODO 아르바이트가 소스를 추가했을때 데이터베이스에 소스를 추가하는 기능 들어가야함.
	static class SourceInsertListener implements SourceUpdateListener {

		@Override
		public void sourceUpdated(SourceModel source) {
			System.out.println("새로 넣을 소스 : " +  source );
			
		}
		
	}
}