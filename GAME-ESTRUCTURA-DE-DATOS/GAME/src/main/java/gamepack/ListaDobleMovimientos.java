
package gamepack;

/**
 * Lista doblemente enlazada para almacenar los movimientos de un jugador.
 * Cada nodo representa una posición alcanzada en el tablero.
 * 
 * @author Bustax, Chelo, Bryan
 */
public class ListaDobleMovimientos {
    private NodoDoble cabeza;
    private NodoDoble ultimo;
    private NodoDoble actual; // Nodo actual para navegar en el historial

    /**
     * Constructor que inicializa la lista vacía.
     */
    public ListaDobleMovimientos() {
        this.cabeza = null;
        this.ultimo = null;
        this.actual = null;
    }

    /**
     * Agrega un nuevo movimiento al historial del jugador.
     * @param posicion La nueva posición alcanzada en el tablero.
     * @param descripcion Información sobre el movimiento (premio/castigo).
     */
    public void agregarMovimiento(int posicion, String descripcion) {
        NodoDoble nuevo = new NodoDoble(posicion, descripcion);
        if (cabeza == null) {
            cabeza = nuevo;
            ultimo = nuevo;
            actual = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
        }
    }

    /**
     * Muestra el historial de movimientos del jugador.
     * Permite navegar hacia atrás y adelante en la lista.
     */
    public void verHistorial() {
        if (cabeza == null) {
            javax.swing.JOptionPane.showMessageDialog(null, "No hay movimientos en el historial.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("📜 HISTORIAL DE MOVIMIENTOS 📜\n\n");

        NodoDoble temp = cabeza;
        while (temp != null) {
            mensaje.append("Posición: ").append(temp.getPosicion())
                   .append(" - ").append(temp.getDescripcion()).append("\n");
            temp = temp.getSiguiente();
        }

        javax.swing.JOptionPane.showMessageDialog(null, mensaje.toString(), "Historial", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
}
