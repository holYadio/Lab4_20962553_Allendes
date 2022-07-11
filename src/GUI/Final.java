package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Final extends JFrame {
    public String ganador;
    public JPanel panel;
    public Final(String winner){
        super("Dobble Game");
        setSize(500, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.getContentPane().setBackground(new Color(217, 188, 67));
        this.ganador = winner;
        initComponent1();
    }
    private void initComponent1() {
        colocarPaneles();
        colocarLabels();

        ImageIcon imagen3 = new ImageIcon("home.png");
        JButton btnHome = new JButton();
        btnHome.setBounds(450,10,30,30);
        btnHome.setOpaque(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setBorderPainted(false);
        btnHome.setIcon(new ImageIcon(imagen3.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        ActionListener AccionHome = e -> {
            dispose();
            new Ventana().setVisible(true);
        };
        btnHome.addActionListener(AccionHome);
        panel.add(btnHome);

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
