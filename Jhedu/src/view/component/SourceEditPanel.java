package view.component;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import model.Role;
import model.SourceModel;
import model.role.ColumnAccess;
import model.role.RoleMapper;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import Crm.JHContext;

//소스편집창
public class SourceEditPanel extends JPanel {
	private JTextField sourceNumField;
	private JTextField sourceDateField;
	
	private SourceModel currentSource ;
	private JComboBox<String> sourceTypeCBox;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private JHContext ctx;
	private JPanel editContainer;
	private JComboBox chargeComboBox;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	
	public SourceEditPanel(JHContext ctx ) {
		this.ctx = ctx;
		setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("\uD68C\uC6D0\uC815\uBCF4\uCC3D");
		add(label, BorderLayout.NORTH);
		
		editContainer = new JPanel();
		add(editContainer, BorderLayout.CENTER);
		GridBagLayout gbl_editContainer = new GridBagLayout();
		gbl_editContainer.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_editContainer.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_editContainer.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		editContainer.setLayout(gbl_editContainer);
		
		JLabel lblSourceNumber = new JLabel("\uC18C\uC2A4\uBC88\uD638");
		GridBagConstraints gbc_lblSourceNumber = new GridBagConstraints();
		gbc_lblSourceNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblSourceNumber.gridx = 0;
		gbc_lblSourceNumber.gridy = 0;
		editContainer.add(lblSourceNumber, gbc_lblSourceNumber);
		
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
		gbc_sourceNumField.insets = new Insets(0, 0, 5, 5);
		gbc_sourceNumField.fill = GridBagConstraints.HORIZONTAL;
		gbc_sourceNumField.gridx = 1;
		gbc_sourceNumField.gridy = 0;
		editContainer.add(sourceNumField, gbc_sourceNumField);
		sourceNumField.setColumns(10);
		sourceNumField.setEditable(false);
		
		JLabel lblSourceType = new JLabel("\uC18C\uC2A4\uC885\uB958");
		GridBagConstraints gbc_lblSourceType = new GridBagConstraints();
		gbc_lblSourceType.insets = new Insets(0, 0, 5, 5);
		gbc_lblSourceType.gridx = 2;
		gbc_lblSourceType.gridy = 0;
		editContainer.add(lblSourceType, gbc_lblSourceType);
		
		sourceTypeCBox = new JComboBox(new String[]{"메일", "전화"});
		sourceTypeCBox.setEnabled(false);
		sourceTypeCBox.setName("소스종류");
		GridBagConstraints gbc_chargeComboBox = new GridBagConstraints();
		gbc_chargeComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_chargeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_chargeComboBox.gridx = 3;
		gbc_chargeComboBox.gridy = 0;
		editContainer.add(sourceTypeCBox, gbc_chargeComboBox);
		
		JLabel lblDate = new JLabel("\uC720\uC785\uB0A0\uC9DC");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 1;
		editContainer.add(lblDate, gbc_lblDate);
		
		sourceDateField = new JTextField();
		sourceDateField.setEnabled(false);
		sourceDateField.setName("\uC720\uC785\uB0A0\uC9DC");
		sourceDateField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				dateChanged();
			}
		});
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		editContainer.add(sourceDateField, gbc_textField);
		sourceDateField.setColumns(10);
		
		JLabel lblTime = new JLabel("\uC720\uC785\uC2DC\uAC04");
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime.gridx = 2;
		gbc_lblTime.gridy = 1;
		editContainer.add(lblTime, gbc_lblTime);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setName("\uC720\uC785\uC2DC\uAC04");
		gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		editContainer.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\uC77C\uBC18\uC804\uD654");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		editContainer.add(label_1, gbc_label_1);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setName("\uC77C\uBC18\uC804\uD654");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		editContainer.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("\uD734\uB300\uC804\uD654");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 2;
		gbc_label_2.gridy = 2;
		editContainer.add(label_2, gbc_label_2);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setName("\uD734\uB300\uC804\uD654");
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 2;
		editContainer.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("\uC774\uB984");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 3;
		editContainer.add(label_3, gbc_label_3);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setName("\uC774\uB984");
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 3;
		editContainer.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel label_4 = new JLabel("\uC131\uBCC4");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.EAST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 2;
		gbc_label_4.gridy = 3;
		editContainer.add(label_4, gbc_label_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setName("\uC131\uBCC4");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 3;
		editContainer.add(comboBox, gbc_comboBox);
		
		JLabel label_5 = new JLabel("\uB098\uC774");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.EAST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 4;
		editContainer.add(label_5, gbc_label_5);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setName("\uB098\uC774");
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 4;
		editContainer.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel label_6 = new JLabel("\uC774\uBA54\uC77C");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.EAST;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 2;
		gbc_label_6.gridy = 4;
		editContainer.add(label_6, gbc_label_6);
		
		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setName("\uC774\uBA54\uC77C");
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 3;
		gbc_textField_5.gridy = 4;
		editContainer.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JLabel label_7 = new JLabel("\uC8FC\uC18C");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.EAST;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 0;
		gbc_label_7.gridy = 5;
		editContainer.add(label_7, gbc_label_7);
		
		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		textField_6.setName("\uC8FC\uC18C");
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 5;
		editContainer.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JLabel label_8 = new JLabel("\uBB38\uC758\uB0B4\uC6A9");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.anchor = GridBagConstraints.EAST;
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 2;
		gbc_label_8.gridy = 5;
		editContainer.add(label_8, gbc_label_8);
		
		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setName("\uBB38\uC758\uB0B4\uC6A9");
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 0);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 3;
		gbc_textField_7.gridy = 5;
		editContainer.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
		JLabel label_9 = new JLabel("\uB2F4\uB2F9\uC790");
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.anchor = GridBagConstraints.EAST;
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 0;
		gbc_label_9.gridy = 6;
		editContainer.add(label_9, gbc_label_9);
		
		chargeComboBox = new JComboBox();
		chargeComboBox.setEnabled(false);
		
		chargeComboBox.setName("\uB2F4\uB2F9\uC790");
		gbc_chargeComboBox = new GridBagConstraints();
		gbc_chargeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_chargeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_chargeComboBox.gridx = 1;
		gbc_chargeComboBox.gridy = 6;
		editContainer.add(chargeComboBox, gbc_chargeComboBox);
		
		JLabel label_10 = new JLabel("\uC694\uB9DD\uB0A0\uC9DC");
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.anchor = GridBagConstraints.EAST;
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.gridx = 0;
		gbc_label_10.gridy = 7;
		editContainer.add(label_10, gbc_label_10);
		
		textField_8 = new JTextField();
		textField_8.setEnabled(false);
		textField_8.setName("\uC694\uB9DD\uB0A0\uC9DC");
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 1;
		gbc_textField_8.gridy = 7;
		editContainer.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\uC694\uB9DD\uC2DC\uAC04");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 7;
		editContainer.add(lblNewLabel, gbc_lblNewLabel);
		
		textField_9 = new JTextField();
		textField_9.setEnabled(false);
		textField_9.setName("\uC694\uB9DD\uC2DC\uAC04");
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.insets = new Insets(0, 0, 5, 0);
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 3;
		gbc_textField_9.gridy = 7;
		editContainer.add(textField_9, gbc_textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\uB9C8\uAC10\uB0A0\uC9DC");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 8;
		editContainer.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_10 = new JTextField();
		textField_10.setEnabled(false);
		textField_10.setName("\uB9C8\uAC10\uB0A0\uC9DC");
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10.gridx = 1;
		gbc_textField_10.gridy = 8;
		editContainer.add(textField_10, gbc_textField_10);
		textField_10.setColumns(10);
		
		JLabel label_11 = new JLabel("\uC18C\uC2A4\uC0C1\uD0DC");
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.anchor = GridBagConstraints.EAST;
		gbc_label_11.insets = new Insets(0, 0, 5, 5);
		gbc_label_11.gridx = 0;
		gbc_label_11.gridy = 9;
		editContainer.add(label_11, gbc_label_11);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setEnabled(false);
		comboBox_1.setName("\uC18C\uC2A4\uC0C1\uD0DC");
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 9;
		editContainer.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblNewLabel_3 = new JLabel("\uC624\uB354\uC77C\uC790");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 9;
		editContainer.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textField_11 = new JTextField();
		textField_11.setEnabled(false);
		textField_11.setName("\uC624\uB354\uC77C\uC790");
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.insets = new Insets(0, 0, 5, 0);
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.gridx = 3;
		gbc_textField_11.gridy = 9;
		editContainer.add(textField_11, gbc_textField_11);
		textField_11.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\uAE30\uC218");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 10;
		editContainer.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setEnabled(false);
		comboBox_2.setName("\uAE30\uC218");
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 1;
		gbc_comboBox_2.gridy = 10;
		editContainer.add(comboBox_2, gbc_comboBox_2);
		
		JLabel label_12 = new JLabel("\uAE09\uC218");
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.anchor = GridBagConstraints.EAST;
		gbc_label_12.insets = new Insets(0, 0, 5, 5);
		gbc_label_12.gridx = 2;
		gbc_label_12.gridy = 10;
		editContainer.add(label_12, gbc_label_12);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setEnabled(false);
		comboBox_3.setName("\uAE09\uC218");
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 3;
		gbc_comboBox_3.gridy = 10;
		editContainer.add(comboBox_3, gbc_comboBox_3);
		
		JLabel lblNewLabel_5 = new JLabel("\uACFC\uBAA9");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 11;
		editContainer.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		textField_12 = new JTextField();
		textField_12.setEnabled(false);
		textField_12.setName("\uACFC\uBAA9");
		GridBagConstraints gbc_textField_12 = new GridBagConstraints();
		gbc_textField_12.insets = new Insets(0, 0, 5, 5);
		gbc_textField_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_12.gridx = 1;
		gbc_textField_12.gridy = 11;
		editContainer.add(textField_12, gbc_textField_12);
		textField_12.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("\uD560\uC778\uC728");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 11;
		editContainer.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		textField_13 = new JTextField();
		textField_13.setEnabled(false);
		textField_13.setName("\uD560\uC778\uC728");
		GridBagConstraints gbc_textField_13 = new GridBagConstraints();
		gbc_textField_13.insets = new Insets(0, 0, 5, 0);
		gbc_textField_13.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_13.gridx = 3;
		gbc_textField_13.gridy = 11;
		editContainer.add(textField_13, gbc_textField_13);
		textField_13.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("\uB4F1\uB85D\uAE08");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 12;
		editContainer.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		textField_14 = new JTextField();
		textField_14.setEnabled(false);
		textField_14.setName("\uB4F1\uB85D\uAE08");
		GridBagConstraints gbc_textField_14 = new GridBagConstraints();
		gbc_textField_14.insets = new Insets(0, 0, 5, 5);
		gbc_textField_14.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_14.gridx = 1;
		gbc_textField_14.gridy = 12;
		editContainer.add(textField_14, gbc_textField_14);
		textField_14.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("\uACB0\uC81C\uC0C1\uD0DC");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 2;
		gbc_lblNewLabel_8.gridy = 12;
		editContainer.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setEnabled(false);
		comboBox_4.setName("\uACB0\uC81C\uC0C1\uD0DC");
		GridBagConstraints gbc_comboBox_4 = new GridBagConstraints();
		gbc_comboBox_4.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_4.gridx = 3;
		gbc_comboBox_4.gridy = 12;
		editContainer.add(comboBox_4, gbc_comboBox_4);
		
		JLabel lblNewLabel_9 = new JLabel("\uAD50\uC7AC\uC0C1\uD0DC");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 13;
		editContainer.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setEnabled(false);
		comboBox_5.setName("\uAD50\uC7AC\uC0C1\uD0DC");
		GridBagConstraints gbc_comboBox_5 = new GridBagConstraints();
		gbc_comboBox_5.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_5.gridx = 1;
		gbc_comboBox_5.gridy = 13;
		editContainer.add(comboBox_5, gbc_comboBox_5);
		
		JLabel lblNewLabel_10 = new JLabel("\uACB0\uC81C\uBC29\uBC95");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 2;
		gbc_lblNewLabel_10.gridy = 13;
		editContainer.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setEnabled(false);
		comboBox_6.setName("\uACB0\uC81C\uBC29\uBC95");
		GridBagConstraints gbc_comboBox_6 = new GridBagConstraints();
		gbc_comboBox_6.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_6.gridx = 3;
		gbc_comboBox_6.gridy = 13;
		editContainer.add(comboBox_6, gbc_comboBox_6);
		
		JLabel lblNewLabel_11 = new JLabel("\uCE74\uB4DC\uC885\uB958");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 14;
		editContainer.add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		textField_15 = new JTextField();
		textField_15.setEnabled(false);
		textField_15.setName("\uCE74\uB4DC\uC885\uB958");
		GridBagConstraints gbc_textField_15 = new GridBagConstraints();
		gbc_textField_15.insets = new Insets(0, 0, 5, 5);
		gbc_textField_15.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_15.gridx = 1;
		gbc_textField_15.gridy = 14;
		editContainer.add(textField_15, gbc_textField_15);
		textField_15.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("\uCE74\uB4DC\uBC88\uD638");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 2;
		gbc_lblNewLabel_12.gridy = 14;
		editContainer.add(lblNewLabel_12, gbc_lblNewLabel_12);
		
		textField_16 = new JTextField();
		textField_16.setEnabled(false);
		textField_16.setName("\uCE74\uB4DC\uBC88\uD638");
		GridBagConstraints gbc_textField_16 = new GridBagConstraints();
		gbc_textField_16.insets = new Insets(0, 0, 5, 0);
		gbc_textField_16.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_16.gridx = 3;
		gbc_textField_16.gridy = 14;
		editContainer.add(textField_16, gbc_textField_16);
		textField_16.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("\uC720\uD6A8\uAE30\uAC04\uC6D4");
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridx = 0;
		gbc_lblNewLabel_13.gridy = 15;
		editContainer.add(lblNewLabel_13, gbc_lblNewLabel_13);
		
		textField_17 = new JTextField();
		textField_17.setEnabled(false);
		textField_17.setName("\uC720\uD6A8\uAE30\uAC04\uC6D4");
		GridBagConstraints gbc_textField_17 = new GridBagConstraints();
		gbc_textField_17.insets = new Insets(0, 0, 5, 5);
		gbc_textField_17.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_17.gridx = 1;
		gbc_textField_17.gridy = 15;
		editContainer.add(textField_17, gbc_textField_17);
		textField_17.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("\uC720\uD6A8\uAE30\uAC04\uB144");
		GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
		gbc_lblNewLabel_14.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_14.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_14.gridx = 2;
		gbc_lblNewLabel_14.gridy = 15;
		editContainer.add(lblNewLabel_14, gbc_lblNewLabel_14);
		
		textField_18 = new JTextField();
		textField_18.setEnabled(false);
		textField_18.setName("\uC720\uD6A8\uAE30\uAC04\uB144");
		GridBagConstraints gbc_textField_18 = new GridBagConstraints();
		gbc_textField_18.insets = new Insets(0, 0, 5, 0);
		gbc_textField_18.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_18.gridx = 3;
		gbc_textField_18.gridy = 15;
		editContainer.add(textField_18, gbc_textField_18);
		textField_18.setColumns(10);
		
		JLabel label_13 = new JLabel("\uD560\uBD80\uAC1C\uC6D4");
		GridBagConstraints gbc_label_13 = new GridBagConstraints();
		gbc_label_13.anchor = GridBagConstraints.EAST;
		gbc_label_13.insets = new Insets(0, 0, 5, 5);
		gbc_label_13.gridx = 0;
		gbc_label_13.gridy = 16;
		editContainer.add(label_13, gbc_label_13);
		
		textField_19 = new JTextField();
		textField_19.setEnabled(false);
		textField_19.setName("\uD560\uBD80\uAC1C\uC6D4");
		GridBagConstraints gbc_textField_19 = new GridBagConstraints();
		gbc_textField_19.insets = new Insets(0, 0, 5, 5);
		gbc_textField_19.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_19.gridx = 1;
		gbc_textField_19.gridy = 16;
		editContainer.add(textField_19, gbc_textField_19);
		textField_19.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("\uC740\uD589\uBA85");
		GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints();
		gbc_lblNewLabel_15.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_15.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_15.gridx = 2;
		gbc_lblNewLabel_15.gridy = 16;
		editContainer.add(lblNewLabel_15, gbc_lblNewLabel_15);
		
		textField_20 = new JTextField();
		textField_20.setEnabled(false);
		textField_20.setName("\uC740\uD589\uBA85");
		GridBagConstraints gbc_textField_20 = new GridBagConstraints();
		gbc_textField_20.insets = new Insets(0, 0, 5, 0);
		gbc_textField_20.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_20.gridx = 3;
		gbc_textField_20.gridy = 16;
		editContainer.add(textField_20, gbc_textField_20);
		textField_20.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("\uD604\uAE08\uC601\uC218\uC99D\uBC1C\uAE09");
		GridBagConstraints gbc_lblNewLabel_16 = new GridBagConstraints();
		gbc_lblNewLabel_16.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_16.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_16.gridx = 0;
		gbc_lblNewLabel_16.gridy = 17;
		editContainer.add(lblNewLabel_16, gbc_lblNewLabel_16);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setEnabled(false);
		comboBox_7.setName("\uD604\uAE08\uC601\uC218\uC99D\uBC1C\uAE09");
		GridBagConstraints gbc_comboBox_7 = new GridBagConstraints();
		gbc_comboBox_7.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_7.gridx = 1;
		gbc_comboBox_7.gridy = 17;
		editContainer.add(comboBox_7, gbc_comboBox_7);
		
		JLabel lblNewLabel_17 = new JLabel("\uCE74\uB4DC\uC804\uD45C\uBC1C\uAE09");
		GridBagConstraints gbc_lblNewLabel_17 = new GridBagConstraints();
		gbc_lblNewLabel_17.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_17.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_17.gridx = 2;
		gbc_lblNewLabel_17.gridy = 17;
		editContainer.add(lblNewLabel_17, gbc_lblNewLabel_17);
		
		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setEnabled(false);
		comboBox_8.setName("\uCE74\uB4DC\uC804\uD45C\uBC1C\uAE09");
		GridBagConstraints gbc_comboBox_8 = new GridBagConstraints();
		gbc_comboBox_8.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_8.gridx = 3;
		gbc_comboBox_8.gridy = 17;
		editContainer.add(comboBox_8, gbc_comboBox_8);
		
		JLabel label_14 = new JLabel("\uC2B9\uC778\uBC88\uD638");
		GridBagConstraints gbc_label_14 = new GridBagConstraints();
		gbc_label_14.anchor = GridBagConstraints.EAST;
		gbc_label_14.insets = new Insets(0, 0, 5, 5);
		gbc_label_14.gridx = 0;
		gbc_label_14.gridy = 18;
		editContainer.add(label_14, gbc_label_14);
		
		textField_21 = new JTextField();
		textField_21.setEnabled(false);
		textField_21.setName("\uC2B9\uC778\uBC88\uD638");
		GridBagConstraints gbc_textField_21 = new GridBagConstraints();
		gbc_textField_21.insets = new Insets(0, 0, 5, 5);
		gbc_textField_21.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_21.gridx = 1;
		gbc_textField_21.gridy = 18;
		editContainer.add(textField_21, gbc_textField_21);
		textField_21.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uBE44\uACE0");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 19;
		editContainer.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 19;
		editContainer.add(scrollPane, gbc_scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setName("\uBE44\uACE0");
		scrollPane.setViewportView(textArea);
		
		initComponent();
	}
	
	private Component findComponent(List<Component> list, String compName ) {
		
		Iterator<Component> it = list.iterator();
		while ( it.hasNext() ) {
			Component comp = it.next();
			if ( compName.equals(comp.getName())) {
				return comp;
			}
		}		
		throw new RuntimeException("대응하는 컴포넌트를 찾지 못했음 : " + compName);
	}

	public void getAllComponents(ArrayList<Component> list, final Container c) {
		Component[] comps = c.getComponents();
		for (Component comp : comps) {
			list.add(comp);
			if (comp instanceof Container) {
				getAllComponents(list, (Container) comp);
			}
		}
	}
	  
	private void initComponent() {
		Role currentRole = ctx.getRole();
		RoleMapper mapper = ctx.getRoleMapper();
		
		Map<String, ColumnAccess> columnMap = mapper.listColumns(currentRole.getRoleName());
		
		ArrayList<Component> components = new ArrayList<Component>();
		getAllComponents(components, editContainer); // 자손 컴포넌트까지 모두다 찾아서 넣음.
		
		Iterator<String> it = columnMap.keySet().iterator();
		while ( it.hasNext()) {
			String key = it.next();
			ColumnAccess accInfo = columnMap.get(key);
			Component comp = findComponent(components, key);
			comp.setEnabled(accInfo.isWritable());
		}
		
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
}
