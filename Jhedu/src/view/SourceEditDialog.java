package view;

import javax.swing.JDialog;
import javax.swing.JFrame;

import model.Role;
import model.SourceModel;
import model.Role.RoleLevel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import view.component.SourceEditPanel;
import dao.DAORegistry;
import dao.SourceDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class SourceEditDialog extends JDialog {

	private SourceEditPanel editComp ;
	private SourceUpdateListener listener;
	
	public SourceEditDialog (SourceUpdateListener parent) {
		
		this.setTitle("소스 편집");
		
		this.setSize(300, 400);
		
		this.listener = parent ;
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		editComp = new SourceEditPanel();
		getContentPane().add(editComp, BorderLayout.CENTER);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processEdit();
			}
		});
		editComp.add(btnEdit, BorderLayout.SOUTH);
	}
	
	public void showSource(Role role, SourceModel src) {
		editComp.showSource(src);
		RoleLevel level = role.getLevel();
		
		if ( level == Role.RoleLevel.SUPERVISOR) {
			this.editComp.setTypeEditable(true);
		} else if ( level == Role.RoleLevel.TEAM_SUPPORT ) {
			this.editComp.setTypeEditable(false) ;
		} else {
			throw new RuntimeException("알 수 없는 role level : " + role );
		}
	}
	
	/*
	 *  변경 내역을 DB로 반영
	 */
	private void processEdit(){
		if ( editComp.isModifed()) {
			
			SourceModel source = editComp.getSourceModel();
			
			DAORegistry registry;
			try {
				
				registry = DAORegistry.getInstance();
				SourceDAO dao = registry.getSourceDAO();
				dao.updateSource(source);
				notifySourceUpdated(source);
						
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		this.setVisible(false); 
	}

	protected void notifySourceUpdated(SourceModel source) {
		
		if ( this.listener != null) {
			this.listener.sourceUpdated(source);
		}
	}
	public void addUpdateListener(SourceUpdateListener listener) {
		
		this.listener = listener ;
		
	}
	
	public static interface SourceUpdateListener {
		public void sourceUpdated(SourceModel source) ;
	}
	
	
}
