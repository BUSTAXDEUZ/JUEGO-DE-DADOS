/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamepack;

/**
 *
 * @author Fabia
 */

/**
 * Implementación de una Cola específica para jugadores.
 */

public class ColaJugadores {
    private NodoJugador frente, finalCola;

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

        /**
     * Elimina un jugador de la cola.
     * @param nombre Nombre del jugador a eliminar.
     * @return true si el jugador fue eliminado, false si no se encontró.
     */
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
        /**
     * Devuelve los jugadores que están en una posición específica del tablero.
     * @param posicion La posición a verificar.
     * @return String con los nombres de los jugadores en esa posición.
     */
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

        /**
     * Busca un jugador en la cola por su nombre.
     * @param nombre Nombre del jugador a buscar.
     * @return Jugador si se encuentra, null si no existe.
     */
    public Jugador buscarJugador(String nombre) {
        NodoJugador actual = frente;
        while (actual != null) {
            if (actual.getJugador().getNombre().equalsIgnoreCase(nombre)) {
                return actual.getJugador();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}
