package com.example.iterator;

import com.example.model.Persona;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class OrdenInversoIterator implements Iterator<Persona> {

    private final List<Persona> lista;
    private int posicion;

    public OrdenInversoIterator(TreeSet<Persona> personas) {
        this.lista = new ArrayList<>(personas.descendingSet());
        this.posicion = 0;
    }

    @Override
    public boolean hasNext() {
        return posicion < lista.size();
    }

    @Override
    public Persona next() {
        return lista.get(posicion++);
    }
}
