package com.example.iterator;

import com.example.model.Persona;

import java.util.Iterator;
import java.util.TreeSet;

public class PersonaCollection implements Iterable<Persona> {

    private final TreeSet<Persona> personas;

    public PersonaCollection() {
        this.personas = new TreeSet<>();
    }

    public void agregar(Persona persona) {
        personas.add(persona);
    }

    public int size() {
        return personas.size();
    }

    @Override
    public Iterator<Persona> iterator() {
        return new OrdenNaturalIterator(personas);
    }

    public Iterator<Persona> iteradorInverso() {
        return new OrdenInversoIterator(personas);
    }

    public <T extends Persona> Iterator<T> iteradorFiltrado(Class<T> tipo) {
        return new FiltradoIterator<>(personas, tipo);
    }
}
