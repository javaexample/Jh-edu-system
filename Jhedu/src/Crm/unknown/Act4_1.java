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

		try { // �̻ڰ� ���ִ°�
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}

		new Act4_1();

	}
	
	private JRootPane jrp; // jrootpane ����
	private Container con; // container ����
	
	public Act4_1() {

		super("������"); // ����
		this.init(); // init����
		this.start(); // start����
		this.setSize(1000, 1000); // ���� ������ ȭ���� ũ�� ���� 205.120

		// //////ȭ���߾ӿ� ���� �κ�//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////

		this.setVisible(true); // ���� �������� ȭ��� ��Ÿ���� ��.
	}

	public void init() {
		// ȭ�� ���� ���� �κ�
		jrp = this.getRootPane(); 
		con = jrp.getContentPane(); // �����̳� ������
		con.setLayout(new BorderLayout()); // �����̳��� ���̾ƿ� �Ŵ��� ����
		String[] name = new String[] { "�� �⺻����", "����", "�̸�", "����", "����",
				"����ó(�Ϲ�)", "����ó(�ڵ���)", "�̸���", "�ּ�", "���ǳ���", "���", "�����" };
		
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
		String[] name = new String[] { "�� �⺻����", "����", "�̸�", "����", "����",
				"����ó(�Ϲ�)", "����ó(�ڵ���)", "�̸���", "�ּ�", "���ǳ���", "���", "�����" };
		
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
		cb1.addItem("����");
		cb1.addItem("����");*/
		
	}

	public void start() {
		// �̺�Ʈ�� Thread ó���� �κ�

		// join_btn.addActionListener(new Login_Act(this));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //x��ư ��Ȱ��ȭ

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}