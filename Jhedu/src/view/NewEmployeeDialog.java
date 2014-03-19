package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

import model.EmployeeModel;
import dao.DAORegistry;
import dao.EmployeeDAO;

public class NewEmployeeDialog extends JDialog implements ActionListener {

	private JRootPane jrp; // jrootpane ����
	private Container con; // container ����

	private JLabel lb1 = new JLabel("    ���̵�       ");
	private JLabel lb2 = new JLabel("  ��й�ȣ      ");
	private JLabel lb3 = new JLabel(" ��й�ȣȮ�� ");
	private JLabel lb4 = new JLabel("     �̸�         ");
	private JLabel lb5 = new JLabel("    �μ���       ");
	private JLabel lb6 = new JLabel(" ");

	private JTextField tfIDField = new JTextField(""); // �����ؽ�Ʈ�ʵ� ����
	private JPasswordField pfPassField = new JPasswordField(); // �����佺�����ʵ� ����
	private JPasswordField pf2PassField = new JPasswordField();
	private JTextField tfEmpName = new JTextField(""); // �����ؽ�Ʈ�ʵ� ����
	private JComboBox cbPart = new JComboBox();

	private JButton btnBack = new JButton("�ڷ�");
	private JButton btnSubscribe = new JButton("����");

	public NewEmployeeDialog() {

		this.setTitle("ȸ������"); // ����
		this.init(); // init����
		this.start(); // start����
		this.setSize(200, 180); // ���� ������ ȭ���� ũ�� ���� 205.120
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// //////ȭ���߾ӿ� ���� �κ�//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////

		this.setVisible(true); // ���� �������� ȭ��� ��Ÿ���� ��.
	}

	public void init() { // ȭ�� ���� ���� �κ�

		jrp = this.getRootPane(); // ���̷�Ʈ�� ���� ���۳�Ʈ ������
		con = jrp.getContentPane(); // �����̳� ������
		con.setLayout(new BorderLayout()); // �����̳��� ���̾ƿ� �Ŵ��� ����

		// ������ ¥�ºκ�

		JPanel jp1 = new JPanel(new BorderLayout());
		jp1.add("West", btnBack);
		jp1.add("Center", btnSubscribe);

		JPanel jp2 = new JPanel(new BorderLayout());
		jp2.add(lb6);
		jp1.add("North", jp2);

		JPanel jp3 = new JPanel(new BorderLayout());
		jp3.add("West", lb5);
		jp3.add("Center", cbPart);
		jp2.add("North", jp3);

		JPanel jp4 = new JPanel(new BorderLayout());
		jp4.add("West", lb4);
		jp4.add("Center", tfEmpName);
		jp3.add("North", jp4);

		JPanel jp5 = new JPanel(new BorderLayout());
		jp5.add("West", lb3);
		jp5.add("Center", pf2PassField);
		jp4.add("North", jp5);

		JPanel jp6 = new JPanel(new BorderLayout());
		jp6.add("West", lb2);
		jp6.add("Center", pfPassField);
		jp5.add("North", jp6);

		JPanel jp7 = new JPanel(new BorderLayout());
		jp7.add("West", lb1);
		jp7.add("Center", tfIDField);
		jp6.add("North", jp7);

		con.add("Center", jp1);

		cbPart.addItem("�н�������");
		cbPart.addItem("�н����");
		cbPart.addItem("ȸ����");
		cbPart.addItem("������");
		cbPart.addItem("�Ƹ�����Ʈ");
		
		installListener();

	}
	
	private void installListener() {
		btnSubscribe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				processNewEmployee();
				
			}
		});
	}
	
	/*
	 * 1. ���� ����
	 * 2. dao ��û�� ������
	 * 3. ����� �޾Ƽ� ȭ�鿡 �����ָ� ��
	 */
	private void processNewEmployee(){
		String empID = tfIDField.getText();
		String empName = tfEmpName.getText();
		String empPassword = new String(pfPassField.getPassword());
		String empPassword2 = new String(pf2PassField.getPassword());
		
		EmployeeDAO dao;
		try {
			dao = DAORegistry.getInstance().getEmployeeDAO();
		
			// TODO 0. ��ȿ���� ���� �� Ȯ���ؾ���. 
			
			// 1. �н����� �Է� Ȯ��
			if ( !checkPassword(empPassword, empPassword2) ){				
				JOptionPane.showMessageDialog(this, "�н���Ʈ�� Ʋ���ϴ�.");
				return ;
			}
			
			// 2. ���̵� �ߺ� Ȯ��
			
			if ( dao.existsEmployee(empID)) {
				JOptionPane.showMessageDialog(this, "���̵� �ߺ�");
				return ;
			}
			
			
			String part = (String) cbPart.getSelectedItem();
			System.out.printf("%s %s %s %s", empID, empName, empPassword, part);
			EmployeeModel emp = dao.insertEmployee(new EmployeeModel(empID, empPassword, empName, part));
			JOptionPane.showMessageDialog(this, emp.toString() );
			
			closeDialog();
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}
		
			
			

		
		
	}
	
	private boolean checkPassword(String pass0, String pass1) {
		return pass0.equals(pass1);
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
		btnBack.addActionListener(this);
		// bt2.addActionListener(this);

		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����Ǵ°�
		// this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // x��ư ��Ȱ��ȭ
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnBack) {
			closeDialog();
		}
	}
	
	public void closeDialog(){
		setVisible(false);
	}
}