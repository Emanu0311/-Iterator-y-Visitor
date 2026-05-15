package com.example.visitor;

import com.example.model.Estudiante;
import com.example.model.Docente;

public interface Visitor {
    void visit(Estudiante estudiante);
    void visit(Docente docente);
}
