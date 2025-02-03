package com.practicamoviles_gestorequipos.practicagestionequiposgonzaloeduardo;

public class Equipo {
    private int id;
    private String nombre;
    private int puntos;

    public Equipo(int id, String nombre, int puntos) {
        this.id = id;
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPuntos() { return puntos; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPuntos(int puntos) { this.puntos = puntos; }
}
