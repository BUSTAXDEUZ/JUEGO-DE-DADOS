
package gamepack;

/**
 *
 * @author Fabia
 */

import javax.swing.JOptionPane;

/**
 * Implementación de una lista doblemente enlazada circular para la bitácora de cada jugador.
 */
public class ListaDobleCircular {
    private NodoDoble cabeza;
    private NodoDoble ultimo;

    public ListaDobleCircular() {
        this.cabeza = null;
        this.ultimo = null;
    }

    public void insertar(int posicion) {
        NodoDoble nuevo = new NodoDoble(posicion, "Sin descripción"); // Se agrega un valor por defecto

        if (cabeza == null) {
            cabeza = nuevo;
            ultimo = nuevo;
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(ultimo);
        } else {
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            ultimo = nuevo;
        }
    }

    public void mostrarLista(ColaJugadores jugadores) { // Aceptar la cola de jugadores como parámetro
        if (cabeza == null) {
            JOptionPane.showMessageDialog(null, "No hay movimientos en el historial.");
            return;
        }

        StringBuilder historial = new StringBuilder("Historial de Movimientos:\n\n");
        NodoDoble actual = cabeza;

        do {
            // Buscar el jugador que tiene esa posición
            Jugador jugador = buscarJugadorEnPosicion(actual.getPosicion(), jugadores);

            if (jugador != null) {
                historial.append("Posición: ").append(actual.getPosicion())
                        .append(" - ").append(jugador.getNombre()).append("\n");
            } else {
                historial.append("Posición: ").append(actual.getPosicion())
                        .append(" - Sin jugador asignado\n");
            }

            actual = actual.getSiguiente();
        } while (actual != cabeza);

        JOptionPane.showMessageDialog(null, historial.toString(), "Historial", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para buscar el jugador que tiene una posición específica
    private Jugador buscarJugadorEnPosicion(int posicion, ColaJugadores jugadores) {
        NodoJugador actual = jugadores.frente; // Acceder a la lista de jugadores
        while (actual != null) {
            if (actual.jugador.getPosicion() == posicion) {
                return actual.jugador;
            }
            actual = actual.siguiente;
        }
        return null; // Si no se encuentra el jugador, devuelve null
    }
}
