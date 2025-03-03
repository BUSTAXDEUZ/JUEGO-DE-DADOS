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

public class GAME {
    private static Cola<Jugador> jugadores = new Cola<>();
    private static Pila<String> premios = new Pila<>();
    private static Pila<String> castigos = new Pila<>();
    private static Random random = new Random();

    public static void main(String[] args) {
        inicializarPremiosYCastigos();
        mostrarMenu();
    }

    private static void inicializarPremiosYCastigos() {
        premios.apilar("+2");
        premios.apilar("+8");
        premios.apilar("+0");
        
        castigos.apilar("-3");
        castigos.apilar("=1");
        castigos.apilar("-5");
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
                "5. Salir\n" +
                "Elija una opción:"));

            switch (opcion) {
                case 1 -> registrarJugadores();
                case 2 -> jugar();
                case 3 -> listarJugadores();
                case 4 -> listarPremiosYCastigos();
                case 5 -> JOptionPane.showMessageDialog(null, "Juego terminado.");
                default -> JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 5);
    }

    private static void registrarJugadores() {
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de jugadores (máximo 4):"));
        for (int i = 0; i < cantidad; i++) {
            String nombre = JOptionPane.showInputDialog("Nombre del jugador " + (i + 1) + ":");
            jugadores.encolar(new Jugador(nombre));
        }
    }

    private static void listarJugadores() {
        if (jugadores.estaVacia()) {
            JOptionPane.showMessageDialog(null, "No hay jugadores registrados.");
            return;
        }

        StringBuilder lista = new StringBuilder("Lista de Jugadores:\n");
        Cola<Jugador> temp = new Cola<>();

        while (!jugadores.estaVacia()) {
            Jugador jugador = jugadores.desencolar();
            lista.append(jugador.getNombre()).append("\n");
            temp.encolar(jugador);
        }

        while (!temp.estaVacia()) {
            jugadores.encolar(temp.desencolar());
        }

        JOptionPane.showMessageDialog(null, lista.toString());
    }

    private static void listarPremiosYCastigos() {
        StringBuilder lista = new StringBuilder("Premios:\n");
        Pila<String> tempPremios = new Pila<>();
        while (!premios.estaVacia()) {
            String premio = premios.desapilar();
            lista.append(premio).append("\n");
            tempPremios.apilar(premio);
        }
        while (!tempPremios.estaVacia()) {
            premios.apilar(tempPremios.desapilar());
        }

        lista.append("\nCastigos:\n");
        Pila<String> tempCastigos = new Pila<>();
        while (!castigos.estaVacia()) {
            String castigo = castigos.desapilar();
            lista.append(castigo).append("\n");
            tempCastigos.apilar(castigo);
        }
        while (!tempCastigos.estaVacia()) {
            castigos.apilar(tempCastigos.desapilar());
        }

        JOptionPane.showMessageDialog(null, lista.toString());
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

    
        JOptionPane.showMessageDialog(null, jugador + " sacó " + dado1 + " y " + dado2 + ". Posicion Actual: " + total);

        if (total % 2 == 0) {
            JOptionPane.showMessageDialog(null, "Obtuviste un número par, debes tomar un premio de la pila. " + "Suerte!!");
            JOptionPane.showMessageDialog(null, "Premio Avanza: " + premios.desapilar() + " Casillas.");
            JOptionPane.showMessageDialog(null, "Pocicion Actual: " + total);
        } else {
            JOptionPane.showMessageDialog(null, "Obtuviste un número impar, debes tomar un castigo de la pila. " + "Mejor suerte la próxima vez!!");
            JOptionPane.showMessageDialog(null, "Castigo Retrocede: " + castigos.desapilar() + " Casillas.");
            JOptionPane.showMessageDialog(null, "Pocicion Actual: " + total);
        }

        jugadores.encolar(jugador);
    }
}
