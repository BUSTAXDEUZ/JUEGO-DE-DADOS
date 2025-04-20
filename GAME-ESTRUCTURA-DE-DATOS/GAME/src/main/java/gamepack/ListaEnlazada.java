/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamepack;

/**
 *
 * @author Fabia
 */

public class ListaEnlazada {
    private NodoPregunta cabeza;

    public void agregarPregunta(String codigo, String nombre, String respuesta) {
        NodoPregunta nuevo = new NodoPregunta(codigo, nombre, respuesta);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoPregunta actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    // Métodos para imprimir preguntas
    public void imprimirPreguntas() {
        NodoPregunta actual = cabeza;
        while (actual != null) {
            System.out.println("Código: " + actual.codigo + ", Nombre: " + actual.nombre + ", Respuesta: " + actual.respuesta);
            actual = actual.siguiente;
        }
    }
}
