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
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponent();
        this.userName = nombreUsuario;
    }

    private void initComponent() {
        colocarPaneles();
        colocarLabels();
        colocarBotones();
    }

    private void colocarLabels() {
        JLabel etiq1 = new JLabel("Bienvenido " + userName, SwingConstants.CENTER);
        etiq1.setForeground(new Color(0, 0, 0));
        etiq1.setFont(new Font("roboto",Font.BOLD,15));
        etiq1.setBounds(140,10,200,20);

        JLabel etiq2 = new JLabel("Seleccione una de las opciones", SwingConstants.CENTER);
        etiq2.setForeground(new Color(0, 0, 0));
        etiq2.setFont(new Font("roboto",Font.PLAIN,14));
        etiq2.setBounds(135,40,200,20);
        panel.add(etiq1);
        panel.add(etiq2);
    }

    private void colocarBotones() {
        // Boton crear juego predeterminado
        JButton btnCrearJuego1 = new JButton("Predeterminado");
        btnCrearJuego1.setBounds(180,70,130,25);
        // Boton crear juego personalizado
        JButton btnCrearJuego2 = new JButton("Personalizado");
        btnCrearJuego2.setBounds(180,110,130,25);
        // Boton para Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(180,150,130,25);
        ActionListener AccionBtnSalir = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        };
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

        //Panel decorativo
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(234, 184, 35));
        panel1.setBounds(0,0,100,500);
        panel.add(panel1);
    }

}