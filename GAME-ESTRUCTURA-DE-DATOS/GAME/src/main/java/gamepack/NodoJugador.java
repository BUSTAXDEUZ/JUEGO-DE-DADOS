
package gamepack;

/**
 *
 * @author Fabia
 */

/**
 * Nodo para la Cola de Jugadores.
 */

public class NodoJugador {
    Jugador jugador;
    NodoJugador siguiente;

    public NodoJugador(Jugador jugador) {
        this.jugador = jugador;
        this.siguiente = null;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public NodoJugador getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoJugador siguiente) {
        this.siguiente = siguiente;
    }
}
