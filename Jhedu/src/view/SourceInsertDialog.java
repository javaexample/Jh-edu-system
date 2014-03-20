package view;

import javax.swing.JDialog;
import view.component.SourceInsertPanel;
import Crm.JHContext;
import java.awt.BorderLayout;

public class SourceInsertDialog extends JDialog{
	public SourceInsertDialog(JHContext ctx ) {
		
		SourceInsertPanel sourceInsertPanel = new SourceInsertPanel(ctx);
		getContentPane().add(sourceInsertPanel, BorderLayout.CENTER);
	}
	

}
