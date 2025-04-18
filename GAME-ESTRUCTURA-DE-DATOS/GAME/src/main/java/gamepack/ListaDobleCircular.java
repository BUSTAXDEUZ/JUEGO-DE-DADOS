
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

    public void mostrarLista() {
        if (cabeza == null) {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay movimientos en el historial.");
            return;
        }

        StringBuilder historial = new StringBuilder("Historial de Movimientos:\n\n");
        NodoDoble actual = cabeza;

        do {
            historial.append("Posición: ").append(actual.getPosicion()) // Se reemplazó getDato() por getPosicion()
                    .append(" - ").append(actual.getDescripcion()).append("\n");
            actual = actual.getSiguiente();
        } while (actual != cabeza);

        javax.swing.JOptionPane.showMessageDialog(null, historial.toString(), "Historial", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
}
