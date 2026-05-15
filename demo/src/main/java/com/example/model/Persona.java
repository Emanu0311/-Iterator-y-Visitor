package com.example.model;

import java.util.ArrayList;
import com.example.visitor.Visitor;

public abstract class Persona implements Comparable<Persona> {

    protected String codigo;
    protected String nombres;
    protected String direccion;
    protected ArrayList<String> telefonos;

    public Persona(String codigo, String nombres, String direccion, ArrayList<String> telefonos) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.direccion = direccion;
        this.telefonos = telefonos;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    @Override
    public int compareTo(Persona otra) {
        if (this.codigo == null && otra.codigo == null) return 0;
        if (this.codigo == null) return -1;
        if (otra.codigo == null) return 1;
        return this.codigo.compareTo(otra.codigo);
    }

    public abstract void accept(Visitor visitor);

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [codigo=" + codigo +
                ", nombres=" + nombres +
                ", direccion=" + direccion +
                ", telefonos=" + telefonos + "]";
    }
}
