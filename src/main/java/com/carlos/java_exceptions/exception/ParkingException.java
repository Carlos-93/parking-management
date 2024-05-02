package com.carlos.java_exceptions.exception;

public class ParkingException extends Exception {

    private final String matricula;

    // Constructor que inicializa la excepción con el mensaje y la matrícula
    public ParkingException(String mensaje, String matricula) {
        super(mensaje);
        this.matricula = matricula;
    }

    // Método que devuelve la matrícula de la excepción
    public String getMatricula() {
        return matricula;
    }
}