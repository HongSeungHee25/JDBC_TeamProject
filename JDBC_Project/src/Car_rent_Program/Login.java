package Car_rent_Program;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import DAO.CustomerDAO;
import DTO.Customer;
import gui.Choicecar_1;

//로그인 화면 구현 - 승희
//로그인 JDBC 연결 - 병인
@SuppressWarnings("serial")
public class Login extends JFrame {
	
	private JButton btnMypage;
	Customer customer; 
	private JScrollPane jScrollPane;
    private ImageIcon icon;
    private JLabel label;
    private JLabel label_1;
    private JButton loginButton;
    private JButton joinButton;
    private JButton Managerbutton;
    
	String loggedInUserName;

    public Login() {
    	setTitle("렌트가 예약 프로그램");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\82108\\OneDrive\\바탕 화면\\자동차 로고\\로그인Test.jpg"));
        setSize(900, 800);
        setLocationRelativeTo(null);  
        
        icon = new ImageIcon("./image/로그인백그라운드.jpg");

        JPanel back = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        back.setLayout(null);


        label = new JLabel("ID : ");
        label.setBounds(280, 450, 100, 30);
        label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        back.add(label);

        JTextField textField = new JTextField();
        textField.setBounds(330, 450, 220, 30);
        back.add(textField);

        label_1 = new JLabel("PW : ");
        label_1.setBounds(280, 500, 100, 30);
        label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
        back.add(label_1);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(330, 500, 220, 30);
        back.add(passwordField);

        loginButton = new JButton("로그인");
        loginButton.setBounds(280, 550, 100, 30);
        loginButton.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
        back.add(loginButton);

        joinButton = new JButton("회원 가입");
        joinButton.setBounds(392, 550, 100, 30);
        joinButton.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
        back.add(joinButton);

        Managerbutton = new JButton("관리자로 로그인");
        Managerbutton.setBounds(370, 600, 150, 30);
        Managerbutton.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
        back.add(Managerbutton);

        btnMypage = new JButton("MyPage");
        btnMypage.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
        btnMypage.setBounds(504, 550, 100, 30);
        back.add(btnMypage);
        
        // 마이페이지 버튼 액션 리스너
        btnMypage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String id = textField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    customer = CustomerDAO.getCustomerDAO().login(id, password);
                    if (customer != null) {
                    	// 로그인 성공 시 실행할 코드 작성
                    	DataHolder.loggedInUserName = customer.getName();
                    	JOptionPane.showMessageDialog(null, "마이페이지로 이동! "+ customer.getName()+" 님 환영합니다.\n확인 버튼을 누르면 마이페이지로 이동합니다.");

                    	openMyPage(customer.getName());


                        // 현재 로그인 창 닫기
                        dispose();
                    } else {
                        // 로그인 실패 시 실행할 코드 작성
                        JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 일치하지 않습니다.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
				
			}
		});
        
        // 로그인 버튼에 액션 리스너 추가
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                String id = textField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    customer = CustomerDAO.getCustomerDAO().login(id, password);
                    if (customer != null) {
                    	// 로그인 성공 시 실행할 코드 작성
                    	DataHolder.loggedInUserName = customer.getName();
                    	JOptionPane.showMessageDialog(null, "로그인 성공! "+ customer.getName()+" 님 환영합니다.\n확인 버튼을 누르면 차량 등급을 선택합니다.");

                    	// 차량 등급 패널로 이동
                        openChoiceCarType(customer.getName()); // Pass the loggedInUserName


                        // 현재 로그인 창 닫기
                        dispose();
                    } else {
                        // 로그인 실패 시 실행할 코드 작성
                        JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 일치하지 않습니다.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


       


        // 회원 가입 버튼에 액션 리스너 추가
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 회원 가입 창을 띄우는 코드 작성
                Join join = new Join();
                join.setVisible(true);
            }
        });
    
     // 관리자로 로그인 버튼에 액션 리스너 추가
        Managerbutton.addActionListener(e -> {
            JPasswordField M_passwordField = new JPasswordField();
            int option = JOptionPane.showOptionDialog(this, M_passwordField, "관리자 비밀번호",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            if (option == JOptionPane.OK_OPTION) {
                char[] passwordChars = M_passwordField.getPassword();
                String password = new String(passwordChars);
                if (password.equals("1234")) {
                    openManagerScreen();
                }
                // 비밀번호 사용 이후에는 passwordChars 배열을 직접 지워주는 것이 안전합니다.
                // Arrays.fill(passwordChars, '0');
            }
        });
    
    
    

        jScrollPane = new JScrollPane(back);
        setContentPane(jScrollPane);
        
    }
    
 // 차량 선택 화면으로 이동
 protected void openChoiceCarType(String name) throws SQLException {

	 Choicecar choicecar =  new Choicecar(name); // 여기서 생성자 한 번 호출 
     choicecar.setVisible(true);
     //choicecar.main(null);		// 여기서 choicecar 클래스 내부 메인 한 번 호출.
		
	}
 
 	private void openMyPage(String name) {
 		MyPage mypage = new MyPage(name);
 		mypage.setVisible(true);
 	}
 
    private void openManagerScreen() {
        SwingUtilities.invokeLater(() -> {
            Manager manager = null;
			manager = new Manager();
            JFrame frame = new JFrame("관리자");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(900, 800);
            frame.setLocationRelativeTo(null);
            frame.add(manager);
            frame.setVisible(true);
        });
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

public class DataHolder {
    public static String loggedInUserName;  // 전역 변수로 사용자 이름 저장

    // ... 추가 필요한 데이터 저장
}
}
