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

		try { // �̻ڰ� ���ִ°�
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}

		CustomerInfoDialog cInfoDialog = new CustomerInfoDialog();
		cInfoDialog.setToCenter();
		cInfoDialog.setVisible(true);

	}

	public CustomerInfoDialog() {

		this.setTitle("ȸ����������"); // ����
		this.init(); // init����
		this.start(); // start����
		this.setSize(202, 585); // ������ ũ������
		this.setResizable(false); // �������Ұ�
	}
	
	public void setToCenter() {
		// //////ȭ���߾ӿ� ���� �κ�//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
		(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////
	}

	public void init() {
		// ȭ�� ���� ���� �κ�
		this.setLayout(null); // ���̾ƿ� ����

		JLabel[] labels = new JLabel[13]; // �󺧹迭 []�� ������ŭ ����
		JTextField[] TextFields = new JTextField[7]; // �ؽ�Ʈ�ʵ�迭[]������ŭ ����

		// �� �̸� �迭 ����
		String[] name = new String[] { "�� �⺻����", "����", "�̸�", "����", "����",
				"����ó(�Ϲ�)", "����ó(�ڵ���)", "�̸���", "�����", "�ּ�", "���ǳ���", "���" };

		// �� ũ�� �迭 ����
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

		// �� ����
		for (int i = 0; i < labels.length - 1; i++) {

			labels[i] = new JLabel(name[i], JLabel.CENTER);
			EtchedBorder eb = new EtchedBorder(EtchedBorder.RAISED);
			labels[i].setBorder(eb);
			this.add(labels[i]);
			labels[i].setAlignmentY(1);
			labels[i].setBounds(lbs1[i], lbs2[i], lbs3[i], lbs4[i]);

		}

		// �ؽ�Ʈ�ʵ� ����
		for (int i = 0; i < TextFields.length - 1; i++) {
			TextFields[i] = new JTextField();
			this.add(TextFields[i]);
			TextFields[i].setBounds(tfs1[i], tfs2[i], tfs3[i], tfs4[i]);
			TextFields[0].setEnabled(false); //���Ŵ°�
		}

		// �޺��ڽ� 1 ����
		JComboBox cb1 = new JComboBox();
		this.add(cb1);
		cb1.setBounds(100, 80, 90, 20);
		cb1.addItem("����");
		cb1.addItem("����");

		// �޺��ڽ� 2 ����
		JComboBox cb2 = new JComboBox();
		this.add(cb2);
		cb2.setBounds(100, 205, 90, 20);
		cb2.addItem("����ö");
		cb2.addItem("����");

		// �ؽ�Ʈ�Ƹ���1 ����
		JScrollPane sp1 = new JScrollPane();
		JTextArea ta1 = new JTextArea();
		ta1.setLineWrap(true);
		sp1.getViewport().add(ta1);
		this.add(sp1);
		sp1.setBounds(5, 255, 185, 50);

		// �ؽ�Ʈ�Ƹ���2 ����
		JScrollPane sp2 = new JScrollPane();
		JTextArea ta2 = new JTextArea();
		ta2.setLineWrap(true);
		sp2.getViewport().add(ta2);
		this.add(sp2);
		sp2.setBounds(5, 335, 185, 80);

		// �ؽ�Ʈ�Ƹ���3 ����
		JScrollPane sp3 = new JScrollPane();
		JTextArea ta3 = new JTextArea();
		ta3.setLineWrap(true);
		sp3.getViewport().add(ta3);
		this.add(sp3);
		sp3.setBounds(5, 445, 185, 80);

		// ��ư1 ����
		JButton bt1 = new JButton("�ڷ�");
		this.add(bt1);
		bt1.setBounds(5, 530, 70, 20);
		
		// ��ư2 ����
		JButton bt2 = new JButton("����");
		this.add(bt2);
		bt2.setBounds(120, 530, 70, 20);

	}

	public void start() {

		// �ڷΰ���� �̰͸� ���ִ� ��
		this.addWindowListener(new WindowAdapter() {
			public void WondowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //x��ư ��Ȱ��ȭ

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}