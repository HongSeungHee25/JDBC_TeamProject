package Car_rent_Program;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import DAO.CarRentDAO;
import DAO.CarSuperintendDAO;

// 차량 선택 화면 구현 - 병인, 종화, 진만, 지수
// 차량 선택 JDBC 연결(자동차 정보) - 승희, 지수, 진만
@SuppressWarnings("serial")
public class Choicecar extends JPanel {
	
	 private String selectedCar;  	// 선택한 차량 정보를 저장하기 위한 변수
	 private int rentalFee;			// 선택한 차량의 일일 대여료를 저장하기 위한 변수
	 private int insuranceFee;		// 선택한 차량의 일일 보험료를 저장하기 위한 변수 
	 private String name;
	 private Image backgroundImage; // 백그라운드 이미지를 저장할 변수
	 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    frame.setTitle("SOULCAR");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(900, 800);
                    frame.setLocationRelativeTo(null);

                    Choicecar choicecar1 = new Choicecar(null);
                    frame.getContentPane().add(choicecar1);

                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Choicecar(String na) throws SQLException {
    	this.name = na;
        initialize();
    }

    public void initialize() throws SQLException {
    	JFrame frame = new JFrame();
        frame.setTitle("SOULCAR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);
        frame.setLocationRelativeTo(null);
    	
        frame.getContentPane().add(this);

        frame.setVisible(true);
    	
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(900, 800));
        
        // 이미지 파일을 로드하여 backgroundImage 변수에 저장
        backgroundImage = Toolkit.getDefaultToolkit().getImage("./image/차량선택.jpg");

        // 탭 객체 생성 및 위치 지정
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 10, 870, 740);
        add(tabbedPane);
        tabbedPane.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        List<String> carGradeList = CarSuperintendDAO.getCarSuperintendDAO().car_grade();

        // 차량 이미지 파일명 배열
        String[][] carImages = { 
            { "./image/2023 Morning.jpg", "./image/2023 Ray.jpg", "./image/2023 Spark.jpg", "./image/2023 Casper.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg" },
            { "./image/2023 Kona.jpg", "./image/2023 Seltos.jpg", "./image/2023 K3.jpg", "./image/2023 Tivoli air.jpg", "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg"  },
            { "./image/2023 Sonata.jpg", "./image/2023 K5.jpg", "./image/2023 Sportage.jpg", "./image/2023 Tucson.jpg", "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg"  },
            { "./image/2023 palisade.jpg", "./image/2023 Carnival.jpg", "./image/2023 Staria.jpg", "./image/Add_image.jpg","./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg",  "./image/Add_image.jpg" },
            { "./image/2023 G90.png", "./image/2023 K9.jpg", "./image/Add_image.jpg", "./image/Add_image.jpg", "./image/Add_image.jpg", "./image/Add_image.jpg", "./image/Add_image.jpg", "./image/Add_image.jpg", "./image/Add_image.jpg", "./image/Add_image.jpg", "./image/Add_image.jpg"}
        };

