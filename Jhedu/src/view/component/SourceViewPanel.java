package view.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import Crm.JHContext;
import view.SearchResultDialog;
import view.SourceEditDialog;
import view.SourceEditDialog.SourceUpdateListener;
import view.SourceInsertDialog;
import model.EmployeeModel;
import model.Role;
import model.RoleLevel;
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

	private JHContext ctx ;
	private JTextField sourceTypeTextField;
	private JComboBox comboBox;
	
	ReloadThread reloader ;
	
	public SourceViewPanel ( JHContext ctx  ) {
		
		this.setLayout(new BorderLayout());
		
		String [] columnNames = ctx.getBaseColumnNames();
		tableModel = new SourceTableModel(columnNames, modelVector);
		
		sourceTable = new JTable(tableModel);
		
		/* click listener 설치 */
		sourceTable.addMouseListener(new ClickListener());
		
//		if ( ctx.getRole().getLevel() == RoleLevel.TEAM_SUPPORT){
			
			JPanel searchPanel = new JPanel();
			add(searchPanel, BorderLayout.NORTH);
			GridBagLayout gbl_searchPanel = new GridBagLayout();
			searchPanel.setLayout(gbl_searchPanel);

			comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 0;
			searchPanel.add(comboBox, gbc_comboBox);
			
			sourceTypeTextField = new JTextField();
			sourceTypeTextField.addKeyListener(new KeyAdapter(){
				
				@Override
				public void keyTyped(KeyEvent e) {

					if ( e.getKeyChar() == '\n') {
						search();
					}
				}
			});
			
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
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			searchPanel.add(lblNewLabel, gbc_lblNewLabel);
			
			JButton btnNewButton = new JButton("\uC18C\uC2A4\uC785\uB825\uCC3D");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showSourceInputDialog();
				}
			});
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton.gridx = 4;
			gbc_btnNewButton.gridy = 0;
			searchPanel.add(btnNewButton, gbc_btnNewButton);
			
			install();
			
//		}
		
		
		
		JScrollPane scrollpane = new JScrollPane(sourceTable);
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		centerPanel.setLayout(new BorderLayout());

		centerPanel.add(scrollpane);
		scrollpane.setBounds(5, 40, 1290, 160);

		this.add(centerPanel);
		centerPanel.setBounds(5, 5, 1300, 200);

		this.setSize(1375, 770);
		
		this.ctx = ctx;
		
		this.editDialog = new SourceEditDialog(ctx, this);
		
		
		
		this.loadData();
		
		/* 데이터베이스 조회 스레드 실행시킴*/
		reloader = new ReloadThread(this, ctx);
		reloader.start();
		
	}
	
	private void install() {
		comboBox.addItem("이름");
		comboBox.addItem("일반전화");
		comboBox.addItem("휴대전화");
		comboBox.addItem("이메일");
		
	}
	
	
	private String readColumnToSearch(){
		return (String) comboBox.getSelectedItem();
	}
	
	private String readQueryText() {
		return sourceTypeTextField.getText().trim();
	}
	
	public void search() {
		try {
			SourceDAO dao = ctx.getDAORegistry().getSourceDAO();
			String column = readColumnToSearch();
			String type = readQueryText();
			
			// TODO type 값이 없으면 중지시켜야 함.
			
			List<SourceModel> sources = dao.findBySourceType(column, type);
			
			if ( sources.size() == 0) {
				
				return ;
				
			} else if ( sources.size() == 1 ){
				editDialog.showSource(sources.get(0));
				editDialog.setVisible(true);
				editDialog.setLocationRelativeTo(this);
				
			} else {
				SearchResultDialog searchResultDialog = new SearchResultDialog( ctx, this,this );
				searchResultDialog.showDialog(sources);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void loadData() {
		SourceDAO dao;
		try {
			dao = ctx.getDAORegistry().getSourceDAO(); /*DAORegistry.getInstance().getSourceDAO();*/
			EmployeeModel emp = ctx.getCurrentEmployee();
			String roleName = ctx.getRole().getRoleName();
			List<SourceModel> sources = null;
			
			// TODO 리팩토링 해야함. 
			if ( roleName.equals("학습지원")) {
				
				sources = dao.getSource("담당자", emp.getName() );
				
			} else if ( roleName.equals("학습운영")) {
				sources = dao.getSource("교재상태", "배송대기");
			} else if ( roleName.equals("회계")) {
				sources = dao.getSource("결제상태", "승인요청");
			} else {
				throw new RuntimeException("알 수 없는 부서 이름 : " + roleName);
			}
			
			
			final List<SourceModel> ref = sources;
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					tableModel.clearSources();
					drawSourceData(ref);
				}
			});
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void drawSourceData(List<SourceModel> sources ) {
		
		Iterator<SourceModel> it = sources.iterator();
		
		while ( it.hasNext()  ) {
			
			tableModel.addRow(it.next());
		}
		
	}
	
	private void showSourceInputDialog() {
		SourceInsertDialog dialog = new SourceInsertDialog(this.ctx);
		dialog.setSize(300, 250);
		dialog.setLocationRelativeTo(null);;
		dialog.setVisible(true);
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
				editDialog.setLocationRelativeTo(SourceViewPanel.this);
				editDialog.showSource( source);
				
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
		}
		
	}
	
	static class ReloadThread extends Thread  {
		
		JHContext ctx ;
		SourceViewPanel panel ;
		ReloadThread (SourceViewPanel panel, JHContext context ) {
			ctx = context;
			this.panel = panel ;
		}
		
		@Override
		public void run() {
			while ( true) {
				
				int interval = ctx.getReloadInterval();
				
				try {
					Thread.sleep(interval * 1000 );
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				panel.loadData();
			}
		}
	}


	@Override
	public void sourceUpdated(SourceModel source) {
		tableModel.updateSourceAt(source);
		
	}
}
