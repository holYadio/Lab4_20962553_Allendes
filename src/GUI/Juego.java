package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Juego extends JFrame {
    public String userName;
    public JPanel panel;
    public Juego(String nombreUsuario){
        super("Dobble Game");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.userName = nombreUsuario;
        initComponent();

    }

    private void initComponent() {
        colocarPaneles();
        colocarLabels();
        colocarBotones();

        //Panel decorativo
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(234, 184, 35));
        panel1.setBounds(0,0,100,500);
        panel.add(panel1);
    }

    private void colocarLabels() {
        JLabel etiq1 = new JLabel("Bienvenido " + userName, SwingConstants.CENTER);
        etiq1.setForeground(new Color(0, 0, 0));
        etiq1.setFont(new Font("roboto",Font.BOLD,15));
        etiq1.setBounds(200,10,200,20);

        JLabel etiq2 = new JLabel("Seleccione una de las opciones para crear un juego", SwingConstants.CENTER);
        etiq2.setForeground(new Color(0, 0, 0));
        etiq2.setFont(new Font("roboto",Font.PLAIN,13));
        etiq2.setBounds(120,40,320,20);

        ImageIcon imagen = new ImageIcon("logoDobble.png");
        JLabel etiq3 = new JLabel();
        etiq3.setBounds(10,5,80,80);
        etiq3.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH)));
        panel.add(etiq1);
        panel.add(etiq2);
        panel.add(etiq3);
    }

    private void colocarBotones() {
        // Boton crear juego predeterminado
        JButton btnCrearJuego1 = new JButton("Predeterminado");
        btnCrearJuego1.setBounds(230,70,130,25);

        // Boton crear juego personalizado
        JButton btnCrearJuego2 = new JButton("Personalizado");
        btnCrearJuego2.setBounds(230,110,130,25);

        // Boton para Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(230,150,130,25);
        ActionListener AccionBtnSalir = e -> dispose();
        btnSalir.addActionListener(AccionBtnSalir);

        panel.add(btnCrearJuego1);
        panel.add(btnCrearJuego2);
        panel.add(btnSalir);
    }


    private void colocarPaneles(){
        //Panel Principal
        panel = new JPanel();
        panel.setBackground(new Color(180, 48, 159));
        this.getContentPane().add(panel);
        panel.setLayout(null);


    }

}