        for (int gradeIndex = 0; gradeIndex < carGradeList.size(); gradeIndex++) {
            String grade = carGradeList.get(gradeIndex);

            // 탭 생성 및 설정
            JPanel carPanel = new JPanel(new GridBagLayout()){
            	 @Override
                 protected void paintComponent(Graphics g) {
                     super.paintComponent(g);
                     // 백그라운드 이미지 그리기
                     g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                     
                 }
             };
            

            // 차량 탭 패널에 스크롤 패널 생성
            JScrollPane carScrollPane = new JScrollPane(carPanel);
            carScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            carScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

            // 탭 추가
            tabbedPane.addTab(grade, carScrollPane);

            // 등급에 해당하는 차량 정보 가져오기
            List<String[]> carNamesList = CarSuperintendDAO.getCarSuperintendDAO().getCarNamesByGrade(grade);

            // GridBagLayout의 GridBagConstraints 생성
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(10, 15, 10, 10); // 각 컴포넌트의 여백 조정

            // 차량 정보 출력
            for (int i = 0; i < carNamesList.size(); i++) {
                String[] carInfo = carNamesList.get(i);

                // 차량 이미지 버튼 생성
                ImageIcon carImageIcon = new ImageIcon(carImages[gradeIndex][i]);
                Image image = carImageIcon.getImage().getScaledInstance(220, 150, Image.SCALE_SMOOTH);
                ImageIcon scaledCarImageIcon = new ImageIcon(image);
                JButton carButton = new JButton(scaledCarImageIcon);
                carButton.setPreferredSize(new Dimension(220, 150));
                carButton.setVerticalTextPosition(JButton.BOTTOM);
                carButton.setHorizontalTextPosition(JButton.CENTER);
                carPanel.add(carButton, gbc);

                // 차량 정보 라벨 생성
                gbc.gridx++;
                gbc.anchor = GridBagConstraints.WEST;
                carPanel.add(new JLabel(" "), gbc); // 간격을 위한 빈 JLabel

                DecimalFormat formatter = new DecimalFormat("#,###,###,###");
                String rentalFeeFormatted = formatter.format(Integer.parseInt(carInfo[1]));
                String insuranceFeeFormatted = formatter.format(Integer.parseInt(carInfo[2]));
                
                gbc.gridx++;
                gbc.anchor = GridBagConstraints.WEST;
                JLabel carLabel = new JLabel(
                    "<html>차량 이름: " + carInfo[0] + "<br>" +
                    "일일 대여료: " + rentalFeeFormatted + "<br>" +
                    "일일 보험료: " + insuranceFeeFormatted + "<br>" +
                    "PL: " + carInfo[3] + "<br>" +
                    "차량 번호: " + carInfo[4] + "</html>"
                );
                carLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
                carLabel.setForeground(new Color(255, 255, 255));
                carPanel.add(carLabel, gbc);

                gbc.gridx = 0;
                gbc.gridy++;
                
                
                Map<JButton, Boolean> reservationStatusMap = new HashMap<>(); 

             // 자동차 선택 액션 리스너
             carButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     int option = JOptionPane.showConfirmDialog(null, "해당 차량으로 선택하시겠습니까?", "선택 확인", JOptionPane.YES_NO_OPTION);
                     if (option == JOptionPane.YES_OPTION) {
                         // '확인' 버튼을 클릭한 경우 -> 결제 화면으로 연결
                         selectedCar = carInfo[4];  // 선택한 차량 이름 저장
                         rentalFee = Integer.parseInt(carInfo[1]);  // 일일 대여료 저장
                         insuranceFee = Integer.parseInt(carInfo[2]);  // 일일 보험료 저장

                         // 중복 예약 확인
                         try {
                             boolean isAvailable = CarRentDAO.getCarRentDAO().isReservationAvailable(selectedCar);

                             if (isAvailable) {
                                 // 예약 가능한 상태인 경우 결제 화면으로 이동
                                 Pay pay;
                                 try {
                                     pay = new Pay(Choicecar.this, selectedCar, rentalFee, insuranceFee, name);
                                     JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Choicecar.this);
                                     frame.getContentPane().removeAll();
                                     frame.getContentPane().add(pay);
                                     frame.revalidate();
                                     frame.repaint();
                                 } catch (SQLException e1) {
                                     e1.printStackTrace();
                                 }
                             } else {
                                 // 이미 예약 중인 상태인 경우 메시지 출력
                                 JOptionPane.showMessageDialog(null, "이미 예약된 차량입니다.");
                                 // 예약 상태 텍스트 표시
                                 if (!isAvailable) {
                                	 // 이미 예약 중인 상태인 경우
                                	 carButton.setText("예약 중");
                                	 carButton.setForeground(Color.RED); // 텍스트 색상 설정
                                	 carButton.setFont(new Font("맑은 고딕", Font.BOLD, 14)); // 텍스트 폰트 설정
                                 }
                             }

                             // 버튼 크기 조정
                             Dimension preferredSize = carButton.getPreferredSize();
                             preferredSize.height += 25; // 세로 크기 조정
                             carButton.setPreferredSize(preferredSize);

                             // reservationStatusMap 업데이트
                             reservationStatusMap.put(carButton, isAvailable);
                         } catch (SQLException e1) {
                             e1.printStackTrace();
                         }
                     }
                 }
             });

            }
        }

        add(tabbedPane, BorderLayout.CENTER);
    }
    
}

