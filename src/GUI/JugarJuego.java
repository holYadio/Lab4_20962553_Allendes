package GUI;

import Model.DobbleGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JugarJuego extends JFrame {
    public DobbleGame dobbleGame;
    public String userName;
    public JPanel panel;
    public JugarJuego(DobbleGame dg, String nombreUsuario){
        super("Dobble Game");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(217, 188, 67));
        this.dobbleGame = dg;
        this.userName = nombreUsuario;
        initComponent1();
    }

    private void initComponent1() {
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
        JLabel etiq1 = new JLabel("Bienvenido " + userName, SwingConstants.CENTER);
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
        JButton btnJugarJuego1 = new JButton("Registrar Player");
        btnJugarJuego1.setBounds(185,70,130,25);
        ActionListener accionBtn1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        };

        // Boton crear juego personalizado
        JButton btnJugarJuego2 = new JButton("Siguiente Jugada");
        btnJugarJuego2.setBounds(185,110,130,25);

        JButton btnJugarJuego3 = new JButton("Visualizar juego");
        btnJugarJuego3.setBounds(185,150,130,25);
        ActionListener accionBtnJugarJuego3 = e -> new StatusGame(dobbleGame).setVisible(true);
        btnJugarJuego3.addActionListener(accionBtnJugarJuego3);

        // Boton para Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(185,190,130,25);
        ActionListener accionBtnSalir = e -> dispose();
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
            CrearMazo lastF = new CrearMazo(userName,dobbleGame.getMode());
            lastF.setVisible(true);
        };
        btnBack.addActionListener(AccionVolver);

        panel.add(btnJugarJuego1);
        panel.add(btnJugarJuego2);
        panel.add(btnJugarJuego3);
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
