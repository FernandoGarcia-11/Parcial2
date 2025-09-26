import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private Scanner scanner;                    // Para leer entrada del usuario
    private Pokemon pokemonJugador;            // Pokémon del jugador
    private Pokemon pokemonCPU;                // Pokémon de la CPU
    private String nombreJugador;              // Nombre del jugador
    private List<BattleEvent> eventos;         // Lista de eventos de batalla
    private int turnoActual;                   // Contador de turnos

    public Game() {
        scanner = new Scanner(System.in);
        eventos = new ArrayList<>();
        turnoActual = 0;
    }

    public void iniciarJuego() {
        mostrarBienvenida();
        pedirNombreJugador();
        seleccionarPokemon();
        iniciarBatalla();
        mostrarResumen();
    }

    private void mostrarBienvenida() {
        System.out.println("========================================");
        System.out.println("    ¡Bienvenido al juego Pokémon!      ");
        System.out.println("========================================");
        System.out.println();
    }

     // Pide y guarda el nombre del jugador
    private void pedirNombreJugador() {
        System.out.print("Por favor, ingresa tu nombre: ");
        nombreJugador = scanner.nextLine();
        System.out.println("¡Hola " + nombreJugador + "!");
        System.out.println();
    }


    //El jugador selecciona su Pokémon

    private void seleccionarPokemon() {
        // Crear array con los Pokémon disponibles
        Pokemon[] pokemonsDisponibles = {
                new Charmander(),
                new Squirtle(),
                new Bulbasaur(),
                new Pikachu()
        };

        // Mostrar opciones
        System.out.println("Selecciona tu Pokémon:");
        for (int i = 0; i < pokemonsDisponibles.length; i++) {
            System.out.println((i + 1) + ". " + pokemonsDisponibles[i].getNombre() +
                    " (HP: " + pokemonsDisponibles[i].getHpMaximo() + ")");
        }

        // Validar selección del jugador
        boolean seleccionValida = false;
        while (!seleccionValida) {
            try {
                System.out.print("Tu elección (1-4): ");
                int eleccion = Integer.parseInt(scanner.nextLine());

                if (eleccion < 1 || eleccion > 4) {
                    throw new InvalidChoiceException("Debes elegir un número entre 1 y 4");
                }

                pokemonJugador = pokemonsDisponibles[eleccion - 1];
                seleccionValida = true;

            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            }
        }

        // CPU selecciona aleatoriamente
        int eleccionCPU = (int)(Math.random() * 4);
        pokemonCPU = pokemonsDisponibles[eleccionCPU];

        System.out.println();
        System.out.println("Has elegido a " + pokemonJugador.getNombre());
        System.out.println("La CPU ha elegido a " + pokemonCPU.getNombre());
        System.out.println();
    }

    private void iniciarBatalla() {
        System.out.println("¡Comienza la batalla!");
        System.out.println(pokemonJugador.getNombre() + " vs " + pokemonCPU.getNombre());
        System.out.println();

        // Bucle principal de la batalla
        while (!pokemonJugador.estaDerrotado() && !pokemonCPU.estaDerrotado()) {
            turnoActual++;
            System.out.println("--- Turno " + turnoActual + " ---");

            // Turno del jugador
            if (!pokemonJugador.estaDerrotado()) {
                turnoJugador();
            }

            // Turno de la CPU (solo si el jugador no ganó)
            if (!pokemonCPU.estaDerrotado() && !pokemonJugador.estaDerrotado()) {
                turnoCPU();
            }

            // Mostrar HP actual
            mostrarEstadoActual();
            System.out.println();
        }

        // Anunciar ganador
        if (pokemonJugador.estaDerrotado()) {
            System.out.println("¡La CPU ha ganado! " + pokemonCPU.getNombre() + " es el campeón.");
        } else {
            System.out.println("¡Felicidades " + nombreJugador + "! " + pokemonJugador.getNombre() + " ha ganado.");
        }
    }


     //Turno del jugador

    private void turnoJugador() {
        List<Attack> ataques = pokemonJugador.obtenerTodosLosAtaques();

        // Mostrar ataques usando Streams para ordenar por precisión
        System.out.println("Ataques de " + pokemonJugador.getNombre() + " (ordenados por precisión):");
        List<Attack> ataquesOrdenados = ataques.stream()
                .sorted((a1, a2) -> Integer.compare(a2.getPrecision(), a1.getPrecision()))
                .collect(Collectors.toList());

        for (int i = 0; i < ataquesOrdenados.size(); i++) {
            Attack ataque = ataquesOrdenados.get(i);
            System.out.println((i + 1) + ". " + ataque.getNombre() +
                    " (Precisión: " + ataque.getPrecision() + "%)");
        }

        // Selección de ataque
        boolean ataqueValido = false;
        while (!ataqueValido) {
            try {
                System.out.print("Elige tu ataque (1-" + ataquesOrdenados.size() + "): ");
                int eleccion = Integer.parseInt(scanner.nextLine());

                if (eleccion < 1 || eleccion > ataquesOrdenados.size()) {
                    throw new InvalidChoiceException("Debes elegir un número entre 1 y " + ataquesOrdenados.size());
                }

                Attack ataqueElegido = ataquesOrdenados.get(eleccion - 1);
                ejecutarAtaque(pokemonJugador, pokemonCPU, ataqueElegido, "Jugador");
                ataqueValido = true;

            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            }
        }
    }


     //Turno de la CPU
    private void turnoCPU() {
        System.out.println("Turno de la CPU...");

        List<Attack> ataques = pokemonCPU.obtenerTodosLosAtaques();

        // CPU prioriza ataques más precisos usando Streams
        Attack ataqueElegido = ataques.stream()
                .filter(ataque -> ataque.getPrecision() >= 70) // Filtrar ataques con buena precisión
                .max(Comparator.comparingInt(Attack::getPrecision))
                .orElse(ataques.get(0)); // Si no hay ataques precisos, usar el primero

        System.out.println(pokemonCPU.getNombre() + " usará " + ataqueElegido.getNombre());
        ejecutarAtaque(pokemonCPU, pokemonJugador, ataqueElegido, "CPU");
    }

     //Ataque entre dos Pokémon

    private void ejecutarAtaque(Pokemon atacante, Pokemon defensor, Attack ataque, String actor) {
        try {
            if (!ataque.acierta()) {
                throw new AttackMissedException(atacante.getNombre() + " usó " + ataque.getNombre() + " pero falló!");
            }

            int dano = ataque.calcularDano();
            defensor.recibirDano(dano);

            String mensaje = atacante.getNombre() + " usó " + ataque.getNombre() +
                    " e hizo " + dano + " puntos de daño a " + defensor.getNombre();
            System.out.println(mensaje);

            // Registrar evento exitoso
            eventos.add(new BattleEvent(actor, "atacó con " + ataque.getNombre(),
                    "Daño: " + dano, turnoActual));

        } catch (AttackMissedException e) {
            System.out.println(e.getMessage());

            // Registrar evento de fallo
            eventos.add(new BattleEvent(actor, "falló el ataque " + ataque.getNombre(),
                    "Sin daño", turnoActual));
        }
    }


    //Estado actual de ambos Pokémon

    private void mostrarEstadoActual() {
        System.out.println("Estado actual:");
        System.out.println(pokemonJugador.getNombre() + ": " + pokemonJugador.getHpActual() +
                "/" + pokemonJugador.getHpMaximo() + " HP");
        System.out.println(pokemonCPU.getNombre() + ": " + pokemonCPU.getHpActual() +
                "/" + pokemonCPU.getHpMaximo() + " HP");
    }


     //Resumen final de la batalla con estadísticas

    private void mostrarResumen() {
        System.out.println("\n========================================");
        System.out.println("           RESUMEN DE BATALLA           ");
        System.out.println("========================================");

        // Mostrar eventos clave (últimos 5)
        System.out.println("Eventos clave:");
        eventos.stream()
                .skip(Math.max(0, eventos.size() - 5))
                .forEach(System.out::println);

        System.out.println();

        // Estadísticas con Streams
        System.out.println("ESTADÍSTICAS:");
        System.out.println("Total de turnos: " + turnoActual);

        // Contar eventos por actor
        Map<String, Long> eventosPorActor = eventos.stream()
                .collect(Collectors.groupingBy(BattleEvent::getActor, Collectors.counting()));

        eventosPorActor.forEach((actor, cantidad) ->
                System.out.println("Acciones de " + actor + ": " + cantidad));

        // Top 3 ataques más usados
        System.out.println("\nTop 3 ataques más utilizados:");
        eventos.stream()
                .filter(evento -> evento.getAccion().contains("atacó con"))
                .map(evento -> evento.getAccion().replace("atacó con ", ""))
                .collect(Collectors.groupingBy(ataque -> ataque, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .forEach(entry -> System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " veces"));

        // Verificar si hay eventos antes de calcular precisión
        long ataquesFallidos = eventos.stream()
                .filter(evento -> evento.getDetalles().equals("Sin daño"))
                .count();

        long ataquesExitosos = eventos.stream()
                .filter(evento -> evento.getDetalles().startsWith("Daño:"))
                .count();

        // Evitar división por cero
        if (ataquesExitosos + ataquesFallidos > 0) {
            System.out.println("\nPrecisión general: " +
                    String.format("%.1f", (double)ataquesExitosos / (ataquesExitosos + ataquesFallidos) * 100) + "%");
        } else {
            System.out.println("\nPrecisión general: 0.0%");
        }

        System.out.println("Ataques exitosos: " + ataquesExitosos);
        System.out.println("Ataques fallidos: " + ataquesFallidos);

        System.out.println("\n¡Gracias por jugar!");
    }


    public static void main(String[] args) {
        Game juego = new Game();
        juego.iniciarJuego();
    }
}