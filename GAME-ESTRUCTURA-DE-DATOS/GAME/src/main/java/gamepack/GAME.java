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
    private static ColaPremios premios = new ColaPremios();
    private static ColaPremios castigos = new ColaPremios();
    private static Random random = new Random();
    private static final String VERSION = "2.1.8";
    private static int posicionMaxima; // Declarar la variable de posición máxima
    private static final int META = 20;
    

    /**
     * Método principal del programa.
     * Inicia el juego y gestiona el flujo principal.
     */
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

    /**
     * Inicializa las pilas de premios y castigos con valores predefinidos.
     */
    private static void inicializarPremiosYCastigos() {
        premios.encolar("+2");
        premios.encolar("+8");
        premios.encolar("+0");

        castigos.encolar("-3");
        castigos.encolar("-1"); // -1 significa volver a la posición 1
        castigos.encolar("-5");
    }

    /**
     * Muestra el menú principal con las opciones disponibles.
     */
    // Modificar el método mostrarMenu para incluir la opción "Editar Partida"
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
            case 1 -> editarPartida(); // Nueva opción para editar partida
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

    /** 
     * Permite seleccionar la cantidad de jugadores y registrar sus nombres.
     */
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

    
        /**
     * Maneja la lógica del juego cuando un jugador lanza los dados.
     */
        /**
     * Maneja la lógica del juego cuando un jugador lanza los dados.
     */
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

    // Validar si el jugador gana o rebota
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

    // Actualizar la posición del jugador
    jugador.setPosicion(nuevaPosicion);

    // Agregar el estado del jugador a la lista circular
    EstadoActual.agregarEstado(jugador.getPosicion(), jugador.getNombre());

    JOptionPane.showMessageDialog(null, jugador.getNombre() + " sacó " + dado1 + " y " + dado2 + ".\n" +
            "Nueva Posición: " + jugador.getPosicion());

    // Aplicar premios o castigos
    if (total % 2 == 0) {
        String premio = premios.desencolarYReutilizar();
        int valorPremio = Integer.parseInt(premio);
        jugador.setPosicion(jugador.getPosicion() + valorPremio);
        JOptionPane.showMessageDialog(null, "Premio: " + premio + "\nNueva Posición: " + jugador.getPosicion());
    } else {
        String castigo = castigos.desencolarYReutilizar();
        int valorCastigo = Integer.parseInt(castigo);
        jugador.setPosicion(jugador.getPosicion() + valorCastigo);
        JOptionPane.showMessageDialog(null, "Castigo: " + castigo + "\nNueva Posición: " + jugador.getPosicion());
    }

    jugadores.encolar(jugador); // El jugador vuelve a la cola para su próximo turno
}



    /**
     * Muestra la lista de premios y castigos disponibles.
     */
    private static void listarPremiosYCastigos() {
        JOptionPane.showMessageDialog(null, 
                "Premios disponibles:\n" + premios.listar() + 
                "\nCastigos disponibles:\n" + castigos.listar(),
                "Premios y Castigos",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra el estado actual de las pilas de premios y castigos.
     */
    private static void mantenerPilas() {
        JOptionPane.showMessageDialog(null, 
                "Estado actual de las Pilas:\n" + 
                "Premios: " + premios.listar() + 
                "\nCastigos: " + castigos.listar(),
                "Estado de las Pilas",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra información sobre cómo jugar y los desarrolladores del juego.
     */
    private static void mostrarAyuda() {
        JOptionPane.showMessageDialog(null,
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
                JOptionPane.INFORMATION_MESSAGE);
    }
    
       /**
     * Permite editar la lista de jugadores en la sala (agregar o eliminar jugadores).
     */
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

    /**
     * Agrega un nuevo jugador a la lista si el límite no ha sido alcanzado.
     */
    private static void agregarJugador() {
        if (jugadores.contarJugadores() >= 4) {
            JOptionPane.showMessageDialog(null, "No se pueden agregar más jugadores. Máximo 4.");
            return;
        }
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo jugador:");
        jugadores.encolar(new Jugador(nombre));
    }

    /**
     * Permite eliminar un jugador de la lista seleccionándolo desde un menú de opciones.
     */
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
       // Declarar la variable del tablero dentro de GAME.java
    private static ListaCircular EstadoActual = new ListaCircular();

    /**
     * Muestra el estado actual del tablero con los jugadores en sus posiciones.
     */
    private static void mostrarEstadoActual() {
       if (jugadores.estaVacia()) {
        JOptionPane.showMessageDialog(null, "La partida aún no ha comenzado.");
        return;
    }

    // Limpiar el estado actual del tablero
    EstadoActual = new ListaCircular();

    // Agregar los jugadores y sus posiciones al estado actual
    NodoJugador actual = jugadores.frente; // Acceder al primer jugador en la cola
    while (actual != null) {
        // Agregar el estado del jugador a la lista circular
        EstadoActual.agregarEstado(actual.jugador.getPosicion(), actual.jugador.getNombre());
        actual = actual.siguiente; // Mover al siguiente jugador
    }

    // Mostrar el estado actual del juego
    EstadoActual.mostrarEstadoJuego(); // Llamar al método de ListaCircular para mostrar el estado
    }
     /**
     * Permite ver el historial de movimientos de un jugador.
     */
    private static void verHistorialJugador() {
        if (jugadores.estaVacia()) {
            JOptionPane.showMessageDialog(null, "No hay jugadores en la partida.");
            return;
        }

        String nombreJugador = JOptionPane.showInputDialog("Ingrese el nombre del jugador para ver su historial:");
        Jugador jugador = jugadores.buscarJugador(nombreJugador);

        if (jugador != null) {
            jugador.verHistorial();
        } else {
            JOptionPane.showMessageDialog(null, "Jugador no encontrado.");
        }
    }
    
    // Agregar el método editarPartida
private static void editarPartida() {
    // Preguntar si se permite ingresar más jugadores
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

    // Opciones para la posición máxima
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

//    // Mostrar mensaje de confirmación
//    JOptionPane.showMessageDialog(null, "Partida editada con éxito.\n" +
//            "Permitir más jugadores: " + (permitirMasJugadores ? "Sí" : "No") + "\n" +
//            "Posición máxima: " + posicionMaxima);
//    }
}
