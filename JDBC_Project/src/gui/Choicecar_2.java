package gui;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//차량 등급별 종류 선택 - 2번째 
@SuppressWarnings("serial")
public class Choicecar_2 extends JPanel {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    frame.setTitle("렌트카 예약 프로그램");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(900, 800);
                    frame.setLocationRelativeTo(null);

                    Choicecar_2 choicecar1 = new Choicecar_2();
                    frame.getContentPane().add(choicecar1);

                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Choicecar_2() {
        initialize();
    }

    public void initialize() {
        setLayout(null);

        // 탭 객체 생성 및 위치 지정
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 10, 870, 740);
        add(tabbedPane);

        // 그리드 레이아웃 객체 생성 및 위치 지정
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.insets.top = 5;
        c.insets.bottom = 5;
        c.insets.left = 100;
        c.insets.right = 100;

        // 차량 종류 배열
        String[] carTypes = { "경차", "소형", "중형", "대형" };

        // 차량 이미지 파일명 배열
        String[][] carImages = { 
            { "./image/2023 Ray.jpg", "./image/2023 Spark.jpg", "./image/2023 Casper.jpg", "./image/2023 Morning.jpg", "./image/Add_image.jpg" },
            { "./image/2023 Kona.jpg", "./image/2023 Seltos.jpg", "./image/2023 K3.jpg", "./image/2023 Tivoli air.jpg", "./image/Add_image.jpg" },
            { "./image/2023 Sonata.jpg", "./image/2023 K5.jpg", "./image/2023 Sportage.jpg", "./image/2023 Tucson.jpg", "./image/Add_image.jpg" },
            { "./image/2023 Carnival.jpg", "./image/2023 Staria.jpg", "./image/Add_image.jpg","./image/Add_image.jpg"}
        };

        // 차량 이름 배열
        String[][] carNames = { 
            { "레이", "스파크", "캐스퍼", "모닝", "준비중.."},
            { "코나", "셀토스", "K3", "티볼리에어", "준비중.."},
            { "소나타", "K5", "스포티지", "투싼", "준비중.."},
            { "카니발", "스타리아", "준비중..","준비중.."}
        };

        // 차량 패널 배열
        JPanel[] carPanels = new JPanel[carTypes.length];

        for (int i = 0; i < carTypes.length; i++) {
            // 차량 탭 패널 생성
            carPanels[i] = new JPanel();
            carPanels[i].setLayout(gridBagLayout);

            // 차량 탭 패널에 스크롤 패널 생성
            JScrollPane carScrollPane = new JScrollPane(carPanels[i]);
            carScrollPane.setBounds(0, 0, 900, 800);

            // 빈 테두리 설정(상, 하, 좌, 우)
            carScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

            // 탭에 스크롤 패널 추가
            tabbedPane.addTab(carTypes[i], null, carScrollPane, null);

            // 차량 탭 패널 이름 및 크기와 위치 지정
            JLabel carTypeLabel = new JLabel(carTypes[i]);
            carTypeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
            carTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            carScrollPane.setColumnHeaderView(carTypeLabel);

            // 스크롤 패널에 차량 패널 추가
            carScrollPane.setViewportView(carPanels[i]);

            for (int j = 0; j < carImages[i].length; j++) {
                final int carIndex = j;

                // 차량 이미지 및 버튼 생성
                JButton carButton = new JButton();
                ImageIcon carImageIcon = new ImageIcon(carImages[i][j]);
                Image carImage = carImageIcon.getImage();
                Dimension carButtonSize = new Dimension(250, 170);
                carButton.setIcon(new ImageIcon(
                        carImage.getScaledInstance(carButtonSize.width, carButtonSize.height, Image.SCALE_SMOOTH)));
                carButton.setPreferredSize(carButtonSize);
                c.gridx = 0;
                c.gridy = j;
                c.anchor = (j % 2 == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
                carPanels[i].add(carButton, c);

                //자동차 선택 액션 리스너 
                carButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int option = JOptionPane.showConfirmDialog(null, "해당 차량으로 선택하시겠습니까?", "선택 확인", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                        } 
                    }
                });

                
                // 차량 설명 생성
                JLabel carLabel = new JLabel(carNames[i][j]);
                c.gridx = 1;
                c.gridy = j;
               c.anchor = GridBagConstraints.CENTER;
                carPanels[i].add(carLabel, c);
                carLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
            }
        }
    }
}