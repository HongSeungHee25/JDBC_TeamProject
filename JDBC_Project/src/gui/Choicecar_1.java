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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//차량 등급별 종류 선택 - 1번째 
@SuppressWarnings("serial")
public class Choicecar_1 extends JPanel {

 public static void main(String[] args) {
     EventQueue.invokeLater(new Runnable() {
         public void run() {
             try {
                 JFrame frame = new JFrame();
                 frame.setTitle("렌트가 예약 프로그램");
                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 frame.setSize(900, 800);
                 frame.setLocationRelativeTo(null);

                 Choicecar_1 choicecar1 = new Choicecar_1();
                 frame.getContentPane().add(choicecar1);
                 
                 frame.setVisible(true);
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
     });
 }

 public Choicecar_1() {
     initialize();
 }

 private void initialize() {
     setLayout(null);
     
     JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
     tabbedPane.setBounds(0, 0, 900, 800);
     add(tabbedPane);
		
		JPanel light_car_panel = new JPanel();
		light_car_panel.setLayout(null);
		tabbedPane.addTab("경차", null, light_car_panel, null);
		
		JLabel light_car_lblNewLabel = new JLabel("경차");
		light_car_lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		light_car_lblNewLabel.setBounds(400, 35, 80, 65);
		light_car_panel.add(light_car_lblNewLabel);
		
		
		String morning_name = "차량 : 모닝";
		String morning_pl = "연료 타입 : 휘발유";
		String morning_price = "1일 대여료 : 35,200원";
		String morning_bo = "1일 보험료 : 18,560원";
				
		JLabel morning_lblNewLabel = new JLabel("<html>" + morning_name+ " <br>" + morning_pl + " <br>" + morning_price + " <br>" + morning_bo + "<html>");
		morning_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		morning_lblNewLabel.setBounds(120, 280, 240, 70);
		light_car_panel.add(morning_lblNewLabel);
		
		String ray_name = "차량 : 레이";
		String ray_pl = "연료 타입 : 경유";
		String ray_price = "1일 대여료 : 34,850원";
		String ray_bo = "1일 보험료 : 18,560원";
		
		JLabel ray_lblNewLabel = new JLabel("<html>" + ray_name+ " <br>" + ray_pl + " <br>" + ray_price + " <br>" + ray_bo + "<html>");
		ray_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		ray_lblNewLabel.setBounds(500, 280, 240, 70);
		light_car_panel.add(ray_lblNewLabel);
		
		String spark_name = "차량 : 스파크";
		String spark_pl = "연료 타입 : 휘발유";
		String spark_price = "1일 대여료 : 28,000원";
		String spark_bo = "1일 보험료 : 18,560원";
		
		JLabel spark_lblNewLabel = new JLabel("<html>" + spark_name+ " <br>" + spark_pl + " <br>" + spark_price + " <br>" + spark_bo + "<html>");
		spark_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		spark_lblNewLabel.setBounds(120, 550, 240, 70);
		light_car_panel.add(spark_lblNewLabel);
		
		String Casper_name = "차량 : 캐스퍼";
		String Casper_pl = "연료 타입 : 휘발유";
		String Casper_price = "1일 대여료 : 44,200원";
		String Casper_bo = "1일 보험료 : 22,700원";
		
		JLabel Casper_lblNewLabel = new JLabel("<html>" + Casper_name+ " <br>" + Casper_pl + " <br>" + Casper_price + " <br>" + Casper_bo + "<html>");
		Casper_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		Casper_lblNewLabel.setBounds(500, 550, 240, 70);
		light_car_panel.add(Casper_lblNewLabel);
		
		JButton monibg_btnNewButton = new JButton("차량");
		monibg_btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		monibg_btnNewButton.setIcon(new ImageIcon("./image/2023 모닝.jpg"));
		monibg_btnNewButton.setBounds(120, 130, 250, 150);
		light_car_panel.add(monibg_btnNewButton);
		
		JButton spark_btnNewButton = new JButton("차량");
		spark_btnNewButton.setIcon(new ImageIcon("./image/2022 스파크.jpg"));
		spark_btnNewButton.setBounds(120, 400, 250, 150);
		light_car_panel.add(spark_btnNewButton);
		
		JButton ray_btnNewButton = new JButton("차량");
		ray_btnNewButton.setIcon(new ImageIcon("./image/2023 레이.jpg"));
		ray_btnNewButton.setBounds(500, 130, 250, 150);
		light_car_panel.add(ray_btnNewButton);
		
		JButton Casper_btnNewButton = new JButton("차량");
		Casper_btnNewButton.setIcon(new ImageIcon("./image/2023 캐스퍼.jpg"));
		Casper_btnNewButton.setBounds(500, 400, 250, 150);
		light_car_panel.add(Casper_btnNewButton);
		
		JButton light_car_btnNewButton = new JButton("확정");
		light_car_btnNewButton.setFont(new Font("굴림체", Font.BOLD, 16));
		light_car_btnNewButton.setBounds(360, 640, 130, 45);
		light_car_panel.add(light_car_btnNewButton);
		
		JCheckBox monibg_CheckBox = new JCheckBox("선택");
		monibg_CheckBox.setBounds(200, 360, 115, 23);
		light_car_panel.add(monibg_CheckBox);
		
		JCheckBox ray_CheckBox = new JCheckBox("선택");
		ray_CheckBox.setBounds(580, 360, 115, 23);
		light_car_panel.add(ray_CheckBox);
		
		JCheckBox spark_CheckBox = new JCheckBox("선택");
		spark_CheckBox.setBounds(200, 635, 115, 23);
		light_car_panel.add(spark_CheckBox);
		
		JCheckBox Casper_CheckBox = new JCheckBox("선택");
		Casper_CheckBox.setBounds(580, 635, 115, 23);
		light_car_panel.add(Casper_CheckBox);
		
		JPanel small_type_panel = new JPanel();
		small_type_panel.setLayout(null);
		tabbedPane.addTab("소형", null, small_type_panel, null);
		
		JLabel small_type_lblNewLabel = new JLabel("소형차");
		small_type_lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		small_type_lblNewLabel.setBounds(400, 35, 80, 65);
		small_type_panel.add(small_type_lblNewLabel);
		
		String kona_name = "차량 : 코나";
		String kona_pl = "연료 타입 : 휘발유";
		String kona_price = "1일 대여료 : 49,400원";
		String kona_bo = "1일 보험료 : 22,700원";
		
		JLabel kona_lblNewLabel = new JLabel("<html>" + kona_name+ " <br>" + kona_pl + " <br>" + kona_price + " <br>" + kona_bo + "<html>");
		kona_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		kona_lblNewLabel.setBounds(120, 280, 240, 70);
		small_type_panel.add(kona_lblNewLabel);
		
		String Seltos_name = "차량 : 셀토스";
		String Seltos_pl = "연료 타입 : 경유";
		String Seltos_price = "1일 대여료 : 52,250원";
		String Seltos_bo = "1일 보험료 : 22,700원";
		
		JLabel Seltos_lblNewLabel = new JLabel("<html>" + Seltos_name+ " <br>" + Seltos_pl + " <br>" + Seltos_price + " <br>" + Seltos_bo + "<html>");
		Seltos_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		Seltos_lblNewLabel.setBounds(500, 280, 240, 70);
		small_type_panel.add(Seltos_lblNewLabel);
		
		String K3_name = "차량 : K3";
		String K3_pl = "연료 타입 : 경유";
		String K3_price = "1일 대여료 : 36,550원";
		String K3_bo = "1일 보험료 : 21,800원";
		
		JLabel K3_lblNewLabel = new JLabel("<html>" + K3_name+ " <br>" + K3_pl + " <br>" + K3_price + " <br>" + K3_bo + "<html>");
		K3_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		K3_lblNewLabel.setBounds(120, 550, 240, 70);
		small_type_panel.add(K3_lblNewLabel);
		
		String Tivoli_name = "차량 : 티볼리";
		String Tivoli_pl = "연료 타입 : 경유";
		String Tivoli_price = "1일 대여료 : 44,640원";
		String Tivoli_bo = "1일 보험료 : 19,670원";
		
		JLabel Tivoli_lblNewLabel = new JLabel("<html>" + Tivoli_name+ " <br>" + Tivoli_pl + " <br>" + Tivoli_price + " <br>" + Tivoli_bo + "<html>");
		Tivoli_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		Tivoli_lblNewLabel.setBounds(500, 550, 240, 70);
		small_type_panel.add(Tivoli_lblNewLabel);
		
		JButton kona_btnNewButton = new JButton("차량");
		kona_btnNewButton.setIcon(new ImageIcon("./image/2023 코나.jpg"));
		kona_btnNewButton.setBounds(120, 130, 250, 150);
		small_type_panel.add(kona_btnNewButton);
		
		JButton K3_btnNewButton = new JButton("차량");
		K3_btnNewButton.setIcon(new ImageIcon("./image/2023 K3.jpg"));
		K3_btnNewButton.setBounds(120, 400, 250, 150);
		small_type_panel.add(K3_btnNewButton);
		
		JButton Seltos_btnNewButton = new JButton("차량");
		Seltos_btnNewButton.setIcon(new ImageIcon("./image/2023 셀토스.jpg"));
		Seltos_btnNewButton.setBounds(500, 130, 250, 150);
		small_type_panel.add(Seltos_btnNewButton);
		
		JButton Tivoli_btnNewButton = new JButton("차량");
		Tivoli_btnNewButton.setIcon(new ImageIcon("./image/2023 티볼리에어.jpg"));
		Tivoli_btnNewButton.setBounds(500, 400, 250, 150);
		small_type_panel.add(Tivoli_btnNewButton);
		
		JButton small_type_btnNewButton = new JButton("확정");
		small_type_btnNewButton.setFont(new Font("굴림체", Font.BOLD, 16));
		small_type_btnNewButton.setBounds(360, 640, 130, 45);
		small_type_panel.add(small_type_btnNewButton);
		
		JCheckBox kona_CheckBox = new JCheckBox("선택");
		kona_CheckBox.setBounds(200, 360, 115, 23);
		small_type_panel.add(kona_CheckBox);
		
		JCheckBox Seltos_CheckBox = new JCheckBox("선택");
		Seltos_CheckBox.setBounds(580, 360, 115, 23);
		small_type_panel.add(Seltos_CheckBox);
		
		JCheckBox K3_CheckBox = new JCheckBox("선택");
		K3_CheckBox.setBounds(200, 635, 115, 23);
		small_type_panel.add(K3_CheckBox);
		
		JCheckBox Tivoli_CheckBox = new JCheckBox("선택");
		Tivoli_CheckBox.setBounds(580, 635, 115, 23);
		small_type_panel.add(Tivoli_CheckBox);
		
		JPanel medium_panel = new JPanel();
		medium_panel.setLayout(null);
		tabbedPane.addTab("중형", null, medium_panel, null);
		
		JLabel medium_lblNewLabel = new JLabel("중형차");
		medium_lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		medium_lblNewLabel.setBounds(400, 35, 80, 65);
		medium_panel.add(medium_lblNewLabel);
		
		String sonata_name = "차량 : 소나타";
		String sonata_pl = "연료 타입 : 휘발유";
		String sonata_price = "1일 대여료 : 53,240원";
		String sonata_bo = "1일 보험료 : 26,980원";
		
		JLabel sonata_lblNewLabel = new JLabel("<html>" + sonata_name+ " <br>" + sonata_pl + " <br>" + sonata_price + " <br>" + sonata_bo + "<html>");
		sonata_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		sonata_lblNewLabel.setBounds(120, 280, 240, 70);
		medium_panel.add(sonata_lblNewLabel);
		
		String K5_name = "차량 : K5";
		String K5_pl = "연료 타입 : 휘발유";
		String K5_price = "1일 대여료 : 49,400원";
		String K5_bo = "1일 보험료 : 24,280원";
		
		JLabel K5_lblNewLabel = new JLabel("<html>" + K5_name+ " <br>" + K5_pl + " <br>" + K5_price + " <br>" + K5_bo + "<html>");
		K5_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		K5_lblNewLabel.setBounds(505, 286, 242, 81);
		medium_panel.add(K5_lblNewLabel);
		
		String Sportage_name = "차량 : 스포티지";
		String Sportage_pl = "연료 타입 : 경유";
		String Sportage_price = "1일 대여료 : 65,000원";
		String Sportage_bo = "1일 보험료 : 24,280원";
		
		JLabel Sportage_lblNewLabel = new JLabel("<html>" + Sportage_name+ " <br>" + Sportage_pl + " <br>" + Sportage_price + " <br>" + Sportage_bo + "<html>");
		Sportage_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		Sportage_lblNewLabel.setBounds(120, 550, 240, 70);
		medium_panel.add(Sportage_lblNewLabel);
		
		String Tucson_name = "차량 : 투싼";
		String Tucson_pl = "연료 타입 : 경유";
		String Tucson_price = "1일 대여료 : 67,000원";
		String Tucson_bo = "1일 보험료 : 28,650원";
		
		JLabel Tucson_lblNewLabel = new JLabel("<html>" + Tucson_name+ " <br>" + Tucson_pl + " <br>" + Tucson_price + " <br>" + Tucson_bo + "<html>");
		Tucson_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		Tucson_lblNewLabel.setBounds(500, 550, 240, 70);
		medium_panel.add(Tucson_lblNewLabel);
		
		
		JButton sonata_btnNewButton = new JButton("차량");
		sonata_btnNewButton.setIcon(new ImageIcon("./image/2023 소나타.jpg"));
		sonata_btnNewButton.setBounds(120,130,250,150);
		medium_panel.add(sonata_btnNewButton);
		
		JButton Sportage_btnNewButton = new JButton("차량");
		Sportage_btnNewButton.setIcon(new ImageIcon("./image/2023 스포티지.jpg"));
		Sportage_btnNewButton.setBounds(120,400,250,150);
		medium_panel.add(Sportage_btnNewButton);
		
		JButton K5_btnNewButton = new JButton("차량");
		K5_btnNewButton.setIcon(new ImageIcon("./image/2023 K5.jpg"));
		K5_btnNewButton.setBounds(500,130,250,150);
		medium_panel.add(K5_btnNewButton);
		
		JButton Tucson_btnNewButton = new JButton("차량");
		Tucson_btnNewButton.setIcon(new ImageIcon("./image/2023 투싼.jpg"));
		Tucson_btnNewButton.setBounds(500,400,250,150);
		medium_panel.add(Tucson_btnNewButton);
		
		JButton medium_btnNewButton = new JButton("확정");
		medium_btnNewButton.setFont(new Font("굴림체", Font.BOLD, 16));
		medium_btnNewButton.setBounds(360, 640, 130, 45);
		medium_panel.add(medium_btnNewButton);
		
		JCheckBox sonata_CheckBox = new JCheckBox("선택");
		sonata_CheckBox.setBounds(200, 360, 115, 23);
		medium_panel.add(sonata_CheckBox);
		
		JCheckBox K5_CheckBox = new JCheckBox("선택");
		K5_CheckBox.setBounds(565, 373, 115, 23);
		medium_panel.add(K5_CheckBox);
		
		JCheckBox Sportage_CheckBox = new JCheckBox("선택");
		Sportage_CheckBox.setBounds(200, 635, 115, 23);
		medium_panel.add(Sportage_CheckBox);
		
		JCheckBox Tucson_CheckBox = new JCheckBox("선택");
		Tucson_CheckBox.setBounds(580, 635, 115, 23);
		medium_panel.add(Tucson_CheckBox);
		
		JPanel large_panel = new JPanel();
		large_panel.setLayout(null);
		tabbedPane.addTab("대형", null, large_panel, null);
		
		JLabel large_lblNewLabel = new JLabel("대형차");
		large_lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		large_lblNewLabel.setBounds(400, 35, 80, 65);
		large_panel.add(large_lblNewLabel);
		
		String Carnival_name = "차량 : 카니발";
		String Carnival_pl = "연료 타입 : 휘발유";
		String Carnival_price = "1일 대여료 : 85,050원";
		String Carnival_bo = "1일 보험료 : 25,570원";
	
		
		JLabel Carnival_lblNewLabel = new JLabel("<html>" + Carnival_name+ " <br>" + Carnival_pl + " <br>" + Carnival_price + " <br>" + Carnival_bo + "<html>");
		Carnival_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		Carnival_lblNewLabel.setBounds(120, 400, 240, 70);
		large_panel.add(Carnival_lblNewLabel);
		
		String Staria_name = "차량 : 스타리아";
		String Staria_pl = "연료 타입 : 경유";
		String Staria_price = "1일 대여료 : 81,460원";
		String Staria_bo = "1일 보험료 : 24,760원";
		
		JLabel Staria_lblNewLabel = new JLabel("<html>" + Staria_name+ " <br>" + Staria_pl + " <br>" + Staria_price + " <br>" + Staria_bo + "<html>");
		Staria_lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		Staria_lblNewLabel.setBounds(500, 400, 240, 70);
		large_panel.add(Staria_lblNewLabel);
		
		JButton Carnival_btnNewButton = new JButton("차량");
		Carnival_btnNewButton.setIcon(new ImageIcon("./image/2023 카니발.jpg"));
		Carnival_btnNewButton.setBounds(120, 250, 250, 150);
		large_panel.add(Carnival_btnNewButton);
		
		JButton Staria_btnNewButton = new JButton("차량");
		Staria_btnNewButton.setIcon(new ImageIcon("./image/2023 스타리아.jpg"));
		Staria_btnNewButton.setBounds(500, 250, 250, 150);
		large_panel.add(Staria_btnNewButton);
		
		JButton large_btnNewButton = new JButton("확정");
		large_btnNewButton.setFont(new Font("굴림체", Font.BOLD, 16));
		large_btnNewButton.setBounds(360, 640, 130, 40);
		large_panel.add(large_btnNewButton);
		
		JCheckBox Carnival_CheckBox = new JCheckBox("선택");
		Carnival_CheckBox.setBounds(200, 485, 115, 23);
		large_panel.add(Carnival_CheckBox);
		
		JCheckBox Staria_CheckBox = new JCheckBox("선택");
		Staria_CheckBox.setBounds(580, 485, 115, 23);
		large_panel.add(Staria_CheckBox);
		
		
	}
}