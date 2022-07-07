package GUI;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    public JPanel panel;
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
        colocarPaneles();
        primerosComponents();
    }
    private void colocarPaneles(){
        //Panel
        panel = new JPanel();
        panel.setBackground(new Color(255, 220, 76));
        this.getContentPane().add(panel);
        //panel1.setLayout(null);
    }

    private void primerosComponents(){

        // etiqueta 1
        JLabel txtInicio = new JLabel("Bienvenido al juego Dobble", SwingConstants.CENTER);
        txtInicio.setForeground(new Color(0, 0, 0));
        txtInicio.setFont(new Font("roboto",Font.BOLD,15));


        // Instruccion 1
        JLabel inst1 = new JLabel("Para poder jugar ingrese un nombre de usuario a registrar", SwingConstants.CENTER);

        // Espacio para el nuevo usuario
        JTextField nombreUsuario = new JTextField("Nombre de usuario");


        // Boton 1
        JButton boton1 = new JButton();
        boton1.setText("Registrar");
        // Ocupar setEnabled(false); cuando pasemos a la siguiente ventana

        // Boton 2
        JButton boton2 = new JButton();

        panel.add(txtInicio);
        panel.add(inst1);
        panel.add(nombreUsuario);
        panel.add(boton1);
        }
}
