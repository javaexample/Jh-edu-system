package Crm.unknown;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

class Act_10101010 extends JFrame implements ActionListener {

	public static void main(String[] ar) {

		try { // �̻ڰ� ���ִ°�
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}

		new Act_10101010();
	}

	private JRootPane jrp; // jrootpane ����
	private Container con; // container ����

	public Act_10101010() {

		super("�α���"); // ����
		this.init(); // init����
		this.start(); // start����
		

		// //////ȭ���߾ӿ� ���� �κ�//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////
		
		this.setSize(500, 300); // ���� ������ ȭ���� ũ�� ���� 205.120
		
		this.setVisible(true); // ���� �������� ȭ��� ��Ÿ���� ��.
	}

	public void init() { // ȭ�� ���� ���� �κ�

		jrp = this.getRootPane(); // ���̷�Ʈ�� ���� ���۳�Ʈ ������
		con = jrp.getContentPane(); // �����̳� ������
		con.setLayout(new BorderLayout()); // �����̳��� ���̾ƿ� �Ŵ��� ����
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // ���̾ƿ��� ������ �����ǳ� ����
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
		
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����Ǵ°�
		// this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //x��ư ��Ȱ��ȭ
	}

	public void actionPerformed(ActionEvent e) {


	}
}