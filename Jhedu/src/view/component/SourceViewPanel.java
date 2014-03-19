package view.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import Crm.NewMain.JHContext;
import view.SourceEditDialog;
import view.SourceEditDialog.SourceUpdateListener;
import model.Role;
import model.SourceModel;
import model.SourceTableModel;
import dao.DAORegistry;
import dao.SourceDAO;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SourceViewPanel extends JPanel implements SourceUpdateListener {

	private Vector<SourceModel> modelVector = new Vector<SourceModel>();
	private SourceTableModel tableModel ;
	private JTable sourceTable  ;
	
	SourceEditDialog editDialog ;
//	private Role role ;
	private JHContext ctx ;
	private JTextField sourceTypeTextField;
	private JComboBox comboBox;
	
	public SourceViewPanel ( JHContext ctx  ) {
		
		this.setLayout(new BorderLayout());
		
		String [] columnName = new String[] {
			"소스번호", "소스종류", "유입날짜"	
		};
		tableModel = new SourceTableModel(columnName, modelVector);
		
		sourceTable = new JTable(tableModel);
		
		/* click listener 설치 */
		sourceTable.addMouseListener(new ClickListener());
		
		JPanel searchPanel = new JPanel();
		add(searchPanel, BorderLayout.NORTH);
		GridBagLayout gbl_searchPanel = new GridBagLayout();
//		gbl_searchPanel.columnWidths = new int[]{0, 0, 0, 0};
//		gbl_searchPanel.rowHeights = new int[] {0, 0, 0, 0};
//		gbl_searchPanel.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
//		gbl_searchPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		searchPanel.setLayout(gbl_searchPanel);
		
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		searchPanel.add(comboBox, gbc_comboBox);
		
		sourceTypeTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		searchPanel.add(sourceTypeTextField, gbc_textField);
		sourceTypeTextField.setColumns(10);
		
		JButton btnBtn = new JButton("Btn");
		btnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		GridBagConstraints gbc_btnBtn = new GridBagConstraints();
		gbc_btnBtn.anchor = GridBagConstraints.EAST;
		gbc_btnBtn.insets = new Insets(0, 0, 5, 0);
		gbc_btnBtn.gridx = 3;
		gbc_btnBtn.gridy = 0;
		searchPanel.add(btnBtn, gbc_btnBtn);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.weightx = 1.0;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		searchPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollpane = new JScrollPane(sourceTable);
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		panel.setLayout(new BorderLayout());

		panel.add(scrollpane);
		scrollpane.setBounds(5, 40, 1290, 160);

		this.add(panel);
		panel.setBounds(5, 5, 1300, 200);

		this.setSize(1375, 770);
		
		this.ctx = ctx;
		
		this.editDialog = new SourceEditDialog(ctx, this);
		
		install();
		this.loadData();
		
	}
	
	private void install() {
		comboBox.addItem("소스종류");
//		comboBox.addItem("유입날짜");
		
		
	}
	
	
	private String readSourceType() {
		return sourceTypeTextField.getText().trim();
	}
	public void search() {
		try {
			SourceDAO dao = DAORegistry.getInstance().getSourceDAO();
			String type = readSourceType();
			
			// TODO type 값이 없으면 중지시켜야 함.
			
			List<SourceModel> sources = dao.findBySourceType(type);
			
			tableModel.clearSources();
			
			drawSourceData(sources);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void loadData() {
		SourceDAO dao;
		try {
			dao = DAORegistry.getInstance().getSourceDAO();
			List<SourceModel> sources = dao.getSource();
			
			drawSourceData(sources);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void drawSourceData(List<SourceModel> sources ) {
		
		Iterator<SourceModel> it = sources.iterator();
		
		while ( it.hasNext()  ) {
			
			tableModel.addRow(it.next());
		}
		
	}
	
	class ClickListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// double click 일때만 처리합니다.
			if ( e.getClickCount() != 2) {
				return ;
			}
			int row = sourceTable.getSelectedRow();

			int sourceID = tableModel.getSourceAt(row).getId();
			
			try {
				DAORegistry reg = DAORegistry.getInstance();
				
				SourceDAO dao = reg.getSourceDAO();
				
				SourceModel source = dao.getSourceById( sourceID );
				
				editDialog.setVisible(true);			
				editDialog.showSource( source);
				
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
		}
		
	}


	@Override
	public void sourceUpdated(SourceModel source) {
		tableModel.updateSourceAt(source);
		
	}
}
