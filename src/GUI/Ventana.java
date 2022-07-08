package GUI;

import Model.DobbleGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {
    public String userName;
    public JPanel panel;
    public Ventana(){

        super("Dobble Game");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        panel.setBackground(new Color(180, 48, 159));
        this.getContentPane().add(panel);
        panel.setLayout(null);
    }

    private void primerosComponents(){

        // etiqueta 1
        JLabel etiq1 = new JLabel("Bienvenido al juego Dobble", SwingConstants.CENTER);
        etiq1.setForeground(new Color(0, 0, 0));
        etiq1.setFont(new Font("roboto",Font.BOLD,15));
        etiq1.setBounds(140,10,200,20);

        // Instruccion 1
        JLabel etiq2 = new JLabel("Para poder jugar ingrese un nombre de usuario a registrar",SwingConstants.CENTER);
        etiq2.setFont(new Font("roboto",Font.PLAIN,10));
        etiq2.setBounds(100, 30, 300,20);
        JLabel etiq3 = new JLabel("Seleccione el modo que desea jugar:", SwingConstants.CENTER);
        // Espacio para el nuevo usuario
        JTextField nombreUsuario = new JTextField("Nombre de usuario");
        nombreUsuario.setBounds(180,60,135,25);




        // Panel decorativo
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(234, 184, 35));
        panel1.setBounds(0,0,100,500);
        panel.add(panel1);


        // Boton inicio
        JButton btnIni = new JButton();
        btnIni.setText("Registrar");
        btnIni.setBounds(200,90,100,30);
        btnIni.setBackground(new Color(255, 255, 255,0));


        JButton btnModo1 = new JButton("Stack Mode");
        JButton btnModo2 = new JButton("CPU Mode");
        ActionListener oA = new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Juego crearJuego = new Juego(nombreUsuario.getText());
                crearJuego.setVisible(true);
            }
        };
        btnIni.addActionListener(oA);
        // Ocupar setEnabled(false); cuando pasemos a la siguiente ventana

        String s = """
                Escoja su opcion:
                1. Registrar jugador
                2. Jugar
                3. Visualizar estado completo del juego
                4. Salir
                """;

        panel.add(etiq1);
        panel.add(etiq2);
        panel.add(nombreUsuario);
        panel.add(btnIni);
        }
}
