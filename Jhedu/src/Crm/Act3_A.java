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

		try { // 이쁘게 해주는거
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}
		new Act3_A();
	}

	// 변수 선언부분
	int row = 100; //나중에 디비 크기만큼으로 지정해야됨.
	
	// 1번 열 크기
	int[] ColumnSize1 = new int[] { 140, 210, 300, 130, 350, 380, 220, 110, 120,
			500, 700, 700, 220, 220, 220, 220, 220 };
	
	// 2번 열 크기
	int[] ColumnSize2 = new int[] { 140, 140, 300, 220, 140, 380, 220, 300, 500,
			300, 140, 300, 140, 140, 220, 220, 300, 300, 140, 300, 300, 500, 300 };
	
	// 1번 열이름
	String[] ColumnName1 = new String[] { "순번", "소스종류", "날짜", "시간", "연락처(일반)",
			"연락처(핸드폰)", "이름", "성별", "나이", "이메일", "주소", "문의내용", "비고", "담당자",
			"신청여부", "결제여부", "배송여부" };
	
	// 2번 열이름
	String[] ColumnName2 = new String[] { "순번", "날짜", "첫콜시간", "이름", "성별", "연락처(일반)",
			"연락처(핸드폰)", "메일주소" , "집주소" , "소스종류", "상태", "오더일자", "급수", "과목", "할인율", "등록금", "막콜날짜",
			"막콜시간", "상태", "요망날짜", "요망시간" , "비고" ,  "마감날짜"};

	JButton bt1 = new JButton("새로고침");
	JButton bt2 = new JButton("추가");

	public Act3_A() {
		super("소스관리페이지");
		this.init(); // init실행
		this.start(); // start실행
		this.setSize(1650, 950); // 현재 프레임 화면의 크기 지정 205.120
		//this.setResizable(false);
		this.setVisible(true); // 현재 프레임을 화면상에 나타나게 함.
		// //////화면중앙에 놓는 부분//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////

	}

	public void init() {
		JRootPane jrp = this.getRootPane(); // jrootpane 선언
		Container con = jrp.getContentPane();
		con.setLayout(new BorderLayout()); // 컨테이너의 레이아웃 매니저 설정

		// 제이패널생성
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel jp4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		// 1번 테이블 생성
		
		JTable tb1 = new JTable(row, ColumnName1.length); //테이블생성
		JScrollPane jsp1 = new JScrollPane(tb1);

		for (int i = 0; i < ColumnName1.length; i++) { //열이름설정
			tb1.getTableHeader().getColumnModel().getColumn(i)
					.setHeaderValue(ColumnName1[i]);
		}
		
		for (int i = 0; i < ColumnSize1.length; i++) { //열크기설정
			tb1.getColumnModel().getColumn(i).setPreferredWidth(ColumnSize1[i]);
		}

		DefaultTableModel dtm = new DefaultTableModel(ColumnSize1.length,ColumnName1.length){
		public boolean isCellEditable(int rowIndex, int ColumnIndex){
			return false;
		}
		};
		
		tb1.getTableHeader().setReorderingAllowed(false); //열이동불가
		//tb1.getTableHeader().setResizingAllowed(false); //열조절불가
		// 여기까지 1번 테이블 생성
	
		// 2번 테이블 생성

		JTable tb2 = new JTable(row, ColumnName2.length); //테이블생성
		JScrollPane jsp2 = new JScrollPane(tb2);

		for (int i = 0; i < ColumnName2.length; i++) { //열이름설정
			tb2.getTableHeader().getColumnModel().getColumn(i)
					.setHeaderValue(ColumnName2[i]);
		}
				
		for (int i = 0; i < ColumnSize2.length; i++) { //열크기설정
			tb2.getColumnModel().getColumn(i).setPreferredWidth(ColumnSize2[i]);
		}
		
		tb2.getTableHeader().setReorderingAllowed(false); //열이동불가
		//tb2.getTableHeader().setResizingAllowed(false); //열조절불가
		// 여기까지 2번 테이블 생성
		
		// 제이패널 구성
		jp1.add(bt1);
		jp2.add(bt2);
		jp1.add(jsp1);
		jp2.add(jsp2);
		jp1.setLayout(new BorderLayout()); // 위치 내맘대로 지정할 수 있게
		jp2.setLayout(new BorderLayout());
		bt1.setBounds(5, 15, 80, 20);
		bt2.setBounds(5, 15, 80, 20);
		jsp1.setBounds(5, 40, 1490, 160);
		jsp2.setBounds(5, 40, 1490, 650);
		
		// 콘테이너에 제이패널추가
		con.add(jp1);
		con.add(jp2);
		//con.add(jp3);
		//con.add(jp4);

		//제이패널 이름입력
		TitledBorder bd1 = new TitledBorder("내소스"); 
		TitledBorder bd2 = new TitledBorder("소스상세정보"); 
		//TitledBorder bd3 = new TitledBorder("결제정보"); 
		//TitledBorder bd4 = new TitledBorder("환불정보"); 
		jp1.setBorder(bd1);
		jp2.setBorder(bd2);
		//jp3.setBorder(bd3);
		//jp4.setBorder(bd4);

		// 제이패널 크기지정
		con.setLayout(new BorderLayout()); // 위치 내맘대로 지정할 수 있게
		jp1.setBounds(5, 5, 1500, 200);
		jp2.setBounds(5, 210, 1500, 700);
		//jp3.setBounds(1310, 5, 250, 550);
		//jp4.setBounds(1310, 560, 250, 200);

		//테이블 열클릭시 정렬기능
		tb1.setAutoCreateRowSorter(true); 
		TableRowSorter trs1 = new TableRowSorter(tb1.getModel());
		tb1.setRowSorter(trs1);
		
		tb2.setAutoCreateRowSorter(true); 
		TableRowSorter trs2 = new TableRowSorter(tb2.getModel());
		tb2.setRowSorter(trs2);
		
	}

	public void start() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료되는거
	}
	public void actionPerformed(ActionEvent arg0) {

	}
}