package GUI;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    public Ventana(){
        super("Dobble Game");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(350,400));
        this.getContentPane().setBackground(new Color(217, 188, 67));
        initComponent();
    }
    private void  initComponent() {
        //Panel
        Color base = new Color(255, 220, 76);
        JPanel panel1 = new JPanel();
        panel1.setBackground(base);
        this.getContentPane().add(panel1);
        //panel1.setLayout(null);

        // etiqueta 1
        JLabel txtInicio = new JLabel();
        txtInicio.setText("tuki" );
        txtInicio.setBounds(10,10,200,20);
        txtInicio.setForeground(new Color(0, 0, 0));
        panel1.add(txtInicio);
    }
}
