package GUI;

import Model.DobbleGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class MazoPerso extends JFrame {
    public JPanel panel;
    public String userName;
    public String mode;
    public JTextField txt1;
    public JTextField txt2;
    public JTextField txt3;
    public MazoPerso(String nombreUsuario,String modo){
        super("Dobble Game");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.getContentPane().setBackground(new Color(217, 188, 67));
        this.userName = nombreUsuario;
        this.mode = modo;
        initComponent();
    }
    private void initComponent() {
        colocarPaneles();
        colocarLabels();
        colocarAreaDeTexto();
        colocarBotones();

        //Panel decorativo
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(234, 184, 35));
        panel1.setBounds(0,0,100,300);
        panel.add(panel1);
    }

    private void colocarPaneles(){
        //Panel Principal
        panel = new JPanel();
        panel.setBackground(new Color(180, 48, 159));
        this.getContentPane().add(panel);
        panel.setLayout(null);
    }

    private void colocarBotones() {
        JButton btnCrear = new JButton("Crear Dobble");
        btnCrear.setBounds(200,200,130,25);
        ActionListener accionBtn1 = e -> {
            if((txt1.getText().isEmpty()) || (txt2.getText().isEmpty()) || (txt3.getText().isEmpty())){
                JOptionPane.showMessageDialog(null, "ERROR: Algun campo esta vacio",
                        "Dobble Game", JOptionPane.ERROR_MESSAGE);
            } else{
                int cantidadCartas;
                int numElementos;
                List<String> elementos;
                try{
                    cantidadCartas = Integer.parseInt(txt1.getText());
                    numElementos = Integer.parseInt(txt3.getText());
                    String s = txt2.getText().replace(" ","");
                    String[] b = s.split(",");
                    elementos = Arrays.asList(b);


                    if ((((((((numElementos-1)*(numElementos-1))+numElementos)-2) <= cantidadCartas) &&
                            (cantidadCartas <= (((numElementos-1)*(numElementos-1))+numElementos)))) &&
                            (elementos.size() >= (((numElementos-1)*(numElementos-1))+numElementos))){
                        DobbleGame dg = new DobbleGame(2,cantidadCartas,mode,numElementos,elementos);
                        new Juego(userName,dg).setVisible(true);
                        dispose();
                        System.out.println(numElementos);
                        System.out.println(((((numElementos-1)*(numElementos-1))+numElementos)));
                    }else {
                        JOptionPane.showMessageDialog(null, "ERROR: No cumple la cantidad minima de elementos o el numeros de cartas",
                                "Dobble Game", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception a){
                    JOptionPane.showMessageDialog(null, "ERROR: Algún campo esta erróneo",
                            "Dobble Game", JOptionPane.ERROR_MESSAGE);
                }

            }
        };
        btnCrear.addActionListener(accionBtn1);

        // Boton para volver al menu anterior
        ImageIcon imagen2 = new ImageIcon("back.png");
        JButton btnBack = new JButton();
        btnBack.setBounds(450, 10, 30, 30);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        ActionListener AccionVolver = e -> {
            dispose();
            CrearMazo lastF = new CrearMazo(userName, mode);
            lastF.setVisible(true);
        };
        btnBack.addActionListener(AccionVolver);
        ImageIcon imagen3 = new ImageIcon("home.png");
        JButton btnHome = new JButton();
        btnHome.setBounds(450,45,30,30);
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
        panel.add(btnCrear);
        panel.add(btnBack);
    }

    private void colocarAreaDeTexto() {
        txt1 = new JTextField();
        txt1.setBounds(320,30,100,25);

        txt2 = new JTextField();
        txt2.setBounds(320,70,100,25);

        txt3 = new JTextField();
        txt3.setBounds(320,110,100,25);

        JTextArea txt4 = new JTextArea("""
                                        Considere que la cantidad de cartas puede ser hasta - 2 que 
                                        ((numeroElementosPorCarta-1)^2)+numeroElementosPorCarta 
                                        + 1""");
        txt4.setBounds(110,150,365,50);
        txt4.setOpaque(false);
        txt4.setFont(new Font("roboto",Font.BOLD,13));
        txt4.setEditable(false);
        panel.add(txt1);
        panel.add(txt2);
        panel.add(txt3);
        panel.add(txt4);
    }

    private void colocarLabels() {
        JLabel etiq1 = new JLabel("Ingrese la cantidad de cartas:");
        etiq1.setForeground(new Color(0, 0, 0));
        etiq1.setFont(new Font("roboto",Font.BOLD,13));
        etiq1.setBounds(120,30,200,20);

        JLabel etiq2 = new JLabel("Ingrese los elementos");
        etiq2.setForeground(new Color(0, 0, 0));
        etiq2.setFont(new Font("roboto",Font.BOLD,13));
        etiq2.setBounds(120,70,200,20);

        ImageIcon imagen = new ImageIcon("logoDobble.png");
        JLabel etiq3 = new JLabel();
        etiq3.setBounds(10,5,80,80);
        etiq3.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH)));


        JLabel etiq4 = new JLabel("Ingrese los elementos por carta:");
        etiq4.setForeground(new Color(0, 0, 0));
        etiq4.setFont(new Font("roboto",Font.BOLD,13));
        etiq4.setBounds(120,110,200,20);


        panel.add(etiq1);
        panel.add(etiq2);
        panel.add(etiq3);
        panel.add(etiq4);

    }


}
