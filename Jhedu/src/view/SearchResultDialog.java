package view;

import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;

import Crm.JHContext;
import model.SourceModel;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import view.SourceEditDialog.SourceUpdateListener;

public class SearchResultDialog extends JDialog {

	private JHContext ctx;
	private JList list;
	private DefaultListModel listModel ;
	private Container opener ;
	private SourceEditDialog dialog ;
	
	private SourceUpdateListener editListener ;
	
	public SearchResultDialog( JHContext ctx, Container opener, SourceUpdateListener listener ) {
		this.ctx = ctx;
		this.opener = opener;
		this.editListener = listener ;
		setTitle("검색결과");
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		listModel = new DefaultListModel<>();
		list = new JList(listModel);
		scrollPane.setViewportView(list);
		
		list.setCellRenderer(new SourceRenderder());
		
		
		installListener();
	}
	
	private void installListener() {
		list.addMouseListener(new ClickListener());
	}
	
	public void showDialog(List<SourceModel> sources) {
		
		Iterator<SourceModel> it = sources.iterator();
		
		listModel.clear();
		while ( it.hasNext()) {
			SourceModel src = it.next();
			
			listModel.addElement(src);
		}
		
//		this.setSize(400, 250);
		this.pack();
		this.setLocationRelativeTo(opener);
		this.setVisible(true);
	}
	
	class ClickListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int index =  list.getSelectedIndex();
			SourceModel source = (SourceModel) listModel.getElementAt(index);
			System.out.println("clicked : " + source);
			SourceEditDialog dialog = getEditDialog();
			dialog.showSource(source);
			
			dialog.setLocationRelativeTo(opener);
			dialog.setVisible(true);
		}
	}
	
	private SourceEditDialog getEditDialog() {
		if ( dialog == null) {
			dialog = new SourceEditDialog(ctx, editListener);
		}
		return dialog;
	}
	
	static class SourceRenderder extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list,
				Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			JLabel thisLabel = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			
			SourceModel src = (SourceModel) value;
			String text = "[" + src.getSourceId() + " : " + src.getName() + ", " + src.getHomePhone() + ", " + src.getCellPhone() + ", " + src.getEmail() + "]";
			thisLabel.setText(text);
			
			return this;
		}
		
		
	}
}
