
package gamepack;

/**
 * Clase que representa a un jugador en el juego.
 * 
 * @author Bustax, Chelo, Bryan
 */
public class Jugador {
    private String nombre;
    private int posicion;
    private ListaDobleMovimientos historial;
    private ListaCircular Estado;

    /**
     * Constructor del jugador.
     * @param nombre Nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.posicion = 1; // Inicia en la posición 1
        this.historial = new ListaDobleMovimientos();
        this.Estado = new ListaCircular();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int nuevaPosicion) {
        this.posicion = nuevaPosicion;
        historial.agregarMovimiento(nuevaPosicion, "Movimiento del carro");
    }

    /**
     * Muestra el historial de movimientos del jugador.
     */
    public void verHistorial() {
        historial.verHistorial();
        
    }
    
    public void verEstado() {
        Estado.mostrarEstadoJuego();  
    }
    
}
