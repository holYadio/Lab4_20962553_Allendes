package GUI;

import javax.swing.*;
import java.awt.*;

public class Final extends JFrame {
    public String ganador;
    public JPanel panel;
    public Final(String winner){
        super("Dobble Game");
        setSize(500, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(217, 188, 67));
        this.ganador = winner;
        initComponent1();
    }
    private void initComponent1() {
        colocarPaneles();
        colocarLabels();

        //Panel decorativo
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(234, 184, 35));
        panel1.setBounds(0,0,100,600);
        panel.add(panel1);
    }
    private void colocarPaneles(){
        //Panel Principal
        panel = new JPanel();
        panel.setBackground(new Color(180, 48, 159));
        this.getContentPane().add(panel);
        panel.setLayout(null);
    }
    private void colocarLabels() {
        ImageIcon imagen = new ImageIcon("logoDobble.png");
        JLabel etiq1 = new JLabel();
        etiq1.setBounds(10, 5, 80, 80);
        etiq1.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));

        JLabel etiq2 = new JLabel(ganador,SwingConstants.CENTER);
        etiq2.setForeground(new Color(0, 0, 0));
        etiq2.setFont(new Font("roboto",Font.BOLD,15));
        etiq2.setBounds(150,50,300,20);
        panel.add(etiq1);
        panel.add(etiq2);
    }
}
