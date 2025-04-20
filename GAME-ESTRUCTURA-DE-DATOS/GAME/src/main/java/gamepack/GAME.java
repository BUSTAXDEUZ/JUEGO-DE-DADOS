package gamepack;

import javax.swing.JOptionPane;
import java.util.Random;

/**
 * Clase principal del Juego de Dados.
 * Maneja jugadores, el lanzamiento de dados y la administración de premios y castigos.
 * 
 * @author Bryan, Bustax, Chelo
 * @version 2.1.8
 */
public class GAME {
    private static ColaJugadores jugadores = new ColaJugadores();
    private static PilaPremios premios = new PilaPremios();
    private static PilaCastigos castigos = new PilaCastigos();
    private static Random random = new Random();
    private static final String VERSION = "2.1.8";
    private static int posicionMaxima; // Declarar la variable de posición máxima
    private static final int META = 20;

    public static void main(String[] args) {
        Object[] options = {"Jugar", "Salir"};
        int inicio = JOptionPane.showOptionDialog(null,
                "BIENVENIDO AL JUEGO DE DADOS \n¿Quieres jugar?",
                "Juego de Dados",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (inicio == 0) {
            inicializarPremiosYCastigos();
            seleccionarCantidadJugadores();
            mostrarMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Juego terminado. Hasta la próxima.");
        }
    }

    private static void inicializarPremiosYCastigos() {
        premios.apilar("+2");
        premios.apilar("+8");
        premios.apilar("+0");

        castigos.apilar("-3");
        castigos.apilar("-1"); // -1 significa volver a la posición 1
        castigos.apilar("-5");
    }

    private static void mostrarMenu() {
        Object[] opciones = {"Iniciar Juego", "Editar Partida", "Editar/Añadir Jugadores", "Listar Premios/Castigos",
                "Mantener Pilas", "Estado Actual Del Juego", "Ver Historial", "Ayuda", "Salir"};

        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(null,
                    "JUEGO DE DADOS - MENÚ PRINCIPAL\nSeleccione una opción:",
                    "Menú de Juego",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (opcion) {
                case 0 -> jugar();
                case 1 -> editarPartida();
                case 2 -> editarJugadores();
                case 3 -> listarPremiosYCastigos();
                case 4 -> mantenerPilas();
                case 5 -> mostrarEstadoActual();
                case 6 -> verHistorialJugador();
                case 7 -> mostrarAyuda();
                case 8 -> JOptionPane.showMessageDialog(null, "Juego terminado.");
            }
        } while (opcion != 8);
    }

    private static void seleccionarCantidadJugadores() {
        Object[] opciones = {"1", "2", "3", "4"};
        int cantidad = JOptionPane.showOptionDialog(null,
                "Seleccione la cantidad de jugadores (máximo 4):",
                "Seleccionar jugadores",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        cantidad += 1;
        for (int i = 0; i < cantidad; i++) {
            String nombre = JOptionPane.showInputDialog("Nombre del jugador " + (i + 1) + ":");
            jugadores.encolar(new Jugador(nombre));
        }
    }

    private static void jugar() {
        if (jugadores.estaVacia()) {
            JOptionPane.showMessageDialog(null, "No hay jugadores en la cola.");
            return;
        }

        Jugador jugador = jugadores.desencolar();
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        int total = dado1 + dado2;
        int nuevaPosicion = jugador.getPosicion() + total;

        if (nuevaPosicion >= META) {
            int rebote = nuevaPosicion - META;
            if (rebote == 0) {
                JOptionPane.showMessageDialog(null, jugador.getNombre() + " ha ganado el juego!");
                System.exit(0); // Terminar el juego
            } else {
                nuevaPosicion = META - rebote; // Rebote hacia atrás
                JOptionPane.showMessageDialog(null, jugador.getNombre() + " se pasó de la meta y rebota a la posición " + nuevaPosicion);
            }
        }

        jugador.setPosicion(nuevaPosicion);

        // Aplicar premios o castigos
        if (total % 2 == 0) {
            String premio = premios.desapilar();
            int valorPremio = Integer.parseInt(premio);
            jugador.setPosicion(jugador.getPosicion() + valorPremio);
            JOptionPane.showMessageDialog(null, "Premio: " + premio + "\nNueva Posición: " + jugador.getPosicion());
        } else {
            String castigo = castigos.desapilar();
            int valorCastigo = Integer.parseInt(castigo);
            jugador.setPosicion(jugador.getPosicion() + valorCastigo);
            JOptionPane.showMessageDialog(null, "Castigo: " + castigo + "\nNueva Posición: " + jugador.getPosicion());
        }

        jugadores.encolar(jugador); // El jugador vuelve a la cola para su próximo turno
    }

    private static void listarPremiosYCastigos() {
        JOptionPane.showMessageDialog(null,
                "Premios disponibles:\n" + premios.listar() +
                        "\nCastigos disponibles:\n" + castigos.listar(),
                "Premios y Castigos",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mantenerPilas() {
        JOptionPane.showMessageDialog(null,
                "Estado actual de las Pilas:\n" +
                        "Premios: " + premios.listar() +
                        "\nCastigos: " + castigos.listar(),
                "Estado de las Pilas",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mostrarAyuda() {
    Object[] ayudaOpciones = {"FAQ", "Chatbot", "Volver"};
    int ayudaOpcion = JOptionPane.showOptionDialog(
        null,
        "CÓMO JUGAR: \n" +
        "1. Seleccione la cantidad de jugadores (máximo 4).\n" +
        "2. Cada jugador lanza dos dados.\n" +
        "3. Se suma el resultado de los dados para avanzar en el tablero.\n" +
        "4. Si el número es par, recibe un premio.\n" +
        "5. Si el número es impar, recibe un castigo.\n" +
        "6. Si el castigo es -1, el jugador regresa a la posición 1.\n" +
        "7. El primer jugador en llegar a la meta gana.\n\n" +
        "DESARROLLADO POR:\n" +
        "Bryan, Bustax y Chelo\n" +
        "Versión: " + VERSION,
        "Ayuda - Juego de Dados",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null,
        ayudaOpciones,
        ayudaOpciones[0]
    );

    if (ayudaOpcion == 0) { // FAQ
        JOptionPane.showMessageDialog(
            null,
            "PREGUNTAS FRECUENTES (FAQ):\n\n" +
            "¿Cuántos jugadores pueden participar?\n" +
            "- Hasta 4 jugadores.\n\n" +
            "¿Qué pasa si obtengo un número impar?\n" +
            "- Recibís un castigo, que puede ser retroceder o volver al inicio.\n\n" +
            "¿Cómo gano el juego?\n" +
            "- Llegando a la meta antes que los demás.\n\n" +
            "¿Qué tipo de premios existen?\n" +
            "- Puedes avanzar posiciones o recibir bonificaciones especiales.\n",
            "FAQ - Juego de Dados",
            JOptionPane.INFORMATION_MESSAGE
        );
    } else if (ayudaOpcion == 1) { // Chatbot
        Chatbot chatbot = new Chatbot(); // Crear instancia del Chatbot
        chatbot.mostrarMenu(); // Mostrar menú del Chatbot
    }
}


    private static void editarJugadores() {
        int opcion;
        do {
            String jugadoresEnSala = jugadores.estaVacia() ? "No hay jugadores en sala." : jugadores.listarJugadores();

            opcion = JOptionPane.showOptionDialog(null,
                    "EDITAR JUGADORES EN SALA\n" + jugadoresEnSala + "\nSeleccione una opción:",
                    "Editar Jugadores",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new Object[]{"Agregar Jugador", "Eliminar Jugador", "Volver"},
                    "Agregar Jugador");

            switch (opcion) {
                case 0 -> agregarJugador();
                case 1 -> eliminarJugador();
                case 2 -> JOptionPane.showMessageDialog(null, "Volviendo al menú principal.");
            }
        } while (opcion != 2);
    }

    private static void agregarJugador() {
        if (jugadores.contarJugadores() >= 4) {
            JOptionPane.showMessageDialog(null, "No se pueden agregar más jugadores. Máximo 4.");
            return;
        }
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo jugador:");
        jugadores.encolar(new Jugador(nombre));
    }

    private static void eliminarJugador() {
        if (jugadores.estaVacia()) {
            JOptionPane.showMessageDialog(null, "No hay jugadores en la lista.");
            return;
        }

        String[] nombres = jugadores.obtenerNombresJugadores();
        String jugadorSeleccionado = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el jugador a eliminar:",
                "Eliminar Jugador",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]);

        if (jugadorSeleccionado != null) {
            boolean eliminado = jugadores.eliminarJugador(jugadorSeleccionado);
            if (eliminado) {
                JOptionPane.showMessageDialog(null, jugadorSeleccionado + " ha sido eliminado.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el jugador.");
            }
        }
    }

    private static void mostrarEstadoActual() {
        if (jugadores.estaVacia()) {
            JOptionPane.showMessageDialog(null, "La partida aún no ha comenzado.");
            return;
        }

        ListaCircular estadoActual = new ListaCircular();

        NodoJugador actual = jugadores.frente; // Acceder al primer jugador en la cola
        while (actual != null) {
            estadoActual.agregarEstado(actual.jugador.getPosicion(), actual.jugador.getNombre());
            actual = actual.siguiente; // Mover al siguiente jugador
        }

        estadoActual.mostrarEstadoJuego(); // Llamar al método de ListaCircular para mostrar el estado
    }

    private static void editarPartida() {
        Object[] opcionesIngreso = {"Sí", "No"};
        int permitirIngreso = JOptionPane.showOptionDialog(null,
                "¿Permitir ingresar más jugadores después de iniciar el juego?",
                "Permitir Ingreso",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesIngreso,
                opcionesIngreso[0]);

        boolean permitirMasJugadores = (permitirIngreso == 0); // 0 es "Sí"

        Object[] opcionesPosicionMaxima = {20, 30, 40, 50, 60, 70, 80, 90, 100};
        int seleccion = JOptionPane.showOptionDialog(null,
                "Seleccione la posición máxima del laberinto:",
                "Seleccionar Posición Máxima",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesPosicionMaxima,
                opcionesPosicionMaxima[0]);

        if (seleccion >= 0) {
            posicionMaxima = (int) opcionesPosicionMaxima[seleccion];
            JOptionPane.showMessageDialog(null, "Partida editada con éxito.\n" +
                    "Permitir más jugadores: " + (permitirMasJugadores ? "Sí" : "No") + "\n" +
                    "Posición máxima: " + posicionMaxima);
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó ninguna posición máxima.");
        }
    }
    private static void verHistorialJugador() {
        if (jugadores.estaVacia()) {
            JOptionPane.showMessageDialog(null, "No hay jugadores en la partida.");
            return;
        }

    // Mostrar el historial de todos los jugadores
        ListaDobleCircular bitacora = new ListaDobleCircular();

        NodoJugador actual = jugadores.frente;
        while (actual != null) {
            bitacora.insertar(actual.jugador.getPosicion());
            actual = actual.siguiente;
        }

    bitacora.mostrarLista(jugadores); // Pasar la cola de jugadores como parámetro
}
}
