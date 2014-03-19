package Crm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

class Act3_A extends JFrame implements ActionListener {

	public static void main(String[] ar) {

		try { // �̻ڰ� ���ִ°�
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}
		new Act3_A();
	}

	// ���� ����κ�
	int row = 100; //���߿� ��� ũ�⸸ŭ���� �����ؾߵ�.
	
	// 1�� �� ũ��
	int[] ColumnSize1 = new int[] { 140, 210, 300, 130, 350, 380, 220, 110, 120,
			500, 700, 700, 220, 220, 220, 220, 220 };
	
	// 2�� �� ũ��
	int[] ColumnSize2 = new int[] { 140, 140, 300, 220, 140, 380, 220, 300, 500,
			300, 140, 300, 140, 140, 220, 220, 300, 300, 140, 300, 300, 500, 300 };
	
	// 1�� ���̸�
	String[] ColumnName1 = new String[] { "����", "�ҽ�����", "��¥", "�ð�", "����ó(�Ϲ�)",
			"����ó(�ڵ���)", "�̸�", "����", "����", "�̸���", "�ּ�", "���ǳ���", "���", "�����",
			"��û����", "��������", "��ۿ���" };
	
	// 2�� ���̸�
	String[] ColumnName2 = new String[] { "����", "��¥", "ù�ݽð�", "�̸�", "����", "����ó(�Ϲ�)",
			"����ó(�ڵ���)", "�����ּ�" , "���ּ�" , "�ҽ�����", "����", "��������", "�޼�", "����", "������", "��ϱ�", "���ݳ�¥",
			"���ݽð�", "����", "�����¥", "����ð�" , "���" ,  "������¥"};

	JButton bt1 = new JButton("���ΰ�ħ");
	JButton bt2 = new JButton("�߰�");

	public Act3_A() {
		super("�ҽ�����������");
		this.init(); // init����
		this.start(); // start����
		this.setSize(1650, 950); // ���� ������ ȭ���� ũ�� ���� 205.120
		//this.setResizable(false);
		this.setVisible(true); // ���� �������� ȭ��� ��Ÿ���� ��.
		// //////ȭ���߾ӿ� ���� �κ�//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////

	}

	public void init() {
		JRootPane jrp = this.getRootPane(); // jrootpane ����
		Container con = jrp.getContentPane();
		con.setLayout(new BorderLayout()); // �����̳��� ���̾ƿ� �Ŵ��� ����

		// �����гλ���
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel jp4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		// 1�� ���̺� ����
		
		JTable tb1 = new JTable(row, ColumnName1.length); //���̺����
		JScrollPane jsp1 = new JScrollPane(tb1);

		for (int i = 0; i < ColumnName1.length; i++) { //���̸�����
			tb1.getTableHeader().getColumnModel().getColumn(i)
					.setHeaderValue(ColumnName1[i]);
		}
		
		for (int i = 0; i < ColumnSize1.length; i++) { //��ũ�⼳��
			tb1.getColumnModel().getColumn(i).setPreferredWidth(ColumnSize1[i]);
		}

		DefaultTableModel dtm = new DefaultTableModel(ColumnSize1.length,ColumnName1.length){
		public boolean isCellEditable(int rowIndex, int ColumnIndex){
			return false;
		}
		};
		
		tb1.getTableHeader().setReorderingAllowed(false); //���̵��Ұ�
		//tb1.getTableHeader().setResizingAllowed(false); //�������Ұ�
		// ������� 1�� ���̺� ����
	
		// 2�� ���̺� ����

		JTable tb2 = new JTable(row, ColumnName2.length); //���̺����
		JScrollPane jsp2 = new JScrollPane(tb2);

		for (int i = 0; i < ColumnName2.length; i++) { //���̸�����
			tb2.getTableHeader().getColumnModel().getColumn(i)
					.setHeaderValue(ColumnName2[i]);
		}
				
		for (int i = 0; i < ColumnSize2.length; i++) { //��ũ�⼳��
			tb2.getColumnModel().getColumn(i).setPreferredWidth(ColumnSize2[i]);
		}
		
		tb2.getTableHeader().setReorderingAllowed(false); //���̵��Ұ�
		//tb2.getTableHeader().setResizingAllowed(false); //�������Ұ�
		// ������� 2�� ���̺� ����
		
		// �����г� ����
		jp1.add(bt1);
		jp2.add(bt2);
		jp1.add(jsp1);
		jp2.add(jsp2);
		jp1.setLayout(new BorderLayout()); // ��ġ ������� ������ �� �ְ�
		jp2.setLayout(new BorderLayout());
		bt1.setBounds(5, 15, 80, 20);
		bt2.setBounds(5, 15, 80, 20);
		jsp1.setBounds(5, 40, 1490, 160);
		jsp2.setBounds(5, 40, 1490, 650);
		
		// �����̳ʿ� �����г��߰�
		con.add(jp1);
		con.add(jp2);
		//con.add(jp3);
		//con.add(jp4);

		//�����г� �̸��Է�
		TitledBorder bd1 = new TitledBorder("���ҽ�"); 
		TitledBorder bd2 = new TitledBorder("�ҽ�������"); 
		//TitledBorder bd3 = new TitledBorder("��������"); 
		//TitledBorder bd4 = new TitledBorder("ȯ������"); 
		jp1.setBorder(bd1);
		jp2.setBorder(bd2);
		//jp3.setBorder(bd3);
		//jp4.setBorder(bd4);

		// �����г� ũ������
		con.setLayout(new BorderLayout()); // ��ġ ������� ������ �� �ְ�
		jp1.setBounds(5, 5, 1500, 200);
		jp2.setBounds(5, 210, 1500, 700);
		//jp3.setBounds(1310, 5, 250, 550);
		//jp4.setBounds(1310, 560, 250, 200);

		//���̺� ��Ŭ���� ���ı��
		tb1.setAutoCreateRowSorter(true); 
		TableRowSorter trs1 = new TableRowSorter(tb1.getModel());
		tb1.setRowSorter(trs1);
		
		tb2.setAutoCreateRowSorter(true); 
		TableRowSorter trs2 = new TableRowSorter(tb2.getModel());
		tb2.setRowSorter(trs2);
		
	}

	public void start() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����Ǵ°�
	}
	public void actionPerformed(ActionEvent arg0) {

	}
}