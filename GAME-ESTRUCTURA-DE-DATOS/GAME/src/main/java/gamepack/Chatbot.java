/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamepack;

/**
 *
 * @author Fabia
 */
import javax.swing.JOptionPane;

public class Chatbot {
    private Arbolbinario arbol;

    public Chatbot() {
        arbol = new Arbolbinario();
        inicializarArbol();
    }

    public void mostrarMenu() {
        String[] opciones = {"Ver Chatbot", "Mantenimiento del Chatbot", "Salir"};
        int opcion;

        do {
            opcion = JOptionPane.showOptionDialog(null, "Menú Principal", "Chatbot de Preguntas Frecuentes",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    verChatbot();
                    break;
                case 1:
                    mantenimientoChatbot();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el Chatbot. ¡Hasta luego!");
                    break;
            }
        } while (opcion != 2);
    }

    private void verChatbot() {
        // Implementar la lógica para mostrar el árbol de preguntas
        // y permitir al usuario navegar por él.
    }

    private void mantenimientoChatbot() {
        // Implementar la lógica para el mantenimiento del chatbot
        // (agregar/modificar preguntas).
    }

    private void inicializarArbol() {
        // Inicializar el árbol con las preguntas precargadas.
        arbol.insertar("1", "Preguntas Frecuentes (FAQ)");
        arbol.insertar("11", "Preguntas para jugadores");
        arbol.insertar("111", "Primera vez que juego");
        arbol.insertar("1111", "Soy nuevo en videojuegos", "1. ¿Cuántos Jugadores pueden participar simultáneamente? Se tiene un máximo de 4 jugadores.");
        arbol.insertar("1112", "Ya he jugado otros juegos similares", "1. ¿Puedo jugar en línea? No, en la versión liberada no se permite jugar en línea.");
        arbol.insertar("112", "Jugador Experimentado", "No tiene. Las preguntas estarán en el nivel anterior.");
        arbol.insertar("12", "Preguntas para Administradores");
        arbol.insertar("121", "Funcionalidades");
        arbol.insertar("1211", "Administrador preguntas");
        arbol.insertar("1212", "Mejorar Juego");
    }

    public static void main(String[] args) {
        Chatbot chatbot = new Chatbot();
        chatbot.mostrarMenu();
    }
}
