/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamepack;

/**
 *
 * @author Fabia
 */

public class NodoCastigo {
    String valor;
    NodoCastigo siguiente;

    public NodoCastigo(String valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public NodoCastigo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCastigo siguiente) {
        this.siguiente = siguiente;
    }
}
