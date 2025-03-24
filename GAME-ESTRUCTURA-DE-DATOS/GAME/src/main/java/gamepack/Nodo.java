
package gamepack;

/**
 *
 * @author Fabia
 */

/**
 * Nodo para la lista circular del tablero.
 */
    public class Nodo {
        private int valor;
        private Nodo siguiente;

        public Nodo(int valor) {
            this.valor = valor;
            this.siguiente = null;
        }

        public int getValor() {
            return valor;  // Este método estaba faltando
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }

        public Nodo getSiguiente() {
            return siguiente;
        }
    }
