package Car_rent_Program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Car_rent_Program.Pay.CustomCalendar;
import DAO.CarInspectionDAO;
import DAO.CustomerDAO;
import DAO.ManagerDAO;
import DTO.Car_Inspection;
import DTO.Car_Superintend;
import DTO.Customer;
import DTO.Month_total;
import DTO.Payment;

//관리자 화면 구현 및 JDBC 연결 - 승희
@SuppressWarnings("serial")
public class Manager extends JPanel {

	// calendar가 켜졌을때는 한번 더 open되지 않도록 제한사항을 부여
		int calendarWindowTest = 0;
		// 클릭 횟수 감지
		int clickCheck = 0;
	
    DefaultTableModel price_dm;
    DefaultTableModel customer_price_dm;
    DefaultTableModel Car_Inspection_dm;
    DefaultTableModel car_rent_dm;
    DefaultTableModel customer_dm;
    JTable customer_jt;
    JTable price_jt;
    JTable customer_price_jt;
    JTable Car_Inspection_jt;
    JTable car_rent_jt;
    String[] customer = {"회원 이름","회원 ID","회원 PW","전화번호","면허증 여부"};
    String[] price = { "날짜", "결제 방법", "금액" };
    String[] customer_price = { "순서", "회원 이름", "결제한 날짜", "결제 금액", "결제 방법", "차량 번호" };
    String[] Car_Inspection_menu = { "차량 번호", "검사 종류", "마지막 검사날짜", "다음 검사날짜" };
    String[] car_rent = { "차량 번호", "차량 등급", "차량 종류", "예약 상태", "대여 요금", "보험료(자차)", "연료" };

    JTextField next_date_textField;
    JTextField last_date_textField;
    JTextField car_inspection_textField;
    JTextField car_inspection_no_textField;
    JTextField car_no_textField;
    JTextField car_grade_textField;
    JTextField carType_textField;
    JTextField price_textField;
    JTextField insurance_textField;
    JTextField PL_textField;
    JButton car_insert_Button;
    JButton car_inspection_Button;
    
    
    Car_Inspection car;
    
    private Date last_date;
    private Date next_date;
    
    private ImageIcon icon = new ImageIcon("img/calendar_icon.jpg");
	private Image im = icon.getImage();
	private Image im2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	@SuppressWarnings("unused")
	private ImageIcon icon2 = new ImageIcon(im2);
	private JLabel calendar_Label;

