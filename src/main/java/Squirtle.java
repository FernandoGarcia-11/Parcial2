public class Squirtle extends Pokemon {

    public Squirtle() {
        // Llama al constructor de la clase padre con nombre y HP
        super("Squirtle", 85);
    }

    @Override
    protected void inicializarAtaquesUnicos() {
        // Ataque de agua: daño moderado y buena precisión
        ataquesUnicos.add(new Attack("Pistola Agua", 85, () -> {
            // Daño variable entre 12 y 22
            return (int)(Math.random() * 11) + 12;
        }));

        // Ataque especial de agua: menos daño pero muy preciso
        ataquesUnicos.add(new Attack("Burbuja", 95, () -> {
            // Daño variable entre 8 y 18
            return (int)(Math.random() * 11) + 8;
        }));
    }
}