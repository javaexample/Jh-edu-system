package backup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import view.SourceEditDialog;
import view.SourceEditDialog.SourceUpdateListener;
import model.Role;
import model.SourceModel;
import model.SourceTableModel;
import dao.DAORegistry;
import dao.SourceDAO;

public class SourceViewDialog extends JDialog implements SourceUpdateListener {

	private Vector<SourceModel> modelVector = new Vector<SourceModel>();
	private SourceTableModel tableModel ;
	private JTable sourceTable  ;
	
	SourceEditDialog editDialog = new SourceEditDialog(SourceViewDialog.this);
	private Role role ;
	
	public SourceViewDialog ( Role role  ) {
		
		this.setTitle("소스관리페이지"); // 타이틀 이름
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		String [] columnName = new String[] {
			"소스번호", "소스종류", "유입날짜"	
		};
		tableModel = new SourceTableModel(columnName, modelVector);
		
		sourceTable = new JTable(tableModel);
		
		/* click listener 설치 */
		sourceTable.addMouseListener(new ClickListener());
		
		JScrollPane scrollpane = new JScrollPane(sourceTable);
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		panel.setLayout(new BorderLayout());

		panel.add(scrollpane);
		scrollpane.setBounds(5, 40, 1290, 160);

		this.add(panel);
		panel.setBounds(5, 5, 1300, 200);

		this.setSize(1375, 770);
		
		this.role = role;
		
		this.loadData();
	}
	
	public void loadData() {
		System.out.println("ddd");
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
				editDialog.showSource( role, source);
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
	}


	@Override
	public void sourceUpdated(SourceModel source) {
		System.out.println(" 오냐? ");
		tableModel.updateSourceAt(source);
		
	}
}
