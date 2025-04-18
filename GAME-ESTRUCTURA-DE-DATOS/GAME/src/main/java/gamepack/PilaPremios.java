/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamepack;

/**
 * pila premios donde se manejan los premios
 * 
 * @author Bryan, Bustax, Chelo
 * @version 2.1.8
 */

public class PilaPremios {
    private NodoPremio tope;

    public PilaPremios() {
        this.tope = null;
    }

    public void apilar(String valor) {
        NodoPremio nuevoNodo = new NodoPremio(valor);
        if (tope != null) {
            nuevoNodo.siguiente = tope;
        }
        tope = nuevoNodo;
    }

    public String desapilar() {
        if (tope == null) {
            return "0"; // Si la pila está vacía, retorna "0"
        }
        String valor = tope.valor;
        tope = tope.siguiente;
        return valor;
    }

    public String listar() {
        StringBuilder lista = new StringBuilder("Elementos en la pila:\n");
        NodoPremio actual = tope;
        while (actual != null) {
            lista.append(actual.valor).append("\n");
            actual = actual.siguiente;
        }
        return lista.toString();
    }
}
