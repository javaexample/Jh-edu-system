package view.component;

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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

//소스편집창
public class SourceEditPanel extends JPanel {
	private JTextField sourceNumField;
	private JTextField sourceDateField;
	
	private SourceModel currentSource ;
	private JComboBox<String> sourceTypeCBox;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public SourceEditPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("\uD68C\uC6D0\uC815\uBCF4\uCC3D");
		add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
//		gbl_panel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
//		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblSourceNumber = new JLabel("\uC18C\uC2A4\uBC88\uD638");
		GridBagConstraints gbc_lblSourceNumber = new GridBagConstraints();
		gbc_lblSourceNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblSourceNumber.anchor = GridBagConstraints.EAST;
		gbc_lblSourceNumber.gridx = 0;
		gbc_lblSourceNumber.gridy = 0;
		panel.add(lblSourceNumber, gbc_lblSourceNumber);
		
		sourceNumField = new JTextField();
		sourceNumField.setName("소스번호");
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
		
		JLabel lblSourceType = new JLabel("\uC18C\uC2A4\uC885\uB958");
		GridBagConstraints gbc_lblSourceType = new GridBagConstraints();
		gbc_lblSourceType.anchor = GridBagConstraints.EAST;
		gbc_lblSourceType.insets = new Insets(0, 0, 5, 5);
		gbc_lblSourceType.gridx = 0;
		gbc_lblSourceType.gridy = 1;
		panel.add(lblSourceType, gbc_lblSourceType);
		
		sourceTypeCBox = new JComboBox(new String[]{"메일", "전화"});
		sourceTypeCBox.setName("소스종류");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		panel.add(sourceTypeCBox, gbc_comboBox);
		
		JLabel lblDate = new JLabel("\uC720\uC785\uB0A0\uC9DC");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.EAST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
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
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		panel.add(sourceDateField, gbc_textField);
		sourceDateField.setColumns(10);
		
		JLabel lblTime = new JLabel("\uC720\uC785\uC2DC\uAC04");
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.anchor = GridBagConstraints.EAST;
		gbc_lblTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime.gridx = 0;
		gbc_lblTime.gridy = 3;
		panel.add(lblTime, gbc_lblTime);
		
		textField = new JTextField();
		gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\uC77C\uBC18\uC804\uD654");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 4;
		panel.add(label_1, gbc_label_1);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 4;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("\uD734\uB300\uC804\uD654");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 5;
		panel.add(label_2, gbc_label_2);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 5;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("\uC774\uB984");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 6;
		panel.add(label_3, gbc_label_3);
		
		JLabel label_4 = new JLabel("\uC131\uBCC4");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 7;
		panel.add(label_4, gbc_label_4);
		
		JLabel label_5 = new JLabel("\uB098\uC774");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 8;
		panel.add(label_5, gbc_label_5);
		
		JLabel label_6 = new JLabel("\uC774\uBA54\uC77C");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.insets = new Insets(0, 0, 0, 5);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 9;
		panel.add(label_6, gbc_label_6);
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
		sourceTypeCBox.setEnabled(editable);
	}
	
	
	public void setDateEditable(boolean editable) {
		// TODO 유입 날짜 수정 여부
	}
	
}
