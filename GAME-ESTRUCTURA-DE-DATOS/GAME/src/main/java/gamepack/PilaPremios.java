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
 * Implementación de una pila para manejar premios y castigos.
 */
public class PilaPremios {
    private NodoPremio top;

    public PilaPremios() {
        top = null;
    }

    public void push(String valor) {
        NodoPremio nuevoNodo = new NodoPremio(valor);
        nuevoNodo.siguiente = top;
        top = nuevoNodo;
    }

    public String pop() {
        if (top == null) {
            return "0";
        }
        String valor = top.valor;
        top = top.siguiente;
        return valor;
    }

    public String listar() {
        StringBuilder lista = new StringBuilder("Elementos en la pila:\n");
        NodoPremio actual = top;
        while (actual != null) {
            lista.append(actual.valor).append("\n");
            actual = actual.siguiente;
        }
        return lista.toString();
    }
}
