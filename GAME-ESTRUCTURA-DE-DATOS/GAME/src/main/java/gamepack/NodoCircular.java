
package gamepack;

/**
 *
 * @author marceloquevedo
 */

public class NodoCircular {
    private Jugador jugador; // Almacena la información del jugador
    private NodoCircular siguiente; // Referencia al siguiente nodo en la lista circular

    public NodoCircular(Jugador jugador) {
        this.jugador = jugador;
        this.siguiente = null; // Inicialmente, el siguiente nodo es null
    }

    public Jugador getJugador() {
        return jugador; // Devuelve el jugador almacenado en el nodo
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador; // Establece el jugador en el nodo
    }

    public NodoCircular getSiguiente() {
        return siguiente; // Devuelve el siguiente nodo
    }

    public void setSiguiente(NodoCircular siguiente) {
        this.siguiente = siguiente; // Establece el siguiente nodo
    }
}
