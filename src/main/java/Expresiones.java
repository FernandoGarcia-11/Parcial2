class InvalidChoiceException extends Exception {

    public InvalidChoiceException(String mensaje) {
        super(mensaje);
    }
}

class AttackMissedException extends Exception {

    public AttackMissedException(String mensaje) {
        super(mensaje);
    }
}