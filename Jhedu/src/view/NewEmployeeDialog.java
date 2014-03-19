package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

import model.EmployeeModel;
import dao.DAORegistry;
import dao.EmployeeDAO;

public class NewEmployeeDialog extends JDialog implements ActionListener {

	private JRootPane jrp; // jrootpane 선언
	private Container con; // container 선언

	private JLabel lb1 = new JLabel("    아이디       ");
	private JLabel lb2 = new JLabel("  비밀번호      ");
	private JLabel lb3 = new JLabel(" 비밀번호확인 ");
	private JLabel lb4 = new JLabel("     이름         ");
	private JLabel lb5 = new JLabel("    부서명       ");
	private JLabel lb6 = new JLabel(" ");

	private JTextField tfIDField = new JTextField(""); // 제이텍스트필드 선언
	private JPasswordField pfPassField = new JPasswordField(); // 제이페스워드필드 선언
	private JPasswordField pf2PassField = new JPasswordField();
	private JTextField tfEmpName = new JTextField(""); // 제이텍스트필드 선언
	private JComboBox cbPart = new JComboBox();

	private JButton btnBack = new JButton("뒤로");
	private JButton btnSubscribe = new JButton("가입");

	public NewEmployeeDialog() {

		this.setTitle("회원가입"); // 제목
		this.init(); // init실행
		this.start(); // start실행
		this.setSize(200, 180); // 현재 프레임 화면의 크기 지정 205.120
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// //////화면중앙에 놓는 부분//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////

		this.setVisible(true); // 현재 프레임을 화면상에 나타나게 함.
	}

	public void init() { // 화면 구성 넣을 부분

		jrp = this.getRootPane(); // 제이루트판 상위 컴퍼넌트 돌려줌
		con = jrp.getContentPane(); // 컨테이너 돌려줌
		con.setLayout(new BorderLayout()); // 컨테이너의 레이아웃 매니저 설정

		// 프레임 짜는부분

		JPanel jp1 = new JPanel(new BorderLayout());
		jp1.add("West", btnBack);
		jp1.add("Center", btnSubscribe);

		JPanel jp2 = new JPanel(new BorderLayout());
		jp2.add(lb6);
		jp1.add("North", jp2);

		JPanel jp3 = new JPanel(new BorderLayout());
		jp3.add("West", lb5);
		jp3.add("Center", cbPart);
		jp2.add("North", jp3);

		JPanel jp4 = new JPanel(new BorderLayout());
		jp4.add("West", lb4);
		jp4.add("Center", tfEmpName);
		jp3.add("North", jp4);

		JPanel jp5 = new JPanel(new BorderLayout());
		jp5.add("West", lb3);
		jp5.add("Center", pf2PassField);
		jp4.add("North", jp5);

		JPanel jp6 = new JPanel(new BorderLayout());
		jp6.add("West", lb2);
		jp6.add("Center", pfPassField);
		jp5.add("North", jp6);

		JPanel jp7 = new JPanel(new BorderLayout());
		jp7.add("West", lb1);
		jp7.add("Center", tfIDField);
		jp6.add("North", jp7);

		con.add("Center", jp1);

		cbPart.addItem("학습지원팀");
		cbPart.addItem("학습운영팀");
		cbPart.addItem("회계팀");
		cbPart.addItem("관리자");
		cbPart.addItem("아르바이트");
		
		installListener();

	}
	
	private void installListener() {
		btnSubscribe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				processNewEmployee();
				
			}
		});
	}
	
	/*
	 * 1. 값을 검증
	 * 2. dao 요청을 날리고
	 * 3. 결과를 받아서 화면에 보여주면 끝
	 */
	private void processNewEmployee(){
		String empID = tfIDField.getText();
		String empName = tfEmpName.getText();
		String empPassword = new String(pfPassField.getPassword());
		String empPassword2 = new String(pf2PassField.getPassword());
		
		EmployeeDAO dao;
		try {
			dao = DAORegistry.getInstance().getEmployeeDAO();
		
			// TODO 0. 유효하지 않은 값 확인해야함. 
			
			// 1. 패스워드 입력 확인
			if ( !checkPassword(empPassword, empPassword2) ){				
				JOptionPane.showMessageDialog(this, "패스워트가 틀립니다.");
				return ;
			}
			
			// 2. 아이디 중복 확인
			
			if ( dao.existsEmployee(empID)) {
				JOptionPane.showMessageDialog(this, "아이디 중복");
				return ;
			}
			
			
			String part = (String) cbPart.getSelectedItem();
			System.out.printf("%s %s %s %s", empID, empName, empPassword, part);
			EmployeeModel emp = dao.insertEmployee(new EmployeeModel(empID, empPassword, empName, part));
			JOptionPane.showMessageDialog(this, emp.toString() );
			
			closeDialog();
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}
		
			
			

		
		
	}
	
	private boolean checkPassword(String pass0, String pass1) {
		return pass0.equals(pass1);
	}

	public void start() {

		this.addWindowListener(new WindowAdapter() {
			public void WondowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});

		// tf1.addActionListener(this);
		// pf1.addActionListener(this);
		btnBack.addActionListener(this);
		// bt2.addActionListener(this);

		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료되는거
		// this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // x버튼 비활성화
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnBack) {
			closeDialog();
		}
	}
	
	public void closeDialog(){
		setVisible(false);
	}
}