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

	private JRootPane jrp; // jrootpane ����
	private Container con; // container ����

	private JLabel lb1 = new JLabel(" �Ϲ���ȭ  ");
	private JLabel lb2 = new JLabel("   �ڵ���   ");
	private JLabel lb3 = new JLabel("    ����     ");
	private JLabel lb4 = new JLabel("   ����   ");
	private JLabel lb5 = new JLabel("    ���     ");
	private JLabel lb6 = new JLabel(" ��¥ �� �ð��� �ڵ��Էµ˴ϴ�.");

	private JTextField tf1 = new JTextField(""); // �����ؽ�Ʈ�ʵ� ����
	private JTextField tf2 = new JTextField("");
	private JComboBox cb1 = new JComboBox();
	private JTextField tf4 = new JTextField("");
	private JTextField tf5 = new JTextField("");

	private JButton bt1 = new JButton("�ڷ�");
	private JButton bt2 = new JButton("�߰�");

	public PaymentDialog() {

		this.setTitle("��������"); // ����
		this.init(); // init����
		this.start(); // start����
		this.setSize(200, 180); // ���� ������ ȭ���� ũ�� ���� 205.120
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// //////ȭ���߾ӿ� ���� �κ�//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////

	}

	public void init() { // ȭ�� ���� ���� �κ�

		jrp = this.getRootPane(); // ���̷�Ʈ�� ���� ���۳�Ʈ ������
		con = jrp.getContentPane(); // �����̳� ������
		con.setLayout(new BorderLayout()); // �����̳��� ���̾ƿ� �Ŵ��� ����

		// ������ ¥�ºκ�

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
		
		cb1.addItem("����");
		cb1.addItem("����");

	}

	public void start() {
	}

	public void actionPerformed(ActionEvent e) {

	}
}