package Car_rent_Program;



import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import DAO.CarRentDAO;
import DAO.CustomerDAO;
import DAO.ManagerDAO;
import DTO.Car_rent;
import DTO.Customer;
import DTO.Reservation;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.JComboBox;

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
	
	public MyPage(String name){
		
		try {
			rent = CarRentDAO.getCarRentDAO().selectByName(name);
			customer = CustomerDAO.getCustomerDAO().selectByName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle("렌트가 예약 프로그램");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setLayout(null);
        
        
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 885, 760);
        getContentPane().add(tabbedPane);
        
        JPanel panel = new JPanel();
        tabbedPane.addTab("최근 예약 정보", null, panel, null);
        panel.setLayout(null);
        
        JLabel title = new JLabel("예약 정보");
        title.setBounds(360, 50, 115, 50);
        title.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        panel.add(title);
        
        JLabel rentno = new JLabel("예약 번호 :");
        rentno.setBounds(160, 160, 110, 30);
        rentno.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        panel.add(rentno);
        
        
        JLabel rentname = new JLabel("이름 :");
        rentname.setBounds(160, 260, 110, 30);
        rentname.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        panel.add(rentname);
        
        JLabel rentcarno = new JLabel("차량 번호 :");
        rentcarno.setBounds(160, 360, 110, 30);
        rentcarno.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        panel.add(rentcarno);
        
        
        JLabel startday = new JLabel("대여날짜 : ");
        startday.setBounds(160, 460, 110, 30);
        startday.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        panel.add(startday);
        
        
        JLabel endday = new JLabel("반납날짜 :");
        endday.setBounds(160, 560, 110, 30);
        endday.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        panel.add(endday);
        
        JLabel lblNewLabel_7 = new JLabel(rent.getName());
        lblNewLabel_7.setBounds(360, 260, 400, 30);
        lblNewLabel_7.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        panel.add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel(rent.getCar_no());
        lblNewLabel_8.setBounds(360, 360, 400, 30);
        lblNewLabel_8.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        panel.add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel(rent.getRent_start());
        lblNewLabel_9.setBounds(360, 460, 400, 30);
        lblNewLabel_9.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        panel.add(lblNewLabel_9);
        
        JLabel lblNewLabel_10 = new JLabel(rent.getRent_end());
        lblNewLabel_10.setBounds(360, 560, 400, 30);
        lblNewLabel_10.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        panel.add(lblNewLabel_10);
        
        JLabel lblNewLabel_6 = new JLabel(String.valueOf(rent.getRent_no()));
        lblNewLabel_6.setBounds(360, 160, 400, 30);
        lblNewLabel_6.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        panel.add(lblNewLabel_6);
        
        JButton checkButton = new JButton("확인");
        checkButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
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
        
        
        JPanel customInfoUpdate = new JPanel();
        tabbedPane.addTab("정보 수정", null, customInfoUpdate, null);
        customInfoUpdate.setLayout(null);
        
        JLabel title_1 = new JLabel("정보 수정");
        title_1.setBounds(360, 50, 115, 50);
        title_1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        customInfoUpdate.add(title_1);
        
        JLabel nameLabel = new JLabel("이름 : ");
        nameLabel.setBounds(160, 160, 110, 30);
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        customInfoUpdate.add(nameLabel);
        
        JLabel lblId = new JLabel("ID : ");
        lblId.setBounds(160, 260, 110, 30);
        lblId.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        customInfoUpdate.add(lblId);
        
        JLabel pwTry = new JLabel("비밀번호 : ");
        pwTry.setBounds(160, 360, 110, 30);
        pwTry.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        customInfoUpdate.add(pwTry);
        
        JLabel pwReTry = new JLabel("비밀번호 확인 : ");
        pwReTry.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        pwReTry.setBounds(160, 460, 135, 30);
        customInfoUpdate.add(pwReTry);
        
        JLabel rentno_4 = new JLabel("전화번호 : ");
        rentno_4.setBounds(160, 560, 110, 30);
        rentno_4.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        customInfoUpdate.add(rentno_4);
        
        JLabel nameLa = new JLabel(customer.getName());
        nameLa.setBounds(360, 160, 300, 30);
        nameLa.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        customInfoUpdate.add(nameLa);
        
        JLabel nameLab =  new JLabel(customer.getCustomer_id());
        nameLab.setBounds(360, 260, 300, 30);
        nameLab.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        customInfoUpdate.add(nameLab);
        
        
        textField_1 = new JPasswordField(customer.getPw());
        textField_1.setBounds(360, 360, 300, 30);
        textField_1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        textField_1.setColumns(10);
        customInfoUpdate.add(textField_1);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        passwordField.setColumns(10);
        passwordField.setBounds(360, 460, 300, 30);
        customInfoUpdate.add(passwordField);
        
        textField_2 = new JTextField(customer.getPhone());
        textField_2.setBounds(360, 560, 300, 30);
        textField_2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        textField_2.setColumns(10);
        customInfoUpdate.add(textField_2);
        
        JButton saveButton = new JButton("저장");
        saveButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
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
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "예약 내역 조회에 실패하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }



        
        rent_Panel.add(rent_la);
        rent_Panel.add(rent_jtf);
        rent_Panel.add(rent_jc);
        rent_Panel.add(rent_btn);
        rent_Panel.add(rent_jsp);

        tabbedPane.addTab("이전 예약 내역", rent_Panel);
	}
}

