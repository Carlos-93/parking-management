package com.carlos.java_exceptions.model;

import com.carlos.java_exceptions.exception.ParkingException;

import java.util.ArrayList;
import java.util.Objects;

public class Parking {

    // Atributos de la clase Parking
    private final ArrayList<String> matriculas;
    private final String nombre;

    // Constructor que inicializa el nombre del parking y el número de plazas
    public Parking(String nombre, int numPlazas) {
        this.nombre = nombre;
        matriculas = new ArrayList<>(numPlazas);
        // Inicializa todas las plazas a null
        for (int i = 0; i < numPlazas; i++) {
            matriculas.add(null);
        }
    }

    // Método que devuelve el nombre del parking
    public String getNombreParking() {
        return nombre;
    }

    // Método que devuelve la entrada de la matrícula en la plaza indicada
    public void entrada(String matricula, int plaza) throws ParkingException {
        if (matricula == null || matricula.length() < 4) {
            throw new ParkingException("La matricula '" + matricula + "' no es válida", matricula);
        }
        if (plaza < 0 || plaza >= matriculas.size()) {
            throw new ParkingException("La plaza Nº" + plaza + " no es válida", matricula);
        }
        if (matriculas.get(plaza) != null) {
            throw new ParkingException("La plaza Nº" + plaza + " ya está ocupada por la matrícula '" + matriculas.get(plaza) + "'", matricula);
        }
        if (matriculas.contains(matricula)) {
            throw new ParkingException("La matrícula '" + matricula + "' ya está registrada en el " + nombre, matricula);
        }
        matriculas.set(plaza, matricula);
    }

    // Método que devuelve la salida de la matrícula del parking
    public int salida(String matricula) throws ParkingException {
        // Si la matrícula no está registrada en el parking, lanza una excepción
        if (!matriculas.contains(matricula)) {
            throw new ParkingException("no se encuentra registrada en el " + nombre, matricula);
        }
        // Devuelve la plaza donde se encuentra la matrícula y le asigna null
        int plaza = matriculas.indexOf(matricula);
        matriculas.set(plaza, null);
        return plaza;
    }

    // Método que devuelve las plazas totales del parking
    public int getPlazasTotales() {
        return matriculas.size();
    }

    // Método que devuelve las plazas ocupadas del parking
    public int getPlazasOcupadas() {
        return (int) matriculas.stream().filter(Objects::nonNull).count();
    }

    // Método que devuelve las plazas libres del parking
    public int getPlazasLibres() {
        return getPlazasTotales() - getPlazasOcupadas();
    }

    // Método que devuelve el estado total del parking
    public String getEstadoParking() {
        return "\nPlazas totales: " + getPlazasTotales() +
                "\nPlazas ocupadas: " + getPlazasOcupadas() +
                "\nPlazas libres: " + getPlazasLibres();
    }

    @Override
    // Método toString que devuelve el nombre del parking y el estado de las plazas
    public String toString() {
        StringBuilder sb = new StringBuilder(" " + nombre + "\n");
        sb.append("================\n");
        int lastPlaza = matriculas.size() - 1;
        // Recorre todas las plazas y muestra si están vacías o ocupadas
        for (int i = 0; i < matriculas.size(); i++) {
            sb.append("Plaza ").append(i).append(": ").append(matriculas.get(i) == null ? "(Vacía)" : matriculas.get(i));
            if (i != lastPlaza) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}