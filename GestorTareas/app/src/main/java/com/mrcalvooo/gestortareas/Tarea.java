package com.mrcalvooo.gestortareas;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Tarea implements Serializable {
    private String nombre;
    private boolean completada;

    public Tarea(String nombre) {
        this.nombre = nombre;
        this.completada = false;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Tarea: %-5s\t\tCompletada: %b\n", nombre, completada);
    }
}
