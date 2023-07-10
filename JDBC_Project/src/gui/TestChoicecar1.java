package gui;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

//자동차 선택 화면 구현 - 종화, 지수, 진만
public class TestChoicecar1 extends JPanel {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setTitle("렌트카 예약 프로그램");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(900, 800);
					frame.setLocationRelativeTo(null);

					TestChoicecar1 choicecar1 = new TestChoicecar1();
					frame.getContentPane().add(choicecar1);

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestChoicecar1() {
		initialize();
	}

	// 패널 이동용
	private JPanel light_car_panel = new JPanel(); // 패널 초기화
	private JPanel small_car_panel = new JPanel();
	private JPanel middle_car_panel = new JPanel();
	private JPanel large_car_panel = new JPanel();

	public void initialize() {
		setLayout(null);

		// 탭 객체 생성 및 위치 지정
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 900, 800);
		add(tabbedPane);

		// 그리드 레이아웃 객체 생성 및 위치 지정
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets.top = 5;
		c.insets.bottom = 5;
		c.insets.left = 100;
		c.insets.right = 100;

		// 경차 탭 패널 생성
		light_car_panel.setLayout(gridBagLayout);

		// 경차 탭 패널에 스크롤 패널 생성
		JScrollPane light_car_scrollPane = new JScrollPane(light_car_panel);
		light_car_scrollPane.setBounds(0, 0, 900, 800);

		// 빈 테두리 설정(상, 하, 좌, 우)
		light_car_scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

		// 탭에 스크롤 패널 추가
		tabbedPane.addTab("경차", null, light_car_scrollPane, null);

		// 경차 탭 패널 이름 및 크기와 위치 지정
		JLabel light_car_lblNewLabel = new JLabel("경차");
		light_car_lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		light_car_lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		light_car_scrollPane.setColumnHeaderView(light_car_lblNewLabel);

		// 스크롤 패널에 경차 패널 추가
		light_car_scrollPane.setViewportView(light_car_panel);

		// 레이 이미지 및 버튼 0열, 0행
		JButton Ray_button = new JButton();
		ImageIcon Ray_imageIcon = new ImageIcon("./image/2023 Ray.jpg");
		Image Ray_image = Ray_imageIcon.getImage();
		Dimension Ray_buttonSize = new Dimension(250, 200);
		Ray_button.setIcon(new ImageIcon(
				Ray_image.getScaledInstance(Ray_buttonSize.width, Ray_buttonSize.height, Image.SCALE_SMOOTH)));
		Ray_button.setPreferredSize(Ray_buttonSize);
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		light_car_panel.add(Ray_button, c);

		Ray_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// 레이 설명 1열, 0행
		JLabel Ray_label = new JLabel("레이");
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		light_car_panel.add(Ray_label, c);
		Ray_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// 스파크 이미지 및 버튼 0열, 1행
		JButton Spark_button = new JButton();
		ImageIcon Spark_imageIcon = new ImageIcon("./image/2022 Spark.jpg");
		Image Spark_image = Spark_imageIcon.getImage();
		Dimension Spark_buttonSize = new Dimension(250, 200);
		Spark_button.setIcon(new ImageIcon(
				Spark_image.getScaledInstance(Spark_buttonSize.width, Spark_buttonSize.height, Image.SCALE_SMOOTH)));
		Spark_button.setPreferredSize(Spark_buttonSize);
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		light_car_panel.add(Spark_button, c);

		Spark_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// 스파크 설명 1열, 1행
		JLabel Spark_label = new JLabel("스파크");
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		light_car_panel.add(Spark_label, c);
		Spark_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// 캐스퍼 이미지 및 버튼 0열, 2행
		JButton Casper_button = new JButton();
		ImageIcon Casper_imageIcon = new ImageIcon("./image/2023 Casper.jpg");
		Image Casper_image = Casper_imageIcon.getImage();
		Dimension Casper_buttonSize = new Dimension(250, 200);
		Casper_button.setIcon(new ImageIcon(
				Casper_image.getScaledInstance(Casper_buttonSize.width, Casper_buttonSize.height, Image.SCALE_SMOOTH)));
		Casper_button.setPreferredSize(Casper_buttonSize);
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		light_car_panel.add(Casper_button, c);

