
package gamepack;

/**
 *
 * @author Fabia
 */

/**
 * Implementación de una lista doblemente enlazada circular para la bitácora de cada jugador.
 */
public class ListaDobleCircular {
    private NodoDoble cabeza;
    private NodoDoble ultimo;

    public ListaDobleCircular() {
        this.cabeza = null;
        this.ultimo = null;
    }

        /**
     * Inserta un nuevo nodo con la posición alcanzada en la lista doble circular.
     * @param posicion La posición alcanzada.
     */
    public void insertar(int posicion) {
        NodoDoble nuevo = new NodoDoble(posicion, "Sin descripción"); // Se agrega un valor por defecto

        if (cabeza == null) {
            cabeza = nuevo;
            ultimo = nuevo;
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(ultimo);
        } else {
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            ultimo = nuevo;
        }
    }

    /**
     * Imprime el historial de movimientos del jugador.
     * @return Historial en formato de cadena.
     */
        /**
     * Muestra la lista de posiciones registradas.
     */
    public void mostrarLista() {
        if (cabeza == null) {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay movimientos en el historial.");
            return;
        }

        StringBuilder historial = new StringBuilder("Historial de Movimientos:\n\n");
        NodoDoble actual = cabeza;

        do {
            historial.append("Posición: ").append(actual.getPosicion())  // Se reemplazó getDato() por getPosicion()
                    .append(" - ").append(actual.getDescripcion()).append("\n");
            actual = actual.getSiguiente();
        } while (actual != cabeza);

        javax.swing.JOptionPane.showMessageDialog(null, historial.toString(), "Historial", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
}
