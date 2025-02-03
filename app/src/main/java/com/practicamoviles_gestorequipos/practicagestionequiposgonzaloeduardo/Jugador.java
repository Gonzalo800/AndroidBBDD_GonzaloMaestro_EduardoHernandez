package com.practicamoviles_gestorequipos.practicagestionequiposgonzaloeduardo;

public class Jugador {
    private int id;
    private String nombre;
    private int equipoId;

    public Jugador(int id, String nombre, int equipoId) {
        this.id = id;
        this.nombre = nombre;
        this.equipoId = equipoId;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getEquipoId() { return equipoId; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEquipoId(int equipoId) { this.equipoId = equipoId; }
}
