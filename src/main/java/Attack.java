public class Attack {
    private String nombre;      // Nombre del ataque
    private int precision;      // Precisión del ataque (0-100)
    private DamageRule regla;   // Regla que calcula el daño

  public Attack(String nombre, int precision, DamageRule regla) {
        this.nombre = nombre;
        this.precision = precision;
        this.regla = regla;
    }

    public boolean acierta() {
        // Genera un número aleatorio entre 1 y 100
        int numeroAleatorio = (int)(Math.random() * 100) + 1;
        // El ataque acierta si el número es menor o igual a la precisión
        return numeroAleatorio <= precision;
    }

    public int calcularDano() {
        return regla.calcularDano();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecision() {
        return precision;
    }
}