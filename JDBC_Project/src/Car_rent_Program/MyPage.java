package Car_rent_Program;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import DAO.CarRentDAO;
import DAO.CustomerDAO;
import DTO.Car_rent;
import DTO.Customer;
import DTO.CustomerServiceCenter;
import DTO.Reservation;

//마이페이지 화면 구현 - 승희, 병인
//마이페이지 JDBC 연결 - 승희, 병인
@SuppressWarnings("serial")
public class MyPage extends JFrame {
	
	Car_rent rent;
	Customer customer;
	private JPasswordField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	
	private DefaultTableModel rent_dm; 
	private JTable rent_jt;
	private String[] car_rent = {"차량 번호", "차량 종류","대여 날짜","반납 날짜", "결제 방법", "금액" };
	JLabel carRent_no;
	JLabel rentName;
	JLabel rentCar_no;
	JLabel rent_Start_day;
	JLabel rent_end_day;
	
	ImageIcon gifIcon;
	ImageIcon servicegifIcon;
	private JTextField service_textField;
	private JTextField textField;
	
	
	public MyPage(String name){
		
		
		try {
			rent = CarRentDAO.getCarRentDAO().selectByName(name);
			customer = CustomerDAO.getCustomerDAO().selectByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setTitle("SOULCAL");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setIconImage(Toolkit.getDefaultToolkit().getImage("./image/차량선택 백그라운드.jpg")); // 프로젝트 내부 경로로 변경
        setSize(900, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setLayout(null);
        
        gifIcon = new ImageIcon("./image/소울카마이페이지GIF.gif"); // GIF 이미지 파일 경로로 변경
        
        
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = gifIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 885, 760);
        getContentPane().add(tabbedPane);
        
        tabbedPane.addTab("최근 예약 정보", null, panel, null);
        panel.setLayout(null);
        
        JLabel title = new JLabel("예약 정보");
        title.setForeground(new Color(255, 255, 255));
        title.setBounds(360, 50, 115, 50);
        title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
        panel.add(title);
        
        JLabel rentno = new JLabel("예약 번호 :");
        rentno.setForeground(new Color(255, 255, 255));
        rentno.setBounds(160, 140, 110, 30);
        rentno.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        panel.add(rentno);
        
        
        JLabel rentname = new JLabel("이름 :");
        rentname.setForeground(new Color(255, 255, 255));
        rentname.setBounds(160, 240, 110, 30);
        rentname.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        panel.add(rentname);
        
        JLabel rentcarno = new JLabel("차량 번호 :");
        rentcarno.setForeground(new Color(255, 255, 255));
        rentcarno.setBounds(160, 340, 110, 30);
        rentcarno.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        panel.add(rentcarno);
        
        
        JLabel startday = new JLabel("대여날짜 : ");
        startday.setForeground(new Color(255, 255, 255));
        startday.setBounds(160, 440, 110, 30);
        startday.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        panel.add(startday);
        
        
        JLabel endday = new JLabel("반납날짜 :");
        endday.setForeground(new Color(255, 255, 255));
        endday.setBounds(160, 540, 110, 30);
        endday.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        panel.add(endday);
        
        if (rent != null) {
        	rentName = new JLabel(String.valueOf(rent.getName()));
        } else {
        	rentName = new JLabel("최근 예약 내역 없음");
        }
        
        rentName.setForeground(new Color(255, 255, 255));
        rentName.setBounds(360, 240, 400, 30);
        rentName.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        panel.add(rentName);
        
        
        if (rent != null) {
        	rentCar_no = new JLabel(String.valueOf(rent.getCar_no()));
        } else {
        	rentCar_no = new JLabel("최근 예약 내역 없음");
        }
        rentCar_no.setForeground(new Color(255, 255, 255));
        rentCar_no.setBounds(360, 340, 400, 30);
        rentCar_no.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        panel.add(rentCar_no);
        
        if (rent != null) {
        	rent_Start_day = new JLabel(String.valueOf(rent.getRent_start()));
        } else {
        	rent_Start_day = new JLabel("최근 예약 내역 없음");
        }
        rent_Start_day.setForeground(new Color(255, 255, 255));
        rent_Start_day.setBounds(360, 440, 400, 30);
        rent_Start_day.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        panel.add(rent_Start_day);
        
        
        if (rent != null) {
        	rent_end_day = new JLabel(String.valueOf(rent.getRent_end()));
        } else {
        	rent_end_day = new JLabel("최근 예약 내역 없음");
        }
        rent_end_day.setForeground(new Color(255, 255, 255));
        rent_end_day.setBounds(360, 540, 400, 30);
        rent_end_day.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        panel.add(rent_end_day);
        
        if (rent != null) {
            carRent_no = new JLabel(String.valueOf(rent.getRent_no()));
        } else {
            carRent_no = new JLabel("최근 예약 내역 없음");
        }
        carRent_no.setForeground(new Color(255, 255, 255));
        carRent_no.setBounds(360, 140, 400, 30);
        carRent_no.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        panel.add(carRent_no);
        
        JButton checkButton = new JButton("확인");
        checkButton.setForeground(new Color(0, 0, 0));
        checkButton.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 20));
        checkButton.setBounds(360, 650, 110, 40);
        panel.add(checkButton);
        
        checkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login login = new Login();
				login.setVisible(true);
				
			}
		});
        
        JPanel customInfoUpdate = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = gifIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        
        tabbedPane.addTab("정보 수정", null, customInfoUpdate, null);
        customInfoUpdate.setLayout(null);
        
        JLabel title_1 = new JLabel("정보 수정");
        title_1.setForeground(new Color(255, 255, 255));
        title_1.setBounds(360, 50, 115, 50);
        title_1.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
        customInfoUpdate.add(title_1);
        
        JLabel nameLabel = new JLabel("이름 : ");
        nameLabel.setForeground(new Color(255, 255, 255));
        nameLabel.setBounds(160, 130, 170, 30);
        nameLabel.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        customInfoUpdate.add(nameLabel);
        
        JLabel lblId = new JLabel("ID : ");
        lblId.setForeground(new Color(255, 255, 255));
        lblId.setBounds(160, 230, 170, 30);
        lblId.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        customInfoUpdate.add(lblId);
        
        JLabel pwTry = new JLabel("비밀번호 : ");
        pwTry.setForeground(new Color(255, 255, 255));
        pwTry.setBounds(160, 330, 170, 30);
        pwTry.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        customInfoUpdate.add(pwTry);
        
        JLabel pwReTry = new JLabel("비밀번호 확인 : ");
        pwReTry.setForeground(new Color(255, 255, 255));
        pwReTry.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        pwReTry.setBounds(160, 430, 170, 30);
        customInfoUpdate.add(pwReTry);
        
        JLabel rentno_4 = new JLabel("전화번호 : ");
        rentno_4.setForeground(new Color(255, 255, 255));
        rentno_4.setBounds(160, 530, 170, 30);
        rentno_4.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        customInfoUpdate.add(rentno_4);
        
        JLabel nameLa = new JLabel(customer.getName());
        nameLa.setForeground(new Color(255, 255, 255));
        nameLa.setBounds(360, 130, 300, 30);
        nameLa.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
        customInfoUpdate.add(nameLa);
        
        JLabel nameLab =  new JLabel(customer.getCustomer_id());
        nameLab.setForeground(new Color(255, 255, 255));
        nameLab.setBounds(360, 230, 300, 30);
        nameLab.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
        customInfoUpdate.add(nameLab);
        
        
        textField_1 = new JPasswordField(customer.getPw());
        textField_1.setForeground(new Color(0, 0, 0));
        textField_1.setBounds(360, 330, 300, 40);
        textField_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        textField_1.setColumns(10);
        customInfoUpdate.add(textField_1);
        
        passwordField = new JPasswordField();
        passwordField.setForeground(new Color(0, 0, 0));
        passwordField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        passwordField.setColumns(10);
        passwordField.setBounds(360, 430, 300, 40);
        customInfoUpdate.add(passwordField);
        
        textField_2 = new JTextField(customer.getPhone());
        textField_2.setForeground(new Color(0, 0, 0));
        textField_2.setBounds(360, 530, 300, 40);
        textField_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
        textField_2.setColumns(10);
        customInfoUpdate.add(textField_2);
        
        JButton saveButton = new JButton("저장");
        saveButton.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 20));
        saveButton.setBounds(360, 640, 110, 40);
        customInfoUpdate.add(saveButton);
        
        
        saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(customer);
					if(textField_1.getText().equals(passwordField.getText())) {
						customer = Customer.builder()
		                           .name(nameLa.getText())
		                           .customer_id(nameLab.getText())
		                           .pw(textField_1.getText())
		                           .phone(textField_2.getText())
		                           .licence("Y")
		                           .build();
						JOptionPane.showMessageDialog(null, "회원정보 수정 완료!");
						CustomerDAO.getCustomerDAO().customerUpdate(customer);
						System.out.println(customer);
						Login login = new Login();
						login.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다!");
						dispose();
						new MyPage(name);
					}
				} catch (SQLException e1) {
					
				e1.printStackTrace();
				}
			}
		});
        
        
        JPanel rent_Panel = new JPanel();
        rent_Panel.setLayout(null);

        rent_dm = new DefaultTableModel(null, car_rent);
        rent_jt = new JTable(rent_dm);
        rent_jt.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        rent_jt.setRowHeight(25);
        
        // 테이블 제목의 폰트 변경
        JTableHeader price_header = rent_jt.getTableHeader();
        price_header.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        
        JScrollPane rent_jsp = new JScrollPane(rent_jt);
        rent_jsp.setBounds(10, 60, 870, 700);
        
        // 수직 스크롤바를 오른쪽에 추가
        rent_jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel rent_la = new JLabel("검색 내용");
        rent_la.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        rent_la.setBounds(10, 10, 150, 40);

        JTextField rent_jtf = new JTextField(); // 텍스트 입력
        rent_jtf.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        rent_jtf.setBounds(120, 10, 400, 40); // 텍스트 입력창 크기

        String[] rent_temp = {"차량 종류","결제 방법"};
        JComboBox<String> rent_jc = new JComboBox<>(rent_temp); // 선택항목
        rent_jc.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        rent_jc.setBounds(550, 10, 120, 40); // 선택항목 크기

        JButton rent_btn = new JButton("검색"); // 상품 검색 버튼
        rent_btn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        rent_btn.setBounds(700, 10, 120, 40); // 상품 검색 버튼 크기

        rent_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchKeyword = rent_jtf.getText();
                String selectedOption = (String) rent_jc.getSelectedItem();
                
                try {
                    List<Reservation> reservations = CustomerDAO.getCustomerDAO().getReservationsByCustomer(name);
                    DecimalFormat formatter = new DecimalFormat("#,###,###,###");
                    rent_dm.setRowCount(0); // 기존 테이블 데이터 초기화
                    
                    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        	        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬 설정

        			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                    rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬 설정
        	        
                    for (Reservation reservation : reservations) {
                        String[] rowData = new String[6];
                        rowData[0] = reservation.getCarNo();
                        rowData[1] = reservation.getCarType();
                        rowData[2] = reservation.getRentStart();
                        rowData[3] = reservation.getRentEnd();
                        rowData[4] = reservation.getPaymentMethod();
                        rowData[5] = String.valueOf(formatter.format(reservation.getMoney()));
                        
                        boolean isMatched = false; // 검색 결과 여부를 판단하는 변수

                        if (selectedOption.equals("차량 종류") && rowData[1].contains(searchKeyword)) {
                            isMatched = true;
                        } else if (selectedOption.equals("결제 방법") && rowData[4].contains(searchKeyword)) {
                            isMatched = true;
                        }

                        if (isMatched) {
                            rent_dm.addRow(rowData);
                        }
                    }
                    // 오른쪽 정렬 설정 적용
                    TableColumnModel columnModel = rent_jt.getColumnModel();
                    columnModel.getColumn(5).setCellRenderer(rightRenderer);
                    
                    
                 // 가운데 정렬 설정 적용
        	        for (int i = 0; i < columnModel.getColumnCount(); i++) {
        	            if (i != 5) {
        	                columnModel.getColumn(i).setCellRenderer(centerRenderer);
        	            }
        	        }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "예약 내역 조회에 실패하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });


        // 초기 데이터 조회 및 추가
        try {
            List<Reservation> reservations = CustomerDAO.getCustomerDAO().getReservationsByCustomer(name);
            DecimalFormat formatter = new DecimalFormat("#,###,###,###");
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬 설정

			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬 설정
            
            for (Reservation reservation : reservations) {
                String[] rowData = new String[6];
                rowData[0] = reservation.getCarNo();
                rowData[1] = reservation.getCarType();
                rowData[2] = reservation.getRentStart();
                rowData[3] = reservation.getRentEnd();
                rowData[4] = reservation.getPaymentMethod();
                rowData[5] = String.valueOf(formatter.format(reservation.getMoney()));
                
                rent_dm.addRow(rowData);
            }
         // 오른쪽 정렬 설정 적용
            TableColumnModel columnModel = rent_jt.getColumnModel();
            columnModel.getColumn(5).setCellRenderer(rightRenderer);
            
            
         // 가운데 정렬 설정 적용
	        for (int i = 0; i < columnModel.getColumnCount(); i++) {
	            if (i != 5) {
	                columnModel.getColumn(i).setCellRenderer(centerRenderer);
	            }
	        }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "예약 내역 조회에 실패하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        
        JPanel service_center = new JPanel() {
        	public void paintComponent(Graphics g) {
        	      super.paintComponent(g);
                  Image image = servicegifIcon.getImage();
                  g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        	}
        };
        service_center.setLayout(null);
        tabbedPane.addTab("고객센터", null, service_center, null);
        
        JLabel service_1 = new JLabel("고객센터");
        service_1.setForeground(Color.WHITE);
        service_1.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 22));
        service_1.setBounds(360, 50, 115, 50);
        service_center.add(service_1);
        
        JLabel service_nameLabel = new JLabel("이름 : ");
        service_nameLabel.setForeground(Color.WHITE);
        service_nameLabel.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        service_nameLabel.setBounds(160, 130, 110, 30);
        service_center.add(service_nameLabel);
        
        JLabel service_lb = new JLabel("ID : ");
        service_lb.setForeground(Color.WHITE);
        service_lb.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        service_lb.setBounds(160, 230, 110, 30);
        service_center.add(service_lb);
        
        JLabel service_phone = new JLabel("전화번호 : ");
        service_phone.setForeground(Color.WHITE);
        service_phone.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        service_phone.setBounds(160, 330, 110, 30);
        service_center.add(service_phone);
        
        JLabel service_name = new JLabel(customer.getName());
        service_name.setForeground(new Color(255, 255, 255));
        service_name.setBounds(300, 130, 300, 30);
        service_name.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
        service_center.add(service_name);
        
        JLabel service_id = new JLabel(customer.getCustomer_id());
        service_id.setForeground(Color.WHITE);
        service_id.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 20));
        service_id.setBounds(300, 230, 300, 30);
        service_center.add(service_id);
        
        service_textField = new JTextField(customer.getPhone());
        service_textField.setForeground(new Color(0, 0, 0));
        service_textField.setBounds(300, 330, 483, 30);
        service_textField.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
        service_textField.setColumns(10);
        service_center.add(service_textField);
        
        JLabel service_detail = new JLabel("내용 : ");
        service_detail.setForeground(Color.WHITE);
        service_detail.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 18));
        service_detail.setBounds(160, 480, 110, 30);
        service_center.add(service_detail);
        
        textField = new JTextField();
        textField.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        textField.setBounds(300, 430, 483, 178);
        service_center.add(textField);
        textField.setColumns(10);
        
        JButton receipt_button = new JButton("접수");
        receipt_button.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 20));
        receipt_button.setBounds(360, 640, 110, 50);
        service_center.add(receipt_button);
        
        receipt_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = customer.getName(); // 고객 이름
                String customer_id = customer.getCustomer_id(); // 고객 ID
                String phone = service_textField.getText(); // 전화번호
                String service = textField.getText(); // 접수 내용
                
                CustomerServiceCenter dto = new CustomerServiceCenter(name, customer_id, phone, service);
                
                try {
                    boolean success = CustomerDAO.getCustomerDAO().service(dto);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "고객센터에 접수되었습니다.");
                        
                        // 텍스트 필드 초기화
                        service_textField.setText("");
                        textField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "고객센터 접수에 실패하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        rent_Panel.add(rent_la);
        rent_Panel.add(rent_jtf);
        rent_Panel.add(rent_jc);
        rent_Panel.add(rent_btn);
        rent_Panel.add(rent_jsp);
        
        tabbedPane.addTab("이전 예약 내역", rent_Panel);
        
        
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////
       
        servicegifIcon = new ImageIcon("./image/소울카고객센터GIF.gif"); // GIF 이미지 파일 경로로 변경







        



        
       
	}
}

