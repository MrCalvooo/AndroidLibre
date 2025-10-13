package com.mrcalvooo.miagenda;

public class Tarea {
    private String nombre;
    private int hora, minuto;
    private String prioridad;

    public Tarea(String nombre, int hora, int minuto, String prioridad) {
        this.nombre = nombre;
        this.hora = hora;
        this.minuto = minuto;
        this.prioridad = prioridad;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrioridad() {
        return prioridad;
    }
}
