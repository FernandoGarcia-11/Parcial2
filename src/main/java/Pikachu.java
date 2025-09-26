public class Pikachu extends Pokemon {

    public Pikachu() {
        // Llama al constructor de la clase padre con nombre y HP
        super("Pikachu", 75);
    }

    @Override
    protected void inicializarAtaquesUnicos() {
        // Ataque eléctrico: daño rápido y preciso
        ataquesUnicos.add(new Attack("Impactrueno", 90, () -> {
            // Daño variable entre 10 y 20
            return (int)(Math.random() * 11) + 10;
        }));

        // Ataque especial eléctrico: muy poderoso pero menos preciso
        ataquesUnicos.add(new Attack("Rayo", 70, () -> {
            // Daño variable entre 18 y 28
            return (int)(Math.random() * 11) + 18;
        }));
    }
}