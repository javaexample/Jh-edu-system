package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class SourceManagerDialog extends JDialog implements ActionListener {

	String team;
	String name;

	int num = 100; // ���� �ٰ��� ���� ����
	int arr = 17; // ���� �迭 ��
	private JRootPane jrp; // jrootpane ����
	private Container con; // container ����
	private JLabel lb1 = new JLabel(team + " " + name + "�� �ݰ����ϴ�.");
	private JLabel lb2 = new JLabel(" ");
	private JButton bt1 = new JButton("�ҽ��߰�");
	private JButton bt2 = new JButton("�˻�");
	private JButton bt3 = new JButton("���ΰ�ħ");
	private JTextField tf1 = new JTextField();

	private JComboBox cb1 = new JComboBox();

	private DefaultTableModel dtm; // ����Ʈ���̺�� ����
	private DefaultTableColumnModel dtcm; // ���ڵ鷯 ����
	private DefaultListSelectionModel dlsm; // ����Ʈ���õ� �� ���� ����...
	private JTable jt; // �������̺����
	private JScrollPane jsp; // ��ũ���� ����
	private JTableHeader jth; // �������̺� ��� ����

	// ��ũ��
	int[] ColumnSize = new int[] { 140, 210, 300, 130, 350, 350, 220, 110, 120,
			500, 700, 700, 220, 220, 220, 220, 220 };
	// ���̸�
	String[] ColumnName = new String[] { "����", "�ҽ�", "��¥", "�ð�", "����ó(�Ϲ�)",
			"����ó(�ڵ���)", "�̸�", "����", "����", "�̸���", "�ּ�", "���ǳ���", "���", "�����",
			"��û����", "��������", "��ۿ���" };

	public SourceManagerDialog() {

		this.setTitle("�ҽ�����������"); // ����
		this.init(); // init����
		this.init_1(); // init����
		this.start(); // start����
		this.setSize(1375, 770); // ���� ������ ȭ���� ũ�� ���� 205.120
		this.setResizable(false);

		// //////ȭ���߾ӿ� ���� �κ�//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ����Ǵ°�
	}

	public void init() { // ȭ�� ���� ���� �κ�

		jrp = this.getRootPane(); // ���̷�Ʈ�� ���� ���۳�Ʈ ������
		con = jrp.getContentPane(); // �����̳� ������
		con.setLayout(new BorderLayout()); // �����̳��� ���̾ƿ� �Ŵ��� ����
		JPanel jp1 = new JPanel(new BorderLayout()); // ���̾ƿ��� ������ �����ǳ� ����

		dtm = new DefaultTableModel(num, arr);
		dtcm = new DefaultTableColumnModel();
		dlsm = new DefaultListSelectionModel();
		jt = new JTable(dtm, dtcm, dlsm);
		jsp = new JScrollPane(jt);

		DefaultTableCellRenderer[] DefaultTableCellRenderers = new DefaultTableCellRenderer[arr];
		for (int i = 0; i < DefaultTableCellRenderers.length; i++) {
			DefaultTableCellRenderers[i] = new DefaultTableCellRenderer();
		}

		JTextField[] TextFields = new JTextField[arr];
		for (int i = 0; i < TextFields.length; i++) {
			TextFields[i] = new JTextField();
			TextFields[i].setEditable(false);
		}

		DefaultCellEditor[] DefaultCellEditors = new DefaultCellEditor[arr];
		for (int i = 0; i < DefaultCellEditors.length; i++) {
			DefaultCellEditors[i] = new DefaultCellEditor(TextFields[i]);
		}

		cb1 = new JComboBox();
		DefaultCellEditors[5] = new DefaultCellEditor(cb1);
		
		TableColumn[] TableColumns = new TableColumn[arr];
		for (int i = 0; i < TableColumns.length; i++) {
			TableColumns[i] = new TableColumn(i, ColumnSize[i],
					DefaultTableCellRenderers[i], DefaultCellEditors[i]);
			TableColumns[i].setHeaderValue(ColumnName[i]);
			dtcm.addColumn(TableColumns[i]);
		}


		
		//���̺� ��� �������ִ� ��
		jt.setAutoCreateRowSorter(true); 
		TableRowSorter ts1 = new TableRowSorter(jt.getModel());
		jt.setRowSorter(ts1);
		
	}

	public void init_1() { // ȭ�� ���� ���� �κ�

		this.setLayout(null);
		this.add(bt1);
		this.add(lb1);
		this.add(bt2);
		this.add(bt3);
		this.add(tf1);
		this.add(jsp);
		bt1.setBounds(5, 5, 80, 25);
		lb1.setBounds(85, 5, 800, 25);
		bt2.setBounds(1200, 5, 80, 25);
		bt3.setBounds(1285, 5, 80, 25);
		tf1.setBounds(1075, 5, 120, 25);
		jsp.setBounds(5, 35, 1360, 700);

		jth = new JTableHeader(dtcm);
		jth.setReorderingAllowed(false);
		jt.setTableHeader(jth);

	}

	public void start() {
		// this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //x��ư ��Ȱ��ȭ
	}

	public void actionPerformed(ActionEvent e) {

		try {
		} catch (NumberFormatException ee) {
			return; // ����
		}
		// getContentPane().update(getContentPane().getGraphics()); �ٽ� â���ΰ�ħ
		// �����θ�
	}
}
