package com.example.visitor;

import com.example.model.Estudiante;
import com.example.model.Docente;
import com.example.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class ValidadorPersonaVisitor implements Visitor {

    @Override
    public void visit(Estudiante estudiante) {
        System.out.println("Validando ESTUDIANTE");
        List<String> errores = validarCamposComunes(estudiante);
        mostrarResultado(estudiante, errores);
    }

    @Override
    public void visit(Docente docente) {
        System.out.println("Validando DOCENTE");
        List<String> errores = validarCamposComunes(docente);

        if (docente.getCodigo() != null && !docente.getCodigo().isBlank()) {
            if (docente.getCodigo().length() > 4) {
                errores.add("El codigo del docente excede los 4 digitos permitidos: \""
                        + docente.getCodigo() + "\" (" + docente.getCodigo().length() + " caracteres)");
            }
        }

        mostrarResultado(docente, errores);
    }

    private List<String> validarCamposComunes(Persona persona) {
        List<String> errores = new ArrayList<>();

        if (persona.getCodigo() == null || persona.getCodigo().isBlank()) {
            errores.add("El campo 'codigo' esta vacio o es nulo.");
        }
        if (persona.getNombres() == null || persona.getNombres().isBlank()) {
            errores.add("El campo 'nombres' esta vacio o es nulo.");
        }
        if (persona.getDireccion() == null || persona.getDireccion().isBlank()) {
            errores.add("El campo 'direccion' esta vacio o es nulo.");
        }
        if (persona.getTelefonos() == null || persona.getTelefonos().isEmpty()) {
            errores.add("La lista de 'telefonos' esta vacia o es nula.");
        }

        return errores;
    }

    private void mostrarResultado(Persona persona, List<String> errores) {
        System.out.println("  Persona: " + persona);

        if (errores.isEmpty()) {
            System.out.println("  Datos completos. Validacion exitosa.");
        } else {
            System.out.println("  Se encontraron " + errores.size() + " error(es):");
            for (int i = 0; i < errores.size(); i++) {
                System.out.println("     " + (i + 1) + ". " + errores.get(i));
            }
            enviarNotificacion(persona, errores);
        }
        System.out.println();
    }

    private void enviarNotificacion(Persona persona, List<String> errores) {
        System.out.println("  [NOTIFICACION] Se ha enviado una alerta sobre datos incompletos para: "
                + (persona.getNombres() != null ? persona.getNombres() : "DESCONOCIDO")
                + " (Codigo: " + (persona.getCodigo() != null ? persona.getCodigo() : "N/A") + ")");
    }
}
