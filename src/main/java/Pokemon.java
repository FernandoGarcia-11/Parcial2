import java.util.*;

public abstract class Pokemon {
    protected String nombre;                    // Nombre del Pokémon
    protected int hpMaximo;                    // HP máximo
    protected int hpActual;                    // HP actual
    protected List<Attack> ataquesBasicos;     // Lista de ataques básicos
    protected List<Attack> ataquesUnicos;      // Lista de ataques únicos

public Pokemon(String nombre, int hp) {
        this.nombre = nombre;
        this.hpMaximo = hp;
        this.hpActual = hp;
        this.ataquesBasicos = new ArrayList<>();
        this.ataquesUnicos = new ArrayList<>();

        // Inicializar ataques básicos comunes a todos los Pokémon
        inicializarAtaquesBasicos();
        // Inicializar ataques únicos específicos de cada Pokémon
        inicializarAtaquesUnicos();
    }

    private void inicializarAtaquesBasicos() {
        // Ataque básico: daño fijo de 10
        ataquesBasicos.add(new Attack("Placaje", 95, () -> 10));
        // Ataque básico: daño variable entre 5 y 15
        ataquesBasicos.add(new Attack("Arañazo", 90, () -> (int)(Math.random() * 11) + 5));
    }

    protected abstract void inicializarAtaquesUnicos();

    public List<Attack> obtenerTodosLosAtaques() {
        List<Attack> todosLosAtaques = new ArrayList<>();
        todosLosAtaques.addAll(ataquesBasicos);
        todosLosAtaques.addAll(ataquesUnicos);
        return todosLosAtaques;
    }

    public void recibirDano(int dano) {
        hpActual -= dano;
        // Asegurar que el HP no sea negativo
        if (hpActual < 0) {
            hpActual = 0;
        }
    }

    public boolean estaDerrotado() {
        return hpActual <= 0;
    }

    // Métodos getter
    public String getNombre() {
        return nombre;
    }

    public int getHpActual() {
        return hpActual;
    }

    public int getHpMaximo() {
        return hpMaximo;
    }
}