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

	private JRootPane jrp; // jrootpane 선언
	private Container con; // container 선언
	private JLabel lb1 = new JLabel("   아이디    ");
	private JLabel lb2 = new JLabel("  비밀번호  ");
	private JTextField tf1 = new JTextField(""); // 제이텍스트필드 선언
	private JPasswordField pf1 = new JPasswordField(); // 제이페스워드필드 선언
	private JButton bt1 = new JButton("회원가입");
	private JButton btLogin = new JButton("로그인");

	private DAORegistry registory ;
	public LoginDialog(DAORegistry registory) {

		super("로그인"); // 제목
		this.init(); // init실행
		this.start(); // start실행
		

		// //////화면중앙에 놓는 부분//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////
		
		this.setSize(250, 150); // 현재 프레임 화면의 크기 지정 205.120
		
		this.registory = registory;
		
	}

	public void init() { // 화면 구성 넣을 부분

		jrp = this.getRootPane(); // 제이루트판 상위 컴퍼넌트 돌려줌
		con = jrp.getContentPane(); // 컨테이너 돌려줌
		con.setLayout(new BorderLayout()); // 컨테이너의 레이아웃 매니저 설정
		JPanel jp1 = new JPanel(new BorderLayout()); // 레이아웃을 포함한 제이판넬 생성

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
		con.add("South", jp3); // 콘테이너 북쪽에 세번째 제이판넬 붙여라
		
		EtchedBorder eb = new EtchedBorder(EtchedBorder.RAISED);
		jp1.setBorder(eb);
		
		tf1.setToolTipText("아이디를 입력하세요"); //마우스 갖다대면 글뜨는거
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
			JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
			return ;
		}
		if ( !checkValue(password)) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");			
			return ;
		}
		
		try {
			EmployeeDAO dao = registory.getEmployeeDAO();
			
			EmployeeModel emp = dao.getEmployee(id, password) ;
			
			Role role = null;
			
			if ( emp.getPart().equals("관리자")) {
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
		
		this.setVisible(false); // 로그인 창을 닫음
		
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