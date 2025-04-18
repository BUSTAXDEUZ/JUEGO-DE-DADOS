package gamepack;

import javax.swing.JOptionPane;

/**
 * Implementación de una lista circular para representar el tablero del juego.
 * Contiene posiciones del 1 hasta la meta.
 * 
 * @author Bustax, Chelo, Bryan
 */

import javax.swing.JOptionPane;

public class ListaCircular {
    private NodoCircular primero;
    private NodoCircular ultimo;

    public ListaCircular() {
        this.primero = null;
        this.ultimo = null;
    }

    public NodoCircular getPrimero() {
        return primero;
    }

    public void setPrimero(NodoCircular primero) {
        this.primero = primero;
    }

    public NodoCircular getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoCircular ultimo) {
        this.ultimo = ultimo;
    }

    public void agregarEstado(int posicion, String descripcion) {
        Jugador nuevoJugador = new Jugador(descripcion);
        nuevoJugador.setPosicion(posicion); // Establecer la posición del jugador
        NodoCircular nuevo = new NodoCircular(nuevoJugador);

        if (this.getPrimero() == null) {
            primero = nuevo;
            ultimo = nuevo;
            ultimo.setSiguiente(primero); // La lista circular se cierra en el primer nodo
        } else {
            if (posicion <= primero.getJugador().getPosicion()) {
                nuevo.setSiguiente(primero);
                primero = nuevo;
                ultimo.setSiguiente(primero);
            } else if (posicion > ultimo.getJugador().getPosicion()) {
                ultimo.setSiguiente(nuevo);
                ultimo = nuevo;
                ultimo.setSiguiente(primero);
            } else {
                NodoCircular aux = primero;
                while (aux.getSiguiente() != primero && aux.getSiguiente().getJugador().getPosicion() < posicion) {
                    aux = aux.getSiguiente();
                }
                nuevo.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(nuevo);
            }
        }
    }

    public void mostrarEstadoJuego() {
        if (this.getPrimero() == null) {
            JOptionPane.showMessageDialog(null, "El juego aún no tiene estados.");
            return;
        }

        NodoCircular actual = this.getPrimero();
        int posicionActual = 1;

        int limiteVerde = 8; // Primer 40% de las posiciones
        int limiteAmarillo = 16; // Segundo 40% de las posiciones

        StringBuilder mensaje = new StringBuilder("Estado Actual del Juego:\n");

        do {
            String color;

            if (actual.getJugador() != null) {
                int posicionJugador = actual.getJugador().getPosicion();

                if (posicionJugador <= limiteVerde) {
                    color = "🟢"; // Verde
                } else if (posicionJugador <= limiteAmarillo) {
                    color = "🟡"; // Amarillo
                } else {
                    color = "🔴"; // Rojo
                }

                mensaje.append(color).append(" Posición ").append(posicionJugador).append(": ").append(actual.getJugador().getNombre().replace("ó", "o")).append("\n");
            } else {
                mensaje.append("VACIA\n");
            }

            actual = actual.getSiguiente(); // Moverse al siguiente nodo
            posicionActual++;
        } while (actual != this.getPrimero()); // Continuar hasta volver al primero

        JOptionPane.showMessageDialog(null, mensaje.toString(), "Estado Actual del Juego", JOptionPane.INFORMATION_MESSAGE);
    }
}
