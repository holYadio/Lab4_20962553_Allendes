package GUI;

import Model.DobbleGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StatusGame extends JFrame{
    public DobbleGame dobbleGame;
    public JPanel panel;
    public StatusGame(DobbleGame dg){
        super("Dobble Game");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.getContentPane().setBackground(new Color(217, 188, 67));
        this.dobbleGame = dg;
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
        panel1.setBounds(0,0,100,500);
        panel.add(panel1);
    }

    private void colocarLabels() {
        ImageIcon imagen = new ImageIcon("logoDobble.png");
        JLabel etiq1 = new JLabel();
        etiq1.setBounds(10,5,80,80);
        etiq1.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH)));

        panel.add(etiq1);
    }


    private void colocarBotones() {
        // Boton para Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(200,470,130,25);
        ActionListener accionBtnSalir = e -> dispose();
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
            Modo lastF = new Modo(dobbleGame.getPlayers().get(0).getNombre());
            lastF.setVisible(true);
        };
        btnBack.addActionListener(AccionVolver);
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
        txt1.setText(dobbleGame.toString());
        txt1.setEditable(false);
        JScrollPane scroll = new JScrollPane(txt1);
        scroll.setBounds(100,10,260,450);
        scroll.setFont(new Font("roboto",Font.PLAIN,13));
        scroll.setForeground(new Color(0, 0, 0));
        scroll.setEnabled(false);
        panel.add(scroll);
    }
}
