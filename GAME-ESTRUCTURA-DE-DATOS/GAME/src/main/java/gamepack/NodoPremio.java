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
 * Nodo para la pila de premios y castigos.
 */
public class NodoPremio {
    String valor;
    NodoPremio siguiente;

    public NodoPremio(String valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public NodoPremio getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPremio siguiente) {
        this.siguiente = siguiente;
    }
    
    
}

