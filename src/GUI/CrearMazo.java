package GUI;

import Model.DobbleGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearMazo extends JFrame{
    public String userName;
    public String mode;
    public JPanel panel;
    public CrearMazo(String nombreUsuario, String modo){
        super("Dobble Game");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.userName = nombreUsuario;
        this.mode = modo;
        initComponent();

    }

    private void initComponent() {
        colocarPaneles();
        colocarLabels();
        colocarBotones();
        colocarAreaDeTexto();

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
        etiq1.setBounds(200,10,200,20);

        JLabel etiq2 = new JLabel("Seleccione un mazo para jugar", SwingConstants.CENTER);
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
        ActionListener accionBtn1 = e -> {
        dispose();
        if (mode== "User vs User"){
            Juego f = new Juego(userName,mode);
            f.setVisible(true);
        }else if(mode== "Demo Mode"){
            DobbleGame dg = new DobbleGame(2,57,"Demo Mode",2,8);
            dg.register("CPU 1");
            dg.register("CPU 2");
            JugarJuego f = new JugarJuego(dg,userName);
            f.setVisible(true);
        }
        };
        btnCrearJuego1.addActionListener(accionBtn1);

        // Boton crear juego personalizado
        JButton btnCrearJuego2 = new JButton("Personalizado");
        btnCrearJuego2.setBounds(230,110,130,25);

        // Boton para Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(230,150,130,25);
        ActionListener accionBtnSalir = e -> System.exit(0);
        btnSalir.addActionListener(accionBtnSalir);

        // Boton para volver al menu anterior
        ImageIcon imagen2 = new ImageIcon("back.png");
        JButton btnBack = new JButton();
        btnBack.setBounds(450,10,30,30);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        ActionListener AccionVolver = e -> {
            setVisible(false);
            Modo lastF = new Modo(userName);
            lastF.setVisible(true);
        };
        btnBack.addActionListener(AccionVolver);

        panel.add(btnCrearJuego1);
        panel.add(btnCrearJuego2);
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

    private void colocarAreaDeTexto(){
        JTextArea txt1 = new JTextArea();
        txt1.setText("""
                el mazo predeterminado es de 57 cartas
                con 7 elementos por carta, los elementos
                corresponden a numeros""");
        txt1.setFont(new Font("roboto",Font.BOLD,13));
        txt1.setBounds(180,190,240,60);
        txt1.setOpaque(false);
        txt1.setEditable(false);
        panel.add(txt1);
    }
}

