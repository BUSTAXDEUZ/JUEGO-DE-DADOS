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

    public void mostrarMenu() {
        String pregunta;

        do {
            pregunta = JOptionPane.showInputDialog(null, 
                "Hazme una pregunta sobre el juego (ej. participar, par, impar, ganar) (escribe 'salir' para terminar):",
                "Chatbot - Juego de Dados",
                JOptionPane.QUESTION_MESSAGE);

            if (pregunta == null || pregunta.equalsIgnoreCase("salir")) {
                break;
            }

            String respuesta = responderPregunta(pregunta);
            JOptionPane.showMessageDialog(null, respuesta, "Chatbot", JOptionPane.INFORMATION_MESSAGE);
        } while (true);
    }

    private String responderPregunta(String pregunta) {
        pregunta = pregunta.toLowerCase();

        if (pregunta.contains("jugadores") || pregunta.contains("participar")) {
            return "Puedes jugar con un mínimo de 2 y un máximo de 4 jugadores.";
        } else if (pregunta.contains("par") || pregunta.contains("premio")) {
            return "Si sacás un número par con los dados, recibís un premio.";
        } else if (pregunta.contains("impar") || pregunta.contains("castigo")) {
            return "Si el número es impar, el jugador recibe un castigo.";
        } else if (pregunta.contains("ganar") || pregunta.contains("meta")) {
            return "El primer jugador en llegar a la meta gana el juego.";
        } else {
            return "Lo siento, no entiendo esa pregunta. Intenta con otra relacionada al juego.";
        }
    }
}
