package testtest;

import javax.swing.table.*;
import javax.swing.*;

import java.sql.*;

class Test extends JFrame {
	private JTable jTable;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private Object[] col;
	private Object[][] data;

	// init ����
	public Test() {
		init();
	}

	// init �޼ҵ�
	private void init() {
		try {
			// ����̹��ε� �ڳؼǾ���
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager
					.getConnection("jdbc:sqlserver://211.233.89.202:1433;databaseName=sjedu;user=sjedu;password=jhoneedu@;");
			String sql = "select * from CRMSAUCE";
			
			pstmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			// �� getResultSetSize �Լ����� last()�Լ��� ����ϱ� ���ؼ� ����
			// ��� ���⼭�� ���������� ����� �˾ƿ��� ���� �ξ� ȿ�����̴�.

			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();

			col = new Object[rsmd.getColumnCount()];
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				col[i - 1] = rsmd.getColumnName(i);
			}

			int initNum = getResultSetSize(rs);
			data = new Object[initNum][];
			int index = 0;
			while (rs.next()) {
				data[index++] = new Object[] { rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getString(14) };
			}
		} catch (SQLException sqlExecption) {
			sqlExecption.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		DefaultTableModel tableModel = new DefaultTableModel(data, col);
		DefaultTableCellRenderer tableRender = new DefaultTableCellRenderer();
		tableRender.setHorizontalAlignment(JLabel.CENTER);

		jTable = new JTable(tableModel);

		for (int i = 0; i < jTable.getColumnCount(); i++) {
			jTable.getColumnModel().getColumn(i).setCellRenderer(tableRender); //���
		}

		JScrollPane jsp = new JScrollPane(jTable);

		this.add(jsp);
		this.setTitle("JFrame");
		this.setSize(413, 235);
		this.setVisible(true);
	}

	private int getResultSetSize(ResultSet resultSet) throws SQLException 
	{
		int size = -1;
		resultSet.last();
		size = resultSet.getRow();
		resultSet.beforeFirst();

		return size;
	}

	public static void main(String[] args) {
		new Test();
	}
}
