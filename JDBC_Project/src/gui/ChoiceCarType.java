package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//차량 등급 선택 - 지수
@SuppressWarnings("serial")
class BackgroundPanel_1 extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel_1(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

@SuppressWarnings("serial")
class ChoiceCarType extends BackgroundPanel_1 {
    JButton[] buttonList = new JButton[4];
    Font buttonFont = new Font("굴림", Font.BOLD, 30);
    Dimension buttonSize = new Dimension(200, 100);

    ChoiceCarType() {
        super("C:\\Users\\82108\\OneDrive\\바탕 화면\\자동차 로고\\로그인.jpg"); // 배경 이미지 파일의 경로로 변경해야 합니다.
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets.top = 5;
        c.insets.bottom = 5;
        c.insets.left = 10;
        c.insets.right = 10;

        String[] buttonLabels = { "경차", "소형차", "중형차", "대형차" };
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setFont(buttonFont);
            button.setPreferredSize(buttonSize);
            c.gridx = i % 2;
            c.gridy = i / 2;
            c.anchor = (i % 2 == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
            add(button, c);
            buttonList[i] = button;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("차 종류 선택하기");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);
        frame.setLocationRelativeTo(null);

        ChoiceCarType choiceCarType = new ChoiceCarType();
        frame.getContentPane().add(choiceCarType);
        frame.setVisible(true);
    }
}
