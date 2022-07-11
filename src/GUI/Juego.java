package GUI;

import Model.DobbleGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Juego extends JFrame {
    public String username;
    public DobbleGame dobbleGame;
    public JPanel panel;
    public Juego(String nombreUsuario,String modo){
        super("Dobble Game");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(217, 188, 67));
        this.dobbleGame = new DobbleGame(2,57, modo,2,8);
        this.username = nombreUsuario;
        dobbleGame.register(nombreUsuario);
        initComponent();
    }
    private void initComponent() {
        colocarPaneles();
        colocarLabels();
        colocarBotones();

        //Panel decorativo
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(234, 184, 35));
        panel1.setBounds(0,0,100,300);
        panel.add(panel1);
    }

    private void colocarLabels() {
        JLabel etiq1 = new JLabel("Bienvenido " + username, SwingConstants.CENTER);
        etiq1.setForeground(new Color(0, 0, 0));
        etiq1.setFont(new Font("roboto",Font.BOLD,15));
        etiq1.setBounds(150,10,200,20);

        ImageIcon imagen = new ImageIcon("logoDobble.png");
        JLabel etiq2 = new JLabel();
        etiq2.setBounds(10,5,80,80);
        etiq2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH)));

        JLabel etiq3 = new JLabel("Escoja su opcion:");
        etiq3.setFont(new Font("roboto",Font.BOLD,13));
        etiq3.setBounds(200,40,100,20);

        panel.add(etiq1);
        panel.add(etiq2);
        panel.add(etiq3);
    }


    private void colocarBotones() {
        // Boton crear juego predeterminado
        JButton btnCrearJuego1 = new JButton("Registrar Player");
        btnCrearJuego1.setBounds(185,70,130,25);
        ActionListener accionBtn1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        };

        // Boton crear juego personalizado
        JButton btnCrearJuego2 = new JButton("Jugar");
        btnCrearJuego2.setBounds(185,110,130,25);

        JButton btnCrearJuego3 = new JButton("Visualizar juego");
        btnCrearJuego3.setBounds(185,150,130,25);
        // Boton para Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(185,190,130,25);
        ActionListener accionBtnSalir = e -> System.exit(0);
        btnSalir.addActionListener(accionBtnSalir);

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
            CrearMazo lastF = new CrearMazo(username,dobbleGame.getMode());
            lastF.setVisible(true);
        };
        btnBack.addActionListener(AccionVolver);

        panel.add(btnCrearJuego1);
        panel.add(btnCrearJuego2);
        panel.add(btnCrearJuego3);
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