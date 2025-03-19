/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gamepack;

import javax.swing.JOptionPane;
import java.util.Random;

/**
 * Clase principal del Juego de Dados.
 * Maneja jugadores, el lanzamiento de dados y la administración de premios y castigos.
 * 
 * @author Bryan, Bustax, Chelo
 * @version 1.1.0
 */
public class GAME {
    private static ColaJugadores jugadores = new ColaJugadores();
    private static ColaPremios premios = new ColaPremios();
    private static ColaPremios castigos = new ColaPremios();
    private static Random random = new Random();
    private static final String VERSION = "1.1.0";
    private static final int POSICION_FINAL = 100;

    /**
     * Método principal del programa.
     * Inicia el juego y gestiona el flujo principal.
     */
    public static void main(String[] args) {
        Object[] options = {"Jugar", "Salir"};
        int inicio = JOptionPane.showOptionDialog(null,
                "BIENVENIDO AL JUEGO DE DADOS \n¿Quieres jugar?",
                "Juego de Dados",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (inicio == 0) {
            inicializarPremiosYCastigos();
            seleccionarCantidadJugadores();
            mostrarMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Juego terminado. Hasta la próxima.");
        }
    }

    /**
     * Inicializa las pilas de premios y castigos con valores predefinidos.
     */
    private static void inicializarPremiosYCastigos() {
        premios.encolar("+2");
        premios.encolar("+8");
        premios.encolar("+0");

        castigos.encolar("-3");
        castigos.encolar("-1"); // -1 significa volver a la posición 1
        castigos.encolar("-5");
    }

    /**
     * Muestra el menú principal con las opciones disponibles.
     */
    private static void mostrarMenu() {
        Object[] opciones = {"Iniciar Juego", "Editar Jugadores", "Listar Premios/Castigos", 
                             "Mantener Pilas", "Ayuda", "Salir"};

        int opcion;
        do {
            String jugadoresEnSala = jugadores.estaVacia() ? "No hay jugadores en la sala." : jugadores.listarJugadores();

            opcion = JOptionPane.showOptionDialog(null,
                    "JUEGO DE DADOS - MENÚ PRINCIPAL\n" + jugadoresEnSala + "\nSeleccione una opción:",
                    "Menú de Juego",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0 -> jugar();
                case 1 -> editarJugadores();
                case 2 -> listarPremiosYCastigos();
                case 3 -> mantenerPilas();
                case 4 -> mostrarAyuda();
                case 5 -> JOptionPane.showMessageDialog(null, "Juego terminado.");
            }
        } while (opcion != 5);
    }

    /**
     * Permite seleccionar la cantidad de jugadores y registrar sus nombres.
     */
    private static void seleccionarCantidadJugadores() {
        Object[] opciones = {"1", "2", "3", "4"};
        int cantidad = JOptionPane.showOptionDialog(null,
                "Seleccione la cantidad de jugadores (máximo 4):",
                "Seleccionar jugadores",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        cantidad += 1;
        for (int i = 0; i < cantidad; i++) {
            String nombre = JOptionPane.showInputDialog("Nombre del jugador " + (i + 1) + ":");
            jugadores.encolar(new Jugador(nombre));
        }
    }

    /**
     * Controla la mecánica del juego, incluyendo tiradas de dados, premios y castigos.
     */
    private static void jugar() {
        boolean ganadorEncontrado = false;

        while (!ganadorEncontrado) {
            if (jugadores.estaVacia()) {
                JOptionPane.showMessageDialog(null, "No hay jugadores en la cola.");
                return;
            }

            Jugador jugador = jugadores.desencolar();
            int dado1 = random.nextInt(6) + 1;
            int dado2 = random.nextInt(6) + 1;
            int total = dado1 + dado2;
            int nuevaPosicion = jugador.getPosicion() + total;

            Object[] opciones = {"Continuar", "Volver al Menú"};
            int seleccion = JOptionPane.showOptionDialog(null,
                    jugador.getNombre() + " sacó " + dado1 + " y " + dado2 + ".\n" +
                    "Posición Actual: " + jugador.getPosicion() + " -> Nueva Posición: " + nuevaPosicion,
                    "Turno de " + jugador.getNombre(),
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            if (seleccion == 1) {
                jugadores.encolar(jugador);
                return;
            }

            jugador.setPosicion(nuevaPosicion);

            if (total % 2 == 0) {
    String premio = premios.desencolarYReutilizar();
    int valorPremio = Integer.parseInt(premio);
    jugador.setPosicion(jugador.getPosicion() + valorPremio);
    JOptionPane.showMessageDialog(null, "Premio: " + premio + "\nNueva Posición: " + jugador.getPosicion());
} else {
    String castigo = castigos.desencolarYReutilizar();

    if (castigo.equals("-1")) {
        jugador.setPosicion(1); // Si es "-1", vuelve a la posición 1
        JOptionPane.showMessageDialog(null, "Castigo especial: Regresas a la posición 1.");
    } else {
        int valorCastigo = Integer.parseInt(castigo);
        jugador.setPosicion(jugador.getPosicion() + valorCastigo);
        JOptionPane.showMessageDialog(null, "Castigo: " + castigo + "\nNueva Posición: " + jugador.getPosicion());
    }
}


            if (jugador.getPosicion() >= POSICION_FINAL) {
                JOptionPane.showMessageDialog(null, jugador.getNombre() + " ha llegado a la meta y ha ganado el juego.");
                ganadorEncontrado = true;
                break;
            }

            jugadores.encolar(jugador);
        }
    }

    /**
     * Muestra la lista de premios y castigos disponibles.
     */
    private static void listarPremiosYCastigos() {
        JOptionPane.showMessageDialog(null, 
                "Premios disponibles:\n" + premios.listar() + 
                "\nCastigos disponibles:\n" + castigos.listar(),
                "Premios y Castigos",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra el estado actual de las pilas de premios y castigos.
     */
    private static void mantenerPilas() {
        JOptionPane.showMessageDialog(null, 
                "Estado actual de las Pilas:\n" + 
                "Premios: " + premios.listar() + 
                "\nCastigos: " + castigos.listar(),
                "Estado de las Pilas",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra información sobre cómo jugar y los desarrolladores del juego.
     */
    private static void mostrarAyuda() {
        JOptionPane.showMessageDialog(null,
                "CÓMO JUGAR: \n" +
                "1. Seleccione la cantidad de jugadores (máximo 4).\n" +
                "2. Cada jugador lanza dos dados.\n" +
                "3. Se suma el resultado de los dados para avanzar en el tablero.\n" +
                "4. Si el número es par, recibe un premio.\n" +
                "5. Si el número es impar, recibe un castigo.\n" +
                "6. Si el castigo es -1, el jugador regresa a la posición 1.\n" +
                "7. El primer jugador en llegar a la meta gana.\n\n" +
                "DESARROLLADO POR:\n" +
                "Bryan, Bustax y Chelo\n" +
                "Versión: " + VERSION,
                "Ayuda - Juego de Dados",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
       /**
     * Permite editar la lista de jugadores en la sala (agregar o eliminar jugadores).
     */
    private static void editarJugadores() {
        int opcion;
        do {
            String jugadoresEnSala = jugadores.estaVacia() ? "No hay jugadores en sala." : jugadores.listarJugadores();

            opcion = JOptionPane.showOptionDialog(null,
                    "EDITAR JUGADORES EN SALA\n" + jugadoresEnSala + "\nSeleccione una opción:",
                    "Editar Jugadores",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new Object[]{"Agregar Jugador", "Eliminar Jugador", "Volver"},
                    "Agregar Jugador");

            switch (opcion) {
                case 0 -> agregarJugador();
                case 1 -> eliminarJugador();
                case 2 -> JOptionPane.showMessageDialog(null, "Volviendo al menú principal.");
            }
        } while (opcion != 2);
    }

    /**
     * Agrega un nuevo jugador a la lista si el límite no ha sido alcanzado.
     */
    private static void agregarJugador() {
        if (jugadores.contarJugadores() >= 4) {
            JOptionPane.showMessageDialog(null, "No se pueden agregar más jugadores. Máximo 4.");
            return;
        }
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo jugador:");
        jugadores.encolar(new Jugador(nombre));
    }

    /**
     * Permite eliminar un jugador de la lista seleccionándolo desde un menú de opciones.
     */
    private static void eliminarJugador() {
        if (jugadores.estaVacia()) {
            JOptionPane.showMessageDialog(null, "No hay jugadores en la lista.");
            return;
        }

        String[] nombres = jugadores.obtenerNombresJugadores();
        String jugadorSeleccionado = (String) JOptionPane.showInputDialog(
                null, 
                "Seleccione el jugador a eliminar:", 
                "Eliminar Jugador", 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                nombres, 
                nombres[0]);

        if (jugadorSeleccionado != null) {
            boolean eliminado = jugadores.eliminarJugador(jugadorSeleccionado);
            if (eliminado) {
                JOptionPane.showMessageDialog(null, jugadorSeleccionado + " ha sido eliminado.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el jugador.");
            }
        }
    }

}
