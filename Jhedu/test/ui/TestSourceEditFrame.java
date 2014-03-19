package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import model.SourceModel;

import view.SourceEditDialog;
import view.SourceEditDialog.SourceUpdateListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestSourceEditFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try { // 이쁘게 해주는거
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ee) {
		}

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestSourceEditFrame frame = new TestSourceEditFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestSourceEditFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnOpenEditDialog = new JButton("OPen Edit Dialog");
		btnOpenEditDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openEditDialog();
			}
		});
		contentPane.add(btnOpenEditDialog, BorderLayout.CENTER);
		
		txtManager = new JTextField();
		txtManager.setText("manager");
		contentPane.add(txtManager, BorderLayout.NORTH);
		txtManager.setColumns(10);
	}

	private void openEditDialog(){
		SourceEditDialog dialog = new SourceEditDialog(null, new EditListener());
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}
	
	static class EditListener implements SourceUpdateListener {

		@Override
		public void sourceUpdated(SourceModel source) {
			// TEST Auto-generated method stub
			
		}
		
	}

}
