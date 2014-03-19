package backup;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import model.SourceModel;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusAdapter;
import javax.swing.JComboBox;

public class SourceEditPanel extends JPanel {
	private JTextField sourceNumField;
	private JTextField sourceDateField;
	
	private SourceModel currentSource ;
	private JComboBox comboBox;
	
	public SourceEditPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("\uD68C\uC6D0\uC815\uBCF4\uCC3D");
		add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblSourceNumber = new JLabel("Source Number");
		GridBagConstraints gbc_lblSourceNumber = new GridBagConstraints();
		gbc_lblSourceNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblSourceNumber.anchor = GridBagConstraints.EAST;
		gbc_lblSourceNumber.gridx = 0;
		gbc_lblSourceNumber.gridy = 0;
		panel.add(lblSourceNumber, gbc_lblSourceNumber);
		
		sourceNumField = new JTextField();
		sourceNumField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				numFieldChanged();
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});

		GridBagConstraints gbc_sourceNumField = new GridBagConstraints();
		gbc_sourceNumField.insets = new Insets(0, 0, 5, 0);
		gbc_sourceNumField.fill = GridBagConstraints.HORIZONTAL;
		gbc_sourceNumField.gridx = 1;
		gbc_sourceNumField.gridy = 0;
		panel.add(sourceNumField, gbc_sourceNumField);
		sourceNumField.setColumns(10);
		sourceNumField.setEditable(false);
		
		JLabel lblSourceType = new JLabel("Source Type");
		GridBagConstraints gbc_lblSourceType = new GridBagConstraints();
		gbc_lblSourceType.anchor = GridBagConstraints.EAST;
		gbc_lblSourceType.insets = new Insets(0, 0, 5, 5);
		gbc_lblSourceType.gridx = 0;
		gbc_lblSourceType.gridy = 1;
		panel.add(lblSourceType, gbc_lblSourceType);
		
		comboBox = new JComboBox(new String[]{"메일", "전화"});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		panel.add(comboBox, gbc_comboBox);
		
		JLabel lblDate = new JLabel("Date");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.EAST;
		gbc_lblDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 2;
		panel.add(lblDate, gbc_lblDate);
		
		sourceDateField = new JTextField();
		sourceDateField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				dateChanged();
			}
		});
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		panel.add(sourceDateField, gbc_textField);
		sourceDateField.setColumns(10);
	}
	
	/**
	 * 소스 출력 메소드
	 * @param src
	 */
	public void showSource(SourceModel src) {
		
		this.sourceNumField.setText("" + src.getId());
//		this.sourceTypeField.setText( src.getSourceType());
		this.sourceDateField.setText( src.getWhenContact());
		
		currentSource = src;
	}
	
	public SourceModel getSourceModel() {
		return currentSource;
	}

	private void numFieldChanged( ) {
		currentSource.setId(Integer.parseInt(sourceNumField.getText()) );
	}
	
	private void dateChanged() {
		currentSource.setWhenContact(sourceDateField.getText());
	}
	
	
	public boolean isModifed() {
		// TODO 변경 내역 확인하는 로직 구현해야 함.
		return true;
	}

	
	
	public void setTypeEditable(boolean editable) {
		comboBox.setEnabled(editable);
	}
	
	
	public void setDateEditable(boolean editable) {
		// TODO 유입 날짜 수정 여부
	}
	
}
