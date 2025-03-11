/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gamepack;

/**
 *
 * @author Fabia
 */

import javax.swing.JOptionPane;
import java.util.Random;

/**
 * Clase principal del juego de carreras.
 */
public class GAME {
    private static ColaJugadores jugadores = new ColaJugadores();
    private static PilaPremios premios = new PilaPremios();
    private static PilaPremios castigos = new PilaPremios();
    private static Random random = new Random();
    private static final String VERSION = "1.0.1";

    public static void main(String[] args) {
        inicializarPremiosYCastigos();
        mostrarMenu();
    }

    private static void inicializarPremiosYCastigos() {
        premios.push("+2");
        premios.push("+8");
        premios.push("+0");

        castigos.push("-3");
        castigos.push("-1");
        castigos.push("-5");
    }
    

    private static void mostrarMenu() {
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "JUEGO DE CARRERAS\n" +
                "1. Registrar jugadores\n" +
                "2. Iniciar juego\n" +
                "3. Listar jugadores\n" +
                "4. Listar premios/castigos\n" +
                "5. Mantener Pilas\n" +
                "6. Eliminar jugador\n" +
                "7. Ayuda\n" +
                "8. Salir\n" +
                "Elija una opción:"));

            switch (opcion) {
                case 1 -> registrarJugadores();
                case 2 -> jugar();
                case 3 -> listarJugadores();
                case 4 -> listarPremiosYCastigos();
                case 5 -> mantenerPilas();
                case 6 -> salirJugador();
                case 7 -> mostrarAyuda();
                case 8 -> JOptionPane.showMessageDialog(null, "Juego terminado.");
                default -> JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 8);
    }

    private static void registrarJugadores() {
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de jugadores (máximo 4):"));
        for (int i = 0; i < cantidad; i++) {
            String nombre = JOptionPane.showInputDialog("Nombre del jugador " + (i + 1) + ":");
            jugadores.encolar(new Jugador(nombre));
        }
    }

    private static void listarJugadores() {
        JOptionPane.showMessageDialog(null, jugadores.listarJugadores());
    }

    private static void listarPremiosYCastigos() {
        JOptionPane.showMessageDialog(null, premios.listar() + "\n" + castigos.listar());
    }

    private static void mantenerPilas() {
        JOptionPane.showMessageDialog(null, "Estado actual de las Pilas:\n" + "Premios: " + premios.listar() + "\nCastigos: " + castigos.listar());
    }

    private static void salirJugador() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del jugador que desea eliminar:");
        boolean eliminado = jugadores.eliminarJugador(nombre);
        if (eliminado) {
            JOptionPane.showMessageDialog(null, nombre + " ha salido del juego.");
        } else {
            JOptionPane.showMessageDialog(null, "Jugador no encontrado en la lista.");
        }
    }

    private static void jugar() {
        if (jugadores.estaVacia()) {
            JOptionPane.showMessageDialog(null, "No hay jugadores en la cola.");
            return;
        }

        Jugador jugador = jugadores.desencolar();
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        int total = dado1 + dado2;
        int nuevaPosicion = jugador.getPosicion() + total;

        JOptionPane.showMessageDialog(null, jugador.getNombre() + " sacó " + dado1 + " y " + dado2 + ".\n" +
                "Posición Actual: " + jugador.getPosicion() + " -> Nueva Posición: " + nuevaPosicion);

        jugador.setPosicion(nuevaPosicion);

        if (total % 2 == 0) {
            String premio = premios.pop();
            int valorPremio = Integer.parseInt(premio);
            jugador.setPosicion(jugador.getPosicion() + valorPremio);
            JOptionPane.showMessageDialog(null, "Obtuviste un número par, debes tomar un premio de la pila. \nPremio: " + premio +
                    "\nNueva Posición: " + jugador.getPosicion());
        } else {
            String castigo = castigos.pop();
            int valorCastigo = Integer.parseInt(castigo);
            jugador.setPosicion(jugador.getPosicion() + valorCastigo);
            JOptionPane.showMessageDialog(null, "Obtuviste un número impar, debes tomar un castigo de la pila. \nCastigo: " + castigo +
                    "\nNueva Posición: " + jugador.getPosicion());
        }

        jugadores.encolar(jugador);
    }

    private static void mostrarAyuda() {
        JOptionPane.showMessageDialog(null, "Juego de Carreras\nVersión: " + VERSION + "\nDesarrollado por: BUSTAX & CHELO & BRAYAN");
    }
}
