package Crm.unknown;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class Act3_1 extends JFrame implements ActionListener {

	public static void main(String[] ar) {

		try { // 이쁘게 해주는거
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}

		new Act3_1();
	}

	public Act3_1() {

		super("소스관리페이지"); // 제목
		this.init(); // init실행
		this.start(); // start실행
		this.setSize(1375, 770); // 현재 프레임 화면의 크기 지정 205.120
		this.setResizable(false);

		// //////화면중앙에 놓는 부분//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////

		this.setVisible(true); // 현재 프레임을 화면상에 나타나게 함.
	}

	public void init() { // 화면 구성 넣을 부분

	}

	public void start() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료되는거
	}

	public void actionPerformed(ActionEvent e) {

	}
}
