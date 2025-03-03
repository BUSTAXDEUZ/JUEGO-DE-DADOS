/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamepack;

/**
 *
 * @author Fabia
 */

import javax.swing.JOptionPane;

public class Pila<T> {
    private Nodo<T> top;  

    public Pila() {
        top = null;
    }

    public void apilar(T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor);
        nuevoNodo.siguiente = top;
        top = nuevoNodo;
    }

    public T desapilar() {
        if (top == null) {
            JOptionPane.showMessageDialog(null, "La pila está vacía.");
            return null;
        }
        T valor = top.getValor();
        top = top.siguiente;
        return valor;
    }

    public boolean estaVacia() {
        return top == null;
    }
}


