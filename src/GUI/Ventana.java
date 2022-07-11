package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {
    public JPanel panel;
    public Ventana(){

        super("Dobble Game");
        setSize(400, 180);
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

        ImageIcon imagen = new ImageIcon("logoDobble.png");
        JLabel etiq3 = new JLabel();
        etiq3.setBounds(10,5,80,80);
        etiq3.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH)));

        // Espacio para el nuevo usuario
        JTextField nombreUsuario = new JTextField("Nombre de usuario");
        nombreUsuario.setBounds(180,60,135,25);
        panel.add(etiq3);



        // Panel decorativo
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(234, 184, 35));
        panel1.setBounds(0,0,100,500);
        panel.add(panel1);


        // Boton inicio
        JButton btnIni = new JButton();
        btnIni.setText("Registrar");
        btnIni.setBounds(200,90,100,30);

        ActionListener oA = new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Modo crearJuego = new Modo(nombreUsuario.getText());
                crearJuego.setVisible(true);
            }
        };
        btnIni.addActionListener(oA);
        // Ocupar setEnabled(false); cuando pasemos a la siguiente ventana

        panel.add(etiq1);
        panel.add(etiq2);
        panel.add(nombreUsuario);
        panel.add(btnIni);
        }
}
