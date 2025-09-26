public class Bulbasaur extends Pokemon {

    public Bulbasaur() {
        // Llama al constructor de la clase padre con nombre y HP
        super("Bulbasaur", 90);
    }

    @Override
    protected void inicializarAtaquesUnicos() {
        // Ataque de planta: daño moderado y buena precisión
        ataquesUnicos.add(new Attack("Hoja Afilada", 80, () -> {
            // Daño variable entre 13 y 23
            return (int)(Math.random() * 11) + 13;
        }));

        // Ataque especial de planta: daño consistente
        ataquesUnicos.add(new Attack("Latigo Cepa", 88, () -> {
            // Daño fijo de 18
            return 18;
        }));
    }
}