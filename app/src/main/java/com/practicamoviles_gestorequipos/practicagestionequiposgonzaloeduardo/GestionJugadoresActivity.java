package com.practicamoviles_gestorequipos.practicagestionequiposgonzaloeduardo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;

public class GestionJugadoresActivity extends AppCompatActivity {
    private EditText etNombreJugador;
    private Spinner spinnerEquipos;
    private Button btnAgregarJugador, btnEliminarJugador;
    private ListView lvJugadores;
    private DBManager dbManager;
    private ArrayAdapter<String> adapterJugadores;
    private ArrayAdapter<String> adapterEquipos;
    private List<Equipo> listaEquipos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_jugadores);

        etNombreJugador = findViewById(R.id.etNombreJugador);
        spinnerEquipos = findViewById(R.id.spinnerEquipos);
        btnAgregarJugador = findViewById(R.id.btnAgregarJugador);
        btnEliminarJugador=findViewById(R.id.btnEliminarJugador);
        lvJugadores = findViewById(R.id.lvJugadores);

        dbManager = new DBManager(this);
        cargarListaEquipos();
        cargarListaJugadores();

        btnAgregarJugador.setOnClickListener(v -> {
            String nombre = etNombreJugador.getText().toString().trim();
            int equipoId = spinnerEquipos.getSelectedItemPosition();
            if (!nombre.isEmpty() && equipoId >= 0) {
                dbManager.insertarJugador(nombre, listaEquipos.get(equipoId).getId());
                Toast.makeText(this, "Jugador agregado", Toast.LENGTH_SHORT).show();
                etNombreJugador.setText("");
                cargarListaJugadores();
            } else {
                Toast.makeText(this, "Introduce un nombre y selecciona un equipo", Toast.LENGTH_SHORT).show();
            }
        });

        btnEliminarJugador.setOnClickListener(v -> {
            String nombre = etNombreJugador.getText().toString().trim();
            int equipoId = spinnerEquipos.getSelectedItemPosition();
            if(!nombre.isEmpty() && equipoId >= 0){
                dbManager.eliminarJugador(nombre);
                Toast.makeText(this, "Jugador eliminado", Toast.LENGTH_SHORT).show();
                etNombreJugador.setText("");
                cargarListaJugadores();
            } else {
                Toast.makeText(this, "Introduce un nombre y selecciona un equipo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void cargarListaEquipos() {
        listaEquipos = dbManager.obtenerEquipos();
        List<String> nombresEquipos = new ArrayList<>();
        for (Equipo equipo : listaEquipos) {
            nombresEquipos.add(equipo.getNombre());
        }
        adapterEquipos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nombresEquipos);
        spinnerEquipos.setAdapter(adapterEquipos);
    }

    private void cargarListaJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        for (Equipo equipo : listaEquipos) {
            jugadores.addAll(dbManager.obtenerJugadoresPorEquipo(equipo.getId()));
        }
        adapterJugadores = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (Jugador jugador : jugadores) {
            adapterJugadores.add(jugador.getNombre() + " - Equipo: " + jugador.getEquipoId());
        }
        lvJugadores.setAdapter(adapterJugadores);
    }
}
