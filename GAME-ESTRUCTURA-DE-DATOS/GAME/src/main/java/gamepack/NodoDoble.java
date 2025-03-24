
package gamepack;

/**
 * Nodo doblemente enlazado para la lista de historial de movimientos.
 * 
 * @author Bustax, Chelo, Bryan
 */
public class NodoDoble {
    private int posicion;
    private String descripcion;
    private NodoDoble siguiente;
    private NodoDoble anterior;

    /**
     * Constructor del nodo doble.
     * @param posicion Posición alcanzada en el tablero.
     * @param descripcion Descripción del movimiento (premio/castigo).
     */
    public NodoDoble(int posicion, String descripcion) {
        this.posicion = posicion;
        this.descripcion = descripcion;
        this.siguiente = null;
        this.anterior = null;
    }

    public int getPosicion() {
        return posicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }
}


