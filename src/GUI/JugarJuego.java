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
    public JTextArea txt1;
    public JTextArea txt2;
    public JTextArea txt3;
    public JTextArea txt4;
    public JTextArea txt5;
    public JLabel etiq2;
    public JugarJuego(DobbleGame dg, String nombreUsuario){
        super("Dobble Game");
        setSize(500, 600);
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
        colocarAreaDeTexto();
        colocarBotones();

        //Panel decorativo
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(234, 184, 35));
        panel1.setBounds(0,0,100,600);
        panel.add(panel1);
    }

    private void colocarLabels() {

        ImageIcon imagen = new ImageIcon("logoDobble.png");
        JLabel etiq1 = new JLabel();
        etiq1.setBounds(10,5,80,80);
        etiq1.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH)));

        etiq2 = new JLabel(dobbleGame.whoseTurnIsIt(),SwingConstants.CENTER);
        etiq2.setForeground(new Color(0, 0, 0));
        etiq2.setFont(new Font("roboto",Font.BOLD,15));
        etiq2.setBounds(150,10,300,20);

        JLabel etiq3 = new JLabel("Cartas en mesa",SwingConstants.CENTER);
        etiq3.setForeground(new Color(0, 0, 0));
        etiq3.setFont(new Font("roboto",Font.PLAIN,14));
        etiq3.setBounds(150,35,300,20);

        panel.add(etiq1);
        panel.add(etiq2);
        panel.add(etiq3);
    }





    private void colocarPaneles(){
        //Panel Principal
        panel = new JPanel();
        panel.setBackground(new Color(180, 48, 159));
        this.getContentPane().add(panel);
        panel.setLayout(null);
    }
    private void colocarAreaDeTexto(){
        dobbleGame.ponerCartasEnMesa();
        txt1 = new JTextArea();
        txt1.setText(dobbleGame.getCardsMesa().get(0).toString2());
        txt1.setFont(new Font("roboto",Font.BOLD,13));
        txt1.setBounds(130,60,100,150);
        txt1.setOpaque(false);
        txt1.setEditable(false);

        txt2 = new JTextArea();
        txt2.setText(dobbleGame.getCardsMesa().get(1).toString2());
        txt2.setFont(new Font("roboto",Font.BOLD,13));
        txt2.setBounds(400,60,100,150);
        txt2.setOpaque(false);
        txt2.setEditable(false);

        txt3 = new JTextArea();
        txt3.setEditable(false);
        txt3.setOpaque(false);
        txt3.setText("Quedan "+ dobbleGame.getDobble().numCards()+ " en el mazo");
        txt3.setFont(new Font("roboto",Font.PLAIN,13));
        txt3.setBounds(240,70,150,20);

        txt4 = new JTextArea();
        txt4.setOpaque(false);
        txt4.setBounds(200,100,200,60);

        txt5 = new JTextArea();
        txt5.setText(dobbleGame.getPlayers().get(dobbleGame.getTurnoPlayer()-1).toString());
        txt5.setEditable(false);
        txt5.setBounds(130,215,260,400);
        txt5.setOpaque(false);
        JScrollPane scroll = new JScrollPane(txt5);
        scroll.setBounds(130,220,320,280);
        scroll.setFont(new Font("roboto",Font.PLAIN,13));
        scroll.setForeground(new Color(0, 0, 0));
        scroll.setEnabled(false);
        scroll.setOpaque(false);
        panel.add(txt1);
        panel.add(txt2);
        panel.add(txt3);
        panel.add(scroll);

    }
    private void colocarBotones() {
        // Boton crear juego predeterminado
        JButton btnJugarJuego1 =  new JButton("Siguiente Jugada");
        btnJugarJuego1.setBounds(115,510,135,25);
        ActionListener accionBtnJugar1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dobbleGame.getDobble().getCardsSet().size() >1){
                    panel.add(txt4);
                    panel.revalidate();
                    dobbleGame.ponerCartasEnMesa();
                    etiq2.setText(dobbleGame.whoseTurnIsIt());
                    txt1.setText(dobbleGame.getCardsMesa().get(0).toString2());
                    txt2.setText(dobbleGame.getCardsMesa().get(1).toString2());
                    txt3.setText("Quedan " + dobbleGame.getDobble().numCards() + " en el mazo");
                    txt4.setText(dobbleGame.getElementSelected());
                    txt5.setText(dobbleGame.getPlayers().get(dobbleGame.getTurnoPlayer() - 1).toString());
                    dobbleGame.jugadaDemoMode();
                    dobbleGame.play(0);
                }else{
                    Final f = new Final(dobbleGame.getGanador());
                    setVisible(false);
                    f.setVisible(true);
                }

            }
        };
        btnJugarJuego1.addActionListener(accionBtnJugar1);

        JButton btnJugarJuego2 = new JButton("Visualizar juego");
        btnJugarJuego2.setBounds(260,510,125,25);
        ActionListener accionBtnJugarJuego3 = e -> new StatusGame(dobbleGame).setVisible(true);
        btnJugarJuego2.addActionListener(accionBtnJugarJuego3);

        // Boton para Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(395,510,60,25);
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
            CrearMazo lastF = new CrearMazo(userName,dobbleGame.getMode());
            lastF.setVisible(true);
        };
        btnBack.addActionListener(AccionVolver);

        panel.add(btnJugarJuego1);
        panel.add(btnJugarJuego2);
        panel.add(btnSalir);
        panel.add(btnBack);
    }
}
