package Crm.unknown;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class Act3_1 extends JFrame implements ActionListener {

	public static void main(String[] ar) {

		try { // �̻ڰ� ���ִ°�
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}

		new Act3_1();
	}

	public Act3_1() {

		super("�ҽ�����������"); // ����
		this.init(); // init����
		this.start(); // start����
		this.setSize(1375, 770); // ���� ������ ȭ���� ũ�� ���� 205.120
		this.setResizable(false);

		// //////ȭ���߾ӿ� ���� �κ�//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////

		this.setVisible(true); // ���� �������� ȭ��� ��Ÿ���� ��.
	}

	public void init() { // ȭ�� ���� ���� �κ�

	}

	public void start() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����Ǵ°�
	}

	public void actionPerformed(ActionEvent e) {

	}
}
