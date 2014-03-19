package not_used_classes;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import javax.swing.*;

public class CalendarDialog extends JPanel implements ActionListener {
	String[] days = {"일","월","화","수","목","금","토"} ;
	String[] months = {"1월","2월","3월",
			"4월","5월","6월","7월","8월","9월","10월",
			"11월","12월"} ;
	Monthly ins;
	int pointMonth;
	int pointYear;
	
	JPanel northPanel;
	JTextField yearTF;
	JButton nextMonth;
	JButton previousMonth;
	JComboBox monthsCB;
	
	JPanel centerPanel;
	private GridLayout layout;
	JButton[][] monthButtons = new JButton [6][7];
	JButton[] daysButton = new JButton[7];
	JFrame frame;
	
	int dayOfWeek;
	
	public CalendarDialog(JFrame _frame) {
		frame = _frame;
	}

	public void init() {
		setLayout(new BorderLayout());

		initNorthPanel();initNorthPanel();
		add(northPanel, BorderLayout.NORTH);
		initCenterPanel();
		add(centerPanel, BorderLayout.CENTER);
		action();
		ins = new Monthly();
		pointMonth = ins.getMonthOfYear() + 1;
		pointYear = ins.getYear();
		paint();
	}
	
	public void initNorthPanel() {
		yearTF = new JTextField(15);
		monthsCB = new JComboBox(months);
		yearTF.setEditable(false);
		nextMonth = new JButton("다음달");
		nextMonth.setFont(Font.getFont("돋움"));
		previousMonth = new JButton("이전달");
		previousMonth.setFont(Font.getFont("돋움"));
		
		northPanel = new JPanel();
		northPanel.add(previousMonth);
		northPanel.add(yearTF);
		northPanel.add(monthsCB);
		northPanel.add(nextMonth);
	}
	
	public void action() {
		yearTF.addActionListener(this);
		previousMonth.addActionListener(this);
		nextMonth.addActionListener(this);
		monthsCB.addActionListener(this);
	}
	
	public void initCenterPanel() {
		centerPanel = new JPanel();
		layout = new GridLayout(7, 7);
		centerPanel.setLayout(layout);

		for(int i = 0; i < daysButton.length; i++) {
			daysButton[i] = new JButton(days[i]);
			daysButton[i].setFont(Font.getFont("돋움"));
			daysButton[i].setEnabled(false);
			add(daysButton[i]);
		}

		for(int i = 0; i < monthButtons.length; i++) {
			for(int j = 0; j < monthButtons[0].length; j++) {
				monthButtons[i][j] = new JButton();
				monthButtons[i][j].addActionListener(this);
				add(monthButtons[i][j]);
			}
		}
	}
	
	public void actionPerformed(ActionEvent event){
		
		if(event.getSource() == monthsCB) {
			changeMonth(event);
			return;
		}

		if(event.getSource() == previousMonth) {
			if(pointMonth == 1) {
				pointMonth = 13;
				pointYear -= 1;
			}
			ins.setEntry( "01/" + (--pointMonth) + "/" + pointYear);
		}
		if(event.getSource() == nextMonth) {
			if(pointMonth == 12) {
				pointMonth = 0;
				pointYear += 1;
			}
			ins.setEntry( "01/" + (++pointMonth) + "/" + pointYear);
		}
		if(event.getSource() instanceof JButton) {
			JButton temp = (JButton)event.getSource();
			if(isNumber(temp.getText())) {
				//frame.setVisible(false);
			} 
		}
		paint();
	}
	
	public void changeMonth(ActionEvent e) {
		String temp =  (String)monthsCB.getSelectedItem();
		ins.setEntry( "01/" + (temp.substring(0, (temp.length() -1))) + "/" + pointYear);
		pointMonth = Integer.parseInt(temp.substring(0, (temp.length() -1)));
		paint();
	}
	
	public boolean isNumber(String str) {
		boolean result = true;
		if(str.equals("")) { result = false; }
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(c < 48 || c > 59) {
				result =  false;
				break;
			}
		}
		return result;
	}
	
	public void paint() {
		yearTF.setText("" + ins.getYear());
		for(int i = 0; i < daysButton.length; i++) {
			centerPanel.add(daysButton[i]);
		}
		
		for(int i = 0; i < monthButtons.length; i++) {
			for(int j = 0; j < monthButtons[0].length; j++) {
				monthButtons[i][j].setText("");
				centerPanel.add(monthButtons[i][j]);
			}
		}
		
		int column = ins.getDayOfWeek() - 1;
		int row = 0;
		for(int i = 0; i < ins.getDaysOfMonth(); i++) {
			if((column % 7 == 0) && (column != 0)) {
				row++;
				column = 0;
			}
			monthButtons[row][column].setText(""+(i+1));
			column++;
		}

		for(int i = 0; i < months.length; i++) {
			int temp =  Integer.parseInt(months[i].substring(0, 
								(months[i].length() - 1)));
			if((ins.getMonthOfYear() + 1) == (temp)) {
				monthsCB.setSelectedIndex(temp - 1);
				break;
			}
		}
		updateUI();
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		CalendarDialog sa = new CalendarDialog(frame);
		sa.init();
		frame.setContentPane(sa);
		frame.setSize(400, 300);
		frame.setVisible(true);
	}
	
	static class Monthly {
		
		private GregorianCalendar calendar = null;
		private String entry = null;
		private Date dina=null;
		
		public Monthly(){
				if (dina==null){
					dina = new Date();
					calendar = new GregorianCalendar();
					calendar.setTime(dina);  
					entry = "01/"
							+ (calendar.get(Calendar.MONTH) + 1) + "/"
							+ calendar.get(Calendar.YEAR);
					setEntry(entry);
				}
		}

		public void actionPerformed(ActionEvent e) {
			JButton temp = (JButton)e.getSource();
			//frame.setVisible(false);
			System.out.println(temp.getText());
		}
		public void setEntry(String _entry){
			entry = _entry;
			try {
				dina = DateFormat.getDateInstance
				(DateFormat.SHORT,Locale.FRANCE).parse(entry);
				calendar = new GregorianCalendar();
				calendar.setTime(dina);
			} catch(ParseException e) {
				System.out.println(e);
			}  
		}
		
		public int getDaysOfMonth() {
			int num;
			switch(calendar.get(Calendar.MONTH)){
				case 0: case 2: case 4: case 6: case 7: case 9: case 11:
					num=31;
					break;
				case 1:
					if((calendar.isLeapYear(getYear()))&&(getYear() % 1000!=0))
					num=29;
				else
					num=28;
				break;
					default:
					num=30;
			}
		
			return num;
		}
		
		public int getYear() {
			return calendar.get(Calendar.YEAR);
		}
		
		public int getMonthOfYear() {
			return calendar.get(Calendar.MONTH) ;   
		}
		
		public int getDayOfWeek() {
			return	calendar.get(Calendar.DAY_OF_WEEK) ; 
		}
	}
}



