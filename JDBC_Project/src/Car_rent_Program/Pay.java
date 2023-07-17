package Car_rent_Program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import DAO.CarRentDAO;
import DAO.CarSuperintendDAO;
import DAO.PaymentDAO;
import DTO.Car_Superintend;
import DTO.Car_rent;
import DTO.Payment;

//결제 화면 구현 - 종화
//결제 화면 JDBC 연결 - 병인
@SuppressWarnings("serial")
public class Pay extends JPanel {
	
	
	public Date getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(Date rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public Date getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(Date rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public int getRentalFee() {
        return rentalFee;
    }
    
    public int getInsuranceFee() {
		return insuranceFee;
	}
    public void setInsuranceFee(int insuranceFee) {
		this.insuranceFee = insuranceFee;
	}
	
    private String name;
    private Car_rent rent;
	private Payment payment;
	private String selectedCar;	// 선택한 차량 정보를 저장하기 위한 변수
	private int rentalFee;		// 선택한 차량의 일일 대여료를 저장하기 위한 변수
	private int insuranceFee;	//선택한 차량의 일일 보험료를 저장하기 위한 변수 
	private Date rentStartDate;
	private Date rentEndDate;
	int totalAmount;
	JLabel price_Label;
	// 대여 기간 계산
	long diff;
	int rentalDays;
	
	private Choicecar choicecar;
	
	private JLabel rent_start_label;
	private JLabel payment_label;
	private JLabel price_label;
	private JLabel rent_end_label;
	private JButton pay_button;
	private JButton cancellation_button;
	JTextField rent_start_textField;
	JTextField rent_end_textField;
	
	private ImageIcon icon = new ImageIcon("img/calendar_icon.jpg");
	private Image im = icon.getImage();
	private Image im2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	@SuppressWarnings("unused")
	private ImageIcon icon2 = new ImageIcon(im2);

	private JLabel choicecar_label;
	private JLabel choicecar_price;
	private JLabel calendar_Label;

	// calendar가 켜졌을때는 한번 더 open되지 않도록 제한사항을 부여
	int calendarWindowTest = 0;
	// 클릭 횟수 감지
	int clickCheck = 0;
	// "," 표시
	DecimalFormat formatter = new DecimalFormat("#,###,###,###");
	
	ImageIcon gifIcon;

	// Pay 생성자에 선택한 차량 정보를 전달받도록 수정
		public Pay(Choicecar choicecar, String selectedCar, int rentalFee, int insuranceFee,String na) throws SQLException {
		    this.choicecar = choicecar;
		    this.selectedCar = selectedCar;
		    this.rentalFee = rentalFee;
		    this.insuranceFee = insuranceFee;
		    this.rentStartDate = null;  // rentStartDate 초기화
		    this.rentEndDate = null;    // rentEndDate 초기화
		    this.name = na;
		    initialize();
		}

	
	public Pay() {
		
	}

	private void initialize() {
		setLayout(new BorderLayout());
        setPreferredSize(new Dimension(900, 800));
		

		// calendarWindowTest 초기화
		calendarWindowTest = 0;

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 50, 50);

		
		gifIcon = new ImageIcon("./image/소울카결제화면GIF.gif"); // GIF 이미지 파일 경로로 변경
		
		JPanel backgroundPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = gifIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null);

		
		// 첫 번째 열
		JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        backgroundPanel.setLayout(null);
        backgroundPanel.setPreferredSize(new Dimension(900, 800));

		/////////////////////////////////////////////////////////////////
		
		
		choicecar_label = new JLabel("선택한 차종 : ");
		choicecar_label.setHorizontalAlignment(SwingConstants.CENTER);
		choicecar_label.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
		choicecar_label.setForeground(new Color(255, 255, 255));
		choicecar_label.setBounds(180, 50, 180, 30);
		backgroundPanel.add(choicecar_label);

		// choicecar_label의 텍스트를 선택한 차량 정보로 업데이트
		choicecar_price = new JLabel(selectedCar);
		choicecar_price.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
		choicecar_price.setForeground(new Color(255, 255, 255));
		choicecar_price.setBounds(350, 45, 300, 40);
		backgroundPanel.add(choicecar_price);

		/////////////////////////////////////////////////////////////////
		rent_start_label = new JLabel("대여 날짜 : ");
		rent_start_label.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
		rent_start_label.setForeground(new Color(255, 255, 255));
		rent_start_label.setBounds(180, 150, 180, 30);
		rent_start_label.setHorizontalAlignment(JLabel.CENTER);
		backgroundPanel.add(rent_start_label);

		rent_start_textField = new JTextField();
		rent_start_textField.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
		rent_start_textField.setBounds(350, 150, 300, 40);
		backgroundPanel.add(rent_start_textField);

		icon = new ImageIcon("./image/calendar_icon.jpg");
		im = icon.getImage();
		im2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon2 = new ImageIcon(im2);

		calendar_Label = new JLabel(icon);
		calendar_Label.setBounds(660, 150, 50, 40);
		backgroundPanel.add(calendar_Label);
		
		
		/////////////////////////////////////////////////////////////////
		rent_end_label = new JLabel("반납 날짜 : ");
		rent_end_label.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
		rent_end_label.setForeground(new Color(255, 255, 255));
		rent_end_label.setBounds(180, 250, 180, 30);
		rent_end_label.setHorizontalAlignment(JLabel.CENTER);
		backgroundPanel.add(rent_end_label);

		rent_end_textField = new JTextField();
		rent_end_textField.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
		rent_end_textField.setBounds(350, 250, 300, 40);
		backgroundPanel.add(rent_end_textField);

		/////////////////////////////////////////////////////////////////
		price_label = new JLabel("결제 할 금액 : ");
		price_label.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
		price_label.setForeground(new Color(255, 255, 255));
		price_label.setBounds(180, 350, 180, 30);
		price_label.setHorizontalAlignment(JLabel.CENTER);
		backgroundPanel.add(price_label);
		
		// 결제 금액 계산
	    totalAmount = 0; // 초기화
	    if (rentStartDate != null && rentEndDate != null) {
	    	diff = rentEndDate.getTime() - rentStartDate.getTime();
	        rentalDays = (int) (diff / (24 * 60 * 60 * 1000));
	        totalAmount = (rentalFee + insuranceFee) * rentalDays;
	    }
	    
		price_Label = new JLabel(totalAmount + "원");
		price_Label.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
		price_Label.setForeground(new Color(255, 255, 255));
		price_Label.setBounds(350, 345, 300, 40);
		backgroundPanel.add(price_Label);

		/////////////////////////////////////////////////////////////////
		payment_label = new JLabel("결제 방법 : ");
		payment_label.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
		payment_label.setForeground(new Color(255, 255, 255));
		payment_label.setBounds(180, 450, 180, 30);
		payment_label.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(payment_label);

		String[] temp = { "신용카드", "계좌이체" };
		JComboBox<String> jc = new JComboBox<>(temp);
		jc.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
		jc.setBounds(350, 450, 300, 40);
		backgroundPanel.add(jc);

		/////////////////////////////////////////////////////////////////
		pay_button = new JButton("결제하기");
		pay_button.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
		pay_button.setBounds(430, 600, 150, 50);
		backgroundPanel.add(pay_button);

		cancellation_button = new JButton("취소");
		cancellation_button.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
		cancellation_button.setBounds(250, 600, 150, 50);
		backgroundPanel.add(cancellation_button);
		
		add(backgroundPanel);

		calendar_Label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				me.getSource();
				if (calendarWindowTest == 0) {
					new CustomCalendar(Pay.this); // Pay 객체 전달
					calendarWindowTest = 1;
				}
			}
		});

		setSize(900, 800);
		setVisible(true);

		// 결제하기 버튼 액션 리스너
		pay_button.addActionListener(new ActionListener() {
			
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	            int option = JOptionPane.showConfirmDialog(null, "결제하시겠습니까?", "결제 확인", JOptionPane.YES_NO_OPTION);
	            if (option == JOptionPane.YES_OPTION) {
	            	
	            	if (rentStartDate == null || rentEndDate == null || rent_start_textField.getText().isEmpty() || rent_end_textField.getText().isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "렌트 기간을 정확히 입력해주세요.");
	                    return; // 날짜가 비어 있으면 함수 종료
	                }
	            	
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                try {
	                    rentStartDate = new java.sql.Date(dateFormat.parse(rent_start_textField.getText()).getTime());
	                    rentEndDate = new java.sql.Date(dateFormat.parse(rent_end_textField.getText()).getTime());
	                    try {
	                    	// 결제 금액 계산
	                    	totalAmount = 0; // 초기화
	                    	if (rentStartDate != null && rentEndDate != null) {
	                    		diff = rentEndDate.getTime() - rentStartDate.getTime();
	                    		rentalDays = (int) (diff / (24 * 60 * 60 * 1000));
	                    		totalAmount = (rentalFee + insuranceFee) * rentalDays;
	                    	}
	                    	Car_Superintend cs = new Car_Superintend();
	                    	cs = CarSuperintendDAO.getCarSuperintendDAO().selectByNO(selectedCar);

	                    	rent = Car_rent.builder()
	                    	        .name(name)
	                    	        .rent_start(rent_start_textField.getText())
	                    	        .rent_end(rent_end_textField.getText())
	                    	        .car_no(selectedCar) // 선택한 차량의 car_no 설정
	                    	        .build();

	                    	CarRentDAO.getCarRentDAO().insertReserve(rent);

	                    	Car_rent cr = CarRentDAO.getCarRentDAO().selectOne();

	                    	payment = Payment.builder()
	                    	        .name(name)
	                    	        .rent_no(cr.getRent_no())
	                    	        .money(totalAmount)
	                    	        .payment_method((String) jc.getSelectedItem()) 
	                    	        .car_no(selectedCar) // 선택한 차량의 car_no 설정
	                    	        .build();

	                    	PaymentDAO.getPaymentDAO().insertPayment(payment);
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
	                    
	                    rentEndDate = new java.sql.Date(dateFormat.parse(rent_end_textField.getText()).getTime());
	                    
	                    
	                    if (rentStartDate != null && rentEndDate != null) {
	                        // 대여 기간 계산
	                        long diff = rentEndDate.getTime() - rentStartDate.getTime();
	                        int rentalDays = (int) (diff / (24 * 60 * 60 * 1000));

	                        // 결제 금액 계산
	                        int totalAmount = (rentalFee + insuranceFee) * rentalDays;;

	                        // '확인' 버튼을 클릭한 경우 -> 로그인 화면으로 연결
	                        new Login().setVisible(true);
	                        
	                        // 추가: 결제 완료 창 띄우기
	                        JOptionPane.showMessageDialog(null, formatter.format(totalAmount) + "원 결제가 완료되었습니다.");

	                    } 
	                } catch (ParseException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "잘못된 날짜 형식입니다.");
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "결제가 취소되었습니다.");
	            }
	        }
	    });

		// 취소 버튼 액션 리스너 
		cancellation_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "결제가 취소되었습니다.");
				// 취소 버튼 클릭 시 Choicecar로 돌아가는 동작을 수행
		        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Pay.this);
		        frame.getContentPane().removeAll();
		        frame.getContentPane().add(choicecar);
		        frame.revalidate();
		        frame.repaint();
		    }
		});
		
	}
	
	protected void rent_start(Date date) {
		
	}

	// 달력 파트
	class CustomCalendar extends JFrame implements ActionListener, WindowListener {

		 private Pay pay; // Pay 클래스 참조를 저장할 변수 추가

		
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

		public CustomCalendar(Pay pay) {
			this.pay = pay; // Pay 클래스 참조를 저장
			
			year = now.get(Calendar.YEAR);// 2021년
			month = now.get(Calendar.MONTH) + 1; // 0월 == 1월
			date = now.get(Calendar.DATE);
			for (int i = year; i <= year + 50; i++) {
				yearModel.addElement(i);
			}
			for (int i = 1; i <= 12; i++) {
				monthModel.addElement(i);
			}
			// 프레임
			// 상단 지역
			add("North", bar);
			bar.setLayout(new FlowLayout());
			bar.setSize(300, 400);
			bar.add(lastMonth);

			// 달력
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
				            if (str.equals("")) {
				                return;
				            } else if (str.length() == 1) {
				                str = "0" + str;
				            }

				            // 받은 "월"이 1자리면 0을 붙여라
				            if (m.length() == 1) {
				                m = "0" + m;
				            }

				            if (clickCheck == 0) {
				                rent_start_textField.setText(y + "-" + m + "-" + str);
				                pay.setRentStartDate(parseDate(y + "-" + m + "-" + str));  // Pay2 클래스의 rentStartDate 설정
				                clickCheck++;
				            } else if (clickCheck == 1) {
				                rent_end_textField.setText(y + "-" + m + "-" + str);
				                pay.setRentEndDate(parseDate(y + "-" + m + "-" + str));  // Pay2 클래스의 rentEndDate 설정
				                clickCheck--;

				                // 대여 기간 계산
				                long diff = pay.getRentEndDate().getTime() - pay.getRentStartDate().getTime();
				                int rentalDays = (int) (diff / (24 * 60 * 60 * 1000));

				                // 결제 금액 계산
				                int totalAmount = (pay.getRentalFee()+pay.getInsuranceFee()) * rentalDays;
				                
				                price_Label.setText(formatter.format(totalAmount) + "원");  // 결제 금액을 화면에 표시
				                dispose();  // 캘린더 창 닫기
				                calendarWindowTest = 0;
				            }
				        }

				        // 문자열을 Date 객체로 변환하는 메서드
					private Date parseDate(String dateString) {
					    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					    try {
					        java.util.Date parsedDate = dateFormat.parse(dateString);
					        return new java.sql.Date(parsedDate.getTime());
					    } catch (ParseException e) {
					        e.printStackTrace();
					    }
					    return null;
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
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    frame.setTitle("결제 화면");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(400, 300);
                    frame.setLocationRelativeTo(null);

                    Pay pay = new Pay();  // 선택한 차량 정보를 전달
                    frame.getContentPane().add(pay);

                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
}