	public Date getLast_date() {
		return last_date;
	}
	public Date getNext_date() {
		return next_date;
	}
	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}
	public void setNext_date(Date next_date) {
		this.next_date = next_date;
	}
    
    
    public Manager() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(900, 800));

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        
        // 탭 1 /////////////////////////////////////////////////
        JPanel customer_Panel = new JPanel();
        customer_Panel.setLayout(null);
        
        customer_dm = new DefaultTableModel(null, customer);
        customer_jt = new JTable(customer_dm);
        customer_jt.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        customer_jt.setRowHeight(25);
        
        // 테이블 제목의 폰트 변경
        JTableHeader customer_header = customer_jt.getTableHeader();
        customer_header.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        
        JScrollPane customer_jsp = new JScrollPane(customer_jt);
        customer_jsp.setBounds(10, 60, 870, 700);
        
        // 수직 스크롤바를 오른쪽에 추가
        customer_jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // 데이터 값이 넘칠때만 수직 스크롤바 표시
        //customer_jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        JLabel customer_la = new JLabel("검색 내용");
        customer_la.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        customer_la.setBounds(10, 10, 150, 40);

        JTextField customer_jtf = new JTextField(); // 텍스트 입력
        customer_jtf.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        customer_jtf.setBounds(120, 10, 400, 40); // 텍스트 입력창 크기

        String[] customer_temp = { "회원 이름","회원 ID","회원 PW","전화번호","면허증 여부"};
        JComboBox<String> customer_jc = new JComboBox<>(customer_temp); // 선택항목
        customer_jc.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        customer_jc.setBounds(550, 10, 120, 40); // 선택항목 크기

        JButton customer_btn = new JButton("검색"); // 상품 검색 버튼
        customer_btn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        customer_btn.setBounds(700, 10, 120, 40); // 상품 검색 버튼 크기

        customer_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String find = customer_jtf.getText();
                String selectedOption = (String) customer_jc.getSelectedItem();
                customer_searchAndDisplayData(find, selectedOption);
            }
        });

        customer_Panel.add(customer_la);
        customer_Panel.add(customer_jtf);
        customer_Panel.add(customer_jc);
        customer_Panel.add(customer_btn);
        customer_Panel.add(customer_jsp);

        tabbedPane.addTab("회원 정보", customer_Panel);
        
        // 초기 데이터 표시
        customer_displayAllData();
        

        // 탭 2 /////////////////////////////////////////////////
        JPanel customer_price_Panel = new JPanel();
        customer_price_Panel.setLayout(null);

        customer_price_dm = new DefaultTableModel(null, customer_price);
        customer_price_jt = new JTable(customer_price_dm);
        customer_price_jt.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        customer_price_jt.setRowHeight(25);
        
        // 테이블 제목의 폰트 변경
        JTableHeader customer_price_header = customer_price_jt.getTableHeader();
        customer_price_header.setFont(new Font("맑은 고딕", Font.BOLD, 16));

        JScrollPane customer_price_jsp = new JScrollPane(customer_price_jt);
        customer_price_jsp.setBounds(10, 60, 870, 700);
        
        // 수직 스크롤바를 오른쪽에 추가
        customer_price_jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel customer_price_la = new JLabel("검색 내용");
        customer_price_la.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        customer_price_la.setBounds(10, 10, 150, 40);

        JTextField customer_price_jtf = new JTextField(); // 텍스트 입력
        customer_price_jtf.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        customer_price_jtf.setBounds(120, 10, 400, 40); // 텍스트 입력창 크기

        String[] customer_price_temp = { "순서", "회원 이름", "결제 날짜", "결제 요금", "결제 방법", "차량 번호" };
        JComboBox<String> customer_price_jc = new JComboBox<>(customer_price_temp); // 선택항목
        customer_price_jc.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        customer_price_jc.setBounds(550, 10, 120, 40); // 선택항목 크기

        JButton customer_price_btn = new JButton("검색"); // 상품 검색 버튼
        customer_price_btn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        customer_price_btn.setBounds(700, 10, 120, 40); // 상품 검색 버튼 크기

        customer_price_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String find = customer_price_jtf.getText();
                String selectedOption = (String) customer_price_jc.getSelectedItem();
                customer_price_searchAndDisplayData(find, selectedOption);
            }
        });

        customer_price_Panel.add(customer_price_la);
        customer_price_Panel.add(customer_price_jtf);
        customer_price_Panel.add(customer_price_jc);
        customer_price_Panel.add(customer_price_btn);
        customer_price_Panel.add(customer_price_jsp);

        tabbedPane.addTab("회원별 결제내역", customer_price_Panel);
        
        // 초기 데이터 표시
        customer_price_displayAllData();

        // 탭 3 /////////////////////////////////////////////////
        JPanel carRentPanel = new JPanel();
        carRentPanel.setLayout(null);

        car_rent_dm = new DefaultTableModel(null, car_rent);
        car_rent_jt = new JTable(car_rent_dm);
        car_rent_jt.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        car_rent_jt.setRowHeight(25);
        
        // 테이블 제목의 폰트 변경
        JTableHeader car_rent_header = car_rent_jt.getTableHeader();
        car_rent_header.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        
        JScrollPane car_rent_jsp = new JScrollPane(car_rent_jt);
        car_rent_jsp.setBounds(10, 60, 870, 700);
        
        // 수직 스크롤바를 오른쪽에 추가
        car_rent_jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel car_rent_la = new JLabel("검색 내용");
        car_rent_la.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        car_rent_la.setBounds(10, 10, 150, 40);

        JTextField car_rent_jtf = new JTextField(); // 텍스트 입력
        car_rent_jtf.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        car_rent_jtf.setBounds(120, 10, 400, 40); // 텍스트 입력창 크기

        String[] car_rent_temp = { "차량 번호", "차량 등급", "차량 종류", "예약 상태", "대여 요금", "보험료(자차)", "연료" };
        JComboBox<String> car_rent_jc = new JComboBox<>(car_rent_temp); // 선택항목
        car_rent_jc.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        car_rent_jc.setBounds(550, 10, 120, 40); // 선택항목 크기

        JButton car_rent_btn = new JButton("검색"); // 상품 검색 버튼
        car_rent_btn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        car_rent_btn.setBounds(700, 10, 120, 40); // 상품 검색 버튼 크기

        car_rent_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String find = car_rent_jtf.getText();
                String selectedOption = (String) car_rent_jc.getSelectedItem();
                car_rent_searchAndDisplayData(find, selectedOption);
            }
        });

        carRentPanel.add(car_rent_la);
        carRentPanel.add(car_rent_jtf);
        carRentPanel.add(car_rent_jc);
        carRentPanel.add(car_rent_btn);
        carRentPanel.add(car_rent_jsp);

        tabbedPane.addTab("차량대여 가능여부", carRentPanel);
        
        // 초기 데이터 표시
        car_rent_displayAllData();

        // 탭 4 /////////////////////////////////////////////////
        JPanel pricePanel =new JPanel();
        pricePanel.setLayout(null);

        price_dm = new DefaultTableModel(null, price);
        price_jt = new JTable(price_dm);
        price_jt.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        price_jt.setRowHeight(25);
        
        // 테이블 제목의 폰트 변경
        JTableHeader price_header = price_jt.getTableHeader();
        price_header.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        
        JScrollPane price_jsp = new JScrollPane(price_jt);
        price_jsp.setBounds(10, 60, 870, 700);
        
        // 수직 스크롤바를 오른쪽에 추가
        price_jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel price_la = new JLabel("검색 내용");
        price_la.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        price_la.setBounds(10, 10, 150, 40);

        JTextField price_jtf = new JTextField(); // 텍스트 입력
        price_jtf.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        price_jtf.setBounds(120, 10, 400, 40); // 텍스트 입력창 크기

        String[] price_temp = { "날짜", "결제 방법", "금액" };
        JComboBox<String> price_jc = new JComboBox<>(price_temp); // 선택항목
        price_jc.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        price_jc.setBounds(550, 10, 120, 40); // 선택항목 크기

        JButton price_btn = new JButton("검색"); // 상품 검색 버튼
        price_btn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        price_btn.setBounds(700, 10, 120, 40); // 상품 검색 버튼 크기

        price_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String find = price_jtf.getText();
                String selectedOption = (String) price_jc.getSelectedItem();
                price_searchAndDisplayData(find, selectedOption);
            }
        });

        pricePanel.add(price_la);
        pricePanel.add(price_jtf);
        pricePanel.add(price_jc);
        pricePanel.add(price_btn);
        pricePanel.add(price_jsp);

        tabbedPane.addTab("월별 매출", pricePanel);
        
        // 초기 데이터 표시
	    price_displayAllData();

        // 탭 5 /////////////////////////////////////////////////
        JPanel carInspectionPanel = new JPanel();
        carInspectionPanel.setLayout(null);

        Car_Inspection_dm = new DefaultTableModel(null, Car_Inspection_menu);
        Car_Inspection_jt = new JTable(Car_Inspection_dm);
        Car_Inspection_jt.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        Car_Inspection_jt.setRowHeight(25);
        
        // 테이블 제목의 폰트 변경
        JTableHeader Car_Inspection_header = Car_Inspection_jt.getTableHeader();
        Car_Inspection_header.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        
        JScrollPane Car_Inspection_jsp = new JScrollPane(Car_Inspection_jt);
        Car_Inspection_jsp.setBounds(10, 60, 870, 700);
        
        // 수직 스크롤바를 오른쪽에 추가
        Car_Inspection_jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel Car_Inspection_la = new JLabel("검색 내용");
        Car_Inspection_la.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        Car_Inspection_la.setBounds(10, 10, 150, 40);

        JTextField Car_Inspection_jtf = new JTextField(); // 텍스트 입력
        Car_Inspection_jtf.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        Car_Inspection_jtf.setBounds(120, 10, 400, 40); // 텍스트 입력창 크기

        String[] Car_Inspection_temp = { "차량 번호", "검사 종류", "마지막 검사날짜", "다음 검사날짜" };
        JComboBox<String> Car_Inspection_jc = new JComboBox<>(Car_Inspection_temp); // 선택항목
        Car_Inspection_jc.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        Car_Inspection_jc.setBounds(550, 10, 120, 40); // 선택항목 크기

        JButton Car_Inspection_btn = new JButton("검색"); // 상품 검색 버튼
        Car_Inspection_btn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        Car_Inspection_btn.setBounds(700, 10, 120, 40); // 상품 검색 버튼 크기

        Car_Inspection_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String find = Car_Inspection_jtf.getText();
                String selectedOption = (String) Car_Inspection_jc.getSelectedItem();
                Car_Inspection_searchAndDisplayData(find, selectedOption);
            }
        });

        carInspectionPanel.add(Car_Inspection_la);
        carInspectionPanel.add(Car_Inspection_jtf);
        carInspectionPanel.add(Car_Inspection_jc);
        carInspectionPanel.add(Car_Inspection_btn);
        carInspectionPanel.add(Car_Inspection_jsp);

        tabbedPane.addTab("차량 정기 검사", carInspectionPanel);
        
        // 초기 데이터 표시
        Car_Inspection_displayAllData();

        // 탭 6 /////////////////////////////////////////////////
        JPanel carInsert_Panel = new JPanel();
        carInsert_Panel.setLayout(null);
        tabbedPane.addTab("차량 등록", null, carInsert_Panel, null);
        
        JLabel car_no_Label = new JLabel("차량 번호 : ");
        car_no_Label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        car_no_Label.setBounds(180, 30, 150, 50);
        carInsert_Panel.add(car_no_Label);
        
        car_no_textField = new JTextField();
        car_no_textField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        car_no_textField.setBounds(350, 35, 350, 50);
        carInsert_Panel.add(car_no_textField);
        car_no_textField.setColumns(10);
        
        JLabel car_grade_Label = new JLabel("차량 등급 : ");
        car_grade_Label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        car_grade_Label.setBounds(180, 120, 150, 50);
        carInsert_Panel.add(car_grade_Label);
        
        car_grade_textField = new JTextField();
        car_grade_textField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        car_grade_textField.setBounds(350, 125, 350, 50);
        carInsert_Panel.add(car_grade_textField);
        car_grade_textField.setColumns(10);
        
        
        JLabel carType_Label = new JLabel("차량 종류 : ");
        carType_Label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        carType_Label.setBounds(180, 210, 150, 50);
        carInsert_Panel.add(carType_Label);
        
        carType_textField = new JTextField();
        carType_textField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        carType_textField.setBounds(350, 215, 350, 50);
        carInsert_Panel.add(carType_textField);
        carType_textField.setColumns(10);
        
        JLabel price_Label = new JLabel("일일 대여요금 : ");
        price_Label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        price_Label.setBounds(180, 300, 150, 50);
        carInsert_Panel.add(price_Label);
        
        price_textField = new JTextField();
        price_textField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        price_textField.setBounds(350, 305, 350, 50);
        carInsert_Panel.add(price_textField);
        price_textField.setColumns(10);
        
        JLabel insurance_Label = new JLabel("일일 보험료 : ");
        insurance_Label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        insurance_Label.setBounds(180, 390, 150, 50);
        carInsert_Panel.add(insurance_Label);
        
        insurance_textField = new JTextField();
        insurance_textField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        insurance_textField.setBounds(350, 395, 350, 50);
        carInsert_Panel.add(insurance_textField);
        insurance_textField.setColumns(10);
        
        JLabel PL_Label = new JLabel("연료 타입 : ");
        PL_Label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        PL_Label.setBounds(180, 480, 150, 50);
        carInsert_Panel.add(PL_Label);
        
        PL_textField = new JTextField();
        PL_textField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        PL_textField.setBounds(350, 485, 350, 50);
        carInsert_Panel.add(PL_textField);
        PL_textField.setColumns(10);
        
        car_insert_Button = new JButton("차량 등록");
        car_insert_Button.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        car_insert_Button.setBounds(350, 600, 150, 50);
        carInsert_Panel.add(car_insert_Button);
        
        //차량 등록 버튼 액션 리스너
        car_insert_Button.addActionListener(e ->{
        	//입력 필드가 비어 있는지 확인
        	if(car_no_textField.getText().isEmpty() || car_grade_textField.getText().isEmpty()
        			|| carType_textField.getText().isEmpty() || price_textField.getText().isBlank()
        			|| insurance_textField.getText().isEmpty() || PL_textField.getText().isEmpty()) {
        		JOptionPane.showMessageDialog(null, "모두 입력해야 차량 등록이 가능합니다.");
        	}else {
        		Car_Superintend car = car(); //차량 세부 정보 가져오기
        		
        		if(car != null) {
        			try {
						if(ManagerDAO.getManagerDAO().car_insert(car)) {
							JOptionPane.showMessageDialog(null, "차량 등록 되었습니다.");
							
						}else {
							JOptionPane.showMessageDialog(null, "차량 등록에 실패하였습니다.");
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "차량 등록에 실패하였습니다.\n 중복된 차량 번호입니다.", "오류", JOptionPane.ERROR_MESSAGE);
					}
        		}else {
        			JOptionPane.showMessageDialog(null, "차량 등록에 실패하였습니다.");
        		}
        		
        	}
        });
        // 탭 7 /////////////////////////////////////////////////
        JPanel car_inspection_Panel = new JPanel();
        car_inspection_Panel.setLayout(null);
        tabbedPane.addTab("차량 검사 등록", null, car_inspection_Panel, null);
        
        JLabel car_inspection_no_Label = new JLabel("차량 번호 : ");
        car_inspection_no_Label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        car_inspection_no_Label.setBounds(180, 100, 150, 50);
        car_inspection_Panel.add(car_inspection_no_Label);
        
        car_inspection_no_textField = new JTextField();
        car_inspection_no_textField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        car_inspection_no_textField.setBounds(350, 105, 350, 50);
        car_inspection_Panel.add(car_inspection_no_textField);
        car_inspection_no_textField.setColumns(10);
        
        JLabel car_inspection_Type_Label = new JLabel("검사 종류 : ");
        car_inspection_Type_Label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        car_inspection_Type_Label.setBounds(180, 200, 150, 50);
        car_inspection_Panel.add(car_inspection_Type_Label);
        
        car_inspection_textField = new JTextField();
        car_inspection_textField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        car_inspection_textField.setBounds(350, 205, 350, 50);
        car_inspection_Panel.add(car_inspection_textField);
        car_inspection_textField.setColumns(10);
        
        
        JLabel lase_date_Label = new JLabel("마지막 검사날짜 : ");
        lase_date_Label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        lase_date_Label.setBounds(180, 300, 152, 50);
        car_inspection_Panel.add(lase_date_Label);
        
        last_date_textField = new JTextField();
        last_date_textField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        last_date_textField.setBounds(350, 305, 350, 50);
        car_inspection_Panel.add(last_date_textField);
        last_date_textField.setColumns(10);
        
        icon = new ImageIcon("./image/calendar_icon.jpg");
		im = icon.getImage();
		im2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icon2 = new ImageIcon(im2);
		
		calendar_Label = new JLabel(icon);
		calendar_Label.setBounds(720, 305, 50, 50);
		car_inspection_Panel.add(calendar_Label);
		
		calendar_Label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				me.getSource();
				if (calendarWindowTest == 0) {
					new CustomCalendar(Manager.this); 
					calendarWindowTest = 1;
				}
			}
		});
        
        
        JLabel next_date_Label = new JLabel("다음 검사날짜 : ");
        next_date_Label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        next_date_Label.setBounds(180, 400, 150, 50);
        car_inspection_Panel.add(next_date_Label);
        
        next_date_textField = new JTextField();
        next_date_textField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        next_date_textField.setBounds(350, 405, 350, 50);
        car_inspection_Panel.add(next_date_textField);
        next_date_textField.setColumns(10);
        
        car_inspection_Button = new JButton("차량 검사 등록");
        car_inspection_Button.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        car_inspection_Button.setBounds(350, 550, 155, 50);
        car_inspection_Panel.add(car_inspection_Button);
        
        
        
     // 차량 등록 버튼 액션 리스너
        car_inspection_Button.addActionListener(e ->{
            // 입력 필드가 비어 있는지 확인
            if(car_inspection_no_textField.getText().isEmpty() || car_inspection_textField.getText().isEmpty()
                    || last_date_textField.getText().isEmpty() || next_date_textField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "모두 입력해야 차량 검사 등록이 가능합니다.");
            } else {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date last_date = dateFormat.parse(last_date_textField.getText());
                    java.util.Date next_date = dateFormat.parse(next_date_textField.getText());

                    Car_Inspection car = Car_Inspection.builder()
                        .car_no(car_inspection_no_textField.getText())
                        .inspection_type(car_inspection_textField.getText())
                        .Last_date(dateFormat.format(last_date))
                        .Next_date(dateFormat.format(next_date))
                        .build();

                    if(ManagerDAO.getManagerDAO().insertInspection(car)) {
                        JOptionPane.showMessageDialog(null, "차량 검사 등록 되었습니다.");
                    } else {
                        JOptionPane.showMessageDialog(null, "차량 검사 등록에 실패하였습니다.");
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "날짜 형식이 올바르지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "차량 검사 등록에 실패하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        add(tabbedPane, BorderLayout.CENTER);
    }
    
	
		
    private Car_Superintend car() {
        // 관리자가 입력한 세부 정보를 가져오고 차량 객체를 만듭니다.
        String car_no = car_no_textField.getText();
        String car_grade = car_grade_textField.getText();
        String CarType = carType_textField.getText();
        int price = Integer.parseInt(price_textField.getText()); // String을 int로 변환
        int insurance = Integer.parseInt(insurance_textField.getText()); // String을 int로 변환
        String pl = PL_textField.getText();

        return Car_Superintend.builder()
        		.car_no(car_no)
        		.car_garde(car_grade)
        		.carType(CarType)
        		.price(price)
        		.insurance(insurance)
        		.PL(pl)
        		.build();
    }


	//월 별 매출 데이터 보여주기
    public void price_displayAllData() {
        try {
            List<Month_total> monthTotalList = ManagerDAO.getManagerDAO().month_total();
            price_dm.setRowCount(0); // 기존 테이블 데이터 초기화

            for (Month_total mt : monthTotalList) {
                String[] rowData = new String[3];
                rowData[0] = mt.getMonths();
                rowData[1] = mt.getPayment_method();
                rowData[2] = String.valueOf(mt.getTotal());
                price_dm.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //월 별 매출 검색해서 보기
    public void price_searchAndDisplayData(String find, String selectedOption) {
        try {
            List<Month_total> monthTotalList = ManagerDAO.getManagerDAO().month_total();
            price_dm.setRowCount(0); // 기존 테이블 데이터 초기화

            for (Month_total mt : monthTotalList) {
                String[] rowData = new String[3];
                rowData[0] = mt.getMonths();
                rowData[1] = mt.getPayment_method();
                rowData[2] = String.valueOf(mt.getTotal());

                boolean isMatched = false; // 검색 결과 여부를 판단하는 변수

                if (selectedOption.equals("날짜") && rowData[0].contains(find)) {
                    isMatched = true;
                } else if (selectedOption.equals("결제 방법") && rowData[1].contains(find)) {
                    isMatched = true;
                } else if (selectedOption.equals("금액") && rowData[2].contains(find)) {
                    isMatched = true;
                }

                if (isMatched) {
                    price_dm.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
  //자동차 검사 데이터 보여주기
    public void Car_Inspection_displayAllData() {
        try {
            List<Car_Inspection> monthTotalList = CarInspectionDAO.getCarInspectionDAO().selectAll();
            Car_Inspection_dm.setRowCount(0); // 기존 테이블 데이터 초기화

            for (Car_Inspection mt : monthTotalList) {
                String[] rowData = new String[4];
                rowData[0] = mt.getCar_no();
                rowData[1] = mt.getInspection_type();
                rowData[2] = String.valueOf(mt.getLast_date());
                rowData[3] = String.valueOf(mt.getNext_date());
                Car_Inspection_dm.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    //자동차 검사 검색해서 보기
    public void Car_Inspection_searchAndDisplayData(String find, String selectedOption) {
    	try {
            List<Car_Inspection> monthTotalList = CarInspectionDAO.getCarInspectionDAO().selectAll();
            Car_Inspection_dm.setRowCount(0); // 기존 테이블 데이터 초기화

            for (Car_Inspection mt : monthTotalList) {
                String[] rowData = new String[4];
                rowData[0] = mt.getCar_no();
                rowData[1] = mt.getInspection_type();
                rowData[2] = String.valueOf(mt.getLast_date());
                rowData[3] = String.valueOf(mt.getNext_date());
                

                boolean isMatched = false; // 검색 결과 여부를 판단하는 변수

                if (selectedOption.equals("차량 번호") && rowData[0].contains(find)) {
                    isMatched = true;
                } else if (selectedOption.equals("검사 종류") && rowData[1].contains(find)) {
                    isMatched = true;
                } else if (selectedOption.equals("마지막 검사날짜") && rowData[2].contains(find)) {
                    isMatched = true;
                }else if (selectedOption.equals("다음 검사날짜") && rowData[3].contains(find)) {
                    isMatched = true;
                }

                if (isMatched) {
                	Car_Inspection_dm.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //대여 상태 보여주기
    public void car_rent_displayAllData() {
        try {
            List<Car_Superintend> monthTotalList = ManagerDAO.getManagerDAO().status();
            car_rent_dm.setRowCount(0); // 기존 테이블 데이터 초기화

            for (Car_Superintend mt : monthTotalList) {
                String[] rowData = new String[7];
                rowData[0] = mt.getCar_no();
                rowData[1] = mt.getCar_garde();
                rowData[2] = mt.getCarType();
                rowData[3] = mt.getRent_Type();
                rowData[4] = String.valueOf(mt.getPrice());
                rowData[5] = String.valueOf(mt.getInsurance());
                rowData[6] = mt.getPL();
                car_rent_dm.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //대여 상태 검색해서 보기
    public void car_rent_searchAndDisplayData(String find, String selectedOption) {
    	try {
            List<Car_Superintend> monthTotalList = ManagerDAO.getManagerDAO().status();
            car_rent_dm.setRowCount(0); // 기존 테이블 데이터 초기화

            for (Car_Superintend mt : monthTotalList) {
                String[] rowData = new String[7];
                rowData[0] = mt.getCar_no();
                rowData[1] = mt.getCar_garde();
                rowData[2] = mt.getCarType();
                rowData[3] = mt.getRent_Type();
                rowData[4] = String.valueOf(mt.getPrice());
                rowData[5] = String.valueOf(mt.getInsurance());
                rowData[6] = mt.getPL();
                
                boolean isMatched = false; // 검색 결과 여부를 판단하는 변수

                if (selectedOption.equals("차량 번호") && rowData[0].contains(find)) {
                	isMatched = true;
                } else if (selectedOption.equals("차량 등급") && rowData[1].contains(find)) {
                	isMatched = true;
                } else if (selectedOption.equals("차량 종류") && rowData[2].contains(find)) {
                	isMatched = true;
                } else if (selectedOption.equals("예약 상태") && rowData[3].contains(find)) {
                	isMatched = true;
                }else if (selectedOption.equals("대여요금") && rowData[4].contains(find)) {
                	isMatched = true;
                }else if (selectedOption.equals("보험료(자차)") && rowData[5].contains(find)) {
                	isMatched = true;
                }else if (selectedOption.equals("연료") && rowData[6].contains(find)) {
                	isMatched = true;
                }
                if (isMatched) {
                	car_rent_dm.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    
    //회원별 매출 보여주기
    public void customer_price_displayAllData() {
        try {
            List<Payment> monthTotalList = ManagerDAO.getManagerDAO().sales();
            customer_price_dm.setRowCount(0); // 기존 테이블 데이터 초기화

            for (int i = 0; i < monthTotalList.size(); i++) {
                Payment payment = monthTotalList.get(i);
                String[] rowData = new String[6];
                rowData[0] = String.valueOf(i+1);
                rowData[1] = payment.getName();
                rowData[2] = String.valueOf(payment.getPayment_day());
                rowData[3] = String.valueOf(payment.getMoney());
                rowData[4] = payment.getPayment_method();
                rowData[5] = payment.getCar_no();
                customer_price_dm.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //회원별 매출 검색해서 보기
    public void customer_price_searchAndDisplayData(String find, String selectedOption) {
    	try {
            List<Payment> monthTotalList = ManagerDAO.getManagerDAO().sales();
            customer_price_dm.setRowCount(0); // 기존 테이블 데이터 초기화

            for (int i = 0; i < monthTotalList.size(); i++) {
                Payment payment = monthTotalList.get(i);
                String[] rowData = new String[6];
                rowData[0] = String.valueOf(i+1);
                rowData[1] = payment.getName();
                rowData[2] = String.valueOf(payment.getPayment_day());
                rowData[3] = String.valueOf(payment.getMoney());
                rowData[4] = payment.getPayment_method();
                rowData[5] = payment.getCar_no();
                
                boolean isMatched = false; // 검색 결과 여부를 판단하는 변수

                if (selectedOption.equals("순서") && rowData[0].contains(find)) {
                	isMatched = true;
                } else if (selectedOption.equals("회원 이름") && rowData[1].contains(find)) {
                	isMatched = true;
                } else if (selectedOption.equals("결제 날짜") && rowData[2].contains(find)) {
                	isMatched = true;
                } else if (selectedOption.equals("결제 금액") && rowData[3].contains(find)) {
                	isMatched = true;
                }else if (selectedOption.equals("결제 방법") && rowData[4].contains(find)) {
                	isMatched = true;
                }else if (selectedOption.equals("차량 번호") && rowData[5].contains(find)) {
                	isMatched = true;
                }
                if (isMatched) {
                	customer_price_dm.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  //회원 정보 보여주기
    public void customer_displayAllData() {
        try {
            List<Customer> customers = CustomerDAO.getCustomerDAO().selectAll();
            customer_dm.setRowCount(0); // 기존 테이블 데이터 초기화

            for (int i = 0; i < customers.size(); i++) {
                Customer cus = customers.get(i);
                String[] rowData = new String[5];
                rowData[0] = cus.getName();
                rowData[1] = cus.getCustomer_id();
                rowData[2] = cus.getPw();
                rowData[3] = cus.getPhone();
                rowData[4] = cus.getLicence();
                customer_dm.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //회원 정보 검색해서 보기
    public void customer_searchAndDisplayData(String find, String selectedOption) {
    	try {
    		List<Customer> customers = CustomerDAO.getCustomerDAO().selectAll();
            customer_dm.setRowCount(0); // 기존 테이블 데이터 초기화

            for (int i = 0; i < customers.size(); i++) {
                Customer cus = customers.get(i);
                String[] rowData = new String[5];
                rowData[0] = cus.getName();
                rowData[1] = cus.getCustomer_id();
                rowData[2] = cus.getPw();
                rowData[3] = cus.getPhone();
                rowData[4] = cus.getLicence();
                customer_dm.addRow(rowData);
                
                boolean isMatched = false; // 검색 결과 여부를 판단하는 변수

                if (selectedOption.equals("회원 이름") && rowData[0].contains(find)) {
                	isMatched = true;
                } else if (selectedOption.equals("회원 ID") && rowData[1].contains(find)) {
                	isMatched = true;
                } else if (selectedOption.equals("회원 PW") && rowData[2].contains(find)) {
                	isMatched = true;
                } else if (selectedOption.equals("전화번호") && rowData[3].contains(find)) {
                	isMatched = true;
                }else if (selectedOption.equals("면허증 여부") && rowData[4].contains(find)) {
                	isMatched = true;
                }
                if (isMatched) {
                	customer_dm.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    //main
    public static void main(String[] args) {

        // 프레임 생성 및 설정
        JFrame frame = new JFrame("관리자 관리 창");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\82108\\OneDrive\\바탕 화면\\자동차 로고\\로그인Test.jpg"));

        // 패널 생성 및 프레임에 추가
        Manager manager = new Manager();
        frame.getContentPane().add(manager);

        // 프레임 크기 자동 조절 및 표시
        frame.pack();
        frame.setVisible(true);
    }

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	class CustomCalendar extends JFrame implements ActionListener, WindowListener {

		private Manager manager; 

		
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

		public CustomCalendar(Manager manager) {
			this.manager = manager; // Pay 클래스 참조를 저장
			
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
				        if (str.equals("")) {
				            return;
				        } else if (str.length() == 1) {
				            str = "0" + str;
				        }

				        // 받은 "월"이 1자리면 0을 붙여라
				        if (m.length() == 1) {
				            m = "0" + m;
				        }

				        last_date_textField.setText(y + "-" + m + "-" + str);
				        manager.setLast_date(parseDate(y + "-" + m + "-" + str));

				        // 다음 검사 날짜 계산
				        Calendar cal = Calendar.getInstance();
				        cal.set(Integer.parseInt(y), Integer.parseInt(m) - 1, Integer.parseInt(str));
				        cal.add(Calendar.YEAR, 2); // 다음 날짜로 변경
				        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				        String nextDate = dateFormat.format(cal.getTime());
				        next_date_textField.setText(nextDate);
				        manager.setNext_date(parseDate(nextDate));

				        dispose();  // 캘린더 창 닫기
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
}

