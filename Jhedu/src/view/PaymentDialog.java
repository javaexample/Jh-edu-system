package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class PaymentDialog extends JDialog implements ActionListener {

	public static void main(String[] ar) {

		new PaymentDialog();
	}

	private JRootPane jrp; // jrootpane 선언
	private Container con; // container 선언

	private JLabel lb1 = new JLabel(" 일반전화  ");
	private JLabel lb2 = new JLabel("   핸드폰   ");
	private JLabel lb3 = new JLabel("    성별     ");
	private JLabel lb4 = new JLabel("   고객명   ");
	private JLabel lb5 = new JLabel("    비고     ");
	private JLabel lb6 = new JLabel(" 날짜 및 시간은 자동입력됩니다.");

	private JTextField tf1 = new JTextField(""); // 제이텍스트필드 선언
	private JTextField tf2 = new JTextField("");
	private JComboBox cb1 = new JComboBox();
	private JTextField tf4 = new JTextField("");
	private JTextField tf5 = new JTextField("");

	private JButton bt1 = new JButton("뒤로");
	private JButton bt2 = new JButton("추가");

	public PaymentDialog() {

		this.setTitle("결제정보"); // 제목
		this.init(); // init실행
		this.start(); // start실행
		this.setSize(200, 180); // 현재 프레임 화면의 크기 지정 205.120
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// //////화면중앙에 놓는 부분//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////

	}

	public void init() { // 화면 구성 넣을 부분

		jrp = this.getRootPane(); // 제이루트판 상위 컴퍼넌트 돌려줌
		con = jrp.getContentPane(); // 컨테이너 돌려줌
		con.setLayout(new BorderLayout()); // 컨테이너의 레이아웃 매니저 설정

		// 프레임 짜는부분

		JPanel jp1 = new JPanel(new BorderLayout());
		jp1.add("West", bt1);
		jp1.add("Center", bt2);

		JPanel jp2 = new JPanel(new BorderLayout());
		jp2.add(lb6);
		jp1.add("North", jp2);	
		
		JPanel jp3 = new JPanel(new BorderLayout());
		jp3.add("West", lb5);
		jp3.add("Center", tf5);
		jp2.add("North", jp3);

		JPanel jp4 = new JPanel(new BorderLayout());
		jp4.add("West", lb4);
		jp4.add("Center", tf4);
		jp3.add("North", jp4);

		JPanel jp5 = new JPanel(new BorderLayout());
		jp5.add("West", lb3);
		jp5.add("Center", cb1);
		jp4.add("North", jp5);

		JPanel jp6 = new JPanel(new BorderLayout());
		jp6.add("West", lb2);
		jp6.add("Center", tf2);
		jp5.add("North", jp6);

		JPanel jp7 = new JPanel(new BorderLayout());
		jp7.add("West", lb1);
		jp7.add("Center", tf1);
		jp6.add("North", jp7);

		con.add("Center", jp1);
		
		cb1.addItem("남자");
		cb1.addItem("여자");

	}

	public void start() {
	}

	public void actionPerformed(ActionEvent e) {

	}
}