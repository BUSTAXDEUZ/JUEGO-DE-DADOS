
package gamepack;

/**
 *
 * @author Fabia
 */

/**
 * Implementación de una Cola específica para jugadores.
 */

public class ColaJugadores {
    public NodoJugador frente;
    private NodoJugador finalCola;

    public ColaJugadores() {
        this.frente = null;
        this.finalCola = null;
    }

    public void encolar(Jugador jugador) {
        NodoJugador nuevoNodo = new NodoJugador(jugador);
        if (finalCola != null) {
            finalCola.siguiente = nuevoNodo;
        }
        finalCola = nuevoNodo;
        if (frente == null) {
            frente = nuevoNodo;
        }
    }

    public int contarJugadores() {
        int contador = 0;
        NodoJugador actual = frente;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    public Jugador desencolar() {
        if (frente == null) {
            return null;
        }
        Jugador jugador = frente.jugador;
        frente = frente.siguiente;
        if (frente == null) {
            finalCola = null;
        }
        return jugador;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public boolean eliminarJugador(String nombre) {
        if (frente == null) {
            return false;
        }

        if (frente.getJugador().getNombre().equalsIgnoreCase(nombre)) {
            frente = frente.getSiguiente();
            return true;
        }

        NodoJugador actual = frente;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getJugador().getNombre().equalsIgnoreCase(nombre)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public String listarJugadores() {
        StringBuilder lista = new StringBuilder("Lista de Jugadores:\n");
        NodoJugador actual = frente;
        while (actual != null) {
            lista.append(actual.jugador.getNombre()).append(" - Posición: ").append(actual.jugador.getPosicion()).append("\n");
            actual = actual.siguiente;
        }
        return lista.toString();
    }

    public String[] obtenerNombresJugadores() {
        int cantidad = contarJugadores();
        if (cantidad == 0) {
            return new String[0];
        }

        String[] nombres = new String[cantidad];
        NodoJugador actual = frente;
        int i = 0;
        while (actual != null) {
            nombres[i++] = actual.jugador.getNombre();
            actual = actual.siguiente;
        }
        return nombres;
    }

    public String obtenerJugadoresEnPosicion(int posicion) {
        StringBuilder jugadoresEnPosicion = new StringBuilder();
        NodoJugador actual = frente;

        while (actual != null) {
            if (actual.jugador.getPosicion() == posicion) {
                jugadoresEnPosicion.append(actual.jugador.getNombre()).append(", ");
            }
            actual = actual.siguiente;
        }

        if (jugadoresEnPosicion.length() > 0) {
            jugadoresEnPosicion.setLength(jugadoresEnPosicion.length() - 2); // Eliminar última coma
        }

        return jugadoresEnPosicion.toString();
    }

    public Jugador getFrenteJugador() {
        return frente != null ? frente.getJugador() : null; // Método para acceder al jugador en frente
    }
}
