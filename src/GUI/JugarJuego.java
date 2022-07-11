package GUI;

import Model.DobbleGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JugarJuego extends JFrame {
    public DobbleGame dobbleGame;
    public String userName;
    public JPanel panel;
    public JTextField elementoUser;

    /**
     * Constructor del Frame
     * @param dg partida creada
     * @param nombreUsuario nombre de usuario
     */
    public JugarJuego(DobbleGame dg, String nombreUsuario){
        super("Dobble Game");
        this.dobbleGame = dg;
        this.userName = nombreUsuario;
        if(dobbleGame.getMode().equals("Demo Mode")){
            setSize(500, 600);
        }else{
            setSize(500, 350);
        }

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.getContentPane().setBackground(new Color(217, 188, 67));

        initComponent();

        
    }

    /**
     * Coloca los componenetes en el frame
     */
    private void initComponent() {
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

    /**
     * Coloca los labels en el panel
     */
    private void colocarLabels() {

        ImageIcon imagen = new ImageIcon("logoDobble.png");
        JLabel etiq1 = new JLabel();
        etiq1.setBounds(10,5,80,80);
        etiq1.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH)));

        JLabel etiq2 = new JLabel(dobbleGame.whoseTurnIsIt(),SwingConstants.CENTER);
        etiq2.setForeground(new Color(0, 0, 0));
        etiq2.setFont(new Font("roboto",Font.BOLD,15));
        etiq2.setBounds(150,10,300,20);

        JLabel etiq3 = new JLabel("Cartas en mesa",SwingConstants.CENTER);
        etiq3.setForeground(new Color(0, 0, 0));
        etiq3.setFont(new Font("roboto",Font.PLAIN,14));
        etiq3.setBounds(150,35,300,20);
        if(dobbleGame.getMode().equals("User vs User")){
            JLabel etiq4 = new JLabel("Ingrese el elemento en comun:");
            etiq4.setForeground(new Color(0, 0, 0));
            etiq4.setFont(new Font("roboto",Font.PLAIN,14));
            etiq4.setBounds(200,100,220,20);
            panel.add(etiq4);
        }

        panel.add(etiq1);
        panel.add(etiq2);
        panel.add(etiq3);
    }

    /**
     * Coloca los paneles en el frame
     */
    private void colocarPaneles(){
        //Panel Principal
        panel = new JPanel();
        panel.setBackground(new Color(180, 48, 159));
        this.getContentPane().add(panel);
        panel.setLayout(null);
    }

    /**
     * Coloca las areas de texto en el panel
     */
    private void colocarAreaDeTexto(){
        if( dobbleGame.getDobble().numCards() >1){
            dobbleGame.ponerCartasEnMesa();
        }

        JTextArea txt1 = new JTextArea();
        txt1.setText(dobbleGame.getCardsMesa().get(0).toString2());
        txt1.setFont(new Font("roboto",Font.BOLD,13));
        txt1.setBounds(130,60,100,150);
        txt1.setOpaque(false);
        txt1.setEditable(false);

        JTextArea txt2 = new JTextArea();
        txt2.setText(dobbleGame.getCardsMesa().get(1).toString2());
        txt2.setFont(new Font("roboto",Font.BOLD,13));
        txt2.setBounds(400,60,100,150);
        txt2.setOpaque(false);
        txt2.setEditable(false);

        JTextArea txt3 = new JTextArea();
        txt3.setEditable(false);
        txt3.setOpaque(false);
        txt3.setText("Quedan "+ dobbleGame.getDobble().numCards()+ " en el mazo");
        txt3.setFont(new Font("roboto",Font.PLAIN,13));
        txt3.setBounds(240,70,150,20);
        
        

        if(dobbleGame.getMode().equals("Demo Mode")){
            JTextArea txt4 = new JTextArea();
            txt4.setText(dobbleGame.getElementSelected());
            txt4.setOpaque(false);
            txt4.setBounds(200,100,200,60);

            JTextArea txt5 = new JTextArea();
            txt5.setText(dobbleGame.getPlayers().get(dobbleGame.getTurnoPlayer() - 1).toString());
            txt5.setEditable(false);
            txt5.setBounds(130, 215, 260, 400);
            txt5.setOpaque(false);
            JScrollPane scroll = new JScrollPane(txt5);
            scroll.setBounds(130, 220, 320, 280);
            scroll.setFont(new Font("roboto", Font.PLAIN, 13));
            scroll.setForeground(new Color(0, 0, 0));
            scroll.setEnabled(false);
            scroll.setOpaque(false);
            panel.add(scroll);
            panel.add(txt4);
        } else if (dobbleGame.getMode().equals("User vs User")) {
            elementoUser = new JTextField();
            elementoUser.setBounds(240,130,100,20);
            elementoUser.setOpaque(false);
            panel.add(elementoUser);
        }
        panel.add(txt1);
        panel.add(txt2);
        panel.add(txt3);
    }

    /**
     * Coloca los botones en el panel
     */
    private void colocarBotones() {
        if(dobbleGame.getMode().equals("Demo Mode")){
            // Boton crear juego predeterminado
            JButton btnJugarJuego1 = new JButton("Siguiente Jugada");
            btnJugarJuego1.setBounds(115, 510, 135, 25);
            ActionListener accionBtnJugar1 = e -> {
                dobbleGame.play(0, "");
                if (dobbleGame.getDobble().getCardsSet().size() > 1) {
                    dispose();
                    new JugarJuego(dobbleGame, userName).setVisible(true);
                } else {
                    Final f = new Final(dobbleGame.getGanador());
                    setVisible(false);
                    f.setVisible(true);
                }

            };
            btnJugarJuego1.addActionListener(accionBtnJugar1);

            JButton btnJugarJuego2 = new JButton("Visualizar juego");
            btnJugarJuego2.setBounds(255, 510, 125, 25);
            ActionListener accionBtnJugarJuego3 = e -> new StatusGame(dobbleGame).setVisible(true);
            btnJugarJuego2.addActionListener(accionBtnJugarJuego3);

            // Boton para Salir
            JButton btnSalir = new JButton("Finalizar");
            btnSalir.setBounds(390, 510, 90, 25);
            ActionListener accionBtnSalir = e -> {
                dobbleGame.play(1, "");
                new Final(dobbleGame.getGanador()).setVisible(true);
                dispose();
            };
            btnSalir.addActionListener(accionBtnSalir);

            panel.add(btnJugarJuego1);
            panel.add(btnJugarJuego2);
            panel.add(btnSalir);
        } else if (dobbleGame.getMode().equals("User vs User")) {
            JButton btnJugar1 = new JButton("Jugar");
            btnJugar1.setBounds(140, 230, 135, 25);
            ActionListener accionBtnJugar1 = e -> {

                dobbleGame.play(1,elementoUser.getText());
                if(dobbleGame.getError() == 3){
                    JOptionPane.showMessageDialog(null,"Es un elemento comun entre las cartas","Dobble Game",
                            JOptionPane.INFORMATION_MESSAGE);
                } else if (dobbleGame.getError() == 4) {
                    JOptionPane.showMessageDialog(null,"No es un elemento comun entre las cartas","Dobble Game",
                            JOptionPane.ERROR_MESSAGE);
                }
                if(dobbleGame.getDobble().numCards() > 1) {
                    new JugarJuego(dobbleGame, userName).setVisible(true);
                    dispose();
                }else{
                    dobbleGame.play(3,"");
                    new Final(dobbleGame.getGanador()).setVisible(true);
                    dispose();
                }
            };
            btnJugar1.addActionListener(accionBtnJugar1);

            JButton btnJugar2 = new JButton("Pasar");
            btnJugar2.setBounds(300, 230, 135, 25);
            ActionListener accionBtnJugar2 = e -> {
                dobbleGame.play(2,"");
                JOptionPane.showMessageDialog(null,"Ha pasado el turno","Dobble Game",
                        JOptionPane.INFORMATION_MESSAGE);
                new JugarJuego(dobbleGame,userName).setVisible(true);
                dispose();
            };
            btnJugar2.addActionListener(accionBtnJugar2);

            JButton btnJugar3 = new JButton("Visualizar juego");
            btnJugar3.setBounds(140, 265, 135, 25);
            ActionListener accionBtnJugar3 = e -> new StatusGame(dobbleGame).setVisible(true);
            btnJugar3.addActionListener(accionBtnJugar3);

            JButton btnJugar4 = new JButton("Finalizar");
            btnJugar4.setBounds(300, 265, 135, 25);
            ActionListener accionBtnJugar4 = e -> {
                dobbleGame.play(3,"");
                new Final(dobbleGame.getGanador()).setVisible(true);
                dispose();
            };
            btnJugar4.addActionListener(accionBtnJugar4);

            panel.add(btnJugar1);
            panel.add(btnJugar2);
            panel.add(btnJugar3);
            panel.add(btnJugar4);
        }

        // Boton para volver al menu anterior
        ImageIcon imagen2 = new ImageIcon("back.png");
        JButton btnBack = new JButton();
        btnBack.setBounds(450, 10, 30, 30);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        ActionListener AccionVolver = e -> {
            setVisible(false);
            CrearMazo lastF = new CrearMazo(userName, dobbleGame.getMode());
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
        panel.add(btnBack);
    }
}
