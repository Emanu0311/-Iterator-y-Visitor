package com.example;

import com.example.iterator.PersonaCollection;
import com.example.model.Docente;
import com.example.model.Estudiante;
import com.example.model.Persona;
import com.example.view.View;
import com.example.visitor.ValidadorPersonaVisitor;
import com.example.visitor.Visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Controller {

    private final View vista;
    private final PersonaCollection coleccion;
    private final Visitor validador;

    public Controller() {
        this.vista = new View();
        this.coleccion = new PersonaCollection();
        this.validador = new ValidadorPersonaVisitor();
        cargarDatos();
    }

    private void cargarDatos() {
        coleccion.agregar(new Estudiante("E001", "Carlos Ramirez", "Calle 10 #45-23",
                new ArrayList<>(Arrays.asList("3001234567", "3017654321"))));

        coleccion.agregar(new Estudiante("E003", "Ana Torres", "Av. Libertador 789",
                new ArrayList<>(Arrays.asList("3109876543"))));

        coleccion.agregar(new Estudiante("E002", "Pedro Lopez", null,
                new ArrayList<>()));

        coleccion.agregar(new Estudiante("E004", null, "Carrera 5 #12-34",
                new ArrayList<>(Arrays.asList("3201112233"))));

        coleccion.agregar(new Docente("D001", "Maria Gonzalez", "Calle 50 #12-08",
                new ArrayList<>(Arrays.asList("3112223344", "3005556677"))));

        coleccion.agregar(new Docente("D00234", "Roberto Diaz", "Av. Principal 456",
                new ArrayList<>(Arrays.asList("3158889900"))));

        coleccion.agregar(new Docente(null, "Laura Martinez", "Calle 22 #5-10",
                null));

        coleccion.agregar(new Docente("D002", "Jorge Hernandez", "Carrera 15 #30-45",
                new ArrayList<>(Arrays.asList("3004443322"))));
    }

    public void iniciar() {
        vista.mostrarMensaje("Total de personas en la coleccion: " + coleccion.size());

        boolean ejecutando = true;
        while (ejecutando) {
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();

            switch (opcion) {
                case 1:
                    recorrerRegistros();
                    break;
                case 2:
                    recorridoInverso();
                    break;
                case 3:
                    recorridoEstudiantes();
                    break;
                case 4:
                    recorridoDocentes();
                    break;
                case 5:
                    agregarRegistro();
                    break;
                case 6:
                    ejecutando = false;
                    vista.mostrarDespedida();
                    break;
                default:
                    vista.mostrarOpcionInvalida();
                    break;
            }
        }
    }

    private void recorrerRegistros() {
        vista.mostrarMensaje("Recorrer registros");
        for (Persona persona : coleccion) {
            persona.accept(validador);
        }
    }

    private void recorridoInverso() {
        vista.mostrarMensaje("Recorrido inverso");
        Iterator<Persona> it = coleccion.iteradorInverso();
        while (it.hasNext()) {
            it.next().accept(validador);
        }
    }

    private void recorridoEstudiantes() {
        vista.mostrarMensaje("Recorrido solo ESTUDIANTES");
        Iterator<Estudiante> it = coleccion.iteradorFiltrado(Estudiante.class);
        while (it.hasNext()) {
            it.next().accept(validador);
        }
    }

    private void recorridoDocentes() {
        vista.mostrarMensaje("Recorrido solo DOCENTES");
        Iterator<Docente> it = coleccion.iteradorFiltrado(Docente.class);
        while (it.hasNext()) {
            it.next().accept(validador);
        }
    }

    private void agregarRegistro() {
        vista.mostrarSubmenuTipoPersona();
        int tipo = vista.leerOpcion();

        if (tipo != 1 && tipo != 2) {
            vista.mostrarOpcionInvalida();
            return;
        }

        vista.mostrarMensaje("");
        String codigo = vista.leerTexto("Codigo: ");
        String nombres = vista.leerTexto("Nombres: ");
        String direccion = vista.leerTexto("Direccion: ");
        String telefonosStr = vista.leerTexto("Telefonos (separados por coma): ");

        ArrayList<String> telefonos = new ArrayList<>();
        if (telefonosStr != null) {
            String[] partes = telefonosStr.split(",");
            for (String parte : partes) {
                String tel = parte.trim();
                if (!tel.isEmpty()) {
                    telefonos.add(tel);
                }
            }
        }

        if (tipo == 1) {
            Estudiante nuevo = new Estudiante(codigo, nombres, direccion, telefonos);
            coleccion.agregar(nuevo);
            vista.mostrarMensaje("Estudiante agregado.");
            nuevo.accept(validador);
        } else {
            Docente nuevo = new Docente(codigo, nombres, direccion, telefonos);
            coleccion.agregar(nuevo);
            vista.mostrarMensaje("Docente agregado.");
            nuevo.accept(validador);
        }

        vista.mostrarMensaje("Total de personas en la coleccion: " + coleccion.size());
    }
}
