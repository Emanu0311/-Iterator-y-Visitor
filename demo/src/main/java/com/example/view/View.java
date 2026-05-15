package com.example.view;

import java.util.Scanner;

public class View {

    private final Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarMenu() {
        System.out.println();
        System.out.println("MENU DE OPCIONES");
        System.out.println("1. Recorrer registros");
        System.out.println("2. Recorrer registros inverso");
        System.out.println("3. Recorrido solo Estudiantes");
        System.out.println("4. Recorrido solo Docentes");
        System.out.println("5. Agregar nuevo registro");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    public void mostrarSubmenuTipoPersona() {
        System.out.println();
        System.out.println("Que tipo de registro desea agregar?");
        System.out.println("1. Estudiante");
        System.out.println("2. Docente");
        System.out.print("Seleccione una opcion: ");
    }

    public String leerTexto(String etiqueta) {
        System.out.print(etiqueta);
        String valor = scanner.nextLine().trim();
        if (valor.isEmpty()) {
            return null;
        }
        return valor;
    }

    public int leerOpcion() {
        int opcion = -1;
        try {
            opcion = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            opcion = -1;
        }
        return opcion;
    }

    public void mostrarOpcionInvalida() {
        System.out.println("Opcion no valida. Intente de nuevo.");
    }

    public void mostrarDespedida() {
        System.out.println("Programa finalizado.");
    }
}
