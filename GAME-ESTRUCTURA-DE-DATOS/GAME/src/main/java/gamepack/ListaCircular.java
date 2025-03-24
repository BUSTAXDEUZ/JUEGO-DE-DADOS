package gamepack;

import javax.swing.JOptionPane;

/**
 * Implementación de una lista circular para representar el tablero del juego.
 * Contiene posiciones del 1 hasta la meta.
 * 
 * @author Bustax, Chelo, Bryan
 */
public class ListaCircular {
    private NodoDoble Primero;
    private NodoDoble Ultimo;
    private int Tamaño;  // Número de posiciones en el juego

    public ListaCircular() {
        this.Primero = null;
        this.Ultimo = null;
        this.Tamaño = 0;
    }

    public NodoDoble getPrimero() {
        return Primero;
    }

    public void setPrimero(NodoDoble Primero) {
        this.Primero = Primero;
    }

    public NodoDoble getUltimo() {
        return Ultimo;
    }

    public void setUltimo(NodoDoble Ultimo) {
        this.Ultimo = Ultimo;
    }

    public int getTamaño() {
        return Tamaño;
    }

    public void setTamaño(int Tamaño) {
        this.Tamaño = Tamaño;
    }

    public void agregarEstado(int posicion, String descripcion) {
        NodoDoble Nuevo = new NodoDoble(posicion, descripcion);
        if (Primero == null) {
            Primero = Nuevo;
            Ultimo = Nuevo;
            Primero.setSiguiente(Primero); // La lista circular se cierra en el primer nodo
            Primero.setAnterior(Primero);
        } else {
            Ultimo.setSiguiente(Nuevo);
            Nuevo.setAnterior(Ultimo);
            Nuevo.setSiguiente(Primero); // Se cierra la lista circular
            Primero.setAnterior(Nuevo);
            Ultimo = Nuevo;
        }
        Tamaño++;
    }

    /**
     * Imprime el estado actual del juego con colores para las posiciones.
     */
    public void mostrarEstadoJuego() {
    if (Primero == null) {
        JOptionPane.showMessageDialog(null, "El juego aún no tiene estados.");
        return;
    }
    
    NodoDoble actual = Primero;
    int posicionActual = 1;

    // Calcular los límites para los colores
    int limiteVerde = (int) (Tamaño * 0.4);
    int limiteAmarillo = (int) (Tamaño * 0.8);
    
    while (posicionActual <= Tamaño) {
        String mensaje = "Posición " + actual.getPosicion() + ": ";
        String color = "";

        // Colorear según el rango con emojis
        if (posicionActual <= limiteVerde) {
            color = "🟢"; // Verde
        } else if (posicionActual <= limiteAmarillo) {
            color = "🟡"; // Amarillo
        } else {
            color = "🔴"; // Rojo
        }

        // Imprimir información del jugador y premio/castigo
        if (actual.getDescripcion().equals("VACIA")) {
            mensaje += "VACIA";
        } else {
            mensaje += actual.getDescripcion(); // Aquí va el jugador, premio/castigo
        }

        // Mostrar el mensaje con el color correspondiente
        System.out.println(color + " " + mensaje);

        actual = actual.getSiguiente();  // Moverse al siguiente nodo
        posicionActual++;
    }
  } 
}