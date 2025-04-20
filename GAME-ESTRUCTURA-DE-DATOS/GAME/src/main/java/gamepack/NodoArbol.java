/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamepack;

/**
 *
 * @author Fabia
 */

public class NodoArbol {
    String codigo;
    String nombre;
    ListaEnlazada preguntas; // Lista de preguntas
    NodoArbol izquierdo;
    NodoArbol derecho;

    public NodoArbol(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.preguntas = new ListaEnlazada();
        this.izquierdo = null;
        this.derecho = null;
    }
}
