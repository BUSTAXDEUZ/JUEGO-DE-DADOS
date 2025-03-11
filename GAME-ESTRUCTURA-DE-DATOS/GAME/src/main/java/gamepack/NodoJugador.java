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
 * Nodo para la Cola de Jugadores.
 */
public class NodoJugador {
    Jugador jugador;
    NodoJugador siguiente;

    public NodoJugador(Jugador jugador) {
        this.jugador = jugador;
        this.siguiente = null;
    }
}

