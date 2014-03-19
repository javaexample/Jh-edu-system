package view.component;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Crm.JHContext;
import view.SourceEditDialog;
import dao.DAORegistry;
import dao.SourceDAO;
import model.Role;
import model.SourceModel;
import model.SourceTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SupervisorPanel extends JPanel {
	private JTable table;
	private JComboBox<String> lv0SearchCombobox;
	private JComboBox<String> lv1SearchCombobox;
	
	private HashMap<String, List<String>> searchData = new HashMap<>();

	private String [] columnName;
	private SourceTableModel tableModel;
		
	
	private JHContext ctx ;
	/**
	 * Create the panel.
	 */
	public SupervisorPanel(JHContext ctx) {
		
		this.ctx = ctx;
		this.columnName = ctx.getBaseColumnNames();
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel searchPanel = new JPanel();
		add(searchPanel, BorderLayout.NORTH);
		GridBagLayout gbl_searchPanel = new GridBagLayout();
		gbl_searchPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_searchPanel.rowHeights = new int[]{0, 0, 0};
		gbl_searchPanel.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_searchPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		searchPanel.setLayout(gbl_searchPanel);
		
		JLabel lblHello = new JLabel("??? 님 반갑습니다.");
		GridBagConstraints gbc_lblHello = new GridBagConstraints();
		gbc_lblHello.weightx = 1.0;
		gbc_lblHello.insets = new Insets(0, 0, 5, 5);
		gbc_lblHello.anchor = GridBagConstraints.WEST;
		gbc_lblHello.gridx = 0;
		gbc_lblHello.gridy = 0;
		searchPanel.add(lblHello, gbc_lblHello);
		
		lv0SearchCombobox = new JComboBox<String>();
		GridBagConstraints gbc_lv0SearchCombobox = new GridBagConstraints();
		gbc_lv0SearchCombobox.anchor = GridBagConstraints.EAST;
		gbc_lv0SearchCombobox.insets = new Insets(4, 0, 5, 5);
		gbc_lv0SearchCombobox.gridx = 1;
		gbc_lv0SearchCombobox.gridy = 0;
		searchPanel.add(lv0SearchCombobox, gbc_lv0SearchCombobox);
		
		lv1SearchCombobox = new JComboBox<String>();
		GridBagConstraints gbc_lv1SearchCombobox = new GridBagConstraints();
		gbc_lv1SearchCombobox.insets = new Insets(4, 0, 5, 0);
		gbc_lv1SearchCombobox.fill = GridBagConstraints.HORIZONTAL;
		gbc_lv1SearchCombobox.gridx = 2;
		gbc_lv1SearchCombobox.gridy = 0;
		searchPanel.add(lv1SearchCombobox, gbc_lv1SearchCombobox);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 0;
		searchPanel.add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);
		
		tableModel = new SourceTableModel(columnName);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		installSearchForm();
		installListener();
		
		search();
		

	}
	
	private void installSearchForm() {
		ArrayList<String> list0 = new ArrayList<>();
		list0.add("홀딩+가망");
		list0.add("홀딩");
		list0.add("가망");
		list0.add("오더");
		list0.add("폐기");
		
		searchData.put("소스상태", list0);
		
		
		lv0SearchCombobox.addItem("소스상태");
		
		updateComboBox(lv0SearchCombobox.getSelectedItem());
		
	}
	
	private void installListener() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("???");
				if ( e.getClickCount() == 2) {
					processSourceDetailDialog();
				}
			}
		});
	}
	
	/*
	 * 상세 정보 창 보여주기
	 */
	private void processSourceDetailDialog() {
		int selectedRow = table.getSelectedRow();
		SourceModel source = tableModel.getSourceAt(selectedRow);
		
		SourceEditDialog editDialog = new SourceEditDialog(ctx, null);
		editDialog.setVisible(true);
		editDialog.setLocationRelativeTo(this);
		
		editDialog.showSource(source);
	}
	
	private void updateComboBox(Object lv0Item){
		List<String> comboValues = searchData.get(lv0Item);
		Iterator<String> it = comboValues.iterator();
		
		lv1SearchCombobox.removeAllItems();
		
		while ( it.hasNext() ) {
			lv1SearchCombobox.addItem(it.next());
		}
		
	}
	
	private String [] createStates() {
		String type = (String) lv1SearchCombobox.getSelectedItem();
		int pos = type.indexOf("+");
		String [] types = null;
		if ( pos >= 0 ) {
			types = new String [2];
			types[0] = type.substring(0, pos);
			types[1] = type.substring(pos+1);
			
		} else {
			types = new String []{type};
			
		}
		
		return types;
	}
	
	public void search() {
		// TODO combobox 의 값을 잡아서 뿌려줌.
		
		try {
			SourceDAO dao = DAORegistry.getInstance().getSourceDAO();
			
			String [] states = createStates();
			
			List<SourceModel> sourceList = dao.findBySourceState(states);

			updateSource(sourceList);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 새로운 소스 정보를 테이블에 출력함
	 * @param sourceList
	 */
	public void updateSource(List<SourceModel> sourceList) {
		tableModel.clearSources(); // 다 날려버림
		tableModel.addSources(sourceList);
	}

}
