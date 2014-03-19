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

	int num = 100; // 현재 줄개수 추후 변동
	int arr = 17; // 현재 배열 수
	private JRootPane jrp; // jrootpane 선언
	private Container con; // container 선언
	private JLabel lb1 = new JLabel(team + " " + name + "님 반갑습니다.");
	private JLabel lb2 = new JLabel(" ");
	private JButton bt1 = new JButton("소스추가");
	private JButton bt2 = new JButton("검색");
	private JButton bt3 = new JButton("새로고침");
	private JTextField tf1 = new JTextField();

	private JComboBox cb1 = new JComboBox();

	private DefaultTableModel dtm; // 디폴트테이블모델 선언
	private DefaultTableColumnModel dtcm; // 열핸들러 선언
	private DefaultListSelectionModel dlsm; // 리스트선택된 것 선언 오오...
	private JTable jt; // 제이테이블생성
	private JScrollPane jsp; // 스크롤판 생성
	private JTableHeader jth; // 제이테이블 헤더 관리

	// 열크기
	int[] ColumnSize = new int[] { 140, 210, 300, 130, 350, 350, 220, 110, 120,
			500, 700, 700, 220, 220, 220, 220, 220 };
	// 열이름
	String[] ColumnName = new String[] { "순번", "소스", "날짜", "시간", "연락처(일반)",
			"연락처(핸드폰)", "이름", "성별", "나이", "이메일", "주소", "문의내용", "비고", "담당자",
			"신청여부", "결제여부", "배송여부" };

	public SourceManagerDialog() {

		this.setTitle("소스관리페이지"); // 제목
		this.init(); // init실행
		this.init_1(); // init실행
		this.start(); // start실행
		this.setSize(1375, 770); // 현재 프레임 화면의 크기 지정 205.120
		this.setResizable(false);

		// //////화면중앙에 놓는 부분//////////////////////////////////////////
		Dimension di = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension di1 = this.getSize();
		this.setLocation((int) (di.getWidth() / 2 - di1.getWidth() / 2),
				(int) (di.getHeight() / 2 - di1.getHeight() / 2));
		// /////////////////////////////////////////////////////////////

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 종료되는거
	}

	public void init() { // 화면 구성 넣을 부분

		jrp = this.getRootPane(); // 제이루트판 상위 컴퍼넌트 돌려줌
		con = jrp.getContentPane(); // 컨테이너 돌려줌
		con.setLayout(new BorderLayout()); // 컨테이너의 레이아웃 매니저 설정
		JPanel jp1 = new JPanel(new BorderLayout()); // 레이아웃을 포함한 제이판넬 생성

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


		
		//테이블 헤더 정렬해주는 거
		jt.setAutoCreateRowSorter(true); 
		TableRowSorter ts1 = new TableRowSorter(jt.getModel());
		jt.setRowSorter(ts1);
		
	}

	public void init_1() { // 화면 구성 넣을 부분

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
		// this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //x버튼 비활성화
	}

	public void actionPerformed(ActionEvent e) {

		try {
		} catch (NumberFormatException ee) {
			return; // 리턴
		}
		// getContentPane().update(getContentPane().getGraphics()); 다시 창새로고침
		// 디자인만
	}
}