		Casper_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// 캐스퍼 설명 1열, 2행
		JLabel Casper_label = new JLabel("캐스퍼");
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		light_car_panel.add(Casper_label, c);
		Casper_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// 모닝 이미지 및 버튼 0열, 3행
		JButton Morning_button = new JButton();
		ImageIcon Morning_imageIcon = new ImageIcon("./image/2023 Morning.jpg");
		Image Morning_image = Morning_imageIcon.getImage();
		Dimension Morning_buttonSize = new Dimension(250, 200);
		Morning_button.setIcon(new ImageIcon(Morning_image.getScaledInstance(Morning_buttonSize.width,
				Morning_buttonSize.height, Image.SCALE_SMOOTH)));
		Morning_button.setPreferredSize(Morning_buttonSize);
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		light_car_panel.add(Morning_button, c);

		Morning_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// 모닝 설명 1열, 3행
		JLabel Morning_label = new JLabel("모닝");
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		light_car_panel.add(Morning_label, c);
		Morning_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// 소형차 탭 패널 생성
		small_car_panel.setLayout(gridBagLayout);

		// 소형차 탭 패널에 스크롤 패널 생성
		JScrollPane small_car_scrollPane = new JScrollPane();
		small_car_scrollPane.setBounds(0, 0, 900, 800);

		// 빈 테두리 설정(상, 하, 좌, 우)
		small_car_scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

		// 탭에 스크롤 패널 추가
		tabbedPane.addTab("소형", null, small_car_scrollPane, null);

		// 소형차 탭 패널 이름 및 크기와 위치 지정
		JLabel small_type_lblNewLabel = new JLabel("소형차");
		small_type_lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		small_type_lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		small_car_scrollPane.setColumnHeaderView(small_type_lblNewLabel);

		// 스크롤 패널에 소형차 패널 추가
		small_car_scrollPane.setViewportView(small_car_panel);

		// 코나 이미지 및 버튼 0열, 0행
		JButton Kona_Button = new JButton();
		ImageIcon Kona_imageIcon = new ImageIcon("./image/2023 Kona.jpg");
		Image Kona_image = Kona_imageIcon.getImage();
		Dimension Kona_buttonSize = new Dimension(250, 200);
		Kona_Button.setIcon(new ImageIcon(
				Kona_image.getScaledInstance(Kona_buttonSize.width, Kona_buttonSize.height, Image.SCALE_SMOOTH)));
		Kona_Button.setPreferredSize(Kona_buttonSize);
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		small_car_panel.add(Kona_Button, c);

		Kona_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// 코나 설명 1열, 0행
		JLabel Kona_label = new JLabel("코나");
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		small_car_panel.add(Kona_label, c);
		Kona_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// 셀토스 이미지 및 버튼 0열, 1행
		JButton Seltos_button = new JButton();
		ImageIcon Seltos_imageIcon = new ImageIcon("./image/2023 Seltos.jpg");
		Image Seltos_image = Seltos_imageIcon.getImage();
		Dimension Seltos_buttonSize = new Dimension(250, 200);
		Seltos_button.setIcon(new ImageIcon(
				Seltos_image.getScaledInstance(Seltos_buttonSize.width, Seltos_buttonSize.height, Image.SCALE_SMOOTH)));
		Seltos_button.setPreferredSize(Seltos_buttonSize);
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		small_car_panel.add(Seltos_button, c);

		Seltos_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// 셀토스 설명 1열, 1행
		JLabel Seltos_label = new JLabel("셀토스");
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		small_car_panel.add(Seltos_label, c);
		Seltos_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// K3 이미지 및 버튼 0열, 2행
		JButton K3_button = new JButton();
		ImageIcon K3_imageIcon = new ImageIcon("./image/2023 K3.jpg");
		Image K3_image = K3_imageIcon.getImage();
		Dimension K3_buttonSize = new Dimension(250, 200);
		K3_button.setIcon(new ImageIcon(
				K3_image.getScaledInstance(K3_buttonSize.width, K3_buttonSize.height, Image.SCALE_SMOOTH)));
		K3_button.setPreferredSize(K3_buttonSize);
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		small_car_panel.add(K3_button, c);

