package com.carlos.java_exceptions.exception;

public class ParkingException extends Exception {

    private final String matricula;

    public ParkingException(String mensaje, String matricula) {
        super(mensaje);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }
}
