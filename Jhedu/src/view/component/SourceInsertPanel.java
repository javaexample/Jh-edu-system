package view.component;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import java.awt.Insets;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import Crm.JHContext;
import dao.SourceDAO;
import model.SourceModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

/**
 * 아르바이트 입력용 페이지
 * @author Youngjae
 *
 */
public class SourceInsertPanel extends JPanel {
	private JTextField dateTextField;
	private JTextField timeTextField;
	private JTextField homePhoneTextField;
	private JTextField cellPhoneTextField;
	private JTextField nameTextField;
	private JTextField inquiryTextField;
	private JComboBox typeComboBox;
	private JComboBox genderCbBox;
	private JHContext ctx;
	
	public SourceInsertPanel(JHContext ctx) {
		this.ctx = ctx;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("\uC18C\uC2A4\uC885\uB958");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		typeComboBox = new JComboBox();
		GridBagConstraints gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_typeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeComboBox.gridx = 1;
		gbc_typeComboBox.gridy = 0;
		add(typeComboBox, gbc_typeComboBox);
		
		JLabel lblNewLabel_1 = new JLabel("\uC720\uC785\uB0A0\uC9DC");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		dateTextField = new JTextField();
		GridBagConstraints gbc_dateTextField = new GridBagConstraints();
		gbc_dateTextField.insets = new Insets(0, 0, 5, 0);
		gbc_dateTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateTextField.gridx = 1;
		gbc_dateTextField.gridy = 1;
		add(dateTextField, gbc_dateTextField);
		dateTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uC720\uC785\uC2DC\uAC04");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		timeTextField = new JTextField();
		GridBagConstraints gbc_timeTextField = new GridBagConstraints();
		gbc_timeTextField.insets = new Insets(0, 0, 5, 0);
		gbc_timeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_timeTextField.gridx = 1;
		gbc_timeTextField.gridy = 2;
		add(timeTextField, gbc_timeTextField);
		timeTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\uC77C\uBC18\uC804\uD654");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		homePhoneTextField = new JTextField();
		GridBagConstraints gbc_homePhoneTextField = new GridBagConstraints();
		gbc_homePhoneTextField.insets = new Insets(0, 0, 5, 0);
		gbc_homePhoneTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_homePhoneTextField.gridx = 1;
		gbc_homePhoneTextField.gridy = 3;
		add(homePhoneTextField, gbc_homePhoneTextField);
		homePhoneTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\uD734\uB300\uC804\uD654");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		cellPhoneTextField = new JTextField();
		GridBagConstraints gbc_cellPhoneTextField = new GridBagConstraints();
		gbc_cellPhoneTextField.insets = new Insets(0, 0, 5, 0);
		gbc_cellPhoneTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cellPhoneTextField.gridx = 1;
		gbc_cellPhoneTextField.gridy = 4;
		add(cellPhoneTextField, gbc_cellPhoneTextField);
		cellPhoneTextField.setColumns(10);
		
		JLabel label = new JLabel("\uC774\uB984");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 5;
		add(label, gbc_label);
		
		nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.gridx = 1;
		gbc_nameTextField.gridy = 5;
		add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("\uC131\uBCC4");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 6;
		add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		genderCbBox = new JComboBox();
		GridBagConstraints gbc_genderCbBox = new GridBagConstraints();
		gbc_genderCbBox.insets = new Insets(0, 0, 5, 0);
		gbc_genderCbBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_genderCbBox.gridx = 1;
		gbc_genderCbBox.gridy = 6;
		add(genderCbBox, gbc_genderCbBox);
		
		JLabel lblNewLabel_6 = new JLabel("\uBB38\uC758\uB0B4\uC6A9");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 7;
		add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		inquiryTextField = new JTextField();
		GridBagConstraints gbc_inquiryTextField = new GridBagConstraints();
		gbc_inquiryTextField.insets = new Insets(0, 0, 5, 0);
		gbc_inquiryTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_inquiryTextField.gridx = 1;
		gbc_inquiryTextField.gridy = 7;
		add(inquiryTextField, gbc_inquiryTextField);
		inquiryTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("\uC785\uB825\uC644\uB8CC");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processInsert();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 8;
		add(btnNewButton, gbc_btnNewButton);
	}
	
	private boolean isDuplicated(SourceDAO dao) {
		// TODO 중복 검사 로직
		String homePhone = homePhoneTextField.getText().trim();
		String cellPhone = cellPhoneTextField.getText().trim();
	
		try {
			
			StringBuilder sb = new StringBuilder();
			List<String> empList = null;
			if ( homePhone.length() > 0 ) {
				empList = dao.findSourcesByHomephone(homePhone ) ;
				
				
				sb.append("중복된 일반 전화번호 (" + homePhone + ")");
				sb.append("\n");
				sb.append("담당자 : ");
				for (int i = 0; i < empList.size(); i++) {
					sb.append("\n     " + empList.get(i));
				}
				
				dao.findSourcesByCellphone(cellPhone);
				
			}
			
			if (cellPhone.length() > 0 ) {
				empList = dao.findSourcesByCellphone( cellPhone ) ;
				sb.append("\n");
				sb.append("중복된 휴대전화번호 (" + cellPhone + ")");
				sb.append("\n");
				sb.append("담당자 : ");
				
				for (int i = 0; i < empList.size(); i++) {
					sb.append("\n      " + empList.get(i));
				}
				
				dao.findSourcesByCellphone(cellPhone);
			}
			
			JOptionPane.showMessageDialog(this, sb.toString());
			return true;
		} catch (SQLException e) {
			//
			return false;
		}
	
		
	}
	
	private void resetPage() {
		
//		typeComboBox.getSelectedItem();
		dateTextField.setText("");
		timeTextField.setText("");
		homePhoneTextField.setText("");
		cellPhoneTextField.setText("");
		nameTextField.setText("");
//		this.genderCbBox.getSelectedItem()
		inquiryTextField.setText("");
	}
	private void processInsert() {
//		String type = (String) typeComboBox.getSelectedItem();
//		String date = dateTextField.getText();
//		String time = timeTextField.getText();
//		String homePhone = homePhoneTextField.getText();
//		String cellPhone = cellPhoneTextField.getText();
//		String name = nameTextField.getText();
//		String gender = (String) this.genderCbBox.getSelectedItem();
//		String inquiry = inquiryTextField.getText();
		
		SourceDAO dao = this.ctx.getDAORegistry().getSourceDAO();
		if ( isDuplicated(dao) ) {
			// 중복 메세지 보여주고 중지
			return ;
		}
		SourceModel newSource = new SourceModel();
		
		newSource.setSourceType((String) typeComboBox.getSelectedItem() );
		newSource.setWhenContact(dateTextField.getText() );
		newSource.setContactTime( timeTextField.getText() );
		newSource.setHomePhone( homePhoneTextField.getText() );
		newSource.setCellPhone( cellPhoneTextField.getText() );
		newSource.setName( nameTextField.getText() );
		newSource.setGender( (String) this.genderCbBox.getSelectedItem() );
		newSource.setInquiry( inquiryTextField.getText() );
		
		
		try {
			dao.insertSource(newSource);
			JOptionPane.showMessageDialog(this, "새로운 소스가 입력되었습니다.");
			resetPage();
			
		} catch (SQLException e) {
			// 입력 실패시
			e.printStackTrace();
		}
		
		
	}

}
