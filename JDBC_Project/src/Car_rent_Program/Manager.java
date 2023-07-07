package Car_rent_Program;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import DAO.CarInspectionDAO;
import DAO.ManagerDAO;
import DTO.Car_Inspection;
import DTO.Car_Superintend;
import DTO.Month_total;
import DTO.Payment;
import java.awt.Toolkit;
import java.awt.Font;

//관리자
@SuppressWarnings("serial")
public class Manager extends JPanel {

    DefaultTableModel price_dm;
    DefaultTableModel customer_dm;
    DefaultTableModel Car_Inspection_dm;
    DefaultTableModel car_rent_dm;
    JTable price_jt;
    JTable customer_jt;
    JTable Car_Inspection_jt;
    JTable car_rent_jt;
    String[] price = { "날짜", "결제 방법", "금액" };
    String[] customer = { "순서", "회원 이름", "결제한 날짜", "결제 금액", "결제 방법", "차량 번호" };
    String[] Car_Inspection = { "차량 번호", "검사 종류", "마지막 검사날짜", "다음 검사날짜" };
    String[] car_rent = { "차량 번호", "차량 등급", "차량 종류", "예약 상태", "대여 요금", "보험료(자차)", "연료" };

    public Manager() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(900, 800));

        JTabbedPane tabbedPane = new JTabbedPane();

        // 탭 1 /////////////////////////////////////////////////
        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(null);

        customer_dm = new DefaultTableModel(null, customer);
        customer_jt = new JTable(customer_dm);
        customer_jt.setFont(new Font("굴림", Font.BOLD, 14));

        JScrollPane customer_jsp = new JScrollPane(customer_jt);
        customer_jsp.setBounds(10, 60, 550, 500);

        JLabel customer_la = new JLabel("검색 내용");
        customer_la.setFont(new Font("굴림", Font.BOLD, 12));
        customer_la.setBounds(10, 10, 100, 30);

        JTextField customer_jtf = new JTextField(); // 텍스트 입력
        customer_jtf.setBounds(120, 10, 200, 30); // 텍스트 입력창 크기

        String[] customer_temp = { "순서", "회원 이름", "결제 날짜", "결제 요금", "결제 방법", "차량 번호" };
        JComboBox<String> customer_jc = new JComboBox<>(customer_temp); // 선택항목
        customer_jc.setFont(new Font("굴림", Font.BOLD, 12));
        customer_jc.setBounds(330, 10, 100, 30); // 선택항목 크기

        JButton customer_btn = new JButton("검색"); // 상품 검색 버튼
        customer_btn.setFont(new Font("굴림", Font.BOLD, 12));
        customer_btn.setBounds(440, 10, 100, 30); // 상품 검색 버튼 크기

        customer_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String find = customer_jtf.getText();
                String selectedOption = (String) customer_jc.getSelectedItem();
                customer_searchAndDisplayData(find, selectedOption);
            }
        });

        customerPanel.add(customer_la);
        customerPanel.add(customer_jtf);
        customerPanel.add(customer_jc);
        customerPanel.add(customer_btn);
        customerPanel.add(customer_jsp);

        tabbedPane.addTab("회원 결제 내역", customerPanel);
        
        // 초기 데이터 표시
        customer_displayAllData();

        // 탭 2 /////////////////////////////////////////////////
        JPanel carRentPanel = new JPanel();
        carRentPanel.setLayout(null);

        car_rent_dm = new DefaultTableModel(null, car_rent);
        car_rent_jt = new JTable(car_rent_dm);
        car_rent_jt.setFont(new Font("굴림", Font.BOLD, 14));

        JScrollPane car_rent_jsp = new JScrollPane(car_rent_jt);
        car_rent_jsp.setBounds(10, 60, 550, 500);

        JLabel car_rent_la = new JLabel("검색 내용");
        car_rent_la.setFont(new Font("굴림", Font.BOLD, 12));
        car_rent_la.setBounds(10, 10, 100, 30);

        JTextField car_rent_jtf = new JTextField(); // 텍스트 입력
        car_rent_jtf.setBounds(120, 10, 200, 30); // 텍스트 입력창 크기

        String[] car_rent_temp = { "차량 번호", "차량 등급", "차량 종류", "예약 상태", "대여 요금", "보험료(자차)", "연료" };
        JComboBox<String> car_rent_jc = new JComboBox<>(car_rent_temp); // 선택항목
        car_rent_jc.setFont(new Font("굴림", Font.BOLD, 12));
        car_rent_jc.setBounds(330, 10, 100, 30); // 선택항목 크기

        JButton car_rent_btn = new JButton("검색"); // 상품 검색 버튼
        car_rent_btn.setFont(new Font("굴림", Font.BOLD, 12));
        car_rent_btn.setBounds(440, 10, 100, 30); // 상품 검색 버튼 크기

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

        tabbedPane.addTab("차량 대여 내역", carRentPanel);
        
        // 초기 데이터 표시
        car_rent_displayAllData();

        // 탭 3 /////////////////////////////////////////////////
        JPanel pricePanel =new JPanel();
        pricePanel.setLayout(null);

        price_dm = new DefaultTableModel(null, price);
        price_jt = new JTable(price_dm);
        price_jt.setFont(new Font("굴림", Font.BOLD, 14));

        JScrollPane price_jsp = new JScrollPane(price_jt);
        price_jsp.setBounds(10, 60, 550, 500);

        JLabel price_la = new JLabel("검색 내용");
        price_la.setFont(new Font("굴림", Font.BOLD, 12));
        price_la.setBounds(10, 10, 100, 30);

        JTextField price_jtf = new JTextField(); // 텍스트 입력
        price_jtf.setBounds(120, 10, 200, 30); // 텍스트 입력창 크기

        String[] price_temp = { "날짜", "결제 방법", "금액" };
        JComboBox<String> price_jc = new JComboBox<>(price_temp); // 선택항목
        price_jc.setFont(new Font("굴림", Font.BOLD, 12));
        price_jc.setBounds(330, 10, 100, 30); // 선택항목 크기

        JButton price_btn = new JButton("검색"); // 상품 검색 버튼
        price_btn.setFont(new Font("굴림", Font.BOLD, 12));
        price_btn.setBounds(440, 10, 100, 30); // 상품 검색 버튼 크기

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

        tabbedPane.addTab("결제 금액 내역", pricePanel);
        
        // 초기 데이터 표시
	    price_displayAllData();

        // 탭 4 /////////////////////////////////////////////////
        JPanel carInspectionPanel = new JPanel();
        carInspectionPanel.setLayout(null);

        Car_Inspection_dm = new DefaultTableModel(null, Car_Inspection);
        Car_Inspection_jt = new JTable(Car_Inspection_dm);
        Car_Inspection_jt.setFont(new Font("굴림", Font.BOLD, 14));

        JScrollPane Car_Inspection_jsp = new JScrollPane(Car_Inspection_jt);
        Car_Inspection_jsp.setBounds(10, 60, 550, 500);

        JLabel Car_Inspection_la = new JLabel("검색 내용");
        Car_Inspection_la.setFont(new Font("굴림", Font.BOLD, 12));
        Car_Inspection_la.setBounds(10, 10, 100, 30);

        JTextField Car_Inspection_jtf = new JTextField(); // 텍스트 입력
        Car_Inspection_jtf.setBounds(120, 10, 200, 30); // 텍스트 입력창 크기

        String[] Car_Inspection_temp = { "차량 번호", "검사 종류", "마지막 검사날짜", "다음 검사날짜" };
        JComboBox<String> Car_Inspection_jc = new JComboBox<>(Car_Inspection_temp); // 선택항목
        Car_Inspection_jc.setFont(new Font("굴림", Font.BOLD, 12));
        Car_Inspection_jc.setBounds(330, 10, 100, 30); // 선택항목 크기

        JButton Car_Inspection_btn = new JButton("검색"); // 상품 검색 버튼
        Car_Inspection_btn.setFont(new Font("굴림", Font.BOLD, 12));
        Car_Inspection_btn.setBounds(440, 10, 100, 30); // 상품 검색 버튼 크기

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

        tabbedPane.addTab("차량 검사 내역", carInspectionPanel);
        
        // 초기 데이터 표시
        Car_Inspection_displayAllData();

        add(tabbedPane, BorderLayout.CENTER);
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
    public void customer_displayAllData() {
        try {
            List<Payment> monthTotalList = ManagerDAO.getManagerDAO().sales();
            customer_dm.setRowCount(0); // 기존 테이블 데이터 초기화

            for (int i = 0; i < monthTotalList.size(); i++) {
                Payment payment = monthTotalList.get(i);
                String[] rowData = new String[6];
                rowData[0] = String.valueOf(i+1);
                rowData[1] = payment.getName();
                rowData[2] = String.valueOf(payment.getPayment_day());
                rowData[3] = String.valueOf(payment.getMoney());
                rowData[4] = payment.getPayment_method();
                rowData[5] = payment.getCar_no();
                customer_dm.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //회원별 매출 검색해서 보기
    public void customer_searchAndDisplayData(String find, String selectedOption) {
    	try {
            List<Payment> monthTotalList = ManagerDAO.getManagerDAO().sales();
            customer_dm.setRowCount(0); // 기존 테이블 데이터 초기화

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
}

