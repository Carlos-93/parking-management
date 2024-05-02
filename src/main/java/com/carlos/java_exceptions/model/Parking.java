package com.carlos.java_exceptions.model;

import com.carlos.java_exceptions.exception.ParkingException;

import java.util.ArrayList;
import java.util.Objects;

public class Parking {

    private final ArrayList<String> matriculas;
    private final String nombre;

    public Parking(String nombre, int numPlazas) {
        this.nombre = nombre;
        matriculas = new ArrayList<>(numPlazas);
        for (int i = 0; i < numPlazas; i++) {
            matriculas.add(null);
        }
    }

    public String getNombreParking() {
        return nombre;
    }

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

    public int salida(String matricula) throws ParkingException {
        if (!matriculas.contains(matricula)) {
            throw new ParkingException("no se encuentra registrada en el " + nombre, matricula);
        }
        int plaza = matriculas.indexOf(matricula);
        matriculas.set(plaza, null);
        return plaza;
    }

    public int getPlazasTotales() {
        return matriculas.size();
    }

    public int getPlazasOcupadas() {
        return (int) matriculas.stream().filter(Objects::nonNull).count();
    }

    public int getPlazasLibres() {
        return getPlazasTotales() - getPlazasOcupadas();
    }

    public String getEstadoParking() {
        return "\nPlazas totales: " + getPlazasTotales() +
                "\nPlazas ocupadas: " + getPlazasOcupadas() +
                "\nPlazas libres: " + getPlazasLibres();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" " + nombre + "\n");
        sb.append("================\n");
        int lastPlaza = matriculas.size() - 1;
        for (int i = 0; i < matriculas.size(); i++) {
            sb.append("Plaza ").append(i).append(": ").append(matriculas.get(i) == null ? "(Vacía)" : matriculas.get(i));
            if (i != lastPlaza) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
