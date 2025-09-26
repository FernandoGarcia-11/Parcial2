public class BattleEvent {
    private String actor;           // Quien realizó la acción (Jugador/CPU)
    private String accion;          // Qué hizo (atacó, falló, etc.)
    private String detalles;        // Detalles específicos del evento
    private int turno;              // En qué turno ocurrió

    public BattleEvent(String actor, String accion, String detalles, int turno) {
        this.actor = actor;
        this.accion = accion;
        this.detalles = detalles;
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Turno " + turno + ": " + actor + " " + accion + " - " + detalles;
    }

    // Métodos getter
    public String getActor() {
        return actor;
    }

    public String getAccion() {
        return accion;
    }

    public String getDetalles() {
        return detalles;
    }

    public int getTurno() {
        return turno;
    }
}