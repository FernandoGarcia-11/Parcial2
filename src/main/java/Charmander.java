public class Charmander extends Pokemon {

    public Charmander() {
        // Llama al constructor de la clase padre con nombre y HP
        super("Charmander", 80);
    }

    @Override
    protected void inicializarAtaquesUnicos() {
        // Ataque de fuego: daño alto pero menos preciso
        ataquesUnicos.add(new Attack("Ascuas", 75, () -> {
            // Daño variable entre 15 y 25
            return (int)(Math.random() * 11) + 15;
        }));

        // Ataque especial de fuego: muy poderoso pero poco preciso
        ataquesUnicos.add(new Attack("Lanzallamas", 60, () -> {
            // Daño variable entre 20 y 30
            return (int)(Math.random() * 11) + 20;
        }));
    }
}