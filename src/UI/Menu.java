package UI;

import Model.DobbleGame;
import java.util.Scanner;
/**
 *
 *
 */
public class Menu {
    //          ATRIBUTOS           //
    Scanner inputInt = new Scanner(System.in);
    Scanner inputStr = new Scanner(System.in);
    private String menuInicial;
    /**
     *
     */
    public Menu(){
        menuInicial = "Bienvenido al juego Dobble\n";
    }
    /**
     * String con el menu del inicio
     * @return String con el menu de inicio
     */
    public String menuInicio(){
        return """
                Escoja su opcion:
                1. Crear una partida de Dobble
                2. Salir
                """;
    }

    /**
     * String con el menu del juego antes de crearse
     * @return String con el menu del juego antes de crearse
     */
    public String menuGame() {
        return """
                Escoja su opcion:
                1. Registrar jugador
                2. Jugar
                3. Visualizar estado completo del juego
                4. Salir
                """;
    }

    /**
     * String con el menu para seleccionar el modo de juego
     * @return String con el menu para seleccionar el modo de juego
     */
    public String menuCrearGame(){
        return """
                Escoja el modo:
                1. Stack Mode (modo que pueden jugar entre jugadores)
                2. Demo Mode (modo de demostracion para la maquina)
                """;
    }
    /**
     * String con el menu del juego en progreso
     * @return String con el menu del juego en progreso
     */
    public String menuJuegoProgreso(){
        return """
                Escoja su opcion:
                1. Jugada
                2. Pasar
                3. Finalizar (Entrega el ganador hasta ese momento)
                4. Estado Del Juego
                """;
    }

    /**
     * Funcion que ejecuta el menu
     */
    public void run(){
        System.out.println(menuInicial + "Para poder jugar ingrese un usuari"
                + "o a registrar");
        String jugador = inputStr.nextLine();

        int opcion1 = 0;

        while(opcion1 != 2){
            System.out.println(new Menu().menuInicio());
            opcion1 = inputInt.nextInt();
            if(opcion1 == 1){
                System.out.println(new Menu().menuCrearGame());
                int opcion2 = 0;
                while((opcion2 != 1)&&(opcion2 != 2)){
                    opcion2 = inputInt.nextInt();
                    if (opcion2 == 1){
                        String modo = "stackMode";
                        System.out.println("Ingrese la Cantidad de Jugadores para el juego");
                        int cantJugadores = inputInt.nextInt();
                        System.out.println("Ingrese la cantidad de cartas para el mazo\n"
                                + "Ingrese un 0 o un numero negativo si desea generar el maximo de cartas posibles");
                        int cantCartas = inputInt.nextInt();
                        DobbleGame game = new DobbleGame(cantJugadores,cantCartas,modo);
                        game.register(jugador);
                        int opcion3 = 0;
                        while(opcion3 != 4){
                            System.out.println(new Menu().menuGame());
                            opcion3 = inputInt.nextInt();
                            switch (opcion3) {
                                case 1:
                                    System.out.println("Ingrese el nombre del nuevo jugador");
                                    jugador = inputStr.nextLine();
                                    game.register(jugador);
                                    break;
                                case 2:
                                    if(game.getPlayers().size() == game.getCantPlayers()){
                                        int opcion4 = 0;
                                        while(opcion4 != 3){
                                            if(game.getDobble().numCards() > 1){
                                                System.out.println("\n" + game.whoseTurnIsIt());
                                                if(game.getCardsMesa().isEmpty()){
                                                    int x = game.getDobble().numCards();
                                                    System.out.println("Cartas en mesa");
                                                    System.out.println(game.getDobble().nthCard(x-1));
                                                    System.out.println(game.getDobble().nthCard(x-2));
                                                    game.getCardsMesa().add(game.getDobble().nthCard(x-1));
                                                    game.getCardsMesa().add(game.getDobble().nthCard(x-2));
                                                    game.getDobble().deleteCard(x);
                                                    game.getDobble().deleteCard(x-1);
                                                }else{
                                                    System.out.println(game.getCardsMesa().get(0));
                                                    System.out.println(game.getCardsMesa().get(1));
                                                }

                                                System.out.println(new Menu().menuJuegoProgreso());
                                                opcion4 = inputInt.nextInt();
                                                if(opcion4 != 4){
                                                    game.play(opcion4);
                                                }
                                            }else{
                                                opcion4 = 3;
                                            }
                                            if(opcion4 == 3){
                                                System.out.println("Gracias por jugar\n");
                                                opcion3 = 4;
                                                opcion1 = 2;

                                            }else if(opcion4 == 4){
                                                System.out.println(game.toString());
                                            }

                                        }
                                    }else{
                                        System.out.println("Por favor registre "
                                                + "los jugadores del juego ante"
                                                + "s de comenzar\nJugadores reg"
                                                + "istrados: "
                                                + game.getPlayers().size()
                                                + "/"
                                                + game.getCantPlayers());
                                    }
                                    break;
                                case 3:
                                    System.out.println(game.toString());
                                    break;
                                case 4:
                                    System.out.println("Gracias por jugar\n");
                                    opcion1 = 2;
                                    break;
                                default:
                                    System.out.println("Ingrese opcion valida\n");
                                    break;
                            }
                        }



                    }else if (opcion2 == 2){
                        String modo = "demoMode";
                        System.out.println("Ingrese la cantidad de cartas para "
                                + "el mazo\nIngrese un 0 o un numero negativo s"
                                + "i desea generar el maximo de cartas posibles"
                                + "");
                        int cantCartas = inputInt.nextInt();
                        DobbleGame game = new DobbleGame(2,cantCartas,modo);
                        game.register("CPU 1");
                        game.register("CPU 2");
                        game.play(1);
                    }else{
                        System.out.println("Ingrese opcion valida\n");
                    }
                }
                opcion1 = 2;
            }else if (opcion1 == 2){
                System.out.println("Gracias por jugar\n");
            }else{
                System.out.println("Ingrese opcion valida\n");
            }

        }
    }

}

