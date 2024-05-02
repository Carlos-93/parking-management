package com.carlos.java_exceptions;

import com.carlos.java_exceptions.exception.ParkingException;
import com.carlos.java_exceptions.model.Parking;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parking parking = new Parking("Parking Monlau", 10);

        while (true) {
            System.out.println("\n¿Qué deseas hacer en el " + parking.getNombreParking() + "?\n");
            System.out.println("1) Entrada de coche");
            System.out.println("2) Salida de coche");
            System.out.println("3) Mostrar parking");
            System.out.println("4) Finalizar programa");
            System.out.print("\nIntroduce una opción (1-4): ");

            int opcion;
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nError: Por favor, introduce un valor numérico válido");
                continue;
            }
            
            String matricula;
            int plaza;
            
            try {
                switch (opcion) {
                    case 1:
                        System.out.print("\nIntroduce la matrícula del coche: ");
                        matricula = scanner.nextLine();
                        System.out.print("Introduce la plaza para aparcar: ");
                        try {
                            plaza = Integer.parseInt(scanner.nextLine());
                            parking.entrada(matricula, plaza);
                            System.out.println("\nCoche aparcado correctamente en la plaza Nº" + plaza);
                            System.out.println(parking.getEstadoParking());
                        } catch (NumberFormatException e) {
                            System.out.println("\nError: Por favor, introduce un número válido para la plaza");
                        }
                        break;
                    case 2:
                        System.out.print("\nIntroduce la matrícula del coche para salir: ");
                        try {
                            matricula = scanner.nextLine();
                            plaza = parking.salida(matricula);
                            System.out.println("\nCoche retirado de la plaza Nº" + plaza);
                            System.out.println(parking.getEstadoParking());
                        } catch (ParkingException e) {
                            System.out.println("\nError: La matrícula '" + e.getMatricula() + "' " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("\n" + parking);
                        break;
                    case 4:
                        System.out.println("\nFinalizando el programa...");
                        scanner.close();
                        System.exit(0);
                        return;
                    default:
                        System.out.println("\nError: Opción inválida, introduce un número entre 1 y 4");
                }
            } catch (ParkingException e) {
                System.out.println("\nError: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\nError General: " + e.getMessage());
            }
        }
    }
}
