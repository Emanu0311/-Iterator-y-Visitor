package com.example.model;

import java.util.ArrayList;
import com.example.visitor.Visitor;

public class Docente extends Persona {

    public Docente(String codigo, String nombres, String direccion, ArrayList<String> telefonos) {
        super(codigo, nombres, direccion, telefonos);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
