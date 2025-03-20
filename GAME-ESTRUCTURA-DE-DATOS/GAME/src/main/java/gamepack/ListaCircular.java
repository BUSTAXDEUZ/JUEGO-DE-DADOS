/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamepack;

import javax.swing.JOptionPane;

/**
 * Implementación de una lista circular para representar el tablero del juego.
 * Contiene posiciones del 1 hasta la meta.
 * 
 * @author Bustax, Chelo, Bryan
 */
public class ListaCircular {
    private Nodo primero;
    private Nodo ultimo;

    public ListaCircular() {
        this.primero = null;
        this.ultimo = null;
    }

    public void insertar(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
            ultimo.setSiguiente(primero);
        } else {
            ultimo.setSiguiente(nuevo);
            nuevo.setSiguiente(primero);
            ultimo = nuevo;
        }
    }

    public void mostrarTableroConJugadores(ColaJugadores jugadores, int meta) {
        if (primero == null) {
            JOptionPane.showMessageDialog(null, "El tablero aún no ha sido configurado.");
            return;
        }

        StringBuilder tablero = new StringBuilder("ESTADO DEL TABLERO\n\n");
        Nodo actual = primero;
        do {
            int posicion = actual.getValor();
            String color = determinarZona(posicion, meta);
            tablero.append(color).append(" Posición ").append(posicion).append(": ");

            // Obtener jugadores en esta posición
            String jugadoresEnPosicion = jugadores.obtenerJugadoresEnPosicion(posicion);
            tablero.append(jugadoresEnPosicion.isEmpty() ? "VACIA" : jugadoresEnPosicion);
            tablero.append("\n");

            actual = actual.getSiguiente();
        } while (actual != primero);

        JOptionPane.showMessageDialog(null, tablero.toString(), "Estado del Tablero", JOptionPane.INFORMATION_MESSAGE);
    }

    private String determinarZona(int posicion, int meta) {
        double porcentaje = (double) posicion / meta * 100;
        if (porcentaje <= 40) {
            return "[🟢]";
        } else if (porcentaje <= 80) {
            return "[🟡]";
        } else {
            return "[🔴]";
        }
    }
}
