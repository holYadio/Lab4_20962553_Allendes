package GUI;

import Model.DobbleGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Juego extends JFrame {
    public String username;
    public DobbleGame dobbleGame;
    public JPanel panel;
    public JTextField txt1;
    public Juego(String nombreUsuario,DobbleGame dg){
        super("Dobble Game");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.getContentPane().setBackground(new Color(217, 188, 67));
        this.dobbleGame = dg;
        this.username = nombreUsuario;
        if(dobbleGame.getPlayers().size() != 2) {
            initComponent();
        }else{
            initComponent2();
        }
    }

    private void initComponent() {
        colocarPaneles();
        colocarLabels();
        colocarBotones();
        colocarTextFiel();

        //Panel decorativo
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(234, 184, 35));
        panel1.setBounds(0,0,100,300);
        panel.add(panel1);
    }
    private void initComponent2() {
        colocarPaneles();
        colocarLabels();
        colocarBotones2();

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
        etiq1.setBounds(150,20,200,20);

        ImageIcon imagen = new ImageIcon("logoDobble.png");
        JLabel etiq2 = new JLabel();
        etiq2.setBounds(10,5,80,80);
        etiq2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH)));

        JLabel etiq3 = new JLabel();
        if(dobbleGame.getPlayers().size() != 2) {
            etiq3.setText("Ingrese el nombre del otro jugador:");
            etiq3.setFont(new Font("roboto",Font.BOLD,13));
            etiq3.setBounds(160,50,220,20);
        }else{
            etiq3.setText("Seleccione una opcion:");
            etiq3.setFont(new Font("roboto",Font.BOLD,13));
            etiq3.setBounds(185,40,220,20);
        }


        panel.add(etiq1);
        panel.add(etiq2);
        panel.add(etiq3);
    }


    private void colocarBotones() {
        // Boton crear juego predeterminado
        JButton btnCrearJuego1 = new JButton("Registrar Player");
        btnCrearJuego1.setBounds(185,120,130,25);
        ActionListener accionBtn1 = e -> {
            if(dobbleGame.getPlayers().isEmpty()){
                dobbleGame.register(username);
            }
            dobbleGame.register(txt1.getText().replace(" ",""));
            if(dobbleGame.getError() == 0){
                new Juego(username,dobbleGame).setVisible(true);
                dispose();
            }else if(dobbleGame.getError() == 1){
                JOptionPane.showMessageDialog(null,"ERROR: El usuario que se intenta registrar ya" +
                        " existe","Dobble Game",JOptionPane.ERROR_MESSAGE);
            } else if (dobbleGame.getError() == 2){
                JOptionPane.showMessageDialog(null,"ERROR: El campo esta vacio","Dobble Game",
                        JOptionPane.ERROR_MESSAGE);
            }

        };
        btnCrearJuego1.addActionListener(accionBtn1);

        // Boton para Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(185,160,130,25);
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

        ImageIcon imagen3 = new ImageIcon("home.png");
        JButton btnHome = new JButton();
        btnHome.setBounds(235, 200,30,30);
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
        panel.add(btnCrearJuego1);
        panel.add(btnSalir);
        panel.add(btnBack);
    }
    private void colocarBotones2(){
        // Boton crear juego personalizado
        JButton btnCrearJuego2 = new JButton("Jugar");
        btnCrearJuego2.setBounds(185,70,130,25);
        ActionListener accionBtnCrear2 = e -> {
            new JugarJuego(dobbleGame,username).setVisible(true);
            dispose();
        };
        btnCrearJuego2.addActionListener(accionBtnCrear2);

        JButton btnCrearJuego3 = new JButton("Visualizar juego");
        btnCrearJuego3.setBounds(185,110,130,25);
        ActionListener accionBtnCrear3 = e -> new StatusGame(dobbleGame).setVisible(true);
        btnCrearJuego3.addActionListener(accionBtnCrear3);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(185,150,130,25);
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
            CrearMazo lastF = new CrearMazo(username,dobbleGame.getMode());
            lastF.setVisible(true);
            dispose();
        };
        btnBack.addActionListener(AccionVolver);
        ImageIcon imagen3 = new ImageIcon("home.png");
        JButton btnHome = new JButton();
        btnHome.setBounds(235,190,30,30);
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
        panel.add(btnCrearJuego2);
        panel.add(btnCrearJuego3);
        panel.add(btnSalir);
        panel.add(btnBack);
    }


    private void colocarPaneles(){
        //Panel Principal
        panel = new JPanel();
        panel.setBackground(new Color(185, 48, 159));
        this.getContentPane().add(panel);
        panel.setLayout(null);
    }

    private void colocarTextFiel(){
        txt1 = new JTextField();
        txt1.setBounds(185,80,130,25);
        txt1.setOpaque(false);
        panel.add(txt1);
    }
}