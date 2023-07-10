package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

@SuppressWarnings("serial")
public class CarRentalGUI extends JPanel implements ActionListener {
	private Font fnt = new Font("맑은 고딕", Font.BOLD, 15);

	private JLabel selectedCarLabel;
	private JTextField selectedCarTextField;
	private JLabel rentalDateLabel;
	private JTextField rentalDateTextField;
	private JLabel returnDateLabel;
	private JTextField returnDateTextField;
	private JLabel paymentLabel;
	private JTextField paymentTextField;
	private JLabel paymentMethodLabel;
	private JMenuBar paymentMethodMenuBar;
	private JMenuItem creditCardMenuItem;
	private JMenuItem bankTransferMenuItem;
	private JButton backButton;
	private JButton paymentButton;

	private ImageIcon icon = new ImageIcon("img/calendar.png");
	private Image im = icon.getImage();
	private Image im2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	private ImageIcon icon2 = new ImageIcon(im2);
	private JLabel startCalendar = new JLabel(icon2);

	// calendar가 켜졌을때는 한번 더 open되지 않도록 제한사항을 부여
	int calendarWindowTest = 0;
	// 클릭 횟수 감지
	private int clickCheck = 0;

	public CarRentalGUI() {
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 50, 50);

		// 첫 번째 열
		JPanel firstColumnPanel = new JPanel(new GridBagLayout());
		firstColumnPanel.setBackground(Color.WHITE);

		selectedCarLabel = new JLabel("선택한 차 종 : ");
		selectedCarTextField = new JTextField();
		selectedCarLabel.setFont(fnt);
		selectedCarTextField.setFont(fnt);
		
		rentalDateLabel = new JLabel("대여 날짜 : ");
		rentalDateTextField = new JTextField();
		rentalDateLabel.setFont(fnt);
		rentalDateTextField.setFont(fnt);
		
		icon = new ImageIcon("./image/calendar_icon.jpg");
		im = icon.getImage();
		im2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon2 = new ImageIcon(im2);
		startCalendar = new JLabel(icon2);

		returnDateLabel = new JLabel("반납 날짜 : ");
		returnDateTextField = new JTextField();
		returnDateLabel.setFont(fnt);
		returnDateTextField.setFont(fnt);

		paymentLabel = new JLabel("결제할 금액 : ");
		paymentTextField = new JTextField();
		paymentLabel.setFont(fnt);
		paymentTextField.setFont(fnt);

		paymentMethodLabel = new JLabel("결제 방법 : ");
		paymentMethodLabel.setFont(fnt);
		
		paymentButton = new JButton("결제하기");
		paymentButton.setFont(fnt);
		paymentButton.addActionListener(this);
		
		backButton = new JButton("뒤로가기");
		backButton.setFont(fnt);
		backButton.addActionListener(this);

		// 선택한 차 종(0열 0행)
		c.gridx = 0;
		c.gridy = 0;
		firstColumnPanel.add(selectedCarLabel, c);

		// 대여 날짜(1행 추가)
		c.gridy++;
		firstColumnPanel.add(rentalDateLabel, c);

		// 반납 날짜
		c.gridy++;
		firstColumnPanel.add(returnDateLabel, c);

		// 결제할 금액
		c.gridy++;
		firstColumnPanel.add(paymentLabel, c);

		// 결제 방법
		c.gridy++;
		firstColumnPanel.add(paymentMethodLabel, c);

		// 결제하기
		c.gridy++;
		firstColumnPanel.add(paymentButton, c);

		// 선택한 차 종 TextField(1열)
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL; // 가로 방향으로 컴포넌트가 채워지도록 설정
		firstColumnPanel.add(selectedCarTextField, c);
		selectedCarTextField.setPreferredSize(new Dimension(150, 25));

		// 대여 날짜 TextField
		c.gridy++;
		firstColumnPanel.add(rentalDateTextField, c);

		// 반납 날짜 TextField
		c.gridy++;
		firstColumnPanel.add(returnDateTextField, c);
		
