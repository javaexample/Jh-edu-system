package Crm.unknown;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.JPasswordField;

class Act4_1 extends JFrame implements ActionListener {

	public static void main(String[] ar) {

		try { // 이쁘게 해주는거
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}

		new Act4_1();

	}
	
	private JRootPane jrp; // jrootpane 선언
	private Container con; // container 선언
	
	public Act4_1() {

		super("상세정보"); // 제목
		this.init(); // init실행
		this.start(); // start실행
		this.setSize(1000, 1000); // 현재 프레임 화면의 크기 지정 205.120

		// //////화면중앙에 놓는 부분//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////

		this.setVisible(true); // 현재 프레임을 화면상에 나타나게 함.
	}

	public void init() {
		// 화면 구성 넣을 부분
		jrp = this.getRootPane(); 
		con = jrp.getContentPane(); // 컨테이너 돌려줌
		con.setLayout(new BorderLayout()); // 컨테이너의 레이아웃 매니저 설정
		String[] name = new String[] { "■ 기본정보", "순번", "이름", "성별", "나이",
				"연락처(일반)", "연락처(핸드폰)", "이메일", "주소", "문의내용", "비고", "담당자" };
		
//		JLabel[] labels = new JLabel[13];
//		JTextField[] textFields = new JTextField[12];
		
		JLabel[] labels = new JLabel[name.length];
		JTextField[] textFields = new JTextField[name.length];
		
		for (int i = 0; i < labels.length - 1; i++) {
			labels[i + 1] = new JLabel(name[i]);
		}

		JPanel[] panels = new JPanel[11];

		for (int i = 0; i < panels.length - 1; i++) {

			panels[i + 1] = new JPanel();
			panels[i + 1].add("west", labels[i+1]);
			panels[i + 1].add("East", textFields[i+1]);
			
		}
		
		//this.setLayout(null);
		
		//this.add(panels[1]);
		//panels[1].setBounds(5, 5, 80, 20);
		//con.setBounds(5, 5, 80, 20);
		//JPanel jp1 = new JPanel(new BorderLayout());
		//jp1.add
		
		/*JPanel jp2 = new JPanel(new BorderLayout());
		jp2.add("West", lb2);
		jp2.add("Center", pf1);*/
		
		
		/*JPanel[] panels = new JPanel[11];

		for (int i = 0; i < panels.length - 1; i++) {

			panels[i + 1] = new JPanel();
			
		}/*
		
		/*this.setLayout(null);

		JLabel[] labels = new JLabel[13];
		JTextField[] TextFields = new JTextField[12];
		String[] name = new String[] { "■ 기본정보", "순번", "이름", "성별", "나이",
				"연락처(일반)", "연락처(핸드폰)", "이메일", "주소", "문의내용", "비고", "담당자" };
		
		int[] jsb1 = new int[] { 5, 5, 90, 5, 110, 5, 5, 5, 5, 5, 5, 5};
		int[] jsb2 = new int[] { 5, 27, 27, 49, 49, 71, 93, 115, 137, 159, 181, 203};
		int[] jsb3 = new int[] { 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90};
		int[] jsb4 = new int[] { 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		int[] tsb1 = new int[] { 40, 125, 140, 90, 90, 90, 40, 90, 90, 90, 90};
		int[] tsb2 = new int[] { 27, 27, 49, 71, 93, 115, 137, 159, 181, 203, 225,247};
		int[] tsb3 = new int[] { 40, 55, 40, 90, 90, 90, 140, 90, 90, 90, 90};
		int[] tsb4 = new int[] { 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		
		
		
		for (int i = 0; i < labels.length - 1; i++) {

			labels[i + 1] = new JLabel(name[i]);
			this.add(labels[i + 1]);
			labels[i + 1].setAlignmentY(1);
			labels[i + 1].setBounds(jsb1[i], jsb2[i], jsb3[i], jsb4[i]);
		}

		for (int i = 0; i < TextFields.length - 1; i++) {
			TextFields[i + 1] = new JTextField();
			this.add(TextFields[i + 1]);
			TextFields[i + 1].setBounds(tsb1[i], tsb2[i], tsb3[i], tsb4[i]);
		}

		JComboBox cb1 = new JComboBox();
		this.add(cb1);
		cb1.setBounds(40, 49, 60, 20);
		cb1.addItem("남자");
		cb1.addItem("여자");*/
		
	}

	public void start() {
		// 이벤트나 Thread 처리할 부분

		// join_btn.addActionListener(new Login_Act(this));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //x버튼 비활성화

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}