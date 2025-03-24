
package gamepack;

/**
 * Implementación de una Cola para manejar premios y castigos (FIFO).
 */
public class ColaPremios {
    private NodoPremio frente, finalCola;

    /**
     * Constructor que inicializa la cola vacía.
     */
    public ColaPremios() {
        this.frente = null;
        this.finalCola = null;
    }

    /**
     * Agrega un nuevo premio o castigo a la cola.
     * @param valor Premio o castigo a encolar.
     */
    public void encolar(String valor) {
        NodoPremio nuevoNodo = new NodoPremio(valor);
        if (finalCola != null) {
            finalCola.siguiente = nuevoNodo;
        }
        finalCola = nuevoNodo;
        if (frente == null) {
            frente = nuevoNodo;
        }
    }

    /**
     * Extrae el primer elemento de la cola y lo reinserta al final.
     * @return Premio o castigo en la parte frontal de la cola.
     */
    public String desencolarYReutilizar() {
        if (frente == null) {
            return "0";  // Si la cola está vacía, retorna "0"
        }
        String valor = frente.valor;
        frente = frente.siguiente;
        if (frente == null) {
            finalCola = null;
        }
        encolar(valor); // Reinsertar al final para su reutilización
        return valor;
    }

    /**
     * Devuelve una representación en cadena de los elementos de la cola.
     * @return Lista de premios o castigos.
     */
    public String listar() {
        StringBuilder lista = new StringBuilder("Elementos en la cola:\n");
        NodoPremio actual = frente;
        while (actual != null) {
            lista.append(actual.valor).append("\n");
            actual = actual.siguiente;
        }
        return lista.toString();
    }
}