		K3_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// K3 설명 1열, 2행
		JLabel K3_label = new JLabel("K3");
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		small_car_panel.add(K3_label, c);
		K3_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// 티볼리에어 이미지 및 버튼 0열, 3행
		JButton Tivoli_button = new JButton();
		ImageIcon Tivoli_imageIcon = new ImageIcon("./image/2023 Tivoli air.jpg");
		Image Tivoli_image = Tivoli_imageIcon.getImage();
		Dimension Tivoli_buttonSize = new Dimension(250, 200);
		Tivoli_button.setIcon(new ImageIcon(
				Tivoli_image.getScaledInstance(Tivoli_buttonSize.width, Tivoli_buttonSize.height, Image.SCALE_SMOOTH)));
		Tivoli_button.setPreferredSize(Tivoli_buttonSize);
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		small_car_panel.add(Tivoli_button, c);

		Tivoli_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// 티볼리에어 설명 1열, 3행
		JLabel Tivoli_label = new JLabel("티볼리에어");
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		small_car_panel.add(Tivoli_label, c);
		Tivoli_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// 중형차 탭 패널 생성
		middle_car_panel.setLayout(gridBagLayout);

		// 중형차 탭 패널에 스크롤 패널 생성
		JScrollPane middle_car_scrollPane = new JScrollPane(middle_car_panel);
		middle_car_scrollPane.setBounds(0, 0, 900, 800);

		// 빈 테두리 설정(상, 하, 좌, 우)
		middle_car_scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

		// 탭에 스크롤 패널 추가
		tabbedPane.addTab("중형", null, middle_car_scrollPane, null);

		// 중형차 탭 패널 이름 및 크기와 위치 지정
		JLabel middle_type_lblNewLabel = new JLabel("중형차");
		middle_type_lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		middle_type_lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		middle_car_scrollPane.setColumnHeaderView(middle_type_lblNewLabel);

		// 스크롤 패널에 경차 패널 추가
		middle_car_scrollPane.setViewportView(middle_car_panel);

		// 소나타 이미지 및 버튼 0열, 0행
		JButton Sonata_button = new JButton();
		ImageIcon Sonata_imageIcon = new ImageIcon("./image/2023 Sonata.jpg");
		Image Sonata_image = Sonata_imageIcon.getImage();
		Dimension Sonata_buttonSize = new Dimension(250, 200);
		Sonata_button.setIcon(new ImageIcon(
				Sonata_image.getScaledInstance(Sonata_buttonSize.width, Sonata_buttonSize.height, Image.SCALE_SMOOTH)));
		Sonata_button.setPreferredSize(Sonata_buttonSize);
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		middle_car_panel.add(Sonata_button, c);

		Sonata_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// 소나타 설명 1열, 0행
		JLabel Sonata_label = new JLabel("소나타");
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		middle_car_panel.add(Sonata_label, c);
		Sonata_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// K5 이미지 및 버튼 0열, 1행
		JButton K5_button = new JButton();
		ImageIcon K5_imageIcon = new ImageIcon("./image/2023 K5.jpg");
		Image K5_image = K5_imageIcon.getImage();
		Dimension K5_buttonSize = new Dimension(250, 200);
		K5_button.setIcon(new ImageIcon(
				K5_image.getScaledInstance(K5_buttonSize.width, K5_buttonSize.height, Image.SCALE_SMOOTH)));
		K5_button.setPreferredSize(K5_buttonSize);
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		middle_car_panel.add(K5_button, c);

