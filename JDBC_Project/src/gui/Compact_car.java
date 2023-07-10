package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

//경차 선택 - 진만
public class Compact_car {

    private JPanel panel_Small_Car = new JPanel();

    public JPanel getPanel_Small_Car() {
        return panel_Small_Car;
    }

    public JPanel Small_Car() {
        panel_Small_Car.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets.top = 5;
        c.insets.bottom = 5;
        c.insets.left = 10;
        c.insets.right = 10;

        JButton button1 = new JButton();
        ImageIcon imageIcon1 = new ImageIcon("C:/Users/JM/Desktop/Team Project (D team)/Data Survey/2023 Ray.jpg");
        Image image1 = imageIcon1.getImage();
        Dimension buttonSize1 = new Dimension(200, 200);
        button1.setIcon(new ImageIcon(image1.getScaledInstance(buttonSize1.width, buttonSize1.height, Image.SCALE_SMOOTH)));
        button1.setPreferredSize(buttonSize1);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        panel_Small_Car.add(button1, c);

        JButton button2 = new JButton();
        ImageIcon imageIcon2 = new ImageIcon("C:/Users/JM/Desktop/Team Project (D team)/Data Survey/2022 Spark.jpg");
        Image image2 = imageIcon2.getImage();
        Dimension buttonSize2 = new Dimension(200, 200);
        button2.setIcon(new ImageIcon(image2.getScaledInstance(buttonSize2.width, buttonSize2.height, Image.SCALE_SMOOTH)));
        button2.setPreferredSize(buttonSize2);
        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        panel_Small_Car.add(button2, c);

        JLabel label1 = new JLabel("레이");
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        panel_Small_Car.add(label1, c);
        label1.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        JLabel label2 = new JLabel("스파크");
        c.gridx = 2;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        panel_Small_Car.add(label2, c);
        label2.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        JButton button3 = new JButton();
        ImageIcon imageIcon3 = new ImageIcon("C:/Users/JM/Desktop/Team Project (D team)/Data Survey/2023 Casper.png");
        Image image3 = imageIcon3.getImage();
        Dimension buttonSize3 = new Dimension(200, 200);
        button3.setIcon(new ImageIcon(image3.getScaledInstance(buttonSize3.width, buttonSize3.height, Image.SCALE_SMOOTH)));
        button3.setPreferredSize(buttonSize3);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        panel_Small_Car.add(button3, c);

        JButton button4 = new JButton();
        ImageIcon imageIcon4 = new ImageIcon("C:/Users/JM/Desktop/Team Project (D team)/Data Survey/2023 Morning.jpg");
        Image image4 = imageIcon4.getImage();
        Dimension buttonSize4 = new Dimension(200, 200);
        button4.setIcon(new ImageIcon(image4.getScaledInstance(buttonSize4.width, buttonSize4.height, Image.SCALE_SMOOTH)));
        button4.setPreferredSize(buttonSize4);
        c.gridx = 2;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        panel_Small_Car.add(button4, c);

        JLabel label3 = new JLabel("캐스퍼");
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        panel_Small_Car.add(label3, c);
        label3.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        JLabel label4 = new JLabel("모닝");
        c.gridx = 2;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        panel_Small_Car.add(label4, c);
        label4.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        JButton button5 = new JButton("되돌아가기");
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        panel_Small_Car.add(button5, c);
        button5.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        return panel_Small_Car;
    }
}