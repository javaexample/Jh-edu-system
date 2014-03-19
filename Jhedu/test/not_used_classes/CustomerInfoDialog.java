package not_used_classes;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.JPasswordField;

class CustomerInfoDialog extends JDialog implements ActionListener {

	public static void main(String[] ar) {

		try { // 이쁘게 해주는거
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}

		CustomerInfoDialog cInfoDialog = new CustomerInfoDialog();
		cInfoDialog.setToCenter();
		cInfoDialog.setVisible(true);

	}

	public CustomerInfoDialog() {

		this.setTitle("회원정보변경"); // 제목
		this.init(); // init실행
		this.start(); // start실행
		this.setSize(202, 585); // 프레임 크기지정
		this.setResizable(false); // 사이즈변경불가
	}
	
	public void setToCenter() {
		// //////화면중앙에 놓는 부분//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
		(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////
	}

	public void init() {
		// 화면 구성 넣을 부분
		this.setLayout(null); // 레이아웃 해제

		JLabel[] labels = new JLabel[13]; // 라벨배열 []안 개수만큼 생성
		JTextField[] TextFields = new JTextField[7]; // 텍스트필드배열[]개수만큼 생성

		// 열 이름 배열 생성
		String[] name = new String[] { "■ 기본정보", "순번", "이름", "성별", "나이",
				"연락처(일반)", "연락처(핸드폰)", "이메일", "담당자", "주소", "문의내용", "비고" };

		// 열 크기 배열 생성
		int[] lbs1 = new int[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
		int[] lbs2 = new int[] { 5, 30, 55, 80, 105, 130, 155, 180, 205, 230,
				310, 420 };
		int[] lbs3 = new int[] { 185, 90, 90, 90, 90, 90, 90, 90, 90, 185, 185,
				185 };
		int[] lbs4 = new int[] { 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20 };

		int[] tfs1 = new int[] { 100, 100, 100, 100, 100, 100 };
		int[] tfs2 = new int[] { 30, 55, 105, 130, 155, 180 };
		int[] tfs3 = new int[] { 90, 90, 90, 90, 90, 90 };
		int[] tfs4 = new int[] { 20, 20, 20, 20, 20, 20 };

		// 라벨 생성
		for (int i = 0; i < labels.length - 1; i++) {

			labels[i] = new JLabel(name[i], JLabel.CENTER);
			EtchedBorder eb = new EtchedBorder(EtchedBorder.RAISED);
			labels[i].setBorder(eb);
			this.add(labels[i]);
			labels[i].setAlignmentY(1);
			labels[i].setBounds(lbs1[i], lbs2[i], lbs3[i], lbs4[i]);

		}

		// 텍스트필드 생성
		for (int i = 0; i < TextFields.length - 1; i++) {
			TextFields[i] = new JTextField();
			this.add(TextFields[i]);
			TextFields[i].setBounds(tfs1[i], tfs2[i], tfs3[i], tfs4[i]);
			TextFields[0].setEnabled(false); //락거는거
		}

		// 콤보박스 1 생성
		JComboBox cb1 = new JComboBox();
		this.add(cb1);
		cb1.setBounds(100, 80, 90, 20);
		cb1.addItem("남자");
		cb1.addItem("여자");

		// 콤보박스 2 생성
		JComboBox cb2 = new JComboBox();
		this.add(cb2);
		cb2.setBounds(100, 205, 90, 20);
		cb2.addItem("윤병철");
		cb2.addItem("박희영");

		// 텍스트아리아1 생성
		JScrollPane sp1 = new JScrollPane();
		JTextArea ta1 = new JTextArea();
		ta1.setLineWrap(true);
		sp1.getViewport().add(ta1);
		this.add(sp1);
		sp1.setBounds(5, 255, 185, 50);

		// 텍스트아리아2 생성
		JScrollPane sp2 = new JScrollPane();
		JTextArea ta2 = new JTextArea();
		ta2.setLineWrap(true);
		sp2.getViewport().add(ta2);
		this.add(sp2);
		sp2.setBounds(5, 335, 185, 80);

		// 텍스트아리아3 생성
		JScrollPane sp3 = new JScrollPane();
		JTextArea ta3 = new JTextArea();
		ta3.setLineWrap(true);
		sp3.getViewport().add(ta3);
		this.add(sp3);
		sp3.setBounds(5, 445, 185, 80);

		// 버튼1 생성
		JButton bt1 = new JButton("뒤로");
		this.add(bt1);
		bt1.setBounds(5, 530, 70, 20);
		
		// 버튼2 생성
		JButton bt2 = new JButton("수정");
		this.add(bt2);
		bt2.setBounds(120, 530, 70, 20);

	}

	public void start() {

		// 뒤로가기시 이것만 업애는 거
		this.addWindowListener(new WindowAdapter() {
			public void WondowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //x버튼 비활성화

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}