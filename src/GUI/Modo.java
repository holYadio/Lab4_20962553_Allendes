package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modo extends JFrame {
    public String userName;
    public JPanel panel;
    public Modo(String nombreUsuario){
        super("Dobble Game");
        setSize(400, 300);
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
        etiq1.setBounds(150,20,200,20);

        JLabel etiq2 = new JLabel("Seleccione un modo de juego", SwingConstants.CENTER);
        etiq2.setForeground(new Color(0, 0, 0));
        etiq2.setFont(new Font("roboto",Font.PLAIN,13));
        etiq2.setBounds(100,50,300,20);

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
        JButton btnModo1 = new JButton("User vs User");
        //JButton btnCrearJuego1 = new JButton("Predeterminado");
        btnModo1.setBounds(180,80,130,25);
        ActionListener AccionModoUserVsUser = e -> {
            setVisible(false);
            CrearMazo nextFrame = new CrearMazo(userName,"User vs User");
            nextFrame.setVisible(true);
        };
        btnModo1.addActionListener(AccionModoUserVsUser);

        // Boton crear juego personalizado
        JButton btnModo2 = new JButton("Demo Mode");
        //JButton btnCrearJuego2 = new JButton("Personalizado");
        btnModo2.setBounds(180,120,130,25);
        ActionListener AccionCPUMode = e -> {
            setVisible(false);
            CrearMazo nextFrame = new CrearMazo(userName,"Demo Mode");
            nextFrame.setVisible(true);
        };
        btnModo2.addActionListener(AccionCPUMode);


        // Boton para Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(180,160,130,25);
        ActionListener AccionBtnSalir = e -> System.exit(0);
        btnSalir.addActionListener(AccionBtnSalir);

        // Boton para volver al menu anterior
        ImageIcon imagen2 = new ImageIcon("back.png");
        JButton btnBack = new JButton();
        btnBack.setBounds(350,10,30,30);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        ActionListener AccionVolver = e -> {
            setVisible(false);
            Ventana ventana = new Ventana();
            ventana.setVisible(true);
        };
        btnBack.addActionListener(AccionVolver);

        panel.add(btnModo1);
        panel.add(btnModo2);
        panel.add(btnSalir);
        panel.add(btnBack);
    }


    private void colocarPaneles(){
        //Panel Principal
        panel = new JPanel();
        panel.setBackground(new Color(180, 48, 159));
        this.getContentPane().add(panel);
        panel.setLayout(null);


    }
}