		// 결제할 금액 TextField
		c.gridy++;
		firstColumnPanel.add(paymentTextField, c);
		
		// 결제 방법 선택 항목
		c.gridy++;
		JComboBox<String> paymentMethodCombo = new JComboBox<String>();
		paymentMethodCombo.addItem("신용카드");
		paymentMethodCombo.addItem("계좌이체");
		paymentMethodCombo.setSelectedIndex(-1); // 처음 보여질 선택 항목 없음으로 설정

		paymentMethodCombo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        
		    }
		});
		
		firstColumnPanel.add(paymentMethodCombo, c);

		// 뒤로가기
		c.gridy++;
		firstColumnPanel.add(backButton, c);

		// 달력 아이콘
		c.gridx = 2;
		c.gridy = 1;
		firstColumnPanel.add(startCalendar, c);

		// firstColumnPanel을 첫번째 행에 배치
		c.gridx = 0;
		c.gridy = 0;
		add(firstColumnPanel, c);

		startCalendar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				me.getSource();
				if (calendarWindowTest == 0) {
					new CustomCalendar();
					calendarWindowTest = 1;
				}
			}
		});
		
		setBackground(Color.white);
		setSize(900, 800);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
	
	
	
	
/////////////////////////////////////////////////////////////////////////////////
	class CustomCalendar extends JFrame implements ActionListener, WindowListener {

		// 상단 지역
		JPanel bar = new JPanel();
		JButton lastMonth = new JButton("◀");

		JComboBox<Integer> yearCombo = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
		
		JLabel yLbl = new JLabel("년 ");

		JComboBox<Integer> monthCombo = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
		
		JLabel mLbl = new JLabel("월");
		JButton nextMonth = new JButton("▶");

		// 중앙 지역
		JPanel center = new JPanel(new BorderLayout());
		// 중앙 상단 지역
		JPanel cntNorth = new JPanel(new GridLayout(0, 7));
		// 중앙 중앙 지역
		JPanel cntCenter = new JPanel(new GridLayout(0, 7));

		// 요일 입력
		String dw[] = { "일", "월", "화", "수", "목", "금", "토" };

		Calendar now = Calendar.getInstance();
		int year, month, date;

		public CustomCalendar() {
			year = now.get(Calendar.YEAR);// 2021년
			month = now.get(Calendar.MONTH) + 1; // 0월 == 1월
			date = now.get(Calendar.DATE);
			for (int i = year; i <= year + 50; i++) {
				yearModel.addElement(i);
			}
			for (int i = 1; i <= 12; i++) {
				monthModel.addElement(i);
			}
			////////////////////////// 프레임///////////////////////////////////////////
			// 상단 지역
			add("North", bar);
			bar.setLayout(new FlowLayout());
			bar.setSize(300, 400);
			bar.add(lastMonth);
			
			////////////////////////// 달력/////////////////////////////////////////////
			bar.add(yearCombo);
			yearCombo.setModel(yearModel);
			yearCombo.setSelectedItem(year);

			bar.add(yLbl);
			bar.add(monthCombo);
			monthCombo.setModel(monthModel);
			monthCombo.setSelectedItem(month);

			bar.add(mLbl);
			bar.add(nextMonth);
			bar.setBackground(new Color(0, 210, 180));

			// 중앙 지역
			add("Center", center);
			// 중앙 상단 지역
			center.add("North", cntNorth);
			for (int i = 0; i < dw.length; i++) {
				JLabel dayOfWeek = new JLabel(dw[i], JLabel.CENTER);
				if (i == 0)
					dayOfWeek.setForeground(Color.red);
				else if (i == 6)
					dayOfWeek.setForeground(Color.blue);
				cntNorth.add(dayOfWeek);
			}

			// 중앙 중앙 지역
			center.add("Center", cntCenter);
			dayPrint(year, month);

			// 이벤트
			yearCombo.addActionListener(this);
			monthCombo.addActionListener(this);
			lastMonth.addActionListener(this);
			nextMonth.addActionListener(this);
			addWindowListener(this);

			// frame 기본 셋팅
			setSize(400, 300);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}

		// 이벤트 처리
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			if (obj instanceof JButton) {
				JButton eventBtn = (JButton) obj;
				int yy = (Integer) yearCombo.getSelectedItem();
				int mm = (Integer) monthCombo.getSelectedItem();
				if (eventBtn.equals(lastMonth)) { // 전달
					if (mm == 1 && yy == year) {
					} else if (mm == 1) {
						yy--;
						mm = 12;
					} else {
						mm--;
					}
				} else if (eventBtn.equals(nextMonth)) { // 다음달
					if (mm == 12) {
						yy++;
						mm = 1;
					} else {
						mm++;
					}
				}
				yearCombo.setSelectedItem(yy);
				monthCombo.setSelectedItem(mm);
			} else if (obj instanceof JComboBox) { // 콤보박스 이벤트 발생시
				createDayStart();
			}
		}

		private void createDayStart() {
			cntCenter.setVisible(false); // 패널 숨기기
			cntCenter.removeAll(); // 날짜 출력한 라벨 지우기
			dayPrint((Integer) yearCombo.getSelectedItem(), (Integer) monthCombo.getSelectedItem());
			cntCenter.setVisible(true); // 패널 재출력
		}

		// 날짜 출력
		public void dayPrint(int y, int m) {
			Calendar cal = Calendar.getInstance();
			cal.set(y, m - 1, 1);
			int week = cal.get(Calendar.DAY_OF_WEEK); // 1일에 대한 요일
			int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 1월에 대한 마지막 요일
			for (int i = 1; i < week; i++) { // 1월 1일 전까지 공백을 표시해라
				cntCenter.add(new JLabel(""));
			}

			for (int i = 0; i <= lastDate - 1; i++) { // 1월 마지막 날까지 숫자를 적어라, 그리고 토요일 일요일은 색깔을 입혀라
				JLabel day = new JLabel();
				day.setHorizontalAlignment(JLabel.CENTER);
				if ((week + i) % 7 == 0) {
					cntCenter.add(day).setForeground(Color.blue);
					day.setText(1 + i + "");
				} else if ((week + i) % 7 == 1) {
					cntCenter.add(day).setForeground(Color.red);
					day.setText(1 + i + "");
				} else {
					cntCenter.add(day);
					day.setText(1 + i + "");
				}
				day.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
						JLabel mouseClick = (JLabel) me.getSource();
						String str = mouseClick.getText();
						String y = "" + yearCombo.getSelectedItem();
						String m = "" + monthCombo.getSelectedItem();

						// 받은 "요일"이 1자리면 0을 붙여라
						if (str.equals(""))
							;
						else if (str.length() == 1)
							str = "0" + str;

						// 받은 "월"이 1자리면 0을 붙여라
						if (m.length() == 1)
							m = "0" + m;

						if (clickCheck == 0) {
							rentalDateTextField.setText(y + "-" + m + "-" + str);
							rentalDateTextField.setEnabled(false);
							clickCheck++;
						} 
						else if (clickCheck == 1) {
							returnDateTextField.setText(y + "-" + m + "-" + str);
							returnDateTextField.setEnabled(false);
							clickCheck--;
						}
					}
				});
			}

		}

		public void windowOpened(WindowEvent e) {
			calendarWindowTest = 1;
		}

		public void windowClosing(WindowEvent e) {
			calendarWindowTest = 0;
		}

		public void windowClosed(WindowEvent e) {
		}

		public void windowIconified(WindowEvent e) {
		}

		public void windowDeiconified(WindowEvent e) {
		}

		public void windowActivated(WindowEvent e) {
		}

		public void windowDeactivated(WindowEvent e) {
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				frame.setTitle("렌트카 결제 프로그램");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(900, 800);
				frame.setLocationRelativeTo(null);

				CarRentalGUI carRentalGUI = new CarRentalGUI();
				frame.getContentPane().add(carRentalGUI);

				frame.setVisible(true);
			}
		});
	}
}