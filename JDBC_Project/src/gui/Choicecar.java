package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;


//차량 등급별 종류 선택 - for 문
@SuppressWarnings("serial")
public class Choicecar extends JPanel {
	//화면출력 데이터를 배열에 저장 
    private String[] carTypes = { "경차", "소형", "중형", "대형" };
    private String[][] carNames = {
            { "모닝", "레이", "스파크", "캐스퍼" },
            { "코나", "셀토스", "K3", "티볼리" },
            { "소나타", "K5", "스포티지", "투싼" },
            { "카니발", "스타리아" }
    };
    private String[][] fuelTypes = {
            { "휘발유", "경유", "휘발유", "휘발유" },
            { "휘발유", "경유", "경유", "경유" },
            { "휘발유", "휘발유", "경유", "경유" },
            { "휘발유", "경유" }
    };
    private String[][] rentalPrices = {
            { "35,200원", "34,850원", "28,000원", "44,200원" },
            { "49,400원", "52,250원", "36,550원", "44,640원" },
            { "53,240원", "49,400원", "65,000원", "67,000원" },
            { "85,050원", "81,460원" }
    };
    private String[][] insurancePrices = {
            { "18,560원", "18,560원", "18,560원", "22,700원" },
            { "22,700원", "22,700원", "21,800원", "19,670원" },
            { "26,980원", "24,280원", "24,280원", "28,650원" },
            { "25,570원", "24,760원" }
    };

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    frame.setTitle("렌트가 예약 프로그램");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(900, 800);
                    frame.setLocationRelativeTo(null);

                    Choicecar choicecar1 = new Choicecar();
                    frame.getContentPane().add(choicecar1);

                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Choicecar() {
        initialize();
    }

    private void initialize() {
        setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 900, 800);
        add(tabbedPane);

        JPanel[] panels = new JPanel[carTypes.length];
        JLabel[][] labels = new JLabel[carTypes.length][carNames[0].length];
        JButton[][] buttons = new JButton[carTypes.length][carNames[0].length];
        JCheckBox[][] checkBoxes = new JCheckBox[carTypes.length][carNames[0].length];

        for (int i = 0; i < carTypes.length; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(null);
            tabbedPane.addTab(carTypes[i], null, panels[i], null);

            JLabel typeLabel = new JLabel(carTypes[i]);
            typeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
            typeLabel.setBounds(400, 35, 80, 65);
            panels[i].add(typeLabel);

            for (int j = 0; j < carNames[i].length; j++) {
                labels[i][j] = new JLabel("<html>" + "차량: " + carNames[i][j] + "<br>" + "연료 타입: " + fuelTypes[i][j] +
                        "<br>" + "1일 대여료: " + rentalPrices[i][j] + "<br>" + "1일 보험료: " + insurancePrices[i][j] + "<html>");
                labels[i][j].setVerticalAlignment(SwingConstants.TOP);
                labels[i][j].setBounds(120 + (j % 2) * 380, 280 + (j / 2) * 270, 240, 70);
                panels[i].add(labels[i][j]);

                buttons[i][j] = new JButton("차량");
                buttons[i][j].setIcon(new ImageIcon("./image/2023 " + carNames[i][j] + ".jpg"));
                buttons[i][j].setBounds(120 + (j % 2) * 380, 130 + (j / 2) * 270, 250, 150);
                panels[i].add(buttons[i][j]);

                checkBoxes[i][j] = new JCheckBox("선택");
                checkBoxes[i][j].setBounds(200 + (j % 2) * 380, 360 + (j / 2) * 270, 115, 23);
                panels[i].add(checkBoxes[i][j]);
            }

            JButton confirmButton = new JButton("확정");
            confirmButton.setFont(new Font("굴림체", Font.BOLD, 16));
            confirmButton.setBounds(360, 640, 130, 40);
            panels[i].add(confirmButton);
        }
    

  
    
}
}
