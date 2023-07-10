package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;


//경차 선택 main - 진만
@SuppressWarnings("serial")
public class Compact_Car_Main extends JFrame {
    
    public Compact_Car_Main() {
        mainframe();
    }
    
    public void mainframe() {
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        
        Compact_car test4 = new Compact_car();
        
        JPanel panel_Test4 = test4.Small_Car();
        panel_Test4.setBounds(0, 0, 900, 800);
        getContentPane().add(panel_Test4);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Compact_Car_Main t0 = new Compact_Car_Main();
                    t0.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}