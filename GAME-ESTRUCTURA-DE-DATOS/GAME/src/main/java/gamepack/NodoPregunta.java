/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamepack;

/**
 *
 * @author Fabia
 */

public class NodoPregunta {
    String codigo;
    String nombre;
    String respuesta;
    NodoPregunta siguiente;

    public NodoPregunta(String codigo, String nombre, String respuesta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.respuesta = respuesta;
        this.siguiente = null;
    }
}
