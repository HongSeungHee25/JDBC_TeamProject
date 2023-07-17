package Car_rent_Program;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import DAO.CustomerDAO;
import DTO.Customer;
import java.awt.Color;

//회원 가입 화면 구현 - 종화
//회원 가입 JDBC 연결(Insert) - 승희
@SuppressWarnings("serial")
public class Join extends JFrame {
	
    private JLabel label;
    private JLabel label_4;
    private JLabel label_3;
    private JLabel label_2;
    private JLabel label_1;
    private JCheckBox licenseCheckBox; 
    private JTextField nameTextField;
    private JTextField idTextField;
    private JPasswordField passwordField;
    private JTextField phoneTextField;
    private JButton joinbutton;
    private JButton cancelButton;
    private JScrollPane jScrollPane;
    
    ImageIcon gifIcon;

    public Join() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("./image/차량선택 백그라운드.jpg")); // 프로젝트 내부 경로로 변경
    	setTitle("회원 가입");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(700, 600);
        setLocationRelativeTo(null);

        gifIcon = new ImageIcon("./image/소울카회원가입GIF.gif"); // GIF 이미지 파일 경로로 변경

        
        JPanel back = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = gifIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        back.setLayout(null);
        setContentPane(back);
        
        label = new JLabel("회원 이름 : ");
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
        label.setBounds(150, 130, 100, 30);
        label.setHorizontalAlignment(JLabel.CENTER);
        back.add(label);

        nameTextField = new JTextField();
        nameTextField.setBounds(250, 130, 250, 30);
        back.add(nameTextField);

        label_1 = new JLabel("회원 ID : ");
        label_1.setForeground(new Color(255, 255, 255));
        label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
        label_1.setBounds(150, 180, 100, 30);
        label_1.setHorizontalAlignment(JLabel.CENTER);
        back.add(label_1);

        idTextField = new JTextField();
        idTextField.setBounds(250, 180, 250, 30);
        back.add(idTextField);

        label_2 = new JLabel("비밀번호 : ");
        label_2.setForeground(new Color(255, 255, 255));
        label_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
        label_2.setBounds(150, 230, 100, 30);
        label_2.setHorizontalAlignment(JLabel.CENTER);
        back.add(label_2);

        passwordField = new JPasswordField();
        passwordField.setBounds(250, 230, 250, 30);
        back.add(passwordField);

        label_3 = new JLabel("전화번호 : ");
        label_3.setForeground(new Color(255, 255, 255));
        label_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
        label_3.setBounds(150, 280, 100, 30);
        label_3.setHorizontalAlignment(JLabel.CENTER);
        back.add(label_3);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(250, 280, 250, 30);
        back.add(phoneTextField);

        label_4 = new JLabel("면허증 여부 ");
        label_4.setForeground(new Color(255, 255, 255));
        label_4.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
        label_4.setBounds(260, 330, 120, 30);
        label_4.setHorizontalAlignment(JLabel.CENTER);
        back.add(label_4);

        licenseCheckBox = new JCheckBox(); 
        licenseCheckBox.setBounds(380, 330, 250, 30);
        licenseCheckBox.setOpaque(false);
        back.add(licenseCheckBox);

        joinbutton = new JButton("회원가입");
        joinbutton.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
        joinbutton.setBounds(350, 380, 90, 30);
        joinbutton.setHorizontalAlignment(JLabel.CENTER);
        back.add(joinbutton);

        cancelButton = new JButton("취소");
        cancelButton.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
        cancelButton.setBounds(260, 380, 60, 30);
        cancelButton.setHorizontalAlignment(JLabel.CENTER);
        back.add(cancelButton);

     // 회원가입 버튼 액션 리스너
        joinbutton.addActionListener(e -> {
            //입력 필드가 비어 있는지 확인
            if (nameTextField.getText().isEmpty()
                    || idTextField.getText().isEmpty()
                    || new String(passwordField.getPassword()).isEmpty()
                    || phoneTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "회원 가입 정보를 모두 입력해야 합니다.");
            } else if (!licenseCheckBox.isSelected()) {
                JOptionPane.showMessageDialog(null, "면허증을 체크해야 회원 가입이 가능합니다.");
            } else {
                Customer customer = join(); // 고객 세부 정보 가져오기

                if (customer != null) {
                    try {
                        if (CustomerDAO.getCustomerDAO().join(customer)) {
                            JOptionPane.showMessageDialog(null, "회원 가입 성공!");
                            dispose(); // 가입 창 닫기
                        } else {
                            JOptionPane.showMessageDialog(null, "회원 가입 실패!");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "회원 가입 실패!");
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "회원 가입 실패!");
                }
            }
        });

        // 취소 버튼 액션 리스너 
        cancelButton.addActionListener(e -> {
            dispose(); // 가입 창 닫기 
        });
        
        jScrollPane = new JScrollPane(back);
        setContentPane(jScrollPane);
    }
    public Customer join() {
        // 사용자가 입력한 세부 정보를 가져오고 고객 개체를 만듭니다.
        String name = nameTextField.getText();
        String id = idTextField.getText();
        String password = new String(passwordField.getPassword());
        String phone = phoneTextField.getText();
        String license = licenseCheckBox.isSelected() ? "Y" : "N";

        // 입력 확인(선택 사항): 입력 값이 요구 사항에 따라 유효한지 확인합니다.

        return Customer.builder()
                .name(name)
                .customer_id(id)
                .pw(password)
                .phone(phone)
                .licence(license)
                .build();
    }


}