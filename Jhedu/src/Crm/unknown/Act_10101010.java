package Crm.unknown;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

class Act_10101010 extends JFrame implements ActionListener {

	public static void main(String[] ar) {

		try { // 이쁘게 해주는거
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}

		new Act_10101010();
	}

	private JRootPane jrp; // jrootpane 선언
	private Container con; // container 선언

	public Act_10101010() {

		super("로그인"); // 제목
		this.init(); // init실행
		this.start(); // start실행
		

		// //////화면중앙에 놓는 부분//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////
		
		this.setSize(500, 300); // 현재 프레임 화면의 크기 지정 205.120
		
		this.setVisible(true); // 현재 프레임을 화면상에 나타나게 함.
	}

	public void init() { // 화면 구성 넣을 부분

		jrp = this.getRootPane(); // 제이루트판 상위 컴퍼넌트 돌려줌
		con = jrp.getContentPane(); // 컨테이너 돌려줌
		con.setLayout(new BorderLayout()); // 컨테이너의 레이아웃 매니저 설정
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // 레이아웃을 포함한 제이판넬 생성
		JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		con.add(jp1);
		con.add(jp2);
		con.add(jp3);
		con.setLayout(new BorderLayout());
		
		EtchedBorder eb = new EtchedBorder(EtchedBorder.RAISED);
		jp1.setBorder(eb);
		jp2.setBorder(eb);
		jp3.setBorder(eb);
		
		jp1.setBounds(5, 5, 100, 20);
		jp2.setBounds(5, 30, 100, 20);
		jp3.setBounds(5, 55, 100, 20);
		

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
		//bt1.addActionListener(this);
		// bt2.addActionListener(this);
		
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료되는거
		// this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //x버튼 비활성화
	}

	public void actionPerformed(ActionEvent e) {


	}
}