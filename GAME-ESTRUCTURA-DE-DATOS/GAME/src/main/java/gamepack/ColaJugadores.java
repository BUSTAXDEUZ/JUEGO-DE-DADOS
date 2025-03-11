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
        if (frente == null) return false;

        if (frente.jugador.getNombre().equals(nombre)) {
            frente = frente.siguiente;
            return true;
        }

        NodoJugador actual = frente;
        while (actual.siguiente != null && !actual.siguiente.jugador.getNombre().equals(nombre)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            return true;
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
}
