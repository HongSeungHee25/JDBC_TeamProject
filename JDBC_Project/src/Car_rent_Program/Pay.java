package Car_rent_Program;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//결제 
@SuppressWarnings("serial")
public class Pay extends JFrame{

	private JLabel label;
	private JLabel label_3;
	private JLabel label_2;
	private JLabel label_1;
	private JButton button;
	
	public Pay() {
		setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 13));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\82108\\OneDrive\\바탕 화면\\자동차 로고\\로그인Test.jpg"));
		setBounds(100, 100, 900, 800);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("결제 화면");
	    getContentPane().setLayout(null);
	    setLocationRelativeTo(null);
		
		label = new JLabel("대여 날짜 : ");
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 19));
		label.setBounds(200,150,150,30);
		label.setHorizontalAlignment(JLabel.CENTER);
		getContentPane().add(label);
		
		JTextField textField = new JTextField();
		textField.setBounds(350, 150, 300, 40);
		getContentPane().add(textField);
		
		
		label_1 = new JLabel("반납 날짜 : ");
		label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 19));
		label_1.setBounds(200,250,150,30);
		label_1.setHorizontalAlignment(JLabel.CENTER);
		getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(350, 250, 300, 40);
		getContentPane().add(textField);
		
		
		label_2 = new JLabel("결제 할 금액 : ");
		label_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 19));
		label_2.setBounds(200,350,150,30);
		label_2.setHorizontalAlignment(JLabel.CENTER);
		getContentPane().add(label_2);
		
		JLabel lblNewLabel = new JLabel("금액 출력");
		lblNewLabel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		lblNewLabel.setBounds(350, 350, 300, 40);
		getContentPane().add(lblNewLabel);
		
		
		label_3 = new JLabel("결제 방법 : ");
		label_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 19));
		label_3.setBounds(200,450,150,30);
		label_3.setHorizontalAlignment(JLabel.CENTER);
		getContentPane().add(label_3);
		
		String[] temp = {"신용카드","계좌이체"};
		JComboBox<String> jc = new JComboBox<>(temp);
		jc.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		jc.setBounds(350, 450, 300, 40);
		getContentPane().add(jc);
		
		button = new JButton("결제하기");
		button.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		button.setBounds(380, 600, 150, 50);
		getContentPane().add(button);
		
		//결제하기 버튼 액션 리스너 -> 수정 해야됨 
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "결제가 완료 되었습니다.");
			}
		});
		
	}
	public static void main(String[] args) {
		 EventQueue.invokeLater(new Runnable() {
	         public void run() {
	            try {
	            	Pay window = new Pay();
	               window.setVisible(true);
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	         }
	      });
	   }
}