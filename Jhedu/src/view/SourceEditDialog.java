package view;

import javax.swing.JDialog;
import javax.swing.JFrame;

import model.Role;
import model.RoleLevel;
import model.SourceModel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import Crm.JHContext;
import view.component.SourceEditPanel;
import dao.DAORegistry;
import dao.SourceDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class SourceEditDialog extends JDialog {

	private SourceEditPanel editComp ;
	private SourceUpdateListener listener;
	
	private JHContext ctx;
	public SourceEditDialog (JHContext ctx, SourceUpdateListener parent) {
		
		this.setTitle("소스 편집");
		
		this.setSize(600, 700);
		
		this.listener = parent ;
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		editComp = new SourceEditPanel(ctx);
		getContentPane().add(editComp, BorderLayout.CENTER);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processEdit();
			}
		});
		editComp.add(btnEdit, BorderLayout.SOUTH);
		
		this.ctx = ctx;
	}
	
	public void showSource(SourceModel src) {
		editComp.showSource(src);
	}
	
	/*
	 *  변경 내역을 DB로 반영
	 */
	private void processEdit(){
		if ( editComp.isModifed()) {
			
			SourceModel source = editComp.getSourceModel();
			editComp.writeToSourceModel(source);
			
			try {
				SourceDAO dao = ctx.getDAORegistry().getSourceDAO();
				dao.updateSource(source);
				notifySourceUpdated(source);
						
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
