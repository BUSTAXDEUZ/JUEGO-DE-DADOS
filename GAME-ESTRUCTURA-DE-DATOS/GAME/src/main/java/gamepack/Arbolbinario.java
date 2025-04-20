/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamepack;

/**
 *
 * @author Fabia
 */

public class Arbolbinario {
    private NodoArbol raiz;

    public void insertar(String codigo, String nombre) {
        raiz = insertarRecursivo(raiz, codigo, nombre);
    }

    private NodoArbol insertarRecursivo(NodoArbol nodo, String codigo, String nombre) {
        if (nodo == null) {
            return new NodoArbol(codigo, nombre);
        }

        if (codigo.compareTo(nodo.codigo) < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, codigo, nombre);
        } else if (codigo.compareTo(nodo.codigo) > 0) {
            nodo.derecho = insertarRecursivo(nodo.derecho, codigo, nombre);
        }

        return nodo;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void insertar(String codigo, String nombre, String pregunta) {
        // Lógica para insertar preguntas en nodos hoja
        NodoArbol nodo = buscarNodo(raiz, codigo);
        if (nodo != null) {
            nodo.preguntas.agregarPregunta(codigo, nombre, pregunta);
        }
    }

    private NodoArbol buscarNodo(NodoArbol nodo, String codigo) {
        if (nodo == null) {
            return null;
        }
        if (nodo.codigo.equals(codigo)) {
            return nodo;
        }
        NodoArbol encontrado = buscarNodo(nodo.izquierdo, codigo);
        if (encontrado == null) {
            encontrado = buscarNodo(nodo.derecho, codigo);
        }
        return encontrado;
    }
}
