package gamepack;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *       
 * @author Fabia
 */
import javax.swing.JOptionPane;

public class Cola<T> {
    private Nodo<T> frente, finalCola;

    public Cola() {
        this.frente = null;
        this.finalCola = null;
    }

    public void encolar(T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor);
        if (finalCola != null) {
            finalCola.siguiente = nuevoNodo;
        }
        finalCola = nuevoNodo;
        if (frente == null) {
            frente = nuevoNodo;
        }
    }

    public T desencolar() {
        if (frente == null) {
            return null;
        }
        T valor = frente.getValor();
        frente = frente.siguiente;
        if (frente == null) {
            finalCola = null;
        }
        return valor;
    }

    public boolean estaVacia() {
        return frente == null;
    }
}