		K5_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// K5 설명 1열, 1행
		JLabel K5_label = new JLabel("K5");
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		middle_car_panel.add(K5_label, c);
		K5_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// 스포티지 이미지 및 버튼 0열, 2행
		JButton Sportage_button = new JButton();
		ImageIcon Sportage_imageIcon = new ImageIcon("./image/2023 Sportage.jpg");
		Image Sportage_image = Sportage_imageIcon.getImage();
		Dimension Sportage_buttonSize = new Dimension(250, 200);
		Sportage_button.setIcon(new ImageIcon(Sportage_image.getScaledInstance(Sportage_buttonSize.width,
				Sportage_buttonSize.height, Image.SCALE_SMOOTH)));
		Sportage_button.setPreferredSize(Sportage_buttonSize);
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		middle_car_panel.add(Sportage_button, c);

		Sportage_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// 스포티지 설명 1열, 2행
		JLabel Sportage_label = new JLabel("스포티지");
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		middle_car_panel.add(Sportage_label, c);
		Sportage_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// 투싼 이미지 및 버튼 0열, 3행
		JButton Tucson_button = new JButton();
		ImageIcon Tucson_imageIcon = new ImageIcon("./image/2023 Tucson.jpg");
		Image Tucson_image = Tucson_imageIcon.getImage();
		Dimension Tucson_buttonSize = new Dimension(250, 200);
		Tucson_button.setIcon(new ImageIcon(
				Tucson_image.getScaledInstance(Tucson_buttonSize.width, Tucson_buttonSize.height, Image.SCALE_SMOOTH)));
		Tucson_button.setPreferredSize(Tucson_buttonSize);
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		middle_car_panel.add(Tucson_button, c);

		Tucson_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// 투싼 설명 1열, 3행
		JLabel Tucson_label = new JLabel("투싼");
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		middle_car_panel.add(Tucson_label, c);
		Tucson_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// 대형차 탭 패널 생성
		large_car_panel.setLayout(gridBagLayout);

		// 대형차 탭 패널에 스크롤 패널 생성
		JScrollPane large_car_scrollPane = new JScrollPane();
		large_car_scrollPane.setBounds(0, 0, 900, 800);

		// 빈 테두리 설정(상, 하, 좌, 우)
		large_car_scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

		// 탭에 스크롤 패널 추가
		tabbedPane.addTab("대형", null, large_car_scrollPane, null);

		// 대형차 탭 패널 이름 및 크기와 위치 지정
		JLabel large_type_lblNewLabel = new JLabel("대형차");
		large_type_lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		large_type_lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		large_car_scrollPane.setColumnHeaderView(large_type_lblNewLabel);

		// 스크롤 패널에 소형차 패널 추가
		large_car_scrollPane.setViewportView(large_car_panel);

		// 카니발 이미지 및 버튼 0열, 0행
		JButton Carnival_button = new JButton();
		ImageIcon Carnival_imageIcon = new ImageIcon("./image/2023 Carnival.jpg");
		Image Carnival_image = Carnival_imageIcon.getImage();
		Dimension Carnival_buttonSize = new Dimension(250, 200);
		Carnival_button.setIcon(new ImageIcon(Carnival_image.getScaledInstance(Carnival_buttonSize.width,
				Carnival_buttonSize.height, Image.SCALE_SMOOTH)));
		Carnival_button.setPreferredSize(Carnival_buttonSize);
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		large_car_panel.add(Carnival_button, c);

		Carnival_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// 카니발 설명 1열, 0행
		JLabel Carnival_label = new JLabel("카니발");
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		large_car_panel.add(Carnival_label, c);
		Carnival_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		// 스타리아 이미지 및 버튼 0열, 1행
		JButton Staria_button = new JButton();
		ImageIcon Staria_imageIcon = new ImageIcon("./image/2023 Staria.jpg");
		Image Staria_image = Staria_imageIcon.getImage();
		Dimension Staria_buttonSize = new Dimension(250, 200);
		Staria_button.setIcon(new ImageIcon(
				Staria_image.getScaledInstance(Staria_buttonSize.width, Staria_buttonSize.height, Image.SCALE_SMOOTH)));
		Staria_button.setPreferredSize(Staria_buttonSize);
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		large_car_panel.add(Staria_button, c);

		Staria_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// 스타리아 설명 1열, 1행
		JLabel Staria_label = new JLabel("스타리아");
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		large_car_panel.add(Staria_label, c);
		Staria_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));

	}
}