package com.example.iterator;

import com.example.model.Persona;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class FiltradoIterator<T extends Persona> implements Iterator<T> {

    private final List<T> listaFiltrada;
    private int posicion;

    @SuppressWarnings("unchecked")
    public FiltradoIterator(TreeSet<Persona> personas, Class<T> tipo) {
        this.listaFiltrada = new ArrayList<>();
        for (Persona p : personas) {
            if (tipo.isInstance(p)) {
                listaFiltrada.add((T) p);
            }
        }
        this.posicion = 0;
    }

    @Override
    public boolean hasNext() {
        return posicion < listaFiltrada.size();
    }

    @Override
    public T next() {
        return listaFiltrada.get(posicion++);
    }
}
